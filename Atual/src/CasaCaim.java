public class CasaCaim extends Ambiente {
    
    /**
     * Constroi um ambiente "Inferno" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    private boolean visitouBob;
    
    
    public CasaCaim(String nomeAmbiente)  {
        super(nomeAmbiente);
        visitouBob = false;
    }
    
    public void mensagemDeEntrada(Dean dean){
        if(getJaVisitada() == false){ // se o jogador nunca veio neste ambiente
            for (int i = 0; i < dean.getDiario().getTamanho(); i++) {
                if(dean.getDiario().getPagina(i).equals("Procurar caim para derrotar o demônio"));
                visitouBob = true;
            }
            
            if(visitouBob == true){
                //fazer o texto necessário para este ambiente falando sobre a missão que deve ser feita
            }
            else{
                // fazer o texto necessario dizendo que ele ainda nao sabe o que fazer neste ambiente
            }
        }
        else{ // se o jogador ja veio nesse ambiente
            for (int i = 0; i < dean.getDiario().getTamanho(); i++) {
                if(dean.getDiario().getPagina(i).equals("Procurar caim para derrotar o demônio"));
                visitouBob = true;
            }
            
            
        }
    }

}
