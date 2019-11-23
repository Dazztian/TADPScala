package dominioQuest

abstract class Recompensa {
  val unEquipo: Equipo
  
  type TipoRecompensa= Equipo=> Equipo
  
 def obtenerRecompensa:Equipo= {
    return unEquipo
  }
}

case class AgregarOroPozo(unEquipo:Equipo)(oro: Int)extends Recompensa {
  override def obtenerRecompensa:Equipo= {
    return unEquipo.agregarOroPozo(oro)
  }
}

case class AgregarMiembro(unEquipo:Equipo)(unHeroe: Heroe)extends Recompensa {
  override def obtenerRecompensa:Equipo= {
    return unEquipo.obtenerMiembro(unHeroe)
  }
}

case class IncrementarStats(unEquipo:Equipo)(modificadores: Map[Stat, Int=>Int])extends Recompensa {
  override def obtenerRecompensa:Equipo= {
    return unEquipo.copy(integrantes=
      unEquipo.integrantes.map(unHeroe => unHeroe.modificarStats(modificadores)))
  }
}
/*
case class EquiparItem(unEquipo:Equipo)extends Recompensa {
  override def obtenerRecompensa(unHeroe: Heroe):Equipo= {
    return unEquipo.obtenerMiembro(unHeroe)
  }
}*/