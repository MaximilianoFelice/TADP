package Transporte

import org.scalatest.FunSuite
import Transportes._

/**
 * Created by maximilianofelice on 05/11/14.
 */

package object Transportes {

  abstract class Transporte(val hola: Int) {}

  abstract class Terrestre(hola: Int) extends Transporte(hola) {}

  case class Avion(override val hola: Int = 2) extends Transporte(hola) {}

  case class Camion(override val hola: Int = 3) extends Terrestre(hola) {}

  case class Furgoneta(override val hola: Int = 5) extends Terrestre(hola) {}

  def algo[T <: Transporte]: T => Boolean = {
    case Camion(_) => true
    case _ => false
  }

}

class Costos extends FunSuite {
  test("Algo así") {
    assert(algo(Camion()))

  }

}
