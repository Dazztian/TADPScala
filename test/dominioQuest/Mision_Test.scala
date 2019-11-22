package dominioQuest

import org.junit.Before

import org.junit.Test
import org.junit.Assert._

class Mision_Test {

var heroe:Heroe = null
var mago:Trabajo=null
var equipo:Equipo = null
var arcoViejo:Item = null
var sinRequerimiento : List[RequerimientosItem] = List()
var liderLadron:Heroe = null
var ladron: Trabajo = null
var estoNoEsUnEquipo:Equipo = null
var mision:Mision = null

  @Before
  def setup() = {
    mago=new Mago(Inteligencia, Map(Fuerza -> (100+)) )
    heroe = new Heroe(2,100,1,1, Some(mago),List())
    ladron =new Ladron(Velocidad, Map(Velocidad -> (10+),Hp ->(5-)) )
    liderLadron = new Heroe(100,200,300,400,Some(ladron),List())
    equipo = new Equipo(0,"Equipo sin gracia",List(heroe,liderLadron))
    arcoViejo = new Item(Some(Manos),Map(Fuerza -> (2+)), sinRequerimiento)    
    estoNoEsUnEquipo = new Equipo(0,"Equipo sin nadie",List())
    mision = new Mision(List(),List(RobarTalisman(arcoViejo),PelearContraMonstruo(10)))
  }

  @Test
  def equipoNoCumpleCondicionTarea()= {
       assertEquals(equipo,equipo.realizarMision(mision))
   }

}
