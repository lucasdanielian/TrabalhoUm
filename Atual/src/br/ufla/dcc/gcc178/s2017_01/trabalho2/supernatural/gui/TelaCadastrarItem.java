/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.gui;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.interacaousuario.TelaPrincipal;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    
    //
    private Item item;

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
        configurarEventosTela();
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
        
        try{
            BufferedReader arq = new BufferedReader(new FileReader("persistencias/ambientes.txt"));
            
            String linha = arq.readLine();
            
            while(linha!=null){
                
                String aux = linha;
                dropAmbienteInicial.addItem(linha);
                linha = arq.readLine();
            }
        }
        catch(Exception e){
            
        }

        spDescricao =  new JScrollPane(caixaDescricao);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBackground(Color.green);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBackground(Color.red);
        
        tela.pack();
    }

    /**
     * Configura os eventos da tela.
     */
    private void configurarEventosTela() {
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tela.dispose();
            }
        });
        
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(caixaNome.getText().length() != 0 && caixaDescricao.getText().length() !=0 && !(dropAmbienteInicial.getSelectedItem().equals("Selecione um ambiente"))){
                    
                    item = new Item(caixaNome.getText(), caixaDescricao.getText());

                    try{
                        FileWriter arq = new FileWriter("persistencias/itens.txt", true);
                        arq.write(item.getNomeItem() + "," + item.getDescricao() + "," + dropAmbienteInicial.getSelectedItem() + "\n");
                        arq.close();
                        
                        JOptionPane.showMessageDialog(tela,"Item cadastrado com sucesso" ,"Confirmação" , 2);
                        
                        caixaNome.setText("");
                        caixaDescricao.setText("");
                        dropAmbienteInicial.setSelectedItem("Selecione um ambiente");
                    }  
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(tela, ex.getMessage());
                    }

                }
                else{
                    
                    JOptionPane.showMessageDialog(tela, "Preencha todos os campos", "Erro", 0);
                }
            }
        });
    }
    
    private void montarJanela(){

        tela.setSize(300, 400);
        tela.setResizable(false);
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

        labelAmbienteInicial.setBounds(10, 200, 150,20);
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
