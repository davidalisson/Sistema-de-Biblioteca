package br.com.sisbiblioteca.devolucaolivros;

import br.com.sisbiblioteca.Sobre.Sobre;
import br.com.sisbiblioteca.cadastroEquipamento.cadastroequipamento;
import br.com.sisbiblioteca.cadastroInstituicao.cadastroInstituicao;
import br.com.sisbiblioteca.cadastroLivro.cadastroLivro;
import br.com.sisbiblioteca.cadastrolocatario.cadastrolocatario;
import br.com.sisbiblioteca.cadastrousuarios.cadastrousuario;
import br.com.sisbiblioteca.devolucaoEquip.DevolucaoEquip;
import br.com.sisbiblioteca.emprestimoEquipamento.EmprestimoEquipamento;
import br.com.sisbiblioteca.emprestimoLivro.registroLivro;
import br.com.sisbiblioteca.emprestimoLivro.registroLivroBean;
import br.com.sisbiblioteca.login.login;
import br.com.sisbiblioteca.pendenciasEquip.pendenciasEquip;
import br.com.sisbiblioteca.principal.principal;
import br.com.sisbiblioteca.registroequipamentos.reservaequipamento;
import br.com.sisbiblioteca.reservaLivro.reservaLivro;
import java.awt.Toolkit;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Regina Kecia
 */
public class devolucaolivro extends javax.swing.JFrame {

    private List<registroLivroBean> registroLivroBean;

    /**
     * Creates new form devolucaolivro
     */
    public devolucaolivro() {
        initComponents();
        tfNome.setEditable(false);
        tfLivro.setEditable(false);
        tfCodigo.setEditable(false);
        tfQuantidade.setEditable(false);
        tfEmprestimo.setEditable(false);
        tfDevolucao.setEditable(false);


        java.util.Date d = new java.util.Date();
        tfDataDevolucao.setDate(d);
        tfDataDevolucao.setEnabled(false);

        msg();

        setExtendedState(MAXIMIZED_BOTH);
    }
    public static String nome;

    public void msg() {
        msgNome.setText("" + nome);

        java.util.Date h = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        lbDate.setText("" + formato.format(h));

        iniciaRelogioN();

    }

    public void iniciaRelogioN() {
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

    private void cadastraDataDevol() {
        if (verificarCampos()) {

            int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente devolver este Livro ?", "Verificação!!", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                registroLivroBean db = new registroLivroBean();

                db.setIdregistrolivro(rbean.get(tbDevolucao.getSelectedRow()).getIdregistrolivro());
                db.setStatus("devolvido");

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date datadevolucao = Date.valueOf(formato.format(tfDataDevolucao.getDate()));
                db.setData_dev_emp(datadevolucao);

                registroLivroBean eq = new registroLivroBean();

                eq.setIdlivro(rbean.get(tbDevolucao.getSelectedRow()).getIdlivro());
                eq.setStatusL("reservado");

                registroLivroBean rb = new registroLivroBean();


                devolucaolivrosControl ep = new devolucaolivrosControl();
                ep.devolverLivro(db);
                ep.alterarLivro(eq);

                listarLocatarios();


            }

        }
    }
    DefaultTableModel tmLivro = new DefaultTableModel(null, new String[]{"Nome", "Livro", "Codigo do livro", "Quantidade", "Data de empréstimo", "Data prev. devolução"});
    List<devolucaolivrosBean> devolucaolivrosBean;
    ListSelectionModel lsmDevolucao;
    //ListSelectionModel lsmLocatario;
    List<registroLivroBean> rbean;

    public boolean verificaCampos() {
        if (tbDevolucao.getSelectedRow() != -1) {
            return true;
        }
        return false;
    }

