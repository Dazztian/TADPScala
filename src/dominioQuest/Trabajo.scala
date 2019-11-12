package dominioQuest

//Define si sos guerrero, ladrón,etc...
class Trabajo (
    var atributoPrincipal:String,
    var atributosHeroe: Map[Stat, Int=>Int]){  
}

 case class Mago( var atributoPrincipal:String, var atributosHeroe: Map[Stat, Int=>Int])
extends Trabajo( atributoPrincipal:String, atributosHeroe: Map[Stat, Int=>Int]) 
 
case class Ladron( var atributoPrincipal:String, var atributosHeroe: Map[Stat, Int=>Int])
extends Trabajo( atributoPrincipal:String, atributosHeroe: Map[Stat, Int=>Int]) 

case class Hechicero( var atributoPrincipal:String, var atributosHeroe: Map[Stat, Int=>Int])
extends Trabajo( atributoPrincipal:String, atributosHeroe: Map[Stat, Int=>Int]) 



