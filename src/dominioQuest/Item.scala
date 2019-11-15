package dominioQuest

case class Item (
    var parte: Option[Equipamiento],
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

