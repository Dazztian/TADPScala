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
var laBotellitaDeRicky:Item = null
var palitoMagico:Item = null
var tacosDeSarkany:Item = null


  @Before
  def setup() = {
  
    druida=new Trabajo(Hp, Map(Fuerza -> (1+)))
    
     var funcionPrueba: Int=>Boolean=(30<)
     var requi:RequerimientosItem=new RequerimientosItem(Some(druida), Map(Fuerza -> funcionPrueba))
     var requerimientoPalitoMagico: List[RequerimientosItem]= List(requi) 
     
     laBotellita=new Item(Manos.derecha,Map(Hp ->(4* )), requerimientoPalitoMagico)
     
     //val partesDelCuerpo: List[ParteDelCuerpo]= List(Manos.derecha)
     var listaItems: List[Item]= List(laBotellita)

    
     kaerin = new Heroe(1,40,3,4, Some(druida), listaItems)//,partesDelCuerpo )
     
     
      druida=new Trabajo(Hp, Map(Fuerza -> (1+)))
       
      laBotellitaDeRicky = new Item(Manos.derecha,Map(Hp ->(40* )), requerimientoPalitoMagico)
      tacosDeSarkany = new Item(Pies, Map(Fuerza -> (300*)), List(new RequerimientosItem(None, Map(Inteligencia ->(3 ==)))))
      palitoMagico = new Item(Manos.izquiera,Map(Hp ->(40* )), requerimientoPalitoMagico)
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
  def cantItemPOSTEquipamientoDeIgualParte_test() = {// como ya tenia un item en la mao derecha, se le reemplaza
   
    var kaerinConNuevoItem=kaerin.equiparItem(laBotellitaDeRicky) 
    
    assertEquals(1, kaerinConNuevoItem.items.size)
  }
 @Test
  def cantItemPOSTEquipamientoDeDistintaManos_test() = {
   
    var kaerinConNuevoItem=kaerin.equiparItem(palitoMagico) 
    
    assertEquals(2, kaerinConNuevoItem.items.size)
  }
 @Test
  def cantItemPOSTEquipamientoDeDistintaParte_test() = {
   
    var kaerinConNuevoItem=kaerin.equiparItem(tacosDeSarkany) 
    
    assertEquals(2, kaerinConNuevoItem.items.size)
  }
  
 
  
}