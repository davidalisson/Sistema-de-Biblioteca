/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EmprestimoEquipamento.java
 * se data de emprstimo =  a data de hoja ee hora de agora >= a hora de emprestimo e que pode fazer
 *
 * Created on 21/08/2012, 14:09:48
 */
package br.com.sisbiblioteca.emprestimoEquipamento;

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
import br.com.sisbiblioteca.emprestimoLivro.registroLivro;
import br.com.sisbiblioteca.login.login;
import br.com.sisbiblioteca.pendenciasEquip.pendenciasEquip;
import br.com.sisbiblioteca.principal.principal;
import br.com.sisbiblioteca.registroequipamentos.reservaequipamento;
import br.com.sisbiblioteca.registroequipamentos.reservaequipamentoBean;
import br.com.sisbiblioteca.registroequipamentos.reservaequipamentoControl;
import br.com.sisbiblioteca.reservaLivro.reservaLivro;
import java.awt.Toolkit;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Murilo Alves
 */
public class EmprestimoEquipamento extends javax.swing.JFrame {

    /**
     * Creates new form EmprestimoEquipamento
     */
    public EmprestimoEquipamento() {
        initComponents();


        iniciaRelogio();
        tfhora.setEnabled(false);
        listarEquipamento();
        listarEmprestimos();
        listar_locatario_funcionario();
        java.util.Date d = new java.util.Date();
        dcDataEmp.setDate(d);
        dcDataEmp.setEnabled(false);
        setExtendedState(MAXIMIZED_BOTH);


        msg();
    }
    
    public static String nome;

    public void msg() {
        msgNome.setText("" + nome);

        java.util.Date h = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        lbDate.setText("" + formato.format(h));

        iniciaRelogioM();
    }

    
    
