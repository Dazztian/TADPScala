package dominioQuest

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class Tarea_Test {

var heroe:Heroe = null
var mago:Trabajo=null


  @Before
  def setup() = {
    mago=new Mago(Inteligencia, Map(Fuerza -> (100+)) )
    heroe = new Heroe(2,100,1,1, Some(mago),List())
  }

//@Test
//def someTest()= {
//     assertEquals(new NingunHeroeParaTareaException, heroe.realizarTarea(PelearContraMonstruo(1)))
// }
}
