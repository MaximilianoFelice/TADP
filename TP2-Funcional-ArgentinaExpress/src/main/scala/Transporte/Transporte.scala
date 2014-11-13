package ArgentinaExpress.Transporte

import ArgentinaExpress.Envio.Envio

/**
 * Created by maximilianofelice on 05/11/14.
 */

package object {
  //TODO Ver que onda con el implicit de transporte, es posible?
	
  abstract class Transporte (val capacidad:Int,
                            val costoBase: Double,
                            val velocidadPromedio: Int){

    var envios: Set[Envio] = Set()
  
    val caracteristicas: Seq[Caracteristica] = Nil 
  
    def volumenOcupado: Int = {
      envios.map(_.volumen).sum
    }

  }

  val insertarCaracteristica: (Transporte, Caracteristica) => Option[Transporte] = {
    case (t, c) if t.caracteristicas.contains(c) => None
    case (t, c) => Some(???) //new Transporte() ? TODO
  }
  
  //TODO Ver cómo patear toda la lógica repetida
  val agregarCaracteristica: (Caracteristica, Transporte) => Transporte = {
   case (carac, transp) => {
      (for {
            transpActualizado <- insertarCaracteristica(transp, carac)
      } yield carac.actualizarTransporte(transpActualizado) ) match {
        case Some(transpAc) => transpAc
        case None => transp
      }
    } 
  }
  
}