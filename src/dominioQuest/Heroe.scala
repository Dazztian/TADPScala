package dominioQuest

case class Heroe (var hp: Int,var fuerza: Int,var velocidad: Int,var inteligencia: Int, 
    var especializacion: Trabajo,var items: Item,var listaPartes: List[ParteDelCuerpo] )  
{
  def modificarHp(modificacion: Int=>Int) = this.copy(hp = modificacion(this.hp)).verificarParams
  def modificarFuerza(modificacion: Int=>Int) = this.copy(fuerza = modificacion(this.fuerza)).verificarParams
  def modificarInteligencia(modificacion: Int=>Int) = this.copy(inteligencia = modificacion(this.inteligencia)).verificarParams
  def modificarVelocidad(modificacion: Int=>Int) = this.copy(velocidad = modificacion(this.velocidad)).verificarParams

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
     for ( (stat, modificacion) <- unItem.efectos)
    {  
      stat match {
       case Fuerza =>  this.modificarFuerza(modificacion).verificarParams
      case Hp => this.modificarHp(modificacion).verificarParams
      case Velocidad => this.modificarInteligencia(modificacion).verificarParams
      case Inteligencia => this.modificarVelocidad(modificacion).verificarParams
      }
    }
   //equipo el item  
   this.copy()      
  }
 //Esto esta x el caso en el que no pueda equipar el item devuelvo el mismo tipo sin modificar
 this.copy()  
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
  for ( (stat, modificacion) <- unTrabajo.atributosHeroe)
  {
    //ESTO DEBERIA SER
    //this.statObtenido=modificacion(this.statObtenido) 
      stat match {
       case Fuerza =>  this.modificarFuerza(modificacion).verificarParams
      case Hp => this.modificarHp(modificacion).verificarParams
      case Velocidad => this.modificarInteligencia(modificacion).verificarParams
      case Inteligencia => this.modificarVelocidad(modificacion).verificarParams
      }
    
    
  }
  this.copy(especializacion = unTrabajo)
}

}