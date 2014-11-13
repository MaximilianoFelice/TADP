
case class Coordenada(val x:Int, val y:Int)

implicit def CoordenadaToTuple2(c: Coordenada):Tuple2[Int, Int] = {new Tuple2(c.x, c.y)}
implicit def TupleToCoord(aT: Tuple2[Int,Int]): Coordenada = {new Coordenada(aT._1, aT._2)}

def incrementCoord(c1: Coordenada, i: Int): Coordenada ={
  //var toMatch:Tuple2[Int, Int] = c1
  implicitly[Tuple2[Int,Int]](c1) match{
    case (x, y) => new Coordenada(x+i, y+i)
  }
}

println(incrementCoord((2,2), 4));