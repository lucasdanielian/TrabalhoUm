package br.ufla.dcc.ppoo.trabalhofinal.gui;

import br.ufla.dcc.ppoo.trabalhofinal.comandos.Analisador;
import br.ufla.dcc.ppoo.trabalhofinal.comandos.Comando;
import br.ufla.dcc.ppoo.trabalhofinal.excecoes.ExcecaoImagens;
import br.ufla.dcc.ppoo.trabalhofinal.interacaousuario.TelaPrincipal;
import br.ufla.dcc.ppoo.trabalhofinal.i18n.I18N;
import br.ufla.dcc.ppoo.trabalhofinal.imagens.GerenciadorDeImagens;
import br.ufla.dcc.ppoo.trabalhofinal.regranegocio.RegraNegocio;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private BorderLayout layout;
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
    private JScrollPane jScrollPaneSaida;
    private ImageIcon logo;
    private JLabel label;
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
    }

    /**
     * Troca a imgagem atual da tela
     * @param String o endereço da mesma deve está dentro do pacote imagens
     */
    private void adicionarImagem(String diretorio){
        //URL resource = getClass().getResource(diretorio);
        //File file = new File(resource.toURI());
        
        File file = new File(diretorio);
        if(file.exists()) {
            logo = new ImageIcon(file.getPath());

            label = new JLabel(logo);
            janela.add(label, BorderLayout.EAST);
        }else{
            throw new ExcecaoImagens("Imagem: " + diretorio + " Nao encontrada. \t"
                    + "O jogo sera fechado, tente jogar novamente. \n"
                    + "Se o problema persistir contate o administrador do sistema");
        }
    }
    
    /**
     * Troca a imgagem atual da tela
     * @param String o endereço da mesma deve está dentro do pacote imagens
     */
    private void trocaImagem(String diretorio){

        File file = new File(diretorio);
        if(file.exists()) {
            label.setIcon(new ImageIcon(file.getPath()));
        }else{
            throw new ExcecaoImagens("Imagem: " + diretorio + " Nao encontrada. \t"
                    + "O jogo sera fechado, tente jogar novamente. \n"
                    + "Se o problema persistir contate o administrador do sistema");
        }
    }
    
    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentes(){
        try{
            diretorio = "/home/junior/projetos/TrabalhoUm/Atual/src/br/ufla/dcc/ppoo/trabalhofinal/imagens/bandeira-br.png";
            adicionarImagem(diretorio);

            //Gerenciador do Jogo
            regraNegocio = new RegraNegocio();
            //Analisador de comandos do jogo
            analisador = new Analisador();

            //Imprime o texto na tela
            textoDinamico = new JTextArea(regraNegocio.mensagemBoasVindas());
            //Adiciona barra de rolagem ao texto
            jScrollPaneSaida = new JScrollPane(textoDinamico);
            //Adiciona o texto na tela
            janela.add(jScrollPaneSaida, BorderLayout.CENTER);

            //Recebe a entrada do usuario na tela
            txtEntradaComandos = new JTextField(10);
            janela.add(txtEntradaComandos, BorderLayout.SOUTH);

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

            //Adiciona os botoes principais na tela
            JPanel painelBotoes = new JPanel();
            painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
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
            janela.add(painelBotoes, BorderLayout.WEST);
        }catch(ExcecaoImagens e){
            JOptionPane.showMessageDialog(janela, e.getMessage());
                    janela.dispose();
        }
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {

        btnIrCeu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    comando = analisador.pegarComandoInterfaceGrafia("ir Ceu");
                    textoDinamico.setText(regraNegocio.processarComando(comando));
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(janela, "Imagem: " + regraNegocio.imagemAmbienteAtual()
                            + " Nao encontrada");
                    JOptionPane.showMessageDialog(janela, "O jogo sera fechado, tente jogar novamente. \n"
                            + "Se o problema persistir contate o administrador do sistema");
                    janela.dispose();
                }   
            }
        });
            
        btnIrCasaWinchester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    comando = analisador.pegarComandoInterfaceGrafia("ir CasaWinchester");
                    textoDinamico.setText(regraNegocio.processarComando(comando));
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                }catch(ExcecaoImagens excecaoImagens){
                    JOptionPane.showMessageDialog(janela, excecaoImagens.getMessage());
                    janela.dispose();
                }
            }
        });
        
        btnIrDenver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    comando = analisador.pegarComandoInterfaceGrafia("ir Denver");
                    textoDinamico.setText(regraNegocio.processarComando(comando));
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                }catch(ExcecaoImagens excecaoImagens){
                    JOptionPane.showMessageDialog(janela, excecaoImagens.getMessage());
                    janela.dispose();
                }
            }
        });
        
        btnIrHouston.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    comando = analisador.pegarComandoInterfaceGrafia("ir Houston");
                    textoDinamico.setText(regraNegocio.processarComando(comando));
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                }catch(ExcecaoImagens excecaoImagens){
                    JOptionPane.showMessageDialog(janela, excecaoImagens.getMessage());
                    janela.dispose();
                }
            }
        });
        
        btnIrInferno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    comando = analisador.pegarComandoInterfaceGrafia("ir Inferno");
                    textoDinamico.setText(regraNegocio.processarComando(comando));
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                }catch(ExcecaoImagens excecaoImagens){
                    JOptionPane.showMessageDialog(janela, excecaoImagens.getMessage());
                    janela.dispose();
                }
            }
        });
        
        btnIrPurgatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    comando = analisador.pegarComandoInterfaceGrafia("ir Purgatorio");
                    textoDinamico.setText(regraNegocio.processarComando(comando));
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                }catch(ExcecaoImagens excecaoImagens){
                    JOptionPane.showMessageDialog(janela, excecaoImagens.getMessage());
                    janela.dispose();
                }
            }
        });
        
        btnIrCasaCaim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    comando = analisador.pegarComandoInterfaceGrafia("ir CasaCaim");
                    textoDinamico.setText(regraNegocio.processarComando(comando));
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                }catch(ExcecaoImagens excecaoImagens){
                    JOptionPane.showMessageDialog(janela, excecaoImagens.getMessage());
                    janela.dispose();
                }
            }
        });
        
        btnIrCasaBob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    comando = analisador.pegarComandoInterfaceGrafia("ir CasaBob");
                    textoDinamico.setText(regraNegocio.processarComando(comando));
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                }catch(ExcecaoImagens excecaoImagens){
                    JOptionPane.showMessageDialog(janela, excecaoImagens.getMessage());
                    janela.dispose();
                }
            }
        });
        
        btnEnviarComando.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    comando = analisador.pegarComandoInterfaceGrafia(txtEntradaComandos.getText());
                    textoDinamico.setText(regraNegocio.processarComando(comando));
                    trocaImagem(regraNegocio.imagemAmbienteAtual());
                }catch(ExcecaoImagens excecaoImagens){
                    JOptionPane.showMessageDialog(janela, excecaoImagens.getMessage());
                    janela.dispose();
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
        janela = new JFrame("Super Natural : Morte Subita");
        janela.setTitle(I18N.obterTituloTelaMeuJogo());
        layout = new BorderLayout();
        janela.setLayout(layout);
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
