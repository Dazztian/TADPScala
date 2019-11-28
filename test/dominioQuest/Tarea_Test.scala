package dominioQuest

import org.junit.Before

import org.junit.Test
import org.junit.Assert._

class Tarea_Test {
// Trabajos
var mago:Trabajo=null
var guerrero: Trabajo = null
var ladron: Trabajo = null

// Heroes
var heroeMago:Heroe = null
var liderLadron:Heroe = null
var heroeGuerrero:Heroe = null

// Equipos
var equipo:Equipo = null
var estoNoEsUnEquipo:Equipo = null

var arcoViejo:Item = null
var sinRequerimiento : List[RequerimientosItem] = List()

  @Before
  def setup() = {

    // Trabajos
    mago=new Mago(Inteligencia, List(_.modificarFuerza(100+)) )
    guerrero = new Guerrero(Inteligencia, List(_.modificarFuerza(100+)))
    ladron = new Ladron(Velocidad, List(_.modificarVelocidad (10+), _.modificarHp (5-)) )
    
    // Heroes
    heroeMago = new Heroe(0, 2,100,1,1, 2,100,1,1, Some(mago),List())
    heroeGuerrero = new Heroe(1, 200,100,100,100, 200,100,100,100, Some(guerrero),List())
    liderLadron = new Heroe(1, 100,200,300,400, 100,200,300,400,Some(ladron),List())
    
    // Equipos
    equipo = new Equipo(0,"Equipo sin gracia",List(heroeMago, heroeGuerrero))
    estoNoEsUnEquipo = new Equipo(0,"Equipo sin nadie",List())
    
    arcoViejo = new Item(Some(Manos),List(_.modificarFuerza( (2+))), sinRequerimiento,10)
    
  }

  @Test
  def buscarMejorHeroeNormal() = {
     assertEquals(Some(heroeGuerrero),RobarTalisman(arcoViejo).encontrarMejorHeroe(equipo))
  }
  
  @Test
  def buscarMejorHeroeEquipoVacio() = {
    assertEquals(None,RobarTalisman(arcoViejo).encontrarMejorHeroe(estoNoEsUnEquipo))
  }

}
