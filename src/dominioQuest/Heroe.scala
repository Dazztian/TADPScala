package dominioQuest

case class Heroe (var hp: Int,var fuerza: Int,var velocidad: Int,var inteligencia: Int, 
    var especializacion: Trabajo,var items: Item,var listaPartes: List[ParteDelCuerpo] )  
{
 
  
//verifica que como minimo reduzca a  1
def reduccionStat() {}


def equiparItem(unItem: Item)
{
  if (this.puedePortarItem(unItem))
  {
    println("Bien ahi crack podés portar el item")
    //equipo el item
      /*this.listaPartes 
      unItem.parte    */
    
    
    
    //aplico las modificaciones del item
     for ( (stat, modificacion) <- unItem.efectos)
    {  //ESTO ES LITERALMENTE IGUAL QUE EN APLICAR TRABAJO
       //LOGICA REPETIDA
      stat match {
      case Fuerza => this.fuerza=modificacion(this.fuerza)
      case Hp => this.hp=modificacion(this.hp)
      case Velocidad => this.velocidad=modificacion(this.velocidad)
      case Inteligencia => this.inteligencia=modificacion(this.inteligencia)
      }
    }
  }

  
  
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



def aplicarTrabajo(unTrabajo: Trabajo)
{
  for ( (stat, modificacion) <- unTrabajo.atributosHeroe)
  {
    //ESTO DEBERIA SER
    //this.statObtenido=modificacion(this.statObtenido)
    stat match {
      case Fuerza => this.fuerza=modificacion(this.fuerza)
      case Hp => this.hp=modificacion(this.hp)
      case Velocidad => this.velocidad=modificacion(this.velocidad)
      case Inteligencia => this.inteligencia=modificacion(this.inteligencia)
    }
    
    
  } 
   this.especializacion=unTrabajo
}

}