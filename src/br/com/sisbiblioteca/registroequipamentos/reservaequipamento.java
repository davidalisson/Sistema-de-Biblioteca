/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * reservaequipamento.java
 *
 * Created on 14/08/2012, 12:39:03
 */
package br.com.sisbiblioteca.registroequipamentos;

import br.com.sisbiblioteca.Sobre.Sobre;
import br.com.sisbiblioteca.cadastroEquipamento.cadastroequipamento;
import br.com.sisbiblioteca.cadastroEquipamento.equipamentoBean;
import br.com.sisbiblioteca.cadastroInstituicao.cadastroInstituicao;
import br.com.sisbiblioteca.cadastroLivro.cadastroLivro;
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
import br.com.sisbiblioteca.reservaLivro.reservaLivro;
import java.awt.Toolkit;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Murilo Alves
 */
public class reservaequipamento extends javax.swing.JFrame {

    /** Creates new form reservaequipamento */
    public reservaequipamento() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        iniciaRelogio();
        tfHoraReserva.setEnabled(false);

        listar_locatario_funcionario();
        listarEquipamento();
        listarReservas();
        java.util.Date d = new java.util.Date();
        dcDataReserva.setDate(d);
        dcDataReserva.setEnabled(false);

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

    public void iniciaRelogio() {
        new Thread() {//instancia nova thread já implementando o método run()

            @Override
            public void run() {//sobrescreve o método run()
                while (0 == 0) {//while para fazer o loop infinito
                    GregorianCalendar gc = new GregorianCalendar();//novo gregorian calendar, onde temos a data atual
                    int hora = gc.get(Calendar.HOUR_OF_DAY);//pega as horas
                    int minuto = gc.get(Calendar.MINUTE);//pega os minutos

                    String horaString;//nova string horas
                    String minString;//nova string minutos

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

                    tfHoraReserva.setText(horaString + ":" + minString);//seta hora atual no label

                    //empre();
                    /*if (data.after(d))   {
                    JOptionPane.showMessageDialog(null, "Ainda vai acontecer!");
                    } else if (data.before(d))   {
                    JOptionPane.showMessageDialog(null, "Ja passou!");
                    }else{
                    JOptionPane.showMessageDialog(null, "Hoje muda pra emprestimo!");
                    }*/

                    try {
                        sleep(1000);//faz a thread entrar em estado de espera por 1000 milissegundos ou 1 segundo
                    } catch (Exception e) {
                    }
                }
            }
        }.start();//inicia a thread.
    }
    DefaultTableModel tmLocatarios = new DefaultTableModel(null, new String[]{"Nome"});
    DefaultTableModel tmEquipamentos = new DefaultTableModel(null, new String[]{"Equipamento", "Quantidade"});
    DefaultTableModel tmReserva = new DefaultTableModel(null, new String[]{"Equipamento", "Código", "Locatário", "Data/Emprestimo", "Hora/Emprestimo"});
    
    List<reservaequipamentoBean> rbean;
    List<equipamentoBean> ebean;
    List<cadastrolocatarioBean> fbean;
    
    public void listarReservas() {
        reservaequipamentoControl rec = new reservaequipamentoControl();
        rbean = rec.listarReservas("%" + tfPesquisaR.getText().trim() + "%");
        mostrarReservas(rbean);


    }

    public void mostrarReservas(List<reservaequipamentoBean> reservab) {
        while (tmReserva.getRowCount() > 0) {
            tmReserva.removeRow(0);
        }
        if (reservab.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma reserva encontrada");
        } else {
            String[] campos = new String[]{null, null, null, null, null};
            for (int i = 0; i < reservab.size(); i++) {
                tmReserva.addRow(campos);
                //DefaultTableModel tmReserva = new DefaultTableModel(null, new String[]{"Equipamento", "Data Imprestimo", "Hora Imprestimo", "Data devolução", "Hora Devolução"});

                tmReserva.setValueAt(reservab.get(i).getNome_equip(), i, 0);
                tmReserva.setValueAt(reservab.get(i).getIdequipamento(), i, 1);
                tmReserva.setValueAt(reservab.get(i).getNome_loc(), i, 2);
                tmReserva.setValueAt(reservab.get(i).getDataEmprestimo(), i, 3);
                tmReserva.setValueAt(reservab.get(i).getDataDevolucao(), i, 4);
            }
        }
    }

