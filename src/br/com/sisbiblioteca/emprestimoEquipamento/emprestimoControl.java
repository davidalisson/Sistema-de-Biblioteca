/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisbiblioteca.emprestimoEquipamento;

import br.com.sisbiblioteca.acessoMysql.acessoMysql;
import br.com.sisbiblioteca.cadastroEquipamento.equipamentoBean;

import br.com.sisbiblioteca.cadastrolocatario.cadastrolocatarioBean;
import br.com.sisbiblioteca.cadastrolocatario.cadastrolocatarioControl;
import br.com.sisbiblioteca.registroequipamentos.reservaequipamentoBean;
import br.com.sisbiblioteca.registroequipamentos.reservaequipamentoControl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

/**
 *
 * @author David
 */
public class emprestimoControl {

    PreparedStatement pstm;
    ResultSet rs;
    acessoMysql bd = new acessoMysql();
    String consulta_locatario_aluno = "SELECT * FROM CADASTRO_LOCATARIO_ALUNO WHERE NOME_ALUNO LIKE ?";
    String cadastraEmp = "INSERT INTO EMPRESTIMO_EQUIPAMENTO(COD_LOC_EQUIP, COD_EQUIP_EQUIP, COD_USU_EQUIP, DATA_EMP_EQUIP, HORA_EMP_EQUIP, HORA_DEV_EQUIP, DATA_PREV_EQUIP, STATUS)VALUES(?,?,?,?,?,?,?,?)";
    String alterarQuantidade = " UPDATE  CADASTRO_EQUIPAMENTO SET QTDPEQUIPAMRNTO  = ? WHERE IDEQUIPAMENTO = ?";
    
