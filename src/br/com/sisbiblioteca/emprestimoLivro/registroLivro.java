package br.com.sisbiblioteca.emprestimoLivro;

import br.com.sisbiblioteca.Sobre.Sobre;
import br.com.sisbiblioteca.cadastroEquipamento.cadastroequipamento;
import br.com.sisbiblioteca.cadastroInstituicao.cadastroInstituicao;
import br.com.sisbiblioteca.cadastroLivro.cadastroLivro;
import br.com.sisbiblioteca.cadastroLivro.livroBean;
import br.com.sisbiblioteca.cadastroLivro.livroControl;
import br.com.sisbiblioteca.cadastrolocatario.cadastrolocatario;
import br.com.sisbiblioteca.cadastrolocatario.cadastrolocatarioBean;
import br.com.sisbiblioteca.cadastrolocatario.cadastrolocatarioControl;
import br.com.sisbiblioteca.cadastrousuarios.cadastrousuario;
import br.com.sisbiblioteca.devolucaoEquip.DevolucaoEquip;
import br.com.sisbiblioteca.devolucaolivros.devolucaolivro;
import br.com.sisbiblioteca.emprestimoEquipamento.EmprestimoEquipamento;
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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class registroLivro extends javax.swing.JFrame {

    /**
     * Creates new form registroLivro
     */
    public registroLivro() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        msg();
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
    List<cadastrolocatarioBean> cadastrolocatariobean;
    DefaultTableModel tmLocatarios = new DefaultTableModel(null, new String[]{"Código", "Nome", "Data do Cadastro", "Hora do Cadastro", "Tipo locatário"});
    ListSelectionModel lsmLocatarios;
    DefaultTableModel tmLivros = new DefaultTableModel(null, new String[]{"Código", "Nome", "Autor", "Categoria", "Gênero", "Editora", "Clas.Etária", "Quantidade"});
    List<livroBean> livroBean;
    ListSelectionModel lsmLivros;
    List<registroLivroBean> rlbean;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cbOpcao = new javax.swing.JComboBox();
        tfPesquisar = new javax.swing.JTextField();
        btPesquisarlocatario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLocatario = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        brReservar = new javax.swing.JButton();
        tfQuantidade = new javax.swing.JTextField();
        tfHoraemprestimo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cbLivro = new javax.swing.JComboBox();
        tfPesquisarLivros = new javax.swing.JTextField();
        btPesquisarlivros = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbLivros = new javax.swing.JTable();
        dcDataPrevista = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        btFinalizar = new javax.swing.JButton();
        btMenuprincipal = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        dcDataEmprestimo = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        lbHora = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        msgNome = new javax.swing.JLabel();
        Ramiro = new javax.swing.JPanel();
        lbDate = new javax.swing.JLabel();
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
        setTitle("Sistema de Biblioteca - Empréstimo de Livros");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icone.png")));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Empréstimo de Livro"));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Escolha um locatário.:"));

        cbOpcao.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"...", "Aluno", "Funcionário", "Outros"}));

        tfPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPesquisarActionPerformed(evt);
            }
        });

        btPesquisarlocatario.setText("Pesquisar");
        btPesquisarlocatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarlocatarioActionPerformed(evt);
            }
        });

        tbLocatario.setModel(tmLocatarios);
        jScrollPane1.setViewportView(tbLocatario);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btPesquisarlocatario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btPesquisarlocatario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(tfPesquisar, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbOpcao, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setText("Quantidade.:");

        brReservar.setText("Reservar");
        brReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brReservarActionPerformed(evt);
            }
        });

        tfQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfQuantidadeActionPerformed(evt);
            }
        });

        jLabel2.setText("Hora do empréstimo.:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Escolha um livro.:"));

        cbLivro.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"...", "Nome", "Autor", "Categoria", "Gênero", "Editora"}));
        cbLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLivroActionPerformed(evt);
            }
        });

        tfPesquisarLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPesquisarLivrosActionPerformed(evt);
            }
        });

        btPesquisarlivros.setText("Pesquisar");
        btPesquisarlivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarlivrosActionPerformed(evt);
            }
        });

        tbLivros.setModel(tmLivros);
        jScrollPane2.setViewportView(tbLivros);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfPesquisarLivros, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btPesquisarlivros)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btPesquisarlivros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(tfPesquisarLivros, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbLivro, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jLabel3.setText("Data prev.Devolução.:");

        btFinalizar.setText("Finalizar");
        btFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarActionPerformed(evt);
            }
        });

        btMenuprincipal.setText("Menu principal");
        btMenuprincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMenuprincipalActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar");

        jLabel1.setText("Data do empréstimo.:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1))
                            .addComponent(dcDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(tfHoraemprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(dcDataPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btMenuprincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(brReservar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcDataPrevista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tfHoraemprestimo))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dcDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btMenuprincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(37, 29, 0, 0);
        getContentPane().add(jPanel3, gridBagConstraints);

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
        gridBagConstraints.insets = new java.awt.Insets(6, 30, 15, 0);
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setLayout(null);

        jLabel11.setText("Usuário:");
        jPanel5.add(jLabel11);
        jLabel11.setBounds(10, 0, 60, 30);
        jPanel5.add(msgNome);
        msgNome.setBounds(70, 0, 310, 30);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 404;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 15, 0);
        getContentPane().add(jPanel5, gridBagConstraints);

        Ramiro.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Ramiro.setLayout(null);
        Ramiro.add(lbDate);
        lbDate.setBounds(10, 4, 100, 20);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 159;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 9, 15, 3);
        getContentPane().add(Ramiro, gridBagConstraints);

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
        setBounds((screenSize.width-749)/2, (screenSize.height-715)/2, 749, 715);
    }// </editor-fold>//GEN-END:initComponents

    //Mostrar Livros
    public void mostrarLivros(List<livroBean> livrosB) {
        while (tmLivros.getRowCount() > 0) {
            tmLivros.removeRow(0);
        }
        if (livrosB.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum Livro encontrado!");
        } else {
            String[] campos = new String[]{null, null, null, null, null, null, null, null};
            for (int i = 0; i < livrosB.size(); i++) {
                tmLivros.addRow(campos);
                tmLivros.setValueAt(livrosB.get(i).getIdlivro(), i, 0);
                tmLivros.setValueAt(livrosB.get(i).getNomelivro(), i, 1);
                tmLivros.setValueAt(livrosB.get(i).getAutor(), i, 2);
                tmLivros.setValueAt(livrosB.get(i).getCategoria(), i, 3);
                tmLivros.setValueAt(livrosB.get(i).getGenero(), i, 4);
                tmLivros.setValueAt(livrosB.get(i).getEditora(), i, 5);
                tmLivros.setValueAt(livrosB.get(i).getClassificacaoEtaria(), i, 6);
                tmLivros.setValueAt(livrosB.get(i).getQuantidade(), i, 7);

            }
        }
    }
    //Listar Por Nome

    public void listarLivro() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivros("%" + tfPesquisar.getText().trim() + "%");

        mostrarLivros(livroBean);

    }
    //Listar Por Autor

    public void listarLivroAutor() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivrosAutor("%" + tfPesquisar.getText().trim() + "%");

        mostrarLivros(livroBean);

    }

    //Listar Por Categoria
    public void listarLivroCategoria() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivrosCategoria("%" + tfPesquisar.getText().trim() + "%");

        mostrarLivros(livroBean);

    }
    //Listar Por Genero

    public void listarLivroGenero() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivrosGenero("%" + tfPesquisar.getText().trim() + "%");

        mostrarLivros(livroBean);

    }
    //Listar Por Editora

    public void listarLivroEditora() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivros("%" + tfPesquisar.getText().trim() + "%");

        mostrarLivros(livroBean);
    }

    //Mostrar Locatários
    public void listar_locatario_aluno() {
        cadastrolocatarioControl ac = new cadastrolocatarioControl();

        cadastrolocatariobean = ac.listar_locatario_aluno("%" + tfPesquisar.getText().trim() + "%");
        mostrar_locatario_aluno(cadastrolocatariobean);

    }

    public void mostrar_locatario_aluno(List<cadastrolocatarioBean> usuarioB) {
        while (tmLocatarios.getRowCount() > 0) {
            tmLocatarios.removeRow(0);
        }
        if (usuarioB.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum Locatário encontrado!!!");
        } else {
            String[] campos = new String[]{null, null, null, null, null};

            for (int i = 0; i < usuarioB.size(); i++) {
                tmLocatarios.addRow(campos);
                tmLocatarios.setValueAt(usuarioB.get(i).getIdlocatario(), i, 0);
                tmLocatarios.setValueAt(usuarioB.get(i).getNome_locatario(), i, 1);
                tmLocatarios.setValueAt(usuarioB.get(i).getData_cadastro(), i, 2);
                tmLocatarios.setValueAt(usuarioB.get(i).getHora_cadastro(), i, 3);
                tmLocatarios.setValueAt(usuarioB.get(i).getTipo_locatario(), i, 4);


            }
        }
    }

    public void listar_locatario_funcionario() {
        cadastrolocatarioControl ac = new cadastrolocatarioControl();

        cadastrolocatariobean = ac.listar_locatario_funcionario("%" + tfPesquisar.getText().trim() + "%");
        mostrar_locatario_funcionario(cadastrolocatariobean);

    }

    public void mostrar_locatario_funcionario(List<cadastrolocatarioBean> usuarioB) {
        while (tmLocatarios.getRowCount() > 0) {
            tmLocatarios.removeRow(0);
        }
        if (usuarioB.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum Locatário encontrado!!!");
        } else {
            String[] campos = new String[]{null, null, null, null, null};

            for (int i = 0; i < usuarioB.size(); i++) {
                tmLocatarios.addRow(campos);
                tmLocatarios.setValueAt(usuarioB.get(i).getIdlocatario(), i, 0);
                tmLocatarios.setValueAt(usuarioB.get(i).getNome_locatario(), i, 1);
                tmLocatarios.setValueAt(usuarioB.get(i).getData_cadastro(), i, 2);
                tmLocatarios.setValueAt(usuarioB.get(i).getHora_cadastro(), i, 3);
                tmLocatarios.setValueAt(usuarioB.get(i).getTipo_locatario(), i, 4);
            }
        }
    }

    public void listar_locatario_outro() {
        cadastrolocatarioControl ac = new cadastrolocatarioControl();

        cadastrolocatariobean = ac.listar_locatario_outro("%" + tfPesquisar.getText().trim() + "%");
        mostrar_locatario_outro(cadastrolocatariobean);

    }

    public void mostrar_locatario_outro(List<cadastrolocatarioBean> usuarioB) {
        while (tmLocatarios.getRowCount() > 0) {
            tmLocatarios.removeRow(0);
        }
        if (usuarioB.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum Locatário encontrado!!!");
        } else {
            String[] campos = new String[]{null, null, null, null, null};

            for (int i = 0; i < usuarioB.size(); i++) {
                tmLocatarios.addRow(campos);
                tmLocatarios.setValueAt(usuarioB.get(i).getIdlocatario(), i, 0);
                tmLocatarios.setValueAt(usuarioB.get(i).getNome_locatario(), i, 1);
                tmLocatarios.setValueAt(usuarioB.get(i).getData_cadastro(), i, 2);
                tmLocatarios.setValueAt(usuarioB.get(i).getHora_cadastro(), i, 3);
                tmLocatarios.setValueAt(usuarioB.get(i).getTipo_locatario(), i, 4);

            }
        }
    }

    private void listarLocatarios() {

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

    private void excecoesLivros() {
        int quant = Integer.parseInt(String.valueOf(tfQuantidade.getText().trim().toString()));
        if (cbOpcao.getSelectedItem().equals("...")) {

            JOptionPane.showMessageDialog(null, "Selecione um tipo de locatário primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (cbOpcao.getSelectedItem().equals("Aluno") && quant > 3) {

            JOptionPane.showMessageDialog(null, "Operação impossível para este tipo de locatário! Número máximo de locação atingida!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (cbOpcao.getSelectedItem().equals("Funcionário") && quant > 5) {

            JOptionPane.showMessageDialog(null, "Operação impossível para este tipo de locatário! Número máximo de locação atingida!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (cbOpcao.getSelectedItem().equals("Outros") && quant > 2) {

            JOptionPane.showMessageDialog(null, "Operação impossível para este tipo de locatário! Número máximo de locação atingida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Registrar
    public boolean verificaCampos() {
        if (tbLocatario.getSelectedRow() != -1 && tbLivros.getSelectedRow() != -1) {
            return true;
        }
        return false;
    }

    private void confirmarRegistroAluno() {

        if (verificaCampos()) {
            registroLivroBean ab = new registroLivroBean();
            ab.setIdlocatario(cadastrolocatariobean.get(tbLocatario.getSelectedRow()).getIdlocatario());
            ab.setIdlivro(livroBean.get(tbLivros.getSelectedRow()).getIdlivro());
            ab.setQuantidade(Integer.parseInt(String.valueOf(tfQuantidade.getText().trim().toString())));
            ab.setTipo_locatario("aluno");

            livroBean lb = new livroBean();
            lb.setIdlivro(livroBean.get(tbLivros.getSelectedRow()).getIdlivro());
            lb.setStatus("ocupado");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dataEmprestimo = Date.valueOf(formato.format(dcDataEmprestimo.getDate()));
            ab.setData_emprestimo(dataEmprestimo);

            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            Date dataPrevista = Date.valueOf(formato2.format(dcDataPrevista.getDate()));
            ab.setData_prevista_devolucao(dataPrevista);

            ab.setStatusEmp("utilizando");

            Calendar now = Calendar.getInstance();
            ab.setHora_emprestimo(String.format("%1$tH:%1$tM:%1$tS", now));

            registroLivroControl ac = new registroLivroControl();
            excecoesLivros();
            ac.registrarLivroAluno(ab);
            ac.alterarStatus(lb);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Locatário e/ou um Livro!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirmarRegistroFuncionario() {

        if (verificaCampos()) {

            registroLivroBean ab = new registroLivroBean();
            ab.setIdlocatario(cadastrolocatariobean.get(tbLocatario.getSelectedRow()).getIdlocatario());
            ab.setIdlivro(livroBean.get(tbLivros.getSelectedRow()).getIdlivro());
            ab.setQuantidade(Integer.parseInt(String.valueOf(tfQuantidade.getText().trim().toString())));
            ab.setTipo_locatario("funcionario");

            livroBean lb = new livroBean();
            lb.setIdlivro(livroBean.get(tbLivros.getSelectedRow()).getIdlivro());
            lb.setStatus("ocupado");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dataEmprestimo = Date.valueOf(formato.format(dcDataEmprestimo.getDate()));
            ab.setData_emprestimo(dataEmprestimo);

            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            Date dataPrevista = Date.valueOf(formato2.format(dcDataPrevista.getDate()));
            ab.setData_prevista_devolucao(dataPrevista);

            Calendar now = Calendar.getInstance();
            ab.setHora_emprestimo(String.format("%1$tH:%1$tM:%1$tS", now));

            ab.setStatusEmp("utilizando");
            
            registroLivroControl ac = new registroLivroControl();
            excecoesLivros();
//            ac.alterarStatus(ab);
            ac.registrarLivroFuncionario(ab);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Locatário e/ou Livro!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirmarRegistroOutro() {

        if (verificaCampos()) {
            registroLivroBean ab = new registroLivroBean();
            ab.setIdlocatario(cadastrolocatariobean.get(tbLocatario.getSelectedRow()).getIdlocatario());
            ab.setIdlivro(livroBean.get(tbLivros.getSelectedRow()).getIdlivro());
            ab.setQuantidade(Integer.parseInt(String.valueOf(tfQuantidade.getText().trim().toString())));
            ab.setTipo_locatario("outro");

            livroBean lb = new livroBean();
            lb.setIdlivro(livroBean.get(tbLivros.getSelectedRow()).getIdlivro());
            lb.setStatus("ocupado");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dataEmprestimo = Date.valueOf(formato.format(dcDataEmprestimo.getDate()));
            ab.setData_emprestimo(dataEmprestimo);

            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            Date dataPrevista = Date.valueOf(formato2.format(dcDataPrevista.getDate()));
            ab.setData_prevista_devolucao(dataPrevista);

            Calendar now = Calendar.getInstance();
            ab.setHora_emprestimo(String.format("%1$tH:%1$tM:%1$tS", now));
            
            ab.setStatusEmp("utilizando");
            
            registroLivroControl ac = new registroLivroControl();
            excecoesLivros();
//            ac.alterarStatus(ab);
            ac.registrarLivroOutro(ab);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Locatário e/ou Livro!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Controle de Quantidade
    public void quantidade() {
        int quant = Integer.parseInt(String.valueOf(tfQuantidade.getText().trim().toString()));
        int q2 = livroBean.get(tbLivros.getSelectedRow()).getQuantidade();
        int r = q2 - quant;

        if (r >= 0) {

            livroBean cp = new livroBean();

            cp.setQuantidade(r);
            cp.setIdlivro(livroBean.get(tbLivros.getSelectedRow()).getIdlivro());

            livroControl cc = new livroControl();
            cc.Controlequantidade(cp);

            if (cbOpcao.getSelectedItem().equals("...")) {
                JOptionPane.showMessageDialog(null, "Selecione um tipo de locatário primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);

            } else if (cbOpcao.getSelectedItem().equals("Aluno")) {
                excecoesLivros();
                confirmarRegistroAluno();
                JOptionPane.showMessageDialog(null, "Locação realizada com sucesso");
                listar_locatario_aluno();
                listarLivro();

            } else if (cbOpcao.getSelectedItem().equals("Funcionário")) {
                excecoesLivros();
                confirmarRegistroFuncionario();
                JOptionPane.showMessageDialog(null, "Locação realizada com sucesso");
                listar_locatario_funcionario();
                listarLivro();

            } else if (cbOpcao.getSelectedItem().equals("Outros")) {
                excecoesLivros();
                confirmarRegistroOutro();
                JOptionPane.showMessageDialog(null, "Locação realizada com sucesso");
                listar_locatario_outro();
                listarLivro();
            }
        }
    }

    private void camposNaoEditaveis() {

        tfQuantidade.setEditable(false);
        // btFinalizar.setEnabled(false);

    }

    private void limparCampos() {
        tfQuantidade.setText("");

        camposNaoEditaveis();

        // btFinalizar.setEnabled(false);
    }
    //Listar

    private void opcaoLivro() {

        if (cbLivro.getSelectedItem().equals("...")) {
            JOptionPane.showMessageDialog(null, "Selecione um tipo de livro primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (cbLivro.getSelectedItem().equals("Nome")) {
            listarLivro();
        } else if (cbLivro.getSelectedItem().equals("Autor")) {
            listarLivroAutor();
        } else if (cbLivro.getSelectedItem().equals("Categoria")) {
            listarLivroCategoria();
        } else if (cbLivro.getSelectedItem().equals("Gênero")) {
            listarLivroGenero();
        } else if (cbLivro.getSelectedItem().equals("Editora")) {
            listarLivroEditora();
        }
    }

    public void iniciaRelogio() {
        new Thread() {//instancia nova thread já implementando o método run()
            @Override
            public void run() {//sobrescreve o método run()

                while (0 == 0) {//while para fazer o loop infinito
                    GregorianCalendar gc = new GregorianCalendar();//novo gregorian calendar, onde temos a data atual
                    int hora = gc.get(Calendar.HOUR_OF_DAY);//pega as horas
                    int minuto = gc.get(Calendar.MINUTE);//pega os minutos
                    int segundo = gc.get(Calendar.SECOND);//pega os segundos

                    String horaString;//nova string horas
                    String minString;//nova string minutos
                    String segundoString;//nova string segundos
                    if (hora < 10) { //se hora for menor que 10 precisa colocar um 0 à esquerda
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
                    tfHoraemprestimo.setText(horaString + ":" + minString + ":" + segundoString);//seta hora atual no label
                    try {
                        sleep(1000);//faz a thread entrar em estado de espera por 1000 milissegundos ou 1 segundo

                    } catch (Exception e) {
                    }
                }
            }
        }.start();//inicia a thread.
    }

private void btFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarActionPerformed
    quantidade();
    if (tfQuantidade.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Preencha o campo Quantidade!", "Erro", JOptionPane.ERROR_MESSAGE);

    }
}//GEN-LAST:event_btFinalizarActionPerformed

private void brReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brReservarActionPerformed
    reservaLivro a = new reservaLivro();
    a.main(null);
    dispose();
}//GEN-LAST:event_brReservarActionPerformed

private void tfPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPesquisarActionPerformed
    listarLocatarios();
}//GEN-LAST:event_tfPesquisarActionPerformed

private void btPesquisarlocatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarlocatarioActionPerformed
    listarLocatarios();
}//GEN-LAST:event_btPesquisarlocatarioActionPerformed

private void tfPesquisarLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPesquisarLivrosActionPerformed
    opcaoLivro();
}//GEN-LAST:event_tfPesquisarLivrosActionPerformed

private void btPesquisarlivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarlivrosActionPerformed
    opcaoLivro();
}//GEN-LAST:event_btPesquisarlivrosActionPerformed

private void tfQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfQuantidadeActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tfQuantidadeActionPerformed

private void cbLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLivroActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_cbLivroActionPerformed

private void btMenuprincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMenuprincipalActionPerformed
    principal a = new principal();
    a.main(null);
    dispose();
}//GEN-LAST:event_btMenuprincipalActionPerformed

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
            java.util.logging.Logger.getLogger(registroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registroLivro().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Pendencias;
    private javax.swing.JPanel Ramiro;
    private javax.swing.JButton brReservar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btFinalizar;
    private javax.swing.JButton btMenuprincipal;
    private javax.swing.JButton btPesquisarlivros;
    private javax.swing.JButton btPesquisarlocatario;
    private javax.swing.JComboBox cbLivro;
    private javax.swing.JComboBox cbOpcao;
    private com.toedter.calendar.JDateChooser dcDataEmprestimo;
    private com.toedter.calendar.JDateChooser dcDataPrevista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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
    private javax.swing.JTable tbLivros;
    private javax.swing.JTable tbLocatario;
    private javax.swing.JTextField tfHoraemprestimo;
    private javax.swing.JTextField tfPesquisar;
    private javax.swing.JTextField tfPesquisarLivros;
    private javax.swing.JTextField tfQuantidade;
    // End of variables declaration//GEN-END:variables
}
