/**
 * Classe que representa um ambiente chamado purgatorio e que herda da classe
 * abstrata Ambientes 
 * @author junior
 */


public class Purgatorio extends Ambiente {
    
    /**
     * Constroi um ambiente "Purgatorio" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    
    private Item portadorAlmas;
    private boolean itemFoiColetado;
    
    public Purgatorio(String nomeAmbiente)  {
        super(nomeAmbiente);
        portadorAlmas = new Item("Portador de almas", "Armazena 10 almas em seu interior.");
        itemFoiColetado = false;
    }

    @Override
    public void mensagemDeEntrada(Dean dean) {
        
        if(getJaVisitada() == false){ // se o jogador ainda nao passou por este ambiente
            //fazer texto do purgatório
            setJaVisitada(true);
            
            if(dean.getMochila().espacoDisponivel()){
                dean.getMochila().inserir(portadorAlmas);
                itemFoiColetado = true;
                System.out.println("O portador de almas foi coletado e está na mochila");
            }
            else{
                System.out.println("Você não possui espaço suficiente na mochila para pegar o item");
            }
        }
        else{ // caso o jogador ja tenha passado por este ambiente antes
            if(itemFoiColetado == false){
                if(dean.getMochila().espacoDisponivel()){
                    dean.getMochila().inserir(portadorAlmas);
                    itemFoiColetado = true;
                    System.out.println("O portador de almas foi coletado e está na mochila");
                }
                else{
                    System.out.println("Você não possui espaço suficiente na mochila para pegar o item");
                }
            }
            else{
                // fazer mensagem de que não existe mais nada a ser feito neste ambiente
            }
        }
    }

}
