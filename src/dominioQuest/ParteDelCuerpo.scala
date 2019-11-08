package dominioQuest

class ParteDelCuerpo {
  
  var itemAsociado:Item = null
  
  def estaOcupada() :Boolean= return this.itemAsociado == null
  
}