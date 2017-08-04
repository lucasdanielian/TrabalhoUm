/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.gui;

import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes.Ambiente;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.ambientes.AmbienteDefault;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.interacaousuario.TelaPrincipal;
import br.ufla.dcc.gcc178.s2017_01.trabalho2.supernatural.itens.Item;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author diva
 */
public class TelaCadastrarAmbiente {
    
    private final TelaPrincipal telaPrincipal;
    private JFrame tela;
 
        private JLabel labelNome;
        private JLabel labelMensagem;
        private JLabel labelSaidas;
        private JLabel labelImagem;
        private JLabel labelEntradas;
        
        private JFileChooser selecionadorDeImagens;
        
        private JTextField caixaNome;
        private JTextField caixaImagem;
        private JTextField barreira;
        private JTextArea caixaMensagem;
        private JComboBox dropAmbienteSaida1;
        private JComboBox dropAmbienteSaida2;
        private JComboBox dropAmbienteSaida3;
        private JComboBox dropAmbienteSaida4;
        private JScrollPane spDescricao;
        private JComboBox dropAmbienteEntrada1;
        private JComboBox dropAmbienteEntrada2;
        private JComboBox dropAmbienteEntrada3;
        private JComboBox dropAmbienteEntrada4;
        
        private JButton botaoSalvar;
        private JButton botaoCancelar;
        
        private Ambiente ambiente;
        
        public TelaCadastrarAmbiente(TelaPrincipal telaPrincipal) {
            this.telaPrincipal = telaPrincipal;
        }
        
        public void inicializar(){
            criarComponentes();
            montarJanela();
            configurarEventosTela();
            exibirTela();
        }
        
