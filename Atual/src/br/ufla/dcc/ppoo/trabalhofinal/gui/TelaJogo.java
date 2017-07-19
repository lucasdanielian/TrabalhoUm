package br.ufla.dcc.ppoo.trabalhofinal.gui;

import br.ufla.dcc.ppoo.trabalhofinal.comandos.Analisador;
import br.ufla.dcc.ppoo.trabalhofinal.comandos.Comando;
import br.ufla.dcc.ppoo.trabalhofinal.interacaousuario.TelaPrincipal;
import br.ufla.dcc.ppoo.trabalhofinal.i18n.I18N;
import br.ufla.dcc.ppoo.trabalhofinal.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.trabalhofinal.regranegocio.RegraNegocio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.BoxLayout;
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
  Usuarios podem caminhar em um cenario, e precisam consquitar objetos para
  comprir a missão que é salvar seu irmão do inferno.
  Para isto o mesmo deve realizar a tarefa em um tempo inferior a 30. 
 
  Para iniciar esse jogo, crie uma instancia dessa classe e chame o metodo
  "iniciar".
 
  Essa classe principal cria e inicializa todas as outras: ela cria os
  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
  executa os comandos que o analisador retorna.
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
    
    //Botao que mostra os itens do armario na casa Winchester
    private JButton btnVerItensArmario;
    
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
        
        //Botao que mostra os itens contidos no armario da casaWinchester
        btnVerItensArmario.setEnabled(true);
        
        //Botoes Itens Ambientes
        btnItemCartaAmbiente.setEnabled(true);
        btnItemPenaAmbiente.setEnabled(true);
        btnItemDenteLoboAmbiente.setEnabled(true);
        btnItemCabecaVampiroAmbiente.setEnabled(true);
        btnItemPortadorAlmasAmbiente.setEnabled(true);
        
        //Botões Itens mochila
        btnItemCartaMochila.setEnabled(false);
        btnItemPenaMochila.setEnabled(false);
        btnItemDenteLoboMochila.setEnabled(false);
        btnItemCabecaVampiroMochila.setEnabled(false);
        btnItemPortadorAlmasMochila.setEnabled(false);
    }
    
    /**
     * Trata o estado inicial do ambiente Casa Bob
     */
    private void prepararComponentesEstadoInicialCasaBob() {

        btnSalvarJogo.setEnabled(true);
        btnCancelarJogo.setEnabled(true);
        btnEnviarComando.setEnabled(true);
        btnIrCasaWinchester.setEnabled(true);
        btnIrDenver.setEnabled(true);
        btnIrHouston.setEnabled(true);
        btnIrCasaCaim.setEnabled(true);
        btnIrCasaBob.setEnabled(true);
        btnIrPortalInferno.setEnabled(true);
        btnIrPurgatorio.setEnabled(true);
        btnIrCeu.setEnabled(true);
        btnVerItensArmario.setEnabled(false);
        btnItemCartaAmbiente.setEnabled(false);
        btnItemPenaAmbiente.setEnabled(false);
        btnItemDenteLoboAmbiente.setEnabled(false);
        btnItemCabecaVampiroAmbiente.setEnabled(false);
        btnItemPortadorAlmasAmbiente.setEnabled(false);
    }
    
    /**
     * Trata o estado inicial do ambiente Casa Caim
     */
    private void prepararComponentesEstadoInicialCasaCaim() {

        btnSalvarJogo.setEnabled(true);
        btnCancelarJogo.setEnabled(true);
        btnEnviarComando.setEnabled(true);
        btnIrCasaWinchester.setEnabled(true);
        btnIrDenver.setEnabled(true);
        btnIrHouston.setEnabled(true);
        btnIrCasaCaim.setEnabled(true);
        btnIrCasaBob.setEnabled(true);
        btnIrPortalInferno.setEnabled(true);
        btnIrPurgatorio.setEnabled(true);
        btnIrCeu.setEnabled(true);
        btnVerItensArmario.setEnabled(false);
        btnItemCartaAmbiente.setEnabled(false);
        btnItemPenaAmbiente.setEnabled(false);
        btnItemDenteLoboAmbiente.setEnabled(false);
        btnItemCabecaVampiroAmbiente.setEnabled(false);
        btnItemPortadorAlmasAmbiente.setEnabled(false);
    }
    
    /**
     * Trata o estado inicial do ambiente Casa Winchester
     */
    private void prepararComponentesEstadoInicialCasaWinchester() {

        btnSalvarJogo.setEnabled(true);
        btnCancelarJogo.setEnabled(true);
        btnEnviarComando.setEnabled(true);
        btnIrCasaWinchester.setEnabled(true);
        btnIrDenver.setEnabled(true);
        btnIrHouston.setEnabled(true);
        btnIrCasaCaim.setEnabled(true);
        btnIrCasaBob.setEnabled(true);
        btnIrPortalInferno.setEnabled(true);
        btnIrPurgatorio.setEnabled(true);
        btnIrCeu.setEnabled(true);
        btnVerItensArmario.setEnabled(true);
        btnItemCartaAmbiente.setEnabled(true);
        btnItemPenaAmbiente.setEnabled(true);
        btnItemDenteLoboAmbiente.setEnabled(true);
        btnItemCabecaVampiroAmbiente.setEnabled(true);
        btnItemPortadorAlmasAmbiente.setEnabled(true);
    }
    
    /**
     * Trata o estado inicial do ambiente CasaBob
     */
    private void prepararComponentesEstadoInicialCeu() {

        btnSalvarJogo.setEnabled(true);
        btnCancelarJogo.setEnabled(true);
        btnEnviarComando.setEnabled(true);
        btnIrCasaWinchester.setEnabled(true);
        btnIrDenver.setEnabled(true);
        btnIrHouston.setEnabled(true);
        btnIrCasaCaim.setEnabled(true);
        btnIrCasaBob.setEnabled(true);
        btnIrPortalInferno.setEnabled(true);
        btnIrPurgatorio.setEnabled(true);
        btnIrCeu.setEnabled(true);
        btnVerItensArmario.setEnabled(false);
        btnItemCartaAmbiente.setEnabled(false);
        btnItemPenaAmbiente.setEnabled(false);
        btnItemDenteLoboAmbiente.setEnabled(false);
        btnItemCabecaVampiroAmbiente.setEnabled(false);
        btnItemPortadorAlmasAmbiente.setEnabled(false);
    }
    
    /**
     * Trata o estado inicial do ambiente CasaBob
     */
    private void prepararComponentesEstadoInicialDenver() {

        btnSalvarJogo.setEnabled(true);
        btnCancelarJogo.setEnabled(true);
        btnEnviarComando.setEnabled(true);
        btnIrCasaWinchester.setEnabled(true);
        btnIrDenver.setEnabled(true);
        btnIrHouston.setEnabled(true);
        btnIrCasaCaim.setEnabled(true);
        btnIrCasaBob.setEnabled(true);
        btnIrPortalInferno.setEnabled(true);
        btnIrPurgatorio.setEnabled(true);
        btnIrCeu.setEnabled(true);
        btnVerItensArmario.setEnabled(false);
        btnItemCartaAmbiente.setEnabled(false);
        btnItemPenaAmbiente.setEnabled(false);
        btnItemDenteLoboAmbiente.setEnabled(false);
        btnItemCabecaVampiroAmbiente.setEnabled(false);
        btnItemPortadorAlmasAmbiente.setEnabled(false);
    }
    
    /**
     * Trata o estado inicial do ambiente CasaBob
     */
    private void prepararComponentesEstadoInicialHouston() {

        btnSalvarJogo.setEnabled(true);
        btnCancelarJogo.setEnabled(true);
        btnEnviarComando.setEnabled(true);
        btnIrCasaWinchester.setEnabled(true);
        btnIrDenver.setEnabled(true);
        btnIrHouston.setEnabled(true);
        btnIrCasaCaim.setEnabled(true);
        btnIrCasaBob.setEnabled(true);
        btnIrPortalInferno.setEnabled(true);
        btnIrPurgatorio.setEnabled(true);
        btnIrCeu.setEnabled(true);
        btnVerItensArmario.setEnabled(false);
        btnItemCartaAmbiente.setEnabled(false);
        btnItemPenaAmbiente.setEnabled(false);
        btnItemDenteLoboAmbiente.setEnabled(false);
        btnItemCabecaVampiroAmbiente.setEnabled(false);
        btnItemPortadorAlmasAmbiente.setEnabled(false);
    }

    /**
     * Trata o estado inicial do ambiente CasaBob
     */
    private void prepararComponentesEstadoInicialPortalInferno() {

        btnSalvarJogo.setEnabled(true);
        btnCancelarJogo.setEnabled(true);
        btnEnviarComando.setEnabled(true);
        btnIrCasaWinchester.setEnabled(true);
        btnIrDenver.setEnabled(true);
        btnIrHouston.setEnabled(true);
        btnIrCasaCaim.setEnabled(true);
        btnIrCasaBob.setEnabled(true);
        btnIrPortalInferno.setEnabled(true);
        btnIrPurgatorio.setEnabled(true);
        btnIrCeu.setEnabled(true);
        btnVerItensArmario.setEnabled(false);
        btnItemCartaAmbiente.setEnabled(false);
        btnItemPenaAmbiente.setEnabled(false);
        btnItemDenteLoboAmbiente.setEnabled(false);
        btnItemCabecaVampiroAmbiente.setEnabled(false);
        btnItemPortadorAlmasAmbiente.setEnabled(false);
    }
    
    /**
     * Trata o estado inicial do ambiente CasaBob
     */
    private void prepararComponentesEstadoInicialPurgatorio() {

        btnSalvarJogo.setEnabled(true);
        btnCancelarJogo.setEnabled(true);
        btnEnviarComando.setEnabled(true);
        btnIrCasaWinchester.setEnabled(true);
        btnIrDenver.setEnabled(true);
        btnIrHouston.setEnabled(true);
        btnIrCasaCaim.setEnabled(true);
        btnIrCasaBob.setEnabled(true);
        btnIrPortalInferno.setEnabled(true);
        btnIrPurgatorio.setEnabled(true);
        btnIrCeu.setEnabled(true);
        btnVerItensArmario.setEnabled(false);
        btnItemCartaAmbiente.setEnabled(false);
        btnItemPenaAmbiente.setEnabled(false);
        btnItemDenteLoboAmbiente.setEnabled(false);
        btnItemCabecaVampiroAmbiente.setEnabled(false);
        btnItemPortadorAlmasAmbiente.setEnabled(false);
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
    private void adicionarComponentes(){
        diretorio = "/br/ufla/dcc/ppoo/trabalhofinal/imagens/principal.jpeg";
        adicionarImagem(diretorio);

        //Gerenciador do Jogo
        regraNegocio = new RegraNegocio();
        
        //Analisador de comandos do jogo
        analisador = new Analisador();

        //Imprime o texto na tela
        textoDinamico = new JTextArea(regraNegocio.mensagemBoasVindas());
        textoDinamico.setEditable(false);
        
        //Adiciona barra de rolagem ao texto
        jScrollPaneSaida = new JScrollPane(textoDinamico);
        //textoDinamico.setMinimumSize(new Dimension(200, 100));
        //textoDinamico.setMaximumSize(new Dimension(500, 400));
        //textoDinamico.setPreferredSize(new Dimension(200, 100));
        //jScrollPaneSaida.setMinimumSize(new Dimension(200, 100));
        //jScrollPaneSaida.setMaximumSize(new Dimension(500, 400));
        jScrollPaneSaida.setPreferredSize(new Dimension(600, 400));
        
        //Adiciona o texto na tela
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
                GerenciadorDeImagens.OK);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemCartaAmbiente,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                2, 0, 1, 1);
        
        btnItemPenaAmbiente = new JButton(I18N.obterBotaoItenPena(),
            GerenciadorDeImagens.OK);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemPenaAmbiente,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                4, 0, 1, 1);
        
        btnItemDenteLoboAmbiente = new JButton(I18N.obterBotaoItenDenteLobo(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemDenteLoboAmbiente,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                6, 0, 1, 1);
        
        btnItemCabecaVampiroAmbiente = new JButton(I18N.obterBotaoItenCabecaVampiro(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemCabecaVampiroAmbiente,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                8, 0, 1, 1);
        
        btnItemPortadorAlmasAmbiente = new JButton(I18N.obterBotaoItemPortadorAlmas(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemPortadorAlmasAmbiente,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                10, 0, 1, 1);
        
        //Botoes de Exibicao de itens
        btnVerItensArmario = new JButton(I18N.obterBotaoVerItensAmbiente(),
                GerenciadorDeImagens.OK);
        //Adicão do botao VerItensArmario no painei Oeste
        adicionarComponentePainelLeste(btnVerItensArmario,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                12, 0, 1, 1);

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
                GerenciadorDeImagens.OK);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemCartaMochila,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                22, 0, 1, 1);
        
        btnItemPenaMochila = new JButton(I18N.obterBotaoItenPena(),
            GerenciadorDeImagens.OK);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemPenaMochila,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                24, 0, 1, 1);
        
        btnItemDenteLoboMochila = new JButton(I18N.obterBotaoItenDenteLobo(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemDenteLoboMochila,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                26, 0, 1, 1);
        
        btnItemCabecaVampiroMochila = new JButton(I18N.obterBotaoItenCabecaVampiro(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelLeste(btnItemCabecaVampiroMochila,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                28, 0, 1, 1);
        
        btnItemPortadorAlmasMochila = new JButton(I18N.obterBotaoItemPortadorAlmas(),
                GerenciadorDeImagens.OK);
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
                if (validaAmbiente.indexOf("Nao ha passagem!")>=0){
                    textoDinamico.setText("\nNao ha passagem!\n");
                }else{
                    textoDinamico.setText(validaAmbiente);
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                    prepararComponentesEstadoInicialCeu();
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
                    textoDinamico.setText(validaAmbiente);
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                    prepararComponentesEstadoInicialCasaWinchester();
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
                    textoDinamico.setText(validaAmbiente);
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                    prepararComponentesEstadoInicialDenver();
                }
            }
        });
        
        btnIrHouston.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String validaAmbiente;
                comando = analisador.pegarComando("ir Houston");
                validaAmbiente = regraNegocio.processarComando(comando);
                if (validaAmbiente.indexOf("Nao ha passagem!")>=0){
                    textoDinamico.setText("\nNao ha passagem!\n");
                }else{
                    textoDinamico.setText(validaAmbiente);
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                    prepararComponentesEstadoInicialHouston();
                }
            }
        });
        
        btnIrPortalInferno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String validaAmbiente;
                comando = analisador.pegarComando("ir PortalInferno");
                validaAmbiente = regraNegocio.processarComando(comando);
                if (validaAmbiente.indexOf("Nao ha passagem!")>=0){
                    textoDinamico.setText("\nNao ha passagem!\n");
                }else{
                    textoDinamico.setText(validaAmbiente);
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                    prepararComponentesEstadoInicialPortalInferno();
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
                    textoDinamico.setText(validaAmbiente);
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                    prepararComponentesEstadoInicialPurgatorio();
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
                    textoDinamico.setText(validaAmbiente);
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                    prepararComponentesEstadoInicialCasaCaim();
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
                    textoDinamico.setText(validaAmbiente);
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                    prepararComponentesEstadoInicialCasaBob();
                }
            }
        });
        
        btnVerItensArmario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoExibicao;
                comando = analisador.pegarComando("analisar armario");
                textoExibicao = regraNegocio.processarComando(comando);
                if(textoExibicao != null){
                    textoDinamico.setText(textoExibicao);
                }else{
                    textoDinamico.setText("Nao ha intens guardados no armario");
                }
            }
        });
        
        btnItemCabecaVampiroMochila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoExibicao;
                comando = analisador.pegarComando("guardar CabecaVampiro");
                textoExibicao = regraNegocio.processarComando(comando);
                if(textoExibicao.indexOf("Item: CabecaVampiro guardado com sucesso")>=0){
                    textoDinamico.setText(textoExibicao);
                    btnItemCabecaVampiroMochila.setVisible(false);
                    btnItemCabecaVampiroAmbiente.setVisible(true);
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
                if(textoExibicao.indexOf("Item: Carta guardado com sucesso")>=0){
                    textoDinamico.setText(textoExibicao);
                    btnItemCartaMochila.setVisible(false);
                    btnItemCartaAmbiente.setVisible(true);
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
                if(textoExibicao.indexOf("Item: CabecaVampiro guardado com sucesso")>=0){
                    textoDinamico.setText(textoExibicao);
                    btnItemDenteLoboMochila.setVisible(false);
                    btnItemDenteLoboAmbiente.setVisible(true);
                    
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
                if(textoExibicao.indexOf("Item: Carta guardado com sucesso")>=0){
                    textoDinamico.setText(textoExibicao);
                    btnItemPenaMochila.setVisible(false);
                    btnItemPenaAmbiente.setVisible(true);
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
                if(textoExibicao.indexOf("Item: PortadorAlmas guardado com sucesso")>=0){
                    textoDinamico.setText(textoExibicao);
                    btnItemPortadorAlmasMochila.setVisible(false);
                    btnItemPortadorAlmasAmbiente.setVisible(true);
                }else{
                    textoDinamico.setText(textoExibicao);
                }
            }
        });
        
        btnItemCabecaVampiroAmbiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComando("pegar CabecaVampiro");
                textoDinamico.setText(regraNegocio.processarComando(comando));
                btnItemCabecaVampiroAmbiente.setVisible(false);
                btnItemCabecaVampiroMochila.setEnabled(true);
            }
        });
        
        btnItemCartaAmbiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComando("pegar Carta");
                textoDinamico.setText(regraNegocio.processarComando(comando));
                btnItemCartaAmbiente.setVisible(false);
                btnItemCartaMochila.setEnabled(true);
            }
        });
        
        btnItemDenteLoboAmbiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                comando = analisador.pegarComando("pegar Dente");
                textoDinamico.setText(regraNegocio.processarComando(comando));
                btnItemDenteLoboAmbiente.setVisible(false);
                btnItemDenteLoboMochila.setEnabled(true);
            }
        });
        
        btnItemPenaAmbiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                comando = analisador.pegarComando("pegar Pena");
                textoDinamico.setText(regraNegocio.processarComando(comando));
                btnItemPenaAmbiente.setVisible(false);
                btnItemPenaMochila.setEnabled(true);
            }
        });
        
        btnItemPortadorAlmasAmbiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                comando = analisador.pegarComando("pegar Almas");
                textoDinamico.setText(regraNegocio.processarComando(comando));
                btnItemPortadorAlmasAmbiente.setVisible(false);
                btnItemPortadorAlmasMochila.setEnabled(true);
            }
        });
        
        btnEnviarComando.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                comando = analisador.pegarComando(txtEntradaComandos.getText());
                textoDinamico.setText(regraNegocio.processarComando(comando));
                trocaImagem(regraNegocio.imagemAmbienteAtual());
                txtEntradaComandos.setText("Entrada de Comandos:");
            
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
        adicionarComponentes();
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