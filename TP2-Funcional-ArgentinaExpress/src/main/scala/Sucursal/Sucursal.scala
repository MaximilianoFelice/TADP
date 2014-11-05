package ArgentinaExpress.Sucursal

import ArgentinaExpress.Envio.Envio

/**
 * Created by maximilianofelice on 05/11/14.
 */
class Sucursal (
  val volumenDeposito: Integer){

  var enviosDeposito: Set[Envio] = Set()
  var enviosViajando: Set[Envio] = Set()

  def espacioDisponible: Integer = {
    volumenDeposito - enviosDeposito.map(_.volumen).sum - enviosViajando.map(_.volumen).sum

  }

}
