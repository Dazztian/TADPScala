package dominioQuest

class Heroe (hpHeroe: Int, FuerzaHeroe: Int, VelocidadHeroe: Int,inteligenciaHeroe: Int, 
    unaEspecializacion: Trabajo, listaDeItems: Item, partesDelCuerpo: List[ParteDelCuerpo] )  
{

  var hp=hpHeroe
  var fuerza=FuerzaHeroe
  var velocidad=VelocidadHeroe
  var inteligencia=inteligenciaHeroe
  var especializacion=unaEspecializacion
  var items=listaDeItems 
  
  //var listaPartes: List[ParteDelCuerpo]=partesDelCuerpo
  var listaPartes=partesDelCuerpo
  
  
//verifica que como minimo reduzca a  1
def reduccionStat() {}


def equiparItem(unItem: Item)
{
  if (this.puedePortarItem(unItem))
  {
    println("Bien ahi crack podés portar el item")
    //equipo el item
    //aplico las modificaciones del item
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
}

}