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
    
    private boolean itemFoiColetado;
    
    /**
     * Constroi um ambiente "Ceu" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    public AmbienteCeu(String nomeAmbiente)  {
        super(nomeAmbiente);
        itemFoiColetado = false;
    }
    
    /**
     * Sobrescreve a mensagem de entrada da classe pai de acordo com as informações
     * corretas do ambiente Céu e as retorna em uma String
     * @param dean
     * @return String
     */
    @Override
    public String mensagemDeEntrada(JogadorDean dean){
        
        if(getJaVisitada()== false){//texto a ser exibido caso o jogador entre pela primeira vez neste ambiente
            setJaVisitada(true);
            dean.getDiario().adicionarPagina("Deve-se entregar uma pena de anjo "
                    + "e um dente de lobo no portal do inferno.");
            dean.getDiario().adicionarPagina("Sam morrerá após 30 dias após a ligação");
            dean.getDiario().adicionarPagina("Você pode, mas não deve buscar as almas no Purgatório.");
            if(dean.getMochila().espacoDisponivel()){
                dean.getMochila().inserirItens(super.getItem());
                itemFoiColetado = true;
                return "Dean chega até o portal do céu. Reconhecidamente\n"
                    + "um amigo de Castiel, o mesmo foi chamado para se encontrar\n"
                    + "com o irmão Winchester. Após o encontro dos dois,Dean\n"
                    + "conta toda a história do acontecido para seu amigo. Castiel\n"
                    + "pede para Dean lhe mostrar a frase dita pela voz do outro\n"
                    + "lado da ligação. Instantaneamente, ele reconhece o idioma\n"
                    + "como Demvalium, a língua criada por Lúcifer : Derivada\n"
                    + "do Valirium, castiel consegue traduzir a mensagem facilmente\n"
                    + ": “Sam é um guerreiro nato, mas em trinta dias não resistirá\n"
                    + "sob as paredes do inferno. Pelo preço de dez almas o\n"
                    + "libertarei daqui.”. Castiel complementa : “Para salvar\n"
                    + "seu irmão, você terá de ir até o inferno. Entretanto,\n"
                    + "para conseguir passar pelo portal, você precisará entregar\n"
                    + "o mal e o bem. Como um anjo do senhor, posso lhe fornecer\n"
                    + "uma pena de minhas asas, que servirão como item de bem.\n"
                    + "Um dente de lobisomem será suficiente como item de mal.\n"
                    + "Mais uma coisa, você sabe que pode pegar as almas no\n"
                    + "Purgatório para o demônio,mas seria cometer um pecado\n"
                    + "ainda maior que deixar seu irmao morrer. Você deveria\n"
                    + "descobrir uma outra maneira de derrotá - lo. Infelizmente\n"
                    + "não posso ajudá-lo com mais nada .Tome cuidado”\n"
                    + "A pena foi colocada na sua mochila\n";
            }
            else{
                return "Dean chega até o portal do céu. Reconhecidamente\n"
                    + "um amigo de Castiel,o mesmo foi chamado para se encontrar\n"
                    + "com o irmão Winchester. Após o encontrodos dois,Dean\n"
                    + "conta toda a história do acontecido para seu amigo.Castiel\n"
                    + "pede para Dean lhe mostrar a frase dita pela voz do outro\n"
                    + "lado da ligação. Instantaneamente, ele reconhece o idioma\n"
                    + "como Demvalium, a língua criada por lúcifer : Derivada\n"
                    + "do Valirium, castiel consegue traduzir a mensagem facilmente\n"
                    + ": “Sam é um guerreiro nato, mas em trinta dias não resistirá\n"
                    + "sob as paredes do inferno. Pelo preço de dez almas o\n"
                    + "libertarei daqui.”.Castiel complementa : “Para salvar\n"
                    + "seu irmão, você terá de ir até o inferno.Entretanto,\n"
                    + "para conseguir passar pelo portal, você precisará entregar\n"
                    + "o mal e o bem.Como um anjo do senhor, posso lhe fornecer\n"
                    + "uma pena de minhas asas, que servirão como item de bem.\n"
                    + "Um dente de lobisomem será suficiente como item de mal\n."
                    + "Mais uma coisa, você sabe que pode pegar as almas no\n"
                    + "Purgatório para o demônio,mas seria cometer um pecado\n"
                    + "ainda maior que deixar seu irmao morrer. Você deveria\n"
                    + "descobrir uma outra maneira de derrotá - lo. Infelizmente\n"
                    + "não posso ajudá-lo com mais nada.Tome cuidado”\n"
                    + "Você nao possui espaço na mochila disponivel.\n"
                    + "Você deve liberar espaço para coletar o item.\n";
            }
        }
        else{ // texto a ser exibido caso o jogador já tenha vindo ao ambiente em questão
            if(itemFoiColetado == false){
                if(dean.getMochila().espacoDisponivel()){
                    dean.getMochila().inserirItens(super.getItem());
                    itemFoiColetado = true;
                    return "A pena foi colocada na sua mochila";
                }
                else{
                     return "Você nao possui espaço na mochila disponivel."
                            + "\n Você deve liberar espaço para coletar o item";
                }
            }
            else{
                return "Dean chega até o portal do céu. Reconhecidamente um amigo de Castiel,"
                        + "\no mesmo foi chamado para se encontrar com o irmão Winchester."
                        + "Castiel diz:\n“Me desculpe Dean, não há mais nada em que posso ajudar”.";
            }
        }
    }
}
