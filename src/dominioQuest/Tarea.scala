package dominioQuest

 sealed trait Result { def equipo:  Equipo }
  object Result {
    def apply(equipo: =>  Equipo): Result = try {
      Success(equipo)
    } catch {
      case error: NingunHeroeParaTareaException => Failure(equipo, error)
    }
  }

  case class NoPuedeRealizarse(equipo :Equipo) extends Result
  case class Success(equipo: Equipo) extends Result
  case class Failure(equipo: Equipo, error: Exception) extends Result
  class NingunHeroeParaTareaException extends RuntimeException // TODO hay q mostrar la tarea q no se peude realizar


abstract class Tarea{
  type Efecto = Heroe => Heroe
  type Condicion = Equipo => Boolean
  
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
  
  def puedeRealizarlaAlgunHeroe(equipo :Equipo) :Result = Success(equipo) // x default si no se tiene condiciones

}


case class PelearContraMonstruo(vidaAReducir: Int) extends Tarea{
 override def facilidad(unHeroe: Heroe, unEquipo: Equipo):Int = 
    unEquipo.lider().flatMap(_.especializacion) match{ //si tiene lider(devuelve Option[Heroe]) y si este tiene especializacion (Devuelve Option[Trabajo])
      case None => 0
      case Some(Guerrero(_,_)) => 20 
      case Some(_) => 10
    }
 
}
  

case class ForzarPuerta() extends Tarea
{
  override def facilidad(unHeroe: Heroe, unEquipo: Equipo):Int =
  {
    return unHeroe.inteligencia + 10 * 
    unEquipo.getCantLadrones()
  }
 
}
case class RobarTalisman(unItem: Item) extends Tarea
{
  //def facilidad(unHeroe: Heroe, unEquipo: Equipo):Int ={}
    
   override def puedeRealizarlaAlgunHeroe(equipo :Equipo) :Result = //esta vendria a ser la condicion
     equipo.lider() match{
     case Some(lider)=> if(lider.especializacion == Ladron){
           Success(equipo)
         }else{
           NoPuedeRealizarse(equipo)
         }
     case None => NoPuedeRealizarse(equipo)
   }
}


