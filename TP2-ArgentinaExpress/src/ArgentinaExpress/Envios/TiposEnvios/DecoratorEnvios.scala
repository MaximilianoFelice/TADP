package ArgentinaExpress.Envios.TiposEnvios

import ArgentinaExpress.Transportes.Transporte
import ArgentinaExpress.Sucursal.Sucursal
import ArgentinaExpress.Envios.EnvioInterface

trait DecoratorEnvios extends EnvioInterface {

	var decorated: EnvioInterface
	
  	def origen: Sucursal ={
		decorated.origen
  	}
  	
	def destino: Sucursal = {
	  decorated.destino
	}
	
	// TODO: Refactor to JodaTime
	def fechaSalida: Int={
	  decorated.fechaSalida
	}
	def fechaLlegada: Int={
	  decorated.fechaLlegada
	}
	
	def peso: Double={
	  decorated.peso
	}
	
	def transporte: Transporte = {
	  decorated.transporte
	}
	
}