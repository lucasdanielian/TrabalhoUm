package br.ufla.dcc.ppoo.trabalhofinal.ambientes;

import br.ufla.dcc.ppoo.trabalhofinal.jogador.JogadorDean;

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
    
    /**
     * @param nomeAmbiente
     * Constroi um ambiente "AmbientePurgatorio" passando seu nome por parametro
     */
    public AmbientePurgatorio(String nomeAmbiente)  {
        super(nomeAmbiente);
        itemFoiColetado = false;
        foiCeu = false;
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
            
            for (int i = 0; i < dean.getDiario().getTamanho(); i++) {
                if(dean.getDiario().getPagina(i).equals("Deve-se entregar uma pena de anjo e um dente de lobo no portal do inferno.")){
                    foiCeu = true;
                }
            }
            
            if(foiCeu == true){
            
                setJaVisitada(true);

                if(dean.getMochila().espacoDisponivel()){
                    dean.getMochila().inserirItens(super.getItem());
                    itemFoiColetado = true;
                    return texto1 + "\nO item 'Almas' foi adicionado na mochila\n";
                }
                else{
                    return texto1 + "\nEntretanto, você não possui espaço\n"
                            + "suficiente na mochila para pegar o item\n";
                }
            }
            else{
                return "Dean se direciona para o purgatório, mas não sabe o que fazer neste ambiente.";
            }
        }
        else{ // caso o jogador ja tenha passado por este ambiente antes
            if(itemFoiColetado == false){
                if(dean.getMochila().espacoDisponivel()){
                    dean.getMochila().inserirItens(super.getItem());
                    itemFoiColetado = true;
                    return "O item 'Almas' foi adicionado na mochila";
                }
                else{
                    return "Você não possui espaço suficiente na mochila para pegar o item";
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
        return "/home/junior/projetos/TrabalhoUm/Atual/src/br/ufla/dcc/ppoo/trabalhofinal/imagens/logout.png";
    }

}