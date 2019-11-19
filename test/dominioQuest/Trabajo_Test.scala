package dominioQuest


import org.junit.Before
import org.junit.Test
import org.junit.Assert._


class Trabajo_Test {

var heroe:Heroe = null

var hechicero:Trabajo = null
var berserk:Trabajo = null

var cascoVikingo:Item = null
var laMataDragones:Item = null

var manoDerecha= new ParteDelCuerpo(None)
var cabeza= new ParteDelCuerpo(None)

  @Before
  def setup() = {
     hechicero=new Trabajo(Fuerza, Map(Fuerza -> (158+)))
     berserk=new Trabajo(Inteligencia, Map(Hp -> (299+)))

     cascoVikingo=new Item(Some(Cabeza),Map(Hp -> (10+)),List(new RequerimientosItem(None, Map(Fuerza -> (30<)))))
     laMataDragones= new Item(Some(ManoDerecha),Map(Fuerza ->(1000*)),List(new RequerimientosItem(None, Map(Fuerza -> (10>)))))
        
     
     heroe = new Heroe(1,2,3,4, None, List(cascoVikingo))
  }

 @Test
  def aplicarUnTrabajo_test() = {
    println("fuerza antes del trabajo: " + heroe.fuerza)
    assertEquals(160,  heroe.aplicarTrabajo(Some(hechicero)).fuerza)
  }
// @Test
//  def cambioDeTrabajo_test() = {
//    println("Organizacion del trabajo anterior " + heroe.especializacion.atributoPrincipal)
//    assertEquals(300, heroe.aplicarTrabajo(berserk).hp)
//    println("Organizacion del  nuevo trabajo " + heroe.aplicarTrabajo(berserk).especializacion.atributoPrincipal)
//  }

}