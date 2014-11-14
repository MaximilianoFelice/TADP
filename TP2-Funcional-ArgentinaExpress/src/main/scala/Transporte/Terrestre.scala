package ArgentinaExpress.Transporte

import ArgentinaExpress.Transporte.Transportes._
import ArgentinaExpress.Caracteristica.Caracteristicas._

/**
 * Created by maximilianofelice on 05/11/14.
 */
abstract class Terrestre (capacidad:Int, costoBase:Float, val costoPeaje: Double, velocidadPromedio:Int, caracteristicas: Seq[Caracteristica])
  extends Transporte(capacidad, costoBase, velocidadPromedio, caracteristicas) {

  override def costo: Double ={
    super.costo  + costoPeaje + bonusRefrigeracion
  }

  def bonusRefrigeracion: Double = {
    envios.count(_.caracteristicas.contains(Refrigerado)) * costoRefrig
  }

  val costoRefrig: Double = 5

}

case class Camion(override val caracteristicas: Seq[Caracteristica] = Nil) extends Terrestre(capacidad=45, costoBase=100, costoPeaje=12, velocidadPromedio=60, caracteristicas){

  def agregarCaracteristica (caracteristica: Caracteristica): Camion = {
    val caracs: Seq[Caracteristica] = caracteristicas :+ caracteristica
    new Camion(caracs)
  }

}

case class Furgoneta(override val caracteristicas: Seq[Caracteristica] = Nil) extends Terrestre(capacidad=9, costoBase=40, costoPeaje=6, velocidadPromedio=80, caracteristicas){

  def agregarCaracteristica (caracteristica: Caracteristica): Furgoneta = {
    val caracs: Seq[Caracteristica] = caracteristicas :+ caracteristica
    new Furgoneta(caracs)
  }

}
