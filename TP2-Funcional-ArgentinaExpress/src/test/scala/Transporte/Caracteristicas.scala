package Transporte

/**
 * Created by maximilianofelice on 14/11/14.
 */

import ArgentinaExpress.Envio.Envios.Envio
import ArgentinaExpress.Sucursal.Sucursal
import ArgentinaExpress.Transporte.Transportes._
import ArgentinaExpress.Caracteristica.Caracteristicas._

import ArgentinaExpress.Transporte.Camion


import org.scalatest.FunSuite

class TestTransportes extends FunSuite {


  test("Se agrega una caracteristica a un transporte"){

    val camion = new Camion(Seq(Normal,Refrigerado))
    val camionConCaract = agregarCaracteristica(Fragil, camion)
    assert(camionConCaract.caracteristicas.contains(Fragil))
  }

  test("Si la caracteristica no es compatible, no se agrega"){

    val camion = new Camion(Seq(Normal,Refrigerado))
    val camionConCaract = agregarCaracteristica(Urgente, camion)
     assert(!camionConCaract.caracteristicas.contains(Urgente))
  }

  test("Se puede agregar un envio a un camion compatible"){

    val camion = new Camion(Seq(Normal,Refrigerado))
    val envio = new Envio(10, new Sucursal(5), 10, Seq(Refrigerado))
    assert(camion.esAgregable(envio))
  }

  test("No se puede agregar un envio a un camion incompatible"){

    val camion = new Camion(Seq(Normal,Refrigerado))
    val envio = new Envio(10, new Sucursal(5), 10, Seq(Urgente))
    assert(!camion.esAgregable(envio))
  }

  test("No se puede agregar un envio a un camion cuyo destino es diferente al actual"){

    val camion = new Camion(Seq(Urgente,Refrigerado))
    val envio = new Envio(10, new Sucursal(5), 10, Seq(Urgente))
    val envio2 = new Envio(10, new Sucursal(6), 10, Seq(Urgente))

    assert(camion.esAgregable(envio))
    camion.agregarEnvio(envio)
    assert(!camion.esAgregable(envio2))
  }

}
