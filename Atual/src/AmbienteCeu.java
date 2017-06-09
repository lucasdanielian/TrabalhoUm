/**
 * Classe AmbienteCasaCaim - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como denver,houston,casaCaim,casaBob,inferno,purgatorio,
 * casaWinchester e ceu, que são outras saídas. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2017.05.16)
 * @editor Versao Winchester feita por Lucas Danielian e Valdeci como atividade academica
 */
public class AmbienteCeu extends Ambiente {
    
    private Item pena;
    private boolean itemFoiColetado;
    
    /**
     * Constroi um ambiente "Ceu" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    public AmbienteCeu(String nomeAmbiente)  {
        super(nomeAmbiente);
        pena = new Item("pena", "pena do anjo Castiel");
        itemFoiColetado = false;
    }
    
    /**
     * Sobrescreve a mensagem de entrada da classe pai de acordo com as informações
     * corretas do ambiente Céu
     * @param dean 
     */
    @Override
    public void mensagemDeEntrada(JogadorDean dean){
        if(getJaVisitada()== false){//texto a ser exibido caso o jogador entre pela primeira vez neste ambiente
            System.out.println("Dean chega até o portal do céu. Reconhecidamente um amigo de Castiel,\no mesmo foi chamado para se encontrar com o irmão Winchester. Após o encontro\ndos dois,Dean conta toda a história do acontecido para seu amigo.\nCastiel pede para Dean lhe mostrar a frase dita pela voz do outro\nlado da ligação. Instantaneamente, ele reconhece o idioma como Demvalium, a língua\ncriada por lúcifer : Derivada do Valirium, castiel consegue traduzir a\nmensagem facilmente : “Sam é um guerreiro nato, mas em trinta dias não resistirá sob as\nparedes do inferno. Pelo preço de dez almas o libertarei daqui.”.\nCastiel complementa : “Para salvar seu irmão, você terá de ir até o inferno.\nEntretanto, para conseguir passar pelo portal, você precisará entregar o mal e o bem.\nComo um anjo do senhor, posso lhe fornecer uma pena de minhas asas, que servirão\ncomo item de bem. Um dente de lobisomem será suficiente como item de mal\n. Mais uma coisa, você sabe que pode pegar as almas no Purgatório para o\ndemônio,mas seria cometer um pecado ainda maior que deixar seu irmao morrer.\nVocê deveria descobrir uma outra maneira de derrotá - lo. Infelizmente não posso ajudá-lo com mais nada\n.Tome cuidado”\n");
            setJaVisitada(true);
            dean.getDiario().adicionarPagina("Deve-se entregar uma pena de anjo e um dente de lobo no portal do inferno.");
            dean.getDiario().adicionarPagina("Sam morrerá após 30 dias após a ligação");
            dean.getDiario().adicionarPagina("Você pode, mas não deve buscar as almas no Purgatório.");
            if(dean.getMochila().espacoDisponivel()){
                System.out.println("A pena foi colocada na sua mochila");
                dean.getMochila().inserir(pena);
                itemFoiColetado = true;
            }
            else{
                System.out.println("Você nao possui espaço na mochila disponivel.\n Você deve liberar espaço para coletar o item.");
            }
        }
        else{ // texto a ser exibido caso o jogador já tenha vindo ao ambiente em questão
            if(itemFoiColetado == false){
                if(dean.getMochila().espacoDisponivel()){
                    System.out.println("A pena foi colocada na sua mochila");
                    dean.getMochila().inserir(pena);
                    itemFoiColetado = true;
                }
                else{
                     System.out.println("Você nao possui espaço na mochila disponivel.\n Você deve liberar espaço para coletar o item");
                }
            }
            else{
                System.out.println("Dean chega até o portal do céu. Reconhecidamente um amigo de Castiel,\no mesmo foi chamado para se encontrar com o irmão Winchester. Castiel diz:\n“Me desculpe Dean, não há mais nada em que posso ajudar”.");
            }
        }
    }

}
