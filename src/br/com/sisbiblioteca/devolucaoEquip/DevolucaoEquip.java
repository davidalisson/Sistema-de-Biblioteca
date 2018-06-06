/*
 * 08/2012, 16:39:53
 */
package br.com.sisbiblioteca.devolucaoEquip;

import br.com.sisbiblioteca.Sobre.Sobre;
import br.com.sisbiblioteca.cadastroEquipamento.cadastroequipamento;
import br.com.sisbiblioteca.cadastroEquipamento.equipamentoBean;
import br.com.sisbiblioteca.cadastroInstituicao.cadastroInstituicao;
import br.com.sisbiblioteca.cadastroLivro.cadastroLivro;
import br.com.sisbiblioteca.cadastrolocatario.cadastrolocatario;
import br.com.sisbiblioteca.cadastrolocatario.cadastrolocatarioBean;
import br.com.sisbiblioteca.cadastrousuarios.cadastrousuario;
import br.com.sisbiblioteca.devolucaolivros.devolucaolivro;
import br.com.sisbiblioteca.emprestimoEquipamento.EmprestimoEquipamento;

import br.com.sisbiblioteca.emprestimoEquipamento.emprestimoBean;
import br.com.sisbiblioteca.emprestimoLivro.registroLivro;
import br.com.sisbiblioteca.login.login;
import br.com.sisbiblioteca.pendenciasEquip.pendenciasEquip;
import br.com.sisbiblioteca.principal.principal;
import br.com.sisbiblioteca.registroequipamentos.reservaequipamento;
import br.com.sisbiblioteca.registroequipamentos.reservaequipamentoBean;
import br.com.sisbiblioteca.reservaLivro.reservaLivro;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author David
 */
public class DevolucaoEquip extends javax.swing.JFrame {

