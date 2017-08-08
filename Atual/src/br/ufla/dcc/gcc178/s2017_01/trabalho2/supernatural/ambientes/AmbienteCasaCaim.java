package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.imagens.GerenciadorDeImagens;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.Jogador;
import javax.swing.ImageIcon;

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
public class AmbienteCasaCaim extends Ambiente {
    private boolean visitouBob;
    private boolean recebeuTask;
    
    /**
     * @param nomeAmbiente
     * Constroi um ambiente "Inferno" passando seu nome por parametro
     */
    public AmbienteCasaCaim(String nomeAmbiente)  {
        super(nomeAmbiente);
        visitouBob = false;
        recebeuTask = false;
    }
    
    /**
     * @param dean 
     * Passa o jogador como parametro para interagir com o ambiente
     * @return
     * Retorna uma String que devera exibir a mensagem de entrada referente a casa Caim
     */
    @Override
    public String mensagemDeEntrada(Jogador dean){
        if(getJaVisitada() == false){ // se o jogador nunca veio neste ambiente
            setJaVisitada(true);
            if(dean.buscarItemPeloNomeNaMochila("Carta")!=null){
                visitouBob = true;
            }
            if(visitouBob == true){ // se ele passou por Bob
                recebeuTask = true;
                dean.adicionarPaginaDiario("Em Houston, existe um grupo de"
                        + " vampiros que Caim o pediu para que fossem eliminados.");
                return "Dean se direciona para a casa de caim.\n"
                        + "Chegando lá, Dean apresenta a ele a carta entregue\n"
                        + "por Bob. Caim, com muita fúria afirma que ele era um\n"
                        + "rapaz muito ousado para encarar face a face o primeiro\n"
                        + "assassino da história. Entretanto, Caim decide ajudar\n"
                        + "Dean pelo pedido de Bob. Ele diz : “Para que você\n"
                        + "receba o poder da marca de Caim, que será necessário\n"
                        + "para derrotar o demônio que aprisionou seu irmão,\n"
                        + "você precisará fazer algo para mim também. Atualmente,\n"
                        + "um dos meus negócios é a venda ilegal de orgãos.\n"
                        + "Entretanto, existe um grupo de vampiros em Houston\n"
                        + "que está drenando os corpos dos cadáveres, inutilizando\n"
                        + "meus preciosos corpos.Preciso que você mate todos\n"
                        + "para mim, e me traga a cabeça de um deles como prova.\n"
                        + "Após isto, volte até mim e eu o darei o que precisa.\n";
            }
            else{ // se ele nao passou por Bob
                return "Dean se direciona para a casa de caim.\n "
                        + "Chegando lá, Caim, com muita fúria afirma que ele era\n"
                        + "um rapaz muito ousado para encarar face a face o\n"
                        + "primeiro assassino da história. Diz ainda que não deve\n"
                        + "nada ao mesmo, e que não irá ajudá-lo, a não ser que\n"
                        + "alguém importante o peça para fazê-lo.\n";
            }
        }
        else{ // se o jogador ja veio nesse ambiente
            if(recebeuTask == true && dean.getMarcaCaim() == false){
                    // se ele completou a task
                if(dean.buscarItemPeloNomeNaMochila("CabecaVampiro")!=null){
                    dean.setMarcaCaim(true);
                    dean.adicionarPaginaDiario("Você possui a marca de Caim");
                    dean.removerPeloNomeDaMochila("CabecaVampiro");
                    return "Dean se direciona para a casa de caim.\n"
                        + "Chegando lá, ele entrega a caim a cabeça de vampiro\n"
                        + "que coletou na missão feita. Caim, impressionado\n"
                        + "com o sucesso do rapaz, concede ao mesmo a marca de\n"
                        + "Caim, um poder que o torna um Semi-Deus.\n";
                }
                if(dean.getMarcaCaim() == false){ // se ele nao completou a task
                    return "Dean se direciona para a casa de caim.\n "
                            + "Chegando lá, Caim fica furioso com Dean, pois ele\n"
                            + "ainda não fez a task requerida por ele. O mesmo\n"
                            + "ordena que Dean vá embora, e apenas volte com a\n"
                            + "cabeça de vampiro.\n";
                }
            }
             // nao ha mais nada a se fazer no ambiente
            else if(recebeuTask == true && dean.getMarcaCaim() == true){
                return"Dean se direciona para a casa de Caim,porem"
                        + " nao há mais nada a ser feito neste ambiente. \nVocê "
                        + "está perdendo tempo aqui";
            }
            
            else if(recebeuTask == false && dean.buscarItemPeloNomeNaMochila("Carta") != null){
            
                visitouBob = true;
                dean.adicionarPaginaDiario("Em Houston, existe um grupo de"
                        + " vampiros que Caim o pediu para que fossem eliminados.");
                return "Dean se direciona para a casa de caim.\n"
                        + "Chegando lá, Dean apresenta a ele a carta entregue\n"
                        + "por Bob. Caim, com muita fúria afirma que ele era um\n"
                        + "rapaz muito ousado para encarar face a face o primeiro\n"
                        + "assassino da história. Entretanto, Caim decide ajudar\n"
                        + "Dean pelo pedido de Bob. Ele diz : “Para que você\n"
                        + "receba o poder da marca de Caim, que será necessário\n"
                        + "para derrotar o demônio que aprisionou seu irmão,\n"
                        + "você precisará fazer algo para mim também. Atualmente,\n"
                        + "um dos meus negócios é a venda ilegal de orgãos.\n"
                        + "Entretanto, existe um grupo de vampiros em Houston\n"
                        + "que está drenando os corpos dos cadáveres, inutilizando\n"
                        + "meus preciosos corpos.Preciso que você mate todos\n"
                        + "para mim, e me traga a cabeça de um deles como prova.\n"
                        + "Após isto, volte até mim e eu o darei o que precisa.\n";
            
            }
            
            //ainda nao foi em Bob
            else{
                return "Dean se direciona para a casa de caim.\n"
                        + "Chegando lá, Caim, com muita fúria afirma que ele era\n"
                        + "um rapaz muito ousado para encarar face a face o\n"
                        + "primeiro assassino da história. Diz ainda que não deve\n"
                        + "nada ao mesmo, e que não irá ajudá-lo, a não ser que\n"
                        + "alguém importante o peça para fazê-lo.\n";
            }
            
        }
        return null;
    }
    
    /**
     * 
     * @return String
     * retorna uma String com o endereco da imagem
     */
    @Override
    public ImageIcon imagemDoAmbiente() {
        return GerenciadorDeImagens.CASA_CAIM;
    }
}
