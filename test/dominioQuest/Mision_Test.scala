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
var zapatillaTrucha:Item = null

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
var obtenerRobando: Mision = null
var roboNoConveniente: Mision = null

// Tareas
var robarTalis:Tarea = null
var robarZapa:Tarea = null

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
    soloRobar = new Mision(new AgregarOroPozo(888),List(robarTalis))
    equipoSinLiderLadron = new Equipo(0,"No tenemos lider ladron", List(heroeMago, heroeGuerrero))
    
    misionConRobar = new Mision(new AgregarOroPozo(100),List(robarTalis,PelearContraMonstruo(10)))
    misionImposible = new Mision(new AgregarMiembro(heroeGuerrero),List(robarTalis,PelearContraMonstruo(10)))
    obtenerRobando = new Mision(new EquiparItem(arcoViejo), List(robarTalis))
    zapatillaTrucha = new Item(Some(Pies), Map(Fuerza -> (1-)), sinRequerimiento, 100)
    robarZapa = RobarTalisman(zapatillaTrucha)
    roboNoConveniente = new Mision(new EquiparItem(zapatillaTrucha), List(robarZapa))
  }


  @Test
  def equipoAgregaOro() = {
    assertEquals(888, soloRobar.recompensa.obtenerRecompensa(equipoSoloLadron).pozoComun)
  }
  //Queda probar 1)EquiparItem, 2)AgregarMiembro y 3)IncrementarStats
  @Test
  def equipoNoCumpleMisionSimple() = {
    assertEquals(NoPuedeRealizarse(equipoSinLiderLadron,robarTalis), equipoSinLiderLadron.realizarMision(soloRobar))
  }
  

  @Test
  def equipoNoCumpleMisionCompleja() = {
    assertEquals(NoPuedeRealizarse(equipoSinLiderLadron,robarTalis), equipoSinLiderLadron.realizarMision(misionConRobar))
  }
  @Test
  def equipoCumpleCondicionTarea()= {
    var heroeModificado = new Heroe(100,200,300,400, Some(ladron),List())
    var equipoSoloLadronModificado = equipoSoloLadron.reemplazarMiembro(heroeModificado, heroeLadron).agregarOroPozo(100)
    
       assertEquals(Success(equipoSoloLadronModificado),equipoSoloLadron.realizarMision(misionConRobar))
  }
  @Test
  def equipoGanaRecompensaOro()= {
  
       assertEquals(100,equipoSoloLadron.realizarMision(misionConRobar).equipo.pozoComun)
  }
   @Test
  def equipoGanaRecompensaMiembro()= {
      assertEquals(true,equipoSoloLadron.realizarMision(misionImposible).equipo.integrantes.contains(heroeGuerrero))
  }
   
   @Test
   def equipoGanaItem() = {
     assertEquals(true, equipoSoloLadron.realizarMision(obtenerRobando).equipo.integrantes(0).items.contains(arcoViejo))
   }
   
   @Test
   def equipoRobaItemPeroLoVende() = {
     assertEquals(100, equipoSoloLadron.realizarMision(roboNoConveniente).equipo.pozoComun)
   }

}
