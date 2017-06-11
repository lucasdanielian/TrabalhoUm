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
public class AmbienteInferno extends Ambiente {
    private boolean denteLobo;
    private boolean penaAnjo;
    private boolean almas;
    
    /**
     * Constroi um ambiente "Inferno" passando seu nome por parametro
     * denteLobo, penaAnjo e almas é inicializado como false
     * @param nomeAmbiente 
     */
    public AmbienteInferno(String nomeAmbiente)  {
        super(nomeAmbiente);
        denteLobo = false;
        penaAnjo = false;
        almas = false;
    }
    
    /**
     * Metodo que retorna a mensagem de entrada quando se chega ao ambiente inferno
     * @param dean 
     * @return String
     */
    @Override
    public String mensagemDeEntrada(JogadorDean dean) {
         for (int i = 0; i < dean.getMochila().getQuantidadeAtual() ;i++) {
                    if(dean.getMochila().retornaItem(i).getNomeItem().equals("Dente de lobisomem")){
                        denteLobo = true;
                    }
                    if(dean.getMochila().retornaItem(i).getNomeItem().equals("pena")){
                        penaAnjo = true;
                    }
                    if(dean.getMochila().retornaItem(i).getNomeItem().equals("Portador de almas")){
                        almas = true;
                    }
            }
         if(denteLobo == true && penaAnjo == true){ //significa que ele pode entrar
             if(almas == false && dean.getMarcaCaim() == false){
                return "fazer texto de que ambos os irmãos morreram";
             }
             else if(almas == true && dean.getMarcaCaim() == false){
                 return "fazer texto que apenas sam se salva, dean morre";
             }
             else{
                return "dean mata o demonio, e salva seu irmao";
             }
         }
         else{
            return "fazer texto de que JogadorDean fica aprisionado no inferno sem poder ajudar o irmão";
         }
    }
}
