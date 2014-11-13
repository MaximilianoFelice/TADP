package ArgentinaExpress.Envios

import ArgentinaExpress.Transportes.Transporte
import ArgentinaExpress.Sucursal.Sucursal
import ArgentinaExpress.Envios.TiposEnvios.DecoratorEnvios

class EnvioPosta (var origen: Sucursal,
		var destino: Sucursal,
		var fechaSalida: Int,
		var fechaLlegada: Int,
		
		var peso: Double,
		
		var transporte: Transporte
    ) extends EnvioInterface {
	
	
	def agregarTipo(envType: DecoratorEnvios): Unit;

}