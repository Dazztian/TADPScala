package dominioQuest

case class Heroe (
    val id: Int,
    val hpBase: Int,
    val fuerzaBase: Int,
    val velocidadBase: Int,
    val inteligenciaBase: Int,
    val hp: Int,
    val fuerza: Int,
    val velocidad: Int,
    val inteligencia: Int,
    val especializacion: Option[Trabajo],
    val items: List[Item]
    )  {
  
  def modificarHp(modificacion: Int=>Int) = this.copy(hp = modificacion(this.hp).max(1))
  def modificarFuerza(modificacion: Int=>Int) = this.copy(fuerza = modificacion(this.fuerza).max(1))
  def modificarInteligencia(modificacion: Int=>Int) = this.copy(inteligencia = modificacion(this.inteligencia).max(1))
  def modificarVelocidad(modificacion: Int=>Int) = this.copy(velocidad = modificacion(this.velocidad).max(1))
  
  def modificarListaItems(listaNueva: List[(Item)]) = this.copy(items = listaNueva)

def getStatActual(unStat: Stat):Int ={ 
    val heroeconTrabajoAplicadoYItemEquipado=this.items.foldLeft(this.aplicarEfectosDelTrabajo(this.especializacion))
      {(semilla,unItem) => semilla.equiparItem(unItem) }
    devolverStat(unStat, heroeconTrabajoAplicadoYItemEquipado)
}
  
def equiparItem(unItem: Item) :Heroe = 
{
 if (unItem.puedeSerPortadoPor(this))
  {  
    //aplico las modificaciones del item e incorporo item
   return  this.modificarStats(unItem.efectos).incorporarItem(unItem)     
  }
//Caso no puede portar item, se devuelve a si mismo
 return  this  
}

def incorporarItem(itemNuevo: Item) :Heroe =
  
  itemNuevo.parte match{
    case Some(parte) =>  this.copy(items = itemNuevo :: this.items.filter(item => !this.itemsOcupandoParte(parte).contains(item)))
    case None => this.copy(items = itemNuevo :: this.items)
  
}

def itemsOcupandoParte(parteAOcupar :Equipamiento) :List[Item]=
   this.items.filter(item => item.parte match{
     case Some(ManoDerecha) => (parteAOcupar == Manos || parteAOcupar == ManoDerecha)
     case Some(ManoIzquierda) => (parteAOcupar == Manos || parteAOcupar == ManoIzquierda)
     case Some(Manos) => (parteAOcupar == ManoIzquierda || parteAOcupar == Manos || parteAOcupar == ManoDerecha )
     case Some(parteDelItem) => parteDelItem == parteAOcupar
     case None => false
})

def cambiarTrabajo(unTrabajo: Option[Trabajo]) :Heroe =
  this.copy(especializacion = unTrabajo)

def aplicarEfectosDelTrabajo(unTrabajo: Option[Trabajo]) :Heroe = {
  unTrabajo.map(trabajo => this.modificarStats(trabajo.atributosHeroe)).getOrElse(this.copy(hp= hpBase, fuerza = fuerzaBase, inteligencia = inteligenciaBase))
}


def modificarStats(modificadores: List[Heroe => Heroe]):Heroe= {
  modificadores.foldLeft(this){
  (semilla,efecto) => efecto(semilla)}//(diccionarioStatEfecto._1.modificar(semilla,diccionarioStatEfecto._2))}

}
     
//Funcion auxiliar, devuelve true si cumple con todas las condiciones dadas
def cumpleCon(condiciones: Map[Stat, Int=>Boolean]) :Boolean =
  condiciones.forall(diccionarioStatCondicion => diccionarioStatCondicion._1.cumpleCon(this,diccionarioStatCondicion._2))

def mainStatSegunEspecializacion(): Int = {
  this.especializacion match{
    case Some(esp) => devolverStat(esp.atributoPrincipal, this)
    case _ => 0
  }
}

def devolverStat(unStat: Stat, unHeroe:Heroe) :Int = unStat.devolver(unHeroe)
 
}