    /**
     * Creates new form DevolucaoEquip
     */
    public DevolucaoEquip() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);

        java.util.Date d = new java.util.Date();
        dcDataHoje.setDate(d);
        dcDataHoje.setEnabled(false);
        msg();
    }
    public static String nome;

    public void msg() {
        msgNome.setText("" + nome);

        java.util.Date h = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        lbDate.setText("" + formato.format(h));

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
                    lbHora.setText("" + horaString + ":" + minString + ":" + segundoString);//seta hora atual no label
                    try {
                        sleep(1000);//faz a thread entrar em estado de espera por 1000 milissegundos ou 1 segundo
                    } catch (Exception e) {
                    }
                }
            }
        }.start();//inicia a thread.
    }

    public boolean VerificCampos() {
        if (tbDevolucao.getSelectedRow() != -1) {
            return true;
        }
        return false;
    }

    private void cadastraDataDevol() {
        if (VerificCampos()) {

            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente devolver este equipamento ?", "Verificação!!", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                emprestimoBean db = new emprestimoBean();

                db.setCod_emp_equip(empBean.get(tbDevolucao.getSelectedRow()).getCod_emp_equip());

                db.setStatus("devolvido");

                emprestimoBean eq = new emprestimoBean();

                eq.setCod_equip_equip(empBean.get(tbDevolucao.getSelectedRow()).getCod_equip_equip());
                eq.setStatusE("reservado");

                devolucaoControl ep = new devolucaoControl();
                ep.devolverEquip(db);
                ep.alterarEquipamento(eq);

                listarEmprestimos();

            }

        }
    }
    DefaultTableModel tmLocatarios = new DefaultTableModel(null, new String[]{"Nome"});
    List<cadastrolocatarioBean> fbean;
    DefaultTableModel tmEquipamentos = new DefaultTableModel(null, new String[]{"Equipamento", "Quantidade"});
    List<equipamentoBean> ebean;
    DefaultTableModel tmReserva = new DefaultTableModel(null, new String[]{"Equipamento", "Código", "Locatário", "Data/Empréstimo", "Data/Devolução"});
    List<reservaequipamentoBean> rbean;
    List<cadastrolocatarioBean> obean;
    DefaultTableModel tmEmprestimo = new DefaultTableModel(null, new String[]{"Equipamento", "Código", "Locatário", "Data/Empréstimo", "Data/Devolução"});
    List<emprestimoBean> empBean;
    List<devolucaoBean> dbean;
    ListSelectionModel lsmDevolucao;

    public void listarEmprestimos() {
        devolucaoControl ec = new devolucaoControl();
        //empBean = ec.listarEmprestimos("%" + tfPesquisarEmp.getText().trim() + "%");
        if (dcInicial.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Informe a data Inicial!");
            dcInicial.requestFocus();
        } else if (dcFinal.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Informe a data Final!");
            dcFinal.requestFocus();
        } else {
            while (tmEmprestimo.getRowCount() > 0) {
                tmEmprestimo.removeRow(0);

            }
            SimpleDateFormat formato1 = new SimpleDateFormat("yyyy-MM-dd");
            Date dataInicio = Date.valueOf(formato1.format(dcInicial.getDate()));

            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            Date dataFinal = Date.valueOf(formato2.format(dcFinal.getDate()));



            empBean = ec.listarEmprestimos(Date.valueOf(formato1.format(dataInicio)), Date.valueOf(formato2.format(dataFinal)));
            mostrarEmprestimos(empBean);

            if (rbDataHoje.isSelected()) {
                rbtDataHoje();
            }


        }
    }

    public void mostrarEmprestimos(List<emprestimoBean> emprestimoB) {
        while (tmEquipamentos.getRowCount() > 0) {
            tmEquipamentos.removeRow(0);
        }
        if (emprestimoB.size() == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum Empréstimo encontrado!");
        } else {

            String[] campos = new String[]{null, null, null, null, null};
            for (int i = 0; i < empBean.size(); i++) {
                tmEmprestimo.addRow(campos);

                tmEmprestimo.setValueAt(empBean.get(i).getNome_equip(), i, 0);
                tmEmprestimo.setValueAt(empBean.get(i).getCod_equip_equip(), i, 1);
                tmEmprestimo.setValueAt(empBean.get(i).getNome_loc(), i, 2);
                tmEmprestimo.setValueAt(empBean.get(i).getData_emp_equip(), i, 3);
                tmEmprestimo.setValueAt(empBean.get(i).getData_prev_equip(), i, 4);

            }
        }
    }

    public void tb_emprestimosLinhaSelecionada(JTable tb) {


        if (tb.getSelectedRow() != -1) {
            tfNomeL.setText(empBean.get(tb.getSelectedRow()).getNome_loc());

            dcDataEmp.setDate(empBean.get(tb.getSelectedRow()).getData_emp_equip());

            dcDataPrev.setDate(empBean.get(tb.getSelectedRow()).getData_prev_equip());

            tfHoraEmp.setText(empBean.get(tb.getSelectedRow()).getHora_emp_equip());

            tfHoraPrev.setText(empBean.get(tb.getSelectedRow()).getHora_dev_equip());

            tfNomeF.setText(empBean.get(tb.getSelectedRow()).getNome_func());


        }
    }

    public void rbtDataHoje() {

        java.util.Date dinicial = new java.util.Date();
        dcInicial.setDate(dinicial);

        java.util.Date dfinal = new java.util.Date();
        dcFinal.setDate(dfinal);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDevolucao = new javax.swing.JTable();
        dcDataEmp = new com.toedter.calendar.JDateChooser();
        dcDataPrev = new com.toedter.calendar.JDateChooser();
        tfNomeL = new javax.swing.JTextField();
        tfHoraEmp = new javax.swing.JTextField();
        tfHoraPrev = new javax.swing.JTextField();
        tfNomeF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btSalvar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        dcDataHoje = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        btPesquisar = new javax.swing.JButton();
        dcFinal = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dcInicial = new com.toedter.calendar.JDateChooser();
        rbDataHoje = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        Ramiro = new javax.swing.JPanel();
        lbDate = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        msgNome = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbHora = new javax.swing.JLabel();
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

        jLabel9.setText("Hora do Empréstimo:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Biblioteca - Devolução de Equipamentos");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icone.png")));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Busque por data inicial até a data final ou pela data de hoje"));

        tbDevolucao.setModel(tmEmprestimo);
        tbDevolucao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsmDevolucao = tbDevolucao.getSelectionModel();
        lsmDevolucao.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                if(! e.getValueIsAdjusting()){
                    tb_emprestimosLinhaSelecionada(tbDevolucao);
                }
            }
        });
        jScrollPane1.setViewportView(tbDevolucao);

        dcDataEmp.setEnabled(false);

        dcDataPrev.setEnabled(false);

        tfNomeL.setEnabled(false);
        tfNomeL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeLActionPerformed(evt);
            }
        });

        tfHoraEmp.setEnabled(false);

        tfHoraPrev.setEnabled(false);

        tfNomeF.setEnabled(false);

        jLabel3.setText("Data do Empréstimo:");

        jLabel4.setText("Hora do Empréstimo:");

        jLabel5.setText("Hora Prev.Devolução:");

        jLabel6.setText("Data Prev.Devolução:");

        jLabel7.setText("Responsável pelo Empréstimo:");

        jLabel8.setText("Nome Locatário:");

        btSalvar.setText("Confirmar Devoloção");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btCancelar.setText("Ir para menu");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        jLabel10.setText("Data da Devolução");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfNomeF))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tfNomeL)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(dcDataPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfHoraPrev))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(dcDataEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfHoraEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dcDataHoje, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btSalvar)
                .addGap(18, 18, 18)
                .addComponent(btCancelar)
                .addGap(216, 216, 216))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCancelar, btSalvar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNomeL, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcDataEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfHoraEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dcDataHoje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcDataPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfHoraPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNomeF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCancelar))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dcDataEmp, dcDataHoje, dcDataPrev, tfHoraEmp, tfHoraPrev, tfNomeF, tfNomeL});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btCancelar, btSalvar});

        btPesquisar.setText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        jLabel2.setText("Data final:");

        rbDataHoje.setText("Data de Hoje");
        rbDataHoje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDataHojeActionPerformed(evt);
            }
        });

        jLabel1.setText("Data inicial:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(rbDataHoje)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dcFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(rbDataHoje)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 9, 0, 0);
        getContentPane().add(jPanel5, gridBagConstraints);

        Ramiro.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Ramiro.setLayout(null);
        Ramiro.add(lbDate);
        lbDate.setBounds(10, 4, 100, 20);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 147;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 23, 0);
        getContentPane().add(Ramiro, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel6.setLayout(null);

        jLabel11.setText("Usuário:");
        jPanel6.add(jLabel11);
        jLabel11.setBounds(10, 0, 60, 30);
        jPanel6.add(msgNome);
        msgNome.setBounds(70, 0, 430, 30);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 527;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 23, 0);
        getContentPane().add(jPanel6, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(null);
        jPanel4.add(lbHora);
        lbHora.setBounds(30, 4, 90, 20);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 117;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 9, 23, 0);
        getContentPane().add(jPanel4, gridBagConstraints);

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
        setBounds((screenSize.width-841)/2, (screenSize.height-597)/2, 841, 597);
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        principal a = new principal();
        a.main(null);
        dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        listarEmprestimos();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        cadastraDataDevol();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void tfNomeLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeLActionPerformed

    private void rbDataHojeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDataHojeActionPerformed
