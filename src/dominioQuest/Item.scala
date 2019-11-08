package dominioQuest

//listaCondiciones diccionario: atributo, condicion
class Item (unaDescripcion: String, dondeSeEquipa: ExtremidadDelCuerpo, listaCondiciones: Map[Stat, Int=>Boolean], listaEfectos: Map[Stat, Int=>Int] )
{
  var descripcion=unaDescripcion
  var parte=dondeSeEquipa
  var condiciones=listaCondiciones
  
}
