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
  //TODO Sucursal origen ??
    var envios: Set[Envio] = Set()

    def volumenOcupado: Int = {
      envios.map(_.volumen).sum
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

    def salirDe (origen: Sucursal) = {
      if (puedeSalir){
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

    def costo: Double = {
      envios.map(_.costo).sum + costoBase
    }
  }

  val insertarCaracteristica: (Transporte, Caracteristica) => Option[Transporte] = {
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

}