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
public class AmbienteHouston extends Ambiente {

    private boolean itemFoiColetado;
    private boolean foiCaim;
    
    /**
     * @param nomeAmbiente
     * Constroi um ambiente "Houston" passando seu nome por parametro
     */
    public AmbienteHouston(String nomeAmbiente)  {
        super(nomeAmbiente);
        itemFoiColetado = false;
        foiCaim = false;
    }
    
    /**
     * @param dean
     * Passa o jogador como parametro para interação com o ambiente
     * @return String
     * Retorna uma String que deverá ser utilizada para exibir a mensagem de
     * entrada ao ambiente Houston e fazer a sobrescrita do metodo na classe pai
     */
    @Override
    public String mensagemDeEntrada(JogadorDean dean){
        //
        String texto1 = "Dean se direciona para Houston. Ele investiga onde o grupo\n"
                            + "de vampiros citado por Caim está. Após descobrir,\n"
                            + "Dean mata cada um deles e reserva uma das cabeças para\n"
                            + "levar para Caim, assim como foi exigido.";
        if(getJaVisitada() == false){ // caso o jogador nunca tenha vindo a este ambiente
            // fazer o texto dos vampiros
            
            for (int i = 0; i < dean.getDiario().getTamanho(); i++) {
                
                if(dean.getDiario().getPagina(i).equals("Em Houston, existe um "
                        + "grupo de vampiros que Caim o pediu para que fossem eliminados.")){
                    
                    foiCaim = true;
                }
            }
            
            if(foiCaim == true){
                setJaVisitada(true);
                if(dean.getMochila().espacoDisponivel()){
                    dean.getMochila().inserirItens(super.getItem());
                    itemFoiColetado = true;
                    return texto1 + "\nO item. 'CabecaVampiro' foi coletado\n";
                }
            
                else{
                    return texto1 + "\nSua mochila está cheia, não há espaço para coletar o item\n";
                }
                
            }
            else{
                return "Dean se direciona para Houston. Entretanto, Dean não "
                        + "sabe o que fazer lá.";
            }
        }
        else{// caso o jogador ja tenha vindo a esse ambiente
            if(itemFoiColetado == false){
                if(dean.getMochila().espacoDisponivel()){
                    dean.getMochila().inserirItens(super.getItem());
                    itemFoiColetado = true;
                    return "O item 'CabecaVampiro' foi coletado";
                }
                else{
                    return "Sua mochila está cheia, não há espaço para coletar o item";
                }
            }
            else{
                return "Dean se direciona para Houston, mas não existem mais ações"
                        + " aqui. Você está perdendo tempo!";
            }
        }
        
    }
    
        /**
     * 
     * @return String
     * retorna uma String com o endereco da imagem
     */
    @Override
    public String imagemDeEntrada() {
        return "/home/junior/projetos/TrabalhoUm/Atual/src/br/ufla/dcc/ppoo/trabalhofinal/imagens/group_add.png";
    }
}
