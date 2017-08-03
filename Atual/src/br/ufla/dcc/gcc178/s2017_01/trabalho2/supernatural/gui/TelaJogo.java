package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.gui;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes.Ambiente;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.comandos.Analisador;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.comandos.Comando;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.interacaousuario.TelaPrincipal;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.i18n.I18N;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.imagens.GerenciadorDeImagens;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.jogador.Jogador;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.persistencia.Serializacao;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
public class TelaJogo implements Serializacao {

    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;
    
    //atributo para Serializacao
    private static final long serialVersionUID = 1L;
            
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
    private JButton btnRecuperarJogo;
    
    //Botoes para navegacao entre os ambientes    
    private JButton btnAmbiente;
    private List<JButton> botoesAmbientes;
    
    //Botao que mostra os itens dos ambientes
    private JButton btnItemAmbiente;
    private List<JButton> botoesItensAmbientes;

    
    //Botoes que mostra os itens da mochila
    private JButton btnItemJogador;
    private List<JButton> botoesItensJogador;
    
    
    //Exibicao de texto
    private JTextArea textoDinamico;
    private JTextArea tituloBotoesItens;
    private JTextArea tituloBotoesAmbientes;
    private JTextArea tituloBotoesPrincipais;
    private JTextArea tituloBotoesVerItens;
    private JTextArea diasCorridos;
    private JTextArea diasRestantes;
    private JTextArea ambienteAtual;
    private JLabel labelNaoHaBotoes;
    
    //Entradas do usuario
    private JTextField txtEntradaComandos;
    
