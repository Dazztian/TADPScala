package dominioQuest

abstract class Tarea
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
}




