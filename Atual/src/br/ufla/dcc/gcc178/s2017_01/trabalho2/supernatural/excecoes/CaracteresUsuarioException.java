/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.excecoes;

/**
 *
 * @author junior
 */
public class CaracteresUsuarioException extends RuntimeException{


    public CaracteresUsuarioException() {
        
    }

    @Override
    public String getMessage() {
        return "Nomes de usuário devem ter no mínimo 4 caracteres e no máximo 20.";
    }
    
}
