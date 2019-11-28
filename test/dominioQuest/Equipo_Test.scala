package dominioQuest

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class Equipo_Test {
  
var heroe:Heroe = null
var mago:Trabajo=null
var equipo:Equipo = null
var arcoViejo:Item = null
var sinRequerimiento : List[RequerimientosItem] = List()
var liderLadron:Heroe = null
var ladron: Trabajo = null
var estoNoEsUnEquipo:Equipo = null
var guerrero:Trabajo = null
var guerreroDragon:Heroe = null
var equipoSolapa:Equipo = null
var talismanMinimalismo: Item = null

@Before
  def setup() = {
    mago=new Mago(Inteligencia, List(_.modificarFuerza(100+)))
    heroe = new Heroe(0,2,100,1,1, 2,100,1,1, Some(mago),List())
    ladron =new Ladron(Velocidad,List(_.modificarVelocidad(10+),_.modificarHp(5-)))
    liderLadron = new Heroe(1,100,200,3000,400, 100,200,3000,400,Some(ladron),List())
    guerrero = new Guerrero(Fuerza,List(_.modificarHp(10+),_.modificarInteligencia(10-),_.modificarFuerza(15+)))
    guerreroDragon = new Heroe(2,50,2000,5,200, 50,2000,5,200, Some(guerrero), List())
    equipo = new Equipo(0,"Equipo sin gracia",List(heroe,liderLadron,guerreroDragon))
    arcoViejo = new Item(Some(Manos),List(_.modificarFuerza(2+)), sinRequerimiento,10)  
    estoNoEsUnEquipo = new Equipo(0,"Equipo sin nadie",List())
    equipoSolapa = new Equipo(100,"Mejor solo que mal acomppaniado",List(heroe))  
    talismanMinimalismo = new Item(None,List(_.modificarHp(50+)),sinRequerimiento,10)

}

@Test
  def mejorHeroeSegun_test() = {
  assertEquals(Some(liderLadron),equipo.mejorHeroeSegun(equipo.integrantes, _.hp))
}

@Test
  def mejorHeroeSegunEquipoVacio_test() = {
  assertEquals(None,estoNoEsUnEquipo.mejorHeroeSegun(estoNoEsUnEquipo.integrantes, _.hp))
}
@Test
  def mejorHeroeSegunEquipoUnitario_test() = {
  assertEquals(Some(heroe),equipoSolapa.mejorHeroeSegun(equipoSolapa.integrantes, _.hp))
}
@Test
  def obtenerItem_test() = {
   var equipoConItem = equipo.reemplazarMiembro(liderLadron.equiparItem(arcoViejo), liderLadron)
    assertEquals(equipoConItem, equipo.obtenerItem(arcoViejo))
  }
@Test
  def obtenerItemEquipoUnitario_test() = {
    assertEquals(heroe.equiparItem(arcoViejo), equipoSolapa.obtenerItem(arcoViejo).integrantes(0))
  }
@Test
  def obtenerMiembre_Test() = {
    assertEquals(2, equipoSolapa.obtenerMiembro(guerreroDragon).integrantes.size)
  }
@Test
  def reemplarHeroePorOtro_test() = {
    assertEquals(guerreroDragon, equipoSolapa.reemplazarMiembro(guerreroDragon, heroe).integrantes(0))
  }
@Test
  def reemplarHeroePorSiMismoModificado_test() = {
  var guerreroDragonRecargado:Heroe = guerreroDragon.equiparItem(talismanMinimalismo)
   assertEquals(guerreroDragonRecargado, equipoSolapa.reemplazarMiembro(guerreroDragonRecargado, guerreroDragon).integrantes(0))
  }
@Test
  def sinLider_test() = {
   assertEquals(None, estoNoEsUnEquipo.lider())
  }
@Test
   def liderEquipoMultitudinario_Test()={
    assertEquals(Some(liderLadron), equipo.lider()) 
  }
@Test
  def liderSolo_test() = {
   assertEquals(Some(heroe), equipoSolapa.lider())
  }
}