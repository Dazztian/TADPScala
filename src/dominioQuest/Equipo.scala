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
    val heroesOrdenados= this.integrantes.
    sortWith(_.mainStatSegunEspecializacion()>_.mainStatSegunEspecializacion())
    if(heroesOrdenados(0).mainStatSegunEspecializacion() ==heroesOrdenados(1).mainStatSegunEspecializacion())
    {return None}
    return Some(heroesOrdenados(0))
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
  
  def realizarMision() :Equipo = {
    return this
  }
  
  def getCantIntegrantesPor(unaEspecializacion: Trabajo):Int = {
    return integrantes.count(i => i.especializacion == unaEspecializacion)
  }
  
}