    public void mostrarEquipamentos(List<equipamentoBean> equipamentosB) {
        while (tmEquipamentos.getRowCount() > 0) {
            tmEquipamentos.removeRow(0);
        }
        if (equipamentosB.size() == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum Equipamento encontrado!");
        } else {
            String[] campos = new String[]{null, null};
            for (int i = 0; i < equipamentosB.size(); i++) {
                tmEquipamentos.addRow(campos);

                tmEquipamentos.setValueAt(equipamentosB.get(i).getNomeequipamento(), i, 0);

                tmEquipamentos.setValueAt(equipamentosB.get(i).getQuantidade(), i, 1);

            }
        }
    }

    public void listarEquipamento() {

        reservaequipamentoControl ce = new reservaequipamentoControl();
        ebean = ce.ListarEquipamentosQ("%%");

        mostrarEquipamentos(ebean);

    }

    public void listar_locatario_funcionario() {
        cadastrolocatarioControl ac = new cadastrolocatarioControl();

        fbean = ac.listar_locatario_funcionario("%%");
        mostrar_locatario_funcionario(fbean);

    }

    public void mostrar_locatario_funcionario(List<cadastrolocatarioBean> usuarioB) {

        while (tmLocatarios.getRowCount() > 0) {
            tmLocatarios.removeRow(0);
        }
        if (usuarioB.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nada encontrado!!!");
        } else {
            String[] campos = new String[]{null};

            for (int i = 0; i < usuarioB.size(); i++) {
                tmLocatarios.addRow(campos);
                tmLocatarios.setValueAt(usuarioB.get(i).getNome_locatario(), i, 0);


            }
        }
    }
    int idfuncionario;

    public boolean VerificCampos() {
        if (tbLocatarios.getSelectedRow() != -1) {
            return true;
        }
        return false;
    }
    String dataEmprestimo;
    String horaEmprestimo;
    String horaDevolucao;

