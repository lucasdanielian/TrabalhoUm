package br.ufla.dcc.ppoo.trabalhodois.gui;

import br.ufla.dcc.ppoo.i18n.I18N;
import br.ufla.dcc.ppoo.trabalhodois.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.util.Utilidades;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Classe que representa a tela Meus Filmes
 * 
 * @author Julio Alves e Paulo Jr.
 */
public class TelaJogo {

    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
            
    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JButton btnSalvarJogo;
    private JButton btnCancelarJogo;
    private JLabel lbTexto1;
    private JLabel lbTexto2;
    private JLabel lbEntradaComandos;
    private JTextField txtEntradaComandos;


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
    public void inicializar() {
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
    }

    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentes() {
        
        lbTexto1 = new JLabel(I18N.obterRotuloTextoComandos());
        adicionarComponente(lbTexto1,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                1, 0, 1, 1);
        
        lbTexto2 = new JLabel(I18N.obterRotuloTextoComandos());
        adicionarComponente(lbTexto1,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                1, 0, 1, 1);
        
        lbEntradaComandos = new JLabel(I18N.obterRotuloEntradaComandos());
        adicionarComponente(lbEntradaComandos,
                GridBagConstraints.LINE_END,
                GridBagConstraints.NONE,
                2, 0, 2, 2);

        txtEntradaComandos = new JTextField(25);
        adicionarComponente(txtEntradaComandos,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL,
                2, 2, 4, 2);

        btnSalvarJogo = new JButton(I18N.obterBotaoSalvar(),
                GerenciadorDeImagens.OK);

        btnCancelarJogo = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);

        prepararComponentesEstadoInicial();

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSalvarJogo);
        painelBotoes.add(btnCancelarJogo);

        adicionarComponente(painelBotoes,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                6, 0, 4, 1);
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
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
    private void construirTela() {
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
