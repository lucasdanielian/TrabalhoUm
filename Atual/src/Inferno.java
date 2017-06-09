/**
 * Classe que representa um ambiente chamado purgatorio e que herda da classe
 * abstrata Ambientes 
 * @author junior
 */
public class Inferno extends Ambiente {
    // teste lucas 3
    /**
     * Constroi um ambiente "Inferno" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    
    private boolean denteLobo;
    private boolean penaAnjo;
    private boolean almas;
    
    
    public Inferno(String nomeAmbiente)  {
        super(nomeAmbiente);
    }

    @Override
    public void mensagemDeEntrada(Dean dean) {
         for (int i = 0; i < dean.getMochila().getQuantidadeAtual() ;i++) {
                    if(dean.getMochila().retornaItem(i).getNomeItem().equals("Dente de lobisomem")){
                        denteLobo = true;
                    }
                    if(dean.getMochila().retornaItem(i).getNomeItem().equals("pena")){
                        penaAnjo = true;
                    }
                    if(dean.getMochila().retornaItem(i).getNomeItem().equals("Portador de almas")){
                        almas = true;
                    }
            }
         
         if(denteLobo == true && penaAnjo == true){ //significa que ele pode entrar
             if(almas == false && dean.getMarcaCaim() == false){
                 //fazer texto de que ambos os irmãos morreram
             }
             else if(almas == true && dean.getMarcaCaim() == false){
                 //fazer texto que apenas sam se salva, dean morre
             }
             else{
                 // dean mata o demonio, e salva seu irmao
             }
         }
         else{
             //fazer texto de que Dean fica aprisionado no inferno sem poder ajudar o irmão
         }
        
    }

}
