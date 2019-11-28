package dominioQuest

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class Heroe_Test {
  
var heroe:Heroe = null
var mago:Trabajo = null
var palitoMagico:Item = null



@Before
  def setup() = {
  
  
     mago=new Mago(Inteligencia,  List(_.modificarFuerza(100+)))
  
     var funcionPrueba: Int=>Boolean=(100==)
     var requi:RequerimientosItem=new RequerimientosItem(Some(mago), Map(Hp -> funcionPrueba))
     var requerimientoPalitoMagico: List[RequerimientosItem]= List(requi) 
     
     palitoMagico= new Item(Some(ManoDerecha),  List(_.modificarFuerza(100*)),requerimientoPalitoMagico,10)
     
     
     var listaItems: List[Item]= List(palitoMagico)
        
     
     heroe = new Heroe(0, 100,200,300,400, 100,200,300,400, Some(mago), listaItems)
  }

@Test
  def statModificado_test() = {
  assertEquals(2010000, heroe.equiparItem(palitoMagico).getStatActual(Fuerza))
}
@Test
  def aceptaItem_test() = {
    assertEquals(true, palitoMagico.puedeSerPortadoPor(heroe))
  }

 @Test
  def conocerElValorDeTuMainStat() = {
  assertEquals(heroe.inteligencia,heroe.mainStatSegunEspecializacion)
  }
}