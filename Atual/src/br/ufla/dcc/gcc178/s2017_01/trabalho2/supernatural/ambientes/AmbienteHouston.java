package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.Jogador;

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
public class AmbienteHouston extends Ambiente {

    private boolean foiCaim;
    private Item item;
    
    /**
     * Constroi um ambiente "Houston"
     * @param nomeAmbiente  passando seu nome por parametro
     */
    public AmbienteHouston(String nomeAmbiente)  {
        super(nomeAmbiente);
        foiCaim = false;
        item = new Item("CabecaVampiro", "Cabeca do vampiro oiginal");
        inserirItensAmbiente(item);
    }
    
    /**
     * @param dean
     * Passa o jogador como parametro para interação com o ambiente
     * @return String
     * Retorna uma String que deverá ser utilizada para exibir a mensagem de
     * entrada ao ambiente Houston e fazer a sobrescrita do metodo na classe pai
     */
    @Override
    public String mensagemDeEntrada(Jogador dean){
        //
        String texto1 = "Dean se direciona para Houston. Ele investiga onde o grupo\n"
                            + "de vampiros citado por Caim está. Após descobrir,\n"
                            + "Dean mata cada um deles e reserva uma das cabeças para\n"
                            + "levar para Caim, assim como foi exigido.";
        if(getJaVisitada() == false){ // caso o jogador nunca tenha vindo a este ambiente
            // fazer o texto dos vampiros
            
            for (int i = 0; i < dean.tamanhoDiario(); i++) {
                
                if(dean.lerPaginasDiario().indexOf("Em Houston, existe um grupo "
                        + "de vampiros que Caim o pediu para que fossem eliminados.")>=0){
                    
                    foiCaim = true;
                }
            }
            
            if(foiCaim == true){
                setJaVisitada(true);
                if(dean.espacoDisponivelMochila()){
                    return texto1 + "\nO item 'CabecaVampiro' agora esta disponivel para ser coletado\n";
                }
            
                else{
                    return texto1 + "\nO item 'CabecaVampiro' nao esta disponivel para ser coletado"
                            + "pois nao ha espaco na mochila\n";
                }
                
            }
            else{
                return "Dean se direciona para Houston. Entretanto, Dean não "
                        + "sabe o que fazer lá.\n";
            }
        }
        else{// caso o jogador ja tenha vindo a esse ambiente
            if(getItemFoiColetado() == false){
                if(dean.espacoDisponivelMochila()){
                    return "\nO item 'CabecaVampiro' agora esta disponivel para ser coletado\n";
                }
                else{
                    return "\nO item 'CabecaVampiro' nao esta disponivel para ser coletado"
                            + "pois nao ha espaco na mochila\n";
                }
            }
            else{
                return "Dean se direciona para Houston, mas não existem mais ações"
                        + " aqui. Você está perdendo tempo!\n";
            }
        }
        
    }
    
        /**
     * 
     * @return String
     * retorna uma String com o endereco da imagem
     */
    @Override
    public String imagemDoAmbiente() {
        return "Houston.jpg";
    }
    
    /**
     * Metodo que retorna o conteudo da variavel foiCeu
     * @return true se visitado e false caso contrario
     */
    @Override
    public boolean getFoiCeu() {
        return false;
    }
}
