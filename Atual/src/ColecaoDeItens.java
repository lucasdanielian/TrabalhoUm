
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
public class ColecaoDeItens {
    
    private int capacidade;
    private int quantidadeAtual;
    private List<Item> itens; 
    
    
    /**
     * Constroi uma lista de objetos da classe Itens, e determina a capacidade da mesma a partir de uma capacidade
     * @param capacidade 
     */
    public ColecaoDeItens(int capacidade) {
        this.capacidade = capacidade;
        itens = new ArrayList<Item>();
        quantidadeAtual = 0;
    }
    
    
    /**
     * o metodo exibir() mostra na tela a atual disposicao dos itens na sua 
     */
    public String exibirItens(){
        String itensRetorno = null;
        for(Item i:itens){
            itensRetorno = " " + i.getDescricaoItem();
        }
        return itensRetorno;
    }
    
    /**
     * O metodo inserirItens() insere um novo item na coleção recebendo o mesmo como parâmetro
     * @param novo 
     */
    public boolean inserirItens(Item novo){
        if(quantidadeAtual < capacidade){
            itens.add(novo);
            return true;
        }
        else{
            return false;
        }
    }
    
    
    /**
     * O metodo removerPeloNome(nomeDoItem) busca pelo objeto passado como parâmetro e o remove caso exista.
     * @param nomeDoItem
     * @return Retorna o objeto do tipo Item que o usuario busca, ou retorna nulo (caso nao encontrar)
     */
    public Item removerPeloNome(String nomeDoItem){
        for(Item i:itens){
            if(i.getNomeItem().equals(nomeDoItem)){
                itens.remove(i);
                return i;
            }
        }
        
        return null;
    }
    
    /**
     * O metodo que busca um item na lista tendo como chave o seu nome.
     * @param nomeDoItem
     * @return Retorna o objeto do tipo Item que o usuario busca, ou retorna nulo (caso nao encontrar)
     */
    public Item buscarPeloNome(String nomeDoItem){
        for(Item i:itens){
            if(i.getNomeItem().equals(nomeDoItem)){
                return i;
            }
        }
        return null;
    }
    
    /**
     * Retorna true caso haja espaço disponível para armazenar mais itens, ou
     * false caso já esteja cheio e não seja mais possivel adicionar
     * @return 
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
     * Retorna um inteiro com a quantidade de itens
     * @return 
     */
    public int getQuantidadeAtual(){
        return quantidadeAtual;
    }
    
    /**
     * Retorna um item, passado sua posicao no vetor.
     * @param i
     * @return 
     */
    public Item retornaItem(int i){
        return itens.get(i);
    } 
    
}
