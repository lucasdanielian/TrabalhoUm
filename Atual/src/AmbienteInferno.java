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
        if(dean.getMochila().buscarPeloNome("Dente")!=null){
            denteLobo = true;
        }
        if(dean.getMochila().buscarPeloNome("Pena")!=null){
            penaAnjo = true;
        }
        if(dean.getMochila().buscarPeloNome("Almas")!=null){
            almas = true;
        }
         if(denteLobo == true && penaAnjo == true){ //significa que ele pode entrar
             if(almas == false && dean.getMarcaCaim() == false){
                return "Dean chega até ao portal do inferno. Com o devido ritual,\n"
                        + "ele oferece o dente e a pena e assim, sua entrada é liberada.\n"
                        + "Tudo está muito escuro, ele mal consegue enxergar.\n"
                        + "Repentinamente, o demônio que capturou Sam aparece e\n"
                        + "identifica que ele não trouxe as suas almas requeridas.\n"
                        + "Ele mata ambos irmãos.\n"
                        + "GAME OVER! Digite 'sair' e tente novamente!";
             }
             else if(almas == true && dean.getMarcaCaim() == false){
                 return "Dean chega até ao portal do inferno. Com o devido ritual,\n"
                         + "ele oferece o dente e a pena e assim, sua entrada é\n"
                         + "liberada. Tudo está muito escuro, ele mal consegue\n"
                         + "enxergar. Repentinamente, o demônio que capturou Sam\n"
                         + "aparece e captura as almas trazidas por Dean. Em seguida,\n"
                         + "o mesmo libera Sam para ir embora, cumprindo sua\n"
                         + "palavra. Entretanto, ele nunca prometeu à Dean que o\n"
                         + "deixaria voltar, e dessa maneira o mata cruelmente.\n"
                         + "GAME OVER! Digite 'sair' e tente novamente";
             }
             else{
                return "Dean chega até ao portal do inferno. Com o devido ritual,\n"
                        + "ele oferece o dente e a pena e assim, sua entrada é\n"
                        + "liberada. Tudo está muito escuro, ele mal consegue\n"
                        + "enxergar. Repentinamente, o demônio que capturou Sam\n"
                        + "aparece. Dean, com sua fúria por seu irmão ter sido\n"
                        + "torturado e quase morto, utiliza o poder que adquiriu\n"
                        + "e mata o demonio, liberta seu irmão e vai embora para\n"
                        + "casa. Eles podem, enfim, voltar à sua nada normal vida\n"
                        + "de caçadores de criaturas sobrenaturais.\n"
                        + "Parabéns! Você Ganhou! Digite 'sair'. " ;
             }
         }
         else{
            return "Dean chega até o portal do inferno. Entretanto, não possui\n"
                    + " os itens necessários para entrar. Assim, ele ficará preso\n"
                    + "para sempre num limbo do inferno e seu irmão morrerá\n"
                    + "GAME OVER! Digite 'sair' e tente novamente";
         }
    }
}
