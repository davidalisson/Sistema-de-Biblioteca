/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * login.java
 *
 * Created on 06/08/2012, 14:17:56
 */
package br.com.sisbiblioteca.login;

import br.com.sisbiblioteca.cadastroEquipamento.cadastroequipamento;
import br.com.sisbiblioteca.cadastroLivro.cadastroLivro;
import br.com.sisbiblioteca.cadastrolocatario.cadastrolocatario;
import br.com.sisbiblioteca.cadastrousuarios.cadastrousuario;
import br.com.sisbiblioteca.devolucaoEquip.DevolucaoEquip;
import br.com.sisbiblioteca.devolucaolivros.devolucaolivro;
import br.com.sisbiblioteca.emprestimoEquipamento.EmprestimoEquipamento;
import br.com.sisbiblioteca.emprestimoLivro.registroLivro;
import br.com.sisbiblioteca.principal.principal;
import br.com.sisbiblioteca.registroequipamentos.reservaequipamento;
import br.com.sisbiblioteca.reservaLivro.reservaLivro;
import java.awt.Toolkit;

/**
 *
 * @author Murilo Alves
 */
public class login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    public login() {
        initComponents();
        msg.setVisible(false);
    }
    cadastroequipamento ce;
    principal p;
    cadastrolocatario cl;
    cadastroLivro l;
    cadastrousuario cau;
    DevolucaoEquip d;
    EmprestimoEquipamento ee;
    registroLivro rl;
    reservaLivro rel;
    reservaequipamento ree;
    devolucaolivro dl;
    
    public static String nome;
    

    private void verificasenha() {
        String login = tfLogin.getText();
        String senha = tfSenha.getText();

        loginControl lc = new loginControl();

        boolean resposta = lc.vsenha(login, senha);

        if (resposta == true) {
            p.nome = nome;
            cl.nome = nome;
            ce.nome = nome;
            l.nome = nome;
            cau.nome = nome;
            d.nome = nome;
            ee.nome = nome;
            rl.nome = nome;
            rel.nome = nome;
            ree.nome = nome;
            dl.nome = nome;

            principal p = new principal();
            p.setVisible(true);
            dispose();

        } else {
            msg.setVisible(true);
            tfSenha.setText("");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbLogin = new javax.swing.JLabel();
        lbSenha = new javax.swing.JLabel();
        tfLogin = new javax.swing.JTextField();
        tfSenha = new javax.swing.JPasswordField();
        btEntrar = new javax.swing.JButton();
        msg = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Biblioteca - Login");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/login.png")));
        setResizable(false);
        getContentPane().setLayout(null);

        lbLogin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbLogin.setForeground(new java.awt.Color(0, 0, 51));
        lbLogin.setText("Login:");
        getContentPane().add(lbLogin);
        lbLogin.setBounds(330, 250, 60, 17);

        lbSenha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbSenha.setForeground(new java.awt.Color(0, 0, 51));
        lbSenha.setText("Senha:");
        getContentPane().add(lbSenha);
        lbSenha.setBounds(330, 290, 60, 17);

        tfLogin.setText("Administrador");
        tfLogin.setToolTipText("Login do funcionário");
        tfLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfLoginActionPerformed(evt);
            }
        });
        getContentPane().add(tfLogin);
        tfLogin.setBounds(410, 240, 210, 30);

        tfSenha.setText("1234");
        tfSenha.setToolTipText("Senha do usuário");
        tfSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(tfSenha);
        tfSenha.setBounds(410, 280, 210, 30);

        btEntrar.setText("Entrar");
        btEntrar.setToolTipText("Entrar");
        btEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEntrarActionPerformed(evt);
            }
        });
        getContentPane().add(btEntrar);
        btEntrar.setBounds(440, 340, 90, 31);

        msg.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        msg.setForeground(new java.awt.Color(255, 0, 0));
        msg.setText("Login ou senha incorreto, por favor tente novamente !!!");
        getContentPane().add(msg);
        msg.setBounds(290, 310, 390, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Logo Biblioteca.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 420, 460);

        jLabel2.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setText("Sistema Bibliotecário");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(300, 170, 380, 40);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-706)/2, (screenSize.height-506)/2, 706, 506);
    }// </editor-fold>//GEN-END:initComponents

private void tfSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSenhaActionPerformed
    verificasenha();
}//GEN-LAST:event_tfSenhaActionPerformed

private void btEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEntrarActionPerformed
    verificasenha();
}//GEN-LAST:event_btEntrarActionPerformed

private void tfLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfLoginActionPerformed
    verificasenha();
}//GEN-LAST:event_tfLoginActionPerformed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel msg;
    private javax.swing.JTextField tfLogin;
    private javax.swing.JPasswordField tfSenha;
    // End of variables declaration//GEN-END:variables
}
