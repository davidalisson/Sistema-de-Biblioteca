/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * principal.java
 *
 * Created on 06/08/2012, 14:12:48
 */
package br.com.sisbiblioteca.principal;

import br.com.sisbiblioteca.Sobre.Sobre;
import br.com.sisbiblioteca.cadastroEquipamento.cadastroequipamento;
import br.com.sisbiblioteca.cadastroInstituicao.cadastroInstituicao;
import br.com.sisbiblioteca.cadastroLivro.cadastroLivro;
import br.com.sisbiblioteca.cadastrousuarios.usuarioBean;
import br.com.sisbiblioteca.cadastrolocatario.cadastrolocatario;
import br.com.sisbiblioteca.cadastrousuarios.cadastrousuario;
import br.com.sisbiblioteca.devolucaoEquip.DevolucaoEquip;
import br.com.sisbiblioteca.devolucaolivros.devolucaolivro;
import br.com.sisbiblioteca.emprestimoEquipamento.EmprestimoEquipamento;
import br.com.sisbiblioteca.emprestimoLivro.registroLivro;
import br.com.sisbiblioteca.login.login;
import br.com.sisbiblioteca.pendenciasEquip.pendenciasEquip;
import br.com.sisbiblioteca.registroequipamentos.reservaequipamento;
import br.com.sisbiblioteca.reservaLivro.reservaLivro;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Murilo Alves
 */
public class principal extends javax.swing.JFrame {

    /**
     * Creates new form principal
     */
    public principal() {
        initComponents();
        msg();
        
        
        

    }
    public static String nome;

    public void msg() {
        msgNome.setText("" + nome);
        
        java.util.Date h = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        lbDate.setText(""+ formato.format(h));
        
        iniciaRelogio();
    }

