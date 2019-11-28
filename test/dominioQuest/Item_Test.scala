package dominioQuest

import org.junit.Before

import org.junit.Test
import org.junit.Assert._

class Item_Test {

var kaerin:Heroe = null
var kaerinRecargado :Heroe = null
var druida:Trabajo = null
var laBotellita:Item = null
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
   
    druida =new Trabajo(Hp, List(_.modificarFuerza(1+)))
    
    var funcionPrueba: Int=>Boolean=(30<)
    var requi:RequerimientosItem=new RequerimientosItem(Some(druida), Map(Fuerza -> funcionPrueba))
    var requerimientoPalitoMagico: List[RequerimientosItem]= List(requi) 
     
    laBotellita=new Item(Some(ManoDerecha),List(_.modificarHp(4+)), requerimientoPalitoMagico,10)
     
    var listaItems: List[Item]= List(laBotellita)
    
    kaerin = new Heroe(0, 1,40,3,4,1,40,3,4, Some(druida), listaItems)
        

    //ITEMS
    //val modificarHpFuerza :Heroe =>Heroe = _.modificarHp(40*)
    laBotellitaDeRicky = new Item(Some(ManoDerecha),List(_.modificarHp(40*)), requerimientoPalitoMagico,10)
    tacosDeSarkany = new Item(Some(Pies),List(_.modificarFuerza(300*)), List(new RequerimientosItem(Some(druida), Map(Inteligencia ->(0<)))),10)
    palitoMagico = new Item(Some(ManoIzquierda),List(_.modificarInteligencia(20+)), requerimientoPalitoMagico,10)
    //talismanDedicacion = new Item(None,Map(Trabajo -> (Trabajo.atributoPrincipal*0.1*)), requerimientoPalitoMagico)
    talismanMinimalismo = new Item(None, List(_.modificarHp(50+)),sinRequerimiento,10)
    arcoViejo = new Item(Some(Manos),List(_.modificarFuerza(2+)), sinRequerimiento,10)
    
     var listaItemsRecargada: List[Item]= List(laBotellita, palitoMagico)
     var listaItemsDefensor: List[Item]=List(arcoViejo)

    kaerinRecargado =new Heroe(1, 10,40,3,4, 10,40,3,4, Some(druida), listaItemsRecargada)//,partesDelCuerpo ))
    kaerinDefensor= new Heroe(2, 10,4,3,4, 10,4,3,4,Some(druida),listaItemsDefensor)
  }

  @Test
  def puedePortarItem_test() = {
    assertEquals(true, laBotellita.puedeSerPortadoPor(kaerin))
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

    assertEquals(3, kaerinRecargadoConNuevoTalisman.items.size)
  }
 @Test
  def cantItemPOSTEquipamientoDeItemAmbasManos_test() = { //antes tenia algo en una de sus manos
   
    var kaerinConItem2Manos=kaerin.equiparItem(arcoViejo) 
    println(kaerinConItem2Manos.items)
    assertEquals(1, kaerinConItem2Manos.items.size)
  }
 @Test
  def cantItemPOSTEquipamientoDeItemAmbasManoss_test() = { //antes tenia un item en cada mano
    var kaerinConItem2Manos=kaerinRecargado.equiparItem(arcoViejo) 
    assertEquals(1, kaerinConItem2Manos.items.size)
  } 
 
  @Test
  def cantItemPOSTEquipamientoDeItemAmbasManosss_test() = { //antes tenia un item en ambas manos
    var kaerinDefensorArmado=kaerinDefensor.equiparItem(laBotellitaDeRicky) 
    assertEquals(1, kaerinDefensorArmado.items.size)
  } 
}