package dominioQuest

import java.awt.TextArea

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class Taberna_Test {
  var maguitoDelBien:Heroe
  var liderLadron:Heroe
  var guerrerito:Heroe

  var arcoViejo:Item
  var talismanMinimalismo:Item

  var mago:Trabajo
  var ladron: Trabajo
  var guerrero: Trabajo

  var elEquipo: Equipo

  var misionConRobar:Mision
  var soloRobar:Mision

  var robarTalis: Tarea

  var sinRequerimiento : List[RequerimientosItem] = List()

  var tabernita:Taberna
  var tablonote: Seq[Mision]

  @Before
  def setup() = {
    mago = new Mago(Inteligencia, Map(Fuerza -> (100 +)))
    ladron =new Ladron(Velocidad, Map(Velocidad -> (10+),Hp ->(5-)) )
    guerrero = new Guerrero(Fuerza, Map(Hp -> (10+), Inteligencia -> (10-), Fuerza ->(15+)))

    maguitoDelBien= new Heroe(2,100,1,1, Some(mago),List())
    liderLadron= new Heroe(100,200,3000,400,Some(ladron),List())
    guerrerito= new  Heroe(50,2000,5,200, Some(guerrero), List())

    elEquipo = new Equipo(0,"Equipo sin gracia",List(maguitoDelBien,liderLadron,guerrerito))

    arcoViejo = new Item(Some(Manos),Map(Fuerza -> (2+)), sinRequerimiento)
    talismanMinimalismo = new Item(None, Map(Hp ->(50+)),sinRequerimiento)

    robarTalis = RobarTalisman(arcoViejo)

    misionConRobar = new Mision(List(_.agregarOroPozo(100)),List(robarTalis,PelearContraMonstruo(10)))
    soloRobar = new Mision(List(),List(robarTalis))

    tablonote=Seq(misionConRobar,soloRobar)
    val critiquin = {(e1:Equipo, e2:Equipo) => e1.pozoComun > e2.pozoComun}
    tabernita = new Taberna(tablonote)

  }

  @Test


}
