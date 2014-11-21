package ArgentinaExpress.Estadisticas

import ArgentinaExpress.Caracteristica.Caracteristicas.Caracteristica
import ArgentinaExpress.Envio.Envios._
import ArgentinaExpress.Sucursal.Sucursal
import ArgentinaExpress.Transporte.Transportes._
import Viaje.Viaje

/**
 * Created by maximilianofelice on 19/11/14.
 */

package object EstadisticasPack {

  case object Estadisticas {
    var viajes: Set[Viaje] = Set()
    var transportes: Set[Transporte] = Set()
    var envios: Set[Envio] = Set()

    def sucursales = viajes.sucursales
    def addViaje (viaje: Viaje) = viajes += viaje
  }

  implicit class Viajes (viajes: Set[Viaje]){
    def sucursales: Set[Sucursal] = viajes.flatMap(v => v.sucursales)
    def costoPromedio: Double = viajes.promedio(_.costo)
    def tiempoPromedio: Double = viajes.promedio(_.tiempo)
    def gananciaBrutaPromedio: Double = viajes.promedio(_.gananciaBruta)
    def cantidadEnvios: Int = viajes.map(_.cantidadEnvios).sum
    def cantidadViajes: Int = viajes.size
    def facturacionTotal: Double = viajes.map(_.beneficio).sum

    def transportes: Set[Transporte] = viajes.map(_.transporte)
    def envios: Set[Envio] = viajes.flatMap(_.envios)

    def filtroPorCaracteristica(carac: Caracteristica): Set[Viaje] = viajes.filter(v => v.transporte.caracteristicas.contains(carac))

    def promedio(criterio: Viaje => Double): Double = viajes.map(criterio).sum / viajes.size
  }

  implicit class Transportes[+T <: Transporte] (transportes: Seq[T]){
    def envios: Set[Envio] = Estadisticas.envios
    def filtroPorCaracteristica(carac: Caracteristica): Seq[T] = transportes.filter(t => t.caracteristicas.contains(carac))
    def filtroPorTipo[G <: Transporte](tipo: G): Seq[T] = transportes.filter(t => t match {
      case a if a.getClass == `tipo`.getClass => true
      case _ => false})
  }

  implicit class Envios (envios: Set[Envio]){
    def filtroPorCaracteristica(carac: Caracteristica): Envios = envios.filter(_.caracteristicas.contains(carac))
  }


}