package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.Jogador;

/**
 * Classe AmbienteCasaBob - um ambiente em um jogo adventure.
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
public class AmbienteSanAntonio extends Ambiente {
    private boolean foiCeu;
    private Item item;
    
    /**
     * Constroi um ambiente CasaBob 
     * @param nomeAmbiente passando seu nome por parametro
     */
    public AmbienteSanAntonio(String nomeAmbiente)  {
        super(nomeAmbiente);
        foiCeu = false;
        item = new Item("Telefone", "Telefone que toca na igreja.");
        inserirItensAmbiente(item);
    }
    
    /**
     * 
     * @param dean
     * Passa o jogador como parametro
     * @return String
     * Retornará uma String que devera exibir a mensagem de entrada referente
     * a casa do Bob
     */
    @Override
    public String mensagemDeEntrada(Jogador dean){
        //texto a ser exibido caso o jogador entre pela primeira vez neste ambiente
        String mensagemEntrada1 = "\nDean se direciona para San Antonio, no estado do\n"
            + "Texas.";
        String mensagemEntrada2 = "Ele se direciona para uma antiga catedral à \n "
                + "qual frequentava quando era apenas uma criança. Dean não é \n"
                + "uma pessoa de rezar muito, o que se justifica por todos estes\n"
                + " anos lutando contra criaturas do mal, entretanto, ele se \n"
                + "ajoelha e reza. Reza para que Deus possa lhe ajudar, arrependido\n"
                + " de ter perdido tempo se divertindo em Las Vegas.Como recompensa\n"
                + " por este momento de fé, Dean ganhou 2 dias extra para procurar\n"
                + " seu irmão. No momento em que ia se levantar para ir embora ,\n"
                + " um telefone , aparentemente esquecido em cima do banco, começa \n"
                + "a tocar.";
        if(getJaVisitada() == false){
            
            for (int i = 0; i < dean.tamanhoDiario(); i++) {
                
                if(dean.lerPaginasDiario().indexOf("Você pode, mas não deve "
                        + "buscar as almas no Purgatório.")>=0){
                    
                    foiCeu = true;
                }
            }
            if(foiCeu == true){
                setJaVisitada(true);
                dean.adicionarPaginaDiario("Você ganhou mais 2 dias extra");
                if(dean.espacoDisponivelMochila()){
                    return mensagemEntrada1 + mensagemEntrada2 + "O item telefone "
                            + "agora esta disponivel para ser coletado";
                }
                else{
                    return mensagemEntrada1 + mensagemEntrada2 + "O item telefone "
                            + "nao esta disponivel para ser coletado pois na ha"
                            + " espaco suficiente na mochila";
                }
            }
            else{
                return mensagemEntrada1 + "Entretanto,"
                        + "Dean não sabe o que fazer neste ambiente.\n";
            }
        }
        else{ // texto a ser exibido caso o jogador já tenha vindo ao ambiente em questão
            if(getItemFoiColetado() == true){    
            return mensagemEntrada1 + "Entretanto, não sabe mais o que fazer neste ambiente";
            }
            else{
                if(dean.espacoDisponivelMochila()){
                    return mensagemEntrada1 + "O item telefone"
                            + "agora esta disponivel para ser coletado";
                }
                else{
                    return mensagemEntrada1 + "O item telefone "
                            + "nao esta disponivel para ser coletado pois na ha"
                            + " espaco suficiente na mochila";
                }
            }
        }
    }

    /**
     * Metodo que seta a imagem do ambiente
     * @return String com o endereco da imagem
     */
    @Override
    public String imagemDoAmbiente() {
        return "sanfrancisco.jpg";
    }
    
    /**
     * Metodo que retorna o conteudo da variavel foiCeu
     * @return true se visitado e false caso contrario
     */
    @Override
    public boolean getFoiCeu() {
        return foiCeu;
    }
}
