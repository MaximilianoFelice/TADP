package ArgentinaExpress.test.Envio

import ArgentinaExpress.Caracteristica.{Caracteristica, Refrigerado}
import ArgentinaExpress.Envio.Envios._
import ArgentinaExpress.Sucursal.Sucursal

/**
 * Created by maximilianofelice on 05/11/14.
 */
import org.scalatest.FunSuite

class SetSuite extends FunSuite {

  test("it's true"){

    val envio = new Envio(10, new Sucursal(3), new Sucursal(5), 10, Nil)

    var x = agregarCaracteristica(Refrigerado, envio)
    assert(x.costo == 20)

  }

  test("Tambien es true"){
    assert(Nil.contains(Refrigerado) == false)
  }

  test("un tercer test"){

    val envio = new Envio(10, new Sucursal(3), new Sucursal(5), 10, Nil)

    var alguito: Option[Envio] = insertarCaracteristica(envio, Refrigerado)

    val id: (Envio, Caracteristica) => Option[Caracteristica] = {
      case (env, Refrigerado) if env.costo > 3 => Some(Refrigerado)
    }
    assert(id(envio, Refrigerado) == Some(Refrigerado))
  }

}