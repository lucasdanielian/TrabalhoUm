package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.gui;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.comandos.Analisador;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.comandos.Comando;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.interacaousuario.TelaPrincipal;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.i18n.I18N;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.imagens.GerenciadorDeImagens;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.regranegocio.RegraNegocio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *  Essa eh a classe principal(Para iniciar com interface Grafica) do RegraNegocio "SuperNatural".
 *  "SuperNatural" eh um jogo de aventura muito simples, baseado em texto.
 * Usuarios podem caminhar em um cenario, e precisam consquitar objetos para
 *  comprir a missão que é salvar seu irmão do inferno.
 *  Para isto o mesmo deve realizar a tarefa em um tempo inferior a 30. 
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * Modificado: Valdeci Soares da Silva Junior e Lucas Danielian 
 * @version 2011.07.31 (2017.05.16)
 */
public class TelaJogo {

    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
            
    // componentes da tela
    private JFrame janela;
    private JPanel painelNorte;
    private JPanel painelSul;
    private JPanel painelLeste;
    private JPanel painelOeste;
    private JPanel painelCentral;
    private GridBagConstraints gbc;
    private GridBagLayout layoutNorte;
    private GridBagLayout layoutSul;
    private GridBagLayout layoutLeste;
    private GridBagLayout layoutOeste;
    private GridBagLayout layoutCentral;
    
    //Botao que envia os comandos
    private JButton btnEnviarComando;
    
    //Botoes principais
    private JButton btnSalvarJogo;
    private JButton btnCancelarJogo;
    
    //Botoes para navegacao entre os ambientes
    private JButton btnIrCasaWinchester;
    private JButton btnIrDenver;
    private JButton btnIrHouston;
    private JButton btnIrCasaCaim;
    private JButton btnIrCasaBob;
    private JButton btnIrPortalInferno;
    private JButton btnIrPurgatorio;
    private JButton btnIrCeu;
    
    //Botao que mostra os itens dos ambientes
    private JButton btnItemCartaAmbiente;
    private JButton btnItemPenaAmbiente;
    private JButton btnItemDenteLoboAmbiente;
    private JButton btnItemCabecaVampiroAmbiente;
    private JButton btnItemPortadorAlmasAmbiente;
    
    //Botoes que mostra os itens da mochila
    private JButton btnItemCartaMochila;
    private JButton btnItemPenaMochila;
    private JButton btnItemDenteLoboMochila;
    private JButton btnItemCabecaVampiroMochila;
    private JButton btnItemPortadorAlmasMochila;
    
    //Exibicao de texto
    private JTextArea textoDinamico;
    private JTextArea tituloBotoesItens;
    private JTextArea tituloBotoesAmbientes;
    private JTextArea tituloBotoesPrincipais;
    private JTextArea tituloBotoesVerItens;
    private JTextArea diasCorridos;
    private JTextArea diasRestantes;
    
    //Entradas do usuario
    private JTextField txtEntradaComandos;
    
    //Outros
    private RegraNegocio regraNegocio;
    private Comando comando;
    private Analisador analisador;
    private JScrollPane jScrollPaneSaida;
    private ImageIcon logo;
    private JLabel imagensJogo;
    private JLabel rotuloTxtEntradaComandos;
    private File file;
    private URL resource;
    private String diretorio;

