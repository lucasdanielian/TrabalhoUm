public class CasaWinchester extends Ambiente {
    
    /**
     * Constroi um ambiente "Inferno" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    
    private ColecaoDeItens armario;
    
    public CasaWinchester(String nomeAmbiente)  {
        super(nomeAmbiente);//Inicializa o nome do ambiente na classe pai
        armario = new ColecaoDeItens(100);
    }

    @Override
    public void mensagemDeEntrada(Dean dean) {
        
        //mensagem da casa winchester
        
        
        
    }
    
    

}
