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

  var manoDerecha = new ParteDelCuerpo(None)
  var cabeza = new ParteDelCuerpo(None)

  @Before
  def setup() = {
    hechicero = new Trabajo(Fuerza, Map(Fuerza -> (158+)))
    berserk = new Trabajo(Inteligencia, Map(Hp -> (299+)))
    trabajoRestador = new Trabajo(Velocidad, Map(Velocidad -> (_ - 300)))
    trabajoInutil = new Trabajo(Hp, Map())
    cascoVikingo = new Item(Some(Cabeza), Map(Hp -> (10+)), List(new RequerimientosItem(None, Map(Fuerza -> (30<)))), 10)
    laMataDragones = new Item(Some(ManoDerecha), Map(Fuerza -> (1000*)), List(new RequerimientosItem(None, Map(Fuerza -> (10>)))), 10)

    heroe = new Heroe(1, 2, 3, 4, None, List(cascoVikingo))
  }

  @Test
  def aplicarUnTrabajo_test() = {
    assertEquals(160, heroe.aplicarEfectosDelTrabajo(Some(hechicero)).fuerza)
  }
  @Test
  def cambioDeTrabajo_test() = {
    assertEquals(300, heroe.aplicarEfectosDelTrabajo(Some(berserk)).hp)
  }
  @Test
  def trabajoQueDejaEnCeroUnStat(): Unit = {
    assertEquals(1, heroe.aplicarEfectosDelTrabajo(Some(trabajoRestador)).velocidad)
  }
  @Test
  def cambioDeTrabajoSinAtributosHeroe_test() = {
    assertEquals(Some(trabajoInutil), heroe.aplicarEfectosDelTrabajo(Some(trabajoInutil)).especializacion)
  }
  @Test
  def siUnHeroeSinTrabajoLeDoyUnTrabajoYLuegoSeLoQuitoQUedaIgualQueAlPrincipio() = {
    assertEquals(heroe, heroe.aplicarEfectosDelTrabajo(Some(hechicero)).aplicarEfectosDelTrabajo(None))
  }
  @Test
  def heroeDesempleado_test() = {
    assertEquals(
      None,
      heroe.aplicarEfectosDelTrabajo(None).especializacion)
  }
}