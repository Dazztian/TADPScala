package dominioQuest


sealed trait Stat

case object Fuerza extends Stat
case object Hp extends Stat
case object Inteligencia extends Stat
case object Velocidad extends Stat