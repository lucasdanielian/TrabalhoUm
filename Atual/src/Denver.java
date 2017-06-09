public class Denver extends Ambiente {
    // Teste git
    /**
     * Constroi um ambiente "Denver" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    
    private Item denteLobo;
    private boolean itemFoiColetado;
            
    public Denver(String nomeAmbiente)  {
        super(nomeAmbiente);
        denteLobo = new Item("Dente de lobisomem","O dente de lobisomem representa o mal do mundo");
        itemFoiColetado = false;
    }
    
    public void mensagemDeEntrada(Dean dean){
        
        if(getJaVisitada() == false){//se o jogador nunca visitou este ambiente
            System.out.println("Dean se direciona para a cidade de Denver, no estado do Colorado.\nHá informações de que uma alcateia de lobisomens vêm atacando os moradores.\nApós certa investigação, Dean descobre onde estes lobos estão, invade seu esconderijo,\ne mata todos, um a um.");
            setJaVisitada(true);
            if(dean.getMochila().espacoDisponivel()){ // se ha espaco disponivel para armazenar o item
                dean.getMochila().inserir(denteLobo);
                itemFoiColetado = true;
                System.out.println("Um dente de lobo foi coletado");
            }
            else{
                System.out.println("Você não pode coletar o dente de lobo,\npois nao há espaço disponível na sua mochila");
            }
        }
        else{ // caso ele ja tenha vindo no ambiente
            if(itemFoiColetado == false){
                if(dean.getMochila().espacoDisponivel()){
                    dean.getMochila().inserir(denteLobo);
                    itemFoiColetado = true;
                    System.out.println("Um dente de lobo foi coletado");
                }
                else{
                    System.out.println("Você não pode coletar o dente de lobo,\npois nao há espaço disponível na sua mochila");
                }
            }
            else{ // caso ele ja tenha realizado todas as açoes possiveis neste ambiente
                System.out.println("Dean retorna à cidade de Denver, entretanto, ele não sabe o que fazer.\nNão há mais ações nesta cidade. Você está perdendo tempo aqui!");
            }
        }
    }
    
    
}
