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
public class Ambiente  {
    public String descricao;
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
    public Ambiente(String descricao)  {
        this.descricao = descricao;
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul A saida sul.
     * @param oeste A saida oeste.
     */
    public void ajustarSaidas(Ambiente um, Ambiente dois, Ambiente tres, Ambiente quatro, Ambiente cinco, Ambiente seis, Ambiente sete)  {
        if(um != null)
            saida1 = um;
        if(dois != null)
            saida2 = dois;
        if(tres != null)
            saida3 = tres;
        if(quatro != null)
            saida4 = quatro;
        if(cinco != null)
            saida5 = cinco;
        if(seis != null)
            saida6 = seis;
        if(sete != null)
            saida7 = sete;
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao() {
        return descricao;
    }

}
