package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Classe que trata a internacionalização do sistema (idiomas)
 *
 * @author Paulo Jr. e Julio Alves
 */
public class I18N {

    // caminho base para os arquivos de internacionalização
    private static final String CAMINHO_ARQUIVOBASE_I18N = "br/ufla/dcc/gcc178/s2017_01/trabalho2/supernatural/i18n/Strings";
    // objeto utilizado para carregar os textos do sistema de acordo com a localidade
    private static ResourceBundle rb = ResourceBundle.getBundle(CAMINHO_ARQUIVOBASE_I18N, Locale.getDefault());
    // indica a localidade (idioma) Português - Brasil

    /**
     *
     */
    public static final Locale PT_BR = new Locale("pt", "BR");
    // indica a localidade (idioma) Inglês - Americano

    /**
     *
     */
    public static final Locale EN_US = new Locale("en", "US");

    /**
     * Altera a localidade a ser utilizada.
     *
     * @param localidade Nova localidade a ser utilizada (Português - Brasil é a
     * padrão)
     */
    public static void alterarLocalidade(Locale localidade) {
        Locale.setDefault(localidade);
        rb = ResourceBundle.getBundle(CAMINHO_ARQUIVOBASE_I18N, localidade);
    }

    /**
     * Retorna o nome do sistema.
     *
     * @return Nome do sistema.
     */
    public static String obterNomeDoSistema() {
        return rb.getString("sistema.nome");
    }

    /**
     * Retorna o texto do menu Início.
     *
     * @return Texto do menu Início.
     */
    public static String obterMenuInicio() {
        return rb.getString("menu.inicio");
    }
    
    /**
     * Retorna o texto do menu Cadastar Item.
     *
     * @return Texto do menu Cadastrar Item.
     */
    public static String obterMenuCadastrarItem() {
        return rb.getString("menu.cadastrar_item");
    }

    /**
     * Retorna o mnemônico do menu Início.
     *
     * @return Mnemônico do menu Início.
     */
    public static char obterMnemonicoMenuInicio() {
        return rb.getString("mnemonico.menu.inicio").charAt(0);
    }

    /**
     * Retorna o texto do menu Entrar.
     *
     * @return Texto do menu Entrar.
     */
    public static String obterMenuEntrar() {
        return rb.getString("menu.inicio.entrar");
    }

    /**
     * Retorna o texto do menu Meus Filmes.
     *
     * @return Texto do menu Meus Filmes.
     */
    public static String obterMenuMorteSubita() {
        return rb.getString("menu.inicio.morte_subita");
    }
    
    /**
     * Retorna o texto do menu RankingJogadores.
     *
     * @return Texto do menu Ranking Jogadores.
     */
    public static String obterMenuRankingJogadores() {
        return rb.getString("menu.inicio.ranking_Jogadores");
    }

    /**
     * Retorna o texto do menu Cadastrar Usuário.
     *
     * @return Texto do menu Cadastrar Usuário.
     */
    public static String obterMenuCadastrarUsuario() {
        return rb.getString("menu.inicio.cadastrar");
    }

    /**
     * Retorna o texto do menu Sair.
     *
     * @return Texto do menu Sair.
     */
    public static String obterMenuSair() {
        return rb.getString("menu.inicio.sair");
    }

    /**
     * Retorna o texto do menu Logout.
     *
     * @return Texto do menu Logout.
     */
    public static String obterMenuLogout() {
        return rb.getString("menu.inicio.logout");
    }

    /**
     * Retorna o texto do menu Idioma.
     *
     * @return Texto do menu Idioma.
     */
    public static String obterMenuIdioma() {
        return rb.getString("menu.idioma");
    }

    /**
     * Retorna o mnemônico do menu Idioma.
     *
     * @return Mnemônico do menu Idioma.
     */
    public static char obterMnemonicoMenuIdioma() {
        return rb.getString("mnemonico.menu.idioma").charAt(0);
    }

    /**
     * Retorna o texto do menu Idioma Português.
     *
     * @return Texto do menu Idioma Português.
     */
    public static String obterMenuIdiomaPortugues() {
        return rb.getString("menu.idioma.pt_br");
    }
    
    /**
     * Retorna o texto do menu Cadastrar Ambiente
     * @return texto do menu cadastrar ambiente
     */
    public static String obterMenuCadastrarAmbiente() {
        return rb.getString("menu.cadastrar_ambiente");
    }

    /**
     * Retorna o texto do menu Idioma Inglês.
     *
     * @return Texto do menu Idioma Inglês.
     */
    public static String obterMenuIdiomaIngles() {
        return rb.getString("menu.idioma.en_us");
    }

    /**
     * Retorna o texto do menu Ajuda.
     *
     * @return Texto do menu Ajuda.
     */
    public static String obterMenuAjuda() {
        return rb.getString("menu.ajuda");
    }