    public void iniciaRelogio() {
        new Thread() {//instancia nova thread já implementando o método run()

           
            public void run() {//sobrescreve o método run()
                while (0 == 0) {//while para fazer o loop infinito
                    GregorianCalendar gc = new GregorianCalendar();//novo gregorian calendar, onde temos a data atual
                    int hora = gc.get(Calendar.HOUR_OF_DAY);//pega as horas
                    int minuto = gc.get(Calendar.MINUTE);//pega os minutos
                    int segundo = gc.get(Calendar.SECOND);//pega os segundos
                    String horaString;//nova string horas
                    String minString;//nova string minutos
                    String segundoString;//nova string segundos
                    if (hora < 10) {//se hora for menor que 10 precisa colocar um 0 à esquerda
                        horaString = "0" + hora;
                    } else {
                        horaString = "" + hora;
                    }
                    if (minuto < 10) {//se minuto for menor que 10 precisa colocar um 0 à esquerda
                        minString = "0" + minuto;
                    } else {
                        minString = "" + minuto;
                    }
                    if (segundo < 10) {//se segundo for menor que 10 precisa colocar um 0 à esquerda
                        segundoString = "0" + segundo;
                    } else {
                        segundoString = "" + segundo;
                    }
                    lbHora.setText(""+horaString + ":" + minString + ":" + segundoString);//seta hora atual no label
                    try {
                        sleep(1000);//faz a thread entrar em estado de espera por 1000 milissegundos ou 1 segundo
                    } catch (Exception e) {
                    }
                }
            }
        }.start();//inicia a thread.
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btConsultas = new javax.swing.JButton();
        btRegistros = new javax.swing.JButton();
        btCadastros = new javax.swing.JButton();
        btPendencias = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbHora = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        msgNome = new javax.swing.JLabel();
        Ramiro = new javax.swing.JPanel();
        lbDate = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuOpcoes = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        nmuInstituicao = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        mnuPrincipal = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuSair = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mnuLocatarios = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mnuLivros = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mnuEquipamentos = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnuReservas = new javax.swing.JMenu();
        mnuLivrosR = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        mnuEquipamentosE = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenu5 = new javax.swing.JMenu();
        mnuLivroE = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        mnuEquipamentoE = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenu6 = new javax.swing.JMenu();
        mnuLivroD = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        mnuEquipamentoD = new javax.swing.JMenuItem();
        Pendencias = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        mnuSobre = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Biblioteca - Principal");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icone.png")));
        setResizable(false);
        getContentPane().setLayout(null);

        btConsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/43.png"))); // NOI18N
        btConsultas.setText("Devoluções");
        btConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsultasActionPerformed(evt);
            }
        });
        getContentPane().add(btConsultas);
        btConsultas.setBounds(290, 140, 180, 73);

        btRegistros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/registro.png"))); // NOI18N
        btRegistros.setText("Reservas");
        btRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegistrosActionPerformed(evt);
            }
        });
        getContentPane().add(btRegistros);
        btRegistros.setBounds(80, 140, 180, 73);

        btCadastros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icone_cadastrar2.png"))); // NOI18N
        btCadastros.setText("Cadastros");
        btCadastros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrosActionPerformed(evt);
            }
        });
        getContentPane().add(btCadastros);
        btCadastros.setBounds(80, 50, 180, 73);

        btPendencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/emprestimo.png"))); // NOI18N
        btPendencias.setText("Emprestimos");
        btPendencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPendenciasActionPerformed(evt);
            }
        });
        getContentPane().add(btPendencias);
        btPendencias.setBounds(290, 50, 180, 73);

        jPanel1.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(null);
        jPanel2.add(lbHora);
        lbHora.setBounds(30, 4, 90, 20);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 120, 30);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setLayout(null);

        jLabel1.setText("Usuário:");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(10, 0, 60, 30);
        jPanel3.add(msgNome);
        msgNome.setBounds(60, 0, 260, 30);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(120, 0, 330, 30);

        Ramiro.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Ramiro.setLayout(null);
        Ramiro.add(lbDate);
        lbDate.setBounds(10, 4, 100, 20);

        jPanel1.add(Ramiro);
        Ramiro.setBounds(450, 0, 120, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 310, 570, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/g.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 610, 360);

        mnuOpcoes.setText("Opções");
        mnuOpcoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuOpcoesActionPerformed(evt);
            }
        });

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/conf.png"))); // NOI18N
        jMenu4.setText("Configurações");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        nmuInstituicao.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nmuInstituicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/School.png"))); // NOI18N
        nmuInstituicao.setText("Instituição/Escola");
        nmuInstituicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nmuInstituicaoActionPerformed(evt);
            }
        });
        jMenu4.add(nmuInstituicao);

        mnuOpcoes.add(jMenu4);
        mnuOpcoes.add(jSeparator11);

        mnuPrincipal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/home_blue.png"))); // NOI18N
        mnuPrincipal.setText("Menu Principal");
        mnuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPrincipalActionPerformed(evt);
            }
        });
        mnuOpcoes.add(mnuPrincipal);
        mnuOpcoes.add(jSeparator1);

        mnuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/k-gpg-icon.png"))); // NOI18N
        mnuSair.setText("Sair");
        mnuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSairActionPerformed(evt);
            }
        });
        mnuOpcoes.add(mnuSair);

        jMenuBar1.add(mnuOpcoes);

        jMenu1.setText("Cadastros");

        mnuLocatarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mnuLocatarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Users-icon.png"))); // NOI18N
        mnuLocatarios.setText("Locatários");
        mnuLocatarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLocatariosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuLocatarios);
        jMenu1.add(jSeparator5);

        mnuLivros.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        mnuLivros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bookcase.png"))); // NOI18N
        mnuLivros.setText("Livros");
        mnuLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLivrosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuLivros);
        jMenu1.add(jSeparator6);

        mnuEquipamentos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        mnuEquipamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vaio.png"))); // NOI18N
        mnuEquipamentos.setText("Equipamentos");
        mnuEquipamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEquipamentosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuEquipamentos);
        jMenu1.add(jSeparator7);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/usuario.png"))); // NOI18N
        jMenuItem1.setText("Usuários");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Registrar");

        mnuReservas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/reservar.png"))); // NOI18N
        mnuReservas.setText("Reservas");

        mnuLivrosR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        mnuLivrosR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bookcase.png"))); // NOI18N
        mnuLivrosR.setText("Livros");
        mnuLivrosR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLivrosRActionPerformed(evt);
            }
        });
        mnuReservas.add(mnuLivrosR);
        mnuReservas.add(jSeparator8);

        mnuEquipamentosE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        mnuEquipamentosE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vaio.png"))); // NOI18N
        mnuEquipamentosE.setText("Equipamentos");
        mnuEquipamentosE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEquipamentosEActionPerformed(evt);
            }
        });
        mnuReservas.add(mnuEquipamentosE);

        jMenu3.add(mnuReservas);
        jMenu3.add(jSeparator3);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/save_go.png"))); // NOI18N
        jMenu5.setText("Emprestimos");

        mnuLivroE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuLivroE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bookcase.png"))); // NOI18N
        mnuLivroE.setText("Livros");
        mnuLivroE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLivroEActionPerformed(evt);
            }
        });
        jMenu5.add(mnuLivroE);
        jMenu5.add(jSeparator9);

        mnuEquipamentoE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuEquipamentoE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vaio.png"))); // NOI18N
        mnuEquipamentoE.setText("Equipamentos");
        mnuEquipamentoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEquipamentoEActionPerformed(evt);
            }
        });
        jMenu5.add(mnuEquipamentoE);

        jMenu3.add(jMenu5);
        jMenu3.add(jSeparator4);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/return.png"))); // NOI18N
        jMenu6.setText("Devolução");

        mnuLivroD.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        mnuLivroD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bookcase.png"))); // NOI18N
        mnuLivroD.setText("Livros");
        mnuLivroD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLivroDActionPerformed(evt);
            }
        });
        jMenu6.add(mnuLivroD);
        jMenu6.add(jSeparator10);

        mnuEquipamentoD.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuEquipamentoD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vaio.png"))); // NOI18N
        mnuEquipamentoD.setText("Equipamentos");
        mnuEquipamentoD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEquipamentoDActionPerformed(evt);
            }
        });
        jMenu6.add(mnuEquipamentoD);

        jMenu3.add(jMenu6);

        jMenuBar1.add(jMenu3);

        Pendencias.setText("Pendências");
        Pendencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PendenciasActionPerformed(evt);
            }
        });

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vaio.png"))); // NOI18N
        jMenuItem2.setText("Equipamentos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Pendencias.add(jMenuItem2);
        Pendencias.add(jSeparator2);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bookcase.png"))); // NOI18N
        jMenuItem4.setText("Livros");
        Pendencias.add(jMenuItem4);

        jMenuBar1.add(Pendencias);

        mnuSobre.setText("Sobre");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sobre.png"))); // NOI18N
        jMenuItem3.setText("Sobre o Sistema");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnuSobre.add(jMenuItem3);

        jMenuBar1.add(mnuSobre);

        setJMenuBar(jMenuBar1);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-574)/2, (screenSize.height-388)/2, 574, 388);
    }// </editor-fold>//GEN-END:initComponents

