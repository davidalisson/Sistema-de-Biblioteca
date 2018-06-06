/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisbiblioteca.cadastroEquipamento;

import br.com.sisbiblioteca.acessoMysql.acessoMysql;
import br.com.sisbiblioteca.registroequipamentos.reservaequipamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Murilo Alves
 * mudar quant por idT
 * SELECT NOMEEQUIPAMENTO, COUNT( * ) FROM CADASTRO_EQUIPAMENTO WHERE NOMEEQUIPAMENTO LIKE  ? AND STATUS =  'disponivel'GROUP BY NOMEEQUIPAMENTO
 */
public class equipamentoControl {

    PreparedStatement pstm;
    ResultSet rs;
    acessoMysql bd = new acessoMysql();
    String cadastroEquipamento = "INSERT INTO cadastro_equipamento (nomeequipamento, status, categoria, caracteristicas) VALUES (?,?,?,?)";

    String consultaEquipamento = "SELECT * FROM CADASTRO_EQUIPAMENTO WHERE idequipamento LIKE  ?";
    
    String excluirEquipamento = "DELETE FROM cadastro_equipamento WHERE  idequipamento = ?";
    String alterarEquipamento = "UPDATE cadastro_equipamento SET nomeequipamento = ?, caregoria = ?, caracteristicas = ?, status = ? WHERE idequipamento = ?";

    
    //Cadastro
    public void CadastroEquipamento(equipamentoBean ab) {
        try {
            pstm = bd.conectar().prepareStatement(cadastroEquipamento);

            pstm.setString(1, ab.getNomeequipamento());
            pstm.setString(2, ab.getStatus());
            pstm.setString(3, ab.getCategoria());
            pstm.setString(4, ab.getCaracteristicas());

            pstm.executeUpdate();
            bd.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(equipamentoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Consulta
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
                
                Equipamentos.add(cb);
                
                

            }

        } catch (SQLException ex) {
            Logger.getLogger(equipamentoControl.class.getName()).log(Level.SEVERE, null, ex);
        }



        return Equipamentos;
    }
//Excluir Livro

    public void excluirEquipamento(int idEquipamento) {
        try {
            pstm = bd.conectar().prepareStatement(excluirEquipamento);
            pstm.setInt(1, idEquipamento);
            pstm.executeUpdate();
            bd.desconectar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Alterar

    public void alterarEquipamento(equipamentoBean cb) {
        try {

            pstm = bd.conectar().prepareStatement(alterarEquipamento);

            pstm.setString(1, cb.getNomeequipamento());
            pstm.setString(2, cb.getCategoria());
            pstm.setString(3, cb.getCaracteristicas());
            pstm.setString(4, cb.getStatus());
            pstm.setInt(5, cb.getIdequipamento());

            pstm.executeUpdate();
            bd.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(equipamentoControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
