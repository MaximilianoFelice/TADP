package ArgentinaExpress.Envios

import ArgentinaExpress.Transportes.Transporte
import ArgentinaExpress.Sucursal.Sucursal
import ArgentinaExpress.Envios.TiposEnvios.DecoratorEnvios

class Envio ( var origen: Sucursal,		
		var destino: Sucursal,
		var fechaSalida: Int,
		var fechaLlegada: Int,
		
		var peso: Double,
		
		var transporte: Transporte)
		extends EnvioInterface {
  
		var proxiedEnvio: EnvioInterface = new EnvioPosta(origen, destino, fechaSalida, fechaLlegada, peso, transporte);
		
		def agregarTipo(envType: DecoratorEnvios):Unit = {
		  
		}

}