package dominioQuest

case class Heroe (val hp: Int,
    val fuerza: Int,
    val velocidad: Int,
    val inteligencia: Int,
    val especializacion: Option[Trabajo],
    val items: List[Item],
    )  {
  
  def modificarHp(modificacion: Int=>Int) = this.copy(hp = modificacion(this.hp)).verificarParams
  def modificarFuerza(modificacion: Int=>Int) = this.copy(fuerza = modificacion(this.fuerza)).verificarParams
  def modificarInteligencia(modificacion: Int=>Int) = this.copy(inteligencia = modificacion(this.inteligencia)).verificarParams
  def modificarVelocidad(modificacion: Int=>Int) = this.copy(velocidad = modificacion(this.velocidad)).verificarParams
  def modificarListaItems(listaNueva: List[(Item)]) = this.copy(items = listaNueva)
  
def getStatActaul(unStat: Stat):Int ={ 
    this.items.foldLeft(this.aplicarTrabajo(this.especializacion)){
       (semilla,unItem) => semilla.equiparItem(unItem)
     }
    unStat match{
      case Hp => return this.hp
      case Fuerza => return this.fuerza
      case Inteligencia => return this.inteligencia
      case Velocidad => return this.velocidad
    }
  }

  def verificarParams = {
    if (this.hp < 1)
     this.copy(hp.max(1))
    if (this.fuerza < 1)
     this.copy(fuerza.max(1))
    if (this.inteligencia < 1)
     this.copy(inteligencia.max(1))
    if (this.velocidad < 1)
     this.copy(velocidad.max(1))
    this
 }
  
def equiparItem(unItem: Item) :Heroe = 
{
 if (this.puedePortarItem(unItem))
  {  
    //aplico las modificaciones del item e incorporo item
   return  this.modificarStats(unItem.efectos).incorporarItem(unItem)     
  }
//Caso no puede portar item, se devuelve a si mismo
 return  this  
}

def puedePortarItem(unItem: Item) :Boolean = 
  return  unItem.puedeSerPortadoPor(this)

def incorporarItem(itemNuevo: Item) :Heroe =
  
  itemNuevo.parte match{
    case Some(parte) =>  this.copy(items = itemNuevo :: this.items.filter(item => !this.itemsOcupadandoParte(parte).contains(item))) 
    case None => this.copy(items = itemNuevo :: this.items)
  
}


def itemsOcupadandoParte(parteAOcupar :Equipamiento) :List[Item]= 
   this.items.filter(item => item.parte match{
     case Some(ManoDerecha) => (parteAOcupar == Manos || parteAOcupar == ManoDerecha)
     case Some(ManoIzquierda) => (parteAOcupar == Manos || parteAOcupar == ManoIzquierda)
     case Some(Manos) => (parteAOcupar == ManoIzquierda || parteAOcupar == Manos || parteAOcupar == ManoDerecha )
     case Some(parteDelItem) => parteDelItem == parteAOcupar
     case None => false
})

def aplicarTrabajo(unTrabajo: Option[Trabajo]) :Heroe =
{ 
  return  this.modificarStats(unTrabajo.get.atributosHeroe).copy(especializacion = unTrabajo)
}

def modificarStats(modificadores: Map[Stat, Int=>Int]):Heroe=
  modificadores.foldLeft(this)
    {
     (semilla,diccionarioStatEfecto) =>   (diccionarioStatEfecto._1 match {
      case Fuerza =>  semilla.modificarFuerza(diccionarioStatEfecto._2)
      case Hp => semilla.modificarHp(diccionarioStatEfecto._2)
      case Velocidad => semilla.modificarInteligencia(diccionarioStatEfecto._2)
      case Inteligencia => semilla.modificarVelocidad(diccionarioStatEfecto._2)})
     }
     
//Funcion auxiliar, devuelve true si cumple con todas las condiciones dadas
def cumpleCon(condiciones: Map[Stat, Int=>Boolean]) :Boolean = 
return condiciones.foldLeft(true)
{(semilla,diccionarioStatCondicion) => semilla &&  (diccionarioStatCondicion._1 match {
    case Fuerza => diccionarioStatCondicion._2(this.fuerza)
    case Hp => diccionarioStatCondicion._2(this.hp)
    case Velocidad => diccionarioStatCondicion._2(this.velocidad)
    case Inteligencia => diccionarioStatCondicion._2(this.inteligencia)
 })

}


def realizarTarea(unaTarea :Tarea):ResultTarea = {
  unaTarea.realizarTareaPor(Some(this))
//  unaTarea match {
//  case PelearContraMonstruo(vidaAReducir) if(this.fuerza<20)=> this.copy(hp =this.hp-vidaAReducir)
//  case RobarTalisman(unItem) =>return this 
//  case ForzarPuerta() => this.especializacion match {
//    case Some(Mago(_,_)) =>return this 
//    case Some(Ladron(_,_))=>return this
//    case _ =>return this.copy(hp= this.hp-5, fuerza=this.fuerza+1)}  
//     }
//  
  }

def mainStatSegunEspecializacion(): Int = {
  //return this.hp 
  especializacion.get.atributoPrincipal match {
      case Hp => return this.hp
      case Fuerza => return this.fuerza
      case Inteligencia => return this.inteligencia
      case Velocidad => return this.velocidad
      case _ => return 0
    }
  }



}

