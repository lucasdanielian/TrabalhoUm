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
    String nomeItem;
    //Descrição Objeto
    String descricaoItem;
    
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
        String retorno = "Item: " + nomeItem + "Descricao Item: " + descricaoItem;
        return retorno;
    }
}
