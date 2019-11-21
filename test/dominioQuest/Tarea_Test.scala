package dominioQuest

import org.junit.Before

import org.junit.Test
import org.junit.Assert._

class Tarea_Test {

var heroe:Heroe = null
var mago:Trabajo=null
var equipo:Equipo = null
var arcoViejo:Item = null
var sinRequerimiento : List[RequerimientosItem] = List()


  @Before
  def setup() = {
    mago=new Mago(Inteligencia, Map(Fuerza -> (100+)) )
    heroe = new Heroe(2,100,1,1, Some(mago),List())
    equipo = new Equipo(0,"Equipo sin gracia",List(heroe))
    arcoViejo = new Item(Some(Manos),Map(Fuerza -> (2+)), sinRequerimiento)    

  }

  @Test
  def someTest()= {
    println(equipo.puedeRealizarTarea(RobarTalisman(arcoViejo)))
       assertEquals(Failure(equipo,new NingunHeroeParaTareaException),equipo.puedeRealizarTarea(RobarTalisman(arcoViejo)))
   }
}
