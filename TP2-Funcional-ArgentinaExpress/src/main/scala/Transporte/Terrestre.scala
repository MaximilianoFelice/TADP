package ArgentinaExpress.Transporte

/**
 * Created by maximilianofelice on 05/11/14.
 */
abstract class Terrestre (capacidad:Integer, costoBase:Float, val costoPeaje: Double, velocidadPromedio:Integer)
  extends Transporte(capacidad, costoBase, velocidadPromedio) {



  val costoRefrig: Double = 5

}

case class Camion() extends Terrestre(capacidad=45, costoBase=100, costoPeaje=12, velocidadPromedio=60){

}

case class Furgoneta() extends Terrestre(capacidad=9, costoBase=40, costoPeaje=6, velocidadPromedio=80){

}
