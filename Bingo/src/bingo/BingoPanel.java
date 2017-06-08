package bingo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.dbos.Cartela;
import bd.dbos.User;
import cliente.ClienteBingo;
import cliente.ClienteNormal;
import listener.ListenerCompletaCartela;
import listener.ListenerSelecionaDeselecionaBotao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.DropMode;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import java.awt.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import protocolo.AcaoPedido;
import protocolo.ProtocoloPedido;

import javax.swing.JScrollPane;

public class BingoPanel extends JFrame {

	private JPanel contentPane;
	private Cartela cartela;
	private JToggleButton [] botoes = new JToggleButton[25];
	private int ultimoBotao=-1;
	private JButton btnBingo;
	private JTextArea saida;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BingoPanel frame = new BingoPanel(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	ListenerCompletaCartela completa = new ListenerCompletaCartela(this); 

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public BingoPanel(User dono) throws Exception {
		setResizable(false);
		setTitle("PRENCHA SUA CARTELA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 279, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(12, 51, 253, 194);
		
		this.btnBingo = new JButton("Bingo");
		this.btnBingo.setBounds(12, 12, 253, 34);
		this.btnBingo.addActionListener(completa);
		
		
		int max=99;
		int min=1;
		String  numerosSorteados[][] = new String[5][5];
		Vector <String> numerosJaAdd = new Vector<String>(24);
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if((i==2)&&(j==2)){
					numerosSorteados[i][j] = "Bingo";
					JToggleButton button = new JToggleButton("Bingo");
					button.setSize(64,45);
					button.addActionListener(new ListenerSelecionaDeselecionaBotao(this,button));
					buttonPanel.add(button);
					this.ultimoBotao++;
					this.botoes[this.ultimoBotao] = button; 
				}else{
					while(true){
						Random rand = new Random();
						Integer randomNum = rand.nextInt((max - min) + 1) + min;
						if(!numerosJaAdd.contains(randomNum.toString())){
							numerosJaAdd.add(randomNum.toString());
							numerosSorteados[i][j] =randomNum.toString();
							JToggleButton button = new JToggleButton(new Integer(randomNum).toString());
							button.setSize(64,38);
							button.addActionListener(new ListenerSelecionaDeselecionaBotao(this,button));
							buttonPanel.add(button);
							this.ultimoBotao++;
							this.botoes[this.ultimoBotao] = button; 
							break;
						}
					}
				}
			}
		}
		this.cartela = new Cartela(numerosSorteados,dono);
		contentPane.setLayout(null);
		contentPane.add(buttonPanel);
		contentPane.add(btnBingo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 256, 253, 114);
		contentPane.add(scrollPane);
		this.saida = new JTextArea();
		this.saida.setEditable(false);
		scrollPane.setViewportView(saida);
		this.desabilitaButton();
		ClienteBingo bin = new ClienteBingo(null,this); 
		Thread t = new Thread(bin);
		t.start();
		this.habilitaButton();
	}
	public void selecioneBotao(String valor){
		this.cartela.seleciona(valor);
	}
	public void deSelecioneBotao(String valor) throws Exception{
		this.cartela.deSeleciona(valor);
	}
	public Cartela getCartela(){
		return this.cartela;
	}
	public void desabilitaButton(){
		for(int i=0;i<this.botoes.length;i++){
			this.botoes[i].setEnabled(false);
		}
		this.btnBingo.setEnabled(false);
	}
	public void habilitaButton(){
		for(int i=0;i<this.botoes.length;i++){
			this.botoes[i].setEnabled(true);
		}
		this.btnBingo.setEnabled(true);
	}
	public void printaInfo(String s){
		this.saida.setText(this.saida.getText()+"\n"+s);
	}
}
