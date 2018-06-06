/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisbiblioteca.pendenciasEquip;

import br.com.sisbiblioteca.acessoMysql.acessoMysql;
import br.com.sisbiblioteca.devolucaoEquip.devolucaoControl;
import br.com.sisbiblioteca.emprestimoEquipamento.emprestimoBean;
import br.com.sisbiblioteca.emprestimoEquipamento.emprestimoControl;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Murilo Alves
 */
public class pendenciasEquipControl {

    PreparedStatement pstm;
    ResultSet rs;
    acessoMysql bd = new acessoMysql();
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date h = new java.util.Date();
    Date hj = Date.valueOf(formato.format(h));
    String consultaPorData = "SELECT CADASTRO_EQUIPAMENTO.NOMEEQUIPAMENTO, cadastro_locatario.NOME_LOCATARIO,cadastro_funcionario.NOME, EMPRESTIMO_EQUIPAMENTO. * FROM EMPRESTIMO_EQUIPAMENTO,  cadastro_locatario,cadastro_funcionario, CADASTRO_EQUIPAMENTO WHERE EMPRESTIMO_EQUIPAMENTO.COD_EQUIP_EQUIP = CADASTRO_EQUIPAMENTO.IDEQUIPAMENTO AND EMPRESTIMO_EQUIPAMENTO.cod_loc_equip = cadastro_locatario.idlocatario AND EMPRESTIMO_EQUIPAMENTO.cod_usu_equip = cadastro_funcionario.id AND EMPRESTIMO_EQUIPAMENTO.data_prev_equip <= '" + hj + "' AND EMPRESTIMO_EQUIPAMENTO.STATUS = 'utilizando' AND cod_emp_equip like ?";

    String alteraDataDevolucao = "UPDATE EMPRESTIMO_EQUIPAMENTO SET DATA_DEV_EQUIP = ? WHERE COD_EMP_EMP = ?";
    
    String alterarEquipamento = "UPDATE cadastro_equipamento SET status = ? WHERE idequipamento = ?";
    
    public List<emprestimoBean> listarEmprestimos(String emp) {
        List<emprestimoBean> emprestimo = new ArrayList();
        try {
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
            Logger.getLogger(pendenciasEquipControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emprestimo;
    }
    
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

}
