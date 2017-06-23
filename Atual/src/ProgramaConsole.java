
import br.ufla.dcc.ppoo.trabalhofinal.interacaousuario.Jogo;

/**
 * Classe principal do ProgramaConsole. Apenas inicia o jogo.
 * @author Julio
 * Edicao: Valdeci & Lucas
 */
public class ProgramaConsole {

    /**
     * Método principal. Cria um objeto da classe Jogo e inicia o jogo.
     * 
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.jogarTerminal();
    }
}