package dominioQuest

 sealed trait ResultTarea { def heroe:  Option[Heroe] }
  object ResultTarea {
    def apply(heroe: =>  Option[Heroe]): ResultTarea = try {
      Success(heroe)
    } catch {
      case error: NingunHeroeParaTareaException => Failure(None, error)
    }
  }

  case class Success(heroe: Option[Heroe]) extends ResultTarea
  case class Failure(heroe: Option[Heroe], error: Exception) extends ResultTarea
  class NingunHeroeParaTareaException extends RuntimeException // TODO hay q mostrar la tarea q no se peude realizar


abstract class Tarea{
  type Efecto = Heroe => Heroe
  type Condicion = Heroe => Boolean
  
  var efectos = List[Efecto]() 
  var condiciones = List[Condicion]()
  
  def facilidad(unHeroe: Heroe, unEquipo:Equipo):Int = {
    return 0
  }
  
  def cumplirTarea(unHeroe:Heroe): Heroe = {
    val heroeNuevo = efectos.foldLeft(unHeroe){
      (heroe,efecto) => efecto(heroe)
    }
    return heroeNuevo
  }
  
  def realizarTareaPor(unHeroe:Option[Heroe]) :ResultTarea = null

}


case class PelearContraMonstruo(vidaAReducir: Int) extends Tarea{
 override def facilidad(unHeroe: Heroe, unEquipo: Equipo):Int = 
    unEquipo.lider().flatMap(_.especializacion) match{ //si tiene lider y si este tiene especializacion
      //case None => 0
      case Some(Guerrero(_,_)) => 20 
      //case _ => 10
    }
 
 override def realizarTareaPor(unHeroe: Option[Heroe]) :ResultTarea =
   unHeroe match{ 
    case Some(unHeroe) => {
        if(unHeroe.fuerza<20){
        Success(Some(unHeroe.copy(hp = unHeroe.hp - vidaAReducir)))
       }else{
         throw new NingunHeroeParaTareaException
       }
    }
   case None =>  throw new NingunHeroeParaTareaException
 } 
}
  

/*case class ForzarPuerta() extends Tarea
{
  def facilidad(unHeroe: Heroe, unEquipo: Equipo):Int =
  {
    return unHeroe.inteligencia + 10 * 
    unEquipo.getCantLadrones()
  }
}
case class RobarTalisman(unItem: Item) extends Tarea
{
  def facilidad(unHeroe: Heroe, unEquipo: Equipo):Int =
  {
    (unEquipo.lider().get.especializacion) match
    {
      case Some(Ladron(_,_)) => unHeroe.velocidad 
      //case _ ACA DEBERIA TIRAR ERROR!!! 
    }
      
    
  }
}*/


