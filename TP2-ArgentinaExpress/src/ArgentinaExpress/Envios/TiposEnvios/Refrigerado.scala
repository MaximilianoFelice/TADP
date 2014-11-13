package ArgentinaExpress.Envios.TiposEnvios
import ArgentinaExpress.Envios.EnvioInterface
import ArgentinaExpress.Envios.TiposEnvios.DecoratorEnvios

class Refrigerado(var decorated: EnvioInterface) extends DecoratorEnvios {

  def costoBase(acumulado:Float):Float = {70 + acumulado}
}