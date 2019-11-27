
package dominioQuest
import scala.util.control.Breaks._

class Taberna(val tablon : Seq[Mision]) {

  type criterio = (Equipo,Equipo) => Boolean

  def elegirMision(misiones:Seq[Mision],unEquipo:Equipo,criterio:criterio):Option[Mision] = {
    ordenarMisionesSegunCriterio (misiones, unEquipo, criterio).headOption }

  def ordenarMisionesSegunCriterio(misiones:Seq[Mision],unEquipo:Equipo,criterio:criterio):Seq[Mision] = {
        misiones.sortWith((mision1,mision2) => criterio(mision1.realizarMision(unEquipo).equipo,mision2.realizarMision(unEquipo).equipo))
  }
  
  
   def entrenar(misiones:Seq[Mision], unEquipo:Equipo, criterio:criterio):Equipo = { //falta el caso de seguir con una mision falla
    misiones match{
      case Nil => unEquipo
      case misiones@(primera :: misionesRestantes) => {
          val misionesOrdenadas = ordenarMisionesSegunCriterio(misiones,unEquipo,criterio)
          var resultado = primera.realizarMision(unEquipo)
          resultado match {
            case Success(equipo) =>  entrenar(misionesRestantes,equipo,criterio)
            case NoPuedeRealizarse(equipo,_)=> equipo
            case Failure(equipo,_) => equipo
          }
        }
      }
    }
}

