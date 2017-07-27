/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.persistencia;

/**
 *
 * @author junior
 */
public interface Serializacao {
    
   /**
     * Salva o estado atual do jogo serializando o arquivo
     */ 
   void escritaArquivo();

    /**
     * Recupera o estado do ultimo jogo salvo de um arquivo serializado 
     */
    void leituraArquivo();
}
