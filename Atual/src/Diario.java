
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas Danielian
 */
public class Diario extends Item{
    
    private List<String> paginas;

    public Diario(String nomeItem, String descricaoItem) {
        super(nomeItem, descricaoItem);
        paginas = new ArrayList<String>();
    }
    
    public void adicionarPagina(String chave){
        this.paginas.add(chave);
    }
    
    public String getPaginas(){
        String resposta = "";
        for(String aux : this.paginas){
            resposta += aux + "\n";
        }
        return resposta;
    }
    
}
