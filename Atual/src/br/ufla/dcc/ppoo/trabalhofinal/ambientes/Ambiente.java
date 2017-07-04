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
    private String nomeAmbiente;
    private boolean jaVisitada; // variavel que grava se este ambiente ja foi visitado pelo jogador
    private HashMap<String, Ambiente> saidas;
    private ColecaoDeItens armario;
    private Item item;
    /**
     * @param nomeAmbiente
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "um jardim aberto"
     */
    public Ambiente(String nomeAmbiente)  {
        this.nomeAmbiente = nomeAmbiente;
        jaVisitada = false;
        saidas = new HashMap<String, Ambiente>();
        item = null;
        armario = new ColecaoDeItens(100);
    }

    /**
     * Retorna um item do ambiente
     * @return Item
     */
    public Item getItem() {
        return item;
    }
    
    /**
     * @param item
     * Adiciona um item no ambiente
     */
    public void setItem(Item item) {
        this.item = item;
    }
    
    /**
     * remove o item e passa o item removido como retorno do metodo caso queira
     * adicionar em outra lista
     * @return Item
     */
    public Item removeItem(){
        Item aux = item;
        item = null;
        return aux;
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
     * @return o nome do ambiente.
     */
    public String getNomeAmbiente() {
        return nomeAmbiente;
    }
    
    /**
     * O metodo é abstrato e deve ser sobrescrito nas classes filhas
     * @param dean Jogador
     * passará as informações referentes as condições do jogador de acordo com 
     * cada ambiente, como itens na mochila no qual o jogador está deverá ser impresso
     * @return String
     *  Metodo que retornará as mensagens quando o jogador entra em um novo ambiente.
     */
    public abstract String mensagemDeEntrada(JogadorDean dean);
    
    /**
     * O metodo é abstrato e deve ser sobrescrito nas classes filhas
     * @return String
     *  Metodo que retornará uma string com a localização da imagem.
     */
    public abstract String imagemDoAmbiente();
    
    /**
     * Metodo que retorna o item do Ambiente
     * @return String 
     */
    public String itensAmbiente() {
       if (item == null){
           return "Nao item neste ambiente";
       }else{
           return "Item: " + item.getNomeItem();
       }
    }
    
    /**
     * Metodo que retorna se um ambiente já foi visitado ou nao.
     * @return Boleano
     */
    public boolean getJaVisitada() {
        return jaVisitada;
    }

    public void setJaVisitada(boolean jaVisitada) {
        this.jaVisitada = jaVisitada;
    }
    
    /**
     * @param direcao
     * Recebe a devida direcao
     * @return Ambiente
     * Retorna a saida com a devida direcao
     */
    public Ambiente getAmbiente(String direcao){
        return saidas.get(direcao);
    }
    
    /**
     * 
     * @return String
     */
    public String getSaidas(){
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
        return "\n Objetos no armario: " + armario.exibirItens() + "\n";
    }
    
    /**
     * Insere o item passado como parametro no Armario
     * @param item 
     */
    private void insereObjetosArmario(Item item){
        armario.inserirItens(item);
    }
    
    /**
     * 
     * @param item
     * @return Item
     */
    private Item removerObjetosArmario(Item item){
        armario.removerPeloNome(item.getNomeItem());
        return item;
    }
    
    /**
     * Retoran uma lista dos itens contido no armario
     * @return ColecaoDeItens
     */
    public ColecaoDeItens getArmario(){
        return armario;
    }
}
