package Viaje

import ArgentinaExpress.Envio.Envios._
import ArgentinaExpress.Sucursal.Sucursal
import ArgentinaExpress.Transporte.Transportes._
import com.github.nscala_time.time.Imports._


/**
 * Created by maximilianofelice on 21/11/14.
 */
case class Viaje(val transporte: Transporte, val origen: Sucursal, val envios: Seq[Envio],
                 val tiempo: Int, val beneficio: Double, val gananciaBruta: Double,
                 val costo: Double, val fechaSalida: DateTime) {

  val destino = envios.head.destino

  val cantidadEnvios = envios.size

  val fechaLlegada = fechaSalida + tiempo.hours

  val sucursales = Set(origen, destino)
}
