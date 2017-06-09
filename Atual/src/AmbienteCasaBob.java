/**
 * Classe AmbienteCasaBob - um ambiente em um jogo adventure.
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
public class AmbienteCasaBob extends Ambiente {
    private Item carta;
    private boolean itemFoiColetado;
    
    /**
     * Constroi um ambiente "AmbienteCasaBob" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    public AmbienteCasaBob(String nomeAmbiente)  {
        super(nomeAmbiente);
        carta = new Item("Carta de reconhecimento", "Carta de Bob para Caim, que"
                + " cobra um favor que caim devia a Bob");
    }
    
    /**
     * Exibe a mensagem de entrada referente a casa do Bob
     * @param dean 
     */
    @Override
    public void mensagemDeEntrada(JogadorDean dean){
        System.out.println("Itens da CasaBob: Carta");
        //texto a ser exibido caso o jogador entre pela primeira vez neste ambiente
        if(getJaVisitada() == false){ 
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
            dean.getMochila().inserirItens(carta);
            if(dean.getMochila().espacoDisponivel()){
                dean.getMochila().inserirItens(carta);
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
            System.out.println("Dean se direciona para Boulder, no estado do Colorado.\nLá mora Bob, "
                    + "melhor amigo de seu falecido pai, que se tornou como um pai para os\ngarotos. "
                    + "Chegando à casa de Bob, o mesmo diz a ele : “Infelizmente garoto, eu já não\nposso"
                    + " fazer mais nada por você”\n");
            }
            else{
                if(dean.getMochila().espacoDisponivel()){
                    dean.getMochila().inserirItens(carta);
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
