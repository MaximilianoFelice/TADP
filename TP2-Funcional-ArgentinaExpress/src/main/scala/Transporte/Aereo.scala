package ArgentinaExpress.Transporte.Transportes

import ArgentinaExpress.Caracteristica.Caracteristicas._

/**
 * Created by maximilianofelice on 05/11/14.
 */
case class Avion(override val caracteristicas: Seq[Caracteristica]) extends Transporte(capacidad=200, costoBase=500, velocidadPromedio=500, caracteristicas: Seq[Caracteristica]){

  def agregarCaracteristica (caracteristica: Caracteristica): Avion = {
    val caracs: Seq[Caracteristica] = caracteristicas :+ caracteristica
    new Avion(caracs)
  }

  override def costo: Double = {
    super.costo * porcImpuesto
  }

  val porcImpuesto = 0.1
}
