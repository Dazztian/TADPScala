package dominioQuest

case class Equipo (
    var pozoComun: Int,
    var nombre: String,
    var integrantes: List[Heroe]) 
{
 
  //Devuelvo el heroe que mejor cumple el criterio
  def mejorHeroeSegun(criterio: (Heroe=>Int) ) :Heroe = return integrantes(0)
  
  def obtenerMiembro(miembroNuevo :Heroe):Equipo = 
     this.copy(integrantes=miembroNuevo:: this.integrantes )
     
  /*def reemplazarMiembro(miembroNuevo :Heroe,miembroAReemplazar :Heroe):Equipo =
    this.integrantes.exists(miembroAReemplazar)? return true: return false*/
}