    public void iniciaRelogioM() {
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
                    tfhora.setText(horaString + ":" + minString + ":" + segundoString);//seta hora atual no label
                    try {
                        sleep(1000);//faz a thread entrar em estado de espera por 1000 milissegundos ou 1 segundo
                    } catch (Exception e) {
                    }
                }
            }
        }.start();//inicia a thread.
    }
    int vColIndex = 0;
    DefaultTableModel tmLocatarios = new DefaultTableModel(null, new String[]{"Nome"});
    List<cadastrolocatarioBean> fbean;
    DefaultTableModel tmEquipamentos = new DefaultTableModel(null, new String[]{"Equipamento", "Quantidade"});
    List<equipamentoBean> ebean;
    DefaultTableModel tmReserva = new DefaultTableModel(null, new String[]{"Equipamento", "Código", "Locatário", "Data/Empréstimo", "Data/Devolução"});
    DefaultTableModel tmReservaE = new DefaultTableModel(null, new String[]{"Equipamento", "Código", "Locatário", "Data/Empréstimo", "Data/Devolução"});
    DefaultTableModel tmReservaL = new DefaultTableModel(null, new String[]{"Locatário", "Código", "Locatário", "Data/Empréstimo", "Data/Devolução"});
    List<reservaequipamentoBean> rbean;
    List<cadastrolocatarioBean> obean;
    DefaultTableModel tmEmprestimo = new DefaultTableModel(null, new String[]{"Equipamento", "Código", "Locatário", "Data/Empréstimo", "Data/Devolução"});
    List<emprestimoBean> empBean;
    //ListSelectionModel lsmReserva;

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
        ebean = ce.ListarEquipamentosQ("%" + tfPesquisarE.getText().trim() + "%");

        mostrarEquipamentos(ebean);

    }

    public void listar_locatario_funcionario() {
        cadastrolocatarioControl ac = new cadastrolocatarioControl();

        fbean = ac.listar_locatario_funcionario("%" + tfPesquisarL.getText().trim() + "%");
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

    String tipoListar;
     private void opcaoLocatario() {
        if (cbOpcao.getSelectedItem().equals("...")) {
            tipoListar = "...";
            JOptionPane.showMessageDialog(null, "Selecione um tipo de locatário primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);

        } else if (cbOpcao.getSelectedItem().equals("Equipamento")) {
            
            tipoListar = "Equipamento";

        } else if (cbOpcao.getSelectedItem().equals("Locatário")) {
            
            tipoListar = "Locatário";

        
    }
     }
        /*private void listarReservasLE() {
            opcaoLocatario();
        if (cbOpcao.equals("Equipamento")) {
            listarReservasE();
        } else if (cbOpcao.equals("Locatário")) {
            listarReservasL();
        
    }
        }
    */
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

    private void cadastraEmp() {

        if (VerificCampos()) {

            emprestimoBean cp = new emprestimoBean();


            cp.setCod_loc_equip(fbean.get(tbLocatarios.getSelectedRow()).getIdlocatario());
            cp.setCod_equip_equip(Integer.parseInt(String.valueOf(tfCodigo.getText().trim())));
            cp.setCod_usu_equip(4);
            equipamentoBean u = new equipamentoBean();
            u.setIdequipamento(Integer.parseInt(String.valueOf(tfCodigo.getText().trim())));
            u.setStatus("ocupado");

            cp.setHora_emp_equip(horaEmprestimo);

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date datae = Date.valueOf(formato.format(dcDataEmp.getDate()));
            cp.setData_emp_equip(datae);


            Date datae1 = Date.valueOf(formato.format(dcDataPrev.getDate()));
            cp.setData_prev_equip(datae1);


            cp.setHora_dev_equip(horaDevolucao);

            cp.setStatus("utilizando");
            emprestimoControl ep = new emprestimoControl();
            ep.cadastraEmp(cp);


            reservaequipamentoControl r = new reservaequipamentoControl();
            r.equipamentoReservado(u);

            listarEquipamento();
            listar_locatario_funcionario();
            listarEmprestimos();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um campo primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
     public void listarEmprestimos() {
        emprestimoControl ec = new emprestimoControl();
        empBean = ec.listarEmprestimos("%" + tfPesquisarEmp.getText().trim() + "%");
        
            mostrarEmprestimos(empBean);

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

    public static String status;
    public static Date dataemprestimoR;
    public static Date datadevolucaoR;
    public static String horaemprestimoR;
    public static String horadevolucaoR;

    public void listarStatus() {
        try {
            emprestimoControl ce = new emprestimoControl();
            ebean = ce.ListarEquipamentos("" + tfCodigo.getText().trim() + "");

            reservaequipamentoControl rc = new reservaequipamentoControl();
            rbean = rc.Reservas("" + tfCodigo.getText().trim() + "");

            SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoHora = new SimpleDateFormat("kk:mm");

            Date datae = Date.valueOf(formatoData.format(dcDataEmp.getDate()));
            java.util.Date Hemprestimo = formatoHora.parse(horaEmprestimo);
            java.util.Date Devolucao = formatoHora.parse(horaDevolucao);
            java.util.Date HemprestimoR = formatoHora.parse(horaemprestimoR);
            java.util.Date HemprestimoD = formatoHora.parse(horadevolucaoR);

            Calendar now = Calendar.getInstance();
            String h = String.format("%1$tH:%1$tM", now);
            java.util.Date horAtual = formatoHora.parse(h);

            if (horAtual.getTime() >= Hemprestimo.getTime()) {

                if ("disponivel".equals(status)) {
                    cadastraEmp();
                } else if ("ocupado".equals(status)) {

                    int resposta = JOptionPane.showConfirmDialog(this, "Equipamento Ocupado!!\nDeseja reserva-lo?", "Opss!!", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        reservaequipamento req = new reservaequipamento();
                        req.setVisible(true);
                        dispose();
                    }
                } else if ("reservado".equals(status)) {

                    if (datae.equals(dataemprestimoR)) {
                        if (Hemprestimo.getTime() >= HemprestimoR.getTime() && Hemprestimo.getTime() <= HemprestimoD.getTime()) {
                            JOptionPane.showMessageDialog(null, "Hora para pegar equipamento está reservada", "Erro", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (Devolucao.getTime() >= HemprestimoR.getTime() && Devolucao.getTime() <= HemprestimoD.getTime()) {
                                JOptionPane.showMessageDialog(null, "Hora para devolver equipamento está reservada", "Erro", JOptionPane.ERROR_MESSAGE);
                            } else {
                                if (Hemprestimo.getTime() < HemprestimoR.getTime() && Devolucao.getTime() > HemprestimoD.getTime()) {
                                    JOptionPane.showMessageDialog(null, "Equipamento Reservado!!", "Erro", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    cadastraEmp();
                                    JOptionPane.showMessageDialog(null, "Emprestimo realizado com sucesso!!");
                                }
                            }

                        }
                    }
                }
            } else {
                int resposta = JOptionPane.showConfirmDialog(this, "Impossível fazer emprestimo!!\nDeseja reserva-lo?", "Opss!!", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    reservaequipamento req = new reservaequipamento();
                    req.setVisible(true);
                    dispose();
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(EmprestimoEquipamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void EmprestarReserva() {
        reservaequipamentoBean cp = new reservaequipamentoBean();

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date datae = Date.valueOf(formato.format(dcDataEmp.getDate()));

        cp.setIdlocatario(rbean.get(tbRegistros.getSelectedRow()).getIdlocatario());
        cp.setIdequipamento(rbean.get(tbRegistros.getSelectedRow()).getIdequipamento());
        cp.setCod_usu_equip(2);

        equipamentoBean u = new equipamentoBean();

        u.setIdequipamento(rbean.get(tbRegistros.getSelectedRow()).getIdequipamento());
        u.setStatus("ocupado");

        cp.setHoraEmprestimo(rbean.get(tbRegistros.getSelectedRow()).getHoraEmprestimo());
        cp.setDataEmprestimo(rbean.get(tbRegistros.getSelectedRow()).getDataEmprestimo());
        cp.setDataDevolucao(rbean.get(tbRegistros.getSelectedRow()).getDataDevolucao());
        cp.setHoraDevolucao(rbean.get(tbRegistros.getSelectedRow()).getHoraDevolucao());
        cp.setStatus("utilizando");

        reservaequipamentoControl r = new reservaequipamentoControl();
        r.cadastraEmp(cp);
        r.equipamentoReservado(u);

        cp.setIdreserva(rbean.get(tbRegistros.getSelectedRow()).getIdreserva());
        cp.setEmprestado("Sim");


        r.emprestadoSim(cp);

        listarEquipamento();
        listar_locatario_funcionario();
        listarEmprestimos();

    }

    public boolean VerificReservas() {
        if (tbRegistros.getSelectedRow() != -1) {
            return true;
        }
        return false;
    }

    private void emprestra() {
        if (VerificReservas()) {
            EmprestarReserva();
        } else {
            listarStatus();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btNovoL = new javax.swing.JButton();
        btPesquisarL = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbLocatarios = new javax.swing.JTable();
        tfPesquisarL = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbEquipamentos1 = new javax.swing.JTable();
        btPesquisarE = new javax.swing.JButton();
        btNovoE = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        tfPesquisarE = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        tfhora = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dcDataEmp = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        dcDataPrev = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btCancelr = new javax.swing.JButton();
        btPronto = new javax.swing.JButton();
        btVoltar = new javax.swing.JButton();
        cbHoraDevolucao = new javax.swing.JComboBox();
        tfOutroDevolucao = new javax.swing.JFormattedTextField();
        cbHoraEmprestimo = new javax.swing.JComboBox();
        tfOutroEmprestimo = new javax.swing.JFormattedTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        msgNome = new javax.swing.JLabel();
        Ramiro = new javax.swing.JPanel();
        lbDate = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lbHora = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        tfPesquisaR = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbRegistros = new javax.swing.JTable();
        btPesquisarR = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        cbOpcao = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPesquisaEmp = new javax.swing.JTable();
        tfPesquisarEmp = new javax.swing.JTextField();
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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Biblioteca - Empréstimos de Equipamentos");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icone.png")));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione um Locatário:"));

        btNovoL.setText("Novo");
        btNovoL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoLActionPerformed(evt);
            }
        });

        btPesquisarL.setText("Pesquisar");
        btPesquisarL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarLActionPerformed(evt);
            }
        });

        tbLocatarios.setModel(tmLocatarios);
        jScrollPane5.setViewportView(tbLocatarios);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(tfPesquisarL, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btPesquisarL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btNovoL, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btPesquisarL, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPesquisarL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNovoL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btNovoL, btPesquisarL});

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione um Equipamento:"));

        tbEquipamentos1.setModel(tmEquipamentos);
        jScrollPane4.setViewportView(tbEquipamentos1);

        btPesquisarE.setText("Pesquisar");
        btPesquisarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarEActionPerformed(evt);
            }
        });

        btNovoE.setText("Novo");

        jLabel1.setText("Código:");

        tfCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCodigoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(tfPesquisarE, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btPesquisarE, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btNovoE, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCodigo)))
                .addGap(120, 120, 120))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPesquisarE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisarE, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNovoE, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btNovoE, btPesquisarE});

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, 0, 586, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Novo empréstimo"));

        tfhora.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        tfhora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfhoraActionPerformed(evt);
            }
        });

        jLabel3.setText("Data do Empréstimo:");

        dcDataEmp.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N

        jLabel5.setText("Hora do Empréstimo:");

        jLabel2.setText("Data de entrega:");

        btCancelr.setText("Cancelar");

        btPronto.setText("Emprestar");
        btPronto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProntoActionPerformed(evt);
            }
        });

        btVoltar.setText("Ir para Menu");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        cbHoraDevolucao.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Selecione uma opção", "1ª Aula Manhã", "2ª Aula Manhã","3ª Aula Manhã", "4ª Aula Manhã", "5ª Aula Manhã", "-------------------","1ª Aula Tarde", "2ª Aula Tarde","3ª Aula Tarde", "4ª Aula Tarde", "5ª Aula Tarde", "Outro Horário"}));
        cbHoraDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHoraDevolucaoActionPerformed(evt);
            }
        });

        try {
            tfOutroDevolucao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfOutroDevolucao.setEnabled(false);

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

        try {
            tfOutroEmprestimo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfOutroEmprestimo.setEnabled(false);
        tfOutroEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfOutroEmprestimoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfhora, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcDataEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcDataPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbHoraDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfOutroDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbHoraEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfOutroEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btPronto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btCancelr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfOutroEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbHoraEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfOutroDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbHoraDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(tfhora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(dcDataEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcDataPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btPronto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btCancelr, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel9.setLayout(null);

        jLabel18.setText("Usuário:");
        jPanel9.add(jLabel18);
        jLabel18.setBounds(10, 0, 60, 30);
        jPanel9.add(msgNome);
        msgNome.setBounds(80, 0, 610, 30);

        Ramiro.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Ramiro.setLayout(null);
        Ramiro.add(lbDate);
        lbDate.setBounds(10, 4, 100, 20);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel10.setLayout(null);
        jPanel10.add(lbHora);
        lbHora.setBounds(30, 4, 90, 20);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Reservas:"));

        jButton4.setText("Excluir");

        jButton1.setText("Detalhes");

        tbRegistros.setModel(tmReserva);
        jScrollPane6.setViewportView(tbRegistros);

        btPesquisarR.setText("Pesquisar");
        btPesquisarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarRActionPerformed(evt);
            }
        });

        jButton3.setText("Nova Reserva");

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisar por:"));

        cbOpcao.setMaximumRowCount(4);
        cbOpcao.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"...", "Equipamento", "Locatário"}));
        cbOpcao.setBorder(null);
        cbOpcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOpcaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbOpcao, 0, 149, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(cbOpcao, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(tfPesquisaR, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btPesquisarR, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton3, jButton4});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPesquisaR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btPesquisarR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btPesquisarR, jButton1, jButton3, jButton4});

        jTabbedPane1.addTab("Reservas", jPanel7);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Empréstimos:"));

        jButton2.setText("Pesquisar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tbPesquisaEmp.setModel(tmEmprestimo);
        jScrollPane1.setViewportView(tbPesquisaEmp);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(tfPesquisarEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPesquisarEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Empréstimos", jPanel2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTabbedPane1))
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Ramiro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ramiro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 780, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 91, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 1513;
        gridBagConstraints.ipady = 93;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
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
        setBounds((screenSize.width-1398)/2, (screenSize.height-729)/2, 1398, 729);
    }// </editor-fold>//GEN-END:initComponents

private void btPesquisarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarEActionPerformed
    listarEquipamento();
}//GEN-LAST:event_btPesquisarEActionPerformed

