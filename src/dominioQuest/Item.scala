package dominioQuest

case class Item (
    var parte: Option[Equipamiento],
    var efectos: List [Heroe => Heroe], //(_.modificarHp(40*))
    var condicionesPosta: List[RequerimientosItem],
    var precio: Int
    )
{
  def puedeSerPortadoPor(unHeroe: Heroe):Boolean ={
    //Resuelve automaticamente las condiciones asociadas a un trabajo
    return condicionesPosta.forall( unaCondicion => { 
        (unHeroe.especializacion== unaCondicion.unTrabajo) &&
        unHeroe.cumpleCon(unaCondicion.restriccionesSobreElTrabajo)
        } )
    }
}

