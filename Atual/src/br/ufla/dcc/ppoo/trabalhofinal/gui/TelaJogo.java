package br.ufla.dcc.ppoo.trabalhofinal.gui;

import br.ufla.dcc.ppoo.trabalhofinal.comandos.Analisador;
import br.ufla.dcc.ppoo.trabalhofinal.comandos.Comando;
import br.ufla.dcc.ppoo.trabalhofinal.interacaousuario.TelaPrincipal;
import br.ufla.dcc.ppoo.trabalhofinal.i18n.I18N;
import br.ufla.dcc.ppoo.trabalhofinal.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.trabalhofinal.regranegocio.RegraNegocio;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
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
    private JButton btnEnviarComando;
    private JButton btnSalvarJogo;
    private JButton btnCancelarJogo;
    private JButton btnIrCasaWinchester;
    private JButton btnIrDenver;
    private JButton btnIrHouston;
    private JButton btnIrCasaCaim;
    private JButton btnIrCasaBob;
    private JButton btnIrInferno;
    private JButton btnIrPurgatorio;
    private JButton btnIrCeu;
    private JButton btnVerItensArmario;
    private JButton btnVerItensMochila;
    private JTextArea  textoDinamico;
    private JTextField txtEntradaComandos;
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

        btnSalvarJogo.setEnabled(true);
        btnCancelarJogo.setEnabled(true);
        btnEnviarComando.setEnabled(true);
        btnIrCasaWinchester.setEnabled(true);
        btnIrDenver.setEnabled(true);
        btnIrHouston.setEnabled(true);
        btnIrCasaCaim.setEnabled(true);
        btnIrCasaBob.setEnabled(true);
        btnIrInferno.setEnabled(true);
        btnIrPurgatorio.setEnabled(true);
        btnIrCeu.setEnabled(true);
        btnVerItensArmario.setEnabled(true);
        btnVerItensMochila.setEnabled(true);
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
            adicionarComponentePainelLeste(imagensJogo,
                    GridBagConstraints.NORTH,
                    GridBagConstraints.NONE,
                    0, 0, 1, 1);
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
        
        //Adiciona o texto na tela
        adicionarComponentePainelCentral(jScrollPaneSaida,
                GridBagConstraints.CENTER,
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
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                2, 0, 5, 2);

        //Botao que envia um comando
        btnEnviarComando = new JButton(I18N.obterBotaoEnviar(),
                GerenciadorDeImagens.OK);

        //Botao que salva o jogo em persistencia
        btnSalvarJogo = new JButton(I18N.obterBotaoSalvar(),
                GerenciadorDeImagens.OK);

        //Botao que encerra o jogo
        btnCancelarJogo = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);

        //Botoes dos Ambientes
        btnIrCasaWinchester = new JButton(I18N.obterBotaoWinchester(),
                GerenciadorDeImagens.OK);
        btnIrDenver = new JButton(I18N.obterBotaoDenver(),
                GerenciadorDeImagens.OK);
        btnIrHouston = new JButton(I18N.obterBotaoHouston(),
                GerenciadorDeImagens.OK);
        btnIrCasaCaim = new JButton(I18N.obterBotaoCasaCaim(),
                GerenciadorDeImagens.OK);
        btnIrCasaBob = new JButton(I18N.obterBotaoCasaBob(),
                GerenciadorDeImagens.OK);
        btnIrInferno = new JButton(I18N.obterBotaoInferno(),
                GerenciadorDeImagens.OK);
        btnIrPurgatorio = new JButton(I18N.obterBotaoPurgatorio(),
                GerenciadorDeImagens.OK);
        btnIrCeu = new JButton(I18N.obterBotaoCeu(),
                GerenciadorDeImagens.OK);
        
        //Manipulação de itens
        btnVerItensArmario = new JButton(I18N.obterBotaoVerItensAmbiente(),
                GerenciadorDeImagens.OK);
        btnVerItensMochila = new JButton(I18N.obterBotaoVerItensMochila(),
            GerenciadorDeImagens.OK);

        prepararComponentesEstadoInicial();
        //Component c, anchor, fill, linha, coluna, largura, altura
        
        
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelOeste(btnSalvarJogo,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                0, 0, 1, 1);
        
        //Adicão dos botoes Ambientes no painei Oeste
        adicionarComponentePainelOeste(btnCancelarJogo,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                1, 0, 1, 1);
        
        //Adicão dos botoes principais no painei Oeste
        adicionarComponentePainelOeste(btnEnviarComando,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                3, 0, 1, 1);
        
        //Adicão dos botoes principais no painei Oeste
        adicionarComponentePainelOeste(btnIrCasaWinchester,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                4, 0, 1, 1);
        
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelOeste(btnIrDenver,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                5, 0, 1, 1);
        
        //Adicão dos botoes Ambientes no painei Oeste
        adicionarComponentePainelOeste(btnIrHouston,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                6, 0, 1, 1);
        
        //Adicão dos botoes principais no painei Oeste
        adicionarComponentePainelOeste(btnIrCasaCaim,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                7, 0, 1, 1);
        
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelOeste(btnIrCasaBob,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                8, 0, 1, 1);
        
        //Adicão dos botoes Ambientes no painei Oeste
        adicionarComponentePainelOeste(btnIrInferno,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                9, 0, 1, 1);
        
        //Adicão dos botoes principais no painei Oeste
        adicionarComponentePainelOeste(btnIrPurgatorio,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                10, 0, 1, 1);
        
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelOeste(btnIrCeu,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                11, 0, 1, 1);
        
        //Adicão dos botoes Ambientes no painei Oeste
        adicionarComponentePainelOeste(btnVerItensArmario,
                GridBagConstraints.SOUTH,
                GridBagConstraints.SOUTH,
                13, 0, 1, 1);
        
        //Adicão dos botoes de jogo no painei Oeste
        adicionarComponentePainelOeste(btnVerItensMochila,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                14, 0, 1, 1);
        
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        
        btnIrCeu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            
                comando = analisador.pegarComando("ir Ceu");
                textoDinamico.setText(regraNegocio.processarComando(comando));
                trocaImagem(regraNegocio.imagemAmbienteAtual());
               
            }
        });
            
        btnIrCasaWinchester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                comando = analisador.pegarComando("ir CasaWinchester");
                textoDinamico.setText(regraNegocio.processarComando(comando));
                trocaImagem(regraNegocio.imagemAmbienteAtual());
            
            }
        });
        
        btnIrDenver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                comando = analisador.pegarComando("ir Denver");
                textoDinamico.setText(regraNegocio.processarComando(comando));
                trocaImagem(regraNegocio.imagemAmbienteAtual());
            
            }
        });
        
        btnIrHouston.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                comando = analisador.pegarComando("ir Houston");
                textoDinamico.setText(regraNegocio.processarComando(comando));
                trocaImagem(regraNegocio.imagemAmbienteAtual());
            
            }
        });
        
        btnIrInferno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                comando = analisador.pegarComando("ir PortalInferno");
                textoDinamico.setText(regraNegocio.processarComando(comando));
                trocaImagem(regraNegocio.imagemAmbienteAtual());
            
            }
        });
        
        btnIrPurgatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                comando = analisador.pegarComando("ir Purgatorio");
                textoDinamico.setText(regraNegocio.processarComando(comando));
                trocaImagem(regraNegocio.imagemAmbienteAtual());
            
            }
        });
        
        btnIrCasaCaim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                comando = analisador.pegarComando("ir CasaCaim");
                textoDinamico.setText(regraNegocio.processarComando(comando));
                trocaImagem(regraNegocio.imagemAmbienteAtual());
            
            }
        });
        
        btnIrCasaBob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                comando = analisador.pegarComando("ir CasaBob");
                textoDinamico.setText(regraNegocio.processarComando(comando));
                trocaImagem(regraNegocio.imagemAmbienteAtual());
            
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
        
        btnVerItensMochila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoExibicao;
                comando = analisador.pegarComando("analisar mochila");
                textoExibicao = regraNegocio.processarComando(comando);
                if(textoExibicao != null){
                    textoDinamico.setText(textoExibicao);
                }else{
                    textoDinamico.setText("Nao ha intens na mochila");
                }
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