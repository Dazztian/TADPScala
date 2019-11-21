package dominioQuest

case class Mision (
    var recompensa: Any,
    var tareas: List[Tarea]){
  
  def realizarMision(unEquipo:Equipo):Equipo = { //podemos devolver un equipo o lo que sea que devuelva una recompensa, o un exception de que no logro hace la mision
    if(realizoTodasLasTareas(unEquipo)){
      this.recompensa(unEquipo)
    }else{
      null
      //error, no pudo hacer la mision
    }
  }
  
  def realizoTodasLasTareas(unEquipo:Equipo): Boolean = {
    tareas.foreach{tarea=> realizarTarea(tarea,tarea.heroeMasApto(unEquipo),unEquipo)} //Preguntar como hacerlo con fold
    //porque debemos ir cambiando a la semilla (el heroe) y tambien ir cambiando la tarea
   return true
  }
  
}