    public void listarDevolucaoAluno() {
        devolucaolivrosControl dlc = new devolucaolivrosControl();
        if (dcInicial.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Informe a data Inicial!");
            dcInicial.requestFocus();
        } else if (dcFinal.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Informe a data Final!");
            dcFinal.requestFocus();
        } else {
            while (tmLivro.getRowCount() > 0) {
                tmLivro.removeRow(0);


            }

            SimpleDateFormat formato1 = new SimpleDateFormat("yyyy-MM-dd");
            Date dataInicio = Date.valueOf(formato1.format(dcInicial.getDate()));

            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            Date dataFinal = Date.valueOf(formato2.format(dcFinal.getDate()));



            rbean = dlc.listarDevolucaoAluno(Date.valueOf(formato1.format(dataInicio)), Date.valueOf(formato2.format(dataFinal)));

            if (rbean.size() == 0) {
                JOptionPane.showMessageDialog(this, "Nenhum empréstimo de livro registrado!");
            } else {
                String[] campos = new String[]{null, null, null, null, null, null};
                for (int i = 0; i < rbean.size(); i++) {
                    tmLivro.addRow(campos);

                    tmLivro.setValueAt(rbean.get(i).getNome_locatario(), i, 0);
                    tmLivro.setValueAt(rbean.get(i).getNome_livro(), i, 1);
                    tmLivro.setValueAt(rbean.get(i).getCodigoLivro(), i, 2);
                    tmLivro.setValueAt(rbean.get(i).getQuantidade(), i, 3);
                    tmLivro.setValueAt(rbean.get(i).getData_emprestimo(), i, 4);
                    tmLivro.setValueAt(rbean.get(i).getData_prevista_devolucao(), i, 5);

                }
            }
        }
    }

    public void listarDevolucaoFuncionario() {
        devolucaolivrosControl dlc = new devolucaolivrosControl();
        //empBean = ec.listarEmprestimos("%" + tfPesquisarEmp.getText().trim() + "%");
        if (dcInicial.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Informe a data Inicial!");
            dcInicial.requestFocus();
        } else if (dcFinal.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Informe a data Final!");
            dcFinal.requestFocus();
        } else {
            while (tmLivro.getRowCount() > 0) {
                tmLivro.removeRow(0);

            }
            SimpleDateFormat formato1 = new SimpleDateFormat("yyyy-MM-dd");
            Date dataInicio = Date.valueOf(formato1.format(dcInicial.getDate()));

            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            Date dataFinal = Date.valueOf(formato2.format(dcFinal.getDate()));



            rbean = dlc.listarDevolucaoFuncionario(Date.valueOf(formato1.format(dataInicio)), Date.valueOf(formato2.format(dataFinal)));

            if (rbean.size() == 0) {
                JOptionPane.showMessageDialog(this, "Nenhum empréstimo de livro registrado!");
            } else {
                String[] campos = new String[]{null, null, null, null, null, null};
                for (int i = 0; i < rbean.size(); i++) {
                    tmLivro.addRow(campos);

                    tmLivro.setValueAt(rbean.get(i).getNome_locatario(), i, 0);
                    tmLivro.setValueAt(rbean.get(i).getNome_livro(), i, 1);
                    tmLivro.setValueAt(rbean.get(i).getCodigoLivro(), i, 2);
                    tmLivro.setValueAt(rbean.get(i).getQuantidade(), i, 3);
                    tmLivro.setValueAt(rbean.get(i).getData_emprestimo(), i, 4);
                    tmLivro.setValueAt(rbean.get(i).getData_prevista_devolucao(), i, 5);

                }
            }
        }
    }

    public void listarDevolucaoOutro() {
        devolucaolivrosControl dlc = new devolucaolivrosControl();

        if (dcInicial.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Informe a data Inicial!");
            dcInicial.requestFocus();
        } else if (dcFinal.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Informe a data Final!");
            dcFinal.requestFocus();
        } else {
            while (tmLivro.getRowCount() > 0) {
                tmLivro.removeRow(0);

            }
            SimpleDateFormat formato1 = new SimpleDateFormat("yyyy-MM-dd");
            Date dataInicio = Date.valueOf(formato1.format(dcInicial.getDate()));

            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            Date dataFinal = Date.valueOf(formato2.format(dcFinal.getDate()));



            rbean = dlc.listarDevolucaoOutro(Date.valueOf(formato1.format(dataInicio)), Date.valueOf(formato2.format(dataFinal)));

            if (rbean.size() == 0) {
                JOptionPane.showMessageDialog(this, "Nenhum empréstimo de livro registrado!");

            } else {
                String[] campos = new String[]{null, null, null, null, null, null};
                for (int i = 0; i < rbean.size(); i++) {
                    tmLivro.addRow(campos);

                    tmLivro.setValueAt(rbean.get(i).getNome_locatario(), i, 0);
                    tmLivro.setValueAt(rbean.get(i).getNome_livro(), i, 1);
                    tmLivro.setValueAt(rbean.get(i).getCodigoLivro(), i, 2);
                    tmLivro.setValueAt(rbean.get(i).getQuantidade(), i, 3);
                    tmLivro.setValueAt(rbean.get(i).getData_emprestimo(), i, 4);
                    tmLivro.setValueAt(rbean.get(i).getData_prevista_devolucao(), i, 5);

                }
            }
        }
    }

