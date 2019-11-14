package dominioQuest

//Define si sos guerrero, ladrón,etc...
class Trabajo (
    var atributoPrincipal:Stat,
    var atributosHeroe: Map[Stat, Int=>Int]){  
}

case class IncrementosPorNivelBuilderException(msg: String) extends Exception(msg)

/*case class Mago(   atributoPrincipal: Stat, atributosHeroe: Map[Stat, Int=>Int]
     ) extends Trabajo( atributoPrincipal, atributosHeroe)*/
