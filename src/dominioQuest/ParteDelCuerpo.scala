package dominioQuest

case class ParteDelCuerpo(var itemAsociado:Option[Item]) {
    
  def estaOcupada() :Boolean= return this.itemAsociado == null
  
}