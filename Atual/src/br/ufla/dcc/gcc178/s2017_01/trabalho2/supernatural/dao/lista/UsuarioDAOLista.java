package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.dao.lista;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.dao.UsuarioDAO;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.modelo.Usuario;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.persistencia.Serializacao;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Implementação do Data Access Object (Padrão de Projeto) do Usuário através de
 * Lista em memória
 * 
 * @author Paulo Jr. e Julio Alves
 */
public class UsuarioDAOLista implements Serializable, UsuarioDAO, Serializacao{

    // instância única da classe (Padrão de Projeto Singleton)
    private static UsuarioDAOLista instancia;
    
    // lista em em memória dos usuários cadastrados
    private static List<Usuario> listaUsuario;
    
    private String diretorioPersistencia;

    /**
     * Constrói o objeto já definindo 5 usuários padrões
     */
    private UsuarioDAOLista() {
        listaUsuario = new ArrayList<Usuario>();
        diretorioPersistencia = "persistencias/usuarios.dat";
        leituraArquivo();

    }

    /**
     * Retorna a instância única da classe (Padrão de Projeto Singleton)
     * 
     * @return A instância única da classe
     */
    public static UsuarioDAOLista obterInstancia() {
        if (instancia == null) {
            instancia = new UsuarioDAOLista();
        }
        return instancia;
    }

    /**
     * Retorna o usuário a partir de seu login
     * 
     * @param login Login do usuário a ser retornado.
     * @return Usuário correspondente ao login passado.
     */
    @Override
    public Usuario obterUsuarioPeloLogin(String login) {
        for (Usuario u : listaUsuario) {
            if (login.equals(u.obterLogin())) {
                return u;
            }
        }
        return null;
    }

    /**
     * Metodo que retorna os usuarios cadastrados no sistema
     * @return uma lista contendo os usuarios cadastrados
     */
    public static List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    /**
     * Retorna o usuário a partir de seu nome
     * 
     * @return Usuário correspondente ao nome passado.
     */
    @Override
    public Usuario obterUsuarioPeloNome(String nome) {
        for (Usuario u : listaUsuario) {
            if (nome.equals(u.obterNome())) {
                return u;
            }
        }
        return null;
    }

    /**
     * Cadastra o usuário passado.
     * 
     * @param usuario Usuário a ser cadastrado.
     */
    @Override
    public void adicionarUsuario(Usuario usuario) {
        listaUsuario.add(usuario);
        escritaArquivo();
    }
    
    /**
     * Salva o estado atual do jogo serializando o arquivo
     * @return retorna true se salvo e false caso ao contrario
     */
    @Override
    public boolean escritaArquivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(diretorioPersistencia));
            oos.writeObject(listaUsuario);
            oos.close();
            return true;
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    /**
     * Recupera o estado do ultimo jogo salvo
     * @return retorna true se efetuou a leitura e false caso ao contrario
     */
    @Override
    public boolean leituraArquivo() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(diretorioPersistencia));
            listaUsuario = (ArrayList<Usuario>)ois.readObject();
            ois.close();
            return true;
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }
}
