package dominioQuest

class RequerimientosItem(
    var unTrabajo: Option[Trabajo],
    var restriccionesSobreElTrabajo: Map[Stat, Int=>Boolean]){ 
 
}


