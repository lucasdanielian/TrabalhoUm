package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe responsavel por armazenar uma determinada colecao de itens do jogador
 * 
 * @author Lucas Danielian
 */
public class ColecaoDeItens implements Serializable{
    //Atributo para Serializacao
    private static final long serialVersionUID = 1L;
    private int capacidade;
    private int quantidadeAtual;
    private List<Item> itens;     
    
    /**
     * @param capacidade
     * Constroi uma lista de objetos da classe Itens,
     * e determina a capacidade da mesma a partir de uma capacidade
     */
    public ColecaoDeItens(int capacidade) {
        this.capacidade = capacidade;
        itens = new ArrayList<Item>();
        quantidadeAtual = 0;
    }
    
    
    /**
     * @return String
     * o metodo exibir() mostra na tela a atual disposicao dos itens na sua 
     */
    public String retornaDescricaoTodosItens(){
        if(itens.size() > 0){
            String itensRetorno = " ";
            for(Item i:itens){
                if(i!=null){
                    itensRetorno += " " + i.getDescricaoItem() + "\n";
                }
            }
            return itensRetorno;
        }else{
            return "Não existem itens a serem mostrados";
        }
    }
    
    /**
     * @param novo 
     * O metodo inserirItens() insere um novo item na coleção recebendo o mesmo como parâmetro
     * @return boolean
     * Se inseriu o item retorna true
     */
    public boolean inserirItens(Item novo){
        if(quantidadeAtual < capacidade){
            itens.add(novo);
            quantidadeAtual++;
            return true;
        }
        else{
            return false;
        }
    }
    
    
    /**
     * @param nomeDoItem
     * O metodo removerPeloNome(nomeDoItem) busca pelo objeto passado como parâmetro e o remove caso exista.
     * @return Item
     * Retorna o objeto do tipo Item que o usuario busca, ou retorna nulo (caso nao encontrar)
     */
    public Item removerPeloNome(String nomeDoItem){
        if(itens.size() > 0){
            for(Item i:itens){
                if(i!=null){
                    if(i.getNomeItem().equals(nomeDoItem)){
                        itens.remove(i);
                        quantidadeAtual--;
                        return i;
                    }
                }
            }
        return null;
        }else{
             return null;
        }
    }
    
    /**
     * @param nomeDoItem
     * O metodo que busca um item na lista tendo como chave o seu nome.
     * @return Item
     * Retorna o objeto do tipo Item que o usuario busca, ou retorna nulo (caso nao encontrar)
     */
    public Item buscarPeloNome(String nomeDoItem){
        if(itens.size() > 0){
            for(Item i:itens){
                if(i!=null){
                    if(i.getNomeItem().equals(nomeDoItem)){
                        return i;
                    }
                }
            }
        return null;    
        }else{
            return null;
        }
    }
    
    /**
     * @return boolean
     * Retorna true caso haja espaço disponível para armazenar mais itens, ou
     * false caso já esteja cheio e não seja mais possivel adicionar
     */
    public boolean espacoDisponivel(){
        if(quantidadeAtual < capacidade){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * @return Int
     * Retorna um inteiro com a quantidade de itens
     */
    public int getQuantidadeAtual(){
        return quantidadeAtual;
    }
    
    /**
     * Metodo que retorna todos os itens de um ambiente
     * @return uma lista contendo os itens
     */
    public List<Item> getItens() {
        return itens;
    }
    
}
