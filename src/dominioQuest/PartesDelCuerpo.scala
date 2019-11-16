package dominioQuest


sealed trait Equipamiento

case object Cabeza extends Equipamiento
case object Torso extends Equipamiento
case object Pies extends Equipamiento
case object ManoDerecha extends Equipamiento
case object ManoIzquierda extends Equipamiento
case object Manos extends Equipamiento{
    val tupleManos = new Tuple2(ManoIzquierda, ManoDerecha)  
    val derecha = this.tupleManos._2
    val izquierda = this.tupleManos._1
}
