package br.ufla.dcc.ppoo.trabalhofinal.interacaousuario;

import br.ufla.dcc.ppoo.trabalhofinal.regranegocio.RegraNegocio;
import br.ufla.dcc.ppoo.trabalhofinal.comandos.Comando;
import br.ufla.dcc.ppoo.trabalhofinal.comandos.Analisador;

/**
 *
 * @author junior
 */
public class JogoTerminal{

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        RegraNegocio regraNegocio = new RegraNegocio();
        Analisador analisador = new Analisador();
        Comando comando;
        String terminado = "ok"; // variavel que encerra o jogo
        System.out.println(regraNegocio.mensagemBoasVindas());
        System.out.print("> ");

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
        while (terminado != null) {
            comando = analisador.pegarComandoConsole();
            if(regraNegocio.processarComando(comando) != null){
                System.out.println(regraNegocio.processarComando(comando));
                System.out.print("> ");
            }else{
                terminado = regraNegocio.processarComando(comando);
            }
        }

        //Retorna mensagem final
        System.out.println("\nObrigado por jogar. Ate mais!\n");
    }

}