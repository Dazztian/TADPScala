package dominioQuest


import org.junit.Before
import org.junit.Test
import org.junit.Assert._


class Trabajo_Test {

var heroe:Heroe = null

var hechicero:Trabajo = null
var berserk:Trabajo = null
var trabajoInutil: Trabajo =null
var trabajoRestador: Trabajo = null

var cascoVikingo:Item = null
var laMataDragones:Item = null

var manoDerecha= new ParteDelCuerpo(None)
var cabeza= new ParteDelCuerpo(None)

  @Before
  def setup() = {
     hechicero=new Trabajo(Fuerza, Map(Fuerza -> (158+)))
     berserk=new Trabajo(Inteligencia, Map(Hp -> (299+)))
     trabajoRestador = new Trabajo (Velocidad, Map(Velocidad -> (_-300) ))
     trabajoInutil = new Trabajo(Hp,Map())
     cascoVikingo=new Item(Some(Cabeza),Map(Hp -> (10+)),List(new RequerimientosItem(None, Map(Fuerza -> (30<)))))
     laMataDragones= new Item(Some(ManoDerecha),Map(Fuerza ->(1000*)),List(new RequerimientosItem(None, Map(Fuerza -> (10>)))))
        
     
     heroe = new Heroe(1,2,3,4, None, List(cascoVikingo))
  }

 @Test
  def aplicarUnTrabajo_test() = {
    assertEquals(160,  heroe.aplicarTrabajo(Some(hechicero)).fuerza)
  }
 @Test
  def cambioDeTrabajo_test() = {
    assertEquals(300, heroe.aplicarTrabajo(Some(berserk)).hp)
    }
  @Test
  def trabajoQueDejaEnCeroUnStat(): Unit ={
    assertEquals(1,heroe.aplicarTrabajo(Some(trabajoRestador)).velocidad)
  }
 @Test
  def cambioDeTrabajoSinAtributosHeroe_test() = {
    assertEquals(Some(trabajoInutil), heroe.aplicarTrabajo(Some(trabajoInutil)).especializacion)
    }
  @Test
  def heroeDesempleado_test ()={
    assertEquals(None,
    heroe.aplicarTrabajo(None).especializacion)
  }
}