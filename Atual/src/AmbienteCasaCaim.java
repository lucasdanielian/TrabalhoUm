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
public class AmbienteCasaCaim extends Ambiente {
    private boolean visitouBob;
    private boolean recebeuTask;
    
    /**
     * Constroi um ambiente "Inferno" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    public AmbienteCasaCaim(String nomeAmbiente)  {
        super(nomeAmbiente);
        visitouBob = false;
        recebeuTask = false;
    }
    
    /**
     * Exibe a mensagem de entrada referente a casa Caim
     * @param dean 
     */
    @Override
    public void mensagemDeEntrada(JogadorDean dean){
        if(getJaVisitada() == false){ // se o jogador nunca veio neste ambiente
            setJaVisitada(true);
            for (int i = 0; i < dean.getMochila().getQuantidadeAtual() ;i++) {
                    if(dean.getMochila().retornaItem(i).getNomeItem().equals("Carta de reconhecimento")){
                        visitouBob = true;
                    }
            }
            if(visitouBob == true){
                //fazer o texto necessário para este ambiente falando sobre a missão que deve ser feita
                recebeuTask = true;
            }
            else{
                // fazer o texto necessario dizendo que ele ainda nao sabe o que fazer neste ambiente
            }
        }
        else{ // se o jogador ja veio nesse ambiente
            if(recebeuTask == true && dean.getMarcaCaim() == false){
                for (int i = 0; i < dean.getMochila().getQuantidadeAtual() ;i++) {
                    if(dean.getMochila().retornaItem(i).getNomeItem().equals("Cabeca de Vampiro")){
                        // fazer mensagem de que ele cumpriu as tasks necessarias
                        dean.setMarcaCaim(true);
                        dean.getDiario().adicionarPagina("Você possui a marca de Caim");
                    }
                }
                if(dean.getMarcaCaim() == false){
                    //fazer mensagem de que ele ainda nao cumpriu a missão designada
                }
            }
            else if(recebeuTask == true && dean.getMarcaCaim() == true){
                //fazer mensagem de que nao há nada mais a ser feito neste ambiente
            }
            else{
                //fazer texto dizendo que dean ainda nao sabe o que fazer neste ambiente
            }
            
        }
    }

}
