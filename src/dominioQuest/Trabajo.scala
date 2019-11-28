package dominioQuest

//Define si sos guerrero, ladrón,etc...
class Trabajo (
    val atributoPrincipal:Stat,
    val atributosHeroe: List[Heroe => Heroe]){  
}


case class Mago( override val  atributoPrincipal: Stat, override val atributosHeroe: List[Heroe => Heroe]
     ) extends Trabajo( atributoPrincipal, atributosHeroe)

case class Ladron( override val  atributoPrincipal: Stat, override val atributosHeroe:  List[Heroe => Heroe]
     ) extends Trabajo( atributoPrincipal, atributosHeroe)

case class Guerrero(override val  atributoPrincipal: Stat, override val atributosHeroe:  List[Heroe => Heroe]) extends Trabajo( atributoPrincipal, atributosHeroe)