    private void listarLocatarios() {

        if (cbOpcao.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione um tipo de locatário primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (cbOpcao.getSelectedItem().equals("Aluno")) {
            listarDevolucaoAluno();
        } else if (cbOpcao.getSelectedItem().equals("Funcionário")) {
            listarDevolucaoFuncionario();
        } else if (cbOpcao.getSelectedItem().equals("Outros")) {
            listarDevolucaoOutro();
        }
    }

    public boolean verificarCampos() {
        if (tbDevolucao.getSelectedRow() != -1) {
            return true;
        }
        return false;
    }

    public void rbtDataHoje() {

        java.util.Date dinicial = new java.util.Date();
        dcInicial.setDate(dinicial);

        java.util.Date dfinal = new java.util.Date();
        dcFinal.setDate(dfinal);

    }

    public void tabelaLinhaSelecionada(JTable tb) {
        if (tb.getSelectedRow() != -1) {
            tfNome.setText(rbean.get(tb.getSelectedRow()).getNome_locatario());
            tfLivro.setText(rbean.get(tb.getSelectedRow()).getNome_livro());
            tfCodigo.setText(rbean.get(tb.getSelectedRow()).getCodigoLivro().toString());
            tfQuantidade.setText(rbean.get(tb.getSelectedRow()).getQuantidade().toString());
            tfEmprestimo.setText(rbean.get(tb.getSelectedRow()).getData_emprestimo().toString());
            tfDevolucao.setText(rbean.get(tb.getSelectedRow()).getData_prevista_devolucao().toString());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        pcadastro = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDevolucao = new javax.swing.JTable();
        cbOpcao = new javax.swing.JComboBox();
        jRadioButton1 = new javax.swing.JRadioButton();
        btPesquisarDevolcao = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        dcInicial = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        dcFinal = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfQuantidade = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfLivro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfEmprestimo = new javax.swing.JTextField();
        tfDevolucao = new javax.swing.JTextField();
        btDevolver = new javax.swing.JButton();
        btVoltar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        tfDataDevolucao = new com.toedter.calendar.JDateChooser();
        btIrPEquipamentos = new javax.swing.JButton();
        Ramiro = new javax.swing.JPanel();
        lbDate = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Biblioteca - Devolução de livros");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icone.png")));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Devolução de livros"));

        pcadastro.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione um Item e Pesquise Emprestimos por Datas.:"));

