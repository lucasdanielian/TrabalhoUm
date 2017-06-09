
import java.util.ArrayList;
import java.util.List;

/* // teste da lucas-dev2
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas Danielian
 */
public class ItemDiario extends Item{
    
    private List<String> paginas;
    
    /**
     * Cria um Item Diario com seu nome e sua descrição
     * @param nomeItem
     * @param descricaoItem 
     */
    public ItemDiario(String nomeItem, String descricaoItem) {
        super(nomeItem, descricaoItem);
        paginas = new ArrayList<String>();
    }
    
    /**
     * Adiciona páginas ao diario, que são Strings
     * @param chave 
     */
    public void adicionarPagina(String chave){
        this.paginas.add(chave);
    }
    
    /**
     * Retorna uma String com todas as páginas concatenadas
     * @return 
     */
    public String getPaginas(){
        String resposta = "";
        for(String aux : this.paginas){
            resposta += aux + "\n";
        }
        return resposta;
    }
    
    /**
     * Retorna o tamanho da lista de String, que são as páginas do diario
     * @return 
     */
    public int getTamanho(){
        return paginas.size();
    }
    
    /**
     * Pega uma página especifica, que é a posição da lista na qual a mesma foi
     * adiciona.
     * @param i
     * @return 
     */
    public String getPagina(int i){
        return paginas.get(i);
    }
    
}
