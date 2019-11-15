package dominioQuest

abstract class Tarea

case class PelearContraMonstruo(vidaAReducir: Int) extends Tarea
case class ForzarPuerta() extends Tarea
case class RobarTalisman(unItem: Item) extends Tarea


