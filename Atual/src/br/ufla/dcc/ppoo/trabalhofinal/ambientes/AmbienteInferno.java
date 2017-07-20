package br.ufla.dcc.ppoo.trabalhofinal.ambientes;

import br.ufla.dcc.ppoo.trabalhofinal.jogador.JogadorDean;

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
 * Versao Winchester feita por Lucas Danielian e Valdeci como atividade academica
 * @version 2011.07.31 (2017.05.16)
 */
public class AmbienteInferno extends Ambiente {
    private boolean denteLobo;
    private boolean penaAnjo;
    private boolean almas;
    
    /**
     * @param nomeAmbiente
     * Constroi um ambiente "Inferno" passando seu nome por parametro
     * denteLobo, penaAnjo e almas é inicializado como false
     */
    public AmbienteInferno(String nomeAmbiente)  {
        super(nomeAmbiente);
        denteLobo = false;
        penaAnjo = false;
        almas = false;
    }
    
    /**
     * @param dean 
     * Passa o jogador como parametro para interagir com o ambiente
     * @return String
     * Metodo que retorna a mensagem de entrada quando se chega ao ambiente inferno
     */
    @Override
    public String mensagemDeEntrada(JogadorDean dean) {
        //
        String texto1 = "Dean chega até ao portal do inferno. Com o devido ritual,\n"
                         + "ele oferece o dente e a pena e assim, sua entrada é\n"
                         + "liberada. Tudo está muito escuro, ele mal consegue\n"
                         + "enxergar. Repentinamente, o demônio que capturou Sam\n"
                         + "aparece";
        if(dean.buscarItemPeloNomeNaMochila("Dente")!=null){
            denteLobo = true;
        }
        if(dean.buscarItemPeloNomeNaMochila("Pena")!=null){
            penaAnjo = true;
        }
        if(dean.buscarItemPeloNomeNaMochila("Almas")!=null){
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
                 return texto1 + " e captura as almas trazidas por Dean. Em seguida,\n"
                         + "o mesmo libera Sam para ir embora, cumprindo sua\n"
                         + "palavra. Entretanto, ele nunca prometeu à Dean que o\n"
                         + "deixaria voltar, e dessa maneira o mata cruelmente.\n"
                         + "GAME OVER! Digite 'sair' e tente novamente";
             }
             else{
                return texto1 + " Dean, com sua fúria por seu irmão ter sido\n"
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
    
        /**
     * 
     * @return String
     * retorna uma String com o endereco da imagem
     */
    @Override
    public String imagemDoAmbiente() {
        return "/br/ufla/dcc/ppoo/trabalhofinal/imagens/inferno.png";
    }

    /**
     * Metodo utilizado para verificar se um ambiente possui itens disponiveis
     * para captura
     * @param dean Jogador passado para verificacao da mochila caso tenha itens no
     * ambiente
     * @return String informando que nao é possivel coletar itens neste ambiente
     */
    @Override
    public String disponibilizarItemAmbiente(JogadorDean dean) {
        return "item indisponivel";
    }

    /**
     * Metodo utilizado para informar que o jagador nao pegou o item, uma vez
     * que este ambiente nao possui itens disponiveis para captura
     * @param dean Jogador passado para verificacao da mochila caso tenha itens no
     * ambiente
     * @return String informando que nao pegou nada
     */
    @Override
    public String pegarItemAmbiente(JogadorDean dean) {
        return "item nao coletado";
    }
}