private void btPesquisarLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarLActionPerformed
    listar_locatario_funcionario();
}//GEN-LAST:event_btPesquisarLActionPerformed

private void btNovoLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoLActionPerformed
    cadastrolocatario cl = new cadastrolocatario();
    cl.setVisible(true);
    dispose();
}//GEN-LAST:event_btNovoLActionPerformed

private void btPesquisarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarRActionPerformed
listarReservas();
}//GEN-LAST:event_btPesquisarRActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    listarEmprestimos();
}//GEN-LAST:event_jButton2ActionPerformed

    private void cbHoraEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHoraEmprestimoActionPerformed
        Vhora1();
    }//GEN-LAST:event_cbHoraEmprestimoActionPerformed

    private void cbHoraEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbHoraEmprestimoMouseClicked
    }//GEN-LAST:event_cbHoraEmprestimoMouseClicked

    private void cbHoraDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHoraDevolucaoActionPerformed
        Vhora2();
        ComparaHora();
    }//GEN-LAST:event_cbHoraDevolucaoActionPerformed

    private void btProntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProntoActionPerformed
        emprestra();
    }//GEN-LAST:event_btProntoActionPerformed

    private void tfhoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfhoraActionPerformed
    }//GEN-LAST:event_tfhoraActionPerformed

