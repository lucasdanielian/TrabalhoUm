/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.gui;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.interacaousuario.TelaPrincipal;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Lucas
 */
public class TelaCadastrarItem {
    
    // referência para a tela principal
    private final TelaPrincipal telaPrincipal;

    private JFrame tela;

    private JLabel labelNome;
    private JLabel labelDescricao;
    private JLabel labelAmbienteInicial;

    private JTextField caixaNome;
    private JTextArea caixaDescricao;
    private JComboBox dropAmbienteInicial;
    private JScrollPane spDescricao;

    private JButton botaoSalvar;
    private JButton botaoCancelar;

    public TelaCadastrarItem(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    /**
     * Inicializa a tela, construindo seus componentes, configurando os eventos
     * e, ao final, exibe a tela.
     */
    public void inicializar(){
        criarComponentes();
        montarJanela();
        exibirTela();
    }
    
    private void criarComponentes(){

        tela = new JFrame("Cadastrar Item");
        labelNome = new JLabel("Nome:");
        labelDescricao = new JLabel("Descrição:");
        labelAmbienteInicial = new JLabel("Ambiente Inicial:");

        caixaNome = new JTextField();
        caixaDescricao = new JTextArea();
        dropAmbienteInicial = new JComboBox();

        dropAmbienteInicial.addItem("Selecione um ambiente");

        dropAmbienteInicial.addItem("CasaBob");
        dropAmbienteInicial.addItem("CasaCaim");
        dropAmbienteInicial.addItem("CasaWinchester");
        dropAmbienteInicial.addItem("Ceu");
        dropAmbienteInicial.addItem("Denver");
        dropAmbienteInicial.addItem("Houston");
        dropAmbienteInicial.addItem("Inferno");
        dropAmbienteInicial.addItem("Purgatorio");

        spDescricao =  new JScrollPane(caixaDescricao);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBackground(Color.green);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBackground(Color.red);
    }

    private void montarJanela(){

        tela.setSize(300, 400);
        tela.setResizable(false);
        tela.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tela.setLayout(null);

        labelNome.setBounds(10, 20, 60, 20);
        tela.getContentPane().add(labelNome);

        caixaNome.setBounds(60,15,200,30);
        tela.getContentPane().add(caixaNome);

        labelDescricao.setBounds(10, 70, 80, 20);
        tela.getContentPane().add(labelDescricao);

        spDescricao.setBounds(20,100, 260, 80);
        tela.getContentPane().add(spDescricao);
        //tela.getContentPane().add(caixaDescricao);

        labelAmbienteInicial.setBounds(10, 200, 100,20);
        tela.getContentPane().add(labelAmbienteInicial);

        dropAmbienteInicial.setBounds(45, 230, 200, 20);
        tela.getContentPane().add(dropAmbienteInicial);

        botaoSalvar.setBounds(35,300,100,30);
        tela.getContentPane().add(botaoSalvar);

        botaoCancelar.setBounds(150,300,100,30);
        tela.getContentPane().add(botaoCancelar);

    }

    public void exibirTela(){
        tela.setVisible(true);
    }
    
}