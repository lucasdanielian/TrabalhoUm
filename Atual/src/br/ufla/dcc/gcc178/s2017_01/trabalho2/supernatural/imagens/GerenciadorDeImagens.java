package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.imagens;

import javax.swing.ImageIcon;

/**
 * Classe que centraliza a utilização das imagens do sistema
 * 
 * @author Paulo Jr. e Julio Alves
 */
public class GerenciadorDeImagens {

    // conjunto de ícones / imagens usadas no sistema.
    // Obs: atributos são públicos por serem constantes.

    /**
     *
     */
    
    public static final ImageIcon SAIR = carregarIcone("sair.png");

    /**
     *
     */
    public static final ImageIcon LOGOUT = carregarIcone("logout.png");

    /**
     *
     */
    public static final ImageIcon ENTRAR = carregarIcone("entrar.png");

    /**
     *
     */
    public static final ImageIcon NOVO = carregarIcone("novo.png");

    /**
     *
     */
    public static final ImageIcon EDITAR = carregarIcone("editar.png");

    /**
     *
     */
    public static final ImageIcon DELETAR = carregarIcone("deletar.png");

    /**
     *
     */
    public static final ImageIcon BANDEIRA_BR = carregarIcone("bandeira-br.png");

    /**
     *
     */
    public static final ImageIcon BANDEIRA_GB = carregarIcone("bandeira-gb.png");

    /**
     *
     */
    public static final ImageIcon CADASTRAR_USUARIO = carregarIcone("cadastrar-usuario.png");

    /**
     *
     */
    public static final ImageIcon OK = carregarIcone("btn-ok.png");

    /**
     *
     */
    public static final ImageIcon CANCELAR = carregarIcone("btn-cancelar.png");

    /**
     *
     */
    public static final ImageIcon SOBRE = carregarIcone("sobre.png");
    
    /**
     *
     */
    public static final ImageIcon DENVER = carregarIcone("Denver.jpg");
    
    /**
     *
     */
    public static final ImageIcon HOUSTON = carregarIcone("Houston.jpg");

    /**
     *
     */
    public static final ImageIcon CASA_BOB = carregarIcone("casaBob.jpg");
    
    /**
     *
     */
    public static final ImageIcon CASA_CAIM = carregarIcone("casaCaim.jpg");
    
    /**
     *
     */
    public static final ImageIcon CASA_WINCHESTER = carregarIcone("casaWinchester.jpg");
    
    /**
     *
     */
    public static final ImageIcon CEU = carregarIcone("ceu.jpg");
    
    /**
     *
     */
    public static final ImageIcon INFERNO = carregarIcone("inferno.png");
    
    /**
     *
     */
    public static final ImageIcon PRINCIPAL = carregarIcone("principal.jpeg");
    
    /**
     *
     */
    public static final ImageIcon PURGATORIO = carregarIcone("purgatorio.jpg");
    
    /**
     *
     */
    public static final ImageIcon MEU_JOGO = carregarIcone("filmes.png");

    /**
     * Retorna um ícone (imagem) a partir do seu nome. Utilizado internamente 
     * acima no carregamento das imagens.
     * 
     * @param caminho Nome da imagem a ser carregada.
     * @return A imagem carregada.
     */
    private static ImageIcon carregarIcone(String caminho) {
        return new ImageIcon(GerenciadorDeImagens.class.getResource(caminho));
    }
}
