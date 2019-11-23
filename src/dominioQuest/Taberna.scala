
package dominioQuest
import scala.util.control.Breaks._

class Taberna(val tablon : Seq[Mision]) {

  type criterio = (Equipo,Equipo) => Boolean

  
  def elegirMision(misiones:Seq[Mision],unEquipo:Equipo,criterio:criterio):Option[Mision] = {
    misiones match {
      case Nil=> None
      case mision::nil=>Some(mision)
      case mision::_ => Some(ordenarMisionesSegunCriterio (misiones, unEquipo, criterio).head)
    }
  }

  def ordenarMisionesSegunCriterio(misiones:Seq[Mision],unEquipo:Equipo,criterio:criterio):Seq[Mision] = {
        misiones.sortWith((mision1,mision2) => criterio(unEquipo.realizarMision(mision1).equipo,unEquipo.realizarMision(mision2).equipo))
  }
  
  
   def entrenar(misiones:Seq[Mision], unEquipo:Equipo, criterio:criterio):Equipo = { //falta el caso de seguir con una mision falla
    misiones match{
      case Nil => unEquipo
      case _ => {
          var misionesOrdenadas = ordenarMisionesSegunCriterio(misiones,unEquipo,criterio)
          var resultado = unEquipo.realizarMision(misionesOrdenadas.head) //case loco
          resultado match {
            case Success(equipo) =>  entrenar(misionesOrdenadas.tail,equipo,criterio)
            case NoPuedeRealizarse(equipo,_)=> equipo
            case Failure(equipo,_) => equipo
          }
      
         
        }
      }
    }
}

