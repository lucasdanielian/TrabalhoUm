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
public class AmbienteLasVegas extends Ambiente {
    private boolean foiCeu;

    
    /**
     * Constroi um ambiente CasaBob 
     * @param nomeAmbiente passando seu nome por parametro
     */
    public AmbienteLasVegas(String nomeAmbiente)  {
        super(nomeAmbiente);
        foiCeu = false;

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
        String mensagemEntrada1 = "Dean se direciona para Las Vegas, no estado de Nevada.\n"
            + "";
        String mensagemEntrada2 = "Cansado de lutar, Dean se entrega à sua maior\n"
                + " fraqueza: o álcool. Ele bebe sem parar, até não aguentar mais.\n"
                + " Dean perde 2 dias na cidade.Você perdeu 2 dias!\n";
        if(getJaVisitada() == false){
            
            for (int i = 0; i < dean.tamanhoDiario(); i++) {
                
                if(dean.lerPaginasDiario().indexOf("Você pode, mas não deve "
                        + "buscar as almas no Purgatório.")>=0){
                    
                    foiCeu = true;
                }
            }
            if(foiCeu == true){
                setJaVisitada(true);
                dean.adicionarPaginaDiario("Bebeu até cair em Las Vegas!");
                return mensagemEntrada1 + mensagemEntrada2;
            }
            else{
                return mensagemEntrada1 + "Entretanto, Dean não sabe o que fazer neste ambiente";
            }
        }
        else{ // texto a ser exibido caso o jogador já tenha vindo ao ambiente em questão
            
            return mensagemEntrada1 + mensagemEntrada2; 
        }
        
    }

    /**
     * Metodo que seta a imagem do ambiente
     * @return String com o endereco da imagem
     */
    @Override
    public String imagemDoAmbiente() {
        return "lasvegas.jpg";
    }
}
