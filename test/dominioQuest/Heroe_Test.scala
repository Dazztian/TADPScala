package dominioQuest

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class Heroe_Test {
  
var heroe:Heroe = null
var hechicero:Trabajo = null
var cascoVikingo:Item = null
var laMataDragones:Item = null
//var partesDelCuerpo: List[ParteDelCuerpo]=null
var manoDerecha= new ParteDelCuerpo(None)
var cabeza= new ParteDelCuerpo(None)


  @Before
  def setup() = {
     hechicero=new Trabajo("Perro de ambar", Map(Fuerza -> (158+)))
     cascoVikingo=new Item("Casco Vikingo",Cabeza, Map(Fuerza -> (30<), Fuerza -> (40==), Hp -> (1==) ), Map(Hp ->(4* )))
     
     laMataDragones= new Item("La mata dragones", ManoDerecha, Map(Fuerza -> (30<)),Map(Fuerza ->(1000* )))
     

     manoDerecha.itemAsociado=Some(laMataDragones)
     
     val partesDelCuerpo: List[ParteDelCuerpo]= List(manoDerecha)

     
     heroe = new Heroe(1,2,3,4, hechicero, cascoVikingo, partesDelCuerpo )
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