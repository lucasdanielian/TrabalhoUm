package br.ufla.dcc.ppoo.trabalhofinal.gui;

import br.ufla.dcc.ppoo.trabalhofinal.comandos.Analisador;
import br.ufla.dcc.ppoo.trabalhofinal.comandos.Comando;
import br.ufla.dcc.ppoo.trabalhofinal.interacaousuario.TelaPrincipal;
import br.ufla.dcc.ppoo.trabalhofinal.i18n.I18N;
import br.ufla.dcc.ppoo.trabalhofinal.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.trabalhofinal.regranegocio.RegraNegocio;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
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
    private JTextArea  textoDinamico;
    private JTextField txtEntradaComandos;
    private RegraNegocio regraNegocio;
    private Comando comando;
    private Analisador analisador;


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
     * Adiciona um componente à tela.
     */
    private void adicionarComponente(Component c,
        int anchor, int fill, int linha,
        int coluna, int largura, int altura) {
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.gridy = linha;
        gbc.gridx = coluna;
        gbc.gridwidth = largura;
        gbc.gridheight = altura;
        gbc.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(c, gbc);
        janela.add(c);
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
    }

    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentes(){
        //Gerenciador do Jogo
        regraNegocio = new RegraNegocio();
        //Analisador de comandos do jogo
        analisador = new Analisador();
        
        //Imprime o texto na tela
        textoDinamico = new JTextArea(regraNegocio.mensagemBoasVindas());
        adicionarComponente(textoDinamico,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                //linha, coluna, largura, altura
                1, 1, 10, 10);

        //Recebe a entrada do usuario na tela
        txtEntradaComandos = new JTextField(25);
        adicionarComponente(txtEntradaComandos,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                //linha, coluna, largura, altura
                2, 1, 5, 1);
        
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
        
        prepararComponentesEstadoInicial();

        //Adiciona os botoes na tela
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnEnviarComando);
        painelBotoes.add(btnSalvarJogo);
        painelBotoes.add(btnCancelarJogo);
        painelBotoes.add(btnIrCasaWinchester);
        painelBotoes.add(btnIrDenver);
        painelBotoes.add(btnIrHouston);
        painelBotoes.add(btnIrCasaCaim);
        painelBotoes.add(btnIrCasaBob);
        painelBotoes.add(btnIrInferno);
        painelBotoes.add(btnIrPurgatorio);
        painelBotoes.add(btnIrCeu);

        adicionarComponente(painelBotoes,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                //linha, coluna, largura, altura
                3, 1, 1, 1);
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        
        btnIrCeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComandoInterfaceGrafia("ir Ceu");
                textoDinamico.setText(regraNegocio.processarComando(comando));
            }
        });
        
        btnIrCasaWinchester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComandoInterfaceGrafia("ir CasaWinchester");
                textoDinamico.setText(regraNegocio.processarComando(comando));
            }
        });
        
        btnIrDenver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComandoInterfaceGrafia("ir Denver");
                textoDinamico.setText(regraNegocio.processarComando(comando));
            }
        });
        
        btnIrHouston.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComandoInterfaceGrafia("ir Houston");
                textoDinamico.setText(regraNegocio.processarComando(comando));
            }
        });
        
        btnIrInferno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComandoInterfaceGrafia("ir Inferno");
                textoDinamico.setText(regraNegocio.processarComando(comando));
            }
        });
        
        btnIrPurgatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComandoInterfaceGrafia("ir Purgatorio");
                textoDinamico.setText(regraNegocio.processarComando(comando));
            }
        });
        
        btnIrCasaCaim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComandoInterfaceGrafia("ir CasaCaim");
                textoDinamico.setText(regraNegocio.processarComando(comando));
            }
        });
        
        btnIrCasaBob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComandoInterfaceGrafia("ir CasaBob");
                textoDinamico.setText(regraNegocio.processarComando(comando));
            }
        });
        
        btnEnviarComando.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComandoInterfaceGrafia(txtEntradaComandos.getText());
                textoDinamico.setText(regraNegocio.processarComando(comando));
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
                prepararComponentesEstadoInicial();
            }
        });
    }

    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTela(){
        janela = new JDialog();
        janela.setTitle(I18N.obterTituloTelaMeuJogo());
        layout = new GridBagLayout();
        gbc = new GridBagConstraints();
        janela.setLayout(layout);
        adicionarComponentes();
        janela.pack();
        
    }

    /**
     * Exibe a tela.
     */
    private void exibirTela() {
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setLocationRelativeTo(telaPrincipal.obterJanela());
        janela.setModal(true);
        janela.setVisible(true);
        janela.setResizable(false);
    }
}
