package ArgentinaExpress.Caracteristica

import ArgentinaExpress.Envio.Envios.Envio

/**
 * Created by maximilianofelice on 07/11/14.
 */

abstract class Caracteristica{
  val f: Envio => Envio;
};

case object Normal extends Caracteristica
{
  val f: Envio => Envio = { case x:Envio => new Envio(x.costo + 80, x.origen, x.destino, x.volumen, x.caracteristicas)}
};

case object Urgente extends Caracteristica
{
  val f: Envio => Envio = { case x:Envio => new Envio(x.costo + 110, x.origen, x.destino, x.volumen, x.caracteristicas)}
};

case object Refrigerado extends Caracteristica
{
  val f: Envio => Envio = { case x:Envio => new Envio(x.costo + 210, x.origen, x.destino, x.volumen, x.caracteristicas)}
};

case object Fragil extends Caracteristica
{
  val f: Envio => Envio = { case x:Envio => new Envio(x.costo + 120, x.origen, x.destino, x.volumen, x.caracteristicas)}
};

case object SoporteAnimales extends Caracteristica
{
  val f: Envio => Envio = { case x:Envio => new Envio(x.costo, x.origen, x.destino, x.volumen, x.caracteristicas)}
};

case object SustanciasPeligrosas extends Caracteristica
{
  val f: Envio => Envio = { case x:Envio => new Envio(x.costo, x.origen, x.destino, x.volumen, x.caracteristicas)}
};