private void btRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegistrosActionPerformed
    reservaequipamento re = new reservaequipamento();
    re.setVisible(true);
    dispose();
}//GEN-LAST:event_btRegistrosActionPerformed

private void btCadastrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrosActionPerformed
    cadastrolocatario cu = new cadastrolocatario();
    cu.setVisible(true);
    dispose();
}//GEN-LAST:event_btCadastrosActionPerformed

private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
    int resposta = JOptionPane.showConfirmDialog(this, "Ao sair desta tela verifique se todos os dados foram salvos.\n Deseja realmente sair?","Verificação de Saída!!!", JOptionPane.YES_OPTION);
    if (resposta == JOptionPane.YES_OPTION) {
        login l = new login();
        l.setVisible(true);
        dispose();
    }
}//GEN-LAST:event_mnuSairActionPerformed

private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
}//GEN-LAST:event_jMenu4ActionPerformed

private void nmuInstituicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nmuInstituicaoActionPerformed
    cadastroInstituicao ci = new cadastroInstituicao();
    ci.setVisible(true);

}//GEN-LAST:event_nmuInstituicaoActionPerformed

private void btPendenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPendenciasActionPerformed
    EmprestimoEquipamento e = new EmprestimoEquipamento();
    e.setVisible(true);
    dispose();

}//GEN-LAST:event_btPendenciasActionPerformed

