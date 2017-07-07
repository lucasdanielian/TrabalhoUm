package br.ufla.dcc.ppoo.trabalhofinal.ambientes;


import br.ufla.dcc.ppoo.trabalhofinal.itens.ColecaoDeItens;
import br.ufla.dcc.ppoo.trabalhofinal.itens.Item;
import br.ufla.dcc.ppoo.trabalhofinal.jogador.JogadorDean;
import java.util.HashMap;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
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
public abstract class Ambiente  {
    //Atributos
    private String nomeAmbiente;
    private boolean jaVisitada; // variavel que grava se este ambiente ja foi visitado pelo jogador
    private HashMap<String, Ambiente> saidas;
    private ColecaoDeItens armario;
    
    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "um jardim aberto"
     * @param nomeAmbiente
     */
    public Ambiente(String nomeAmbiente)  {
        this.nomeAmbiente = nomeAmbiente;
        jaVisitada = false;
        saidas = new HashMap<String, Ambiente>();
        armario = new ColecaoDeItens(100);
    }
    
    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param ambiente é o ambiente disponivel para este local.
     */
    public void ajustarSaidas( Ambiente ambiente)  {
        saidas.put(ambiente.getNomeAmbiente(), ambiente);
    }

    /**
     * Metodo utilizado para saber o nome de um ambiente
     * @return uma string contendo o nome do ambiente.
     */
    public String getNomeAmbiente() {
        return nomeAmbiente;
    }
    
    /**
     * O metodo é abstrato e deve ser sobrescrito nas classes filhas
     * passará as informações referentes as condições do jogador de acordo com 
     * cada ambiente, como itens na mochila no qual o jogador está deverá ser impresso
     * @param dean Jogador
     * @return String com as mensagens quando o jogador entra em um novo ambiente.
     */
    public abstract String mensagemDeEntrada(JogadorDean dean);
    
    /**
     *  Metodo que retornará uma string com a localização da imagem.
     * O metodo é abstrato e deve ser sobrescrito nas classes filhas
     * @return String
     */
    public abstract String imagemDoAmbiente();
    
    /**
     * Metodo que retorna se um ambiente já foi visitado ou nao.
     * @return Boleano com true se já visitado
     */
    public boolean getJaVisitada() {
        return jaVisitada;
    }

    /**
     * Altera o status de visita do ambiente
     * @param jaVisitada recebe true quando já visitada e false para os demais casos
     */
    public void setJaVisitada(boolean jaVisitada) {
        this.jaVisitada = jaVisitada;
    }
    
    /**
     * Recebe a devida direcao
     * Retorna a saida com a devida direcao
     * @param direcao recebe a nova direção por string
     * @return Ambiente de acordo com a direção desejada
     */
    public Ambiente irProximoAmbiente(String direcao){
        return saidas.get(direcao);
    }
    
    /**
     * Metodo utilizado para saber as saidas
     * @return String contendo as saidas disponíveis no ambiente
     */
    public String saidasValidas(){
        String textoSaidas = "";
        for (String direcao : saidas.keySet()){
            textoSaidas = textoSaidas + direcao + " ";
        }
        return textoSaidas;
    }
    
    /**
     * Retorna uma string contendo a coleçao de objetos guardados no armario
     * localizado na CasaWinchester
     * @return String
     */
    private String imprimeObjetosArmario(){
        return "\n Objetos no armario: " + armario.retornaItens() + "\n";
    }
    
    /**
     * Insere o item passado como parametro no Armario
     * @param item que deseja inserir no armario
     */
    private void insereObjetosArmario(Item item){
        armario.inserirItens(item);
    }
    
    /**
     * Metodo utilizado quando deseja-se remover um item do armario
     * @param item a ser removido
     * @return Item removido para devido tratamento
     */
    private Item removerObjetosArmario(Item item){
        armario.removerPeloNome(item.getNomeItem());
        return item;
    }
    
    /**
     * Metodo que retorna os itens contidos no armario
     * @return String contendo os itens
     */
    public String retornaItensDoArmario(){
        return armario.retornaItens();
    }
    
    /**
     * Remove um objeto contido no armario.
     * @param nome criterio de remoção
     * @return Item removido é retornado para tratamento
     */
    public Item removerPeloNomeNoArmario(String nome){
        return armario.removerPeloNome(nome);
    }
    
    /**
     * Insere um item no armario
     * @param item o item passado por referencia é colocado no armario
     */
    public void inserirItensArmario(Item item){
        armario.inserirItens(item);
    }
}
