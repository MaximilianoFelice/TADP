package Extra

/**
 * Created by maximilianofelice on 21/11/14.
 */
abstract class Extra {
  def costoPorKilometro: Double
}

case object GPS extends Extra{
  val costoPorKilometro = 0.5 * 2
}

case object Video extends Extra{
  val costoPorKilometro = 3.74 * 2
}