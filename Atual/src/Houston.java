public class Houston extends Ambiente {
    
    /**
     * Constroi um ambiente "Inferno" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    
    private Item cabecaVampiro;
    private boolean itemFoiColetado;
    
    public Houston(String nomeAmbiente)  {
        super(nomeAmbiente);
        cabecaVampiro = new Item("Cabeca de Vampiro", "Cabeca do vampiro oiginal");
        itemFoiColetado = false;
    }
    
    @Override
    public void mensagemDeEntrada(Dean dean){
        
        if(getJaVisitada() == false){ // caso o jogador nunca tenha vindo a este ambiente
            // fazer o texto dos vampiros
            setJaVisitada(true);
            if(dean.getMochila().espacoDisponivel()){
                dean.getMochila().inserir(cabecaVampiro);
                itemFoiColetado = true;
                System.out.println("A cabeça do vampiro foi coletada");
            }
            else{
                System.out.println("Sua mochila está cheia, não há espaço para coletar o item");
            }
        }
        else{// caso o jogador ja tenha vindo a esse ambiente
            if(itemFoiColetado == false){
                if(dean.getMochila().espacoDisponivel()){
                    dean.getMochila().inserir(cabecaVampiro);
                    itemFoiColetado = true;
                    System.out.println("A cabeça do vampiro foi coletada");
                }
                else{
                    System.out.println("Sua mochila está cheia, não há espaço para coletar o item");
                }
            }
            else{
                //fazer texto sobre nao haver mais nada para fazer nesta cidade
            }
        }
        
    }
}
