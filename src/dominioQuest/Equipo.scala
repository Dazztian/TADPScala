package dominioQuest

case class Equipo (
    val pozoComun: Int,
    val nombre: String,
    val integrantes: List[Heroe])
{
  
  def mejorHeroeSegun(unosIntegrantes: List[Heroe], criterio: (Heroe=>Int) ) : Option[Heroe] = {
    unosIntegrantes.sortWith(criterio(_) > criterio(_)).headOption
  }
   
 
 def lider(): Option[Heroe] = {
    val heroesOrdenados=this.integrantes.sortWith(_.mainStatSegunEspecializacion()>_.mainStatSegunEspecializacion())
    heroesOrdenados match{
      case primerHeroe::(segundoHeroe::_)
      if(primerHeroe.mainStatSegunEspecializacion() == segundoHeroe.mainStatSegunEspecializacion()) 
        => return None
      case _ => return mejorHeroeSegun(integrantes, _.mainStatSegunEspecializacion)  
  }
}
     
  def reemplazarMiembro(miembroNuevo :Heroe, miembroAReemplazar :Heroe):Equipo = 
  { 
    this.copy(integrantes=miembroNuevo ::
      this.integrantes.filter(x => {x!=miembroAReemplazar}))
  }
      
    //Obtengo los elementos que NO SON el miembro a reemplazar y le agrego el nuevo miembro
    
      
     
 /* 
  def realizarMision(unaMision:Mision) :Result = {
    unaMision.tareas.foldLeft(Result(this)) { 
      (previousResult, tarea) => {
        previousResult match{
          case Success(_) => {
            val heroeElegido = tarea.encontrarMejorHeroe(previousResult.equipo)
            tarea.cumplirTarea(heroeElegido, previousResult.equipo) // devuelve un Result
          }
          case NoPuedeRealizarse(_,tarea) => NoPuedeRealizarse(this,tarea)
          case Failure(_,_) => Failure(this,new Exception)
        }
      }
    }//Termina de foldear
    match{//Aca COBRA la recompensa
      case Success(equipo) => Success(unaMision.recompensa.obtenerRecompensa(equipo))   
        case NoPuedeRealizarse(equipo,tarea) => NoPuedeRealizarse(equipo, tarea)
        case Failure(equipo, error) => Failure(equipo, error)
      } 
  }
*/

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
        if(recibeAlgoPositivo(unSoloHeroe, heroeConItem)){
          return this.reemplazarMiembro(heroeConItem, unSoloHeroe) 
        }
        else { return this.vender(item)}
      }
     case integrantes => {
        if(integrantes.exists(i => recibeAlgoPositivo(i, i.equiparItem(item)))){
          val integrantesConItem = integrantes.map(integrante => integrante.equiparItem(item))
          val mejorIntegranteConItem = mejorHeroeSegun(integrantesConItem, _.mainStatSegunEspecializacion)
          mejorIntegranteConItem match {
            case Some(integranteConItem) => {
              val miembroAReemplazar = getIntegrante(integranteConItem.id).getOrElse(integranteConItem)
              this.reemplazarMiembro(integranteConItem, miembroAReemplazar)
            }
            case _ => this
          }
        }
        else { 
          return this.vender(item) 
        }
        
      }
   }
  }


  
  def recibeAlgoPositivo(heroeOriginal:Heroe, heroeModificado:Heroe):Boolean =
  {
    return (heroeModificado.getStatActual(Fuerza) > heroeOriginal.getStatActual(Fuerza) 
        || heroeModificado.getStatActual(Hp) > heroeOriginal.getStatActual(Hp) 
        || heroeModificado.getStatActual(Inteligencia) > heroeOriginal.getStatActual(Inteligencia)  
        || heroeModificado.getStatActual(Velocidad) > heroeOriginal.getStatActual(Velocidad) )
  }
  
  def getIntegrante(unId:Int):Option[Heroe] = {
    integrantes.find(h => h.id == unId)
  }
  
  def vender(item: Item):Equipo ={
    this.agregarOroPozo(item.precio)
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
}