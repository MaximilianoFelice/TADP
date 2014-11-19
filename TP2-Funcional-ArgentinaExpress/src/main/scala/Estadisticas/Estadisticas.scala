package Estadisticas

import ArgentinaExpress.Caracteristica.Caracteristicas.Caracteristica
import ArgentinaExpress.Envio.Envios._
import ArgentinaExpress.Sucursal.Sucursal
import ArgentinaExpress.Transporte.Transportes._

/**
 * Created by maximilianofelice on 19/11/14.
 */

package object EstadisticasPack {

  case object Estadisticas {
    var transportes: Set[Transporte] = Set()
    var envios: Set[Envio] = Set()
    def sucursales = transportes.sucursales
  }


  trait Funcionaloso {

  }

  implicit class Sucursales (sucursales: Set[Sucursal]){
    def transportes: Transportes[Transporte] = Estadisticas.transportes
    //def filtroPorTipoTransporte[T <: Transporte]: Transportes[T] = ???

  }

  implicit class Transportes[T <: Transporte] (transportes: Set[T]){
    def envios: Set[Envio] = Estadisticas.envios
    def filtroPorCaracteristica(carac: Caracteristica): Set[T] = transportes.filter(t => t.caracteristicas.contains(carac))
    def sucursales: Set[Sucursal] = transportes.flatMap(t => t.sucursales)
    def hola: Int = 3
  }

  implicit class Envios (envios: Set[Envio]){
    def filtroPorCaracteristica(carac: Caracteristica): Envios = ???
  }

}