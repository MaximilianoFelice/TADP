package ArgentinaExpress.Envios.TiposEnvios

import ArgentinaExpress.Envios.TiposEnvios.DecoratorEnvios
import ArgentinaExpress.Envios.EnvioInterface

class Urgente (var decorated: EnvioInterface)extends DecoratorEnvios {
	
  def costoBase(acumulado:Float):Float = {20 + acumulado}
  
}