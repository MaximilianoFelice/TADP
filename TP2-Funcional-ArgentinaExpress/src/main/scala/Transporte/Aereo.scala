package ArgentinaExpress.Transporte.Transportes

import ArgentinaExpress.Caracteristica.Caracteristicas._
import ArgentinaExpress.Sucursal.CasaCentral
import org.joda.time.DateTime

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
  
  val valorBonusVolumen: Double = 3

   val porcImpuesto = 0.1

  def coefImpuesto: Double = {
    if (origen.pais != sucursalDestino.pais) 1 + porcImpuesto
    else 1
  }
  
  def bonusPorCasaCentral: Double = {
    (sucursalDestino, DateTime.now.getDayOfMonth) match {
      case (CasaCentral(_), dia) if dia > 20 => 0.8 //Pasado el día 20
      case _ => 1.0
    }
  }

}