    //Outros
    private static RegraNegocio regraNegocio;
    private Comando comando;
    private Analisador analisador;
    private JScrollPane jScrollPaneSaida;
    private ImageIcon logo;
    private JLabel imagensJogo;
    private JLabel rotuloTxtEntradaComandos;
    private File file;
    private URL resource;
    private String imagemPrincipal;
    private String diretorioPersistencia;

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
    }
    
    /**
     * Metodo responsvel por verificar a disponibilidade de cada iten
     * em seu respectivo ambiente
     * @param itensDisponiveis O item é passado por referencia, caso esteja
     * indisponivel todos são setados como enable false, se não enable true
     */
    private void atualizarItensAmbiente(){
        String itensDisponiveis = regraNegocio.verificaDisponibilidadeItemAmbiente();
    }
    
    /**
     * Metodo responsvel por verificar a disponibilidade de cada iten
     * na mochila do jogador
     */
    private void atualizarItensJogador(){
        Comando auxComando = analisador.pegarComando("analisar mochila");
        String itensDisponiveis = regraNegocio.processarComando(auxComando);
    }
    
    /**
     * Metodo que finaliza o jogo
     */
    private void gameOver(){
        atualizaPainelPontuacao();
        JOptionPane.showMessageDialog(janela, "GAME OVER! Seu tempo estourou"
            + "\nTente Jogar Novamente!");
        janela.dispose();
    }
    
    /**
     * Metodo que atualiza o painel de visualizacao da quantidade de dias corridos
     */
    private void atualizaPainelPontuacao(){
        diasCorridos.setText("Dias Corridos: " + regraNegocio.getDiasCorridos());
        diasRestantes.setText("Dias Restantes: " + regraNegocio.diasRestantes());
        ambienteAtual.setText("Você está em: " + regraNegocio.getNomeAmbienteAtual());
        
    }
    
    /**
     * Troca a imgagem atual da tela
     * @param String o endereço da mesma deve está dentro do pacote imagens
     */
    private void adicionarImagemAmbiente(String diretorio){
        try{
            URL resource = getClass().getResource(diretorio);
            file = new File(resource.toURI());
            logo = new ImageIcon(file.getPath());
            imagensJogo = new JLabel(logo);
            imagensJogo.setPreferredSize(new Dimension(500, 200));
            //Adicao da imagem no painei Oeste
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
    private void trocaImagemAmbiente(String diretorio){
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
        gbc.insets = new Insets(3, 3, 3, 3);
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
    
    
    private void criarBotoesAmbientes(List<Ambiente> ambientes){
       
        for (Ambiente ambiente : ambientes) {
            
            btnAmbiente = new JButton(ambiente.getNomeAmbiente(),
                    GerenciadorDeImagens.OK);
            btnAmbiente.setName(ambiente.getNomeAmbiente());
            botoesAmbientes.add(btnAmbiente);
        }
    }
    
    private void criarBotoesItensAmbientes(List<Ambiente> ambientes){
       
        for (Ambiente ambiente : ambientes) {
            if(ambiente.getItem() != null){
                btnItemAmbiente = new JButton(ambiente.getItem().getNomeItem(),
                        GerenciadorDeImagens.OK);
                btnItemAmbiente.setName(ambiente.getItem().getNomeItem());
                botoesItensAmbientes.add(btnItemAmbiente);
            }
            
        }
    }
    
    private void criarBotoesItensJogador(List<Item> itensJogador){
        for (Item itemJogador : itensJogador) {
            btnItemJogador = new JButton(itemJogador.getNomeItem(),
                    GerenciadorDeImagens.OK);
            btnItemAmbiente.setName(itemJogador.getNomeItem());
            botoesItensJogador.add(btnItemJogador);
        }
        
    }
    
    
    private void adicionarBotoesAmbientesNaTela(List<JButton> botoesAmbientes){
        int linha = 10;
        if(botoesAmbientes.size() == 0){

            adicionarComponentePainelLeste(labelNaoHaBotoes,
                    GridBagConstraints.NORTH,
                    GridBagConstraints.VERTICAL,
                    linha, 0, 1, 1);
                linha += 2;
            
        }else{
            for (JButton botaoAmbiente : botoesAmbientes) {
                //Adicão dos botoes de jogo no painei Oeste
                adicionarComponentePainelOeste(botaoAmbiente,
                        GridBagConstraints.CENTER,
                        GridBagConstraints.NONE,
                        linha, 0, 1, 1);
                linha += 2;
            }
        }
    }
    
    private void adicionarBotoesItensAmbientesNaTela(List<JButton> botoesItensAmbientes){
        int linha = 2;
        if(botoesItensAmbientes.size() == 0){

            adicionarComponentePainelLeste(labelNaoHaBotoes,
                    GridBagConstraints.NORTH,
                    GridBagConstraints.VERTICAL,
                    linha, 0, 1, 1);
                linha += 2;
            
        }else{
            for (JButton botaoItem : botoesItensAmbientes) {
                //Adicão dos botoes de jogo no painei Leste
            adicionarComponentePainelLeste(botaoItem,
                    GridBagConstraints.NORTH,
                    GridBagConstraints.VERTICAL,
                    linha, 0, 1, 1);
                linha += 2;
            }  
        }
        
    }
    
    private void adicionarBotoesItensJogadorNaTela(List<JButton> botoesItensJogador){
        int linha = 22;
        if(botoesItensJogador.size() == 0){

            adicionarComponentePainelLeste(labelNaoHaBotoes,
                    GridBagConstraints.NORTH,
                    GridBagConstraints.VERTICAL,
                    linha, 0, 1, 1);
                linha += 2;
            
        }else{
            for (JButton botaoItemJogador : botoesItensJogador) {
                //Adicão dos botoes de jogo no painei Leste
                adicionarComponentePainelLeste(botaoItemJogador,
                        GridBagConstraints.CENTER,
                        GridBagConstraints.VERTICAL,
                        linha, 0, 1, 1);
                linha += 2;
            }
        }
    }
    
    private void adicionarPainelOrientacaoJogador(){
        //Imprime os dias corridos do jagador na tela
        diasCorridos = new JTextArea("Dias Corridos: " + regraNegocio.getDiasCorridos());
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
        
        //Imprime os dias restantes do jagador na tela
        ambienteAtual = new JTextArea("Você está em: " + regraNegocio.getNomeAmbienteAtual());
        ambienteAtual.setFont(new Font("Serif", Font.ITALIC, 18));
        ambienteAtual.setBackground(Color.GRAY);
        ambienteAtual.setForeground(Color.WHITE);
        ambienteAtual.setLineWrap(true);
        ambienteAtual.setEditable(false);        
        //Adiciona dias restantes na tela
        adicionarComponentePainelNorte(ambienteAtual,
                GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL,
                1, 5, 1, 1);
    }
    
    private void botoesEstaticos(){
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
        
        btnRecuperarJogo = new JButton(I18N.obterBotaoRecuperarjogo(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes Ambientes no painei Oeste
        adicionarComponentePainelOeste(btnRecuperarJogo,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                6, 0, 1, 1);
        
        //Botao que envia um comando
        btnEnviarComando = new JButton(I18N.obterBotaoEnviar(),
                GerenciadorDeImagens.OK);
        //Adicão do botao Enviar comando no painei Oeste
        adicionarComponentePainelCentral(btnEnviarComando,
                GridBagConstraints.PAGE_END,
                GridBagConstraints.VERTICAL,
                4, 0, 1, 1);
    }
    
    private void botoesItensJogador(){
        //Imprime o titulo dos botoes de itens da mochila na tela
        tituloBotoesVerItens = new JTextArea(" ITENS JOGADOR ");
        tituloBotoesVerItens.setFont(new Font("Serif", Font.ITALIC, 18));
        tituloBotoesVerItens.setBackground(Color.GRAY);
        tituloBotoesVerItens.setForeground(Color.WHITE);
        tituloBotoesVerItens.setEditable(false);
        //Adiciona o texto na tela
        adicionarComponentePainelLeste(tituloBotoesVerItens,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                20, 0, 2, 2);
        
        criarBotoesItensJogador(regraNegocio.getJogador().getMochila().getItens());
        adicionarBotoesItensJogadorNaTela(botoesItensJogador);
    }
    
    private void botoesItensAmbientes(){
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
        
        criarBotoesItensAmbientes(regraNegocio.getAmbientes());
        adicionarBotoesItensAmbientesNaTela(botoesItensAmbientes);
    }
    
    private void botoesAmbientes(){
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

        criarBotoesAmbientes(regraNegocio.getAmbientes());
        adicionarBotoesAmbientesNaTela(botoesAmbientes);
    }
    
    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentesTelaJogo(){
        imagemPrincipal = "/br/ufla/dcc/gcc178/s2017_01/trabalho2/supernatural/imagens/principal.jpeg";
        adicionarImagemAmbiente(imagemPrincipal);
        
        Font myFont = new Font("Arial", Font.BOLD, 16);

        botoesAmbientes = new ArrayList<>();
        
        botoesItensAmbientes = new ArrayList<>();
        
        botoesItensJogador = new ArrayList<>();
        
        //Gerenciador do Jogo
        regraNegocio = new RegraNegocio();
        
        //Analisador de comandos do jogo
        analisador = new Analisador();

        labelNaoHaBotoes = new JLabel("Nao ha itens para "
                                    + "serem exibidos aqui!");
        labelNaoHaBotoes.setFont(myFont);
        
        
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
        
        
        
        botoesAmbientes();
        botoesItensAmbientes();
        botoesItensJogador();
        botoesEstaticos();
        adicionarPainelOrientacaoJogador();
        prepararComponentesEstadoInicial();        
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        
        //Loop responsavel pelas ações dos botões Ambientes
        for (JButton botaoAmbiente : botoesAmbientes) {
            botaoAmbiente.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String validaAmbiente;
                comando = analisador.pegarComando("ir " + botaoAmbiente.getName());
                validaAmbiente = regraNegocio.processarComando(comando);
                if (validaAmbiente.contains("Nao ha passagem!")){
                    textoDinamico.setText("\nNao ha passagem!\n");
                }else{
                    if(regraNegocio.diasRestantes()==0){
                        gameOver();
                    }else{

                        atualizarItensAmbiente();
                        textoDinamico.setText(validaAmbiente);
                        trocaImagemAmbiente(regraNegocio.imagemAmbienteAtual());
                        atualizaPainelPontuacao();
                    }
                }
            }
        });
    }        
     
    //Loop responsavel pelas ações dos botões de itens do Jogador
    for (JButton botaoItemJogador : botoesItensJogador) {
        botaoItemJogador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoExibicao;
                comando = analisador.pegarComando("guardar " + botaoItemJogador.getName());
                textoExibicao = regraNegocio.processarComando(comando);
                if(textoExibicao.contains(botaoItemJogador.getName() + " guardado com sucesso")){
                    textoDinamico.setText(textoExibicao);
                }else{
                    textoDinamico.setText(textoExibicao);
                }
            }
        });
    }
    
    //Loop responsavel pelas ações dos botões de itens dos ambientes
    for (JButton botaoItemAmbiente : botoesItensAmbientes) {
        botaoItemAmbiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComando("pegar " + botaoItemAmbiente.getName());
                String item = regraNegocio.processarComando(comando);
                textoDinamico.setText(item);
                if(item.contains("item coletado")||item.contains("coletado com sucesso")){
                    
                }
            }
        });
    }
        
    btnEnviarComando.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String validaTexto = txtEntradaComandos.getText();
            if(validaTexto.equals("sair")){
                janela.dispose();
            }else{
                comando = analisador.pegarComando(validaTexto);
                textoDinamico.setText(regraNegocio.processarComando(comando));
                trocaImagemAmbiente(regraNegocio.imagemAmbienteAtual());
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
            escritaArquivo();
            regraNegocio.rankingJogadores();
            JOptionPane.showMessageDialog(janela, "Jogo salvo com sucesso");
        }
    });

    btnRecuperarJogo.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            leituraArquivo();
            atualizaPainelPontuacao();
            atualizarItensJogador();
            atualizarItensAmbiente();
            textoDinamico.setText(regraNegocio.descricaoAmbienteAtual());
            JOptionPane.showMessageDialog(janela, "Jogo recuperado com sucesso");
        }
    });
        
    txtEntradaComandos.addKeyListener(new KeyListener() {
        // Chamado logo após o usuário digitar um caractere Unicode
        @Override
        public void keyTyped(KeyEvent e) {}

        //Chamado logo após o usuário pressionar uma tecla 
        @Override
        public void keyPressed(KeyEvent evt) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                String validaTexto = txtEntradaComandos.getText();
                if(validaTexto.equals("sair")){
                    janela.dispose();
                }else{
                    comando = analisador.pegarComando(validaTexto);
                    textoDinamico.setText(regraNegocio.processarComando(comando));
                    trocaImagemAmbiente(regraNegocio.imagemAmbienteAtual());
                    txtEntradaComandos.setText("Entrada de Comandos:");
                }
            }
        }   

        //Chamado logo após o usuário soltar uma tecla
        @Override
        public void keyReleased(KeyEvent e) {}
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
        
        //Nome do arquivo para serializacao
        diretorioPersistencia = "superNatural.dat";
        
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
    
    /**
     * Salva o estado atual do jogo serializando o arquivo
     */
    @Override
    public void escritaArquivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(diretorioPersistencia));
            oos.writeObject(regraNegocio);
            oos.close();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(janela, e.getMessage());
        }
    }

    /**
     * Recupera o estado do ultimo jogo salvo
     */
    @Override
    public void leituraArquivo() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(diretorioPersistencia));
            regraNegocio = (RegraNegocio)ois.readObject();
            ois.close();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(janela, e.getMessage());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(janela, ex.getMessage());
        }
    }
}