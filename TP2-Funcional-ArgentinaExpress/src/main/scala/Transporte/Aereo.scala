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
  
  def distanciaEntreSucursales: Double = {
    distanciaAereaEntre(origen, sucursalDestino)
  }
  
  override def subtotal: Double = {
    super.subtotal * coefImpuesto
  }
  
  def valorBonusVolumen: Double = {
    3 //TODO quizás sea mejor crear un val
  }

   val porcImpuesto = 1.1

  def coefImpuesto: Double = {
    if (origen.pais != sucursalDestino.pais) 1 + porcImpuesto
    else 1
  }

}
