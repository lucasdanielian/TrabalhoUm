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
    public Ambiente saida1;
    public Ambiente saida2;
    public Ambiente saida3;
    public Ambiente saida4;
    public Ambiente saida5;
    public Ambiente saida6;
    public Ambiente saida7;
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
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul A saida sul.
     * @param oeste A saida oeste.
     */

    public void ajustarSaidas(Ambiente denver, Ambiente houston, Ambiente casaCaim, Ambiente casaBob, Ambiente inferno, Ambiente purgatorio, Ambiente casaWinchester){
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
    }

    /**
     * @return o nome do ambiente.
     */
    public String getNomeAmbiente() {
        return nomeAmbiente;
    }

}
