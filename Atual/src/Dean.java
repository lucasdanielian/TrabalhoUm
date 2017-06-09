/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas Danielian
 */
public class Dean {
    
    private ColecaoDeItens mochila;
    private boolean marcaCaim;
    private Diario diario;
    
    public Dean(){
        marcaCaim = false;
        diario = new Diario("Diário de John Winchester", "O diário em questão pertencia ao pai dos irmãos antes de morrer. Ele possui diversas informações importantes.");
        mochila = new ColecaoDeItens(3);
        diario.adicionarPagina("Existem lobisomens a serem caçados em Denver");
        diario.adicionarPagina("Existem vampiros a serem caçados em Houston");
        diario.adicionarPagina("Voce pode buscar ajuda com Castiel, no ceu");
        diario.adicionarPagina("Você pode buscar ajuda com Bob, na casa dele");
        diario.adicionarPagina("Almas podem ser coletadas no purgatorio");
    }
    
    public Diario getDiario(){
        return diario;
    }
    
    public boolean getMarcaCaim(){
        return marcaCaim;
    }
    
    public ColecaoDeItens getMochila(){
        return mochila;
    }
    
    public void setMarcaCaim(boolean aux){
        marcaCaim = aux;
    }
    
    
    
    
}
