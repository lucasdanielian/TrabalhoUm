package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.gui;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes.Ambiente;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.comandos.Analisador;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.comandos.Comando;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.interacaousuario.TelaPrincipal;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.i18n.I18N;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.imagens.GerenciadorDeImagens;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
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
import java.util.HashMap;
import java.util.List;
import java.util.Set;
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
    private HashMap<String,JButton> botoesAmbientes;
    
    //Botao que mostra os itens dos ambientes
    private JButton btnItemAmbiente;
    private HashMap<String,JButton> botoesItensAmbientes;

    
    //Botoes que mostra os itens da mochila
    private JButton btnItemJogador;
    private HashMap<String,JButton> botoesItensJogador;
    
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
    private JLabel labelNaoHaItensAmbientes;
    
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
            adicionarComponentePainel(imagensJogo, painelNorte, layoutNorte,
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
    private void adicionarComponentePainel(Component c, JPanel painel, GridBagLayout layout,
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
        painel.add(c);
    }
    
    
    private void criarBotoes(List<Ambiente> ambientes){
        for (Ambiente ambiente : ambientes) {
            //Cria os botoes item dos ambientes e jogador
            if(ambiente.getItem() != null){
                //Cria botoes ambientes
                if(botoesItensAmbientes.size() > 0){
                    //Verifica se o botao já existe, caso seja null ele cria o botao
                    if(botoesItensAmbientes.get(ambiente.getItem().getNomeItem()) == null){
                        btnItemAmbiente = new JButton(ambiente.getItem().getNomeItem(),
                                GerenciadorDeImagens.OK);
                        btnItemAmbiente.setName(ambiente.getItem().getNomeItem());
                        botoesItensAmbientes.put(btnItemAmbiente.getName(), btnItemAmbiente);
                    }else{
                        
                    }
                    //Caso seja o primeiro botao entra aqui!    
                }else{
                    btnItemAmbiente = new JButton(ambiente.getItem().getNomeItem(),
                                GerenciadorDeImagens.OK);
                    btnItemAmbiente.setName(ambiente.getItem().getNomeItem());
                    botoesItensAmbientes.put(btnItemAmbiente.getName(), btnItemAmbiente);
                }
                
                //cria botoes jogador
                if(botoesItensJogador.size() > 0){
                    if(botoesItensJogador.get(ambiente.getItem().getNomeItem()) == null){
                        btnItemJogador = new JButton(ambiente.getItem().getNomeItem(),
                            GerenciadorDeImagens.OK);
                        btnItemJogador.setName(ambiente.getItem().getNomeItem());
                        botoesItensJogador.put(btnItemJogador.getName(), btnItemJogador);
                    }
                 //Caso seja o primeiro botao entra aqui!   
                }else{
                    btnItemJogador = new JButton(ambiente.getItem().getNomeItem(),
                            GerenciadorDeImagens.OK);
                    btnItemJogador.setName(ambiente.getItem().getNomeItem());
                    botoesItensJogador.put(btnItemJogador.getName(), btnItemJogador);
                }
                
            }
            //Cria os botoes dos ambientes
            if(botoesAmbientes.size() > 0){
                if(botoesAmbientes.get(btnAmbiente.getName()) == null){
                    btnAmbiente = new JButton(ambiente.getNomeAmbiente(),
                            GerenciadorDeImagens.OK);
                    btnAmbiente.setName(ambiente.getNomeAmbiente());
                    botoesAmbientes.put(btnAmbiente.getName(),btnAmbiente);
                }
            //Caso entre aqui é porque é o primeiro botao a ser criado    
            }else{
                btnAmbiente = new JButton(ambiente.getNomeAmbiente(),
                            GerenciadorDeImagens.OK);
                btnAmbiente.setName(ambiente.getNomeAmbiente());
                botoesAmbientes.put(btnAmbiente.getName(),btnAmbiente);
            }
        }
    }
    
    private void painelOrientacaoJogador(){
        //Imprime os dias corridos do jagador na tela
        diasCorridos = new JTextArea("Dias Corridos: " + regraNegocio.getDiasCorridos());
        diasCorridos.setFont(new Font("Serif", Font.ITALIC, 18));
        diasCorridos.setBackground(Color.GRAY);
        diasCorridos.setForeground(Color.WHITE);
        diasCorridos.setEditable(false);
        //Adiciona dias corridos na tela
        adicionarComponentePainel(diasCorridos, painelNorte, layoutNorte,
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
        adicionarComponentePainel(diasRestantes, painelNorte, layoutNorte,
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
        adicionarComponentePainel(ambienteAtual, painelNorte, layoutNorte,
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
        adicionarComponentePainel(tituloBotoesPrincipais, painelOeste, layoutOeste,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                0, 0, 1, 1);
        
        //Botao que salva o jogo em persistencia
        btnSalvarJogo = new JButton(I18N.obterBotaoSalvar(),
                GerenciadorDeImagens.OK);
        //Adicão dos botao Salvar jogo no painei Oeste
        adicionarComponentePainel(btnSalvarJogo, painelOeste, layoutOeste,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                2, 0, 1, 1);

        //Botao que encerra o jogo
        btnCancelarJogo = new JButton(I18N.obterBotaoCancelar(),
                GerenciadorDeImagens.CANCELAR);
        //Adicão dos botoes Ambientes no painei Oeste
        adicionarComponentePainel(btnCancelarJogo, painelOeste, layoutOeste,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                4, 0, 1, 1);
        
        btnRecuperarJogo = new JButton(I18N.obterBotaoRecuperarjogo(),
                GerenciadorDeImagens.OK);
        //Adicão dos botoes Ambientes no painei Oeste
        adicionarComponentePainel(btnRecuperarJogo, painelOeste, layoutOeste,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                6, 0, 1, 1);
        
        //Botao que envia um comando
        btnEnviarComando = new JButton(I18N.obterBotaoEnviar(),
                GerenciadorDeImagens.OK);
        //Adicão do botao Enviar comando no painei Oeste
        adicionarComponentePainel(btnEnviarComando, painelCentral, layoutCentral,
                GridBagConstraints.PAGE_END,
                GridBagConstraints.VERTICAL,
                4, 0, 1, 1);
    }
    
    private void adicionarBotoesItensJogador(){
        int linha = 20;
        //Imprime o titulo dos botoes de itens da mochila na tela
        tituloBotoesVerItens = new JTextArea(" ITENS JOGADOR ");
        tituloBotoesVerItens.setFont(new Font("Serif", Font.ITALIC, 18));
        tituloBotoesVerItens.setBackground(Color.GRAY);
        tituloBotoesVerItens.setForeground(Color.WHITE);
        tituloBotoesVerItens.setEditable(false);
        //Adiciona o texto na tela
        adicionarComponentePainel(tituloBotoesVerItens, painelLeste, layoutLeste,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                linha, 0, 2, 2);
        
        for (JButton botao : botoesItensJogador.values()) {
            adicionarComponentePainel(botao, painelLeste, layoutLeste,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                linha, 0, 2, 2);
            linha += 2;
        }
        
    }
    
    private void adicionaBotoesItensAmbientes(){
        int linha = 0;
        
        //Imprime o titulo dos botões de itens na tela
        tituloBotoesItens = new JTextArea(" ITENS AMBIENTES ");
        tituloBotoesItens.setFont(new Font("Serif", Font.ITALIC, 18));
        tituloBotoesItens.setBackground(Color.GRAY);
        tituloBotoesItens.setForeground(Color.WHITE);
        tituloBotoesItens.setEditable(false);
        //Adiciona o texto na tela
        adicionarComponentePainel(tituloBotoesItens, painelLeste, layoutLeste,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                linha, 0, 2, 2);

        for (JButton botao : botoesItensAmbientes.values()) {
            adicionarComponentePainel(botao, painelLeste, layoutLeste,
                GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL,
                linha, 0, 2, 2);
            linha += 2;
        }
    }
    
    private void adicionaBotoesAmbientes(){
        int linha = 8;
        //Imprime o texto na tela
        tituloBotoesAmbientes = new JTextArea(" NAVEGACAO ENTRE\n       AMBIENTES ");
        tituloBotoesAmbientes.setFont(new Font("Serif", Font.ITALIC, 18));
        tituloBotoesAmbientes.setBackground(Color.GRAY);
        tituloBotoesAmbientes.setForeground(Color.WHITE);
        tituloBotoesAmbientes.setEditable(false);
        //Adiciona o texto na tela
        adicionarComponentePainel(tituloBotoesAmbientes, painelOeste, layoutOeste,
                GridBagConstraints.CENTER,
                GridBagConstraints.VERTICAL,
                linha, 0, 2, 2);
        linha += 2;
        
        for (JButton botao : botoesAmbientes.values()) {
            adicionarComponentePainel(botao, painelOeste, layoutOeste,
                    GridBagConstraints.CENTER,
                    GridBagConstraints.VERTICAL,
                    linha, 0, 2, 2);
            linha += 2;
        }
        
    }
    
    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentesTelaJogo(){
        imagemPrincipal = "/br/ufla/dcc/gcc178/s2017_01/trabalho2/supernatural/imagens/principal.jpeg";
        adicionarImagemAmbiente(imagemPrincipal);
        
        //Gerenciador do Jogo
        regraNegocio = new RegraNegocio();
        
        botoesAmbientes = new HashMap<>();
        
        botoesItensAmbientes = new HashMap<>();
        
        botoesItensJogador = new HashMap<>();
        
        //Analisador de comandos do jogo
        analisador = new Analisador();
        
        //Imprime os textos da regra de negocios
        textoDinamico = new JTextArea(regraNegocio.mensagemBoasVindas());
        textoDinamico.setEditable(false);
        
        //Adiciona barra de rolagem ao texto
        jScrollPaneSaida = new JScrollPane(textoDinamico);
        
        //seta o tamanho do campo de texto
        jScrollPaneSaida.setPreferredSize(new Dimension(600, 400));
        
        //Adiciona o JScrollPane do texto dinamico da tela
        adicionarComponentePainel(jScrollPaneSaida, painelCentral, layoutCentral,
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
        adicionarComponentePainel(txtEntradaComandos, painelCentral, layoutCentral,
                GridBagConstraints.SOUTH,
                GridBagConstraints.NONE,
                2, 0, 1, 1);
        
        criarBotoes(regraNegocio.getAmbientes());
        adicionaBotoesAmbientes();
        adicionaBotoesItensAmbientes();
        adicionarBotoesItensJogador();
        botoesEstaticos();
        painelOrientacaoJogador();
        prepararComponentesEstadoInicial();        
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        
        //Loop responsavel pelas ações dos botões de itens do Jogador
    Set<String> chavesItensJogador = botoesItensJogador.keySet();
    for (String chave : chavesItensJogador) {
        botoesItensJogador.get(chave).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoExibicao;
                comando = analisador.pegarComando("guardar " + chave);
                textoExibicao = regraNegocio.processarComando(comando);
                if(textoExibicao.contains(chave + " guardado com sucesso")){
                    textoDinamico.setText(textoExibicao);
                }else{
                    textoDinamico.setText(textoExibicao);
                }
            }
        });
    }
        
        //Loop responsavel pelas ações dos botões Ambientes
        Set<String> chavesbotoesAmbientes = botoesAmbientes.keySet();
        for (String chave : chavesbotoesAmbientes) {
            botoesAmbientes.get(chave).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String validaAmbiente;
                comando = analisador.pegarComando("ir " + chave);
                validaAmbiente = regraNegocio.processarComando(comando);
                if (validaAmbiente.contains("Nao ha passagem!")){
                    textoDinamico.setText("\nNao ha passagem!\n");
                }else{
                    if(regraNegocio.diasRestantes()==0){
                        gameOver();
                    }else{
                        textoDinamico.setText(validaAmbiente);
                        trocaImagemAmbiente(regraNegocio.imagemAmbienteAtual());
                        atualizaPainelPontuacao();
                    }
                }
            }
        });
    }        
    
    //Loop responsavel pelas ações dos botões de itens dos ambientes
    Set<String> chavesItensAmbientes = botoesItensAmbientes.keySet();
    for (String chave : chavesItensAmbientes) {
        botoesItensAmbientes.get(chave).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comando = analisador.pegarComando("pegar " + chave);
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