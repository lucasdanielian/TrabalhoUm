package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.Jogador;

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
    private Item item;
    
    /**
     * Constroi um ambiente "AmbienteDenver" 
     * @param nomeAmbiente  passando seu nome por parametro
     */
    public AmbienteDenver(String nomeAmbiente)  {
        super(nomeAmbiente);
        itemFoiColetado = false;
        foiCeu = false;
        item = new Item("Dente","O dente de lobisomem representa o mal do mundo");
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
    public String mensagemDeEntrada(Jogador dean){
        //Mensgem de entrada do ambiente
        String texto1 = "Dean se direciona para a cidade de Denver, no\n"
                        + "estado do Colorado.Há informações de que uma alcateia\n"
                        + "de lobisomens vêm atacando os moradores.Após certa\n"
                        + "investigação, Dean descobre onde estes lobos estão, invade\n"
                        + "seu esconderijo,e mata todos, um a um.\n";
        
        //se o jogador nunca visitou este ambiente
        if(getJaVisitada() == false){
            for (int i = 0; i < dean.tamanhoDiario(); i++) {
                if(dean.lerPaginasDiario().indexOf("Deve-se entregar uma pena de"
                        + " anjo e um dente de lobo no portal do inferno.")>=0){
                    foiCeu = true;
                }
            }
            if(foiCeu == true){
                setJaVisitada(true);
                
                // se ha espaco disponivel para armazenar o item
                if(dean.espacoDisponivelMochila()){ 
                    return texto1 + "O item 'Dente' agora esta disponivel para ser coletado\n";
                }
                else{
                    return texto1 + "O item 'Dente' nao esta disponivel para ser coletado"
                            + "pois nao ha espaco na mochila\n";
                }
            }
            else{
                return "Dean se direciona para a cidade de Denver,"
                        + " mas não sabe o que fazer aqui.\n";
            }
        }
        else{ // caso ele ja tenha vindo no ambiente
            if(itemFoiColetado == false){
                if(dean.espacoDisponivelMochila()){
                    return "O item 'Dente' agora esta disponivel para ser coletado\n";
                }
                else{
                    return "O item 'Dente' nao esta disponivel para ser coletado"
                            + "pois nao ha espaco na mochila\n";
                }
            }
            else{ // caso ele ja tenha realizado todas as açoes possiveis neste ambiente
                return "Dean retorna à cidade de Denver, entretanto,ele não sabe o que fazer.\n"
                        + "Não há mais ações nesta cidade. Você está perdendo tempo aqui!\n";
            }
        }
    }
    
    /**
     * 
     * @return String
     * retorna uma String com o endereco da imagem
     */
    @Override
    public String imagemDoAmbiente() {
        return "/br/ufla/dcc/gcc178/s2017_01/trabalho2/supernatural/imagens/Denver.jpg";
    }

    /**
     * Metodo que verifica se um item do ambiente está disponivel ou não
     * @param dean Jogador passado para verificacao da mochila caso tenha itens no
     * ambiente
     * @return uma string para verificacao de adicao
     */
    @Override
    public String disponibilizarItemAmbiente(Jogador dean){
        if(getJaVisitada() == false){
            if(foiCeu == true){ 
                return "Dente disponivel";
            }
            else{
                return "indisponivel";
            }
        }
        else{ // texto a ser exibido caso o jogador já tenha vindo ao ambiente em questão
            if(itemFoiColetado == false && foiCeu == true){    
                return "Dente disponivel";
            }
            else{
                return "indisponivel";
            }
        } 
    }
    
    /**
     * Remove um objeto contido no armario.
     * @param nome criterio de remoção
     * @return Item removido é retornado para tratamento
     */
    @Override
    public Item pegarItemAmbiente(String nome) {
        if(nome.equals(item.getNomeItem()) && itemFoiColetado == false){
            itemFoiColetado = true;
            return item;
        }else{
            return null;
        }
    }

    /**
     * Insere o item passado no ambiente
     * @param item objeto do tipo Item
     * @return true caso o item foi inserido e false caso ao contrario
     */
    @Override
    public boolean inserirItensAmbiente(Item item) {
        if(item.getNomeItem().equals("Dente")){
            itemFoiColetado = false;
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Metodo que retorna o item de um ambinete
     * @return Item 
     */
    @Override
    public Item getItem() {
        return item;
    }
}
