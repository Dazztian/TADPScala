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
//  var condiciones = List[Condicion]()
  
  def facilidad(unHeroe: Heroe, unEquipo:Equipo):Int = {
    return 0
  }
  
  def cumplirTarea(unHeroe:Heroe, equipo:Equipo): Result = {
    val heroeNuevo = efectos.foldLeft(unHeroe){
      (heroe,efecto) => efecto(heroe)
    }
    return Result(equipo)
  }
  
  def puedeRealizarlaAlgunHeroe(equipo :Equipo) :Result ={
  equipo.integrantes match{
    case Nil => NoPuedeRealizarse(equipo)
    case _ =>Success(equipo) // x default si no se tiene condiciones
    }
  }
  
  def encontrarMejorHeroe(equipo :Equipo):Heroe ={
    equipo.integrantes.sortWith((primerHeroe,segundoHeroe)=>this.facilidad(primerHeroe, equipo)> this.facilidad(segundoHeroe, equipo))(0)
  }

}


case class PelearContraMonstruo(vidaAReducir: Int) extends Tarea{
 override def facilidad(unHeroe: Heroe, unEquipo: Equipo):Int = 
    unEquipo.lider().flatMap(_.especializacion) match{ //si tiene lider(devuelve Option[Heroe]) y si este tiene especializacion (Devuelve Option[Trabajo])
      case None => 0
      case Some(Guerrero(_,_)) => 20 
      case Some(_) => 10
    }
 override def cumplirTarea(unHeroe:Heroe, unEquipo:Equipo):Result ={
   unHeroe.fuerza<20 match { //podria hacerse con un if? si, podria
     case true => Success(unEquipo.reemplazarMiembro(unHeroe.copy(hp = unHeroe.hp-1), unHeroe))
     case _ => Success(unEquipo)
   }
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
  override def facilidad(unHeroe: Heroe, unEquipo: Equipo):Int ={
    return unHeroe.velocidad
  }
    
   override def puedeRealizarlaAlgunHeroe(equipo :Equipo) :Result = //esta vendria a ser la condicion
     equipo.lider() match{
     case Some(Heroe(_,_,_,_,Some(Ladron(_,_)),_))=> Success(equipo)
     case Some(_) => NoPuedeRealizarse(equipo)
     case None => NoPuedeRealizarse(equipo)
   }
}


