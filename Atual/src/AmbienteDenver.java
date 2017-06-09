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
public class AmbienteDenver extends Ambiente {
    
    private Item denteLobo;
    private boolean itemFoiColetado;
    private boolean foiCeu;
    
    /**
     * Constroi um ambiente "AmbienteDenver" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    public AmbienteDenver(String nomeAmbiente)  {
        super(nomeAmbiente);
        denteLobo = new Item("Dente de lobisomem","O dente de lobisomem representa o mal do mundo");
        itemFoiColetado = false;
        foiCeu = false;
    }
    
    /**
     * Sobrescreve a mensagem de entrada da classe pai "Ambiente"
     * passando as informações referentes ao ambiente Denver
     * @param dean 
     */
    @Override
    public void mensagemDeEntrada(JogadorDean dean){
        System.out.println("Objeto deste local: Dente de lobisomem");
        if(getJaVisitada() == false){//se o jogador nunca visitou este ambiente
            for (int i = 0; i < dean.getDiario().getTamanho(); i++) {
                if(dean.getDiario().getPagina(i).equals("Deve-se entregar uma pena de anjo e um dente de lobo no portal do inferno.")){
                    foiCeu = true;
                }
            }
            
            if(foiCeu == true){
                System.out.println("Dean se direciona para a cidade de Denver, no "
                        + "estado do Colorado.\nHá informações de que uma alcateia "
                        + "de lobisomens vêm atacando os moradores.\nApós certa "
                        + "investigação, Dean descobre onde estes lobos estão, invade"
                        + " seu esconderijo,\ne mata todos, um a um.");
                setJaVisitada(true);
                if(dean.getMochila().espacoDisponivel()){ // se ha espaco disponivel para armazenar o item
                    dean.getMochila().inserirItens(denteLobo);
                    itemFoiColetado = true;
                    System.out.println("Um dente de lobo foi coletado");
                }
                else{
                    System.out.println("Você não pode coletar o dente de lobo,\npois"
                            + " nao há espaço disponível na sua mochila");
                }
            }
            else{
                System.out.println("Dean se direciona para a cidade de Denver,"
                        + " mas não sabe o que fazer aqui.");
            }
        }
        else{ // caso ele ja tenha vindo no ambiente
            if(itemFoiColetado == false){
                if(dean.getMochila().espacoDisponivel()){
                    dean.getMochila().inserirItens(denteLobo);
                    itemFoiColetado = true;
                    System.out.println("Um dente de lobo foi coletado");
                }
                else{
                    System.out.println("Você não pode coletar o dente de lobo,\npois nao há espaço disponível na sua mochila");
                }
            }
            else{ // caso ele ja tenha realizado todas as açoes possiveis neste ambiente
                System.out.println("Dean retorna à cidade de Denver, entretanto, ele não sabe o que fazer.\nNão há mais ações nesta cidade. Você está perdendo tempo aqui!");
            }
        }
    }
    
    
}
