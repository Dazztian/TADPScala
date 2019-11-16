package dominioQuest

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class Item_Test {

var kaerin:Heroe = null
var kaerinRecargado :Heroe = null
var druida:Trabajo = null
var laBotellita:Item = null
var manoDerecha= new ParteDelCuerpo(None)
var cabeza= new ParteDelCuerpo(None)
var laBotellitaDeRicky:Item = null
var palitoMagico:Item = null
var tacosDeSarkany:Item = null
var sinRequerimiento : List[RequerimientosItem] = List()
var talismanDedicacion:Item = null
var talismanMinimalismo: Item = null
var arcoViejo:Item = null
var kaerinDefensor:Heroe = null

  @Before
  def setup() = {
   
   
    druida=new Trabajo(Hp, Map(Fuerza -> (1+)))
    
    var funcionPrueba: Int=>Boolean=(30<)
    var requi:RequerimientosItem=new RequerimientosItem(Some(druida), Map(Fuerza -> funcionPrueba))
    var requerimientoPalitoMagico: List[RequerimientosItem]= List(requi) 
     
    laBotellita=new Item(Some(Manos.derecha),Map(Hp ->(4* )), requerimientoPalitoMagico)
     
     //val partesDelCuerpo: List[ParteDelCuerpo]= List(Manos.derecha)
    var listaItems: List[Item]= List(laBotellita)
    var listaItemsDefensor: List[Item]=List(arcoViejo)
    
    kaerin = new Heroe(1,40,3,4, Some(druida), listaItems)//,partesDelCuerpo )
     
   
    druida=new Trabajo(Hp, Map(Fuerza -> (1+)))
    
    var listaItemsRecargada: List[Item]= List(laBotellita,tacosDeSarkany, palitoMagico)

    kaerinRecargado =new Heroe(10,40,3,4, Some(druida), listaItemsRecargada)//,partesDelCuerpo ))
    kaerinDefensor= new Heroe(10,4,3,4,Some(druida),listaItemsDefensor)
    //ITEMS
    laBotellitaDeRicky = new Item(Some(Manos.derecha),Map(Hp ->(40* )), requerimientoPalitoMagico)
    tacosDeSarkany = new Item(Some(Pies), Map(Fuerza -> (300*)), List(new RequerimientosItem(None, Map(Inteligencia ->(3 ==)))))
    palitoMagico = new Item(Some(Manos.izquiera),Map(Hp ->(40* )), requerimientoPalitoMagico)
    //talismanDedicacion = new Item(None,Map(Trabajo -> (Trabajo.atributoPrincipal*0.1*)), requerimientoPalitoMagico)
    talismanMinimalismo = new Item(None, Map(Hp ->(50+)),sinRequerimiento)
    arcoViejo = new Item(Some(Manos),Map(Fuerza -> (2+)), sinRequerimiento)    
  }

  @Test
  def puedePortarItem_test() = {
    assertEquals(true, kaerin.puedePortarItem(laBotellita))
   // println("por dios kaerin, largá la botellita!")
  }
  
  @Test
  def estadoPostPortarItem_test() = {
    var kaerinConNuevoItem=kaerin.equiparItem(laBotellita)
    assertEquals(4, kaerinConNuevoItem.hp)
  }
  @Test
  def cantItemPOSTEquipamientoDeIgualParte_test() = {// como ya tenia un item en la mano derecha, se le reemplaza
   
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
 @Test
  def cantItemPOSTEquipamientoDeTalismanes_test() = {
   
    var kaerinRecargadoConNuevoTalisman=kaerinRecargado.equiparItem(talismanMinimalismo) 

    assertEquals(4, kaerinRecargadoConNuevoTalisman.items.size)
  }
 @Test
  def cantItemPOSTEquipamientoDeItemAmbasManos_test() = { //antes tenia algo en una de sus manos
   
    var kaerinConItem2Manos=kaerin.equiparItem(arcoViejo) 
    println(kaerinConItem2Manos.items)
    assertEquals(1, kaerinConItem2Manos.items.size)
  } 
// @Test
//  def cantItemPOSTEquipamientoDeItemAmbasManoss_test() = { //antes tenia un item en cada mano
//   //kaerinRecargado.items.foreach(item=>  println(item))
//    var kaerinConItem2Manos=kaerinRecargado.equiparItem(arcoViejo) 
//   //kaerinConItem2Manos.items.foreach(item=>  println(item))
//    assertEquals(1, kaerinConItem2Manos.items.size)
//  } 
//  @Test
//  def cantItemPOSTEquipamientoDeItemAmbasManosss_test() = { //antes tenia un item en ambas manos
//   kaerinDefensor.items.foreach(item=>  println(item))
//    var kaerinDefensorArmado=kaerinDefensor.equiparItem(palitoMagico) 
//   kaerinDefensorArmado.items.foreach(item=>  println(item))
//    assertEquals(1, kaerinDefensorArmado.items.size)
//  } 
}