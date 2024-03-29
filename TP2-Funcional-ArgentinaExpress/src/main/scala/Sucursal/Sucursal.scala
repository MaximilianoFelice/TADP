package ArgentinaExpress.Sucursal

import ArgentinaExpress.Envio.Envios._
import ArgentinaExpress.Transporte.Transportes._
import ArgentinaExpress.Estadisticas.EstadisticasPack._

/**
 * Created by maximilianofelice on 05/11/14.
 */
class Sucursal (
  val volumenDeposito: Int){

  val pais: String = "Argentina" //TODO Ver si ponerlo en el constructor, sin chequeo imposible
  var enviosDeposito: Set[Envio] = Set()
  var enviosViajando: Set[Envio] = Set()

  def espacioDisponible: Int = {
    volumenDeposito - enviosDeposito.map(_.volumen).sum - enviosViajando.map(_.volumen).sum
  }

  def recibirEnvios(envios: Set[Envio]) = {
    enviosDeposito = enviosDeposito ++ envios
  }

  def disminuirStock(envios: Set[Envio]) = {
    enviosDeposito = enviosDeposito -- envios
  }

  def enViaje(envios: Set[Envio]) = {
    enviosViajando = enviosViajando ++ envios
  }

  def despachar(trans: Transporte) = {
    trans.salirDe(this)
  }

  //ESTADÍSTICAS

  def viajesTotales = Estadisticas.viajes

  def viajesSalientes = viajesTotales.filter(_.origen == this)

  def viajesEntrantes = viajesTotales.filter(_.destino == this)

}

//trait CalculadorDistancia {
//  def distanciaTerrestreEntre(sucursal1: Sucursal, sucursal2: Sucursal): Double = 3
//  def distanciaAereaEntre(sucursal1: Sucursal, sucursal2: Sucursal): Double = 5
//  def cantidadPeajesEntre(sucursal1: Sucursal, sucursal2: Sucursal): Int = 1
//}

case class CasaCentral(override val volumenDeposito: Int = 50) extends Sucursal(volumenDeposito)
