package dominioQuest

case class Equipo (
    val pozoComun: Int,
    val nombre: String,
    val integrantes: List[Heroe])
{
  
  def mejorHeroeSegun(criterio: (Heroe=>Int) ) : Option[Heroe] = {
    integrantes.sortWith(criterio(_) > criterio(_)).headOption
  }
   
 /* 
 def lider(): Option[Heroe] = {
    val heroesOrdenados=this.integrantes.sortWith(_.mainStatSegunEspecializacion()>_.mainStatSegunEspecializacion())
    val elLider= mejorHeroeSegun(_.mainStatSegunEspecializacion)
    heroesOrdenados match{
      case primerHeroe::_ 
      if(heroesOrdenados.head.mainStatSegunEspecializacion() == heroesOrdenados(1).mainStatSegunEspecializacion()) 
        => return None
      case _ => return elLider   
  }
}*/
  
  def lider(): Option[Heroe] = {
    val heroesOrdenados=this.integrantes.sortWith(_.mainStatSegunEspecializacion()>_.mainStatSegunEspecializacion())
    val elLider= mejorHeroeSegun(_.mainStatSegunEspecializacion)
    heroesOrdenados match{
     case Nil => None
     case unSoloHeroe::Nil => Some(unSoloHeroe)
     case primerHeroe::_ =>
       if
         (heroesOrdenados.head.mainStatSegunEspecializacion() == heroesOrdenados(1).mainStatSegunEspecializacion())
        {
         return None
       }else{
        return Some(primerHeroe)
       }
    }
    }
   
  
     
  def reemplazarMiembro(miembroNuevo :Heroe,miembroAReemplazar :Heroe):Equipo =
      this.copy(integrantes=miembroNuevo ::
      this.integrantes.filter(x => {x!=miembroAReemplazar}))
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
          return this.reemplazarMiembro(heroeConItem, unSoloHeroe) // se lo damos a ese nunca va a producir algo negativo con el max
        }
        else { return this.agregarOroPozo(item.precio)}
      }
     case integrantes => {
        if(integrantes.exists(i => recibeAlgoPositivo(i, i.equiparItem(item)))){
          this.reemplazarMiembro(integrantes.map(integrante =>
          integrante.equiparItem(item)).sortWith(_.mainStatSegunEspecializacion()>_.mainStatSegunEspecializacion()).head, integrantes.sortWith
          (_.mainStatSegunEspecializacion()>_.mainStatSegunEspecializacion()).head )
        }
        else { return this.agregarOroPozo(item.precio)}
        
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