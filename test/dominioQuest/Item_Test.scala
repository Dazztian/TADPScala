package dominioQuest

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class Item_Test {

var kaerin:Heroe = null
var druida:Trabajo = null
var laBotellita:Item = null
var manoDerecha= new ParteDelCuerpo(None)
var cabeza= new ParteDelCuerpo(None)


  @Before
  def setup() = {
  
    druida=new Trabajo(Hp, Map(Fuerza -> (1+)))
    
     var funcionPrueba: Int=>Boolean=(30<)
     var requi:RequerimientosItem=new RequerimientosItem(Some(druida), Map(Fuerza -> funcionPrueba))
     var requerimientoPalitoMagico: List[RequerimientosItem]= List(requi) 
     
     laBotellita=new Item(ManoDerecha,Map(Hp ->(4* )), requerimientoPalitoMagico)
     
     val partesDelCuerpo: List[ParteDelCuerpo]= List(manoDerecha)
     var listaItems: List[Item]= List(laBotellita)

    
     kaerin = new Heroe(1,40,3,4, druida, listaItems,partesDelCuerpo )
  }

  @Test
  def puedePortarItem_test() = {
    assertEquals(true, kaerin.puedePortarItem(laBotellita))
    println("por dios kaerin, largá la botellita!")
  }
  
  @Test
  def estadoPostPortarItem_test() = {
    var kaerinConNuevoItem=kaerin.equiparItem(laBotellita)
    assertEquals(4, kaerinConNuevoItem.hp)
  }
  @Test
  def cantItemPOSTEquipamiento_test() = {
    var kaerinConNuevoItem=kaerin.equiparItem(laBotellita)
    assertEquals(2, kaerinConNuevoItem.items.size)//Porque ya arranca con 1 item
  }
  
  @Test
  def estadoErroneoPostPortarItem_test() = {
    var kaerinConNuevoItem=kaerin.equiparItem(laBotellita)
    assertEquals(8, kaerinConNuevoItem.hp)
  }
  
}