package dominioQuest

case class Heroe (val hp: Int,val fuerza: Int,val velocidad: Int,val inteligencia: Int, 
    val especializacion: Trabajo,val items: List[Item],val listaPartes: List[ParteDelCuerpo] )  
{
  def modificarHp(modificacion: Int=>Int) = this.copy(hp = modificacion(this.hp)).verificarParams
  def modificarFuerza(modificacion: Int=>Int) = this.copy(fuerza = modificacion(this.fuerza)).verificarParams
  def modificarInteligencia(modificacion: Int=>Int) = this.copy(inteligencia = modificacion(this.inteligencia)).verificarParams
  def modificarVelocidad(modificacion: Int=>Int) = this.copy(velocidad = modificacion(this.velocidad)).verificarParams
  def modificarListaItems(listaNueva: List[(Item)]) = this.copy(items = listaNueva)


   def verificarParams = {
    if (hp < 1)
      throw NoPuedeHpMenorAUno(this)
    if (fuerza < 1)
      throw NoPuedeFuerzaMenorAUno(this)
    if (inteligencia < 1)
      throw NoPuedeInteligenciaMenorAUno(this)
    if (velocidad < 1)
      throw NoPuedeVelocidaMenorAUno(this)
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
 //Esto esta x el caso en el que no pueda equipar el item devuelvo el mismo tipo sin modificar
 return  this  
}
  



//Aca deberia poder chequear y que no produzca efecto. Efecto se produce cuando se lo equipa
def puedePortarItem(unItem: Item) :Boolean =
//Aca HABRIA que añadir que chequee si tiene la parte del cuerpo que corresponda libre para equipar dicho objeto  
return unItem.condiciones.foldLeft(true)
{(semilla,diccionarioStatCondicion) => semilla &&  (diccionarioStatCondicion._1 match {
    case Fuerza => diccionarioStatCondicion._2(this.fuerza)
    case Hp => diccionarioStatCondicion._2(this.hp)
    case Velocidad => diccionarioStatCondicion._2(this.velocidad)
    case Inteligencia => diccionarioStatCondicion._2(this.inteligencia)
 })
  
  }

def aplicarTrabajo(unTrabajo: Trabajo) :Heroe =
{
 //Esto se resuelve con fold
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


}