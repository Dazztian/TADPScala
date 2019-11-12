package dominioQuest

case class Item (
    var parte: Equipamiento,
    var condiciones: Map[Stat, Int=>Boolean],
    var efectos: Map[Stat, Int=>Int],
    var condicionesPosta: List[RequerimientosItem])
{
  def puedeSerPortadoPor(unHeroe: Heroe):Boolean ={
    //Resuelve automaticamente las condiciones asociadas a un trabajo
    return condicionesPosta.foldLeft(true)( 
        (semilla,unaCondicion) => semilla  || 
        (unHeroe.especializacion== unaCondicion.unTrabajo) &&
        unHeroe.cumpleCon(unaCondicion.restriccionesSobreElTrabajo)
        )
    }
}

