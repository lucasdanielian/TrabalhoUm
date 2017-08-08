package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.gui;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.dao.lista.UsuarioDAOLista;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.interacaousuario.TelaPrincipal;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.i18n.I18N;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.modelo.Usuario;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * Classe que representa a tela Meus Filmes
 * 
 * @author Julio Alves e Paulo Jr.
 */
public class TelaRankingJogadores {
    
    private final TelaPrincipal telaPrincipal;
    private UsuarioDAOLista usuarioDAOLista;
            
    // componentes da tela
    private JDialog janela;
    private GridBagLayout layout;
    private GridBagConstraints gbc;
    private JTable tbRankingJogadores;
    private UsuariosTableModel tableModel;

     /**
 * Classe que representa a tela Meus Filmes
 * 
 * @author Julio Alves e Paulo Jr.
 */

    /**
     * Constrói a tela Ranking de Jogadores guardando a referência da tela principal.
     * 
     * @param telaPrincipal Referência da tela principal.
     */
    public TelaRankingJogadores(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    /**
     * Inicializa a tela, construindo seus componentes, configurando os eventos
     * e, ao final, exibe a tela.
     */
    public void inicializar(){

        construirTela();
        exibirTela();
    }

    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTabela() {
        if(tbRankingJogadores == null){
            tbRankingJogadores = new JTable();
            tbRankingJogadores.setModel(getTableModel());
        }
    }
    
    private UsuariosTableModel getTableModel(){
        if(tableModel == null){
            tableModel = new UsuariosTableModel(usuarioDAOLista.getListaUsuario());
        }
        return tableModel;
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
        tbRankingJogadores.clearSelection();
        tbRankingJogadores.setEnabled(true);

    }

    /**
     * Adiciona os componentes da tela tratando layout e internacionalização
     */
    private void adicionarComponentes() {
        construirTabela();
        JScrollPane scrollPaneTabela = new JScrollPane(tbRankingJogadores);
        adicionarComponente(scrollPaneTabela,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                0, 0, 4, 1);

        prepararComponentesEstadoInicial();
    }

    /**
     * Constrói a janela tratando internacionalização, componentes e layout.
     */
    private void construirTela() {
        janela = new JDialog();
        janela.setTitle(I18N.obterTituloTelaRankingJogadores());
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
    
    
    
    
    /**
     * Classe responsavel por construir a tabela
     */
    private class UsuariosTableModel extends AbstractTableModel{
        //Lista de usarios a serem exibidas na tela
        private List<Usuario> linhas;
        
        //Cria um UsuariosTableModel sem nenhuma linha
        public UsuariosTableModel(){
            linhas = new ArrayList<Usuario>();
        }
        
        //Cria um UsuariosTableModel contendo a lista recebida por parametro 
        public UsuariosTableModel(List<Usuario> listaDeUsuarios){
            linhas = new ArrayList<Usuario>(listaDeUsuarios);
        }
        
        //lista com os rotulos das colunas
        private String[] colunas = new String []{I18N.obterRotuloJogador(),
            I18N.obterRotuloDiasRestantes(), I18N.obterRotuloDiasCorridos()};
        
        private static final int NOME = 0;
        private static final int DIAS_RESTANTES = 1;
        private static final int DIAS_CORRIDOS = 2;
        
        @Override
        public int getRowCount() {
            return linhas.size();
        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }

        @Override
        public String getColumnName(int columnIndex){
            return colunas[columnIndex];
        }
        
        @Override
        public Class<?> getColumnClass(int columnIndex){
            switch (columnIndex){
                case NOME:
                    return String.class;
                case DIAS_RESTANTES:
                    return String.class;
                case DIAS_CORRIDOS:
                    return String.class;
                default:
                    //Nao deve ocorrer uma vez que só temos duas colunas
                    throw new IndexOutOfBoundsException("columnIndex out of bounds");
                 
            }
        }
        
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex){
            return false;
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            //Pega o Usuario referente a linha especifica
            Usuario usuario = linhas.get(rowIndex);
            
            switch (columnIndex){
                case NOME:
                    return usuario.obterNome();
                case DIAS_RESTANTES:
                    return usuario.getDiasRestantes();
                case DIAS_CORRIDOS:
                    return usuario.getDiasCorridos();
                default:
                    //Nao deve ocorrer uma vez que só temos duas colunas
                    throw new IndexOutOfBoundsException("columnIndex out of bounds");
            }
        }
        
    }
}