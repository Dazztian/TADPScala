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
}