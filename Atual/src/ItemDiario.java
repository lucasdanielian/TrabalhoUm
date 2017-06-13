
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
     * Cria um Item Diario
     * @param nomeItem
     * com seu nome
     * @param descricaoItem
     * e sua descrição
     */
    public ItemDiario(String nomeItem, String descricaoItem) {
        super(nomeItem, descricaoItem);
        paginas = new ArrayList<String>();
    }
    
    /**
     * @param chave
     * Adiciona páginas ao diario, que são Strings
     */
    public void adicionarPagina(String chave){
        this.paginas.add(chave);
    }
    
    /**
     * @return String
     * Retorna uma String com todas as páginas concatenadas
     */
    public String getPaginas(){
        String resposta = "";
        for(String aux : this.paginas){
            resposta += aux + "\n";
        }
        return resposta;
    }
    
    /**
     * @return Int
     * Retorna o tamanho da lista de String, que são as páginas do diario
     */
    public int getTamanho(){
        return paginas.size();
    }
    
    /**
     * @param i
     * Pega uma página especifica, que é a posição da lista na qual a mesma foi
     * adiciona.
     * @return
     * e retorna a página 
     */
    public String getPagina(int i){
        return paginas.get(i);
    }
    
}
