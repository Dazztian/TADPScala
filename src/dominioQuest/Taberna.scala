package dominioQuest
import scala.util.control.Breaks._

class Taberna(val numeritos:Int) {
  type criterio = (Equipo,Equipo) => Boolean
  
  val tablon = Seq[Mision]()
  
  def elegirMision(misiones:Seq[Mision],unEquipo:Equipo,criterio:criterio):Mision = {
     ordenarMisionesSegunCriterio(misiones,unEquipo,criterio).head
  }
  
  def ordenarMisionesSegunCriterio(misiones:Seq[Mision],unEquipo:Equipo,criterio:criterio):Seq[Mision] = {
    misiones.sortWith((mision1,mision2) => criterio(unEquipo.realizarMision(mision1).equipo,unEquipo.realizarMision(mision2).equipo))
  }
  
  def entrenar(unEquipo:Equipo,criterio:criterio) = {
    
   val misionesARealizar = tablon
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
    }
  }
}
