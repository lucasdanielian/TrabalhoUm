package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes;


import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.ColecaoDeItens;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.JogadorDean;
import java.io.Serializable;
import java.util.HashMap;

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
    private String nomeAmbiente;
    private boolean jaVisitada; // variavel que grava se este ambiente ja foi visitado pelo jogador
    private HashMap<String, Ambiente> saidas;
    
    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "um jardim aberto"
     * @param nomeAmbiente esperada uma string contendo o nome do ambiente
     */
    public Ambiente(String nomeAmbiente)  {
        this.nomeAmbiente = nomeAmbiente;
        jaVisitada = false;
        saidas = new HashMap<String, Ambiente>();
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
     * Metodo para verificar se um ambiente possui um item disponivel ou nao
     * @param dean Jogador e passado para verificacao se e possivel adicionar o
     * item na mochila ou nao
     * @return String contendo disponivel ou indisponivel de acordo com a
     * disponibilidade do item
     */
    public abstract String disponibilizarItemAmbiente(JogadorDean dean);
    
    /**
     * Metodo que pega um Item do ambiente
     * @param dean Jogador e passado para verificacao se e possivel adicionar o
     * item na mochila ou nao
     * @return String para verificaco se pegou o item ou nao
     */
    public abstract String pegarItemAmbiente(JogadorDean dean);
    
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
}
