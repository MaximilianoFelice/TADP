package ArgentinaExpress.Envio

import ArgentinaExpress.Paquete.Paquete

/**
 * Created by maximilianofelice on 05/11/14.
 */
case class Envio
  (val paquetes: Seq[Paquete]){

  def volumen: Integer = {
    paquetes.map(_.volumen).sum
  }
}
