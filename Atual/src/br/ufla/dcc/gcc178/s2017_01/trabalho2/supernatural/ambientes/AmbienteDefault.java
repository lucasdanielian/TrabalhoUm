/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.imagens.GerenciadorDeImagens;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.Jogador;
import javax.swing.ImageIcon;

/**
 *
 * @author aluno
 */
public class AmbienteDefault extends Ambiente{
    
    private String mensagem;

    public AmbienteDefault(String nomeAmbiente, String m) {
        super(nomeAmbiente, 1);
        mensagem = m;
    }

    @Override
    public String mensagemDeEntrada(Jogador dean) {
        return mensagem;
    }

    @Override
    public ImageIcon imagemDoAmbiente() {
        return GerenciadorDeImagens.PRINCIPAL;
    }
    
}