private void mnuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPrincipalActionPerformed
    principal p = new principal();
    p.setVisible(true);
    dispose();
}//GEN-LAST:event_mnuPrincipalActionPerformed

private void mnuLocatariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLocatariosActionPerformed
    cadastrolocatario cl = new cadastrolocatario();
    cl.setVisible(true);
    dispose();

}//GEN-LAST:event_mnuLocatariosActionPerformed

private void mnuLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLivrosActionPerformed
    cadastroLivro clv = new cadastroLivro();
    clv.setVisible(true);
    dispose();
}//GEN-LAST:event_mnuLivrosActionPerformed

private void mnuEquipamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEquipamentosActionPerformed
    cadastroequipamento ce = new cadastroequipamento();
    ce.setVisible(true);
    dispose();
}//GEN-LAST:event_mnuEquipamentosActionPerformed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    cadastrousuario cus = new cadastrousuario();
    cus.setVisible(true);
    dispose();

}//GEN-LAST:event_jMenuItem1ActionPerformed

private void mnuLivrosRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLivrosRActionPerformed
    reservaLivro rl = new reservaLivro();
    rl.setVisible(true);
    dispose();
}//GEN-LAST:event_mnuLivrosRActionPerformed

private void mnuEquipamentosEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEquipamentosEActionPerformed
    reservaequipamento re = new reservaequipamento();
    re.setVisible(true);
    dispose();
}//GEN-LAST:event_mnuEquipamentosEActionPerformed

private void mnuLivroDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLivroDActionPerformed
    devolucaolivro rl = new devolucaolivro();
    rl.setVisible(true);
    dispose();
}//GEN-LAST:event_mnuLivroDActionPerformed

private void mnuEquipamentoDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEquipamentoDActionPerformed
    DevolucaoEquip d = new DevolucaoEquip();
    d.setVisible(true);
    dispose();
}//GEN-LAST:event_mnuEquipamentoDActionPerformed

private void mnuLivroEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLivroEActionPerformed
    registroLivro rl = new registroLivro();
    rl.setVisible(true);
    dispose();
}//GEN-LAST:event_mnuLivroEActionPerformed

private void mnuEquipamentoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEquipamentoEActionPerformed
    EmprestimoEquipamento e = new EmprestimoEquipamento();
    e.setVisible(true);
    dispose();
}//GEN-LAST:event_mnuEquipamentoEActionPerformed

    private void mnuOpcoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOpcoesActionPerformed
    }//GEN-LAST:event_mnuOpcoesActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Sobre s = new Sobre();
        s.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultasActionPerformed
        DevolucaoEquip d = new DevolucaoEquip();
        d.setVisible(true);
        dispose();
    }//GEN-LAST:event_btConsultasActionPerformed

    private void PendenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PendenciasActionPerformed
    
    }//GEN-LAST:event_PendenciasActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    pendenciasEquip pe = new pendenciasEquip();
    pe.setVisible(true);
    dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Pendencias;
    private javax.swing.JPanel Ramiro;
    private javax.swing.JButton btCadastros;
    private javax.swing.JButton btConsultas;
    private javax.swing.JButton btPendencias;
    private javax.swing.JButton btRegistros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbHora;
    private javax.swing.JMenuItem mnuEquipamentoD;
    private javax.swing.JMenuItem mnuEquipamentoE;
    private javax.swing.JMenuItem mnuEquipamentos;
    private javax.swing.JMenuItem mnuEquipamentosE;
    private javax.swing.JMenuItem mnuLivroD;
    private javax.swing.JMenuItem mnuLivroE;
    private javax.swing.JMenuItem mnuLivros;
    private javax.swing.JMenuItem mnuLivrosR;
    private javax.swing.JMenuItem mnuLocatarios;
    private javax.swing.JMenu mnuOpcoes;
    private javax.swing.JMenuItem mnuPrincipal;
    private javax.swing.JMenu mnuReservas;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JMenu mnuSobre;
    private javax.swing.JLabel msgNome;
    private javax.swing.JMenuItem nmuInstituicao;
    // End of variables declaration//GEN-END:variables
}