    /**
     * Retorna o mnemônico do menu Ajuda.
     *
     * @return Mnemônico do menu Ajuda.
     */
    public static char obterMnemonicoMenuAjuda() {
        return rb.getString("mnemonico.menu.ajuda").charAt(0);
    }

    /**
     * Retorna o texto do menu Sobre.
     *
     * @return Texto do menu Sobre.
     */
    public static String obterMenuSobre() {
        return rb.getString("menu.ajuda.sobre");
    }

    /**
     * Retorna o texto da mensagem de confirmação de saída do sistema.
     *
     * @return Texto da mensagem de confirmação de saída do sistema.
     */
    public static String obterConfirmacaoSaida() {
        return rb.getString("confirmacao.saida.descricao");
    }

    /**
     * Retorna o texto da mensagem de confirmação ao deletar.
     *
     * @return Texto da mensagem de confirmação ao deletar.
     */
    public static String obterConfirmacaoDeletar() {
        return rb.getString("confirmacao.deletar.descricao");
    }

    /**
     * Retorna o texto da mensagem de erro de autencicação
     *
     * @return Texto da mensagem de erro de autencicação
     */
    public static String obterErroAutenticacao() {
        return rb.getString("erro.usuario.autenticacao");
    }

    /**
     * Retorna o texto da mensagem de usuário já cadastrado.
     *
     * @return Texto da mensagem de usuário já cadastrado.
     */
    public static String obterErroUsuarioJaCadastrado() {
        return rb.getString("erro.usuario.ja_cadastrado");
    }

    /**
     * Retorna o texto da mensagem de senhas não conferem.
     *
     * @return Texto da mensagem de senhas não conferem.
     */
    public static String obterErroSenhasNaoConferem() {
        return rb.getString("erro.usuario.senhas_nao_conferem");
    }

    /**
     * Retorna o texto da mensagem de cadastro de usuário efetuado com sucesso.
     *
     * @return Texto da mensagem de cadastro de usuário efetuado com sucesso.
     */
    public static String obterSucessoCadastroUsuario() {
        return rb.getString("sucesso.usuario.cadastro");
    }

    /**
     * Retorna o título da mensagem de confirmação.
     *
     * @return Título da mensagem de confirmação.
     */
    public static String obterTituloMensagemConfirmacao() {
        return rb.getString("confirmacao.titulo");
    }

    /**
     * Retorna o texto da mensagem Sobre.
     *
     * @return Texto da mensagem Sobre.
     */
    public static String obterMensagemSobre() {
        return rb.getString("sistema.sobre");
    }

    /**
     * Retorna o título da mensagem de informação.
     *
     * @return Título da mensagem de informação.
     */
    public static String obterTituloMensagemInformacao() {
        return rb.getString("informacao.titulo");
    }

    /**
     * Retorna o título da mensagem de erro.
     *
     * @return Título da mensagem de erro.
     */
    public static String obterTituloMensagemErro() {
        return rb.getString("erro.titulo");
    }

    /**
     * Retorna o título da tela de autenticação.
     *
     * @return Título da tela de autenticação.
     */
    public static String obterTituloTelaAutenticacao() {
        return rb.getString("tela.autenticacao.titulo");
    }

    /**
     * Retorna o título da tela de principal.
     *
     * @return Título da tela de principal.
     */
    public static String obterTituloTelaPrincipal() {
        return obterNomeDoSistema();
    }

    /**
     * Retorna o título da tela de Cadastro de Usuários.
     *
     * @return Título da tela de Cadastro de Usuários.
     */
    public static String obterTituloTelaCadastrarUsuario() {
        return rb.getString("tela.cadastrousuario.titulo");
    }

    /**
     * Retorna o texto do rótulo login do usuário.
     *
     * @return Texto do rótulo login do usuário.
     */
    public static String obterRotuloUsuarioLogin() {
        return rb.getString("rotulo.usuario.login");
    }

    /**
     * Retorna o texto do rótulo senha do usuário.
     *
     * @return Texto do rótulo senha do usuário.
     */
    public static String obterRotuloUsuarioSenha() {
        return rb.getString("rotulo.usuario.senha");
    }

    /**
     * Retorna o texto do botão Entrar (logar).
     *
     * @return Texto do botão Entrar (logar).
     */
    public static String obterBotaoEntrar() {
        return rb.getString("botao.entrar");
    }

    /**
     * Retorna o texto do botão Cancelar.
     *
     * @return Texto do botão Cancelar.
     */
    public static String obterBotaoCancelar() {
        return rb.getString("botao.cancelar");
    }

    /**
     * Retorna o texto do botão Salvar.
     *
     * @return Texto do botão Salvar.
     */
    public static String obterBotaoSalvar() {
        return rb.getString("botao.salvar");
    }

