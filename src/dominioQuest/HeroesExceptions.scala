package dominioQuest

  
case class NoPuedeHpMenorAUno(val Heroe: Heroe) extends Exception
case class NoPuedeFuerzaMenorAUno(val Heroe: Heroe) extends Exception
case class NoPuedeInteligenciaMenorAUno(val Heroe: Heroe) extends Exception
case class NoPuedeVelocidaMenorAUno(val Heroe: Heroe) extends Exception