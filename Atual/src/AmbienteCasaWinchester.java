/**
 * Classe AmbienteCasaWinchester - um ambiente em um jogo adventure.
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
public class AmbienteCasaWinchester extends Ambiente {
    
    private ColecaoDeItens armario;
    
    /**
     * Constroi um ambiente "CasaWincester" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    public AmbienteCasaWinchester(String nomeAmbiente)  {
        super(nomeAmbiente);//Inicializa o nome do ambiente na classe pai
        armario = new ColecaoDeItens(100);
    }
     
    /**
     * Sobrescreve o metodo mensagem de entrada na classe pai passando a mensagem
     * correta do Ambiente Winchester
     * @param dean 
     */
    @Override
    public void mensagemDeEntrada(JogadorDean dean) {
        imprimeObjetosArmario();
        System.out.println("Falta Implementacao do metodo");
    }
    
    /**
     * Exibe a coleçao de objetos guardados no armario localizado na CasaWinchester
     */
    private void imprimeObjetosArmario(){
        System.out.println("\n Objetos no armario: " + armario.exibirItens() + "\n");
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
     * @return 
     */
    private Item removerObjetosArmario(Item item){
        armario.removerPeloNome(item.getNomeItem());
        return item;
    }
    
}
