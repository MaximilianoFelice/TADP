package ArgentinaExpress.Transporte

import java.lang.NoSuchMethodError

import ArgentinaExpress.Caracteristica.Caracteristicas._
import ArgentinaExpress.Envio.Envios._
import ArgentinaExpress.Sucursal.Sucursal

/**
 * Created by maximilianofelice on 05/11/14.
 */

package object Transportes{

  abstract class Transporte (val capacidad:Int,
                            val costoBase: Double,
                            val velocidadPromedio: Int,
                            val caracteristicas: Seq[Caracteristica]){

    //TODO Extras(Seguimiento GPS y/o Video)
    
    var origen: Sucursal = CasaCentral //Sucursal por defecto es CasaCentral
    
    var envios: Set[Envio] = Set()

    def volumenOcupado: Int = {
      envios.map(_.volumen).sum
    }
    
    def porcentajeVolumenOcupado: Double = {
      volumenOcupado / capacidad
    }
    
    def volumenDisponible: Int = {
      capacidad - volumenOcupado
    }

    def agregarCaracteristica (c:Caracteristica): Transporte


    def destino(e: Envio): Sucursal = {
      if (envios.isEmpty) e.destino
      else sucursalDestino
    }

    def sucursalDestino: Sucursal = {
      envios.head.destino
    }

    def salirDe (sucursal: Sucursal) = {
      if (puedeSalir){
        origen = sucursal
        origen.disminuirStock(envios)
        sucursalDestino.enViaje(envios)
      }
    }

    def puedeSalir = {
      sucursalDestino.espacioDisponible >= volumenOcupado
    }

    def esAgregable(e: Envio): Boolean = {
      volumenDisponible >= e.volumen & e.caracteristicas.intersect(caracteristicas).size == e.caracteristicas.size & destino(e) == e.destino
    }

    def agregarEnvio(e: Envio) = {
      if (esAgregable(e)) envios += e
      else throw new Error("No se pudo agregar el envío, papá ;)") //TODO Crear class para la exception
    }
    
    def distanciaEntreSucursales: Double
    
    def valorBonusVolumen: Double
    
    def bonusVolumen: Double = {
      if (porcentajeVolumenOcupado < 0.2) valorBonusVolumen
      else 1
    }
    
    def subtotal: Double = {
      envios.map(_.costo).sum + costoDistancia
    }
    
    def costoDistancia: Double = {
      (costoBase + costoExtras) * distanciaEntreSucursales + extraDistancia(distanciaEntreSucursales)
    }
    
    val extraDistancia: Double => Double = {
      case d if d < 100 => 50
      case d if d < 200 => 86
      case _ => 137
    }
    
    def costoExtras: Double = {
      //TODO extras.map(_.costoPorKilometro).sum
      0
    }
    
    def costo: Double = {
      subtotal * bonusVolumen
    }
  }

  val insertarCaracteristica: (Transporte, Caracteristica) => Option[Transporte] = {
    // TODO Ver que tan escalable es algo así o hacer algo parecido al env{io (actualizarTransporte) case (t, SustanciasPeligrosas) if sePuedeAgregar(SustanciasPeligrosas, t.caracteristicas) => 
  	case (t,c) if sePuedeAgregar(c, t.caracteristicas) => Some((t.agregarCaracteristica(c)))
    case (t,c) => None
  }

  //TODO Ver como patear la lógica repetida
  val agregarCaracteristica: (Caracteristica, Transporte) => Transporte = {
    case (carac, transp) => {
      (for {
              transpActualizado <- insertarCaracteristica(transp, carac)
      } yield transpActualizado ) match {
        case Some(transpAc) => transpAc
        case None => transp
      }
    }
  }
  
  //Mockeando cálculo de distancias
  val distanciaTerrestreEntre: (Sucursal, Sucursal) => Double = {case _ => 3}
  val distanciaAereaEntre: (Sucursal, Sucursal) => Double = {case _ => 5}
  val cantidadPeajesEntre: (Sucursal, Sucursal) => Int = {case _ => 1}
  
}