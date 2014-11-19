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
  //TODO FALTA AGREGAR CADA PUTO PRECIO A CADA CARACTERISTICA (Normal = 80, Urgente = 110, Refrigerado = 210, Fragil = 120)
  case object Normal extends Caracteristica
  {
    val actualizarEnvio: Envio => Envio = { case x:Envio => new Envio(x.costo + 10, x.destino, x.volumen, x.caracteristicas)}

    val incompatibleCon: PartialFunction[Caracteristica, Boolean] = {case Urgente => false}

  };

  case object Urgente extends Caracteristica
  {
    val actualizarEnvio: Envio => Envio = { case x:Envio => new Envio(x.costo + 20, x.destino, x.volumen, x.caracteristicas)}

    val incompatibleCon: PartialFunction[Caracteristica, Boolean] = {case Normal => false}
  };

  case object Refrigerado extends Caracteristica
  {
    val actualizarEnvio: Envio => Envio = { case x:Envio => new Envio(x.costo + 70, x.destino, x.volumen, x.caracteristicas)}

    val incompatibleCon: PartialFunction[Caracteristica, Boolean] = Map.empty
  };

  case object Fragil extends Caracteristica
  {
    val actualizarEnvio: Envio => Envio = { case x:Envio => new Envio(x.costo + 18, x.destino, x.volumen, x.caracteristicas)}

    val incompatibleCon: PartialFunction[Caracteristica, Boolean] = Map.empty
  };

  case object ConAnimales extends Caracteristica
  {
    val actualizarEnvio: Envio => Envio = {case e => e} //TODO Leé el to do de abajo

    val incompatibleCon: PartialFunction[Caracteristica, Boolean] = {case SustanciasPeligrosas => false}
  };

  case object SustanciasPeligrosas extends Caracteristica
  {
    val actualizarEnvio: Envio => Envio = {case e => e} //TODO Aumentar el costo acá si es para el envío

    val incompatibleCon: PartialFunction[Caracteristica, Boolean] = {case ConAnimales => false}
  };


  val sePuedeAgregar: (Caracteristica, Seq[Caracteristica]) => Boolean = {
    case (c, cs) => cs.forall(otroC => otroC.esCompatibleCon(c) && !otroC.eq(c))
  }

}