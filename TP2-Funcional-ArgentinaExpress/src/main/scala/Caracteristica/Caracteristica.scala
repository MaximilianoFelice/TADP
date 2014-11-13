package ArgentinaExpress.Caracteristica

import ArgentinaExpress.Envio.Envios.Envio

/**
 * Created by maximilianofelice on 07/11/14.
 */

abstract class Caracteristica{
  val actualizarEnvio: Envio => Envio;
  val actualizarTransporte: Transporte => Transporte;
};

//TODO Ver si se banca los implicits y pasamos todo a tuplas

case object Normal extends Caracteristica
{
  val actualizarEnvio: Envio => Envio = { case x:Envio => new Envio(x.costo + 80, x.origen, x.destino, x.volumen, x.caracteristicas)}
  val actualizarTransporte: Transporte => Transporte = {case t => t}
};

case object Urgente extends Caracteristica
{
  val actualizarEnvio: Envio => Envio = { case x:Envio => new Envio(x.costo + 110, x.origen, x.destino, x.volumen, x.caracteristicas)}
  val actualizarTransporte: Transporte => Transporte = {case t => t}
};

case object Refrigerado extends Caracteristica
{
  val actualizarEnvio: Envio => Envio = { case x:Envio => new Envio(x.costo + 210, x.origen, x.destino, x.volumen, x.caracteristicas)}
  val actualizarTransporte: Transporte => Transporte = {case t => t}
};

case object Fragil extends Caracteristica
{
  val actualizarEnvio: Envio => Envio = { case x:Envio => new Envio(x.costo + 120, x.origen, x.destino, x.volumen, x.caracteristicas)}
  val actualizarTransporte: Transporte => Transporte = {case t => t}
};

case object SoporteAnimales extends Caracteristica
{
  val actualizarEnvio: Envio => Envio = {case x => x} //Hace falta? FIXME
  val actualizarTransporte: Transporte => Transporte = {case t => t}
};

case object SustanciasPeligrosas extends Caracteristica
{
  val actualizarEnvio: Envio => Envio = {case x => x} //Hace falta? FIXME
  val actualizarTransporte: Transporte => Transporte = {case t => t}
};