private void tfOutroEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfOutroEmprestimoActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tfOutroEmprestimoActionPerformed

private void tfCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodigoActionPerformed
}//GEN-LAST:event_tfCodigoActionPerformed

private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
    principal p = new principal();
    p.setVisible(true);
    dispose();
}//GEN-LAST:event_btVoltarActionPerformed

    private void cbOpcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOpcaoActionPerformed

    }//GEN-LAST:event_cbOpcaoActionPerformed

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
            java.util.logging.Logger.getLogger(EmprestimoEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmprestimoEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmprestimoEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmprestimoEquipamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmprestimoEquipamento().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Pendencias;
    private javax.swing.JPanel Ramiro;
    private javax.swing.JButton btCancelr;
    private javax.swing.JButton btNovoE;
    private javax.swing.JButton btNovoL;
    private javax.swing.JButton btPesquisarE;
    private javax.swing.JButton btPesquisarL;
    private javax.swing.JButton btPesquisarR;
    private javax.swing.JButton btPronto;
    private javax.swing.JButton btVoltar;
    private javax.swing.JComboBox cbHoraDevolucao;
    private javax.swing.JComboBox cbHoraEmprestimo;
    private javax.swing.JComboBox cbOpcao;
    private com.toedter.calendar.JDateChooser dcDataEmp;
    private com.toedter.calendar.JDateChooser dcDataPrev;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
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
    private javax.swing.JTable tbEquipamentos1;
    private javax.swing.JTable tbLocatarios;
    private javax.swing.JTable tbPesquisaEmp;
    private javax.swing.JTable tbRegistros;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JFormattedTextField tfOutroDevolucao;
    private javax.swing.JFormattedTextField tfOutroEmprestimo;
    private javax.swing.JTextField tfPesquisaR;
    private javax.swing.JTextField tfPesquisarE;
    private javax.swing.JTextField tfPesquisarEmp;
    private javax.swing.JTextField tfPesquisarL;
    private javax.swing.JTextField tfhora;
    // End of variables declaration//GEN-END:variables
}
