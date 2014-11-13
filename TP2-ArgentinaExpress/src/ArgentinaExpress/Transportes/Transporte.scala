package ArgentinaExpress.Transportes

import ArgentinaExpress.Envios.EnvioInterface

trait Transporte {

  def cuantoSale(envio: EnvioInterface): Float
  
}