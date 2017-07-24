package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.interacaousuario;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.regranegocio.RegraNegocio;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.comandos.Comando;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.comandos.Analisador;
import java.util.Scanner;

/**
 *
 * @author junior
 */
public class JogoTerminal{

    /**
     * Metodo principal da classe
     * @param args parametro padrão do java
     */
    public static void main(String[] args) {
        //Variaveis
        RegraNegocio regraNegocio = new RegraNegocio();
        Analisador analisador = new Analisador();
        Scanner entrada = new Scanner(System.in);         // origem da entrada de comandos
        Comando comando;
        String terminado = "ok"; // variavel que encerra o jogo
        
        //start do programa
        System.out.println(regraNegocio.mensagemBoasVindas());
        System.out.print("> ");

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
        while (terminado != null) {
            terminado = entrada.nextLine();
            comando = analisador.pegarComando(terminado);
            terminado = regraNegocio.processarComando(comando);
            System.out.println(terminado);
            System.out.print("> ");
        }

        //Retorna mensagem final
        System.out.println("\nObrigado por jogar. Ate mais!\n");
    }
}