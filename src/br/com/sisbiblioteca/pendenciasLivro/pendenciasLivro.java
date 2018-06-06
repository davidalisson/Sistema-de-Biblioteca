package br.com.sisbiblioteca.pendenciasLivro;

import br.com.sisbiblioteca.emprestimoLivro.registroLivroBean;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class pendenciasLivro extends javax.swing.JFrame {
    
    public pendenciasLivro() {
        initComponents();
    }
    
    DefaultTableModel tmLivro = new DefaultTableModel(null, new String[]{"Nome", "Livro", "Codigo do livro", "Quantidade", "Data de empréstimo", "Data prev. devolução"});
    
    ListSelectionModel lsmDevolucao;
    //ListSelectionModel lsmLocatario;
    List<registroLivroBean> rbean;


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pcadastro1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPendentes = new javax.swing.JTable();
        cbOpcao = new javax.swing.JComboBox();
        btPesquisa = new javax.swing.JButton();
        tfPesquisar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfQuantidade = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfEmprestimo = new javax.swing.JTextField();
        btDevolver = new javax.swing.JButton();
        btVoltar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        tfLivro = new javax.swing.JTextField();
        tfNome = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Listar pendentes"));

        pcadastro1.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione um Item e Pesquise Emprestimos por Datas.:"));

        tbPendentes.setModel(tmLivro);
        jScrollPane2.setViewportView(tbPendentes);

        cbOpcao.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"...", "Aluno", "Funcionário", "Outros"}));
        cbOpcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOpcaoActionPerformed(evt);
            }
        });

        btPesquisa.setText("Pesquisar");
        btPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisaActionPerformed(evt);
            }
        });

        tfPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pcadastro1Layout = new javax.swing.GroupLayout(pcadastro1);
        pcadastro1.setLayout(pcadastro1Layout);
        pcadastro1Layout.setHorizontalGroup(
            pcadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcadastro1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pcadastro1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pcadastro1Layout.setVerticalGroup(
            pcadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcadastro1Layout.createSequentialGroup()
                .addGroup(pcadastro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tfPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(cbOpcao)
                    .addComponent(btPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setText("Data de empréstimo.:");

        jLabel3.setText("Quantidade.:");

        jLabel2.setText("Livro.:");

        jLabel1.setText("Nome do Locatário.:");

        jLabel6.setText("Código do livro.:");

        jLabel5.setText("Data. Prev. Dev.:");

        btDevolver.setText("Devolver");
        btDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDevolverActionPerformed(evt);
            }
        });

        btVoltar.setText("Imprimir");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        jLabel7.setText("Data. Dev.:");

        jButton1.setText("Menu principal ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btVoltar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addGap(115, 115, 115))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(jLabel4)
                                .addGap(3, 3, 3)
                                .addComponent(tfEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pcadastro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pcadastro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tfLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel7)))
                .addGap(15, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btDevolver, btVoltar, jButton1});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tfCodigo, tfEmprestimo, tfLivro, tfNome, tfQuantidade});

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void listar_locatario_aluno() {
        pendenciasLivroControl ac = new pendenciasLivroControl();

        rbean = ac.listarPendentesAluno("%" + tfPesquisar.getText().trim() + "%");
        mostrar_locatario_aluno(rbean);

    }

    public void mostrar_locatario_aluno(List<registroLivroBean> usuarioB) {
        while (tmLivro.getRowCount() > 0) {
            tmLivro.removeRow(0);
        }
        if (usuarioB.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum Locatário encontrado!!!");
        } else {
            String[] campos = new String[]{null, null, null, null, null, null};

            for (int i = 0; i < usuarioB.size(); i++) {
                tmLivro.addRow(campos);
                tmLivro.setValueAt(usuarioB.get(i).getNome_locatario(), i, 0);
                tmLivro.setValueAt(usuarioB.get(i).getNome_livro(), i, 1);
                tmLivro.setValueAt(usuarioB.get(i).getCodigoLivro(), i, 2);
                tmLivro.setValueAt(usuarioB.get(i).getQuantidade(), i, 3);
                tmLivro.setValueAt(usuarioB.get(i).getData_emprestimo(), i, 4);
                tmLivro.setValueAt(usuarioB.get(i).getData_prevista_devolucao(), i, 5);

            }
        }
    }    
       
    public void listar_locatario_funcionario() {
        pendenciasLivroControl ac = new pendenciasLivroControl();

        rbean = ac.listarPendentesAluno("%" + tfPesquisar.getText().trim() + "%");
        mostrar_locatario_funcionario(rbean);

    }

    public void mostrar_locatario_funcionario(List<registroLivroBean> usuarioB) {
        while (tmLivro.getRowCount() > 0) {
            tmLivro.removeRow(0);
        }
        if (usuarioB.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum Locatário encontrado!!!");
        } else {
            String[] campos = new String[]{null, null, null, null, null, null};

            for (int i = 0; i < usuarioB.size(); i++) {
                tmLivro.addRow(campos);
                tmLivro.setValueAt(usuarioB.get(i).getNome_locatario(), i, 0);
                tmLivro.setValueAt(usuarioB.get(i).getNome_livro(), i, 1);
                tmLivro.setValueAt(usuarioB.get(i).getCodigoLivro(), i, 2);
                tmLivro.setValueAt(usuarioB.get(i).getQuantidade(), i, 3);
                tmLivro.setValueAt(usuarioB.get(i).getData_emprestimo(), i, 4);
                tmLivro.setValueAt(usuarioB.get(i).getData_prevista_devolucao(), i, 5);

            }
        }
    }
        
        public void listar_locatario_outro() {
        pendenciasLivroControl ac = new pendenciasLivroControl();

        rbean = ac.listarPendentesAluno("%" + tfPesquisar.getText().trim() + "%");
        mostrar_locatario_outro(rbean);

    }

    public void mostrar_locatario_outro(List<registroLivroBean> usuarioB) {
        while (tmLivro.getRowCount() > 0) {
            tmLivro.removeRow(0);
        }
        if (usuarioB.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum Locatário encontrado!!!");
        } else {
            String[] campos = new String[]{null, null, null, null, null, null};

            for (int i = 0; i < usuarioB.size(); i++) {
                tmLivro.addRow(campos);
                tmLivro.setValueAt(usuarioB.get(i).getNome_locatario(), i, 0);
                tmLivro.setValueAt(usuarioB.get(i).getNome_livro(), i, 1);
                tmLivro.setValueAt(usuarioB.get(i).getCodigoLivro(), i, 2);
                tmLivro.setValueAt(usuarioB.get(i).getQuantidade(), i, 3);
                tmLivro.setValueAt(usuarioB.get(i).getData_emprestimo(), i, 4);
                tmLivro.setValueAt(usuarioB.get(i).getData_prevista_devolucao(), i, 5);

            }
        }
    }

        private void listarPendentes() {

       if (cbOpcao.getSelectedItem().equals("...")) {
           JOptionPane.showMessageDialog(null, "Selecione um tipo de locatário primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (cbOpcao.getSelectedItem().equals("Aluno")) {
            listar_locatario_aluno();
        } else if (cbOpcao.getSelectedItem().equals("Funcionário")) {
            listar_locatario_funcionario();
       } else if (cbOpcao.getSelectedItem().equals("Outros")) {
            listar_locatario_outro();
       }
    }
        
        
    

    private void cbOpcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOpcaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbOpcaoActionPerformed

    private void btPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisaActionPerformed
listarPendentes();       
    }//GEN-LAST:event_btPesquisaActionPerformed

    private void btDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDevolverActionPerformed
       
    }//GEN-LAST:event_btDevolverActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
       
    }//GEN-LAST:event_btVoltarActionPerformed

    private void tfPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPesquisarActionPerformed
listarPendentes();
    }//GEN-LAST:event_tfPesquisarActionPerformed

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
            java.util.logging.Logger.getLogger(pendenciasLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pendenciasLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pendenciasLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pendenciasLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pendenciasLivro().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDevolver;
    private javax.swing.JButton btPesquisa;
    private javax.swing.JButton btVoltar;
    private javax.swing.JComboBox cbOpcao;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pcadastro1;
    private javax.swing.JTable tbPendentes;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfEmprestimo;
    private javax.swing.JTextField tfLivro;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfPesquisar;
    private javax.swing.JTextField tfQuantidade;
    // End of variables declaration//GEN-END:variables
}
