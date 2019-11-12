package dominioQuest

case class Item (
    var parte: Equipamiento,
    var condiciones: Map[Stat, Int=>Boolean],
    var efectos: Map[Stat, Int=>Int],
    var condicionesPosta: List[RequerimientosItem])
{
  def puedeSerPortadoPor(unHeroe: Heroe):Boolean ={
//ACA DEBERIA SER UN ANY, XQ BASTA CON QUE CUMPLA 1 DE LOS SETS DE CONDICIONES ASOCIADAS A UN TRABAJO
    //Resuelve automaticamente las condiciones asociadas a un trabajo
    return condicionesPosta.foldLeft(false)( 
        (semilla,unaCondicion) => semilla  || 
        (unHeroe.especializacion== unaCondicion.unTrabajo) && unHeroe.cumpleCon(unaCondicion.restriccionesSobreElTrabajo)
        )
    }
}

