package ArgentinaExpress.Envio

import ArgentinaExpress.Caracteristica.Caracteristica
import ArgentinaExpress.Sucursal.Sucursal

package object Envios {

  /**
   * Created by maximilianofelice on 05/11/14.
   */

  implicit def envioATupla (e: Envio): EnvioTup = (e.costo, e.origen, e.destino, e.volumen, e.caracteristicas)
  implicit def tuplaAEnvio (e: EnvioTup): Envio = new Envio(e._1, e._2, e._3, e._4, e._5)

  type EnvioTup = (Double, Sucursal, Sucursal, Int, Seq[Caracteristica])

  case class Envio
  (val costo: Double,
   val origen: Sucursal,
   val destino: Sucursal,
   val volumen: Int,
   val caracteristicas: Seq[Caracteristica]) {
  }


  val insertarCaracteristica: ((EnvioTup, Caracteristica)) => Option[Envio] = {
    case (a, c) if a.caracteristicas.contains(c) => None
    case ((x,y,z,w,caracteristicas), c) => Some((x,y,z,w, caracteristicas))
  }

  val agregarCaracteristica: (Caracteristica, Envio) => Envio = {
    case (carac, envio) => {
      (for {
            envioActualizado <- insertarCaracteristica(envio, carac)
      } yield carac.f(envioActualizado) ) match {
        case Some(envioAc) => envioAc
        case None => envio
      }
    }
  }

}