    private void Vhora1() {
        if (cbHoraEmprestimo.getSelectedItem().equals("Selecione uma opção")) {

            JOptionPane.showMessageDialog(null, "Nenhuma hora de emprestimo selecionado!!!", "Opps!!", JOptionPane.ERROR_MESSAGE);
        } else if (cbHoraEmprestimo.getSelectedItem().equals("-------------------")) {

            JOptionPane.showMessageDialog(null, "Nenhuma hora de emprestimo selecionado!!!", "Opps!!", JOptionPane.ERROR_MESSAGE);
        } else if (cbHoraEmprestimo.getSelectedItem().equals("1ª Aula Manhã")) {
            tfOutroEmprestimo.setText("");
            horaEmprestimo = "07:00";

            tfOutroEmprestimo.setEnabled(false);
        } else if (cbHoraEmprestimo.getSelectedItem().equals("2ª Aula Manhã")) {
            tfOutroEmprestimo.setText("");
            horaEmprestimo = "07:50";

            tfOutroEmprestimo.setEnabled(false);
        } else if (cbHoraEmprestimo.getSelectedItem().equals("3ª Aula Manhã")) {
            tfOutroEmprestimo.setText("");
            horaEmprestimo = "08:40";

            tfOutroEmprestimo.setEnabled(false);
        } else if (cbHoraEmprestimo.getSelectedItem().equals("4ª Aula Manhã")) {
            tfOutroEmprestimo.setText("");
            horaEmprestimo = "09:50";

            tfOutroEmprestimo.setEnabled(false);
        } else if (cbHoraEmprestimo.getSelectedItem().equals("5ª Aula Manhã")) {
            tfOutroEmprestimo.setText("");
            horaEmprestimo = "10:40";

            tfOutroEmprestimo.setEnabled(false);
        } else if (cbHoraEmprestimo.getSelectedItem().equals("1ª Aula Tarde")) {
            tfOutroEmprestimo.setText("");
            horaEmprestimo = "13:00";

            tfOutroEmprestimo.setEnabled(false);
        } else if (cbHoraEmprestimo.getSelectedItem().equals("2ª Aula Tarde")) {
            tfOutroEmprestimo.setText("");
            horaEmprestimo = "13:50";

            tfOutroEmprestimo.setEnabled(false);
        } else if (cbHoraEmprestimo.getSelectedItem().equals("3ª Aula Tarde")) {
            tfOutroEmprestimo.setText("");
            horaEmprestimo = "15:00";

            tfOutroEmprestimo.setEnabled(false);
        } else if (cbHoraEmprestimo.getSelectedItem().equals("4ª Aula Tarde")) {
            tfOutroEmprestimo.setText("");
            horaEmprestimo = "15:50";

            tfOutroEmprestimo.setEnabled(false);
        } else if (cbHoraEmprestimo.getSelectedItem().equals("5ª Aula Tarde")) {
            tfOutroEmprestimo.setText("");
            horaEmprestimo = "16:40";

            tfOutroEmprestimo.setEnabled(false);
        } else if (cbHoraEmprestimo.getSelectedItem().equals("Outro Horário")) {
            tfOutroEmprestimo.setEnabled(true);

        }
    }

    private void Vhora2() {
        if (cbHoraDevolucao.getSelectedItem().equals("Selecione uma opção")) {

            JOptionPane.showMessageDialog(null, "Nenhuma hora de emprestimo selecionado!!!", "Opps!!", JOptionPane.ERROR_MESSAGE);
        } else if (cbHoraDevolucao.getSelectedItem().equals("-------------------")) {

            JOptionPane.showMessageDialog(null, "Nenhuma hora de emprestimo selecionado!!!", "Opps!!", JOptionPane.ERROR_MESSAGE);
        } else if (cbHoraDevolucao.getSelectedItem().equals("1ª Aula Manhã")) {
            tfOutroDevolucao.setText("");
            horaDevolucao = "07:49";

            tfOutroDevolucao.setEnabled(false);
        } else if (cbHoraDevolucao.getSelectedItem().equals("2ª Aula Manhã")) {
            tfOutroDevolucao.setText("");
            horaDevolucao = "08:39";

            tfOutroDevolucao.setEnabled(false);
        } else if (cbHoraDevolucao.getSelectedItem().equals("3ª Aula Manhã")) {
            tfOutroDevolucao.setText("");
            horaDevolucao = "09:29";

            tfOutroDevolucao.setEnabled(false);
        } else if (cbHoraDevolucao.getSelectedItem().equals("4ª Aula Manhã")) {
            tfOutroDevolucao.setText("");
            horaDevolucao = "10:39";

            tfOutroDevolucao.setEnabled(false);
        } else if (cbHoraDevolucao.getSelectedItem().equals("5ª Aula Manhã")) {
            tfOutroDevolucao.setText("");
            horaDevolucao = "11:29";

            tfOutroDevolucao.setEnabled(false);
        } else if (cbHoraDevolucao.getSelectedItem().equals("1ª Aula Tarde")) {
            tfOutroDevolucao.setText("");
            horaDevolucao = "13:49";

            tfOutroDevolucao.setEnabled(false);
        } else if (cbHoraDevolucao.getSelectedItem().equals("2ª Aula Tarde")) {
            tfOutroDevolucao.setText("");
            horaDevolucao = "14:39";

            tfOutroDevolucao.setEnabled(false);
        } else if (cbHoraDevolucao.getSelectedItem().equals("3ª Aula Tarde")) {
            tfOutroDevolucao.setText("");
            horaDevolucao = "15:49";

            tfOutroDevolucao.setEnabled(false);
        } else if (cbHoraDevolucao.getSelectedItem().equals("4ª Aula Tarde")) {
            tfOutroDevolucao.setText("");
            horaDevolucao = "16:39";

            tfOutroDevolucao.setEnabled(false);
        } else if (cbHoraDevolucao.getSelectedItem().equals("5ª Aula Tarde")) {
            tfOutroDevolucao.setText("");
            horaDevolucao = "17:29";

            tfOutroDevolucao.setEnabled(false);
        } else if (cbHoraDevolucao.getSelectedItem().equals("Outro Horário")) {
            tfOutroDevolucao.setEnabled(true);

        }
    }

