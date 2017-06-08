public class Ceu extends Ambiente {
    // teste valdeci-dev3
    private Item pena;
    
    /**
     * Constroi um ambiente "Ceu" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    public Ceu(String nomeAmbiente)  {
        super(nomeAmbiente);
        pena = new Item("pena", "pena do anjo Castiel");
    }
    
    @Override
    public void mensagemDeEntrada(Dean dean){
        if(getJaVisitada()== false){//texto a ser exibido caso o jogador entre pela primeira vez neste ambiente
            System.out.println("Dean chega até o portal do céu. Reconhecidamente um amigo de Castiel,\no mesmo foi chamado para se encontrar com o irmão Winchester. Após o encontro\ndos dois,Dean conta toda a história do acontecido para seu amigo.\nCastiel pede para Dean lhe mostrar a frase dita pela voz do outro\nlado da ligação. Instantaneamente, ele reconhece o idioma como Demvalium, a língua\ncriada por lúcifer : Derivada do Valirium, castiel consegue traduzir a\nmensagem facilmente : “Sam é um guerreiro nato, mas em trinta dias não resistirá sob as\nparedes do inferno. Pelo preço de dez almas o libertarei daqui.”.\nCastiel complementa : “Para salvar seu irmão, você terá de ir até o inferno.\nEntretanto, para conseguir passar pelo portal, você precisará entregar o mal e o bem.\nComo um anjo do senhor, posso lhe fornecer uma pena de minhas asas, que servirão\ncomo item de bem. Mais uma coisa, você sabe que não pode levar almas para o\ndemônio, seria cometer um pecado ainda maior que deixar seu irmao morrer.\nVocê deverá descobrir uma outra maneira de derrotá - lo. Infelizmente não posso ajudá-lo com mais nada\n.Tome cuidado”\n");
            setJaVisitada(true);
            dean.getDiario().adicionarPagina("Deve-se entregar o bem e o mal no portal do inferno. A pena servirá como item de bem");
            dean.getDiario().adicionarPagina("Sam morrerá após 30 dias após a ligação");
            if(dean.getMochila().espacoDisponivel()){
                dean.getMochila().inserir(pena);
            }
            else{
                System.out.println("Você nao possui espaço na mochila disponivel");
            }
        }
        else{ // texto a ser exibido caso o jogador já tenha vindo ao ambiente em questão
            System.out.println("Dean chega até o portal do céu. Reconhecidamente um amigo de Castiel,\no mesmo foi chamado para se encontrar com o irmão Winchester. Castiel diz: “Me\ndesculpe Dean, não há mais nada em que posso ajudar”\n");
        }
    }

}
