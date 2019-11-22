package dominioQuest

import org.junit.Before

import org.junit.Test
import org.junit.Assert._

class Mision_Test {

// Heroes 
var heroeMago:Heroe = null
var heroeLadron:Heroe = null
var heroeGuerrero:Heroe = null

// Equipos
var equipoConLiderLadron:Equipo = null
var equipoSinLiderLadron:Equipo = null
var estoNoEsUnEquipo:Equipo = null
var equipoSoloLadron:Equipo = null

// Items
var arcoViejo:Item = null

// Trabajos
var mago:Trabajo=null
var ladron: Trabajo = null
var guerrero:Trabajo=null

// Requerimientos
var sinRequerimiento : List[RequerimientosItem] = List()

// Misiones
var misionConRobar:Mision = null
var soloRobar:Mision = null

// Tareas
var robarTalis:Tarea = null

  @Before
  def setup() = {
    mago=new Mago(Inteligencia, Map(Fuerza -> (100+)) )
    heroeMago = new Heroe(2,100,1,1, Some(mago),List())
    ladron =new Ladron(Velocidad, Map(Velocidad -> (10+),Hp ->(5-)) )
    heroeLadron = new Heroe(100,200,300,400,Some(ladron),List())
    equipoConLiderLadron = new Equipo(0,"Equipo sin gracia",List(heroeMago,heroeLadron))
    guerrero = new Guerrero(Velocidad, Map(Velocidad -> (10+),Hp ->(5-)) )
    heroeGuerrero = new Heroe(100,200,300,400,Some(guerrero),List())
    arcoViejo = new Item(Some(Manos),Map(Fuerza -> (2+)), sinRequerimiento)    
    estoNoEsUnEquipo = new Equipo(0,"Equipo sin nadie",List())
    robarTalis = RobarTalisman(arcoViejo)
    misionConRobar = new Mision(List(),List(robarTalis,PelearContraMonstruo(10)))
    soloRobar = new Mision(List(),List(robarTalis))
    equipoSoloLadron = new Equipo(0, "Solitario ladron", List(heroeLadron))
    
    equipoSinLiderLadron = new Equipo(0,"No tenemos lider ladron", List(heroeMago, heroeGuerrero))
  }

  @Test
  def equipoCumpleCondicionTarea()= {
    var heroeModificado = new Heroe(100,202,300,400, Some(ladron),List()).incorporarItem(arcoViejo)
    var equipoSoloLadronModificado = equipoSoloLadron.reemplazarMiembro(heroeModificado, heroeLadron)
       assertEquals(Success(equipoSoloLadronModificado),equipoSoloLadron.realizarMision(soloRobar))
  }
  
  @Test
  def equipoNoCumpleMisionSimple() = {
    assertEquals(NoPuedeRealizarse(equipoSinLiderLadron), equipoSinLiderLadron.realizarMision(soloRobar))
  }
  
  @Test
  def equipoNoCumpleMisionCompleja() = {
    assertEquals(NoPuedeRealizarse(equipoSinLiderLadron), equipoSinLiderLadron.realizarMision(misionConRobar))
  }

}
