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
        diario = new Diario("Diário de John Winchester", "O diário em questão pertencia ao pai dos irmãos antes de morrer. Ele possui diversas informações auxiliares.");
        mochila = new ColecaoDeItens(3);
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
    
    
    
    
}
