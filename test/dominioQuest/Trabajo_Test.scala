package dominioQuest

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class Trabajo_Test {

  var heroe: Heroe = null

  var hechicero: Trabajo = null
  var berserk: Trabajo = null
  var trabajoInutil: Trabajo = null
  var trabajoRestador: Trabajo = null

  var cascoVikingo: Item = null
  var laMataDragones: Item = null


  @Before
  def setup() = {
    //List(_.modificarFuerza(100+))
    hechicero = new Trabajo(Fuerza, List(_.modificarFuerza(158+))) // Map(Fuerza -> (158+))
    berserk = new Trabajo(Inteligencia, List(_.modificarHp(299+))) // Map(Hp -> (299+))
    trabajoRestador = new Trabajo(Velocidad,List(_.modificarVelocidad(300-))) //  Map(Velocidad -> (_ - 300)
    trabajoInutil = new Trabajo(Hp, List())
    cascoVikingo = new Item(Some(Cabeza), List(_.modificarHp(10+)), List(new RequerimientosItem(None, Map(Fuerza -> (30<)))), 10) // Map(Hp -> (10+) / 
    laMataDragones = new Item(Some(ManoDerecha), List(_.modificarFuerza(1000*)), List(new RequerimientosItem(None, Map(Fuerza -> (10>)))), 10) // Map(Fuerza -> (1000*)

    heroe = new Heroe(0, 1, 2, 3, 4, 1, 2, 3, 4, None, List(cascoVikingo))
  }

//  @Test
//  def aplicarUnTrabajo_test() = {
//    assertEquals(160, heroe.aplicarEfectosDelTrabajo(Some(hechicero)).fuerza)
//  }
//  @Test
//  def cambioDeTrabajo_test() = {
//    assertEquals(300, heroe.aplicarEfectosDelTrabajo(Some(berserk)).hp)
//  }
//  @Test
//  def trabajoQueDejaEnCeroUnStat(): Unit = {
//    assertEquals(1, heroe.aplicarEfectosDelTrabajo(Some(trabajoRestador)).velocidad)
//  }
//  @Test
//  def cambioDeTrabajoSinAtributosHeroe_test() = {
//    assertEquals(heroe, heroe.aplicarEfectosDelTrabajo(Some(trabajoInutil)))
//  }
  @Test
  def siUnHeroeSinTrabajoLeDoyUnTrabajoYLuegoSeLoQuitoQUedaIgualQueAlPrincipio() = {
    var heroeHechi = heroe.aplicarEfectosDelTrabajo(Some(hechicero))
    var supuestMismoHeroe = heroeHechi.aplicarEfectosDelTrabajo(None)
    assertEquals(heroe, supuestMismoHeroe)
  }
//  @Test
//  def heroeDesempleado_test() = {
//    assertEquals(
//      None,
//      heroe.aplicarEfectosDelTrabajo(None).especializacion)
//  }
//  @Test
//  def heroeAplicoNone() = {
//    assertEquals(heroe, heroe.aplicarEfectosDelTrabajo(None))
//  }
}