package dominioQuest


sealed trait Stat {
  def modificar(heroe:Heroe,modificacion:Int=>Int) : Heroe
  def cumpleCon(heroe:Heroe,condicion:Int=>Boolean) : Boolean
  def devolver(heroe:Heroe) : Int
}

case object Fuerza extends Stat {
  def modificar(heroe:Heroe,modificacion:Int=>Int):Heroe = {
    heroe.copy(fuerza = modificacion(heroe.fuerza))
  }
  
  def cumpleCon(heroe:Heroe,condicion:Int=>Boolean):Boolean = {
    condicion(heroe.fuerza)
  }
  def devolver(heroe:Heroe) : Int = heroe.fuerza
}

case object Hp extends Stat {
  def modificar(heroe:Heroe,modificacion:Int=>Int):Heroe = {
    heroe.copy(hp = modificacion(heroe.hp))
  }
  
  def cumpleCon(heroe:Heroe,condicion:Int=>Boolean):Boolean = {
    condicion(heroe.hp)
  }
  
  def devolver(heroe:Heroe) : Int = heroe.hp
}

case object Inteligencia extends Stat {
  def modificar(heroe:Heroe,modificacion:Int=>Int):Heroe = {
    heroe.copy(inteligencia = modificacion(heroe.inteligencia))
  }
  
  def cumpleCon(heroe:Heroe,condicion:Int=>Boolean):Boolean = {
    condicion(heroe.inteligencia)
  }
  
  def devolver(heroe:Heroe) : Int = heroe.inteligencia
}
case object Velocidad extends Stat {
  def modificar(heroe:Heroe,modificacion:Int=>Int):Heroe = {
    heroe.copy(velocidad = modificacion(heroe.velocidad))
  }
  
  def cumpleCon(heroe:Heroe,condicion:Int=>Boolean):Boolean = {
    condicion(heroe.velocidad)
  }
  
  def devolver(heroe:Heroe) : Int = heroe.velocidad
}