    private void ComparaHora() {


        SimpleDateFormat formato = new SimpleDateFormat("kk:mm");

        try {
            Vhora1();
            Vhora2();
            if (cbHoraDevolucao.getSelectedItem().equals("Outro Horário")) {
                horaDevolucao = tfOutroDevolucao.getText();

            }
            if (cbHoraEmprestimo.getSelectedItem().equals("Outro Horário")) {
                horaEmprestimo = tfOutroEmprestimo.getText();

            }
            java.util.Date d1 = formato.parse(horaEmprestimo);
            java.util.Date d2 = formato.parse(horaDevolucao);




            if (d1.getTime() >= d2.getTime()) {

                JOptionPane.showMessageDialog(null, "Impossível!!! Campos de horas inválidos!!", "Verifique as Horas", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println("Deu problema em algo!!!! :(");
        }

    }

    private void ReservarEquipamento() {

        if (VerificCampos()) {

            reservaequipamentoBean re = new reservaequipamentoBean();

            re.setIdlocatario(fbean.get(tbLocatarios.getSelectedRow()).getIdlocatario());
            re.setIdequipamento(Integer.parseInt(String.valueOf(tfCodigo.getText()).trim()));

            equipamentoBean eq = new equipamentoBean();

            eq.setIdequipamento(Integer.parseInt(String.valueOf(tfCodigo.getText()).trim()));
            eq.setStatus("reservado");


            Calendar now = Calendar.getInstance();
            re.setHoraReserva(String.format("%1$tH:%1$tM", now));



            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            Date datae = Date.valueOf(formato2.format(dcDataReserva.getDate()));
            re.setDataReserva(datae);

            re.setHoraEmprestimo(horaEmprestimo);

            SimpleDateFormat formato3 = new SimpleDateFormat("yyyy-MM-dd");
            Date data2 = Date.valueOf(formato3.format(dcDataEmprestimo.getDate()));
            re.setDataEmprestimo(data2);


            SimpleDateFormat formato4 = new SimpleDateFormat("yyyy-MM-dd");
            Date data3 = Date.valueOf(formato4.format(dcDataDevolucao.getDate()));
            re.setDataDevolucao(data3);


            re.setHoraDevolucao(horaDevolucao);

            re.setQuantidade(1);
            
            re.setEmprestado("Nao");


            if (data2.after(datae) || data3.after(datae)) {
                if (data3.after(data2)) {
                    reservaequipamentoControl rec = new reservaequipamentoControl();
                    rec.reservarEquipamentos(re);
                    rec.equipamentoReservado(eq);

                    listarEquipamento();
                    listar_locatario_funcionario();
                } else if (data3.before(data2)) {
                    JOptionPane.showMessageDialog(null, "Alguma data incorreta!!!", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } else if (data2.before(datae) || data3.before(datae)) {
                JOptionPane.showMessageDialog(null, "Alguma data incorreta!!!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                reservaequipamentoControl rec = new reservaequipamentoControl();
                rec.reservarEquipamentos(re);
                rec.equipamentoReservado(eq);

                listarEquipamento();
                listar_locatario_funcionario();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um campo primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSpinner2 = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLocatarios = new javax.swing.JTable();
        btPesquisarL = new javax.swing.JButton();
        btNovoL = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        pReservas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbRegistros = new javax.swing.JTable();
        tfPesquisaR = new javax.swing.JTextField();
        btPesquisarR = new javax.swing.JButton();
        tfOutroDevolucao = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        tfOutroEmprestimo = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        cbHoraDevolucao = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cbHoraEmprestimo = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfHoraReserva = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btIrPnmu = new javax.swing.JButton();
        btReservar = new javax.swing.JButton();
        rbtNome = new javax.swing.JRadioButton();
        rbtEquipamento = new javax.swing.JRadioButton();
        dcDataEmprestimo = new com.toedter.calendar.JDateChooser();
        dcDataReserva = new com.toedter.calendar.JDateChooser();
        dcDataDevolucao = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        tfLocatario = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbEquipamentos = new javax.swing.JTable();
        btPesquisarE = new javax.swing.JButton();
        btNovoE = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        tfPesquisarE = new javax.swing.JTextField();
        Ramiro = new javax.swing.JPanel();
        lbDate = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbHora = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        msgNome = new javax.swing.JLabel();
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
        setTitle("Sistema de Biblioteca - Reserva de Equipamento");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icone.png")));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione um Locatário:"));

        tbLocatarios.setModel(tmLocatarios);
        jScrollPane1.setViewportView(tbLocatarios);

        btPesquisarL.setText("Pesquisar");
        btPesquisarL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarLActionPerformed(evt);
            }
        });

        btNovoL.setText("Novo");
        btNovoL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btNovoL, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btPesquisarL, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btNovoL, btPesquisarL});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btPesquisarL, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btNovoL))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btNovoL, btPesquisarL, jTextField1});

        pReservas.setBorder(javax.swing.BorderFactory.createTitledBorder("Reservas:"));

        tbRegistros.setModel(tmReserva);
        jScrollPane3.setViewportView(tbRegistros);

        tfPesquisaR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPesquisaRActionPerformed(evt);
            }
        });

        btPesquisarR.setText("Pesquisar");
        btPesquisarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarRActionPerformed(evt);
            }
        });

        try {
            tfOutroDevolucao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfOutroDevolucao.setEnabled(false);

        jLabel3.setText("Data da Reserva:");

        try {
            tfOutroEmprestimo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfOutroEmprestimo.setEnabled(false);

        jLabel8.setText("Data Devolução:");

        cbHoraDevolucao.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Selecione uma opção", "1ª Aula Manhã", "2ª Aula Manhã","3ª Aula Manhã", "4ª Aula Manhã", "5ª Aula Manhã", "-------------------","1ª Aula Tarde", "2ª Aula Tarde","3ª Aula Tarde", "4ª Aula Tarde", "5ª Aula Tarde", "Outro Horário"}));
        cbHoraDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHoraDevolucaoActionPerformed(evt);
            }
        });

        jLabel9.setText("Hora:");

        cbHoraEmprestimo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Selecione uma opção", "1ª Aula Manhã", "2ª Aula Manhã","3ª Aula Manhã", "4ª Aula Manhã", "5ª Aula Manhã", "-------------------","1ª Aula Tarde", "2ª Aula Tarde","3ª Aula Tarde", "4ª Aula Tarde", "5ª Aula Tarde", "Outro Horário"}));
        cbHoraEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbHoraEmprestimoMouseClicked(evt);
            }
        });
        cbHoraEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHoraEmprestimoActionPerformed(evt);
            }
        });

        jLabel7.setText("Hora:");

        jLabel6.setText("Data do Emprestimo:");

        jLabel5.setText("Hora:");

        btIrPnmu.setText("Ir para Menu");
        btIrPnmu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIrPnmuActionPerformed(evt);
            }
        });

        btReservar.setText("Reservar");
        btReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReservarActionPerformed(evt);
            }
        });

        rbtNome.setText("Nome Locatário");
        rbtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtNomeActionPerformed(evt);
            }
        });

        rbtEquipamento.setText("Equipamento");
        rbtEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtEquipamentoActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome do Locatário:");

        tfLocatario.setEnabled(false);

        javax.swing.GroupLayout pReservasLayout = new javax.swing.GroupLayout(pReservas);
        pReservas.setLayout(pReservasLayout);
        pReservasLayout.setHorizontalGroup(
            pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pReservasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pReservasLayout.createSequentialGroup()
                        .addComponent(rbtNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtEquipamento))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pReservasLayout.createSequentialGroup()
                        .addComponent(tfPesquisaR)
                        .addGap(18, 18, 18)
                        .addComponent(btPesquisarR, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3)
                    .addGroup(pReservasLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(33, 33, 33)
                        .addComponent(dcDataReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(tfHoraReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pReservasLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(25, 25, 25)
                        .addComponent(tfLocatario))
                    .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pReservasLayout.createSequentialGroup()
                            .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8))
                            .addGap(18, 18, 18)
                            .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(dcDataDevolucao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dcDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pReservasLayout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbHoraDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tfOutroDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pReservasLayout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbHoraEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tfOutroEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pReservasLayout.createSequentialGroup()
                            .addComponent(btReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42)
                            .addComponent(btIrPnmu, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(152, 152, 152))))
                .addContainerGap())
        );

        pReservasLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btIrPnmu, btReservar});

        pReservasLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dcDataDevolucao, dcDataEmprestimo, dcDataReserva});

        pReservasLayout.setVerticalGroup(
            pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pReservasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtNome)
                    .addComponent(rbtEquipamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPesquisaR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisarR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(tfHoraReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcDataReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfLocatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pReservasLayout.createSequentialGroup()
                        .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(dcDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(dcDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pReservasLayout.createSequentialGroup()
                        .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbHoraEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfOutroEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbHoraDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfOutroDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(37, 37, 37)
                .addGroup(pReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btIrPnmu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btReservar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pReservasLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btIrPnmu, btReservar, cbHoraDevolucao, cbHoraEmprestimo, dcDataDevolucao, dcDataEmprestimo, dcDataReserva, tfHoraReserva, tfLocatario, tfOutroDevolucao, tfOutroEmprestimo});

        pReservasLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btPesquisarR, tfPesquisaR});

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione um Equipamento:"));

        tbEquipamentos.setModel(tmEquipamentos);
        jScrollPane2.setViewportView(tbEquipamentos);

        btPesquisarE.setText("Pesquisar");
        btPesquisarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarEActionPerformed(evt);
            }
        });

        btNovoE.setText("Novo");
        btNovoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoEActionPerformed(evt);
            }
        });

        jLabel2.setText("Código:");

        tfCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCodigoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(tfPesquisarE, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btPesquisarE, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btNovoE, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btNovoE, btPesquisarE});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfPesquisarE)
                    .addComponent(btPesquisarE, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNovoE)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btNovoE, btPesquisarE, tfCodigo, tfPesquisarE});

        Ramiro.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Ramiro.setLayout(null);
        Ramiro.add(lbDate);
        lbDate.setBounds(10, 4, 100, 20);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(null);
        jPanel4.add(lbHora);
        lbHora.setBounds(30, 4, 90, 20);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setLayout(null);

        jLabel11.setText("Usuário:");
        jPanel5.add(jLabel11);
        jLabel11.setBounds(10, 0, 60, 30);
        jPanel5.add(msgNome);
        msgNome.setBounds(70, 0, 550, 30);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pReservas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Ramiro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pReservas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ramiro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 109, 9, 109);
        getContentPane().add(jPanel3, gridBagConstraints);

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
        setBounds((screenSize.width-1229)/2, (screenSize.height-648)/2, 1229, 648);
    }// </editor-fold>//GEN-END:initComponents

