package ArgentinaExpress.Transporte

import ArgentinaExpress.Envio.Envio

/**
 * Created by maximilianofelice on 05/11/14.
 */
abstract class Transporte (val capacidad:Integer,
                          val costoBase: Double,
                          val velocidadPromedio: Integer){

  var envios: Set[Envio] = Set()

  def volumenOcupado: Integer = {
    envios.map(_.volumen).sum
  }

}

