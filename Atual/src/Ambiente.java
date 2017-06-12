
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
 * @version 2011.07.31 (2017.05.16)
 * @editor Versao Winchester feita por Lucas Danielian e Valdeci como atividade academica
 */
public abstract class Ambiente  {
    private String nomeAmbiente;
    private boolean jaVisitada; // variavel que grava se este ambiente ja foi visitado pelo jogador
    private HashMap<String, Ambiente> saidas;
    Item item;
    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "um jardim aberto".
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String nomeAmbiente)  {
        this.nomeAmbiente = nomeAmbiente;
        jaVisitada = false;
        saidas = new HashMap<String, Ambiente>();
        item = null;
    }

    /**
     * Retorna um item do ambiente
     * @return 
     */
    public Item getItem() {
        return item;
    }
    
    /**
     * Adiciona um item no ambiente
     * @param item 
     */
    public void setItem(Item item) {
        this.item = item;
    }
    
    /**
     * remove o item e passa o item removido como retorno do metodo caso queira
     * adicionar em outra lista
     * @return 
     */
    public Item removeItem(){
        Item aux = item;
        item = null;
        return aux;
    }
    
    /**
     * De acordo com a parametro recebido, onde cada campo representa um ambiente
     * logo se este valor vier como nulo, a saida estará bloqueada e não terá mais 
     * volta no caso do ambiente inferno, pois o mesmo não possui mais saída.
     * @param denver
     * @param houston
     * @param casaCaim 
     * @param casaBob
     * @param inferno
     * @param purgatorio
     * @param casaWinchester 
     */
    public void ajustarSaidas(Ambiente denver, Ambiente houston, Ambiente casaCaim, Ambiente casaBob, Ambiente inferno, Ambiente purgatorio, Ambiente casaWinchester, Ambiente ceu){
        if(denver != null)
            saidas.put(denver.getNomeAmbiente(), denver);
        if(houston != null)
            saidas.put(houston.getNomeAmbiente(), houston);
        if(casaCaim != null)
            saidas.put(casaCaim.getNomeAmbiente(), casaCaim);
        if(casaBob != null)
            saidas.put(casaBob.getNomeAmbiente(), casaBob);
        if(inferno != null)
            saidas.put(inferno.getNomeAmbiente(), inferno);
        if(purgatorio != null)
            saidas.put(purgatorio.getNomeAmbiente(), purgatorio);
        if(casaWinchester != null)
            saidas.put(casaWinchester.getNomeAmbiente(), casaWinchester);
        if(ceu!=null)
            saidas.put(ceu.getNomeAmbiente(), ceu);
    }

    /**
     * @return o nome do ambiente.
     */
    public String getNomeAmbiente() {
        return nomeAmbiente;
    }
    
    /**
     * Metodo que retornará as mensagens quando o jogador entra em um novo ambiente.
     * passará as informações referentes as condições do jogador de acordo com 
     * cada ambiente, como itens na mochila no qual o jogador está deverá ser impresso
     * O metodo é abstrato e deve ser sobrescrito nas classes filhas
     * @param dean Jogador
     * @return 
     */
    public abstract String mensagemDeEntrada(JogadorDean dean);
    
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
     * @return 
     */
    public boolean getJaVisitada() {
        return jaVisitada;
    }

    public void setJaVisitada(boolean jaVisitada) {
        this.jaVisitada = jaVisitada;
    }
    
    /**
     * Retorna a saida com a devida direcao
     * @param direcao
     * @return 
     */
    public Ambiente getAmbiente(String direcao){
        return saidas.get(direcao);
    }
    
    /**
     * 
     * @return 
     */
    public String getSaidas(){
        String textoSaidas = "";
        for (String direcao : saidas.keySet()){
            textoSaidas = textoSaidas + direcao + " ";
        }
        return textoSaidas;
    }
}
