
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
  
  def entrenar(unEquipo:Equipo,criterio:criterio) = {
    
   /*val misionesARealizar = tablon
   val equipoEntrenando = unEquipo
   for (i <- 0 to tablon.length) {
     
      val mejorMisionActual = elegirMision(misionesARealizar,equipoEntrenando,criterio)
      val resultado = equipoEntrenando.realizarMision(mejorMisionActual)
     breakable {
        if (resultado != Success){
          break
        }else{
          val equipoEntrenado:Equipo = resultado.equipo
        }
		            
        }
      val misionesARealizar:Seq[Mision] = misionesARealizar.filterNot(_ == mejorMisionActual)
    }*/
  }
  
  def Entrenar(misiones:Seq[Mision], unEquipo:Equipo, criterio:criterio):Equipo = { //falta el caso de seguir con una mision falla
    var misionesOrdenadas = ordenarMisionesSegunCriterio(misiones,unEquipo,criterio)
    var resultado = unEquipo.realizarMision(misionesOrdenadas.head) //case loco
    var equipoEntrenado = resultado match {
      case Success(_) => resultado.equipo
      case NoPuedeRealizarse(_)=> throw new Exception
      case _ => //interrumpir todo y devolver el equipo en su ultima etapa
    }

    Entrenar(misionesOrdenadas.tail,equipoEntrenado.asInstanceOf[Equipo],criterio)
  }
}

