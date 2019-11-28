package dominioQuest

abstract class Recompensa {
  
 type TipoRecompensa= Equipo=> Equipo
  
 def obtenerRecompensa(unEquipo:Equipo):Equipo
  
}

case class AgregarOroPozo(oro: Int)extends Recompensa {
  override def obtenerRecompensa(unEquipo:Equipo):Equipo= {
    return unEquipo.agregarOroPozo(oro)
  }
}

case class AgregarMiembro(unHeroe: Heroe)extends Recompensa {
  override def obtenerRecompensa(unEquipo:Equipo):Equipo= {
    return unEquipo.obtenerMiembro(unHeroe)
  }
}

case class IncrementarStats(modificadores: List[Heroe =>Heroe])extends Recompensa {
  override def obtenerRecompensa(unEquipo:Equipo):Equipo= {
    return unEquipo.copy(integrantes=
      unEquipo.integrantes.map(unHeroe => unHeroe.modificarStats(modificadores)))
  }
}

case class EquiparItem(item: Item)extends Recompensa {
  override def obtenerRecompensa(unEquipo:Equipo):Equipo= {
    return unEquipo.obtenerItem(item)
  }
}