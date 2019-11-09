package dominioQuest

//Define si sos guerrero, ladrón,etc...
case class Trabajo (var atributoPrincipal:String, var atributosHeroe: Map[Stat, Int=>Int]) 
{  
}