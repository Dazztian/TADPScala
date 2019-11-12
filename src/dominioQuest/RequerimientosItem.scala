package dominioQuest

class RequerimientosItem(
    var unTrabajo: Trabajo,
    var restriccionesSobreElTrabajo: Map[Stat, Int=>Boolean]){ 
 
}

