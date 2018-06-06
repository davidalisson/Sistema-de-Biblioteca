package br.com.sisbiblioteca.reservaLivro;
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
import br.com.sisbiblioteca.emprestimoLivro.registroLivro;
import br.com.sisbiblioteca.login.login;
import br.com.sisbiblioteca.pendenciasEquip.pendenciasEquip;
import br.com.sisbiblioteca.principal.principal;
import br.com.sisbiblioteca.registroequipamentos.reservaequipamento;
import java.awt.Toolkit;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class reservaLivro extends javax.swing.JFrame {

    /** Creates new form reservaLivro */
    public reservaLivro() {
        initComponents();
        iniciaRelogio();
        setExtendedState(MAXIMIZED_BOTH);
        tfHorareserva.setEnabled(false);
        camposNaoEditaveis();
        msg();

    }
    public static String nome;

    public void msg() {
        msgNome.setText("" + nome);
        
        java.util.Date h = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        lbDate.setText(""+ formato.format(h));
        
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
                    lbHora.setText(""+horaString + ":" + minString + ":" + segundoString);//seta hora atual no label
                    try {
                        sleep(1000);//faz a thread entrar em estado de espera por 1000 milissegundos ou 1 segundo
                    } catch (Exception e) {
                    }
                }
            }
        }.start();//inicia a thread.
    }

    List<cadastrolocatarioBean> cadastrolocatariobean;
    DefaultTableModel tmLocatarios = new DefaultTableModel(null, new String[]{"Código","Nome", "Data do Cadastro", "Hora do Cadastro", "Tipo locatário"});
    ListSelectionModel lsmLocatario;

    DefaultTableModel tmLivros = new DefaultTableModel(null, new String [] {"Código","Nome", "Autor", "Categoria", "Gênero", "Editora", "Clas.Etária", "Quantidade"});
    List<livroBean> livroBean;
    ListSelectionModel lsmLivros;

    DefaultTableModel tmReservas = new DefaultTableModel(null, new String [] {"Livro", "Locatário", "Data reserva","Hora da reserva", "Reservado para:"});
    List<reservaBean> reservaBean;
    ListSelectionModel lsmRegistros;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        pProcurarreservas = new javax.swing.JPanel();
        btPesquisarReservas = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbReservas = new javax.swing.JTable();
        lbrelogio2 = new javax.swing.JLabel();
        cbReservas = new javax.swing.JComboBox();
        tfPesquisarLivros1 = new javax.swing.JTextField();
        pEscolhalivro = new javax.swing.JPanel();
        btPesquisarlivros = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbLivros = new javax.swing.JTable();
        cbOpcao2 = new javax.swing.JComboBox();
        tfPesquisarLivros = new javax.swing.JTextField();
        dcDiaemprestimo = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btCancelar = new javax.swing.JButton();
        dcDataReserva = new com.toedter.calendar.JDateChooser();
        btMenuPrincipal = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        pEscolhalocatario = new javax.swing.JPanel();
        btPesquisarLocatario = new javax.swing.JButton();
        lbrelogio = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbLocatarios = new javax.swing.JTable();
        cbOpcao = new javax.swing.JComboBox();
        tfPesquisarLocatario = new javax.swing.JTextField();
        btNovareserva = new javax.swing.JButton();
        tfHorareserva = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfQuantidade = new javax.swing.JTextField();
        btFinalizar = new javax.swing.JButton();
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
        setTitle("Sistem de Biblioteca - Reserva de livros");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icone.png")));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Reserva de Livros"));

        pProcurarreservas.setBorder(javax.swing.BorderFactory.createTitledBorder("Procurar reservas.:"));
        pProcurarreservas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btPesquisarReservas.setText("Pesquisar");
        btPesquisarReservas.setToolTipText("Clique no botão (Pesquisar) para procurar usuário");
        btPesquisarReservas.setBorder(null);
        btPesquisarReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarReservasActionPerformed(evt);
            }
        });
        pProcurarreservas.add(btPesquisarReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 110, 30));

        tbReservas.setModel(tmReservas);
        tbReservas.setToolTipText("Tabela de usuários do Sistema de Biblioteca");
        jScrollPane4.setViewportView(tbReservas);

        pProcurarreservas.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 450, 130));
        pProcurarreservas.add(lbrelogio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 454, 200, 30));

        cbReservas.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"...", "Aluno", "Funcionário", "Outros"}));
        cbReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbReservasActionPerformed(evt);
            }
        });
        pProcurarreservas.add(cbReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, 30));
        pProcurarreservas.add(tfPesquisarLivros1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 220, 30));

        pEscolhalivro.setBorder(javax.swing.BorderFactory.createTitledBorder("Escolha o Livro:"));
        pEscolhalivro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btPesquisarlivros.setText("Pesquisar");
        btPesquisarlivros.setToolTipText("Clique no botão (Pesquisar) para procurar usuário");
        btPesquisarlivros.setBorder(null);
        btPesquisarlivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarlivrosActionPerformed(evt);
            }
        });
        pEscolhalivro.add(btPesquisarlivros, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 100, 30));

        tbLivros.setModel(tmLivros);
        tbLivros.setToolTipText("Tabela de usuários do Sistema de Biblioteca");
        jScrollPane3.setViewportView(tbLivros);

        pEscolhalivro.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 480, 109));

        cbOpcao2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"...", "Nome", "Autor", "Categoria", "Gênero", "Editora"}));
        cbOpcao2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOpcao2ActionPerformed(evt);
            }
        });
        pEscolhalivro.add(cbOpcao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 110, 30));
        pEscolhalivro.add(tfPesquisarLivros, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 250, 30));

        jLabel3.setText("Hora reserva.:");

        jLabel1.setText("Data reserva.:");

        btCancelar.setText("Cancelar");
        btCancelar.setBorder(null);
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btMenuPrincipal.setText("Menu principal");
        btMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMenuPrincipalActionPerformed(evt);
            }
        });

        jLabel4.setText("Quantidade.:");

        pEscolhalocatario.setBorder(javax.swing.BorderFactory.createTitledBorder("Escolha um Locatário:"));
        pEscolhalocatario.setLayout(null);

        btPesquisarLocatario.setText("Pesquisar");
        btPesquisarLocatario.setToolTipText("Clique no botão (Pesquisar) para procurar usuário");
        btPesquisarLocatario.setBorder(null);
        btPesquisarLocatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarLocatarioActionPerformed(evt);
            }
        });
        pEscolhalocatario.add(btPesquisarLocatario);
        btPesquisarLocatario.setBounds(390, 30, 110, 30);
        pEscolhalocatario.add(lbrelogio);
        lbrelogio.setBounds(560, 454, 200, 30);

        tbLocatarios.setModel(tmLocatarios);
        tbLocatarios.setToolTipText("Tabela de usuários do Sistema de Biblioteca");
        jScrollPane2.setViewportView(tbLocatarios);

        pEscolhalocatario.add(jScrollPane2);
        jScrollPane2.setBounds(10, 70, 490, 130);

        cbOpcao.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"...", "Aluno", "Funcionário", "Outros"}));
        pEscolhalocatario.add(cbOpcao);
        cbOpcao.setBounds(10, 30, 110, 30);
        pEscolhalocatario.add(tfPesquisarLocatario);
        tfPesquisarLocatario.setBounds(130, 30, 250, 30);

        btNovareserva.setText("Nova Reserva");
        btNovareserva.setBorder(null);
        btNovareserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovareservaActionPerformed(evt);
            }
        });

        jLabel2.setText("Data empréstimo.:");

        btFinalizar.setText("Finalizar");
        btFinalizar.setBorder(null);
        btFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pEscolhalivro, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcDataReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(dcDiaemprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tfHorareserva, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel3)))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pEscolhalocatario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pProcurarreservas, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btNovareserva, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCancelar, btFinalizar, btMenuPrincipal, btNovareserva});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfQuantidade)
                    .addComponent(dcDataReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dcDiaemprestimo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfHorareserva))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pProcurarreservas, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                    .addComponent(pEscolhalocatario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pEscolhalivro, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btNovareserva, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btCancelar, btFinalizar, btMenuPrincipal, btNovareserva});

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipady = -2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        getContentPane().add(jPanel1, gridBagConstraints);

        Ramiro.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Ramiro.setLayout(null);
        Ramiro.add(lbDate);
        lbDate.setBounds(10, 4, 100, 20);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 151;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 8, 10);
        getContentPane().add(Ramiro, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setLayout(null);

        jLabel11.setText("Usuário:");
        jPanel5.add(jLabel11);
        jLabel11.setBounds(10, 0, 60, 30);
        jPanel5.add(msgNome);
        msgNome.setBounds(70, 0, 670, 30);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 763;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 8, 0);
        getContentPane().add(jPanel5, gridBagConstraints);

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
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 8, 0);
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
        setBounds((screenSize.width-1082)/2, (screenSize.height-683)/2, 1082, 683);
    }// </editor-fold>//GEN-END:initComponents
