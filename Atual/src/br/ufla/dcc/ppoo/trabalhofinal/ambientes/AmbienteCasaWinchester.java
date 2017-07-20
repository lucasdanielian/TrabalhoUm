package br.ufla.dcc.ppoo.trabalhofinal.ambientes;

import br.ufla.dcc.ppoo.trabalhofinal.itens.ColecaoDeItens;
import br.ufla.dcc.ppoo.trabalhofinal.itens.Item;
import br.ufla.dcc.ppoo.trabalhofinal.jogador.JogadorDean;

/**
 * Classe AmbienteCasaWinchester - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como denver,houston,casaCaim,casaBob,inferno,purgatorio,
 * casaWinchester e ceu, que são outras saídas. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * Versao Winchester feita por Lucas Danielian e Valdeci como atividade academica
 * @version 2011.07.31 (2017.05.16)
 */
public class AmbienteCasaWinchester extends Ambiente {
    
    private ColecaoDeItens armario;
    
    /**
     * @param nomeAmbiente 
     * Constroi um ambiente "CasaWincester" passando seu nome por parametro
     */
    public AmbienteCasaWinchester(String nomeAmbiente)  {
        super(nomeAmbiente);//Inicializa o nome do ambiente na classe pai
        armario = new ColecaoDeItens(100);
    }
     
    /**
     * @param dean
     * Passa o jogador como parametro para interação com o ambiente
     * @return String
     * Sobrescreve o metodo mensagem de entrada na classe pai passando a mensagem
     * correta do Ambiente Winchester e retornando em uma String
     */
    @Override
    public String mensagemDeEntrada(JogadorDean dean) {
        return "Dean está em sua casa. Aqui ele pode guardar itens coletados, e "
                + "pegar itens que estão em seu armário.\n";
    }
    
        /**
     * 
     * @return String
     * retorna uma String com o endereco da imagem
     */
    @Override
    public String imagemDoAmbiente() {
        return "/br/ufla/dcc/ppoo/trabalhofinal/imagens/casaWinchester.jpg";
    }
    
    /**
     * Metodo que retorna os itens contidos no armario
     * @return String contendo os itens
     */
    public String retornaItensDoArmario(){
        return armario.retornaItens();
    }
    
    /**
     * Remove um objeto contido no armario.
     * @param nome criterio de remoção
     * @return Item removido é retornado para tratamento
     */
    public Item removerPeloNomeNoArmario(String nome){
        return armario.removerPeloNome(nome);
    }
    
    /**
     * Insere um item no armario 
     * @param item o item passado por referencia é colocado no armario
     * @return boolean se inserido retorna true e se não inserido retorna false
     */
    public boolean inserirItensArmario(Item item){
        return armario.inserirItens(item);
    }

    /**
     * Metodo utilizado para verificar se um ambiente possui itens disponiveis
     * para captura
     * @param dean Jogador passado para verificacao da mochila caso tenha itens no
     * ambiente
     * @return String informando que nao é possivel coletar itens neste ambiente
     */
    @Override
    public String disponibilizarItemAmbiente(JogadorDean dean) {
        return "item indisponivel";
    }

    /**
     * Metodo utilizado para informar que o jagador nao pegou o item, uma vez
     * que este ambiente nao possui itens disponiveis para captura
     * @param dean Jogador passado para verificacao da mochila caso tenha itens no
     * ambiente
     * @return String informando que nao pegou nada
     */
    @Override
    public String pegarItemAmbiente(JogadorDean dean) {
        return "item nao coletado";
    }
}