package dominioQuest

abstract class Tarea{
  type Efecto = Heroe => Heroe
  type Condicion = Heroe => Boolean
  
  var efectos = List[Efecto]() //TODO: Pasar a que sea un optional
  var condiciones = List[Condicion]() //TODO: Pasar a que sea un optional
  
  def facilidad(unHeroe: Heroe, unEquipo:Equipo):Int = {
    return 0
  }
  
  def cumplirTarea(unHeroe:Heroe): Heroe = {
    val heroeNuevo = efectos.foldLeft(unHeroe){
      (heroe,efecto) => efecto(heroe)
    }
    return heroeNuevo
  }
  
  def realizarTarea(unHeroe:Heroe) = null

}

case class PelearContraMonstruo(vidaAReducir: Int) extends Tarea
case class ForzarPuerta() extends Tarea
case class RobarTalisman(unItem: Item) extends Tarea




/*abstract class Tarea
{
  def facilidad(unHeroe: Heroe, unEquipo: Equipo):Int
}

case class PelearContraMonstruo(vidaAReducir: Int) extends Tarea{
 def facilidad(unHeroe: Heroe, unEquipo: Equipo):Int = 
  {
    (unEquipo.lider().get.especializacion) match{
      case Some(Guerrero(_,_)) => 20 
      case _ => 10
    }
  }
  
}
case class ForzarPuerta() extends Tarea
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




