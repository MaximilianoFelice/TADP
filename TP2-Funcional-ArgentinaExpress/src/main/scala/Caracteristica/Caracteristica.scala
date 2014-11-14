package ArgentinaExpress.Caracteristica

import ArgentinaExpress.Envio.Envios._
import ArgentinaExpress.Transporte.Transportes._

/**
 * Created by maximilianofelice on 07/11/14.
 */

package object Caracteristicas{

  abstract class Caracteristica{
    val actualizarEnvio: Envio => Envio;

    val incompatibleCon: PartialFunction[Caracteristica, Boolean];

    lazy val esCompatibleCon: Caracteristica => Boolean = { //TODO NOPE NOPE NOPE NOPE
      incompatibleCon orElse {case _ => true}
    }
  };

  case object Normal extends Caracteristica
  {
    val actualizarEnvio: Envio => Envio = { case x:Envio => new Envio(x.costo + 80, x.destino, x.volumen, x.caracteristicas)}

    val incompatibleCon: PartialFunction[Caracteristica, Boolean] = {case Urgente => false}

  };

  case object Urgente extends Caracteristica
  {
    val actualizarEnvio: Envio => Envio = { case x:Envio => new Envio(x.costo + 110, x.destino, x.volumen, x.caracteristicas)}

    val incompatibleCon: PartialFunction[Caracteristica, Boolean] = {case Normal => false}
  };

  case object Refrigerado extends Caracteristica
  {
    val actualizarEnvio: Envio => Envio = { case x:Envio => new Envio(x.costo + 210, x.destino, x.volumen, x.caracteristicas)}

    val incompatibleCon: PartialFunction[Caracteristica, Boolean] = Map.empty
  };

  case object Fragil extends Caracteristica
  {
    val actualizarEnvio: Envio => Envio = { case x:Envio => new Envio(x.costo + 120, x.destino, x.volumen, x.caracteristicas)}

    val incompatibleCon: PartialFunction[Caracteristica, Boolean] = Map.empty
  };

  case object ConAnimales extends Caracteristica
  {
    val actualizarEnvio: Envio => Envio = {case e => e}

    val incompatibleCon: PartialFunction[Caracteristica, Boolean] = {case SustanciasPeligrosas => false}
  };

  case object SustanciasPeligrosas extends Caracteristica
  {
    val actualizarEnvio: Envio => Envio = {case e => e}

    val incompatibleCon: PartialFunction[Caracteristica, Boolean] = {case ConAnimales => false}
  };


  val sePuedeAgregar: (Caracteristica, Seq[Caracteristica]) => Boolean = {
    case (c, cs) => cs.forall(otroC => otroC.esCompatibleCon(c) && !otroC.eq(c))
  }

}