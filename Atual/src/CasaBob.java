public class CasaBob extends Ambiente {
    
    /**
     * Constroi um ambiente "CasaBob" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    public CasaBob(String nomeAmbiente)  {
        super(nomeAmbiente);
    }
    
    public void mensagemDeEntrada(){
        
        if(getJaVisitada() == false){ //texto a ser exibido caso o jogador entre pela primeira vez neste ambiente
            System.out.println("Dean se direciona para Boulder, no estado do Colorado.\nLá mora Bob, melhor amigo de seu falecido pai, que se tornou como um pai para os\ngarotos. Chegando à casa de Bob, Dean explica toda a situação ocorrida para o mesmo.\nEm seguida, Bob diz : “Você não pode, de forma alguma, entregar as almas requeridas pelo\ndemônio, seria um desrespeito à memória de seu pai.. Acredito que você possa adquirir\npoderes suficientes para matar a criatura se procurar Caim, filho de adão e\neva. Não será fácil, porém ele poderá lhe conceder a “Marca de Caim”\nque lhe tornará forte o suficiente para lhe transformar em um Deus.” \n");
            setJaVisitada(true);
        }
        else{ // texto a ser exibido caso o jogador já tenha vindo ao ambiente em questão
            System.out.println("Dean se direciona para Boulder, no estado do Colorado.\nLá mora Bob, melhor amigo de seu falecido pai, que se tornou como um pai para os\ngarotos. Chegando à casa de Bob, o mesmo diz a ele : “Infelizmente garoto, eu já não\nposso fazer mais nada por você”\n");
        }
    }

}
