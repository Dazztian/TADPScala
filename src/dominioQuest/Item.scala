package dominioQuest

//listaCondiciones diccionario: atributo, condicion
case class Item (var descripcion: String,var parte: ExtremidadDelCuerpo,var condiciones: Map[Stat, Int=>Boolean],var efectos: Map[Stat, Int=>Int] )
{

}
