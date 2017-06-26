package br.ufla.dcc.ppoo.trabalhofinal.interacaousuario;

import javax.swing.JOptionPane;

public class Jogo{
    private RegraNegocio regraNegocio;

    /**
     * 
     */
    public Jogo(){
        regraNegocio = new RegraNegocio();
    }
    
    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogarTerminal() {            
        System.out.println(regraNegocio.mensagemBoasVindas());
        System.out.println(regraNegocio.descricaoAmbienteAtual());
        System.out.println("> ");

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
        regraNegocio.iniciarConsole();

        //Retorna mensagem final
        System.out.println("\nObrigado por jogar. Ate mais!\n");
    }
    
    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogarInterfaceGrafica() {            
        JOptionPane.showMessageDialog(null, regraNegocio.mensagemBoasVindas());
        JOptionPane.showMessageDialog(null, regraNegocio.descricaoAmbienteAtual());
        
        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
        regraNegocio.iniciarInterfaceGrafica();
        
        //Retorna mensagem final
        JOptionPane.showMessageDialog(null, "Obrigado por jogar. Ate mais!");
    }
}