package dominioQuest

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class Heroe_Test {
  
var heroe:Heroe = null
var hechicero:Trabajo = null
var cascoVikingo:Item = null
var laMataDragones:Item = null

var manoDerecha= new ParteDelCuerpo(None)
var cabeza= new ParteDelCuerpo(None)


  @Before
  def setup() = {
  
     var funcionPrueba: Int=>Boolean=(30<)
     var requi:RequerimientosItem=new RequerimientosItem(hechicero, Map(Fuerza -> funcionPrueba))
     var requerimientoPalitoMagico: List[RequerimientosItem]= List(requi) 
     
     hechicero=new Trabajo("Perro de ambar", Map(Fuerza -> (158+)))
     
     cascoVikingo=new Item(Cabeza, Map(Fuerza -> (30<), Fuerza -> (40==), Hp -> (1==) ), Map(Hp ->(4* )),requerimientoPalitoMagico)
     laMataDragones= new Item(ManoDerecha, Map(Fuerza -> (30<)),Map(Fuerza ->(1000* )), requerimientoPalitoMagico)
     

     manoDerecha.itemAsociado=Some(laMataDragones)
     
     val partesDelCuerpo: List[ParteDelCuerpo]= List(manoDerecha)
     var listaItems: List[Item]= List(cascoVikingo)

     
     heroe = new Heroe(1,2,3,4, hechicero, listaItems, partesDelCuerpo )
  }
  @Test
  def heroeFuerza_test() = {
    assertEquals(2, heroe.fuerza)
  }

  @Test
  def heroeTrabajo_test() = {
    assertEquals("Perro de ambar", heroe.especializacion.atributoPrincipal)
  }
}