        tbDevolucao.setModel(tmLivro);
        tbDevolucao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsmDevolucao=tbDevolucao.getSelectionModel();
        lsmDevolucao.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                if(! e.getValueIsAdjusting()){
                    tabelaLinhaSelecionada(tbDevolucao);
                }
            }
        });
        jScrollPane1.setViewportView(tbDevolucao);

        cbOpcao.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"", "Aluno", "Funcionário", "Outros"}));
        cbOpcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOpcaoActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Datas de hoje");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        btPesquisarDevolcao.setText("Pesquisar");
        btPesquisarDevolcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarDevolcaoActionPerformed(evt);
            }
        });

        jLabel8.setText("Data de Emprestimo.:");

        jLabel9.setText("Data Prev. de Devolução.:");

        javax.swing.GroupLayout pcadastroLayout = new javax.swing.GroupLayout(pcadastro);
        pcadastro.setLayout(pcadastroLayout);
        pcadastroLayout.setHorizontalGroup(
            pcadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pcadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pcadastroLayout.createSequentialGroup()
                        .addComponent(cbOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pcadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(dcInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pcadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pcadastroLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addContainerGap())
                            .addGroup(pcadastroLayout.createSequentialGroup()
                                .addComponent(dcFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btPesquisarDevolcao, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1)))
        );

        pcadastroLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dcFinal, dcInicial});

        pcadastroLayout.setVerticalGroup(
            pcadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pcadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pcadastroLayout.createSequentialGroup()
                        .addGroup(pcadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pcadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dcInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pcadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btPesquisarDevolcao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jRadioButton1))))
                    .addComponent(cbOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pcadastroLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btPesquisarDevolcao, cbOpcao, dcFinal, dcInicial});

        jLabel4.setText("Data de empréstimo.:");

        jLabel3.setText("Quantidade.:");

        jLabel2.setText("Livro.:");

        jLabel1.setText("Nome do Locatário.:");

        jLabel6.setText("Código do livro.:");

        jLabel5.setText("Data prevista para devolução.:");

        btDevolver.setText("Devolver Livro");
        btDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDevolverActionPerformed(evt);
            }
        });

        btVoltar.setText("Ir para Menu");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        jLabel7.setText("Data de devolução:");

        btIrPEquipamentos.setText("Devoluções de Equipamentos");
        btIrPEquipamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIrPEquipamentosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pcadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNome)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                                .addComponent(tfDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfLivro))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btVoltar)
                .addGap(18, 18, 18)
                .addComponent(btIrPEquipamentos)
                .addGap(87, 87, 87))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btDevolver, btIrPEquipamentos, btVoltar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pcadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(tfDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(tfDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btIrPEquipamentos)
                        .addComponent(btVoltar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btDevolver, btIrPEquipamentos, btVoltar});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tfCodigo, tfDataDevolucao, tfDevolucao, tfEmprestimo, tfLivro, tfNome, tfQuantidade});

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 0);
        getContentPane().add(jPanel1, gridBagConstraints);

        Ramiro.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Ramiro.setLayout(null);
        Ramiro.add(lbDate);
        lbDate.setBounds(10, 4, 100, 20);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 141;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 52, 0);
        getContentPane().add(Ramiro, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setLayout(null);

        jLabel11.setText("Usuário:");
        jPanel5.add(jLabel11);
        jLabel11.setBounds(10, 0, 60, 30);
        jPanel5.add(msgNome);
        msgNome.setBounds(70, 0, 450, 30);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 566;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 52, 0);
        getContentPane().add(jPanel5, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(null);
        jPanel4.add(lbHora);
        lbHora.setBounds(30, 4, 90, 20);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 119;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 52, 0);
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
        setBounds((screenSize.width-867)/2, (screenSize.height-647)/2, 867, 647);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        rbtDataHoje();        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void cbOpcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOpcaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbOpcaoActionPerformed

    private void btPesquisarDevolcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarDevolcaoActionPerformed
        listarLocatarios();
    }//GEN-LAST:event_btPesquisarDevolcaoActionPerformed

    private void btDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDevolverActionPerformed
        cadastraDataDevol();
    }//GEN-LAST:event_btDevolverActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        principal p = new principal();
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btIrPEquipamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIrPEquipamentosActionPerformed
        DevolucaoEquip dv = new DevolucaoEquip();
        int resposta = JOptionPane.showConfirmDialog(this, "Antes de sair desta tela verifique se todos os dasdos foram salvos.\nVocê deseja realmente sair?\n\"Sim\" para continuar.", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            dv.setVisible(true);
            dispose();
        }


    }//GEN-LAST:event_btIrPEquipamentosActionPerformed

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
            java.util.logging.Logger.getLogger(devolucaolivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(devolucaolivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(devolucaolivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(devolucaolivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new devolucaolivro().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Pendencias;
    private javax.swing.JPanel Ramiro;
    private javax.swing.JButton btDevolver;
    private javax.swing.JButton btIrPEquipamentos;
    private javax.swing.JButton btPesquisarDevolcao;
    private javax.swing.JButton btVoltar;
    private javax.swing.JComboBox cbOpcao;
    private com.toedter.calendar.JDateChooser dcFinal;
    private com.toedter.calendar.JDateChooser dcInicial;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
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
    private javax.swing.JPanel pcadastro;
    private javax.swing.JTable tbDevolucao;
    private javax.swing.JTextField tfCodigo;
    private com.toedter.calendar.JDateChooser tfDataDevolucao;
    private javax.swing.JTextField tfDevolucao;
    private javax.swing.JTextField tfEmprestimo;
    private javax.swing.JTextField tfLivro;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfQuantidade;
    // End of variables declaration//GEN-END:variables
}