private void btPesquisarLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarLActionPerformed
    listar_locatario_funcionario();
}//GEN-LAST:event_btPesquisarLActionPerformed

private void btNovoLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoLActionPerformed
    cadastrolocatario cl = new cadastrolocatario();
    cl.setVisible(true);
    dispose();
}//GEN-LAST:event_btNovoLActionPerformed

private void btPesquisarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarEActionPerformed
    listarEquipamento();
}//GEN-LAST:event_btPesquisarEActionPerformed

private void btReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReservarActionPerformed
    Vhora2();
    ComparaHora();
    ReservarEquipamento();
}//GEN-LAST:event_btReservarActionPerformed

private void rbtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtNomeActionPerformed
    rbtEquipamento.setSelected(false);
}//GEN-LAST:event_rbtNomeActionPerformed

private void rbtEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtEquipamentoActionPerformed
    rbtNome.setSelected(false);
}//GEN-LAST:event_rbtEquipamentoActionPerformed

private void btIrPnmuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIrPnmuActionPerformed
    principal p = new principal();
    p.setVisible(true);
    dispose();
}//GEN-LAST:event_btIrPnmuActionPerformed

private void cbHoraEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbHoraEmprestimoMouseClicked
}//GEN-LAST:event_cbHoraEmprestimoMouseClicked

