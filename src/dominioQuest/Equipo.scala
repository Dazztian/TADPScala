package dominioQuest

case class Equipo (
    var pozoComun: Int,
    var nombre: String,
    var integrantes: List[Heroe]) 
{
  //val criterio1:PartialFunction[Heroe,Item]={ def apply(f:Item) = }
  
  def mejorHeroeSegun(criterio: (Heroe=>Int) ) : Option[Heroe] = { // TODO repetimos muchas veces logica de match con cantiadad de losta de integrantes
    integrantes match{
      case Nil => None
      case unSoloHeroe::Nil => Some(unSoloHeroe)
      case _ => Some(integrantes.sortWith(criterio(_) > criterio(_)).head)
    }
  }
    
  def lider(): Option[Heroe] = {
    val heroesOrdenados= this.integrantes.sortWith(_.mainStatSegunEspecializacion()>_.mainStatSegunEspecializacion())
    heroesOrdenados match{
     case Nil => None
     case unSoloHeroe::Nil => Some(unSoloHeroe)
     case primerHeroe::_ =>
       if(heroesOrdenados.size >1 &&
         (heroesOrdenados(0).mainStatSegunEspecializacion() == heroesOrdenados(1).mainStatSegunEspecializacion()))
        {
         return None
       }else{
        return Some(heroesOrdenados(0))
       }
    }
   
  }
  
     
  def reemplazarMiembro(miembroNuevo :Heroe,miembroAReemplazar :Heroe):Equipo =
      this.copy(integrantes=miembroNuevo ::
      this.integrantes.filter(x => {x!=miembroAReemplazar}))
    //Obtengo los elementos que NO SON el miembro a reemplazar y le agrego el nuevo miembro
    
      
     
  
  def realizarMision(unaMision:Mision) :Result = { 
    unaMision.tareas.foldLeft(Result(this)) { 
      (previousResult, tarea) => {
        //previusResult.cumplirTarea(heroeElegido, tarea.aplicacion)
        previousResult match{
          case Success(_) => {
            val heroeElegido = tarea.encontrarMejorHeroe(this)
            tarea.cumplirTarea(heroeElegido, this) // devuelve un Result
          }
          case NoPuedeRealizarse(_) => NoPuedeRealizarse(this)
          case Failure(_,_) => Failure(this,new Exception)
        }
      }
    }//Termina de foldear
    match{//Aca COBRA la recompensa
      case Success(equipo) => Success(unaMision.recompensa.obtenerRecompensa)   
        case NoPuedeRealizarse(equipo) => NoPuedeRealizarse(equipo)
        case Failure(equipo, error) => Failure(equipo, error)
      } 
  }

//------------------------------------------   RECOMPENSA      ------------------------------------------------------------
  
//type Recompensa = Equipo

  
def agregarOroPozo(oroNuevo :Int) :Equipo ={
    this.copy(pozoComun = this.pozoComun + oroNuevo)
  }

def obtenerItem(item: Item): Equipo = {
   integrantes match{
      case Nil => this
      case unSoloHeroe::Nil => {
        val heroeConItem = unSoloHeroe.equiparItem(item)
        if(recibeAlgoPositivo(unSoloHeroe, heroeConItem))
        {
          return this.reemplazarMiembro(heroeConItem, unSoloHeroe) // se lo damos a ese nunca va a producir algo negativo con el max
        }
        else
        {
          return this.agregarOroPozo(item.precio)
        }
      }
     case integrantes => {
        if(integrantes.exists(i => recibeAlgoPositivo(i, i.equiparItem(item))))
        {
          this.reemplazarMiembro(integrantes.map(integrante =>
          integrante.equiparItem(item)).sortWith(_.mainStatSegunEspecializacion()>_.mainStatSegunEspecializacion()).head, integrantes.sortWith
          (_.mainStatSegunEspecializacion()>_.mainStatSegunEspecializacion()).head )
        }
        else
        {
          return this.agregarOroPozo(item.precio)
        }
        
      }
   }
  }
  
  def recibeAlgoPositivo(heroeOriginal:Heroe, heroeModificado:Heroe):Boolean =
  {
    return (heroeModificado.getStatActaul(Fuerza) > heroeOriginal.getStatActaul(Fuerza) 
        || heroeModificado.getStatActaul(Hp) > heroeOriginal.getStatActaul(Hp) 
        || heroeModificado.getStatActaul(Inteligencia) > heroeOriginal.getStatActaul(Inteligencia)  
        || heroeModificado.getStatActaul(Velocidad) > heroeOriginal.getStatActaul(Velocidad) )
  }
  
  def obtenerMiembro(miembroNuevo :Heroe):Equipo =
     this.copy(integrantes=miembroNuevo:: this.integrantes )
     
  def incrementarStats(modificadores: Map[Stat, Int=>Int]) :Equipo =
    this.copy(integrantes= this.integrantes.map(unHeroe => unHeroe.modificarStats(modificadores)))
     
  
  
  //------------------------------------------   RECOMPENSA      ------------------------------------------------------------
  
  
  
  def getCantLadrones():Int = {
    return integrantes.count(heroe => heroe.especializacion match {
      case Some(Ladron(_,_))=> true
      case _=> false}  )
      
      }
//  def puedeRealizarTarea(unaTarea :Tarea) :Result = {
//    unaTarea.puedeRealizarlaAlgunHeroe(this)
//  }
}