    String consultaEquipamento = "SELECT * FROM CADASTRO_EQUIPAMENTO WHERE idequipamento LIKE  ?";
   
    
    EmprestimoEquipamento a;
    public List<equipamentoBean> ListarEquipamentos(String nome_equipamento) {
        List<equipamentoBean> Equipamentos = new ArrayList();
        try {
            
            

                pstm = bd.conectar().prepareStatement(consultaEquipamento);
                pstm.setString(1, nome_equipamento);
                rs = pstm.executeQuery();
                equipamentoBean cb;

                while (rs.next()) {
                    cb = new equipamentoBean();
                    
                    cb.setNomeequipamento(rs.getString("nomeequipamento"));
                    cb.setIdequipamento(rs.getInt("idequipamento"));
                    a.status = (rs.getString("status"));
                    Equipamentos.add(cb);

                }
        } catch (SQLException ex) {
            Logger.getLogger(emprestimoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Equipamentos;
    }

    public void cadastraEmp(emprestimoBean reb) {
        try {
            pstm = bd.conectar().prepareStatement(cadastraEmp);
            pstm.setInt(1, reb.getCod_loc_equip());
            pstm.setInt(2, reb.getCod_equip_equip());
            pstm.setInt(3, reb.getCod_usu_equip());
            pstm.setDate(4, reb.getData_emp_equip());
            pstm.setString(5, reb.getHora_emp_equip());
            pstm.setString(6, reb.getHora_dev_equip());
            
            pstm.setDate(7, reb.getData_prev_equip());
            
            pstm.setString(8, reb.getStatus());

            pstm.executeUpdate();
            bd.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(emprestimoControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    String consulta_locatario_funcionario = "SELECT * FROM CADASTRO_LOCATARIO_FUNCIONARIO WHERE NOME_FUNCIONARIO LIKE ?";

    public List<cadastrolocatarioBean> listar_locatario_funcionario(String nome_locatario) {
        List<cadastrolocatarioBean> funcionario = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consulta_locatario_funcionario);
            pstm.setString(1, nome_locatario);
            rs = pstm.executeQuery();
            cadastrolocatarioBean ab;

            while (rs.next()) {
                ab = new cadastrolocatarioBean();
                ab.setIdlocatario(rs.getInt("idcfuncionario"));
                ab.setNome_locatario(rs.getString("nome_funcionario"));
                ab.setData_nascimento(rs.getDate("data_nasc_funcionario"));
                ab.setSexo(rs.getString("sexo"));
                ab.setCelular(rs.getString("celular"));
                ab.setCpf(rs.getString("cpf"));
                ab.setRg(rs.getString("rg"));
                ab.setEmail(rs.getString("email"));
                ab.setEndereco(rs.getString("endereco"));
                ab.setBairro(rs.getString("bairro"));
                ab.setCargo(rs.getString("cargo"));
                ab.setData_cadastro(rs.getDate("data_cadastro"));
                ab.setHora_cadastro(rs.getString("hora_cadastro"));
                ab.setResp_cadastro(rs.getString("resp_funcionario"));

                funcionario.add(ab);
            }

        } catch (SQLException ex) {
            Logger.getLogger(cadastrolocatarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionario;
    }
   
    public List<emprestimoBean> listarEmprestimos(String emp) {
        List<emprestimoBean> emprestimo = new ArrayList();
        try {

           
            String consultaPorData = "SELECT CADASTRO_EQUIPAMENTO.NOMEEQUIPAMENTO,  cadastro_locatario.NOME_LOCATARIO,cadastro_funcionario.NOME, EMPRESTIMO_EQUIPAMENTO. * FROM EMPRESTIMO_EQUIPAMENTO,  cadastro_locatario,cadastro_funcionario, CADASTRO_EQUIPAMENTO WHERE EMPRESTIMO_EQUIPAMENTO.COD_EQUIP_EQUIP = CADASTRO_EQUIPAMENTO.IDEQUIPAMENTO AND EMPRESTIMO_EQUIPAMENTO.cod_loc_equip = cadastro_locatario.idlocatario AND EMPRESTIMO_EQUIPAMENTO.cod_usu_equip = cadastro_funcionario.id AND EMPRESTIMO_EQUIPAMENTO.STATUS !='devolvido' AND cod_emp_equip like ?";
            pstm = bd.conectar().prepareStatement(consultaPorData);
            pstm.setString(1, emp);
            
            rs = pstm.executeQuery();
            emprestimoBean eb;

            while (rs.next()) {
                eb = new emprestimoBean();
                eb.setNome_equip(rs.getString("cadastro_equipamento.nomeequipamento"));
                eb.setNome_loc(rs.getString("cadastro_locatario.NOME_LOCATARIO"));
                eb.setNome_func(rs.getString("cadastro_funcionario.NOME"));
                eb.setCod_equip_equip(rs.getInt("cod_equip_equip"));
                eb.setCod_usu_equip(rs.getInt("cod_usu_equip"));
                eb.setData_emp_equip(rs.getDate("data_emp_equip"));
                eb.setHora_emp_equip(rs.getString("hora_emp_equip"));
                eb.setHora_dev_equip(rs.getString("hora_dev_equip"));
                eb.setData_prev_equip(rs.getDate("data_prev_equip"));
                eb.setCod_emp_equip(rs.getInt("cod_emp_equip"));

                emprestimo.add(eb);
            }

        } catch (SQLException ex) {
            Logger.getLogger(emprestimoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emprestimo;
    }
    String pesquisaReservas = "SELECT * FROM RESERVA_EQUIPAMENTO WHERE IDRESERVA LIKE ?";

    public List<reservaequipamentoBean> listarReservas(String nome_fun) {
        List<reservaequipamentoBean> reservaE = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(pesquisaReservas);
            pstm.setString(1, nome_fun);
            rs = pstm.executeQuery();
            reservaequipamentoBean reb;

            while (rs.next()) {
                reb = new reservaequipamentoBean();


                reb.setIdreserva(rs.getInt("idreserva"));
                reb.setIdequipamento(rs.getInt("idequipamento"));
                reb.setDataReserva(rs.getDate("datareserva"));
                reb.setHoraReserva(rs.getString("horareserva"));
                reb.setDataEmprestimo(rs.getDate("dataemprestimo"));
                reb.setHoraEmprestimo(rs.getString("horaemprestimo"));
                reb.setDataDevolucao(rs.getDate("datadevolucao"));
                reb.setHoraDevolucao(rs.getString("horadevolucao"));
                reb.setQuantidade(rs.getInt("quantidade"));


                reservaE.add(reb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(reservaequipamentoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservaE;
    }

    public void alteraQuantidade(emprestimoBean pb) {
        try {
            pstm = bd.conectar().prepareStatement(alterarQuantidade);

            pstm.setInt(1, pb.getQuant_equip());

            pstm.setInt(2, pb.getCod_equip_equip());

            pstm.executeUpdate();
            bd.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(emprestimoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
