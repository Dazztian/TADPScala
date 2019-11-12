package dominioQuest

case class Heroe (val hp: Int,
    val fuerza: Int,
    val velocidad: Int,
    val inteligencia: Int,
    val especializacion: Trabajo,
    val items: List[Item],
    val listaPartes: List[ParteDelCuerpo] )  {
  
  def modificarHp(modificacion: Int=>Int) = this.copy(hp = modificacion(this.hp)).verificarParams
  def modificarFuerza(modificacion: Int=>Int) = this.copy(fuerza = modificacion(this.fuerza)).verificarParams
  def modificarInteligencia(modificacion: Int=>Int) = this.copy(inteligencia = modificacion(this.inteligencia)).verificarParams
  def modificarVelocidad(modificacion: Int=>Int) = this.copy(velocidad = modificacion(this.velocidad)).verificarParams
  def modificarListaItems(listaNueva: List[(Item)]) = this.copy(items = listaNueva)
  
def getStatActaul(unStat: Stat):Int ={ // TODO ver el tipo del retorno
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
     this.copy(hp = 1)
    if (this.fuerza < 1)
     this.copy(fuerza = 1)
    if (this.inteligencia < 1)
     this.copy(inteligencia = 1)
    if (this.velocidad < 1)
     this.copy(velocidad = 1)
    this
 }
  
def equiparItem(unItem: Item) :Heroe = 
{
 if (this.puedePortarItem(unItem))
  {  
    //aplico las modificaciones del item
   return  (unItem.efectos.foldLeft(this)
    {
     (semilla,diccionarioStatEfecto) =>   (diccionarioStatEfecto._1 match {
      case Fuerza =>  semilla.modificarFuerza(diccionarioStatEfecto._2).verificarParams
      case Hp => semilla.modificarHp(diccionarioStatEfecto._2).verificarParams
      case Velocidad => semilla.modificarInteligencia(diccionarioStatEfecto._2).verificarParams
      case Inteligencia => semilla.modificarVelocidad(diccionarioStatEfecto._2).verificarParams})
     })
   //equipo el item  
   .copy(items = unItem :: this.items  )      
  }
//Caso no puede portar item, se devuelve a si mismo
 return  this  
}

def puedePortarItem(unItem: Item) :Boolean = 
  return this.cumpleConAtributosNecesariosDelItem(unItem) && unItem.puedeSerPortadoPor(this) 

//No se produce efecto xq los atributos son inmutables
def cumpleConAtributosNecesariosDelItem(unItem: Item) :Boolean = 
return unItem.condiciones.foldLeft(true)
{(semilla,diccionarioStatCondicion) => semilla &&  (diccionarioStatCondicion._1 match {
    case Fuerza => diccionarioStatCondicion._2(this.fuerza)
    case Hp => diccionarioStatCondicion._2(this.hp)
    case Velocidad => diccionarioStatCondicion._2(this.velocidad)
    case Inteligencia => diccionarioStatCondicion._2(this.inteligencia)
 })
 //////////////////////////////IMPORTANTE!!!!!////////////////////////////////////////////////////////
  //Aca HABRIA que añadir que chequee si tiene la parte del cuerpo que corresponda libre para equipar dicho objeto
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  }

def aplicarTrabajo(unTrabajo: Trabajo) :Heroe =
{
   return  (unTrabajo.atributosHeroe.foldLeft(this)
    {
     (semilla,diccionarioStatEfecto) =>   (diccionarioStatEfecto._1 match {
      case Fuerza =>  semilla.modificarFuerza(diccionarioStatEfecto._2).verificarParams
      case Hp => semilla.modificarHp(diccionarioStatEfecto._2).verificarParams
      case Velocidad => semilla.modificarInteligencia(diccionarioStatEfecto._2).verificarParams
      case Inteligencia => semilla.modificarVelocidad(diccionarioStatEfecto._2).verificarParams})
     })
     .copy(especializacion = unTrabajo)
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

}//ACA TERMINA HEROE

