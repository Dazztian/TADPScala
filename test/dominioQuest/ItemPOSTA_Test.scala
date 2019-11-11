package dominioQuest

class ItemPOSTA_Test {
  
var heroe:Heroe = null
var mago:Trabajo = null
var palitoMagico:Item = null
var manoDerecha= new ParteDelCuerpo(None)



@Before
  def setup() = {
     mago=new Trabajo("Perro de ambar", Map(Fuerza -> (158+)))
     var requerimientoPalitoMagico: List[RequerimientosItem]= List((Mago,Map(Fuerza -> (30<))))
     palitoMagico= new Item( ManoDerecha, Map(Fuerza -> (30>)),Map(Fuerza ->(1000* )),requerimientoPalitoMagico)
     
     manoDerecha.itemAsociado=Some(palitoMagico)
     
     var partesDelCuerpo: List[ParteDelCuerpo]= List(manoDerecha)
     var listaItems: List[Item]= List(palitoMagico)
        
     
     heroe = new Heroe(100,200,300,400, mago, listaItems, partesDelCuerpo)
  }

}