//Registrar

    public boolean verificaCampos(){
        if(tbLocatarios.getSelectedRow() != -1 && tbLivros.getSelectedRow() != -1){
               return true;
        }
        return false;
    }

     private void confirmarReservaAluno(){

        if(verificaCampos()){
            reservaBean ab = new reservaBean();
            ab.setIdlocatario(cadastrolocatariobean.get(tbLocatarios.getSelectedRow()).getIdlocatario());
            ab.setIdlivro(livroBean.get(tbLivros.getSelectedRow()).getIdlivro());
            ab.setTipo_locatario("aluno");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dataReserva = Date.valueOf(formato.format(dcDataReserva.getDate()));
            ab.setDatareserva(dataReserva);

            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            Date diaEmprestimo = Date.valueOf(formato2.format(dcDiaemprestimo.getDate()));
            ab.setDiaemprestimo(diaEmprestimo);

            Calendar  now = Calendar.getInstance();
            ab.setHorareserva(String.format("%1$tH:%1$tM:%1$tS", now));
             ab.setQuantidade(Integer.parseInt(String.valueOf(tfQuantidade.getText())));

            reservaControl ac = new reservaControl();
            preencherCampos();
            ac.reservarLivroAluno(ab);
    }else {
            JOptionPane.showMessageDialog(null, "Selecione um Locatário e/ou um Livro!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    }

     private void confirmarReservaFuncionario(){

        if(verificaCampos()){
            reservaBean ab = new reservaBean();
            ab.setIdlocatario(cadastrolocatariobean.get(tbLocatarios.getSelectedRow()).getIdlocatario());
            ab.setIdlivro(livroBean.get(tbLivros.getSelectedRow()).getIdlivro());
            ab.setTipo_locatario("funcionario");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dataReserva = Date.valueOf(formato.format(dcDataReserva.getDate()));
            ab.setDatareserva(dataReserva);

            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            Date diaEmprestimo = Date.valueOf(formato2.format(dcDiaemprestimo.getDate()));
            ab.setDiaemprestimo(diaEmprestimo);

            Calendar  now = Calendar.getInstance();
            ab.setHorareserva(String.format("%1$tH:%1$tM:%1$tS", now));
             ab.setQuantidade(Integer.parseInt(String.valueOf(tfQuantidade.getText())));

            reservaControl ac = new reservaControl();
            preencherCampos();
            ac.reservarLivroFuncionario(ab);
    }else {
            JOptionPane.showMessageDialog(null, "Selecione um Locatário e/ou um Livro!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    }

     private void confirmarReservaOutro(){

        if(verificaCampos()){
            reservaBean ab = new reservaBean();
            ab.setIdlocatario(cadastrolocatariobean.get(tbLocatarios.getSelectedRow()).getIdlocatario());
            ab.setIdlivro(livroBean.get(tbLivros.getSelectedRow()).getIdlivro());
            ab.setTipo_locatario("outro");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dataReserva = Date.valueOf(formato.format(dcDataReserva.getDate()));
            ab.setDatareserva(dataReserva);

            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            Date diaEmprestimo = Date.valueOf(formato2.format(dcDiaemprestimo.getDate()));
            ab.setDiaemprestimo(diaEmprestimo);

            Calendar  now = Calendar.getInstance();
            ab.setHorareserva(String.format("%1$tH:%1$tM:%1$tS", now));
            ab.setQuantidade(Integer.parseInt(String.valueOf(tfQuantidade.getText())));

            reservaControl ac = new reservaControl();
            preencherCampos();
            ac.reservarLivroOutro(ab);
    }else {
            JOptionPane.showMessageDialog(null, "Selecione um Locatário e/ou um Livro!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    }
private void TabelaLinhaSelecionadaLocatario(JTable tbLocatarios){
            if(tbLocatarios.getSelectedRow() != -1 ) {
                   confirmarReserva();
            }else{
               JOptionPane.showMessageDialog(null,"Selecione um Locatário");
            }
        }

        public void TabelaLinhaSelecionadaLivros(JTable tbLivros){
            if(tbLivros.getSelectedRow() != -1) {
                     confirmarReserva();
            }else{
                JOptionPane.showMessageDialog(null,"Selecione um Livro");
            }
        }

      public void confirmarReserva(){
        
             if (cbOpcao.getSelectedItem().equals("...")) {
                 JOptionPane.showMessageDialog(null, "Selecione um tipo de locatário primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);

               }
               else if(cbOpcao.getSelectedItem().equals("Aluno")) {

                   confirmarReservaAluno();
                   JOptionPane.showMessageDialog(null, "Locação realizada com sucesso");
                   listar_locatario_aluno();
                   listarLivro();

               } else if (cbOpcao.getSelectedItem().equals("Funcionário")) {

                   confirmarReservaFuncionario();
                   JOptionPane.showMessageDialog(null, "Locação realizada com sucesso");
                    listar_locatario_funcionario();
                    listarLivro();

               } else if (cbOpcao.getSelectedItem().equals("Outros")) {

                   confirmarReservaOutro();
                   JOptionPane.showMessageDialog(null, "Locação realizada com sucesso");
                   listar_locatario_outro();
                   listarLivro();
             }
       }
      //Mostrar Livros
    public void mostrarLivros(List<livroBean> livrosB) {
        while (tmLivros.getRowCount() > 0) {
            tmLivros.removeRow(0);
        }
        if (livrosB.size() == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum Livro encontrado!");
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
        livroBean = ce.ListarLivros("%" + tfPesquisarLivros.getText().trim() + "%");

        mostrarLivros(livroBean);

    }
    //Listar Por Autor
    public void listarLivroAutor() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivrosAutor("%" +  tfPesquisarLivros.getText().trim() + "%");

        mostrarLivros(livroBean);

    }

    //Listar Por Categoria
    public void listarLivroCategoria() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivrosCategoria("%" +  tfPesquisarLivros.getText().trim() + "%");

        mostrarLivros(livroBean);

    }
    //Listar Por Genero
    public void listarLivroGenero() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivrosGenero("%" +  tfPesquisarLivros.getText().trim() + "%");

        mostrarLivros(livroBean);

    }
    //Listar Por Editora
    public void listarLivroEditora() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivros("%" +  tfPesquisarLivros.getText().trim() + "%");

        mostrarLivros(livroBean);

    }
//Listar
    private void opcaoPesquisa() {

       if (cbOpcao2.getSelectedItem().equals("...")) {
           JOptionPane.showMessageDialog(null, "Selecione um tipo de livro primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (cbOpcao2.getSelectedItem().equals("Nome")) {
           listarLivro();
        } else if (cbOpcao2.getSelectedItem().equals("Autor")) {
            listarLivroAutor();
       } else if (cbOpcao2.getSelectedItem().equals("Categoria")) {
            listarLivroCategoria();
       } else if (cbOpcao2.getSelectedItem().equals("Gênero")) {
              listarLivroGenero();
       } else if (cbOpcao2.getSelectedItem().equals("Editora")) {
            listarLivroEditora();
       }
    }

    
     //Mostrar Locatários
     public void listar_locatario_aluno() {
        cadastrolocatarioControl ac = new cadastrolocatarioControl();

        cadastrolocatariobean = ac.listar_locatario_aluno("%" + tfPesquisarLocatario.getText().trim() + "%");
        mostrar_locatario_aluno(cadastrolocatariobean);

    }

    public void mostrar_locatario_aluno(List<cadastrolocatarioBean> usuarioB) {
        while (tmLocatarios.getRowCount() > 0) {
            tmLocatarios.removeRow(0);
        }
        if (usuarioB.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum Locatário encontrado!!!");
        } else {
            String[] campos = new String[]{null, null, null, null};

            for (int i = 0; i < usuarioB.size(); i++) {
                tmLocatarios.addRow(campos);
                tmLocatarios.setValueAt(usuarioB.get(i).getIdlocatario(), i, 0);
                tmLocatarios.setValueAt(usuarioB.get(i).getNome_locatario(), i, 1);
                tmLocatarios.setValueAt(usuarioB.get(i).getData_cadastro(), i, 2);
                tmLocatarios.setValueAt(usuarioB.get(i).getHora_cadastro(), i, 3);

            }
        }
    }

    public void listar_locatario_funcionario() {
        cadastrolocatarioControl ac = new cadastrolocatarioControl();

        cadastrolocatariobean = ac.listar_locatario_funcionario("%" + tfPesquisarLocatario.getText().trim() + "%");
        mostrar_locatario_funcionario(cadastrolocatariobean);

    }

    public void mostrar_locatario_funcionario(List<cadastrolocatarioBean> usuarioB) {
        while (tmLocatarios.getRowCount() > 0) {
            tmLocatarios.removeRow(0);
        }
        if (usuarioB.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum Locatário encontrado!!!");
        } else {
            String[] campos = new String[]{null, null, null, null};

            for (int i = 0; i < usuarioB.size(); i++) {
                tmLocatarios.addRow(campos);
                tmLocatarios.setValueAt(usuarioB.get(i).getIdlocatario(), i, 0);
                tmLocatarios.setValueAt(usuarioB.get(i).getNome_locatario(), i, 1);
                tmLocatarios.setValueAt(usuarioB.get(i).getData_cadastro(), i, 2);
                tmLocatarios.setValueAt(usuarioB.get(i).getHora_cadastro(), i, 3);

            }
            }
    }

    public void listar_locatario_outro() {
        cadastrolocatarioControl ac = new cadastrolocatarioControl();

        cadastrolocatariobean = ac.listar_locatario_outro("%" + tfPesquisarLocatario.getText().trim() + "%");
        mostrar_locatario_outro(cadastrolocatariobean);

    }

    public void mostrar_locatario_outro(List<cadastrolocatarioBean> usuarioB) {
        while (tmLocatarios.getRowCount() > 0) {
            tmLocatarios.removeRow(0);
        }
        if (usuarioB.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum Locatário encontrado!!!");
        } else {
            String[] campos = new String[]{null, null, null, null};

            for (int i = 0; i < usuarioB.size(); i++) {
                tmLocatarios.addRow(campos);
                tmLocatarios.setValueAt(usuarioB.get(i).getIdlocatario(), i, 0);
                tmLocatarios.setValueAt(usuarioB.get(i).getNome_locatario(), i, 1);
                tmLocatarios.setValueAt(usuarioB.get(i).getData_cadastro(), i, 2);
                tmLocatarios.setValueAt(usuarioB.get(i).getHora_cadastro(), i, 3);

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
    //Mostrar Reservas

   public void listarReservasAluno(){

            reservaControl ce = new  reservaControl ();
            reservaBean = ce.listarReservasAluno("%" + btPesquisarReservas.getText().trim()+"%");

            mostrarReservasAluno(reservaBean);
    }

     public void mostrarReservasAluno(List<reservaBean> reservasB){
        while(tmReservas.getRowCount()>0){
            tmReservas.removeRow(0);
        }
        if(reservasB.size()== 0){
            JOptionPane.showMessageDialog(this, "Nenhuma reserva encontrada!");
        }else{
            String [] campos = new String [] {null, null, null, null, null};
            for(int i=0; i<reservasB.size(); i++)
            {
                tmReservas.addRow(campos);
                tmReservas.setValueAt(reservasB.get(i).getNomeLivro(), i, 0);
                tmReservas.setValueAt(reservasB.get(i).getNomeLocatario(), i, 1);
                tmReservas.setValueAt(reservasB.get(i).getDatareserva(), i, 2);
                tmReservas.setValueAt(reservasB.get(i).getHorareserva(), i, 3);
                tmReservas.setValueAt(reservasB.get(i).getDiaemprestimo(), i, 4);
                
                
            
            }
        }
    }
public void listarReservasFuncionario(){

            reservaControl ce = new  reservaControl ();
            reservaBean = ce.listarReservasAluno("%" + btPesquisarReservas.getText().trim()+"%");

            mostrarReservasFuncionario(reservaBean);
    }

     public void mostrarReservasFuncionario(List<reservaBean> reservasFunB){
        while(tmReservas.getRowCount()>0){
            tmReservas.removeRow(0);
        }
        if(reservasFunB.size()== 0){
            JOptionPane.showMessageDialog(this, "Nenhuma reserva encontrada!");
        }else{
            String [] campos = new String [] {null, null};
            for(int i=0; i<reservasFunB.size(); i++)
            {
                tmReservas.addRow(campos);
                tmReservas.setValueAt(reservasFunB.get(i).getNomeLivro(), i, 0);
                tmReservas.setValueAt(reservasFunB.get(i).getNomeLocatario(), i, 1);
                tmReservas.setValueAt(reservasFunB.get(i).getDatareserva(), i, 2);
                tmReservas.setValueAt(reservasFunB.get(i).getDiaemprestimo(), i, 3);
            }
        }
    }

     public void listarReservasOutro(){

            reservaControl ce = new  reservaControl ();
            reservaBean = ce.listarReservasAluno("%" + btPesquisarReservas.getText().trim()+"%");

            mostrarReservasOutro(reservaBean);
    }

     public void mostrarReservasOutro(List<reservaBean> reservasOutB){
        while(tmReservas.getRowCount()>0){
            tmReservas.removeRow(0);
        }
        if(reservasOutB.size()== 0){
            JOptionPane.showMessageDialog(this, "Nenhuma reserva encontrada!");
        }else{
            String [] campos = new String [] {null, null, null, null};
            for(int i=0; i<reservasOutB.size(); i++)
            {
                tmReservas.addRow(campos);
                tmReservas.setValueAt(reservasOutB.get(i).getNomeLivro(), i, 0);
                tmReservas.setValueAt(reservasOutB.get(i).getNomeLocatario(), i, 1);
                tmReservas.setValueAt(reservasOutB.get(i).getDatareserva(), i, 2);
                tmReservas.setValueAt(reservasOutB.get(i).getDiaemprestimo(), i, 3);
            }
        }
    }

private void listarReservas() {

       if (cbReservas.getSelectedItem().equals("...")) {
           JOptionPane.showMessageDialog(null, "Selecione um tipo de locatário primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (cbReservas.getSelectedItem().equals("Aluno")) {
            listarReservasAluno();
        } else if (cbReservas.getSelectedItem().equals("Funcionário")) {
           listarReservasFuncionario();
       } else if (cbReservas.getSelectedItem().equals("Outros")) {
            listarReservasOutro();
       }
    }
       public void iniciaRelogio(){
    new Thread(){//instancia nova thread já implementando o método run()
    @Override
        public void run() {//sobrescreve o método run()

    while(0 == 0){//while para fazer o loop infinito
        GregorianCalendar gc = new GregorianCalendar();//novo gregorian calendar, onde temos a data atual
    int hora = gc.get(Calendar.HOUR_OF_DAY);//pega as horas
    int minuto = gc.get(Calendar.MINUTE);//pega os minutos
    int segundo = gc.get(Calendar.SECOND);//pega os segundos

    String horaString;//nova string horas
    String minString;//nova string minutos
    String segundoString;//nova string segundos
        if(hora < 10){ //se hora for menor que 10 precisa colocar um 0 à esquerda
    horaString = "0"+hora;

        }else{
    horaString = ""+hora;
}
        if(minuto < 10){//se minuto for menor que 10 precisa colocar um 0 à esquerda
    minString = "0"+minuto;
        }else{
    minString = ""+minuto;
}
        if(segundo < 10){//se segundo for menor que 10 precisa colocar um 0 à esquerda
    segundoString = "0"+segundo;
        }else{
    segundoString = ""+segundo;
}
        tfHorareserva.setText(horaString+":"+minString+":"+segundoString);//seta hora atual no label
    try{
        sleep(1000);//faz a thread entrar em estado de espera por 1000 milissegundos ou 1 segundo

}catch(Exception e){}
}
}
}.start();//inicia a thread.
}

       private void camposNaoEditaveis(){
         dcDataReserva.setEnabled(false);
         dcDiaemprestimo.setEnabled(false);
         tfQuantidade.setEditable(false);
         btFinalizar.setEnabled(false);
        
       }
        private void camposEditaveis(){
         dcDataReserva.setEnabled(true);
         dcDiaemprestimo.setEnabled(true);
         tfQuantidade.setEditable(true);
         btFinalizar.setEnabled(true);

       }

        private void preencherCampos(){
            if (dcDataReserva.getDate().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Data da reserva!!!");

        } else if (dcDiaemprestimo.getDate().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Reservado para o dia:");
        } else if (tfQuantidade.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Quantidade!!!");

        }
        }
private void btPesquisarLocatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarLocatarioActionPerformed
listarLocatarios();
}//GEN-LAST:event_btPesquisarLocatarioActionPerformed

private void btNovareservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovareservaActionPerformed
camposEditaveis();
}//GEN-LAST:event_btNovareservaActionPerformed

private void btPesquisarReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarReservasActionPerformed
listarReservas();
}//GEN-LAST:event_btPesquisarReservasActionPerformed

private void cbReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbReservasActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cbReservasActionPerformed

private void btPesquisarlivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarlivrosActionPerformed
opcaoPesquisa();
}//GEN-LAST:event_btPesquisarlivrosActionPerformed

private void cbOpcao2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOpcao2ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cbOpcao2ActionPerformed

private void btFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarActionPerformed
confirmarReserva();
camposNaoEditaveis();
      //  quantidade();
}//GEN-LAST:event_btFinalizarActionPerformed

private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
camposNaoEditaveis();
}//GEN-LAST:event_btCancelarActionPerformed

private void btMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMenuPrincipalActionPerformed
principal a = new principal();
a.main(null);
dispose();
}//GEN-LAST:event_btMenuPrincipalActionPerformed

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
            java.util.logging.Logger.getLogger(reservaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reservaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reservaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reservaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new reservaLivro().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Pendencias;
    private javax.swing.JPanel Ramiro;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btFinalizar;
    private javax.swing.JButton btMenuPrincipal;
    private javax.swing.JButton btNovareserva;
    private javax.swing.JButton btPesquisarLocatario;
    private javax.swing.JButton btPesquisarReservas;
    private javax.swing.JButton btPesquisarlivros;
    private javax.swing.JComboBox cbOpcao;
    private javax.swing.JComboBox cbOpcao2;
    private javax.swing.JComboBox cbReservas;
    private com.toedter.calendar.JDateChooser dcDataReserva;
    private com.toedter.calendar.JDateChooser dcDiaemprestimo;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
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
    private javax.swing.JLabel lbrelogio;
    private javax.swing.JLabel lbrelogio2;
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
    private javax.swing.JPanel pEscolhalivro;
    private javax.swing.JPanel pEscolhalocatario;
    private javax.swing.JPanel pProcurarreservas;
    private javax.swing.JTable tbLivros;
    private javax.swing.JTable tbLocatarios;
    private javax.swing.JTable tbReservas;
    private javax.swing.JTextField tfHorareserva;
    private javax.swing.JTextField tfPesquisarLivros;
    private javax.swing.JTextField tfPesquisarLivros1;
    private javax.swing.JTextField tfPesquisarLocatario;
    private javax.swing.JTextField tfQuantidade;
    // End of variables declaration//GEN-END:variables
}