    /**
     * Retorna o texto do botão Novo.
     *
     * @return Texto do botão Novo.
     */
    public static String obterBotaoNovo() {
        return rb.getString("botao.novo");
    }

    /**
     * Retorna o texto do botão Editar.
     *
     * @return Texto do botão Editar.
     */
    public static String obterBotaoEditar() {
        return rb.getString("botao.editar");
    }

    /**
     * Retorna o texto do botão Excluir (deletar).
     *
     * @return Texto do botão Excluir (deletar).
     */
    public static String obterBotaoDeletar() {
        return rb.getString("botao.deletar");
    }

    /**
     * Retorna o texto do rótulo confirmar senha do usuário.
     *
     * @return Texto do rótulo confirmar senha do usuário.
     */
    public static String obterRotuloUsuarioConfirmarSenha() {
        return rb.getString("rotulo.usuario.confirmar_senha");
    }
    
    /**
     * Retorna o título da tela de Meus Filmes.
     *
     * @return Título da tela de Meus Filmes.
     */
    public static String obterTituloTelaMeuJogo() {
        return rb.getString("tela.meujogo.titulo");
    }
    
    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao ItemPortadorAlmas
     */
    public static String obterTituloTelaRankingJogadores() {
        return rb.getString("tela.ranking.titulo");
    }

    /**
     * Retorna o texto do rótulo nome do usuário.
     *
     * @return Texto do rótulo nome do usuário.
     */
    public static String obterRotuloUsuarioNome() {
        return rb.getString("rotulo.usuario.nome");
    }

    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao enviar
     */
    public static String obterBotaoEnviar() {
        return rb.getString("botao.enviar");
    }

    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao Winchester
     */
    public static String obterBotaoWinchester() {
        return rb.getString("botao.winchester");
    }

    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao Denver
     */
    public static String obterBotaoDenver() {
        return rb.getString("botao.Denver");
    }

    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao Houston
     */
    public static String obterBotaoHouston() {
        return rb.getString("botao.houston");
    }

    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao CasaCaim
     */
    public static String obterBotaoCasaCaim() {
        return rb.getString("botao.casa_caim");
    }

    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao CasaBob
     */
    public static String obterBotaoCasaBob() {
        return rb.getString("botao.CasaBob");
    }

    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao Inferno
     */
    public static String obterBotaoInferno() {
        return rb.getString("botao.inferno");
    }

    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao Purgatorio
     */
    public static String obterBotaoPurgatorio() {
        return rb.getString("botao.purgatorio");
    }

    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao Ceu
     */
    public static String obterBotaoCeu() {
        return rb.getString("botao.ceu");
    }
    
    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao Ver Itens Ambiente
     */
    public static String obterBotaoVerItensAmbiente() {
        return rb.getString("botao.ver_itens_ambiente");
    }

    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao Ver Itens Mochila
     */
    public static String obterBotaoVerItensMochila() {
        return rb.getString("botao.ver_itens_mochila");
    }
    
    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao ItenCarta
     */
    public static String obterBotaoItenCarta() {
        return rb.getString("botao.iten_carta");
    }
    
    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao ItenPena
     */
    public static String obterBotaoItenPena() {
        return rb.getString("botao.iten_pena");
    }
    
    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao ItenDenteLobo
     */
    public static String obterBotaoItenDenteLobo() {
        return rb.getString("botao.iten_dente_lobo");
    }

    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao ItenCabecaVampiro
     */
    public static String obterBotaoItenCabecaVampiro() {
        return rb.getString("botao.iten_cabeca_vampiro");
    }

    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao ItemPortadorAlmas
     */
    public static String obterBotaoItemPortadorAlmas() {
        return rb.getString("botao.iten_portadorAlma");
    }

    /**
     * Retorna o texto do rótulo com o nome do botao
     * @return Texto do rótulo botao Recuperarjogo
     */
    public static String obterBotaoRecuperarjogo() {
        return rb.getString("botao.recuperar_jogo");
    }

    /**
     * Retorna o titulo da linha da tabela de ranking.
     *
     * @return Retorna o titulo da linha da tabela de ranking.
     */
    public static String obterRotuloDiasRestantes() {
        return rb.getString("tabela.dias_restantes");
    }

    /**
     * Retorna o titulo da linha da tabela de ranking.
     *
     * @return Retorna o titulo da linha da tabela de ranking.
     */
    public static String obterRotuloDiasCorridos() {
        return rb.getString("tabela.dias_corridos");
    }

    /**
     * Retorna o titulo da linha da tabela de ranking.
     *
     * @return Retorna o titulo da linha da tabela de ranking.
     */
    public static String obterRotuloJogador() {
        return rb.getString("tabela.jogador");
    }

}
