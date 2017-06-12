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
     * Constroi um item passando seu nome e seu descrição 
     * @param nomeItem
     * @param descricaoItem 
     */
    public Item(String nomeItem, String descricaoItem) {
        this.nomeItem = nomeItem;   
        this.descricaoItem = descricaoItem;
    }

    /**
     * Retorna o nome do item juntamente com sua descrição
     * @return 
     */
    public String getDescricaoItem(){
        String retorno = "Item: " + nomeItem + " / Descricao Item: " + descricaoItem;
        return retorno;
    }
    
    /**
     * Retorna uma String contendo o nome do Item
     * @return 
     */
    public String getNomeItem(){
        return nomeItem;
    }
}
