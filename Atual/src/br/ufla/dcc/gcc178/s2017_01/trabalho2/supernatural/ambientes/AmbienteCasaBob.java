package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.JogadorDean;

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
 * Versao Winchester feita por Lucas Danielian e Valdeci como atividade academica
 * @version 2011.07.31 (2017.05.16)
 */
public class AmbienteCasaBob extends Ambiente {
    private boolean itemFoiColetado;
    private boolean foiCeu;
    private Item carta;
    
    /**
     * Constroi um ambiente CasaBob 
     * @param nomeAmbiente passando seu nome por parametro
     */
    public AmbienteCasaBob(String nomeAmbiente)  {
        super(nomeAmbiente);
        foiCeu = false;
        carta = new Item("Carta", "Carta de Bob para Caim, que"
                + "cedida pelo mesmo para ajudar Dean.");
    }
    
    /**
     * 
     * @param dean
     * Passa o jogador como parametro
     * @return String
     * Retornará uma String que devera exibir a mensagem de entrada referente
     * a casa do Bob
     */
    @Override
    public String mensagemDeEntrada(JogadorDean dean){
        //texto a ser exibido caso o jogador entre pela primeira vez neste ambiente
        String mensagemEntrada1 = "\nDean se direciona para Boulder, no estado do\n"
            + "Colorado.Lá mora Bob, melhor amigo de seu falecido pai,\n"
            + "que se tornou como um pai para os garotos.\n";
        String mensagemEntrada2 = "\nChegando à\n"
            + "casa de Bob, Dean explica toda a situação ocorrida para\n"
            + "o mesmo.Em seguida, Bob diz : “Você não pode, de forma\n"
            + "alguma, entregar as almas requeridas pelo demônio, seri\n"
            + "um desrespeito à memória de seu pai.. Acredito que você\n"
            + "possa adquirir poderes suficientes para matar a criatura\n"
            + "se procurar Caim, filho de adão e eva. Não será fácil,\n"
            + "porém ele poderá lhe conceder a “Marca de Caim” que lhe\n"
            + "tornará forte o suficiente para lhe transformar em um\n"
            + "Deus. Ele me deve alguns favores,leve esta carta contigo\n"
            + "que ele poderá lhe ajudar”\n";
        if(getJaVisitada() == false){
            
            for (int i = 0; i < dean.tamanhoDiario(); i++) {
                
                if(dean.lerPaginasDiario().indexOf("Você pode, mas não deve "
                        + "buscar as almas no Purgatório.")>=0){
                    
                    foiCeu = true;
                }
            }
            if(foiCeu == true){
                setJaVisitada(true);
                dean.adicionarPaginaDiario("Procurar caim para derrotar o demônio");
                if(dean.espacoDisponivelMochila()){
                    return mensagemEntrada1 + mensagemEntrada2 + "O item carta "
                            + "agora esta disponivel para ser coletado";
                }
                else{
                    return mensagemEntrada1 + mensagemEntrada2 + "O item carta "
                            + "nao esta disponivel para ser coletado pois na ha"
                            + " espaco suficiente na mochila";
                }
            }
            else{
                return mensagemEntrada1 + "Entretanto,\n por mais que Bob queira ajudar,"
                        + "Dean não possui informações\n suficientes para que possa ser ajudado\n";
            }
        }
        else{ // texto a ser exibido caso o jogador já tenha vindo ao ambiente em questão
            if(itemFoiColetado == true){    
            return mensagemEntrada1 + "Chegando à casa de Bob, o mesmo diz a ele : “Infelizmente"
                    + "garoto, eu já não posso\n fazer mais nada por você”\n";
            }
            else{
                if(dean.espacoDisponivelMochila()){
                    return mensagemEntrada1 + "O item carta "
                            + "agora esta disponivel para ser coletado";
                }
                else{
                    return mensagemEntrada1 + "O item carta "
                            + "nao esta disponivel para ser coletado pois na ha"
                            + " espaco suficiente na mochila";
                }
            }
        }
    }

    /**
     * Metodo que seta a imagem do ambiente
     * @return String com o endereco da imagem
     */
    @Override
    public String imagemDoAmbiente() {
        return "/br/ufla/dcc/gcc178/s2017_01/trabalho2/supernatural/imagens/casaBob.jpg";
    }
    
    /**
     * Metodo que verifica se um item do ambiente está disponivel ou não
     * @param dean Jogador passado para verificacao da mochila caso tenha itens no
     * ambiente
     * @return uma string para verificacao de adicao
     */
    @Override
    public String disponibilizarItemAmbiente(JogadorDean dean){
        if(getJaVisitada() == false){ //perguntar valdeci se nao tem que colocar a verificacao da mochila aqui
            if(foiCeu == true){
                return "Carta disponivel";
            }
            else{
                return "indisponivel";
            }
        }
        else{ // texto a ser exibido caso o jogador já tenha vindo ao ambiente em questão
            if(itemFoiColetado == false && foiCeu == true){    
                return "Carta disponivel";
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
        if(nome.equals(carta.getNomeItem()) && itemFoiColetado == false){
            itemFoiColetado = true;
            return carta;
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
        if(item.getNomeItem().equals("Carta")){
            itemFoiColetado = false;
            return true;
        }else{
            return false;
        }
    }
}
