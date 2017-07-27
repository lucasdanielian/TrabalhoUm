package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.PortadorAlmas;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.JogadorDean;

/**
 * Classe AmbienteCasaCaim - um ambiente em um jogo adventure.
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
public class AmbientePurgatorio extends Ambiente {

    private boolean itemFoiColetado;
    private boolean foiCeu;
    private Item portadorAlmas;
    
    /**
     * Constroi um ambiente "AmbientePurgatorio"
     * @param nomeAmbiente  passando seu nome por parametro
     */
    public AmbientePurgatorio(String nomeAmbiente)  {
        super(nomeAmbiente);
        itemFoiColetado = false;
        foiCeu = false;
        portadorAlmas = new PortadorAlmas("Almas", "Armazena 10 almas em seu interior.");
    }
    
    /**
     * @param dean
     * Passa um jogador como parametro para interagir com o meio ambiente
     * @return
     * Metodo que retorna uma String que deverá exibir a mensagem de entrada
     * referente ao ambiente pugatorio
     */
    @Override
    public String mensagemDeEntrada(JogadorDean dean) {
        //
        String texto1 = "Dean se direciona para o purgatório. Chegando lá, devido\n"
                            + "à sua enorme experiência como um hunter, ele consegue\n"
                            + "aprisionar as 10 almas requeridas pelo demônio para\n"
                            + "salvar seu irmão.";
        if(getJaVisitada() == false){ // se o jogador ainda nao passou por este ambiente
            //fazer texto do purgatório
            
            for (int i = 0; i < dean.tamanhoDiario(); i++) {
                if(dean.lerPaginasDiario().indexOf("Deve-se entregar uma pena de anjo "
                        + "e um dente de lobo no portal do inferno.")>=0){
                    foiCeu = true;
                }
            }
            
            if(foiCeu == true){
            
                setJaVisitada(true);

                if(dean.espacoDisponivelMochila()){
                    return texto1 + "\nO item 'Almas' agora esta disponivel para ser coletado\n";
                }
                else{
                    return texto1 + "\nO item 'Almas' nao esta disponivel para ser coletado "
                            + "pois nao ha espaco suficiente na mochila";
                }
            }
            else{
                return "Dean se direciona para o purgatório, mas não sabe o que fazer neste ambiente.\n";
            }
        }
        else{ // caso o jogador ja tenha passado por este ambiente antes
            if(itemFoiColetado == false){
                if(dean.espacoDisponivelMochila()){
                    return "\nO item 'Almas' agora esta disponivel para ser coletado\n";
                }
                else{
                    return "\nO item 'Almas' nao esta disponivel para ser coletado "
                            + "pois nao ha espaco suficiente na mochila";
                }
            }
            else{
                return "Dean se direciona para o purgatório, mas não há mais "
                        + "ações a serem feitas aqui. Você está perdendo tempo!";
            }
        }
    }
    
    /**
     * 
     * @return String
     * retorna uma String com o endereco da imagem
     */
    @Override
    public String imagemDoAmbiente() {
        return "/br/ufla/dcc/gcc178/s2017_01/trabalho2/supernatural/imagens/purgatorio.jpg";
    }

    /**
     * Metodo que verifica se um item do ambiente está disponivel ou não
     * @param dean Jogador passado para verificacao da mochila caso tenha itens no
     * ambiente
     * @return uma string para verificacao de adicao
     */
    @Override
    public String disponibilizarItemAmbiente(JogadorDean dean){
         if(getJaVisitada() == false){ //perguntar valdeci se nao tem que colocar a verificacao da mochila aqui
            if(foiCeu == true){
                return "Almas disponivel";
            }
            else{
                return "indisponivel";
            }
        }
        else{ // texto a ser exibido caso o jogador já tenha vindo ao ambiente em questão
            if(itemFoiColetado == false && foiCeu == true){    
                return "Almas disponivel";
            }
            else{
                return "indisponivel";
            }
        }
    }
    
    /**
     * Metodo que pega um Item do ambiente
     * @param dean e passado para que possa ser inserido em sua mochila
     * @return String para verificacao se pego ou nao
     */
    @Override
    public String pegarItemAmbiente(JogadorDean dean) {
        String insercaoMochila = dean.inserirItensMochila(portadorAlmas);
        if(insercaoMochila.contains("adicionado")){
            itemFoiColetado = true;
            return "item coletado";
        }else if(insercaoMochila.contains("nao adicionado")){
            itemFoiColetado = false;
            return insercaoMochila;
        }else{
            itemFoiColetado = false;
            return insercaoMochila;
        }
    }
}
