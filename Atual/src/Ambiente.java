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

    public boolean getJaVisitada() {
        return jaVisitada;
    }

    public void setJaVisitada(boolean jaVisitada) {
        this.jaVisitada = jaVisitada;
    }
    public Ambiente saida1;
    public Ambiente saida2;
    public Ambiente saida3;
    public Ambiente saida4;
    public Ambiente saida5;
    public Ambiente saida6;
    public Ambiente saida7;
    public Ambiente saida8;
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
            saida1 = denver;
        if(houston != null)
            saida2 = houston;
        if(casaCaim != null)
            saida3 = casaCaim;
        if(casaBob != null)
            saida4 = casaBob;
        if(inferno != null)
            saida5 = inferno;
        if(purgatorio != null)
            saida6 = purgatorio;
        if(casaWinchester != null)
            saida7 = casaWinchester;
        if(ceu!=null)
            saida8 = ceu;
    }

    /**
     * @return o nome do ambiente.
     */
    public String getNomeAmbiente() {
        return nomeAmbiente;
    }
    
    public void mensagemDeEntrada() // metodo que retornará as mensagens que o ambiente no qual o jogador está deverá ser impresso
    {
        
    }

}
