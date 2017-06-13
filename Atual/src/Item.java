/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe responsavel por ser os objetos que o jogador ira portar, ou conquistar.
 * @author junior
 */
public class Item {
    //Nome objeto
    private String nomeItem;
    //Descrição Objeto
    private String descricaoItem;
    // teste valdeci3
    /**
     * Constroi um item passando 
     * @param nomeItem
     * seu nome
     * @param descricaoItem
     * e seu descrição
     */
    public Item(String nomeItem, String descricaoItem) {
        this.nomeItem = nomeItem;   
        this.descricaoItem = descricaoItem;
    }

    /**
     * @return String
     * Retorna o nome do item juntamente com sua descrição
     */
    public String getDescricaoItem(){
        String retorno = "Item: " + nomeItem + " / Descricao Item: " + descricaoItem;
        return retorno;
    }
    
    /**
     * @return String
     * Retorna uma String contendo o nome do Item
     */
    public String getNomeItem(){
        return nomeItem;
    }
}
