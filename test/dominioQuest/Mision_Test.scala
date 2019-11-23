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
var misionImposible :Mision = null

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
    arcoViejo = new Item(Some(Manos),Map(Fuerza -> (2+)), sinRequerimiento,20)
    estoNoEsUnEquipo = new Equipo(0,"Equipo sin nadie",List())
    robarTalis = RobarTalisman(arcoViejo)
    equipoSoloLadron = new Equipo(0, "Solitario ladron", List(heroeLadron))
    soloRobar = new Mision(new AgregarOroPozo(equipoSoloLadron)(888),List(robarTalis))
    equipoSinLiderLadron = new Equipo(0,"No tenemos lider ladron", List(heroeMago, heroeGuerrero))
    /*
     misionConRobar = new Mision(_.agregarOroPozo(100),List(robarTalis,PelearContraMonstruo(10)))
    misionImposible = new Mision(_.agregarOroPozo(100),_.obtenerMiembro(heroeGuerrero)),List(robarTalis,PelearContraMonstruo(10)))*/
  }


  @Test
  def equipoAgregaOro() = {
    assertEquals(888, soloRobar.recompensa.obtenerRecompensa.pozoComun)
  }
  //Queda probar 1)EquiparItem, 2)AgregarMiembro y 3)IncrementarStats
  @Test
  def equipoNoCumpleMisionSimple() = {
    assertEquals(NoPuedeRealizarse(equipoSinLiderLadron), equipoSinLiderLadron.realizarMision(soloRobar))
  }
  

  @Test
  def equipoNoCumpleMisionCompleja() = {
    assertEquals(NoPuedeRealizarse(equipoSinLiderLadron), equipoSinLiderLadron.realizarMision(misionConRobar))
  }
  @Test
  def equipoCumpleCondicionTarea()= {
    var heroeModificado = new Heroe(100,202,300,400, Some(ladron),List()).incorporarItem(arcoViejo)
    var equipoSoloLadronModificado = equipoSoloLadron.reemplazarMiembro(heroeModificado, heroeLadron)
       assertEquals(Success(equipoSoloLadronModificado),equipoSoloLadron.realizarMision(soloRobar))
  }
  @Test
  def equipoGanaRecompensaOro()= {
  
       assertEquals(100,equipoSoloLadron.realizarMision(misionConRobar).equipo.pozoComun)
  }
   @Test
  def equipoGanaRecompensaMiembro()= {
      assertEquals(true,equipoSoloLadron.realizarMision(misionImposible).equipo.integrantes.contains(heroeGuerrero))
  }

}
