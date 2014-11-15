package ArgentinaExpress.Transporte

import ArgentinaExpress.Transporte.Transportes._
import ArgentinaExpress.Caracteristica.Caracteristicas._

/**
 * Created by maximilianofelice on 05/11/14.
 */
abstract class Terrestre (capacidad:Int, costoBase:Float, val costoPeaje: Double, velocidadPromedio:Int, caracteristicas: Seq[Caracteristica])
  extends Transporte(capacidad, costoBase, velocidadPromedio, caracteristicas) {
	
  def distanciaEntreSucursales: Double = {
    distanciaTerrestreEntre(origen, sucursalDestino)
  }
  
  override def subtotal: Double ={
    super.subtotal + costoTotalPeaje + bonusRefrigeracion
  }

  def bonusRefrigeracion: Double = {
    envios.count(_.caracteristicas.contains(Refrigerado)) * costoRefrig
  }
  
  def costoTotalPeaje: Double ={
    costoPeaje + cantidadPeajesEntre(origen, sucursalDestino)
  }

  val costoRefrig: Double = 5

}

case class Camion(override val caracteristicas: Seq[Caracteristica] = Nil) extends Terrestre(capacidad=45, costoBase=100, costoPeaje=12, velocidadPromedio=60, caracteristicas){

  def agregarCaracteristica (caracteristica: Caracteristica): Camion = {
    val caracs: Seq[Caracteristica] = caracteristicas :+ caracteristica
    new Camion(caracs)
  }
  
  def valorBonusVolumen: Double = {
    1 + porcentajeVolumenOcupado
  }

}

case class Furgoneta(override val caracteristicas: Seq[Caracteristica] = Nil) extends Terrestre(capacidad=9, costoBase=40, costoPeaje=6, velocidadPromedio=80, caracteristicas){

  def agregarCaracteristica (caracteristica: Caracteristica): Furgoneta = {
    val caracs: Seq[Caracteristica] = caracteristicas :+ caracteristica
    new Furgoneta(caracs)
  }

  def valorBonusVolumen: Double = {
    if (envios.count(_.caracteristicas.contains(Urgente)) >= 3) 2
    else 1
  }
  
}
