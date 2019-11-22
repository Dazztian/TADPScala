package dominioQuest

case class Equipo (
    var pozoComun: Int,
    var nombre: String,
    var integrantes: List[Heroe]) 
{
  //val criterio1:PartialFunction[Heroe,Item]={ def apply(f:Item) = }
  
  //Devuelvo el heroe que mejor cumple el criterio
  def mejorHeroeSegun(criterio: (Heroe=>Int) ) : Heroe = {
    integrantes.sortWith(criterio(_) > criterio(_)).head
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
    
      
  def obtenerItem(item: Item) = {
  this.integrantes.map(integrante => integrante.equiparItem(item)).//equipamos el item a los integrantes
  sortWith(_.mainStatSegunEspecializacion()>_.mainStatSegunEspecializacion()).//comparamos por stat
  head//nos quedamos con el head de la lista 
  }
  
  def realizarMision(unaMision:Mision) :Equipo = {
    return this
  }
  
  /*def getCantLadrones():Int = {
    return integrantes.filter(heroe =>
      heroe.especializacion match {
      case Some(Ladron(_,_))=> true
      case _=> false}  )
      .size
      }*/
  
  def getCantLadrones():Int = {
    return integrantes.count(heroe => heroe.especializacion match {
      case Some(Ladron(_,_))=> true
      case _=> false}  )
      
      }
  def puedeRealizarTarea(unaTarea :Tarea) :Result = {
    unaTarea.puedeRealizarlaAlgunHeroe(this)
  }
}