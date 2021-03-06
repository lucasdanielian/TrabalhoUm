/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.Jogador;

/**
 *
 * @author aluno
 */
public class AmbienteDefault extends Ambiente {
    
    private String mensagem;

    public AmbienteDefault(String nomeAmbiente, String m) {
        super(nomeAmbiente);
        mensagem = m;
    }

    /**
     * Mensagem de entrada do ambiente
     * @param dean jogador
     * @return uma string contendo a mensagem de entrada do ambiente
     */
    @Override
    public String mensagemDeEntrada(Jogador dean) {
        return mensagem;
    }

    /**
     * Metodo responsavel por retornar a imagem do ambiente
     * @return String contendo a imagem do ambiente
     */
    @Override
    public String imagemDoAmbiente() {
        return getNomeAmbiente() + ".png";
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