     /**
     * Constrói a tela Meus Filmes guardando a referência da tela principal.
     * 
     * @param telaPrincipal Referência da tela principal.
     */
    public TelaJogo(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    /**
     * Inicializa a tela, construindo seus componentes, configurando os eventos
     * e, ao final, exibe a tela.
     */
    public void inicializar(){
        construirTela();
        configurarEventosTela();
        exibirTela();
    }

    /**
     * Trata o estado inicial da tela
     */
    private void prepararComponentesEstadoInicial() {
        
        //Botoes Principais
        btnSalvarJogo.setEnabled(true);
        btnCancelarJogo.setEnabled(true);
        
        //Botao que envia os comandos
        btnEnviarComando.setEnabled(true);
        
        //Botoes para navegar entre os ambientes
        btnIrCasaWinchester.setEnabled(true);
        btnIrDenver.setEnabled(true);
        btnIrHouston.setEnabled(true);
        btnIrCasaCaim.setEnabled(true);
        btnIrCasaBob.setEnabled(true);
        btnIrPortalInferno.setEnabled(true);
        btnIrPurgatorio.setEnabled(true);
        btnIrCeu.setEnabled(true);
        
        //Botoes Itens Ambientes
        btnItemCartaAmbiente.setEnabled(false);
        btnItemPenaAmbiente.setEnabled(false);
        btnItemDenteLoboAmbiente.setEnabled(false);
        btnItemCabecaVampiroAmbiente.setEnabled(false);
        btnItemPortadorAlmasAmbiente.setEnabled(false);
        
        //Botões Itens mochila
        btnItemCartaMochila.setEnabled(false);
        btnItemPenaMochila.setEnabled(false);
        btnItemDenteLoboMochila.setEnabled(false);
        btnItemCabecaVampiroMochila.setEnabled(false);
        btnItemPortadorAlmasMochila.setEnabled(false);
    }
    
    private void prepararItensAmbientes(String itensDisponiveis){
        if(itensDisponiveis.contains("indisponivel")){
            btnItemDenteLoboAmbiente.setEnabled(false);
            btnItemDenteLoboAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
            btnItemCabecaVampiroAmbiente.setEnabled(false);
            btnItemCabecaVampiroAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
            btnItemCartaAmbiente.setEnabled(false);
            btnItemCartaAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
            btnItemPortadorAlmasAmbiente.setEnabled(false);
            btnItemPortadorAlmasAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
            btnItemPenaAmbiente.setEnabled(false);
            btnItemPenaAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
        }
        else{
            if(itensDisponiveis.contains("Dente")){
                btnItemDenteLoboAmbiente.setEnabled(true);
                btnItemDenteLoboAmbiente.setIcon(GerenciadorDeImagens.OK);
                btnItemCabecaVampiroAmbiente.setEnabled(false);
                btnItemCabecaVampiroAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemCartaAmbiente.setEnabled(false);
                btnItemCartaAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemPortadorAlmasAmbiente.setEnabled(false);
                btnItemPortadorAlmasAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemPenaAmbiente.setEnabled(false);
                btnItemPenaAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
            }

            if(itensDisponiveis.contains("CabecaVampiro")){
                btnItemDenteLoboAmbiente.setEnabled(false);
                btnItemDenteLoboAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemCabecaVampiroAmbiente.setEnabled(true);
                btnItemCabecaVampiroAmbiente.setIcon(GerenciadorDeImagens.OK);
                btnItemCartaAmbiente.setEnabled(false);
                btnItemCartaAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemPortadorAlmasAmbiente.setEnabled(false);
                btnItemPortadorAlmasAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemPenaAmbiente.setEnabled(false);
                btnItemPenaAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
            }

            if(itensDisponiveis.contains("Carta")){
                btnItemDenteLoboAmbiente.setEnabled(false);
                btnItemDenteLoboAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemCabecaVampiroAmbiente.setEnabled(false);
                btnItemCabecaVampiroAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemCartaAmbiente.setEnabled(true);
                btnItemCartaAmbiente.setIcon(GerenciadorDeImagens.OK);
                btnItemPortadorAlmasAmbiente.setEnabled(false);
                btnItemPortadorAlmasAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemPenaAmbiente.setEnabled(false);
                btnItemPenaAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
            }

            if(itensDisponiveis.contains("Almas")){
                btnItemDenteLoboAmbiente.setEnabled(false);
                btnItemDenteLoboAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemCabecaVampiroAmbiente.setEnabled(false);
                btnItemCabecaVampiroAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemCartaAmbiente.setEnabled(false);
                btnItemCartaAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemPortadorAlmasAmbiente.setEnabled(true);
                btnItemPortadorAlmasAmbiente.setIcon(GerenciadorDeImagens.OK);
                btnItemPenaAmbiente.setEnabled(false);
                btnItemPenaAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
            }

            if(itensDisponiveis.contains("Pena")){
                btnItemDenteLoboAmbiente.setEnabled(false);
                btnItemDenteLoboAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemCabecaVampiroAmbiente.setEnabled(false);
                btnItemCabecaVampiroAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemCartaAmbiente.setEnabled(false);
                btnItemCartaAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemPortadorAlmasAmbiente.setEnabled(false);
                btnItemPortadorAlmasAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                btnItemPenaAmbiente.setEnabled(true);
                btnItemPenaAmbiente.setIcon(GerenciadorDeImagens.OK);
            }
        }
    }
    
    private void gameOver(){
        diasCorridos.setText("Dias Corridos: " + regraNegocio.getContador());
        diasRestantes.setText("Dias Restantes: " + regraNegocio.diasRestantes());
        JOptionPane.showMessageDialog(janela, "GAME OVER! Seu tempo estourou"
            + "\nTente Jogar Novamente!");
        janela.dispose();
    }
    
    private void atualizaPainel(){
        diasCorridos.setText("Dias Corridos: " + regraNegocio.getContador());
        diasRestantes.setText("Dias Restantes: " + regraNegocio.diasRestantes());
    }
    
    /**
     * Troca a imgagem atual da tela
     * @param String o endereço da mesma deve está dentro do pacote imagens
     */
    private void adicionarImagem(String diretorio){
        try{
            URL resource = getClass().getResource(diretorio);
            file = new File(resource.toURI());
            logo = new ImageIcon(file.getPath());
            imagensJogo = new JLabel(logo);
            imagensJogo.setPreferredSize(new Dimension(500, 240));
            //Adicão dos botoes Ambientes no painei Oeste
            adicionarComponentePainelNorte(imagensJogo,
                    GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL,
                    0, 5, 1, 1);
        } catch (URISyntaxException | NullPointerException ex){
            JOptionPane.showMessageDialog(janela, "Imagem: " + diretorio
                    + " Nao encontrada");
            JOptionPane.showMessageDialog(janela, "O jogo continuará sem imagem. \n"
                    + "Se o problema persistir contate o administrador do sistema");
        }
    }
    
    /**
     * Troca a imgagem atual da tela
     * @param String o endereço da mesma deve está dentro do pacote imagens
     */
    private void trocaImagem(String diretorio){
        try{
           URL resource = getClass().getResource(diretorio);
            file = new File(resource.toURI());
            logo = new ImageIcon(file.getPath());
            imagensJogo.setIcon(new ImageIcon(file.getPath())); 
        } catch (URISyntaxException | NullPointerException ex){
            JOptionPane.showMessageDialog(painelNorte, "Imagem: " + diretorio
                    + " Nao encontrada");
            JOptionPane.showMessageDialog(painelNorte, "O jogo continuará sem imagem. \n"
                    + "Se o problema persistir contate o administrador do sistema");
        }
    }
    
    /**
     * Adiciona um componente à tela.
     */
    private void adicionarComponentePainelNorte(Component c,
            int anchor, int fill, int linha,
            int coluna, int largura, int altura) {
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.gridy = linha;
        gbc.gridx = coluna;
        gbc.gridwidth = largura;
        gbc.gridheight = altura;
        gbc.insets = new Insets(5, 5, 5, 5);
        layoutNorte.setConstraints(c, gbc);
        painelNorte.add(c);
    }
    
    /**
     * Adiciona um componente à tela.
     */
    private void adicionarComponentePainelSul(Component c,
            int anchor, int fill, int linha,
            int coluna, int largura, int altura) {
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.gridy = linha;
        gbc.gridx = coluna;
        gbc.gridwidth = largura;
        gbc.gridheight = altura;
        gbc.insets = new Insets(5, 5, 5, 5);
        layoutSul.setConstraints(c, gbc);
        painelSul.add(c);
    }
    
    /**
     * Adiciona um componente à tela.
     */
    private void adicionarComponentePainelLeste(Component c,
            int anchor, int fill, int linha,
            int coluna, int largura, int altura) {
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.gridy = linha;
        gbc.gridx = coluna;
        gbc.gridwidth = largura;
        gbc.gridheight = altura;
        gbc.insets = new Insets(5, 5, 5, 5);
        layoutLeste.setConstraints(c, gbc);
        painelLeste.add(c);
    }
    
    /**
     * Adiciona um componente à tela.
     */
    private void adicionarComponentePainelOeste(Component c, int anchor, 
            int fill, int linha, int coluna, int largura, int altura) {
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.gridy = linha;
        gbc.gridx = coluna;
        gbc.gridwidth = largura;
        gbc.gridheight = altura;
        gbc.insets = new Insets(5, 5, 5, 5);
        layoutOeste.setConstraints(c, gbc);
        painelOeste.add(c);
    }
    
    /**
     * Adiciona um componente à tela.
     */
    private void adicionarComponentePainelCentral(Component c,
            int anchor, int fill, int linha,
            int coluna, int largura, int altura) {
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.gridy = linha;
        gbc.gridx = coluna;
        gbc.gridwidth = largura;
        gbc.gridheight = altura;
        gbc.insets = new Insets(5, 5, 5, 5);
        layoutCentral.setConstraints(c, gbc);
        painelCentral.add(c);
    }
    
    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentesTelaJogo(){
        diretorio = "/br/ufla/dcc/ppoo/trabalhofinal/imagens/principal.jpeg";
        adicionarImagem(diretorio);

        //Gerenciador do Jogo
        regraNegocio = new RegraNegocio();
        
        //Analisador de comandos do jogo
        analisador = new Analisador();

        //Imprime os dias corridos do jagador na tela
        diasCorridos = new JTextArea("Dias Corridos: " + regraNegocio.getContador());
        diasCorridos.setFont(new Font("Serif", Font.ITALIC, 18));
        diasCorridos.setBackground(Color.GRAY);
        diasCorridos.setForeground(Color.WHITE);
        diasCorridos.setEditable(false);
        //Adiciona dias corridos na tela
        adicionarComponentePainelNorte(diasCorridos,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL,
                0, 1, 2, 2);
        
        //Imprime os dias restantes do jagador na tela
        diasRestantes = new JTextArea("Dias Restantes: " + regraNegocio.diasRestantes());
        diasRestantes.setFont(new Font("Serif", Font.ITALIC, 18));
        diasRestantes.setBackground(Color.GRAY);
        diasRestantes.setForeground(Color.WHITE);
        diasRestantes.setEditable(false);        
        //Adiciona dias restantes na tela
        adicionarComponentePainelNorte(diasRestantes,
                GridBagConstraints.EAST,
                GridBagConstraints.HORIZONTAL,
                0, 9, 2, 2);
        
        //Imprime os textos da regra de negocios
        textoDinamico = new JTextArea(regraNegocio.mensagemBoasVindas());
        textoDinamico.setEditable(false);
        
        //Adiciona barra de rolagem ao texto
        jScrollPaneSaida = new JScrollPane(textoDinamico);
        
        //seta o tamanho do campo de texto
        jScrollPaneSaida.setPreferredSize(new Dimension(600, 400));
        
        //Adiciona o JScrollPane do texto dinamico da tela
        adicionarComponentePainelCentral(jScrollPaneSaida,
                GridBagConstraints.NORTH,
                GridBagConstraints.NONE,
                1, 0, 1, 1);
        
        //Recebe a entrada do usuario na tela
        txtEntradaComandos = new JTextField("Comandos Devem Ser Digitados Aqui:");
        txtEntradaComandos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                            txtEntradaComandos.setText("");
			}
		});
        //Adiciona Entrada de comandos na tela
        adicionarComponentePainelCentral(txtEntradaComandos,
                GridBagConstraints.SOUTH,
                GridBagConstraints.NONE,
                2, 0, 1, 1);
        
        //Botao que envia um comando
        btnEnviarComando = new JButton(I18N.obterBotaoEnviar(),
                GerenciadorDeImagens.OK);
        //Adicão do botao Enviar comando no painei Oeste
        adicionarComponentePainelCentral(btnEnviarComando,
                GridBagConstraints.PAGE_END,
                GridBagConstraints.VERTICAL,
                4, 0, 1, 1);
        
        //Imprime o texto na tela
        tituloBotoesPrincipais = new JTextArea("BOTOES PRINCIPAIS");
        tituloBotoesPrincipais.setFont(new Font("Serif", Font.ITALIC, 18));
        tituloBotoesPrincipais.setBackground(Color.GRAY);
        tituloBotoesPrincipais.setForeground(Color.WHITE);
        tituloBotoesPrincipais.setEditable(false);
        //Adiciona o texto na tela
        adicionarComponentePainelOeste(tituloBotoesPrincipais,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                0, 0, 1, 1);
        
        //Botao que salva o jogo em persistencia
        btnSalvarJogo = new JButton(I18N.obterBotaoSalvar(),
                GerenciadorDeImagens.OK);
        //Adicão dos botao Salvar jogo no painei Oeste
        adicionarComponentePainelOeste(btnSalvarJogo,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                2, 0, 1, 1);

        //Botao que encerra o jogo
        btnCancelarJogo = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);
        //Adicão dos botoes Ambientes no painei Oeste
        adicionarComponentePainelOeste(btnCancelarJogo,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                4, 0, 1, 1);
        
        //Imprime o texto na tela
        tituloBotoesAmbientes = new JTextArea(" NAVEGACAO ENTRE\n       AMBIENTES ");
        tituloBotoesAmbientes.setFont(new Font("Serif", Font.ITALIC, 18));
        tituloBotoesAmbientes.setBackground(Color.GRAY);
        tituloBotoesAmbientes.setForeground(Color.WHITE);
        tituloBotoesAmbientes.setEditable(false);
        //Adiciona o texto na tela
        adicionarComponentePainelOeste(tituloBotoesAmbientes,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                8, 0, 2, 2);

        //Botoes dos Ambientes
        btnIrCasaWinchester = new JButton(I18N.obterBotaoWinchester(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes principais no painei Oeste
        adicionarComponentePainelOeste(btnIrCasaWinchester,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                10, 0, 1, 1);
        
        btnIrDenver = new JButton(I18N.obterBotaoDenver(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelOeste(btnIrDenver,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                12, 0, 1, 1);
        
        btnIrHouston = new JButton(I18N.obterBotaoHouston(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes Ambientes no painei Oeste
        adicionarComponentePainelOeste(btnIrHouston,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                14, 0, 1, 1);
        
        btnIrCasaCaim = new JButton(I18N.obterBotaoCasaCaim(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes principais no painei Oeste
        adicionarComponentePainelOeste(btnIrCasaCaim,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                16, 0, 1, 1);
        
        btnIrCasaBob = new JButton(I18N.obterBotaoCasaBob(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelOeste(btnIrCasaBob,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                18, 0, 1, 1);
        
        btnIrPortalInferno = new JButton(I18N.obterBotaoInferno(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes Ambientes no painei Oeste
        adicionarComponentePainelOeste(btnIrPortalInferno,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                20, 0, 1, 1);
        
        btnIrPurgatorio = new JButton(I18N.obterBotaoPurgatorio(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes principais no painei Oeste
        adicionarComponentePainelOeste(btnIrPurgatorio,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                22, 0, 1, 1);
        
        btnIrCeu = new JButton(I18N.obterBotaoCeu(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelOeste(btnIrCeu,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                24, 0, 1, 1);
        
        //Imprime o titulo dos botões de itens na tela
        tituloBotoesItens = new JTextArea(" ITENS AMBIENTES ");
        tituloBotoesItens.setFont(new Font("Serif", Font.ITALIC, 18));
        tituloBotoesItens.setBackground(Color.GRAY);
        tituloBotoesItens.setForeground(Color.WHITE);
        tituloBotoesItens.setEditable(false);
        //Adiciona o texto na tela
        adicionarComponentePainelLeste(tituloBotoesItens,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                0, 0, 2, 2);
        
        //Botoes de Manipulação de itens do ambiente
        btnItemCartaAmbiente = new JButton(I18N.obterBotaoItenCarta(),
                GerenciadorDeImagens.CANCELAR);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemCartaAmbiente,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                2, 0, 1, 1);
        
        btnItemPenaAmbiente = new JButton(I18N.obterBotaoItenPena(),
            GerenciadorDeImagens.CANCELAR);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemPenaAmbiente,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                4, 0, 1, 1);
        
        btnItemDenteLoboAmbiente = new JButton(I18N.obterBotaoItenDenteLobo(),
                GerenciadorDeImagens.CANCELAR);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemDenteLoboAmbiente,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                6, 0, 1, 1);
        
        btnItemCabecaVampiroAmbiente = new JButton(I18N.obterBotaoItenCabecaVampiro(),
                GerenciadorDeImagens.CANCELAR);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemCabecaVampiroAmbiente,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                8, 0, 1, 1);
        
        btnItemPortadorAlmasAmbiente = new JButton(I18N.obterBotaoItemPortadorAlmas(),
                GerenciadorDeImagens.CANCELAR);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemPortadorAlmasAmbiente,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                10, 0, 1, 1);

        //Imprime o titulo dos botoes de itens da mochila na tela
        tituloBotoesVerItens = new JTextArea(" ITENS MOCHILA ");
        tituloBotoesVerItens.setFont(new Font("Serif", Font.ITALIC, 18));
        tituloBotoesVerItens.setBackground(Color.GRAY);
        tituloBotoesVerItens.setForeground(Color.WHITE);
        tituloBotoesVerItens.setEditable(false);
        //Adiciona o texto na tela
        adicionarComponentePainelLeste(tituloBotoesVerItens,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                20, 0, 2, 2);
        
        //Botoes de Manipulação de itens
        btnItemCartaMochila = new JButton(I18N.obterBotaoItenCarta(),
                GerenciadorDeImagens.CANCELAR);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemCartaMochila,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                22, 0, 1, 1);
        
        btnItemPenaMochila = new JButton(I18N.obterBotaoItenPena(),
            GerenciadorDeImagens.CANCELAR);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemPenaMochila,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                24, 0, 1, 1);
        
        btnItemDenteLoboMochila = new JButton(I18N.obterBotaoItenDenteLobo(),
                GerenciadorDeImagens.CANCELAR);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemDenteLoboMochila,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                26, 0, 1, 1);
        
        btnItemCabecaVampiroMochila = new JButton(I18N.obterBotaoItenCabecaVampiro(),
                GerenciadorDeImagens.CANCELAR);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemCabecaVampiroMochila,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                28, 0, 1, 1);
        
        btnItemPortadorAlmasMochila = new JButton(I18N.obterBotaoItemPortadorAlmas(),
                GerenciadorDeImagens.CANCELAR);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemPortadorAlmasMochila,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                30, 0, 1, 1);
        
        prepararComponentesEstadoInicial();        
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        
        btnIrCeu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String validaAmbiente;
                comando = analisador.pegarComando("ir Ceu");
                validaAmbiente = regraNegocio.processarComando(comando);
                if (validaAmbiente.contains("Nao ha passagem!")){
                    textoDinamico.setText("\nNao ha passagem!\n");
                }else{
                    if(regraNegocio.diasRestantes()==0){
                        gameOver();
                    }else{
                        String itensDisponiveis = regraNegocio.verificaDisponibilidadeItemAmbiente();
                        prepararItensAmbientes(itensDisponiveis);
                        textoDinamico.setText(validaAmbiente);
                        trocaImagem(regraNegocio.imagemAmbienteAtual());
                        atualizaPainel();
                    }
                }
            }
        });
            
        btnIrCasaWinchester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String validaAmbiente;
                comando = analisador.pegarComando("ir CasaWinchester");
                validaAmbiente = regraNegocio.processarComando(comando);
                if (validaAmbiente.indexOf("Nao ha passagem!")>=0){
                    textoDinamico.setText("\nNao ha passagem!\n");
                }else{
                    if(regraNegocio.diasRestantes()==0){
                        gameOver();
                    }else{
                        Comando auxComando = analisador.pegarComando("analisar armario");
                        String itensDisponiveis = regraNegocio.processarComando(auxComando);
                        if(itensDisponiveis == null || itensDisponiveis.contains("Não existem itens a serem mostrados")){
                            prepararItensAmbientes("indisponivel");
                        }else{
                            prepararItensAmbientes(itensDisponiveis);
                        }
                        textoDinamico.setText(validaAmbiente);
                        trocaImagem(regraNegocio.imagemAmbienteAtual());
                        atualizaPainel();
                    }
                }
            }
        });
        
        btnIrDenver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String validaAmbiente;
                comando = analisador.pegarComando("ir Denver");
                validaAmbiente = regraNegocio.processarComando(comando);
                if (validaAmbiente.indexOf("Nao ha passagem!")>=0){
                    textoDinamico.setText("\nNao ha passagem!\n");
                }else{
                    if(regraNegocio.diasRestantes()==0){
                        gameOver();
                    }else{
                        String itensDisponiveis = regraNegocio.verificaDisponibilidadeItemAmbiente();
                        prepararItensAmbientes(itensDisponiveis);
                        textoDinamico.setText(validaAmbiente);
                        trocaImagem(regraNegocio.imagemAmbienteAtual());
                        atualizaPainel();
                    }
                }
            }
        });
        
        btnIrHouston.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String validaAmbiente;
                comando = analisador.pegarComando("ir Houston");
                validaAmbiente = regraNegocio.processarComando(comando);
                if (validaAmbiente.contains("Nao ha passagem!")){
                    textoDinamico.setText("\nNao ha passagem!\n");
                }else{
                    if(regraNegocio.diasRestantes()==0){
                        gameOver();
                    }else{
                        String itensDisponiveis = regraNegocio.verificaDisponibilidadeItemAmbiente();
                        prepararItensAmbientes(itensDisponiveis);
                        textoDinamico.setText(validaAmbiente);
                        trocaImagem(regraNegocio.imagemAmbienteAtual());
                        atualizaPainel();
                    }
                }
            }
        });
        
        btnIrPortalInferno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String validaAmbiente;
                comando = analisador.pegarComando("ir PortalInferno");
                validaAmbiente = regraNegocio.processarComando(comando);
                if (validaAmbiente.contains("Nao ha passagem!")){
                    textoDinamico.setText("\nNao ha passagem!\n");
                }else{
                    if(regraNegocio.diasRestantes()==0){
                        gameOver();
                    }else{
                        String itensDisponiveis = regraNegocio.verificaDisponibilidadeItemAmbiente();
                        prepararItensAmbientes(itensDisponiveis);
                        textoDinamico.setText(validaAmbiente);
                        trocaImagem(regraNegocio.imagemAmbienteAtual());
                        atualizaPainel();
                    }
                }
            }
        });
        
        btnIrPurgatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String validaAmbiente;
                comando = analisador.pegarComando("ir Purgatorio");
                validaAmbiente = regraNegocio.processarComando(comando);
                if (validaAmbiente.indexOf("Nao ha passagem!")>=0){
                    textoDinamico.setText("\nNao ha passagem!\n");
                }else{
                    if(regraNegocio.diasRestantes()==0){
                        gameOver();
                    }else{
                        String itensDisponiveis = regraNegocio.verificaDisponibilidadeItemAmbiente();
                        prepararItensAmbientes(itensDisponiveis);
                        textoDinamico.setText(validaAmbiente);
                        trocaImagem(regraNegocio.imagemAmbienteAtual());
                        atualizaPainel();
                    }
                }
            }
        });
        
        btnIrCasaCaim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String validaAmbiente;
                comando = analisador.pegarComando("ir CasaCaim");
                validaAmbiente = regraNegocio.processarComando(comando);
                if (validaAmbiente.indexOf("Nao ha passagem!")>=0){
                    textoDinamico.setText("\nNao ha passagem!\n");
                }else{
                    if(regraNegocio.diasRestantes()==0){
                        gameOver();
                    }else{
                        String itensDisponiveis = regraNegocio.verificaDisponibilidadeItemAmbiente();
                        prepararItensAmbientes(itensDisponiveis);
                        textoDinamico.setText(validaAmbiente);
                        trocaImagem(regraNegocio.imagemAmbienteAtual());
                        atualizaPainel();
                    }
                }
            }
        });
        
        btnIrCasaBob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String validaAmbiente;
                comando = analisador.pegarComando("ir CasaBob");
                validaAmbiente = regraNegocio.processarComando(comando);
                if (validaAmbiente.indexOf("Nao ha passagem!")>=0){
                    textoDinamico.setText("\nNao ha passagem!\n");
                }else{
                    if(regraNegocio.diasRestantes()==0){
                        gameOver();
                    }else{
                        String itensDisponiveis = regraNegocio.verificaDisponibilidadeItemAmbiente();
                        prepararItensAmbientes(itensDisponiveis);
                        textoDinamico.setText(validaAmbiente);
                        trocaImagem(regraNegocio.imagemAmbienteAtual());
                        atualizaPainel();
                    }
                }
            }
        });
        
        btnItemCabecaVampiroMochila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoExibicao;
                comando = analisador.pegarComando("guardar CabecaVampiro");
                textoExibicao = regraNegocio.processarComando(comando);
                if(textoExibicao.contains("Item: CabecaVampiro guardado com sucesso")){
                    textoDinamico.setText(textoExibicao);
                    btnItemCabecaVampiroMochila.setEnabled(false);
                    btnItemCabecaVampiroMochila.setIcon(GerenciadorDeImagens.CANCELAR);
                    btnItemCabecaVampiroAmbiente.setEnabled(true);
                    btnItemCabecaVampiroAmbiente.setIcon(GerenciadorDeImagens.OK);
                }else{
                    textoDinamico.setText(textoExibicao);
                }
            }
        });
        
        btnItemCartaMochila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoExibicao;
                comando = analisador.pegarComando("guardar Carta");
                textoExibicao = regraNegocio.processarComando(comando);
                if(textoExibicao.contains("Item: Carta guardado com sucesso")){
                    textoDinamico.setText(textoExibicao);
                    btnItemCartaMochila.setEnabled(false);
                    btnItemCartaMochila.setIcon(GerenciadorDeImagens.CANCELAR);
                    btnItemCartaAmbiente.setIcon(GerenciadorDeImagens.OK);
                    btnItemCartaAmbiente.setEnabled(true);
                }else{
                    textoDinamico.setText(textoExibicao);
                }
            }
        });
        
        btnItemDenteLoboMochila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoExibicao;
                comando = analisador.pegarComando("guardar DenteLobo");
                textoExibicao = regraNegocio.processarComando(comando);
                if(textoExibicao.contains("Item: DenteLobo guardado com sucesso")){
                    textoDinamico.setText(textoExibicao);
                    btnItemDenteLoboMochila.setEnabled(false);
                    btnItemDenteLoboMochila.setIcon(GerenciadorDeImagens.CANCELAR);
                    btnItemDenteLoboAmbiente.setEnabled(true);
                    btnItemDenteLoboAmbiente.setIcon(GerenciadorDeImagens.OK);
                    
                }else{
                    textoDinamico.setText(textoExibicao);
                }
            }
        });
        
        btnItemPenaMochila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoExibicao;
                comando = analisador.pegarComando("guardar Pena");
                textoExibicao = regraNegocio.processarComando(comando);
                if(textoExibicao.contains("Item: Pena guardado com sucesso")){
                    textoDinamico.setText(textoExibicao);
                    btnItemPenaMochila.setEnabled(false);
                    btnItemPenaMochila.setIcon(GerenciadorDeImagens.CANCELAR);
                    btnItemPenaAmbiente.setEnabled(true);
                    btnItemPenaAmbiente.setIcon(GerenciadorDeImagens.OK);
                }else{
                    textoDinamico.setText(textoExibicao);
                }
            }
        });
        
        btnItemPortadorAlmasMochila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoExibicao;
                comando = analisador.pegarComando("guardar PortadorAlmas");
                textoExibicao = regraNegocio.processarComando(comando);
                if(textoExibicao.contains("Item: PortadorAlmas guardado com sucesso")){
                    textoDinamico.setText(textoExibicao);
                    btnItemPortadorAlmasMochila.setEnabled(false);
                    btnItemPortadorAlmasMochila.setIcon(GerenciadorDeImagens.CANCELAR);
                    btnItemPortadorAlmasAmbiente.setEnabled(true);
                    btnItemPortadorAlmasAmbiente.setIcon(GerenciadorDeImagens.OK);
                }else{
                    textoDinamico.setText(textoExibicao);
                }
            }
        });
        
        btnItemCabecaVampiroAmbiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComando("pegar CabecaVampiro");
                String item = regraNegocio.processarComando(comando);
                textoDinamico.setText(item);
                if(item.contains("item coletado")||item.contains("coletado com sucesso")){
                    btnItemCabecaVampiroAmbiente.setEnabled(false);
                    btnItemCabecaVampiroAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                    btnItemCabecaVampiroMochila.setEnabled(true);
                    btnItemCabecaVampiroMochila.setIcon(GerenciadorDeImagens.OK);
                }
            }
        });
        
        btnItemCartaAmbiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComando("pegar Carta");
                String item = regraNegocio.processarComando(comando);
                textoDinamico.setText(item);
                if(item.contains("item coletado")||item.contains("coletado com sucesso")){
                    btnItemCartaAmbiente.setEnabled(false);
                    btnItemCartaAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                    btnItemCartaMochila.setEnabled(true);
                    btnItemCartaMochila.setIcon(GerenciadorDeImagens.OK);
                }
            }
        });
        
        btnItemDenteLoboAmbiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComando("pegar DenteLoboAmbiente");
                String item = regraNegocio.processarComando(comando);
                textoDinamico.setText(item);
                if(item.contains("item coletado")||item.contains("coletado com sucesso")){
                    btnItemDenteLoboAmbiente.setEnabled(false);
                    btnItemDenteLoboAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                    btnItemDenteLoboMochila.setEnabled(true);
                    btnItemDenteLoboMochila.setIcon(GerenciadorDeImagens.OK);
                }
            }
        });
        
        btnItemPenaAmbiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComando("pegar Pena");
                String item = regraNegocio.processarComando(comando);
                textoDinamico.setText(item);
                if(item.contains("item coletado")||item.contains("coletado com sucesso")){
                    btnItemPenaAmbiente.setEnabled(false);
                    btnItemPenaAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                    btnItemPenaMochila.setEnabled(true);
                    btnItemPenaMochila.setIcon(GerenciadorDeImagens.OK);
                }
            }
        });
        
        btnItemPortadorAlmasAmbiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComando("pegar PortadorAlmas");
                String item = regraNegocio.processarComando(comando);
                textoDinamico.setText(item);
                if(item.contains("item coletado")||item.contains("coletado com sucesso")){
                    btnItemPortadorAlmasAmbiente.setEnabled(false);
                    btnItemPortadorAlmasAmbiente.setIcon(GerenciadorDeImagens.CANCELAR);
                    btnItemPortadorAlmasMochila.setEnabled(true);
                    btnItemPortadorAlmasMochila.setIcon(GerenciadorDeImagens.OK);
                }
            }
        });
        
        btnEnviarComando.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String validaTexto = txtEntradaComandos.getText();
                if(validaTexto.equals("sair")){
                    janela.dispose();
                }else{
                    comando = analisador.pegarComando(validaTexto);
                    textoDinamico.setText(regraNegocio.processarComando(comando));
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                    txtEntradaComandos.setText("Entrada de Comandos:");
                }
            }
        });
        
        btnCancelarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
            }
        });

        btnSalvarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(janela, "Persistencia ainda nao foi implementada, Aguarde pela nova versao");
            }
        });
    }

    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTela(){
        
        //JFrame
        janela = new JFrame("Super Natural : Morte Subita");
        janela.setTitle(I18N.obterTituloTelaMeuJogo());
        janela.setLayout( new BorderLayout());
        
        //JPanel
        painelNorte = new JPanel();
        painelSul = new JPanel();
        painelLeste = new JPanel();
        painelCentral = new JPanel();
        painelOeste = new JPanel();
        
        //GridBagLayout
        gbc = new GridBagConstraints();
        layoutNorte = new GridBagLayout();
        layoutSul = new GridBagLayout();
        layoutLeste = new GridBagLayout();
        layoutOeste = new GridBagLayout();
        layoutCentral = new GridBagLayout();
        
        //Alterando Layout Padrão
        painelOeste.setLayout(layoutOeste);
        painelCentral.setLayout(layoutCentral);
        painelNorte.setLayout(layoutNorte);
        painelSul.setLayout(layoutSul);
        painelLeste.setLayout(layoutLeste);
        
        //Configurando Layout dos paineis
        janela.add(painelNorte, BorderLayout.NORTH);
        janela.add(painelSul, BorderLayout.SOUTH);
        janela.add(painelLeste, BorderLayout.EAST);
        janela.add(painelOeste, BorderLayout.WEST);
        janela.add(painelCentral, BorderLayout.CENTER);
        
        //
        adicionarComponentesTelaJogo();
        janela.pack();
        
    }

    /**
     * Exibe a tela.
     */
    private void exibirTela() {
        janela.setLocationRelativeTo(telaPrincipal.obterJanela());
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
        janela.setResizable(false);
    }
}