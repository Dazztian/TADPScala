package dominioQuest

case class Mision (
    var recompensa: Any,
    var tareas: List[Tarea]){
  
  def realizarMision(unEquipo:Equipo):Result = {//podemos devolver un equipo o lo que sea que devuelva una recompensa, o un exception de que no logro hace la mision
   tareas.foldLeft(Result(unEquipo)){
     (semilla,tarea)=> tarea.puedeRealizarlaAlgunHeroe(unEquipo) match{
      case previousResult: NoPuedeRealizarse  => previousResult
      case previousResult: Failure  => previousResult
      case Success(unEquipo) => tarea.cumplirTarea(tarea.encontrarMejorHeroe(unEquipo),unEquipo)
     }
   }
  }
 
  
}