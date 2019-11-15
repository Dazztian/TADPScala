package dominioQuest

//Define si sos guerrero, ladrón,etc...
class Trabajo (
    val atributoPrincipal:Stat,
    val atributosHeroe: Map[Stat, Int=>Int]){  
}


case class Mago( override val  atributoPrincipal: Stat, override val atributosHeroe: Map[Stat, Int=>Int]
     ) extends Trabajo( atributoPrincipal, atributosHeroe)

case class Ladron( override val  atributoPrincipal: Stat, override val atributosHeroe: Map[Stat, Int=>Int]
     ) extends Trabajo( atributoPrincipal, atributosHeroe)
