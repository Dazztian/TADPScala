package dominioQuest

//import java.awt.TextArea

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class Taberna_Test {

  var maguitoDelBien:Heroe=null
  var liderLadron:Heroe=null
  var guerrerito:Heroe=null

  var arcoViejo:Item=null
  var talismanMinimalismo:Item=null

  var mago:Trabajo=null
  var ladron: Trabajo=null
  var guerrero: Trabajo=null

  var equipito: Equipo = null
  var elEquipo: Equipo=null

  var misionConRobar:Mision=null
  var soloRobar:Mision=null

  var robarTalis: Tarea=null

  var sinRequerimiento : List[RequerimientosItem] = List()

  var tabernita:Taberna=null
  var tablonote: Seq[Mision]=Seq[Mision]()

  val critiquin = {(e1:Equipo, e2:Equipo) => e1.pozoComun > e2.pozoComun}

  @Before
  def setup() = {
    mago = new Mago(Inteligencia, List(_.modificarFuerza(100+)))
    ladron =new Ladron(Velocidad, List(_.modificarVelocidad(10+), _.modificarHp(5-)) ) // Map(Velocidad -> (10+),Hp ->(5-))
    guerrero = new Guerrero(Fuerza, List(_.modificarHp(10+), _.modificarInteligencia(10-), _.modificarFuerza(15+))) // Map(Hp -> (10+), Inteligencia -> (10-), Fuerza ->(15+))

    maguitoDelBien= new Heroe(0, 2,100,1,1, 2,100,1,1, Some(mago),List())
    liderLadron= new Heroe(1, 100,200,3000,400, 100,200,3000,400,Some(ladron),List())
    guerrerito= new  Heroe(2, 50,2000,5,200, 50,2000,5,200, Some(guerrero), List())

    elEquipo = new Equipo(0,"Equipo sin gracia",List(maguitoDelBien,liderLadron,guerrerito))
    equipito = new Equipo(0,"Equipo sin gracia",List(maguitoDelBien,guerrerito))

    arcoViejo = new Item(Some(Manos),List(_.modificarFuerza(2+)), sinRequerimiento,20) // Map(Fuerza -> (2+))
    talismanMinimalismo = new Item(None, List(_.modificarHp(50+)),sinRequerimiento,20) // Map(Hp ->(50+))

    robarTalis = RobarTalisman(arcoViejo)

    soloRobar = new Mision(new AgregarOroPozo(88),List(robarTalis))
    misionConRobar = new Mision(new AgregarOroPozo(888),List(robarTalis,PelearContraMonstruo(10)))

    tablonote=Seq(misionConRobar,soloRobar)

    tabernita = new Taberna(tablonote)

  }

  @Test
  def elegirMisionMasBeneficiosa: Unit ={
    assertEquals(Some(misionConRobar), tabernita.elegirMision(tablonote,elEquipo,critiquin))
  }

  @Test
  def ordernarMisionesConTablonDeUnaSolaMision: Unit = {
    assertEquals(soloRobar, tabernita.ordenarMisionesSegunCriterio(Seq(soloRobar),elEquipo,critiquin).head)
  }

  @Test
  def ordenarTablonVacio: Unit = {
    assertEquals(Seq[Mision](), tabernita.ordenarMisionesSegunCriterio(Seq[Mision](),elEquipo,critiquin))
  }
  /*
 @Test
  def entrenarEquipo: Unit = {
   var equipoMisionero = elEquipo.realizarMision(misionConRobar).equipo.realizarMision(soloRobar).equipo
    assertEquals(equipoMisionero, tabernita.entrenar(tablonote,elEquipo,critiquin))
  }*/
  @Test
  def entrenarEquipoSinMision: Unit = {
    assertEquals(elEquipo, tabernita.entrenar(Seq[Mision](),elEquipo,critiquin))
  }
 @Test
  def entrenarEquipoNoPuedeRealizarMision: Unit = {
    assertEquals(equipito, tabernita.entrenar(tablonote,equipito,critiquin))
  }
}