rbtDataHoje();
    }//GEN-LAST:event_rbDataHojeActionPerformed

    private void nmuInstituicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nmuInstituicaoActionPerformed
        cadastroInstituicao ci = new cadastroInstituicao();
        ci.setVisible(true);
    }//GEN-LAST:event_nmuInstituicaoActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed

    }//GEN-LAST:event_jMenu4ActionPerformed

    private void mnuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPrincipalActionPerformed
        principal p = new principal();
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_mnuPrincipalActionPerformed

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
        int resposta = JOptionPane.showConfirmDialog(this, "Ao sair desta tela verifique se todos os dados foram salvos.\n Deseja realmente sair?","Verificação de Saída!!!", JOptionPane.YES_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            login l = new login();
            l.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_mnuSairActionPerformed

    private void mnuOpcoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOpcoesActionPerformed

    }//GEN-LAST:event_mnuOpcoesActionPerformed

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

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        pendenciasEquip pe = new pendenciasEquip();
        pe.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void PendenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PendenciasActionPerformed

    }//GEN-LAST:event_PendenciasActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Sobre s = new Sobre();
        s.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            java.util.logging.Logger.getLogger(DevolucaoEquip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DevolucaoEquip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DevolucaoEquip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DevolucaoEquip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DevolucaoEquip().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Pendencias;
    private javax.swing.JPanel Ramiro;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btSalvar;
    private com.toedter.calendar.JDateChooser dcDataEmp;
    private com.toedter.calendar.JDateChooser dcDataHoje;
    private com.toedter.calendar.JDateChooser dcDataPrev;
    private com.toedter.calendar.JDateChooser dcFinal;
    private com.toedter.calendar.JDateChooser dcInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
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
    private javax.swing.JRadioButton rbDataHoje;
    private javax.swing.JTable tbDevolucao;
    private javax.swing.JTextField tfHoraEmp;
    private javax.swing.JTextField tfHoraPrev;
    private javax.swing.JTextField tfNomeF;
    private javax.swing.JTextField tfNomeL;
    // End of variables declaration//GEN-END:variables
}
