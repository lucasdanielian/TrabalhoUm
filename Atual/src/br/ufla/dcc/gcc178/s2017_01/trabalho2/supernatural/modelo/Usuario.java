package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.modelo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Representa um usuário do sistema.
 * 
 * @author Paulo Jr. e Julio Alves
 */
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    // login do usuário
    private String login;
    // senha do usuário
    private char[] senha;
    // nome do usuário
    private String nome;
    //Dias gastos pelo usuario para zerar ou tentar zerar o jogo.
    private int diasCorridos;
    //Dias restantes para o usuario tentar zerar o jogo. 
    private int diasRestantes;
    /**
     * Constrói um usuário a partir de seu login, senha e nome.
     * 
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param nome Nome do usuário.
     */
    public Usuario(String login, char[] senha, String nome) {
            this.login = login;
            this.senha = Arrays.copyOf(senha, senha.length);
            this.nome = nome;
            diasCorridos = 0;
            diasRestantes = 0;
    }
    
    /**
     * Constrói um usuário a partir de seu login e senha (deixa nome vazio).
     * 
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     */
    public Usuario(String login, char[] senha) {
        this(login, senha, "");
    }

    /**
     * Retorna o login do usuário.
     * 
     * @return O login do usuário.
     */
    public String obterLogin() {
        return login;
    }

    /**
     * Retorna a senha do usuário.
     * 
     * @return A senha do usuário.
     */
    public char[] obterSenha() {
        return senha;
    }

    /**
     * Retorna o nome do usuário.
     * 
     * @return O nome do usuário.
     */
    public String obterNome() {
        return nome;
    }

    /**
     * Metodo utilizado para saber quantos dias o jogador já utilizou no jogo
     * @return um inteiro contendo os dias corridos.
     */
    public int getDiasCorridos() {
        return diasCorridos;
    }

    /**
     * Metodo que altera os dias corridos do usuario ao tentar finalizar o jogo
     * @param diasCorridos recebe um inteiro contendo os dias corridos
     */
    public void setDiasCorridos(int diasCorridos) {
        this.diasCorridos = diasCorridos;
    }

    /**
     * Metodo que retorna quantos dias faltam para o usuario zerar o jogo
     * @return um inteiro contendo os dias;
     */
    public int getDiasRestantes() {
        return diasRestantes;
    }

    /**
     * Metodo que atualiza o dias restantes para o jogador zerar o jogo
     * @param diasRestantes  recebe um inteiro contendo os dias
     */
    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
    }
    
    
    
}
