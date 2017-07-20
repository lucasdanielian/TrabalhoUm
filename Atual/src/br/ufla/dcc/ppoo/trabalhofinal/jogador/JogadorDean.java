package br.ufla.dcc.ppoo.trabalhofinal.jogador;

import br.ufla.dcc.ppoo.trabalhofinal.itens.ColecaoDeItens;
import br.ufla.dcc.ppoo.trabalhofinal.itens.Item;
import br.ufla.dcc.ppoo.trabalhofinal.itens.ItemDiario;

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
     * Metodo utilizado para ler o diario
     * @return String contendo as paginas de um diario
     */
    public String lerPaginasDiario(){
        return diario.getPaginas();
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
     * @param aux
     * Autera o valor de Marca de false para true caso
     */
    public void setMarcaCaim(boolean aux){
        marcaCaim = aux;
    }
    
    /**
     * Metodo que retorna true caso o item seja inserido ou
     * false caso ao contraio
     * @param item o objeto que sera inserido na mochila
     * @return booleano para informar se foi inserido ou não
     */
    public String inserirItensMochila(Item item){
        if(espacoDisponivelMochila()){
            boolean adicionado = mochila.inserirItens(item);
            if (adicionado == true){
                return "adicionado";
            }else{
                return "nao adicionado";
            }
        }
        else{
            return "Você nao possui espaço na mochila disponivel."
                            + "\n Você deve liberar espaço para coletar o item";
        }
    }
    
    /**
     * Metodo utilizado para remover um item da mochila
     * @param item Recebe o nome do item (String)
     * @return retorna o item removido
     */
    public Item removerPeloNomeDaMochila(String item){
        return mochila.removerPeloNome(item);
    }
    
    /**
     * Metodo Utilizado para mostrar o que há dentro da mochila
     * @return String contendo os itens da mochila
     */
    public String exibirItensMochila(){
        return mochila.retornaItens();
    }
    
    /**
     * Metodo utilizado para verificar se há espaço disponivel na mochila
     * @return true caso haja espaço e false caso ao contrario
     */
    public boolean espacoDisponivelMochila(){
        return mochila.espacoDisponivel();
    }
    
    /**
     * Adiciona a pagina ao diario do jogador
     * @param pagina é uma string que irá compor as paginas.
     * @return true se adicionado ou false caso ao contrario.
     */
    public boolean adicionarPaginaDiario(String pagina){
        boolean adicionou;
        if(diario.getPaginas().contains(pagina)){
            adicionou = false;
        }else{
            diario.adicionarPagina(pagina);
            adicionou = true;
        }
        return adicionou;
    }
    
    /**
     * Retorna o tamanho atual do diario
     * @return número inteiro contendo o tamanho do Diario
     */
    public int tamanhoDiario(){
        return diario.getTamanho();
    }
    
    /**
     * Metodo utilizado para buscar o item
     * @param item é uma string passada com o nome do item buscado
     * @return Item buscado pelo nome
     */
    public Item buscarItemPeloNomeNaMochila(String item){
        return mochila.buscarPeloNome(item);
    }
}
