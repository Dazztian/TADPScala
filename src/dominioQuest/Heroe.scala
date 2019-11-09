package dominioQuest

case class Heroe (var hp: Int,var fuerza: Int,var velocidad: Int,var inteligencia: Int, 
    var especializacion: Trabajo,var items: Item,var listaPartes: List[ParteDelCuerpo] )  
{
 
  
//verifica que como minimo reduzca a  1
def reduccionStat() {}


def equiparItem(unItem: Item) :Heroe = 
{
 if (this.puedePortarItem(unItem))
  {  
    //aplico las modificaciones del item
  
     for ( (stat, modificacion) <- unItem.efectos)
    {  //ESTO ES LITERALMENTE IGUAL QUE EN APLICAR TRABAJO
       //LOGICA REPETIDA
      stat match {
      case Fuerza => this.copy(fuerza = modificacion(this.fuerza))
      case Hp => this.copy(hp=modificacion(this.hp))
      case Velocidad => this.copy(velocidad=modificacion(this.velocidad))
      case Inteligencia => this.copy(inteligencia=modificacion(this.inteligencia))
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
       case Fuerza => this.copy(fuerza = modificacion(this.fuerza))
      case Hp => this.copy(hp=modificacion(this.hp))
      case Velocidad => this.copy(velocidad=modificacion(this.velocidad))
      case Inteligencia => this.copy(inteligencia=modificacion(this.inteligencia))
    }
    
    
  }
  this.copy(especializacion = unTrabajo)
}

}