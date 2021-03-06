/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import bd.dbos.TabelaJogos;
import bd.dbos.User;
import cliente.ClienteNovo;
import cliente.Constants;
import listener.ListenerAbreFechaTelas;
import listener.ListenerAuthUser;
import listener.ListenerCreateUser;
import protocolo.AcaoPedido;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;
import sun.awt.WindowClosingListener;

import javax.swing.GroupLayout;
import javax.swing.JPasswordField;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Color;

/**
 *
 * @author Ultron
 */
public class HomePanel extends javax.swing.JFrame {
	

    private ListenerAbreFechaTelas abreCadastrar = new ListenerAbreFechaTelas(this,"CadastrarPanel");
    //ListenerAbreFechaTelas createUser = new ListenerCreateUser(this);
    private ListenerAuthUser createUser = new ListenerAuthUser(this); 
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abreCadastrar == null) ? 0 : abreCadastrar.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((jButton1 == null) ? 0 : jButton1.hashCode());
		result = prime * result + ((jButton2 == null) ? 0 : jButton2.hashCode());
		result = prime * result + ((jButton3 == null) ? 0 : jButton3.hashCode());
		result = prime * result + ((jLabel1 == null) ? 0 : jLabel1.hashCode());
		result = prime * result + ((jLabel2 == null) ? 0 : jLabel2.hashCode());
		result = prime * result + ((jLabel5 == null) ? 0 : jLabel5.hashCode());
		result = prime * result + ((resposta == null) ? 0 : resposta.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((tabela == null) ? 0 : tabela.hashCode());
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
		HomePanel other = (HomePanel) obj;
		if (abreCadastrar == null) {
			if (other.abreCadastrar != null)
				return false;
		} else if (!abreCadastrar.equals(other.abreCadastrar))
			return false;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (createUser == null) {
			if (other.createUser != null)
				return false;
		} else if (!createUser.equals(other.createUser))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (jButton1 == null) {
			if (other.jButton1 != null)
				return false;
		} else if (!jButton1.equals(other.jButton1))
			return false;
		if (jButton2 == null) {
			if (other.jButton2 != null)
				return false;
		} else if (!jButton2.equals(other.jButton2))
			return false;
		if (jButton3 == null) {
			if (other.jButton3 != null)
				return false;
		} else if (!jButton3.equals(other.jButton3))
			return false;
		if (jLabel1 == null) {
			if (other.jLabel1 != null)
				return false;
		} else if (!jLabel1.equals(other.jLabel1))
			return false;
		if (jLabel2 == null) {
			if (other.jLabel2 != null)
				return false;
		} else if (!jLabel2.equals(other.jLabel2))
			return false;
		if (jLabel5 == null) {
			if (other.jLabel5 != null)
				return false;
		} else if (!jLabel5.equals(other.jLabel5))
			return false;
		if (resposta == null) {
			if (other.resposta != null)
				return false;
		} else if (!resposta.equals(other.resposta))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (tabela == null) {
			if (other.tabela != null)
				return false;
		} else if (!tabela.equals(other.tabela))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "HomePanel [abreCadastrar=" + abreCadastrar + ", createUser=" + createUser + ", jButton1=" + jButton1
				+ ", jButton2=" + jButton2 + ", jButton3=" + jButton3 + ", jLabel1=" + jLabel1 + ", jLabel2=" + jLabel2
				+ ", tabela=" + tabela + ", resposta=" + resposta + ", jLabel5=" + jLabel5 + ", email=" + email
				+ ", senha=" + senha + ", action=" + action + "]";
	}
	/**
     * Creates new form Home
     */
    public HomePanel() {
        initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @return 
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton1.addActionListener(createUser);
        jButton2 = new javax.swing.JButton();
        jButton2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
					System.exit(NORMAL);
        	}
        });
        jButton3 = new javax.swing.JButton();
        jButton3.addActionListener(abreCadastrar);
        email = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tabela = new javax.swing.JLabel();
        resposta = new javax.swing.JLabel();
        resposta.setForeground(Color.RED);
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HOME");
        setResizable(false);

        jButton1.setText("Entrar");

        jButton2.setText("Sair");

        jButton3.setText("Cadastrar-se");
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Email");

        jLabel2.setText("Senha");
        
        senha = new JPasswordField();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(81)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(resposta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel2)
        						.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        							.addComponent(senha)
        							.addComponent(jLabel1)
        							.addGroup(layout.createSequentialGroup()
        								.addComponent(jButton1)
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addComponent(jButton2)
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addComponent(jButton3))
        							.addComponent(email)
        							.addComponent(tabela, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        					.addContainerGap(88, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(resposta, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel1)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(23)
        			.addComponent(jLabel2)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(senha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel5)
        			.addGap(5)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jButton1)
        				.addComponent(jButton2)
        				.addComponent(jButton3))
        			.addGap(18)
        			.addComponent(tabela, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        try {
			this.exibiTabela();
		} catch (Exception e) {
			this.tabela.setText("n�o foi possivel carregar a tabela de ganhadores");
		}
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePanel().setVisible(true);
            }
        });
    }
    public void fechaTela(){
    	this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel tabela;
    private javax.swing.JLabel resposta;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField email;
    private JPasswordField senha;
    private final Action action = new SwingAction();
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	public void printa(String text){
    	this.resposta.setText(text);
    }

	public void bloqueiCaixas() {
		this.email.setEnabled(false);
		this.senha.setEnabled(false);
	}
	private void exibiTabela() throws Exception{
		Constants cons = new Constants();
		ProtocoloPedido protocolo = new ProtocoloPedido(null, AcaoPedido.PegarTabelaJogos, cons.getIP());
		//ClienteNormal cli = new ClienteNormal(protocolo);
		ProtocoloResposta ret = ClienteNovo.realizapedido(protocolo);
		this.tabela.setText(((TabelaJogos)ret.getObjeto()).returnHtml());
	}
	public User createUser(){
		User user = null;
		try {
			user = new User(
					null,
					this.email.getText(),
					this.senha.getText()
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	public void clear(){
		this.email.setText("");
		this.senha.setText("");
	}
}
