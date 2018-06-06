/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisbiblioteca.devolucaoEquip;

import br.com.sisbiblioteca.acessoMysql.acessoMysql;
import br.com.sisbiblioteca.emprestimoEquipamento.emprestimoBean;
import br.com.sisbiblioteca.emprestimoEquipamento.emprestimoControl;

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
public class devolucaoControl {
     ResultSet rs;
    acessoMysql bd = new acessoMysql();

    String alteraDataDevolucao = "UPDATE EMPRESTIMO_EQUIPAMENTO SET DATA_DEV_EQUIP = ? WHERE COD_EMP_EMP = ?";
    String consultaEmprestimos = "SELECT * FROM EMPRESTIMO_EQUIPAMENTO WHERE COD_EMP_EQUIP LIKE ? ";
    String excluirEmp = "DELETE FROM EMPRESTIMO_EQUIPAMENTO WHERE cod_emp_equip = ?";
    String consultaEquip = "SELECT * FROM EMPRESTIMO_EQUIPAMENTO WHERE COD_EQUIP_EQUIP LIKE ?";
    PreparedStatement pstm;
   
    String alterarEquipamento = "UPDATE cadastro_equipamento SET status = ? WHERE idequipamento = ?";
    
    public void alterarEquipamento(emprestimoBean cb) {
        try {     

            pstm = bd.conectar().prepareStatement(alterarEquipamento);

            pstm.setString(1, cb.getStatusE());
            pstm.setInt(2, cb.getCod_equip_equip());

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(devolucaoControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        

    }
    
    String devolverEquip = " UPDATE  emprestimo_equipamento SET status  = ? WHERE cod_emp_equip = ?";
     public void devolverEquip(emprestimoBean pb) {
        try {        
            pstm = bd.conectar().prepareStatement(devolverEquip);

            pstm.setString(1, pb.getStatus());

            pstm.setInt(2, pb.getCod_emp_equip());

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(devolucaoControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    public void cadastraDataDevol(emprestimoBean eb) {
        try {
            pstm = bd.conectar().prepareStatement(alteraDataDevolucao);
            pstm.setDate(1, eb.getData_dev_emp());


            pstm.executeUpdate();
            bd.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(devolucaoControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<emprestimoBean> listarEmprestimos(Date DInicial, Date DFinal) {
        List<emprestimoBean> emprestimo = new ArrayList();
        try {

           
            String consultaPorData = "SELECT CADASTRO_EQUIPAMENTO.NOMEEQUIPAMENTO,  cadastro_locatario.NOME_LOCATARIO,cadastro_funcionario.NOME, EMPRESTIMO_EQUIPAMENTO. * FROM EMPRESTIMO_EQUIPAMENTO,  cadastro_locatario,cadastro_funcionario, CADASTRO_EQUIPAMENTO WHERE EMPRESTIMO_EQUIPAMENTO.COD_EQUIP_EQUIP = CADASTRO_EQUIPAMENTO.IDEQUIPAMENTO AND EMPRESTIMO_EQUIPAMENTO.cod_loc_equip = cadastro_locatario.idlocatario AND EMPRESTIMO_EQUIPAMENTO.cod_usu_equip = cadastro_funcionario.id AND EMPRESTIMO_EQUIPAMENTO.STATUS !='devolvido' AND DATA_EMP_EQUIP BETWEEN ? AND ?";
            pstm = bd.conectar().prepareStatement(consultaPorData);
            pstm.setDate(1, DInicial);
            pstm.setDate(2, DFinal);
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

    public List<emprestimoBean> listarEmprestimosEquip(Date DInicial, Date DFinal) {
        List<emprestimoBean> emprestimo = new ArrayList();
        try {

            //String consultaEmprestimos =" SELECT CADASTRO_EQUIPAMENTO.NOMEEQUIPAMENTO,  cadastro_locatario_funcionario.NOME_FUNCIONARIO, EMPRESTIMO_EQUIPAMENTO. * FROM EMPRESTIMO_EQUIPAMENTO,  cadastro_locatario_funcionario, CADASTRO_EQUIPAMENTO WHERE EMPRESTIMO_EQUIPAMENTO.COD_EQUIP_EQUIP = CADASTRO_EQUIPAMENTO.IDEQUIPAMENTO AND EMPRESTIMO_EQUIPAMENTO.cod_loc_equip = cadastro_locatario_funcionario.idcfuncionario AND EMPRESTIMO_EQUIPAMENTO.COD_EMP_EQUIP LIKE ?" ;
            String consultaPorData = "SELECT * FROM EMPRESTIMO_EQUIPAMENTO WHERE DATA_EMP_EQUIP BETWEEN ? AND ?";
            pstm = bd.conectar().prepareStatement(consultaPorData);
            pstm.setDate(1, DInicial);
            pstm.setDate(2, DFinal);
            rs = pstm.executeQuery();
            emprestimoBean eb;

            while (rs.next()) {
                eb = new emprestimoBean();
                eb.setNome_equip(rs.getString("cadastro_equipamento.nomeequipamento"));
                eb.setNome_loc(rs.getString("cadastro_locatario_funcionario.NOME_FUNCIONARIO"));
                eb.setCod_equip_equip(rs.getInt("cod_equip_equip"));
                eb.setCod_usu_equip(rs.getInt("cod_usu_equip"));
                eb.setData_emp_equip(rs.getDate("data_emp_equip"));
                eb.setHora_emp_equip(rs.getString("hora_emp_equip"));
                eb.setHora_dev_equip(rs.getString("hora_dev_equip"));
                eb.setData_prev_equip(rs.getDate("data_prev_equip"));

                emprestimo.add(eb);
            }

        } catch (SQLException ex) {
            Logger.getLogger(emprestimoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emprestimo;
    }
}
