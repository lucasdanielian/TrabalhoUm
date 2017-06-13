/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe que representa o jogador
 * @author Lucas Danielian
 */
public class JogadorDean {
    
    private ColecaoDeItens mochila;
    private boolean marcaCaim;
    private ItemDiario diario;
    
    /**
     * Cria um "jogador Dean" com uma coleção de itens "Mochila" de tamanho 3
     * e inicializa as páginas do diario para que o jogador através das dicas 
     * possa assim avançar no jogo.
     */
    public JogadorDean(){
        marcaCaim = false;
        diario = new ItemDiario("Diário de John Winchester", "O diário em questão pertencia ao pai dos irmãos antes de morrer. Ele possui diversas informações importantes.");
        mochila = new ColecaoDeItens(3);
    }
    
    /**
     * @return
     * Retorna um Diario
     */
    public ItemDiario getDiario(){
        return diario;
    }
    
    /**
     * @return
     * Retorna o boolean, true se:
     * e false se: 
     */
    public boolean getMarcaCaim(){
        return marcaCaim;
    }
    
    /**
     * @return
     * Retorna a coleção de itens do jogador, denominada mochila
     */
    public ColecaoDeItens getMochila(){
        return mochila;
    }
    
    /**
     * @param aux
     * Autera o valor de Marca de false para true caso
     */
    public void setMarcaCaim(boolean aux){
        marcaCaim = aux;
    }
    
}
