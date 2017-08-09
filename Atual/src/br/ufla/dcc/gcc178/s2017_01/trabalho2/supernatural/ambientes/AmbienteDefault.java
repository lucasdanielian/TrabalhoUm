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
    private ImageIcon imagem;

    public AmbienteDefault(String nomeAmbiente, String m) {
        super(nomeAmbiente);
        mensagem = m;
        imagem = new ImageIcon("src/br/ufla/dcc/gcc178/s2017_01/trabalho2/supernatural/imagens/" + nomeAmbiente + ".png");
    }

    @Override
    public String mensagemDeEntrada(Jogador dean) {
        return mensagem;
    }

    @Override
    public ImageIcon imagemDoAmbiente() {
        return imagem;
    }
    
    /**
     * Metodo responsavel por alterar a imagem padrão do ambiente
     * @param caminhoImagem deve ser passado o nome do ambiente, pois as imagens
     * são salvas com este nome
     */
    public void setImageIcon(String caminhoImagem){
        new ImageIcon(GerenciadorDeImagens.class.getResource(caminhoImagem));
    }
    
}