        private void criarComponentes(){
            
            Font myFont = new Font("Arial", Font.BOLD, 16);
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png");
            
            tela = new JFrame("Cadastrar Ambiente");
            labelNome = new JLabel("Nome:");
            labelNome.setFont(myFont);
            labelMensagem = new JLabel("Mensagem de Entrada:");
            labelMensagem.setFont(myFont);
            labelSaidas = new JLabel("Saídas:");
            labelSaidas.setFont(myFont);
            labelEntradas = new JLabel("Entradas:");
            labelEntradas.setFont(myFont);
            labelImagem = new JLabel("Imagem:");
            labelImagem.setFont(myFont);
            selecionadorDeImagens = new JFileChooser();
            selecionadorDeImagens.setFileFilter(filtro);
            
            caixaNome = new JTextField();
            caixaImagem = new JTextField();
            barreira = new JTextField();
            caixaMensagem= new JTextArea();
            dropAmbienteSaida1 = new JComboBox();
            dropAmbienteSaida2 = new JComboBox();
            dropAmbienteSaida3 = new JComboBox();
            dropAmbienteSaida4 = new JComboBox();
            
            
            dropAmbienteSaida1.addItem("Selecione um ambiente");
            dropAmbienteSaida1.addItem("CasaBob");
            dropAmbienteSaida1.addItem("CasaCaim");
            dropAmbienteSaida1.addItem("CasaWinchester");
            dropAmbienteSaida1.addItem("Ceu");
            dropAmbienteSaida1.addItem("Denver");
            dropAmbienteSaida1.addItem("Houston");
            dropAmbienteSaida1.addItem("Inferno");
            dropAmbienteSaida1.addItem("Purgatorio");
            
            dropAmbienteSaida2.addItem("Selecione um ambiente");
            dropAmbienteSaida2.addItem("CasaBob");
            dropAmbienteSaida2.addItem("CasaCaim");
            dropAmbienteSaida2.addItem("CasaWinchester");
            dropAmbienteSaida2.addItem("Ceu");
            dropAmbienteSaida2.addItem("Denver");
            dropAmbienteSaida2.addItem("Houston");
            dropAmbienteSaida2.addItem("Inferno");
            dropAmbienteSaida2.addItem("Purgatorio");
            
            dropAmbienteSaida3.addItem("Selecione um ambiente");
            dropAmbienteSaida3.addItem("CasaBob");
            dropAmbienteSaida3.addItem("CasaCaim");
            dropAmbienteSaida3.addItem("CasaWinchester");
            dropAmbienteSaida3.addItem("Ceu");
            dropAmbienteSaida3.addItem("Denver");
            dropAmbienteSaida3.addItem("Houston");
            dropAmbienteSaida3.addItem("Inferno");
            dropAmbienteSaida3.addItem("Purgatorio");
            
            dropAmbienteSaida4.addItem("Selecione um ambiente");
            dropAmbienteSaida4.addItem("CasaBob");
            dropAmbienteSaida4.addItem("CasaCaim");
            dropAmbienteSaida4.addItem("CasaWinchester");
            dropAmbienteSaida4.addItem("Ceu");
            dropAmbienteSaida4.addItem("Denver");
            dropAmbienteSaida4.addItem("Houston");
            dropAmbienteSaida4.addItem("Inferno");
            dropAmbienteSaida4.addItem("Purgatorio");
            
            dropAmbienteEntrada1 = new JComboBox();
            dropAmbienteEntrada2 = new JComboBox();
            dropAmbienteEntrada3 = new JComboBox();
            dropAmbienteEntrada4 = new JComboBox();
            
            dropAmbienteEntrada1.addItem("Selecione um ambiente");
            dropAmbienteEntrada1.addItem("CasaBob");
            dropAmbienteEntrada1.addItem("CasaCaim");
            dropAmbienteEntrada1.addItem("CasaWinchester");
            dropAmbienteEntrada1.addItem("Ceu");
            dropAmbienteEntrada1.addItem("Denver");
            dropAmbienteEntrada1.addItem("Houston");
            dropAmbienteEntrada1.addItem("Inferno");
            dropAmbienteEntrada1.addItem("Purgatorio");
            
            dropAmbienteEntrada2.addItem("Selecione um ambiente");
            dropAmbienteEntrada2.addItem("CasaBob");
            dropAmbienteEntrada2.addItem("CasaCaim");
            dropAmbienteEntrada2.addItem("CasaWinchester");
            dropAmbienteEntrada2.addItem("Ceu");
            dropAmbienteEntrada2.addItem("Denver");
            dropAmbienteEntrada2.addItem("Houston");
            dropAmbienteEntrada2.addItem("Inferno");
            dropAmbienteEntrada2.addItem("Purgatorio");
            
            dropAmbienteEntrada3.addItem("Selecione um ambiente");
            dropAmbienteEntrada3.addItem("CasaBob");
            dropAmbienteEntrada3.addItem("CasaCaim");
            dropAmbienteEntrada3.addItem("CasaWinchester");
            dropAmbienteEntrada3.addItem("Ceu");
            dropAmbienteEntrada3.addItem("Denver");
            dropAmbienteEntrada3.addItem("Houston");
            dropAmbienteEntrada3.addItem("Inferno");
            dropAmbienteEntrada3.addItem("Purgatorio");
            
            dropAmbienteEntrada4.addItem("Selecione um ambiente");
            dropAmbienteEntrada4.addItem("CasaBob");
            dropAmbienteEntrada4.addItem("CasaCaim");
            dropAmbienteEntrada4.addItem("CasaWinchester");
            dropAmbienteEntrada4.addItem("Ceu");
            dropAmbienteEntrada4.addItem("Denver");
            dropAmbienteEntrada4.addItem("Houston");
            dropAmbienteEntrada4.addItem("Inferno");
            dropAmbienteEntrada4.addItem("Purgatorio");
            
            try{
            BufferedReader arq = new BufferedReader(new FileReader("ambientes.txt"));
            
            String linha = arq.readLine();
            
                while(linha!=null){

                    String aux = linha;
                    dropAmbienteSaida1.addItem(linha);
                    dropAmbienteSaida2.addItem(linha);
                    dropAmbienteSaida3.addItem(linha);
                    dropAmbienteSaida4.addItem(linha);
                    dropAmbienteEntrada1.addItem(linha);
                    dropAmbienteEntrada2.addItem(linha);
                    dropAmbienteEntrada3.addItem(linha);
                    dropAmbienteEntrada4.addItem(linha);
                    linha = arq.readLine();
                }
            }
            catch(Exception e){

            }

                spDescricao =  new JScrollPane(caixaMensagem);

                botaoSalvar = new JButton("Salvar");
                botaoSalvar.setBackground(Color.green);

                botaoCancelar = new JButton("Cancelar");
                botaoCancelar.setBackground(Color.red);
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
        
       
        
        botaoSalvar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(caixaNome.getText().length() != 0 && caixaMensagem.getText().length()
                        !=0 &&
                        !(dropAmbienteEntrada1.getSelectedItem().equals("Selecione um ambiente")) &&
                        !(dropAmbienteSaida1.getSelectedItem().equals("Selecione um ambiente"))){
                  
                  String aux = caixaNome.getText().replaceAll(" ", "");
 
                    try{
                        FileWriter arq = new FileWriter("ambientes.txt", true);
                        arq.write(aux + "," + caixaMensagem.getText() + "\n");
                        arq.close();
                        
                        JOptionPane.showMessageDialog(tela,"Ambiente cadastrado com sucesso" ,"Confirmação" , 2);
                        
                        caixaNome.setText("");
                        caixaMensagem.setText("");
                        
                    }  
                    catch(Exception ex){

                    }

                }
                else{
                    
                    JOptionPane.showMessageDialog(tela, "Preencha todos os campos", "Erro", 0);
                }
                
           
        }   
                
    }); 
    
    }
        
        private void montarJanela(){
            
            tela.setSize(800, 600);
            tela.setResizable(false);
            tela.setLayout(null);
            
            labelNome.setBounds(10, 20, 60, 20);
            tela.getContentPane().add(labelNome);
            
            caixaNome.setBounds(70,15,360,30);
            tela.getContentPane().add(caixaNome);
            
            labelImagem.setBounds(10,70,80,20);
            tela.getContentPane().add(labelImagem);
            
            //caixaImagem.setBounds(70,65,250,30);
            //tela.getContentPane().add(caixaImagem);
            selecionadorDeImagens.setBounds(10,100,430,450);
            tela.getContentPane().add(selecionadorDeImagens);
            
            barreira.setBounds(445,10,2,550);
            tela.getContentPane().add(barreira);
            
            labelMensagem.setBounds(460, 240, 250, 20);
            tela.getContentPane().add(labelMensagem);
            
            spDescricao.setBounds(480,280, 300, 150);
            tela.getContentPane().add(spDescricao);
            //tela.getContentPane().add(caixaDescricao);

            labelSaidas.setBounds(460, 10, 100,20);
            tela.getContentPane().add(labelSaidas);
            
            dropAmbienteSaida1.setBounds(460, 40, 160, 20);
            tela.getContentPane().add(dropAmbienteSaida1);
            
            dropAmbienteSaida2.setBounds(630, 40, 160, 20);
            tela.getContentPane().add(dropAmbienteSaida2);
            
            dropAmbienteSaida3.setBounds(460, 75, 160, 20);
            tela.getContentPane().add(dropAmbienteSaida3);
            
            dropAmbienteSaida4.setBounds(630, 75, 160, 20);
            tela.getContentPane().add(dropAmbienteSaida4);
            
            labelEntradas.setBounds(460,120,100,20);
            tela.getContentPane().add(labelEntradas);
            
            dropAmbienteEntrada1.setBounds(460,150,160,20);
            tela.getContentPane().add(dropAmbienteEntrada1);
            
            dropAmbienteEntrada2.setBounds(630,150,160,20);
            tela.getContentPane().add(dropAmbienteEntrada2);
            
            dropAmbienteEntrada3.setBounds(460,185,160,20);
            tela.getContentPane().add(dropAmbienteEntrada3);
            
            dropAmbienteEntrada4.setBounds(630,185,160,20);
            tela.getContentPane().add(dropAmbienteEntrada4);
            
            botaoSalvar.setBounds(500, 490, 100, 30);
            tela.getContentPane().add(botaoSalvar);
            
            botaoCancelar.setBounds(640,490,100,30);
            tela.getContentPane().add(botaoCancelar);
            
        }
        
        public void exibirTela(){
            tela.setVisible(true);
        }
    
    
}
