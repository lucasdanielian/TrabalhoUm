/**
 * Classe AmbienteCasaCaim - um ambiente em um jogo adventure.
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
public class AmbientePurgatorio extends Ambiente {

    private boolean itemFoiColetado;
    
    /**
     * Constroi um ambiente "AmbientePurgatorio" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    public AmbientePurgatorio(String nomeAmbiente)  {
        super(nomeAmbiente);
        itemFoiColetado = false;
    }
    
    /**
     * Metodo que retorna uma String que deverá exibir a mensagem de entrada
     * referente ao ambiente pugatorio
     * @param dean
     * @return 
     */
    @Override
    public String mensagemDeEntrada(JogadorDean dean) {
        
        if(getJaVisitada() == false){ // se o jogador ainda nao passou por este ambiente
            //fazer texto do purgatório
            setJaVisitada(true);
            
            if(dean.getMochila().espacoDisponivel()){
                dean.getMochila().inserirItens(item);
                itemFoiColetado = true;
                return "O portador de almas foi coletado e está na mochila";
            }
            else{
                return "Você não possui espaço suficiente na mochila para pegar o item";
            }
        }
        else{ // caso o jogador ja tenha passado por este ambiente antes
            if(itemFoiColetado == false){
                if(dean.getMochila().espacoDisponivel()){
                    dean.getMochila().inserirItens(item);
                    itemFoiColetado = true;
                    return "O portador de almas foi coletado e está na mochila";
                }
                else{
                    return "Você não possui espaço suficiente na mochila para pegar o item";
                }
            }
            else{
                return "fazer mensagem de que não existe mais nada a ser feito neste ambiente";
            }
        }
    }

}
