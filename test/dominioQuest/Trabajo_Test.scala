/*package dominioQuest


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
     hechicero=new Trabajo("Perro de ambar", Map(Fuerza -> (158+)))
     berserk=new Trabajo("Pyria de poder", Map(Hp -> (299+)))

     cascoVikingo=new Item("Casco Vikingo",Cabeza, Map(Fuerza -> (30<), Fuerza -> (40==), Hp -> (1==) ), Map(Hp ->(4* )))
     laMataDragones= new Item("La mata dragones", ManoDerecha, Map(Fuerza -> (30<)),Map(Fuerza ->(1000* )))
     

     manoDerecha.itemAsociado=Some(laMataDragones)
     
     val partesDelCuerpo: List[ParteDelCuerpo]= List(manoDerecha)
     var listaItems: List[Item]= List(cascoVikingo)
        
     
     heroe = new Heroe(1,2,3,4, hechicero, listaItems, partesDelCuerpo)
  }

 @Test
  def aplicarUnTrabajo_test() = {
    println("fuerza antes del trabajo: " + heroe.fuerza)
    assertEquals(160,  heroe.aplicarTrabajo(hechicero).fuerza)
  }
 @Test
  def cambioDeTrabajo_test() = {
    println("Organizacion del trabajo anterior " + heroe.especializacion.atributoPrincipal)
    assertEquals(300, heroe.aplicarTrabajo(berserk).hp)
    println("Organizacion del  nuevo trabajo " + heroe.aplicarTrabajo(berserk).especializacion.atributoPrincipal)
  }

}*/