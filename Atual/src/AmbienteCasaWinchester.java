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
 * @version 2011.07.31 (2017.05.16)
 * @editor Versao Winchester feita por Lucas Danielian e Valdeci como atividade academica
 */
public class AmbienteCasaWinchester extends Ambiente {
    
    /**
     * Constroi um ambiente "CasaWincester" passando seu nome por parametro
     * @param nomeAmbiente 
     */
    public AmbienteCasaWinchester(String nomeAmbiente)  {
        super(nomeAmbiente);//Inicializa o nome do ambiente na classe pai
    }
     
    /**
     * Sobrescreve o metodo mensagem de entrada na classe pai passando a mensagem
     * correta do Ambiente Winchester e retornando em uma String
     * @param dean
     * @return String
     */
    @Override
    public String mensagemDeEntrada(JogadorDean dean) {
        return "Dean está em sua casa. Aqui ele pode guardar itens coletados, e "
                + "pegar itens que estão em seu armário.";
    }
}
