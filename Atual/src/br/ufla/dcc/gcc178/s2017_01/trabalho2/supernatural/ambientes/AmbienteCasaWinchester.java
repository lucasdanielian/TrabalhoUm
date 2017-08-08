package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.imagens.GerenciadorDeImagens;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.Jogador;
import javax.swing.ImageIcon;

/**
 * Classe AmbienteCasaWinchester - um ambiente em um jogo adventure.
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
public class AmbienteCasaWinchester extends Ambiente {
    
    /**
     * @param nomeAmbiente 
     * Constroi um ambiente "CasaWincester" passando seu nome por parametro
     */
    public AmbienteCasaWinchester(String nomeAmbiente)  {
        super(nomeAmbiente, 3);//Inicializa o nome do ambiente na classe pai
    }
     
    /**
     * @param dean
     * Passa o jogador como parametro para interação com o ambiente
     * @return String
     * Sobrescreve o metodo mensagem de entrada na classe pai passando a mensagem
     * correta do Ambiente Winchester e retornando em uma String
     */
    @Override
    public String mensagemDeEntrada(Jogador dean) {
        return "Dean está em sua casa. Aqui ele pode guardar itens coletados, e "
                + "pegar itens que estão em seu armário.\n";
    }
    
        /**
     * 
     * @return String
     * retorna uma String com o endereco da imagem
     */
    @Override
    public ImageIcon imagemDoAmbiente() {
        return GerenciadorDeImagens.CASA_WINCHESTER;
    }
}