package dominioQuest

case class Item (
    var parte: Equipamiento,
    var condiciones: Map[Stat, Int=>Boolean],
    var efectos: Map[Stat, Int=>Int],
    var condicionesPosta: List[RequerimientosItem])
{
  def puedeSerPortadoPor(unHeroe: Heroe):Boolean {}
}

class palitoMagico(
    var parte: Equipamiento,
    var condiciones: Map[Stat, Int=>Boolean],
    var efectos: Map[Stat, Int=>Int],
    var condicionesPosta: List[RequerimientosItem])
    extends Item(
     parte: Equipamiento,
     condiciones: Map[Stat, Int=>Boolean],
     efectos: Map[Stat, Int=>Int],
     condicionesPosta: List[RequerimientosItem])
//Un trabajo tiene asociado un diccionario, el cual contiene stats asociados a "requisitos"
{
  override def puedeSerPortadoPor(unHeroe: Heroe):Boolean ={
//ACA DEBERIA SER UN ANY, XQ BASTA CON QUE CUMPLA 1 DE LOS SETS DE CONDICIONES ASOCIADAS A UN TRABAJO
    return condicionesPosta.foldLeft(true)( 
        (semilla,elementoLista) => unHeroe.cumpleCon(elementoLista.restriccionesSobreElTrabajo)
    )// fold
    
//HAY QUE VER EL MODO DE HACER QUE ESTO LABURE COMO UN ANY, ES X ACA    
       
  }
}


