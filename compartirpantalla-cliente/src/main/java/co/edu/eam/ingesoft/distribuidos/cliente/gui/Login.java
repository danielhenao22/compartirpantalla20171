/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.ingesoft.distribuidos.cliente.gui;

import java.util.List;

import javax.swing.JOptionPane;

import co.edu.eam.ingesoft.distribuidos.cliente.controlador.Controlador;
import co.edu.eam.ingesoft.distribuidos.compartitrpantalla.modelo.Usuario;

/**
 *
 * @author caferrer
 */
public class Login extends javax.swing.JFrame {

	private Controlador control;

	
	/**
	 * Creates new form Login
	 */
	public Login() {
		initComponents();
		control = new Controlador();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jButton1 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		tfuser = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		tfpass = new javax.swing.JPasswordField();
		btnLogin = new javax.swing.JButton();
		btnReg = new javax.swing.JButton();

		jButton1.setText("jButton1");

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("usuario");

		jLabel2.setText("password");

		btnLogin.setText("Login");
		btnLogin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLoginActionPerformed(evt);
			}
		});

		btnReg.setText("REgistrarse");
		btnReg.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRegActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel2)
						.addComponent(jLabel1))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(btnLogin)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(btnReg))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(tfuser).addComponent(tfpass, javax.swing.GroupLayout.DEFAULT_SIZE, 243,
										Short.MAX_VALUE)))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(29, 29, 29)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel1).addComponent(tfuser, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jLabel2).addComponent(tfpass, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnLogin).addComponent(btnReg))
						.addContainerGap(179, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLoginActionPerformed
		// TODO add your handling code here:
		boolean si = control.login(tfuser.getText(), new String(tfpass.getPassword()));
		if (si) {
			new Ventana(control).setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "datos incorrectos");
		}
	}// GEN-LAST:event_btnLoginActionPerformed

	private void btnRegActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRegActionPerformed

		// TODO add your handling code here:
		boolean si = control.registrar(tfuser.getText(), new String(tfpass.getPassword()));
		if (si) {
			new Ventana(control).setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "no se pudo registar");
		}

	}// GEN-LAST:event_btnRegActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting
		// code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
		 * html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Login().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnLogin;
	private javax.swing.JButton btnReg;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPasswordField tfpass;
	private javax.swing.JTextField tfuser;
	// End of variables declaration//GEN-END:variables
}
