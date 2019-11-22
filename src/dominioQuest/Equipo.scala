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
     case primerHeroe::_ =>  if(heroesOrdenados.size >1 && (heroesOrdenados(0).mainStatSegunEspecializacion() == heroesOrdenados(1).mainStatSegunEspecializacion()))
        {
         return None
       }else{
        return Some(heroesOrdenados(0))
       }
    }
   
  }
  
  def obtenerMiembro(miembroNuevo :Heroe):Equipo =
     this.copy(integrantes=miembroNuevo:: this.integrantes )
     
     
  def reemplazarMiembro(miembroNuevo :Heroe,miembroAReemplazar :Heroe):Equipo =
      this.copy(integrantes=miembroNuevo ::
      this.integrantes.filter(x => {x!=miembroAReemplazar}))
    //Obtengo los elementos que NO SON el miembro a reemplazar y le agrego el nuevo miembro
    
      
  def obtenerItem(item: Item): Option[Heroe] = {
   integrantes match{
      case Nil => None
      case unSoloHeroe::Nil => Some(unSoloHeroe.equiparItem(item)) // se lo damos a ese nunca va a producir algo negativo con el max
      case integrantes => Some(integrantes.map(integrante => integrante.equiparItem(item)).sortWith(_.mainStatSegunEspecializacion()>_.mainStatSegunEspecializacion()).head)
    }
  }
  
  def realizarMision(unaMision:Mision) :Result = { 
    unaMision.tareas.foldLeft(Result(this)) { //Result(this)
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
    } match{
      case Success(equipo) => Success(unaMision.recompensa.foldLeft(equipo){
        (Semilla,recompensa)=>{
          recompensa(equipo)
          }
        })
        case NoPuedeRealizarse(equipo) => NoPuedeRealizarse(equipo)
        case Failure(equipo, error) => Failure(equipo, error)
      } 
  }

  def agregarOroPozo(equipo: Equipo) ={
    equipo.copy(pozoComun = equipo.pozoComun + 100)
  }
  
  
  def getCantLadrones():Int = {
    return integrantes.count(heroe => heroe.especializacion match {
      case Some(Ladron(_,_))=> true
      case _=> false}  )
      
      }
//  def puedeRealizarTarea(unaTarea :Tarea) :Result = {
//    unaTarea.puedeRealizarlaAlgunHeroe(this)
//  }
}