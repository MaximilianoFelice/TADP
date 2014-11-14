package ArgentinaExpress.Envio

import ArgentinaExpress.Caracteristica.Caracteristicas._
import ArgentinaExpress.Sucursal.Sucursal

package object Envios {

  /**
   * Created by maximilianofelice on 05/11/14.
   */

  implicit def envioATupla (e: Envio): EnvioTup = (e.costo, e.destino, e.volumen, e.caracteristicas)
  implicit def tuplaAEnvio (e: EnvioTup): Envio = new Envio(e._1, e._2, e._3, e._4)

  type EnvioTup = (Double, Sucursal, Int, Seq[Caracteristica])

  case class Envio
  (val costo: Double,
   val destino: Sucursal,
   val volumen: Int,
   val caracteristicas: Seq[Caracteristica]) {
  }


  val insertarCaracteristica: (EnvioTup, Caracteristica) => Option[Envio] = {
    case ((x,y,z,caracteristicas), c) if sePuedeAgregar(c, caracteristicas) => Some((x,y,z, caracteristicas))
    case _ => None
  }

  val agregarCaracteristica: (Caracteristica, Envio) => Envio = {
    case (carac, envio) => {
      (for {
            envioActualizado <- insertarCaracteristica(envio, carac)
      } yield carac.actualizarEnvio(envioActualizado) ) match {
        case Some(envioAc) => envioAc
        case None => envio
      }
    }
  }

}