/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.Jogador;

/**
 *
 * @author aluno
 */
public class AmbienteDefault extends Ambiente{
    
    private String mensagem;
    private Item item;

    public AmbienteDefault(String nomeAmbiente, String m) {
        super(nomeAmbiente);
        item = null;
        mensagem = m;
    }

    @Override
    public String mensagemDeEntrada(Jogador dean) {
        return mensagem;
    }

    @Override
    public String disponibilizarItemAmbiente(Jogador dean) {
        return "mensagem teste";    
    }

    @Override
    public Item pegarItemAmbiente(String nome) {
        return item;
    }

    @Override
    public boolean inserirItensAmbiente(Item item) {
        return false;
    }

    @Override
    public Item getItem() {
        return item;
    }

    @Override
    public String imagemDoAmbiente() {
        return "sem imagem";
    }
    
}
