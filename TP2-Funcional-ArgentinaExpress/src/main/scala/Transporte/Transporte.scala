package ArgentinaExpress.Transporte

import ArgentinaExpress.Envio.Envio

/**
 * Created by maximilianofelice on 05/11/14.
 */
abstract class Transporte (val capacidad:Int,
                          val costoBase: Double,
                          val velocidadPromedio: Int){

  var envios: Set[Envio] = Set()

  def volumenOcupado: Int = {
    envios.map(_.volumen).sum
  }

}

