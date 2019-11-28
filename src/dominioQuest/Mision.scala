package dominioQuest

case class Mision (
    var recompensa: Recompensa, //(_.agregarOroPozo(10))
    var tareas: List[Tarea]){
  
  def realizarMision(unEquipo:Equipo):Result = {
    tareas.foldLeft(Result(unEquipo)){ 
      (previousResult, tarea) => {
        previousResult match{
          case Success(_) => {
            val heroeElegido = tarea.encontrarMejorHeroe(previousResult.equipo)
            heroeElegido match {
              case Some(heroe) => tarea.cumplirTarea(heroeElegido, previousResult.equipo) // devuelve un Result
              case _ => NoPuedeRealizarse(unEquipo,tarea)
            }
          }
          case NoPuedeRealizarse(_,tarea) => NoPuedeRealizarse(unEquipo,tarea)
          case Failure(_,unaExcepcion) => Failure(unEquipo,unaExcepcion)
        }
      }
    }//Termina de foldear
    match{//Aca COBRA la recompensa
      case Success(equipo) => Success(this.recompensa.obtenerRecompensa(equipo))   
        case NoPuedeRealizarse(equipo,tarea) => NoPuedeRealizarse(equipo, tarea)
        case Failure(equipo, error) => Failure(equipo, error)
      } 
  }
  
}


