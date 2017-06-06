package bingo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTextPane;
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

public class BingoPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BingoPanel frame = new BingoPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BingoPanel() {
		setResizable(false);
		setTitle("PRENCHA SUA CARTELA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 336, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(12, 51, 298, 194);
		
		JButton btnBingo = new JButton("Bingo");
		btnBingo.setBounds(12, 12, 298, 34);
		btnBingo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		
		int max=99;
		int min=1;
		String  numerosSorteados[][] = new String[5][5];
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				/*if((i!=3)&&(j!=3)){*/
					Random rand = new Random();
					Integer randomNum = rand.nextInt((max - min) + 1) + min;
					numerosSorteados[i][j] =randomNum.toString(); 
					buttonPanel.add(new JButton(new Integer(randomNum).toString()));
				/*}else{
					numerosSorteados[i][j] = "Bingo";
					buttonPanel.add((new JButton("Bingo")));
				}*/
			}
		}
		
		JTextArea saida = new JTextArea();
		saida.setBounds(12, 256, 298, 63);
		contentPane.setLayout(null);
		contentPane.add(buttonPanel);
		contentPane.add(btnBingo);
		contentPane.add(saida);
	}
}
