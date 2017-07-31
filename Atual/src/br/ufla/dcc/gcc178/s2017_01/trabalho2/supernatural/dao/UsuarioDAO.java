package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.dao;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.modelo.Usuario;
import java.io.Serializable;

/**
 * Interface do Data Access Object (Padrão de Projeto) do Usuário
 * 
 * @author Paulo Jr. e Julio Alves
 */
public interface UsuarioDAO {
    /**
     * Retorna o usuário a partir de seu login
     * 
     * @param login Login do usuário a ser retornado.
     * @return Usuário correspondente ao login passado.
     */
    public Usuario obterUsuarioPeloLogin(String login);
    
    /**
     * Cadastra o usuário passado.
     * 
     * @param usuario Usuário a ser cadastrado.
     */
    public void adicionarUsuario(Usuario usuario);
    
}
