package ArgentinaExpress.Envios
import ArgentinaExpress.Sucursal.Sucursal
import ArgentinaExpress.Transportes.Transporte
import ArgentinaExpress.Envios.TiposEnvios.DecoratorEnvios

trait EnvioInterface {
  
	def origen: Sucursal  	
	def destino: Sucursal
	
	// TODO: Refactor to JodaTime
	def fechaSalida: Int
	def fechaLlegada: Int
	
	def peso: Double
	
	def transporte: Transporte
	
	//def costoBase(acumulado: Float): Float
	
	//def cuantoSale(transporte: Transporte): Float = { transporte.cuantoSale(this) }
	
	def AllowsTipoEnvio(envioType: DecoratorEnvios): Boolean
	
	def agregarTipo(envType: DecoratorEnvios): Unit
	
}
