package dominioQuest

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class Item_Test {

var kaerin:Heroe = null
var druida:Trabajo = null
var laBotellita:Item = null
var partesDelCuerpo: List[ParteDelCuerpo]=null
var manoDerecha= new ParteDelCuerpo
var cabeza= new ParteDelCuerpo


  @Before
  def setup() = {
     druida=new Trabajo("Perro de ambar", Map(Fuerza -> (1+)))
     laBotellita=new Item("botella fogosa", ManoDerecha,Map(Fuerza -> (30<), Fuerza -> (40==), Hp -> (1==) ), Map(Hp ->(4* )))
     
     val l1= List(manoDerecha)
     
     //partesDelCuerpo:+ l1
     /*partesDelCuerpo:+ manoDerecha
     partesDelCuerpo:+ cabeza*/
     
     //println(partesDelCuerpo(0).itemAsociado.descripcion)
        
     
     
     kaerin = new Heroe(1,2,3,4, druida, laBotellita,l1 )
  }

  @Test
  def puedePortarItem_test() = {
    kaerin.fuerza=40
    assertEquals(true, kaerin.puedePortarItem(laBotellita))
    kaerin.equiparItem(laBotellita)
    println("por dios kaerin, largá la botellita!")
  }
}