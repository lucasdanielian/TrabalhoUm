package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.servicos;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.dao.UsuarioDAO;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.dao.lista.UsuarioDAOLista;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.excecoes.CaracteresUsuarioException;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.i18n.I18N;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.modelo.Usuario;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.seguranca.SessaoUsuario;

/**
 * Classe que representa a camada de negócios de cadastro de usuários. Permite
 * cadastrar e autenticar um usuário.
 * 
 * @author Paulo Jr. e Julio Alves
 */
public class GerenciadorUsuarios {

    // atributo utilizado como camada de acesso a dados do cadastro de usuários
    private final UsuarioDAO repositorioUsuario;
    // atributo para controle de sessão (autenticação do usuário)
    private final SessaoUsuario sessaoUsuario;

    /**
     * Constroi o gerenciador de usuários, inicializando as camadas de acesso a 
     * dados e de sessão.
     */
    public GerenciadorUsuarios() {
        repositorioUsuario = UsuarioDAOLista.obterInstancia();
        sessaoUsuario = SessaoUsuario.obterInstancia();
    }

    /**
     * Tenta autenticar o usuário passado no sistema.
     * 
     * @param usuario Usuário a ser autenticado
     * @throws Exception Exceção gerada caso o usuário não possa ser autenticado,
     * ou seja, usuário não existe ou senha incorreta.
     */
    public void autenticarUsuario(Usuario usuario) throws Exception {
        Usuario usuarioCadastrado = 
                repositorioUsuario.obterUsuarioPeloLogin(usuario.obterLogin());
        
        sessaoUsuario.alterarUsuario(usuarioCadastrado, usuario.obterSenha());
    }

    /**
     * Cadastra o usuário passado no sistema.
     * 
     * @param usuario Usuário a ser cadastrado.
     * @throws Exception Exceção gerada caso o usuário já esteja cadastrado.
     */
    public void cadastrarUsuario(Usuario usuario) throws Exception {
        Usuario ret = repositorioUsuario.obterUsuarioPeloNome(usuario.obterNome());
        if (ret != null ) {
            throw new Exception(I18N.obterErroUsuarioJaCadastrado());
        }else if(usuario.obterNome().length() < 4 || usuario.obterNome().length() > 19){
            throw new CaracteresUsuarioException();
        }else{
            repositorioUsuario.adicionarUsuario(usuario);
        }   
    }
}
