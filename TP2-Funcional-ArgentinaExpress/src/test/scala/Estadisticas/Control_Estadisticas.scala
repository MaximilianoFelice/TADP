package ArgentinaExpress.tests

import ArgentinaExpress.Caracteristica.Caracteristicas._
import ArgentinaExpress.Envio.Envios._
import ArgentinaExpress.Estadisticas.EstadisticasPack._
import ArgentinaExpress.Sucursal.Sucursal
import ArgentinaExpress.Transporte.Transportes._
import ArgentinaExpress.Transporte._
import org.scalatest.{BeforeAndAfter, FunSuite}

/**
 * Created by maximilianofelice on 19/11/14.
 */
class Control_Estadisticas extends FunSuite with BeforeAndAfter {

  var sucursal1: Sucursal  = _
  var sucursal2: Sucursal  = _
  var sucursal3: Sucursal  = _
  var sucursal4: Sucursal = _
  var sucursales: List[Sucursal] = _
  var env1: Envio = _
  var env2: Envio = _
  var env3: Envio = _
  var env4: Envio = _
  var envios: List[Envio] = _
  var camion: Camion = _
  var f100: Furgoneta = _
  var avion: Avion = _
  var camionBis: Camion = _
  var transportes: Transportes[Transporte] = _
  var camiones: Transportes[Camion] = _

  before {
    sucursal1 = new Sucursal(10)
    sucursal2 = new Sucursal(8)
    sucursal3 = new Sucursal(7)
    sucursal4 = new Sucursal(20)
    sucursales = List(sucursal1, sucursal2, sucursal3, sucursal4)
    env1 = Envio(10, sucursal1, 5, Seq(Refrigerado))
    env2 = Envio(15, sucursal2, 2, Seq(Urgente, Refrigerado))
    env3 = Envio(8, sucursal1, 3, Seq(ConAnimales))
    env4 = Envio(14, sucursal3, 4, Seq(Normal, SustanciasPeligrosas))
    envios = List(env1, env2, env3)
    camion = Camion(Seq(Normal, SustanciasPeligrosas))
    f100 = Furgoneta(Seq(Refrigerado, ConAnimales))
    avion = Avion(Seq(Refrigerado))
    camionBis = Camion(Seq(Urgente, Refrigerado))
    transportes = List(camion, camionBis, f100)
    //transportes = Seq(camion, camionBis)

    camion.agregarEnvio(env4)
    f100.agregarEnvio(env3)
    avion.agregarEnvio(env1)
    camionBis.agregarEnvio(env2)

    //camiones = Seq(camion, camionBis)

    Estadisticas.envios = envios
    Estadisticas.transportes = List(camion, camionBis, f100)
  }

  test("Tiene sentido") {
    //assert(Estadisticas.transportes == transportes)
  }

  test("Filtro sucursales por camiones"){
    assert(transportes.filtroPorTipo(Camion()) == Seq(camion, camionBis))
      //filtroPorCaracteristica(Urgente)
  }

  test("Obtener listas del modulo"){

    val facturacionTotal: Sucursal => Double = {_ => 1000}

    Estadisticas.sucursales Where ((1000.0 greaterThan facturacionTotal) or (100.0 ))

    Estadisticas.sucursales Where (1000 < facturacionTotal(_))


    def algo(x: Int)(y: Int): Double = x + y

    Estadisticas.sucursales Where {x => true}

    def test = 100.0 greaterThan algo(3)
  }

}
