
import java.util.HashMap;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2017.05.16)
 */
public abstract class Ambiente  {
    private String nomeAmbiente;
    private boolean jaVisitada; // variavel que grava se este ambiente ja foi visitado pelo jogador
    private HashMap<String, Ambiente> saidas;
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
     * @param dean Jogador
     */
    public abstract void mensagemDeEntrada(Dean dean);
    
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
