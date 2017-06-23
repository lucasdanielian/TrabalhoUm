package br.ufla.dcc.ppoo.trabalhodois.regradenegocio;

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
 * Versao Winchester feita por Lucas Danielian e Valdeci como atividade academica
 * @version 2011.07.31 (2017.05.16)
 */
public class AmbienteDenver extends Ambiente {
    
    private boolean itemFoiColetado;
    private boolean foiCeu;
    
    /**
     * @param nomeAmbiente 
     * Constroi um ambiente "AmbienteDenver" passando seu nome por parametro
     */
    public AmbienteDenver(String nomeAmbiente)  {
        super(nomeAmbiente);
        itemFoiColetado = false;
        foiCeu = false;
    }
    
    /**
     * @param dean
     * Passa o jogador como parametro para interação com o ambiente
     * @return String
     * Sobrescreve a mensagem de entrada da classe pai "Ambiente"
     * passando as informações referentes ao ambiente Denver e as retorna em
     * uma String
     */
    @Override
    public String mensagemDeEntrada(JogadorDean dean){
        if(getJaVisitada() == false){//se o jogador nunca visitou este ambiente
            for (int i = 0; i < dean.getDiario().getTamanho(); i++) {
                if(dean.getDiario().getPagina(i).equals("Deve-se entregar uma pena de anjo e um dente de lobo no portal do inferno.")){
                    foiCeu = true;
                }
            }
            if(foiCeu == true){
                setJaVisitada(true);
                if(dean.getMochila().espacoDisponivel()){ // se ha espaco disponivel para armazenar o item
                    dean.getMochila().inserirItens(super.getItem());
                    itemFoiColetado = true;
                    return "Dean se direciona para a cidade de Denver, no\n"
                        + "estado do Colorado.Há informações de que uma alcateia\n"
                        + "de lobisomens vêm atacando os moradores.Após certa\n"
                        + "investigação, Dean descobre onde estes lobos estão, invade\n"
                        + "seu esconderijo,e mata todos, um a um.\n"
                        + "O item 'Dente' foi colocado na mochila\n";
                }
                else{
                    return "Dean se direciona para a cidade de Denver, no\n"
                        + "estado do Colorado.Há informações de que uma alcateia\n"
                        + "de lobisomens vêm atacando os moradores.Após certa\n"
                        + "investigação, Dean descobre onde estes lobos estão, invade\n"
                        + "seu esconderijo,e mata todos, um a um.\n"
                        + "Você não pode coletar o dente de lobo,pois\n"
                        + "nao há espaço disponível na sua mochila\n";
                }
            }
            else{
                return "Dean se direciona para a cidade de Denver,"
                        + " mas não sabe o que fazer aqui.";
            }
        }
        else{ // caso ele ja tenha vindo no ambiente
            if(itemFoiColetado == false){
                if(dean.getMochila().espacoDisponivel()){
                    dean.getMochila().inserirItens(super.getItem());
                    itemFoiColetado = true;
                    return "O item 'Dente' foi adicionado na mochila";
                }
                else{
                    return "Você não pode coletar o dente de lobo,\n"
                            + "pois nao há espaço disponível na sua mochila";
                }
            }
            else{ // caso ele ja tenha realizado todas as açoes possiveis neste ambiente
                return "Dean retorna à cidade de Denver, entretanto,ele não sabe o que fazer.\n"
                        + "Não há mais ações nesta cidade. Você está perdendo tempo aqui!";
            }
        }
    }
}
