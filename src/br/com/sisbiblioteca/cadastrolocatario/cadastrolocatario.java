package br.com.sisbiblioteca.cadastrolocatario;

import br.com.sisbiblioteca.Sobre.Sobre;
import br.com.sisbiblioteca.cadastroEquipamento.cadastroequipamento;
import br.com.sisbiblioteca.cadastroInstituicao.cadastroInstituicao;
import br.com.sisbiblioteca.cadastroLivro.cadastroLivro;
import br.com.sisbiblioteca.cadastrousuarios.cadastrousuario;
import br.com.sisbiblioteca.devolucaoEquip.DevolucaoEquip;
import br.com.sisbiblioteca.devolucaolivros.devolucaolivro;
import br.com.sisbiblioteca.emprestimoEquipamento.EmprestimoEquipamento;
import br.com.sisbiblioteca.emprestimoLivro.registroLivro;
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
 * @author Murilo Alves
 */
public class cadastrolocatario extends javax.swing.JFrame {

    /** Creates new form cadastrolocatario */
    public cadastrolocatario() {
        initComponents();
        
        setExtendedState(MAXIMIZED_BOTH);
        camposNaoEditaveis();
        camposNaoEditaveisOutro();
        camposNaoEditaveisAluno();
        camposNaoEditaveisFuncionario();
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
    
    String escolha;
    List<cadastrolocatarioBean> cadastrolocatariobean;
    DefaultTableModel tmLocatarios = new DefaultTableModel(null, new String[]{"Nome", "Data do Cadastro", "Hora do Cadastro", "Tipo locatário"});
    ListSelectionModel lsmLocatarios;

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
            JOptionPane.showMessageDialog(null, "Nada encontrado!!!");
        } else {
            String[] campos = new String[]{null, null, null, null};

            for (int i = 0; i < usuarioB.size(); i++) {
                
                tmLocatarios.addRow(campos);
                tmLocatarios.setValueAt(usuarioB.get(i).getNome_locatario(), i, 0);
                tmLocatarios.setValueAt(usuarioB.get(i).getData_cadastro(), i, 1);
                tmLocatarios.setValueAt(usuarioB.get(i).getHora_cadastro(), i, 2);
                tmLocatarios.setValueAt(usuarioB.get(i).getTipo_locatario(), i, 3);


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
            JOptionPane.showMessageDialog(null, "Nada encontrado!!!");
        } else {
            String[] campos = new String[]{null, null, null, null};

            for (int i = 0; i < usuarioB.size(); i++) {
                tmLocatarios.addRow(campos);
                tmLocatarios.setValueAt(usuarioB.get(i).getNome_locatario(), i, 0);
                tmLocatarios.setValueAt(usuarioB.get(i).getData_cadastro(), i, 1);
                tmLocatarios.setValueAt(usuarioB.get(i).getHora_cadastro(), i, 2);
                tmLocatarios.setValueAt(usuarioB.get(i).getTipo_locatario(), i, 3);

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
            JOptionPane.showMessageDialog(null, "Nada encontrado!!!");
        } else {
            String[] campos = new String[]{null, null, null, null};

            for (int i = 0; i < usuarioB.size(); i++) {
                tmLocatarios.addRow(campos);
                tmLocatarios.setValueAt(usuarioB.get(i).getNome_locatario(), i, 0);
                tmLocatarios.setValueAt(usuarioB.get(i).getData_cadastro(), i, 1);
                tmLocatarios.setValueAt(usuarioB.get(i).getHora_cadastro(), i, 2);
                tmLocatarios.setValueAt(usuarioB.get(i).getTipo_locatario(), i, 3);

            }
        }
    }

    public boolean VerificCampos() {
        if (tbLocatarios.getSelectedRow() != -1) {
            return true;
        }
        return false;
    }

    private void alterar_locatario_aluno() {

        if (VerificCampos()) {

            cadastrolocatarioBean ub = new cadastrolocatarioBean();
            ub.setIdlocatario(cadastrolocatariobean.get(tbLocatarios.getSelectedRow()).getIdlocatario());
            ub.setNome_locatario(tfNome.getText().trim());

//            Date data = Date.valueOf(tfNascimento.getText());
//            ub.setData_nascimento(data);

            ub.setSexo(escolheSexo());

            ub.setCelular(tfCelular.getText().trim());
            ub.setSexo(tfCelular.getText().trim());
            ub.setCpf(tfCpf.getText().trim());
            ub.setRg(tfRg.getText().trim());
            ub.setEmail(tfEmail.getText().trim());
            ub.setEndereco(tfEndereco.getText().trim());
            ub.setBairro(tfBairro.getText().trim());


            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d = new java.util.Date();
            Date hoje = Date.valueOf(formato.format(d));
            ub.setData_cadastro(hoje);

            Calendar now = Calendar.getInstance();
            ub.setHora_cadastro(String.format("%1$tH:%1$tM:%1$tS", now));

            ub.setResp_cadastro(nome);
            ub.setSerie(tfSerie.getText().trim());
            ub.setTurma(tfTurma.getText().trim());
            ub.setnMatricula(Integer.parseInt(String.valueOf(tfNMatricula.getText().trim())));
            ub.setEscola(tfEscola.getText().trim());

            if (tfNome.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Nome !!!");
            } else if (tfSerie.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Série !!!");
            } else {

                cadastrolocatarioControl ac = new cadastrolocatarioControl();
                ac.alterar_locatario_aluno(ub);

                JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso !!!");

                btSalvar.setEnabled(false);
                limparCampos();
                limparCamposAluno();
                listar_locatario_aluno();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um campo primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterar_locatario_funcionario() {

        if (VerificCampos()) {

            cadastrolocatarioBean ub = new cadastrolocatarioBean();
            ub.setIdlocatario(cadastrolocatariobean.get(tbLocatarios.getSelectedRow()).getIdlocatario());
            ub.setNome_locatario(tfNome.getText().trim());

//            Date data = Date.valueOf(tfNascimento.getText());
//            ub.setData_nascimento(data);

            ub.setSexo(escolheSexo());

            ub.setCelular(tfCelular.getText().trim());
            ub.setCpf(tfCpf.getText().trim());
            ub.setRg(tfRg.getText().trim());
            ub.setEmail(tfEmail.getText().trim());
            ub.setEndereco(tfEndereco.getText().trim());
            ub.setBairro(tfBairro.getText().trim());
            ub.setCargo(tfCargo.getText().trim());

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d = new java.util.Date();
            Date hoje = Date.valueOf(formato.format(d));
            ub.setData_cadastro(hoje);

            Calendar now = Calendar.getInstance();
            ub.setHora_cadastro(String.format("%1$tH:%1$tM:%1$tS", now));

            ub.setResp_cadastro(nome);

            if (tfNome.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Nome !!!");
            } else if (tfCargo.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Cargo !!!");
            } else {

                cadastrolocatarioControl ac = new cadastrolocatarioControl();
                ac.alterar_locatario_funcionario(ub);

                JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso !!!");

                btSalvar.setEnabled(false);
                limparCampos();
                limparCamposFuncionario();
                listar_locatario_funcionario();

            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um campo primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterar_locatario_outro() {

        if (VerificCampos()) {

            cadastrolocatarioBean ub = new cadastrolocatarioBean();
            ub.setIdlocatario(cadastrolocatariobean.get(tbLocatarios.getSelectedRow()).getIdlocatario());
            ub.setNome_locatario(tfNome.getText().trim());

//            Date data = Date.valueOf(tfNascimento.getText());
//            ub.setData_nascimento(data);

            ub.setSexo(escolheSexo());

            ub.setCelular(tfCelular.getText().trim());
            ub.setCpf(tfCpf.getText().trim());
            ub.setRg(tfRg.getText().trim());
            ub.setEmail(tfEmail.getText().trim());
            ub.setEndereco(tfEndereco.getText().trim());
            ub.setBairro(tfBairro.getText().trim());
            ub.setCidade(tfCidade.getText().trim());
            ub.setCep(tfCep.getText().trim());
            ub.setUf(tfUf.getText().trim());

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d = new java.util.Date();
            Date hoje = Date.valueOf(formato.format(d));
            ub.setData_cadastro(hoje);

            Calendar now = Calendar.getInstance();
            ub.setHora_cadastro(String.format("%1$tH:%1$tM:%1$tS", now));

            ub.setResp_cadastro(nome);


            if (tfNome.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Nome !!!");
            } else if (tfEndereco.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Endereço!!!");
            } else if (tfBairro.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Bairro!!!");
            } else if (tfCidade.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Cidade!!!");
            } else if (tfEmail.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo E-Mail!!!");
            } else {

                cadastrolocatarioControl ac = new cadastrolocatarioControl();
                ac.alterar_locatario_outro(ub);

                JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso !!!");

                btSalvar.setEnabled(false);
                limparCampos();
                limparCamposOutro();
                listar_locatario_outro();

            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um campo primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir_locatario_aluno() {
        if (tbLocatarios.getSelectedRow() != -1) {
            int resposta = JOptionPane.showConfirmDialog(this, "Excluir Selecionado?", "Confimação", JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                cadastrolocatarioControl c = new cadastrolocatarioControl();
                c.excluir_locatario_aluno(cadastrolocatariobean.get(tbLocatarios.getSelectedRow()).getIdlocatario());
                listar_locatario_aluno();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Usuário!!");
        }
    }

    private void excluir_locatario_funcionario() {
        if (tbLocatarios.getSelectedRow() != -1) {
            int resposta = JOptionPane.showConfirmDialog(this, "Excluir Selecionado?", "Confimação", JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                cadastrolocatarioControl c = new cadastrolocatarioControl();
                c.excluir_locatario_funcionario(cadastrolocatariobean.get(tbLocatarios.getSelectedRow()).getIdlocatario());
                listar_locatario_funcionario();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Usuário!!");
        }
    }

    private void excluir_locatario_outro() {
        if (tbLocatarios.getSelectedRow() != -1) {
            int resposta = JOptionPane.showConfirmDialog(this, "Excluir Selecionado?", "Confimação", JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                cadastrolocatarioControl c = new cadastrolocatarioControl();
                c.excluir_locatario_outro(cadastrolocatariobean.get(tbLocatarios.getSelectedRow()).getIdlocatario());
                listar_locatario_outro();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Usuário!!");
        }
    }

    public void tb_LinhaSelecionada_Aluno(JTable tb) {


        if (tb.getSelectedRow() != -1) {
            tfNome.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getNome_locatario());

//            tfNascimento.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getData_nascimento().toString());
            if (cadastrolocatariobean.get(tb.getSelectedRow()).getSexo().equals("Masculino")) {
                rbtM.setSelected(true);
                rbtF.setSelected(false);
            } else {
                rbtF.setSelected(true);
                rbtM.setSelected(false);
            }
            tfCelular.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getCelular().toString());
            tfCpf.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getCpf().toString());
            tfRg.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getRg().toString());
            tfEmail.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getEmail());
            tfEndereco.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getEndereco());
            tfBairro.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getBairro());
            tfSerie.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getSerie());
            tfTurma.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getTurma());
            tfNMatricula.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getnMatricula().toString());
        } else {
            limparCampos();
            limparCamposAluno();
        }
    }

    public void tb_LinhaSelecionada_Funcionario(JTable tb) {


        if (tb.getSelectedRow() != -1) {

            tfNome.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getNome_locatario());
//            tfNascimento.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getData_nascimento().toString());
            if (cadastrolocatariobean.get(tb.getSelectedRow()).getSexo().equals("Masculino")) {
                rbtM.setSelected(true);
                rbtF.setSelected(false);
            } else {
                rbtF.setSelected(true);
                rbtM.setSelected(false);
            }
            tfCelular.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getCelular());
            tfCpf.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getCpf());
            tfRg.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getRg());
            tfEmail.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getEmail());
            tfEndereco.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getEndereco());
            tfBairro.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getBairro());
            tfCargo.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getCargo());

        } else {
            limparCampos();
            limparCamposFuncionario();
        }
    }

    public void tb_LinhaSelecionada_outro(JTable tb) {


        if (tb.getSelectedRow() != -1) {
            tfNome.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getNome_locatario());

//            tfNascimento.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getData_nascimento().toString());
            if (cadastrolocatariobean.get(tb.getSelectedRow()).getSexo().equals("Masculino")) {
                rbtM.setSelected(true);
                rbtF.setSelected(false);
            } else {
                rbtF.setSelected(true);
                rbtM.setSelected(false);
            }
            tfCelular.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getCelular().toString());
            tfCpf.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getCpf().toString());
            tfRg.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getRg().toString());
            tfEmail.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getEmail());
            tfEndereco.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getEndereco());
            tfBairro.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getBairro());
            tfCidade.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getCidade());
            tfCep.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getCep());
            tfUf.setText(cadastrolocatariobean.get(tb.getSelectedRow()).getUf());

        } else {
            limparCampos();
            limparCamposOutro();
        }
    }

    public void tb_LinhaSelecionada(JTable tb) {

        opcaoLocatario();

        if (tipoLocatario.equals("Aluno")) {

            tb_LinhaSelecionada_Aluno(tbLocatarios);
        } else if (tipoLocatario.equals("Funcionário")) {

            tb_LinhaSelecionada_Funcionario(tbLocatarios);
        } else if (tipoLocatario.equals("Outros")) {

            tb_LinhaSelecionada_outro(tbLocatarios);
        }

    }

    private void limparCampos() {
        tfNome.setText("");
//        tfNascimento.setText("");
        tfCelular.setText("");
        tfCpf.setText("");
        tfRg.setText("");
        tfEmail.setText("");
        tfEndereco.setText("");
        tfBairro.setText("");


        camposNaoEditaveis();

        btSalvar.setEnabled(false);
    }

    private void camposEditaveis() {
        tfNome.setEditable(true);
        tfNascimento.setEnabled(true);
        tfCelular.setEditable(true);
        tfCpf.setEditable(true);
        tfRg.setEditable(true);
        tfEmail.setEditable(true);
        tfEndereco.setEditable(true);
        tfBairro.setEditable(true);
    }

    private void limparCamposOutro() {
        tfCidade.setText("");
        tfCep.setText("");
        tfUf.setText("");
    }

    private void limparCamposAluno() {
        tfSerie.setText("");
        tfTurma.setText("");
        tfNMatricula.setText("");
        tfEscola.setText("");
    }

    private void limparCamposFuncionario() {
        tfCargo.setText("");
    }

    private void camposNaoEditaveis() {

        tfNome.setEditable(false);
        tfTurma.setEditable(false);
        tfSerie.setEditable(false);
        tfEscola.setEditable(false);
        tfNMatricula.setEditable(false);
        tfCargo.setEditable(false);
        tfCidade.setEditable(false);
        tfCep.setEditable(false);
        tfUf.setEditable(false);
        tfEmail.setEditable(false);
        tfNascimento.setEnabled(false);
        tfCpf.setEditable(false);
        tfRg.setEditable(false);
        tfCelular.setEditable(false);
        tfBairro.setEditable(false);
        tfEscola.setEditable(false);
        tfSerie.setEditable(false);
        tfTurma.setEditable(false);
        tfEndereco.setEditable(false);
        tfBairro.setEditable(false);
        tfCelular.setEditable(false);

    }

    private void camposNaoEditaveisOutro() {
        tfCargo.setEditable(false);
    }

    private void camposNaoEditaveisFuncionario() {
        tfCargo.setEditable(false);
    }

    private void camposNaoEditaveisAluno() {
        tfSerie.setEditable(false);
        tfTurma.setEditable(false);
        tfNMatricula.setEditable(false);
        tfEscola.setEditable(false);
    }

    private void camposEditaveisOutro() {
        tfCidade.setEditable(true);
        tfCep.setEditable(true);
        tfUf.setEditable(true);
    }

    private void camposEditaveisFuncionario() {
        tfCargo.setEditable(true);
    }

    private void camposEditaveisAluno() {
        tfSerie.setEditable(true);
        tfTurma.setEditable(true);
        tfNMatricula.setEditable(true);
        tfEscola.setEditable(true);
    }

    private void cadastro_locatario_outro() {

        cadastrolocatarioBean ub = new cadastrolocatarioBean();
        ub.setNome_locatario(tfNome.getText().trim());

//        Date data = Date.valueOf(tfNascimento.getText());
//        ub.setData_nascimento(data);

        ub.setSexo(escolheSexo());

        ub.setCelular(tfCelular.getText().trim());
        ub.setCpf(tfCpf.getText().trim());
        ub.setRg(tfRg.getText().trim());
        ub.setEmail(tfEmail.getText().trim());
        ub.setEndereco(tfEndereco.getText().trim());
        ub.setBairro(tfBairro.getText().trim());
        ub.setCidade(tfCidade.getText().trim());
        ub.setCep(tfCep.getText().trim());
        ub.setUf(tfUf.getText().trim());
        ub.setTipo_locatario("outro");

        SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
        Date dataPrevista = Date.valueOf(formato2.format(tfNascimento.getDate()));
        ub.setData_nascimento(dataPrevista);

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = new java.util.Date();
        Date hoje = Date.valueOf(formato.format(d));
        ub.setData_cadastro(hoje);

        Calendar now = Calendar.getInstance();
        ub.setHora_cadastro(String.format("%1$tH:%1$tM:%1$tS", now));

        ub.setResp_cadastro(nome);


        if (tfNome.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Nome !!!");
        } else if (tfEndereco.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Endereço!!!");
        } else if (tfBairro.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Bairro!!!");
        } else if (tfCidade.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Cidade!!!");
        } else if (tfEmail.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo E-Mail!!!");
        } else {

            cadastrolocatarioControl ac = new cadastrolocatarioControl();
            ac.cadastro_locatario_outro(ub);

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso !!!");

            btSalvar.setEnabled(false);
            limparCampos();
            limparCamposOutro();

        }
    }

    private void cadastro_locatario_funcionario() {

        cadastrolocatarioBean ub = new cadastrolocatarioBean();
        ub.setNome_locatario(tfNome.getText().trim());

        //Date data = Date.valueOf(tfNascimento.getText());
        // ub.setData_nascimento(data);
        SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
        Date dataPrevista = Date.valueOf(formato2.format(tfNascimento.getDate()));
        ub.setData_nascimento(dataPrevista);

        ub.setSexo(escolheSexo());

        ub.setCelular(tfCelular.getText().trim());
        ub.setCpf(tfCpf.getText().trim());
        ub.setRg(tfRg.getText().trim());
        ub.setEmail(tfEmail.getText().trim());
        ub.setEndereco(tfEndereco.getText().trim());
        ub.setBairro(tfBairro.getText().trim());
        ub.setCargo(tfCargo.getText().trim());
        // ub.setResp_cadastro(tfResponsavel.getText().trim());
        ub.setTipo_locatario("funcionario");

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = new java.util.Date();
        Date hoje = Date.valueOf(formato.format(d));
        ub.setData_cadastro(hoje);

        Calendar now = Calendar.getInstance();
        ub.setHora_cadastro(String.format("%1$tH:%1$tM:%1$tS", now));

        ub.setResp_cadastro(nome);

        if (tfNome.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Nome !!!");
        } else if (tfCargo.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Cargo !!!");
        } else {

            cadastrolocatarioControl ac = new cadastrolocatarioControl();
            ac.cadastro_locatario_funcionario(ub);

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso !!!");

            btSalvar.setEnabled(false);
            limparCampos();
            limparCamposFuncionario();

        }
    }

    private void cadastro_locatario_aluno() {

        cadastrolocatarioBean ub = new cadastrolocatarioBean();
        ub.setNome_locatario(tfNome.getText().trim());

        //  Date data = Date.valueOf(tfNascimento.getText());
        //ub.setData_nascimento(data);

        ub.setSexo(escolheSexo());

        ub.setCelular(tfCelular.getText().trim());
        ub.setCpf(tfCpf.getText().trim());
        ub.setRg(tfRg.getText().trim());
        ub.setEmail(tfEmail.getText().trim());
        ub.setEndereco(tfEndereco.getText().trim());
        ub.setBairro(tfBairro.getText().trim());
        ub.setTipo_locatario("aluno");

        SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
        Date dataPrevista = Date.valueOf(formato2.format(tfNascimento.getDate()));
        ub.setData_nascimento(dataPrevista);

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = new java.util.Date();
        Date hoje = Date.valueOf(formato.format(d));
        ub.setData_cadastro(hoje);

        Calendar now = Calendar.getInstance();
        ub.setHora_cadastro(String.format("%1$tH:%1$tM:%1$tS", now));

        ub.setResp_cadastro(nome);
        ub.setSerie(tfSerie.getText().trim());
        ub.setTurma(tfTurma.getText().trim());
        ub.setnMatricula(Integer.parseInt(String.valueOf(tfNMatricula.getText().trim())));
        ub.setEscola(tfEscola.getText().trim());

        if (tfNome.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Nome !!!");
        } else if (tfSerie.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Série !!!");
        } else {

            cadastrolocatarioControl ac = new cadastrolocatarioControl();
            ac.cadastro_locatario_aluno(ub);

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso !!!");

            btSalvar.setEnabled(false);
            limparCampos();
            limparCamposAluno();
        }
    }

    public String escolheSexo() {

        if (rbtM.isSelected()) {

            return "Masculino";
        } else {

            return "Feminino";
        }
    }
    String tipoLocatario;

    private void opcaoLocatario() {
        if (cbOpcao.getSelectedItem().equals("")) {
            tipoLocatario = "";
            JOptionPane.showMessageDialog(null, "Selecione um tipo de locatário primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);

        } else if (cbOpcao.getSelectedItem().equals("Aluno")) {
            camposEditaveis();
            camposEditaveisAluno();

            limparCamposFuncionario();
            limparCamposOutro();
            tipoLocatario = "Aluno";

        } else if (cbOpcao.getSelectedItem().equals("Funcionário")) {
            camposEditaveis();
            camposEditaveisFuncionario();

            limparCamposAluno();
            limparCamposOutro();
            tipoLocatario = "Funcionário";

        } else if (cbOpcao.getSelectedItem().equals("Outros")) {
            camposEditaveis();
            camposEditaveisOutro();

            limparCamposAluno();
            limparCamposFuncionario();
            tipoLocatario = "Outros";
        }
    }

    private void listarLocatarios() {
        opcaoLocatario();
        if (tipoLocatario.equals("Aluno")) {
            listar_locatario_aluno();
        } else if (tipoLocatario.equals("Funcionário")) {
            listar_locatario_funcionario();
        } else if (tipoLocatario.equals("Outros")) {
            listar_locatario_outro();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        pcadastro = new javax.swing.JPanel();
        cbOpcao = new javax.swing.JComboBox();
        tfPesquisar = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLocatarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        tfCelular = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        tfBairro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfEndereco = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rbtM = new javax.swing.JRadioButton();
        rbtF = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        tfCpf = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tfRg = new javax.swing.JTextField();
        lbrelogio = new javax.swing.JLabel();
        tfNascimento = new com.toedter.calendar.JDateChooser();
        btNovo = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btLimparcampos = new javax.swing.JButton();
        btLivros = new javax.swing.JButton();
        btEquipamentos = new javax.swing.JButton();
        brFuncionarios = new javax.swing.JButton();
        btIrPMenu = new javax.swing.JButton();
        tpFuncionario = new javax.swing.JTabbedPane();
        pfuncionario = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        tfCargo = new javax.swing.JTextField();
        paluno = new javax.swing.JPanel();
        tfTurma = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfSerie = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfEscola = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfNMatricula = new javax.swing.JTextField();
        pOutro = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        tfCidade = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tfCep = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tfUf = new javax.swing.JTextField();
        Ramiro = new javax.swing.JPanel();
        lbDate = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
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
        setTitle("Sistema de Bilblioteca - Locatário");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icone.png")));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pcadastro.setBorder(javax.swing.BorderFactory.createTitledBorder("Escolha um Locatário:"));
        pcadastro.setLayout(null);

        cbOpcao.setMaximumRowCount(4);
        cbOpcao.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"", "Aluno", "Funcionário", "Outros"}));
        cbOpcao.setBorder(null);
        cbOpcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOpcaoActionPerformed(evt);
            }
        });
        pcadastro.add(cbOpcao);
        cbOpcao.setBounds(20, 34, 110, 30);

        tfPesquisar.setToolTipText("Pesquisar por nome do usuário");
        tfPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPesquisarActionPerformed(evt);
            }
        });
        pcadastro.add(tfPesquisar);
        tfPesquisar.setBounds(140, 34, 250, 30);

        btPesquisar.setText("Pesquisar");
        btPesquisar.setToolTipText("Clique no botão (Pesquisar) para procurar usuário");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        pcadastro.add(btPesquisar);
        btPesquisar.setBounds(400, 34, 110, 30);

        tbLocatarios.setModel(tmLocatarios);
        tbLocatarios.setToolTipText("Tabela de usuários do Sistema de Biblioteca");
        tbLocatarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsmLocatarios = tbLocatarios.getSelectionModel();
        lsmLocatarios.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                if(! e.getValueIsAdjusting()){
                    tb_LinhaSelecionada(tbLocatarios);
                }
            }
        });
        jScrollPane1.setViewportView(tbLocatarios);

        pcadastro.add(jScrollPane1);
        jScrollPane1.setBounds(20, 74, 490, 109);

        jLabel1.setText("Nome do Locatário:");
        pcadastro.add(jLabel1);
        jLabel1.setBounds(16, 189, 120, 14);

        tfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeActionPerformed(evt);
            }
        });
        pcadastro.add(tfNome);
        tfNome.setBounds(20, 210, 360, 30);

        try {
            tfCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        pcadastro.add(tfCelular);
        tfCelular.setBounds(226, 264, 124, 30);

        jLabel4.setText("Celular:");
        pcadastro.add(jLabel4);
        jLabel4.setBounds(220, 244, 70, 14);
        pcadastro.add(tfBairro);
        tfBairro.setBounds(20, 490, 134, 30);

        jLabel3.setText("Bairro:");
        pcadastro.add(jLabel3);
        jLabel3.setBounds(20, 470, 50, 14);

        tfEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEnderecoActionPerformed(evt);
            }
        });
        pcadastro.add(tfEndereco);
        tfEndereco.setBounds(20, 430, 490, 30);

        jLabel2.setText("Endereço:");
        pcadastro.add(jLabel2);
        jLabel2.setBounds(20, 410, 70, 14);

        jLabel11.setText("E-mail:");
        pcadastro.add(jLabel11);
        jLabel11.setBounds(20, 360, 60, 14);
        pcadastro.add(tfEmail);
        tfEmail.setBounds(20, 380, 490, 30);

        jLabel8.setText("Data de Nascimento:");
        pcadastro.add(jLabel8);
        jLabel8.setBounds(390, 190, 120, 14);

        jLabel12.setText("Sexo:");
        pcadastro.add(jLabel12);
        jLabel12.setBounds(20, 244, 50, 14);

        rbtM.setText("Masculino");
        rbtM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtMActionPerformed(evt);
            }
        });
        pcadastro.add(rbtM);
        rbtM.setBounds(20, 264, 80, 23);

        rbtF.setText("Feminino");
        rbtF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtFActionPerformed(evt);
            }
        });
        pcadastro.add(rbtF);
        rbtF.setBounds(110, 268, 110, 23);

        jLabel13.setText("CPF:");
        pcadastro.add(jLabel13);
        jLabel13.setBounds(20, 301, 50, 14);
        pcadastro.add(tfCpf);
        tfCpf.setBounds(20, 321, 160, 30);

        jLabel14.setText("RG:");
        pcadastro.add(jLabel14);
        jLabel14.setBounds(200, 301, 60, 14);
        pcadastro.add(tfRg);
        tfRg.setBounds(200, 321, 150, 30);
        pcadastro.add(lbrelogio);
        lbrelogio.setBounds(560, 465, 200, 30);
        pcadastro.add(tfNascimento);
        tfNascimento.setBounds(390, 210, 120, 30);

        btNovo.setText("Novo");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });
        pcadastro.add(btNovo);
        btNovo.setBounds(920, 40, 140, 40);

        btAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Edit-Male-User.png"))); // NOI18N
        btAlterar.setText("Alterar");
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });
        pcadastro.add(btAlterar);
        btAlterar.setBounds(920, 80, 140, 40);

        btSalvar.setText("Salvar");
        btSalvar.setEnabled(false);
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        pcadastro.add(btSalvar);
        btSalvar.setBounds(920, 120, 140, 40);

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Full Trash.png"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        pcadastro.add(btExcluir);
        btExcluir.setBounds(920, 160, 140, 40);

        btLimparcampos.setText("Limpar Campos");
        btLimparcampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparcamposActionPerformed(evt);
            }
        });
        pcadastro.add(btLimparcampos);
        btLimparcampos.setBounds(920, 200, 140, 40);

        btLivros.setText("Livros");
        btLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLivrosActionPerformed(evt);
            }
        });
        pcadastro.add(btLivros);
        btLivros.setBounds(920, 240, 140, 40);

        btEquipamentos.setText("Equipamentos");
        btEquipamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEquipamentosActionPerformed(evt);
            }
        });
        pcadastro.add(btEquipamentos);
        btEquipamentos.setBounds(920, 280, 140, 40);

        brFuncionarios.setText("Usuários");
        brFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brFuncionariosActionPerformed(evt);
            }
        });
        pcadastro.add(brFuncionarios);
        brFuncionarios.setBounds(920, 320, 140, 40);

        btIrPMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/home.png"))); // NOI18N
        btIrPMenu.setText("Ir para Menu");
        btIrPMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIrPMenuActionPerformed(evt);
            }
        });
        pcadastro.add(btIrPMenu);
        btIrPMenu.setBounds(920, 360, 140, 40);

        pfuncionario.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Exclusivo ao Funcionário:"));

        jLabel10.setText("Cargo:");

        javax.swing.GroupLayout pfuncionarioLayout = new javax.swing.GroupLayout(pfuncionario);
        pfuncionario.setLayout(pfuncionarioLayout);
        pfuncionarioLayout.setHorizontalGroup(
            pfuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pfuncionarioLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pfuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pfuncionarioLayout.setVerticalGroup(
            pfuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pfuncionarioLayout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tfCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tpFuncionario.addTab("Funcionário", pfuncionario);

        paluno.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Exclusivo ao Aluno:"));

        jLabel6.setText("Série:");

        tfSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSerieActionPerformed(evt);
            }
        });

        jLabel5.setText("Turma:");

        jLabel7.setText("Escola:");

        tfEscola.setText("E.E.E.P Monsenhor Odorico de Andrade");

        jLabel9.setText("Nº da Matricula:");

        javax.swing.GroupLayout palunoLayout = new javax.swing.GroupLayout(paluno);
        paluno.setLayout(palunoLayout);
        palunoLayout.setHorizontalGroup(
            palunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(palunoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(palunoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(tfSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(tfTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tfNMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(palunoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(palunoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(tfEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        palunoLayout.setVerticalGroup(
            palunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(palunoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(palunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(palunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(tfEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tpFuncionario.addTab("Aluno", paluno);

        pOutro.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Esclusivo à Outros"));

        jLabel15.setText("Cidade:");

        jLabel16.setText("CEP:");

        jLabel17.setText("UF:");

        javax.swing.GroupLayout pOutroLayout = new javax.swing.GroupLayout(pOutro);
        pOutro.setLayout(pOutroLayout);
        pOutroLayout.setHorizontalGroup(
            pOutroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pOutroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pOutroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pOutroLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pOutroLayout.createSequentialGroup()
                        .addComponent(tfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(tfCep, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(tfUf, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        pOutroLayout.setVerticalGroup(
            pOutroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pOutroLayout.createSequentialGroup()
                .addGroup(pOutroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pOutroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfUf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tpFuncionario.addTab("Outro", pOutro);

        pcadastro.add(tpFuncionario);
        tpFuncionario.setBounds(510, 30, 410, 200);

        Ramiro.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Ramiro.setLayout(null);
        Ramiro.add(lbDate);
        lbDate.setBounds(10, 4, 100, 20);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setLayout(null);

        jLabel18.setText("Usuário:");
        jPanel5.add(jLabel18);
        jLabel18.setBounds(10, 0, 60, 30);
        jPanel5.add(msgNome);
        msgNome.setBounds(80, 0, 610, 30);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(null);
        jPanel4.add(lbHora);
        lbHora.setBounds(30, 4, 90, 20);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pcadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 1068, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Ramiro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(69, 69, 69))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(pcadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ramiro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 2);
        getContentPane().add(jPanel2, gridBagConstraints);

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
        setBounds((screenSize.width-1118)/2, (screenSize.height-639)/2, 1118, 639);
    }// </editor-fold>//GEN-END:initComponents

private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
    if (escolha.equals("novo")) {
        if (tipoLocatario.equals("Aluno")) {
            cadastro_locatario_aluno();
        } else if (tipoLocatario.equals("Funcionário")) {
            cadastro_locatario_funcionario();
        } else if (tipoLocatario.equals("Outros")) {
            cadastro_locatario_outro();
        }

    } else if (escolha.equals("alterar")) {
        if (tipoLocatario.equals("Aluno")) {
            alterar_locatario_aluno();
        } else if (tipoLocatario.equals("Funcionário")) {
            alterar_locatario_funcionario();
        } else if (tipoLocatario.equals("Outros")) {
            alterar_locatario_outro();
        }
    }
}//GEN-LAST:event_btSalvarActionPerformed

private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
    escolha = "novo";
    if (cbOpcao.getSelectedItem().equals("")) {
        JOptionPane.showMessageDialog(null, "Selecione um tipo de locatário primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        btSalvar.setEnabled(false);
    } else {
        opcaoLocatario();
        btSalvar.setEnabled(true);
    }

}//GEN-LAST:event_btNovoActionPerformed

private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
    escolha = "alterar";
    if (cbOpcao.getSelectedItem().equals("")) {
        JOptionPane.showMessageDialog(null, "Selecione um tipo de locatário primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        btSalvar.setEnabled(false);
    } else {
        opcaoLocatario();
        btSalvar.setEnabled(true);
    }
}//GEN-LAST:event_btAlterarActionPerformed

private void btIrPMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIrPMenuActionPerformed
    principal p = new principal();
    p.setVisible(true);
    dispose();
}//GEN-LAST:event_btIrPMenuActionPerformed

private void btLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLivrosActionPerformed
    cadastroLivro cl = new cadastroLivro();
    cl.setVisible(true);
    dispose();
}//GEN-LAST:event_btLivrosActionPerformed

private void tfPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPesquisarActionPerformed
    listarLocatarios();
}//GEN-LAST:event_tfPesquisarActionPerformed

private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
    listarLocatarios();
}//GEN-LAST:event_btPesquisarActionPerformed

private void brFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brFuncionariosActionPerformed
//    cadastrousuario cf = new cadastrousuario();
    //  cf.setVisible(true);
    dispose();
}//GEN-LAST:event_brFuncionariosActionPerformed

private void btLimparcamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparcamposActionPerformed
    limparCampos();
}//GEN-LAST:event_btLimparcamposActionPerformed

private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
    if (tipoLocatario.equals("Aluno")) {
        excluir_locatario_aluno();
    } else if (tipoLocatario.equals("Funcionário")) {
        excluir_locatario_funcionario();
    } else if (tipoLocatario.equals("Outros")) {
        excluir_locatario_outro();
    }
}//GEN-LAST:event_btExcluirActionPerformed

private void btEquipamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEquipamentosActionPerformed
//    cadastroequipamento ce = new cadastroequipamento();
    // ce.setVisible(true);
    dispose();
}//GEN-LAST:event_btEquipamentosActionPerformed

private void cbOpcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOpcaoActionPerformed
}//GEN-LAST:event_cbOpcaoActionPerformed

private void tfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tfNomeActionPerformed

private void tfSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSerieActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tfSerieActionPerformed

private void tfEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEnderecoActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tfEnderecoActionPerformed

private void rbtFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtFActionPerformed
    rbtM.setSelected(false);
}//GEN-LAST:event_rbtFActionPerformed

private void rbtMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtMActionPerformed
    rbtF.setSelected(false);
}//GEN-LAST:event_rbtMActionPerformed

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
            java.util.logging.Logger.getLogger(cadastrolocatario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cadastrolocatario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cadastrolocatario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cadastrolocatario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new cadastrolocatario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Pendencias;
    private javax.swing.JPanel Ramiro;
    private javax.swing.JButton brFuncionarios;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btEquipamentos;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btIrPMenu;
    private javax.swing.JButton btLimparcampos;
    private javax.swing.JButton btLivros;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox cbOpcao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JLabel lbrelogio;
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
    private javax.swing.JPanel pOutro;
    private javax.swing.JPanel paluno;
    private javax.swing.JPanel pcadastro;
    private javax.swing.JPanel pfuncionario;
    private javax.swing.JRadioButton rbtF;
    private javax.swing.JRadioButton rbtM;
    private javax.swing.JTable tbLocatarios;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JTextField tfCargo;
    private javax.swing.JFormattedTextField tfCelular;
    private javax.swing.JTextField tfCep;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JTextField tfCpf;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfEndereco;
    private javax.swing.JTextField tfEscola;
    private javax.swing.JTextField tfNMatricula;
    private com.toedter.calendar.JDateChooser tfNascimento;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfPesquisar;
    private javax.swing.JTextField tfRg;
    private javax.swing.JTextField tfSerie;
    private javax.swing.JTextField tfTurma;
    private javax.swing.JTextField tfUf;
    private javax.swing.JTabbedPane tpFuncionario;
    // End of variables declaration//GEN-END:variables
}
