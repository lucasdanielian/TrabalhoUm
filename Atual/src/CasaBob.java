public class CasaBob extends Ambiente {
    
    /**
     * Constroi um ambiente "CasaBob" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    
    private Item carta;
    private boolean itemFoiColetado;
    
    public CasaBob(String nomeAmbiente)  {
        super(nomeAmbiente);
        carta = new Item("Carta de reconhecimento", "Carta de Bob para Caim, que"
                + " cobra um favor que caim devia a Bob");
    }
    
    @Override
    public void mensagemDeEntrada(Dean dean){
        
        if(getJaVisitada() == false){ //texto a ser exibido caso o jogador entre pela primeira vez neste ambiente
            System.out.println("Dean se direciona para Boulder, no estado do "
                    + "Colorado.\nLá mora Bob, melhor amigo de seu falecido pai,"
                    + " que se tornou como um pai para os\ngarotos. Chegando à "
                    + "casa de Bob, Dean explica toda a situação ocorrida para "
                    + "o mesmo.\nEm seguida, Bob diz : “Você não pode, de forma "
                    + "alguma, entregar as almas requeridas pelo\ndemônio, seria"
                    + " um desrespeito à memória de seu pai.. Acredito que você "
                    + "possa adquirir\npoderes suficientes para matar a criatura"
                    + " se procurar Caim, filho de adão e\neva. Não será fácil, "
                    + "porém ele poderá lhe conceder a “Marca de Caim”\nque lhe "
                    + "tornará forte o suficiente para lhe transformar em um "
                    + "Deus. Ele me deve alguns favores,\nleve esta carta contigo"
                    + " que ele poderá lhe ajudar” \n");
            setJaVisitada(true);
            dean.getDiario().adicionarPagina("Procurar caim para derrotar o demônio");
            if(dean.getMochila().espacoDisponivel()){
                dean.getMochila().inserir(carta);
                itemFoiColetado = true;
                System.out.println("Item : carta de reconhecimento foi colocado na mochila");
            }
            else{
                System.out.println("Voce nao possui espaco na mochila para guardar"
                        + " o item");
            }
        }
        else{ // texto a ser exibido caso o jogador já tenha vindo ao ambiente em questão
            if(itemFoiColetado == true){    
            System.out.println("Dean se direciona para Boulder, no estado do Colorado.\nLá mora Bob, melhor amigo de seu falecido pai, que se tornou como um pai para os\ngarotos. Chegando à casa de Bob, o mesmo diz a ele : “Infelizmente garoto, eu já não\nposso fazer mais nada por você”\n");
            }
            else{
                if(dean.getMochila().espacoDisponivel()){
                    dean.getMochila().inserir(carta);
                    itemFoiColetado = true;
                    System.out.println("Item : carta de reconhecimento foi colocado na mochila");
                }
                else{
                    System.out.println("Voce nao possui espaco na mochila para guardar"
                        + " o item");
                }
            }
        }
    }
}
