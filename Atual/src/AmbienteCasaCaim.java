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
        System.out.println("Nao ha objetos para ser coletado neste local");
        if(getJaVisitada() == false){ // se o jogador nunca veio neste ambiente
            setJaVisitada(true);
            for (int i = 0; i < dean.getMochila().getQuantidadeAtual() ;i++) {
                    if(dean.getMochila().retornaItem(i).getNomeItem().equals("Carta de reconhecimento")){
                        visitouBob = true;
                    }
            }
            if(visitouBob == true){ // se ele passou por Bob
                System.out.println("Dean se direciona para a casa de caim. "
                        + "Chegando lá, Dean apresenta a ele a carta entregue "
                        + "por Bob. Caim, com muita fúria afirma que ele era um "
                        + "rapaz muito ousado para encarar face a face o primeiro"
                        + " assassino da história. Entretanto, Caim decide ajudar"
                        + " Dean pelo pedido de Bob. Ele diz : “Para que você "
                        + "receba o poder da marca de Caim, que será necessário "
                        + "para derrotar o demônio que aprisionou seu irmão, "
                        + "você precisará fazer algo para mim também. Atualmente,"
                        + " um dos meus negócios é a venda ilegal de orgãos. "
                        + "Entretanto, existe um grupo de vampiros em Houston "
                        + "que está drenando os corpos dos cadáveres, inutilizando"
                        + " meus preciosos corpos. Preciso que você mate todos"
                        + " para mim, e me traga a cabeça de um deles como prova."
                        + " Após isto, volte até mim e eu o darei o que precisa.");
                recebeuTask = true;
            }
            else{ // se ele nao passou por Bob
                System.out.println("Dean se direciona para a casa de caim. "
                        + "Chegando lá, Caim, com muita fúria afirma que ele era"
                        + " um rapaz muito ousado para encarar face a face o "
                        + "primeiro assassino da história. Diz ainda que não deve"
                        + " nada ao mesmo, e que não irá ajudá-lo, a não ser que"
                        + " alguém importante o peça para fazê-lo.");
            }
        }
        else{ // se o jogador ja veio nesse ambiente
            if(recebeuTask == true && dean.getMarcaCaim() == false){
                for (int i = 0; i < dean.getMochila().getQuantidadeAtual() ;i++) {
                    if(dean.getMochila().retornaItem(i).getNomeItem().equals("Cabeca de Vampiro")){ // se ele completou a task
                        System.out.println("Dean se direciona para a casa de caim. "
                            + "Chegando lá, ele entrega a caim a cabeça de vampiro"
                            + " que coletou na missão feita. Caim, impressionado "
                            + "com o sucesso do rapaz, concede ao mesmo a marca de"
                            + " Caim, um poder que o torna um Semi-Deus.");
                            //precisa implementar o remover item da mochila
                        dean.setMarcaCaim(true);
                        dean.getDiario().adicionarPagina("Você possui a marca de Caim");
                    }
                }
                if(dean.getMarcaCaim() == false){ // se ele nao completou a task
                    System.out.println("Dean se direciona para a casa de caim. "
                            + "Chegando lá, Caim fica furioso com Dean, pois ele"
                            + " ainda não fez a task requerida por ele. O mesmo "
                            + "ordena que Dean vá embora, e apenas volte com a "
                            + "cabeça de vampiro.");
                }
            }
            else if(recebeuTask == true && dean.getMarcaCaim() == true){ // nao ha mais nada a se fazer no ambiente
                System.out.println("Dean se direciona para a casa de Caim,porem"
                        + " nao há mais nada a ser feito neste ambiente. Você "
                        + "está perdendo tempo aqui");
            }
            else{
                System.out.println("Dean se direciona para a casa de caim. " //ainda nao foi em Bob
                        + "Chegando lá, Caim, com muita fúria afirma que ele era"
                        + " um rapaz muito ousado para encarar face a face o "
                        + "primeiro assassino da história. Diz ainda que não deve"
                        + " nada ao mesmo, e que não irá ajudá-lo, a não ser que"
                        + " alguém importante o peça para fazê-lo.");
            }
            
        }
    }

}
