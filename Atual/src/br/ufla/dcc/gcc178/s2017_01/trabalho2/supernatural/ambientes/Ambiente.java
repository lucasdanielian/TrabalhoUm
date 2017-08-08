package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes;


import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.ColecaoDeItens;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.Jogador;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
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
public abstract class Ambiente implements Serializable{
    //Atributos
    private static final long serialVersionUID = 1L;
    private String nomeAmbiente;
    private boolean jaVisitada; // variavel que grava se este ambiente ja foi visitado pelo jogador
    private HashMap<String, Ambiente> saidas;
    private ColecaoDeItens itens;
    private final int MAX_ITENS = 1000;
    
    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "um jardim aberto"
     * @param nomeAmbiente esperada uma string contendo o nome do ambiente
     * @param qtdItens espera um inteiro com a quantidade de itens do ambiente.
     */
    public Ambiente(String nomeAmbiente)  {
        this.nomeAmbiente = nomeAmbiente;
        jaVisitada = false;
        saidas = new HashMap<String, Ambiente>();
        itens = new ColecaoDeItens(MAX_ITENS);
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
    public abstract String mensagemDeEntrada(Jogador dean);
    
    /**
     * Metodo utilizado para verificar se um ambiente possui itens disponiveis
     * para captura
     * @param dean Jogador passado para verificacao da mochila caso tenha itens no
     * ambiente
     * @return String informando que nao é possivel coletar itens neste ambiente
     */
    public String disponibilizarItemAmbiente(Jogador dean) {
        
        return itens.retornaDescricaoTodosItens();
    }

    /**
     * Remove um objeto contido no armario.
     * @param nome criterio de remoção
     * @return Item removido é retornado para tratamento
     */
    public Item pegarItemAmbiente(String nome) {
        return itens.removerPeloNome(nome);
    }

    /**
     * Insere um item no armario 
     * @param item o item passado por referencia é colocado no armario
     * @return boolean se inserido retorna true e se não inserido retorna false
     */
    public boolean inserirItensAmbiente(Item item) {
        return itens.inserirItens(item);
    }
    
    /**
     * Metodo que retorna o item de um ambinete
     * @return Item 
     */
    public List<Item> getItens() {
        return itens.getItens();
    }
    
    /**
     *  Metodo que retornará uma string com a localização da imagem.
     * O metodo é abstrato e deve ser sobrescrito nas classes filhas
     * @return String
     */
    public abstract ImageIcon imagemDoAmbiente();
    
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
}
