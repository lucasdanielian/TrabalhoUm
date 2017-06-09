public class CasaCaim extends Ambiente {
    
    /**
     * Constroi um ambiente "Inferno" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    private boolean visitouBob;
    private boolean recebeuTask;
    
    
    public CasaCaim(String nomeAmbiente)  {
        super(nomeAmbiente);
        visitouBob = false;
        recebeuTask = false;
    }
    
    @Override
    public void mensagemDeEntrada(Dean dean){
        if(getJaVisitada() == false){ // se o jogador nunca veio neste ambiente
            setJaVisitada(true);
            for (int i = 0; i < dean.getMochila().getQuantidadeAtual() ;i++) {
                    if(dean.getMochila().retornaItem(i).getNomeItem().equals("Carta de reconhecimento")){
                        visitouBob = true;
                    }
            }
            if(visitouBob == true){
                //fazer o texto necessário para este ambiente falando sobre a missão que deve ser feita
                recebeuTask = true;
            }
            else{
                // fazer o texto necessario dizendo que ele ainda nao sabe o que fazer neste ambiente
            }
        }
        else{ // se o jogador ja veio nesse ambiente
            if(recebeuTask == true && dean.getMarcaCaim() == false){
                for (int i = 0; i < dean.getMochila().getQuantidadeAtual() ;i++) {
                    if(dean.getMochila().retornaItem(i).getNomeItem().equals("Cabeca de Vampiro")){
                        // fazer mensagem de que ele cumpriu as tasks necessarias
                        dean.setMarcaCaim(true);
                        dean.getDiario().adicionarPagina("Você possui a marca de Caim");
                    }
                }
                if(dean.getMarcaCaim() == false){
                    //fazer mensagem de que ele ainda nao cumpriu a missão designada
                }
            }
            else if(recebeuTask == true && dean.getMarcaCaim() == true){
                //fazer mensagem de que nao há nada mais a ser feito neste ambiente
            }
            else{
                //fazer texto dizendo que dean ainda nao sabe o que fazer neste ambiente
            }
            
        }
    }

}