private void cbHoraEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHoraEmprestimoActionPerformed
    Vhora1();
}//GEN-LAST:event_cbHoraEmprestimoActionPerformed

private void cbHoraDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHoraDevolucaoActionPerformed
    Vhora2();
    ComparaHora();
}//GEN-LAST:event_cbHoraDevolucaoActionPerformed

private void btPesquisarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarRActionPerformed
    listarReservas();
}//GEN-LAST:event_btPesquisarRActionPerformed

private void tfCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodigoActionPerformed

}//GEN-LAST:event_tfCodigoActionPerformed

private void tfPesquisaRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPesquisaRActionPerformed
    listarReservas();
}//GEN-LAST:event_tfPesquisaRActionPerformed

    private void btNovoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoEActionPerformed
        cadastroequipamento ce = new cadastroequipamento();
        ce.setVisible(true);
        dispose();
    }//GEN-LAST:event_btNovoEActionPerformed

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
            java.util.logging.Logger.getLogger(reservaequipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reservaequipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reservaequipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reservaequipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new reservaequipamento().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Pendencias;
    private javax.swing.JPanel Ramiro;
    private javax.swing.JButton btIrPnmu;
    private javax.swing.JButton btNovoE;
    private javax.swing.JButton btNovoL;
    private javax.swing.JButton btPesquisarE;
    private javax.swing.JButton btPesquisarL;
    private javax.swing.JButton btPesquisarR;
    private javax.swing.JButton btReservar;
    private javax.swing.JComboBox cbHoraDevolucao;
    private javax.swing.JComboBox cbHoraEmprestimo;
    private com.toedter.calendar.JDateChooser dcDataDevolucao;
    private com.toedter.calendar.JDateChooser dcDataEmprestimo;
    private com.toedter.calendar.JDateChooser dcDataReserva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
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
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTextField jTextField1;
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
    private javax.swing.JPanel pReservas;
    private javax.swing.JRadioButton rbtEquipamento;
    private javax.swing.JRadioButton rbtNome;
    private javax.swing.JTable tbEquipamentos;
    private javax.swing.JTable tbLocatarios;
    private javax.swing.JTable tbRegistros;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfHoraReserva;
    private javax.swing.JTextField tfLocatario;
    private javax.swing.JFormattedTextField tfOutroDevolucao;
    private javax.swing.JFormattedTextField tfOutroEmprestimo;
    private javax.swing.JTextField tfPesquisaR;
    private javax.swing.JTextField tfPesquisarE;
    // End of variables declaration//GEN-END:variables
}
