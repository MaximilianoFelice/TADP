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
    var viajes: List[Viaje] = List()
    var transportes: List[Transporte] = List()
    var envios: List[Envio] = List()

    def sucursales = viajes.sucursales
    def addViaje (viaje: Viaje) = viajes :+ viaje

    def Get[T](tipo: T): Set[T]={
        ???
    }
  }

  trait filterable[+A] {
    def col : List[A]
    def Where(filt: A => Boolean): List[A] = col.filter(filt)
  }

  implicit class operatorsExt[T <: Double](left: T){
    /* TODO: Abstraer logica repetida */
    def gT[A <: Double, B <: Double](l: A)(r: B): Boolean = (l > r)
    def lT[A <: Double, B <: Double](l: A)(r: B): Boolean = (l < r)
    def equa[A <: Double, B <: Double](l: A)(r: B): Boolean = (l == r)
    def greaterThan[A, F <: Double](right: A => F) = gT(left)_ compose right
    def lesserThan[A, F <: Double](right: A => F): A => Boolean = lT(left)_ compose right
    def equalsThan[A, F <: Double](right: A => F): A => Boolean = equa(left)_ compose right

  }
  implicit class binaryExt[A](left: A => Boolean){
    def partialForAll[A](coll: Set[A => Boolean])(elem: A): Boolean = coll.forall(x => x(elem))
    def partialAny[A](coll: Set[A => Boolean])(elem: A): Boolean = coll.exists(x => x(elem))
    /* TODO: Abstraer logica repetida */
    def and(right: A => Boolean): A => Boolean = partialForAll(Set(left, right))
    def or(right: A => Boolean): A => Boolean = partialAny(Set(left, right))
  }



  implicit class Viajes (val col: List[Viaje]) extends filterable[Viaje]{
    def sucursales: List[Sucursal] = col.flatMap(v => v.sucursales)
    def costoPromedio: Double = col.promedio(_.costo)
    def tiempoPromedio: Double = col.promedio(_.tiempo)
    def gananciaBrutaPromedio: Double = col.promedio(_.gananciaBruta)
    def cantidadEnvios: Int = col.map(_.cantidadEnvios).sum
    def cantidadViajes: Int = col.size
    def facturacionTotal: Double = col.map(_.beneficio).sum

    def transportes: List[Transporte] = col.map(_.transporte)
    def envios: List[Envio] = col.flatMap(_.envios)

    def filtroPorCaracteristica(carac: Caracteristica): List[Viaje] = col.filter(v => v.transporte.caracteristicas.contains(carac))

    def promedio(criterio: Viaje => Double): Double = col.map(criterio).sum / col.size
  }

  implicit class Transportes[+T <: Transporte] (val col: List[T]) extends filterable[T]{
    def envios: List[Envio] = Estadisticas.envios
    def filtroPorCaracteristica(carac: Caracteristica): List[T] = col.filter(t => t.caracteristicas.contains(carac)).toList
    def filtroPorTipo[G <: Transporte](tipo: G): List[T] = col.filter(t => t match {
      case a if a.getClass == `tipo`.getClass => true
      case _ => false})
  }

  implicit class Envios (val col: List[Envio]) extends filterable[Envio]{
    def filtroPorCaracteristica(carac: Caracteristica): Envios = col.filter(_.caracteristicas.contains(carac))
  }

  implicit class Sucursales (val col: List[Sucursal]) extends filterable[Sucursal]{
    val facturacionTotal: Sucursal => Double = {_ => 1000}
    val aSet: List[Sucursal] = List(new Sucursal(10), new Sucursal(100))
  }

}