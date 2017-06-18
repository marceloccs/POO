package bingo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.dbos.Cartela;
import bd.dbos.User;
import cliente.ClienteNovo;
import cliente.PegadorMensagensBingo;
import listener.ListenerCompletaCartela;
import listener.ListenerSelecionaDeselecionaBotao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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
	private PegadorMensagensBingo mensageiro  = new PegadorMensagensBingo(ClienteNovo.getEntrada(), this);
	private Thread t = new Thread(mensageiro);
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
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public BingoPanel(User dono) throws Exception {
		setResizable(false);
		setTitle("PRENCHA SUA CARTELA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(12, 51, 282, 194);
		
		this.btnBingo = new JButton("Bingo");
		this.btnBingo.setBounds(12, 12, 282, 34);
		this.btnBingo.addActionListener(completa);
		
		
		int max=99;
		int min=1;
		String  numerosSorteados[][] = new String[5][5];
		Vector <String> numerosJaAdd = new Vector<String>(24);
		int posicaoX=0;
		int posicaoY=0;
		int width = 38;
		int heigth = 56;
		int linhas=0;
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if((i==2)&&(j==2)){
					numerosSorteados[i][j] = "Bingo";
					JToggleButton button = new JToggleButton("Bingo");
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
							button.setSize(heigth,width);
							button.setAlignmentX(posicaoX);
							button.setAlignmentY(posicaoY);
							posicaoX+=width;
							linhas++;
							if(linhas==5){
								posicaoX=0;
								posicaoY+=heigth;
							}
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
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(btnBingo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 256, 284, 112);
		contentPane.add(scrollPane);
		this.saida = new JTextArea();
		scrollPane.setViewportView(saida);
		saida.setText("Resultado do sorteio:");
		this.saida.setEditable(false);
		this.desabilitaButton();
		this.t.start();
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
	public void paraThread(){
		this.mensageiro.parar();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(botoes);
		result = prime * result + ((btnBingo == null) ? 0 : btnBingo.hashCode());
		result = prime * result + ((cartela == null) ? 0 : cartela.hashCode());
		result = prime * result + ((completa == null) ? 0 : completa.hashCode());
		result = prime * result + ((contentPane == null) ? 0 : contentPane.hashCode());
		result = prime * result + ((mensageiro == null) ? 0 : mensageiro.hashCode());
		result = prime * result + ((saida == null) ? 0 : saida.hashCode());
		result = prime * result + ((scrollPane == null) ? 0 : scrollPane.hashCode());
		result = prime * result + ((t == null) ? 0 : t.hashCode());
		result = prime * result + ultimoBotao;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BingoPanel other = (BingoPanel) obj;
		if (!Arrays.equals(botoes, other.botoes))
			return false;
		if (btnBingo == null) {
			if (other.btnBingo != null)
				return false;
		} else if (!btnBingo.equals(other.btnBingo))
			return false;
		if (cartela == null) {
			if (other.cartela != null)
				return false;
		} else if (!cartela.equals(other.cartela))
			return false;
		if (completa == null) {
			if (other.completa != null)
				return false;
		} else if (!completa.equals(other.completa))
			return false;
		if (contentPane == null) {
			if (other.contentPane != null)
				return false;
		} else if (!contentPane.equals(other.contentPane))
			return false;
		if (mensageiro == null) {
			if (other.mensageiro != null)
				return false;
		} else if (!mensageiro.equals(other.mensageiro))
			return false;
		if (saida == null) {
			if (other.saida != null)
				return false;
		} else if (!saida.equals(other.saida))
			return false;
		if (scrollPane == null) {
			if (other.scrollPane != null)
				return false;
		} else if (!scrollPane.equals(other.scrollPane))
			return false;
		if (t == null) {
			if (other.t != null)
				return false;
		} else if (!t.equals(other.t))
			return false;
		if (ultimoBotao != other.ultimoBotao)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BingoPanel [contentPane=" + contentPane + ", cartela=" + cartela + ", botoes=" + Arrays.toString(botoes)
				+ ", ultimoBotao=" + ultimoBotao + ", btnBingo=" + btnBingo + ", saida=" + saida + ", mensageiro="
				+ mensageiro + ", t=" + t + ", completa=" + completa + ", scrollPane=" + scrollPane + "]";
	}
}
