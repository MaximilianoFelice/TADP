package ArgentinaExpress.Sucursal

import ArgentinaExpress.Envio.Envio

/**
 * Created by maximilianofelice on 05/11/14.
 */
class Sucursal (
  val volumenDeposito: Int){

  var enviosDeposito: Set[Envio] = Set()
  var enviosViajando: Set[Envio] = Set()

  def espacioDisponible: Int = {
    volumenDeposito - enviosDeposito.map(_.volumen).sum - enviosViajando.map(_.volumen).sum

  }

}

