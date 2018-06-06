/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisbiblioteca.registroequipamentos;

import br.com.sisbiblioteca.acessoMysql.acessoMysql;
import br.com.sisbiblioteca.cadastroEquipamento.equipamentoBean;
import br.com.sisbiblioteca.emprestimoEquipamento.EmprestimoEquipamento;
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
 */
public class reservaequipamentoControl {
    PreparedStatement pstm;
    ResultSet rs;
    acessoMysql bd = new acessoMysql();
    String reservararEquipamentos = "INSERT INTO RESERVA_EQUIPAMENTO(IDLOCATARIO, IDEQUIPAMENTO, DATARESERVA, HORARESERVA, DATAEMPRESTIMO, HORAEMPRESTIMO, DATADEVOLUCAO, HORADEVOLUCAO, QUANTIDADE, EMPRESTADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    String equipamentoOcupado = "UPDATE cadastro_equipamento SET status = ? WHERE idequipamento = ?";
    
    String consultaEquipamentoQ = "SELECT NOMEEQUIPAMENTO, COUNT( * ) FROM CADASTRO_EQUIPAMENTO WHERE NOMEEQUIPAMENTO LIKE  ? AND STATUS !=  'ocupado' GROUP BY NOMEEQUIPAMENTO";
    
    String emprestadoSim = "UPDATE reserva_equipamento SET emprestado = ? WHERE idreserva = ?";
    
    String cadastraEmp = "INSERT INTO EMPRESTIMO_EQUIPAMENTO(COD_LOC_EQUIP, COD_EQUIP_EQUIP, COD_USU_EQUIP, DATA_EMP_EQUIP, HORA_EMP_EQUIP, HORA_DEV_EQUIP, DATA_PREV_EQUIP, STATUS)VALUES(?,?,?,?,?,?,?,?)";
    public void cadastraEmp(reservaequipamentoBean reb) {
        try {        
            pstm = bd.conectar().prepareStatement(cadastraEmp);
            pstm.setInt(1, reb.getIdlocatario());
            pstm.setInt(2, reb.getIdequipamento());
            pstm.setInt(3, reb.getCod_usu_equip());
            pstm.setDate(4, reb.getDataEmprestimo());
            pstm.setString(5, reb.getHoraEmprestimo());
            pstm.setString(6, reb.getHoraDevolucao());
            
            pstm.setDate(7, reb.getDataDevolucao());
            
            pstm.setString(8, reb.getStatus());

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(reservaequipamentoControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        

    }
    public void emprestadoSim(reservaequipamentoBean cb) {
        try {
            pstm = bd.conectar().prepareStatement(emprestadoSim);

            pstm.setString(1, cb.getEmprestado());
            pstm.setInt(2, cb.getIdreserva());

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(reservaequipamentoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<equipamentoBean> ListarEquipamentosQ(String nome_equipamento) {
         List<equipamentoBean> Equipamentos = new ArrayList();
        try {
           
            

                pstm = bd.conectar().prepareStatement(consultaEquipamentoQ);
                pstm.setString(1, nome_equipamento);
                rs = pstm.executeQuery();
                equipamentoBean cb;

                while (rs.next()) {
                    cb = new equipamentoBean();
                    
                    cb.setNomeequipamento(rs.getString("nomeequipamento"));
                    cb.setQuantidade(rs.getInt("COUNT( * )"));
                    Equipamentos.add(cb);

                }

            
        } catch (SQLException ex) {
            Logger.getLogger(reservaequipamentoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Equipamentos;
    }
    
    public void equipamentoReservado(equipamentoBean cb) {
        try {
            pstm = bd.conectar().prepareStatement(equipamentoOcupado);

            pstm.setString(1, cb.getStatus());
            pstm.setInt(2, cb.getIdequipamento());

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(reservaequipamentoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    String equipamentoDisponivel = "UPDATE cadastro_equipamento SET status = ? WHERE idequipamento = ?";
    
    public void equipamentoDisponivel(equipamentoBean cb) {
        try {
            pstm = bd.conectar().prepareStatement(equipamentoOcupado);

            pstm.setString(1, cb.getStatus());
            pstm.setInt(2, cb.getIdequipamento());

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(reservaequipamentoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    String pesquisaReservas = "SELECT CADASTRO_EQUIPAMENTO.NOMEEQUIPAMENTO,  cadastro_locatario.NOME_LOCATARIO, RESERVA_EQUIPAMENTO. * FROM RESERVA_EQUIPAMENTO,  cadastro_LOCATARIO, CADASTRO_EQUIPAMENTO WHERE RESERVA_EQUIPAMENTO.IDEQUIPAMENTO = CADASTRO_EQUIPAMENTO.IDEQUIPAMENTO AND RESERVA_EQUIPAMENTO.IDLOCATARIO = cadastro_LOCATARIO.idlocatario AND RESERVA_EQUIPAMENTO.emprestado = 'Nao' and idreserva like ?";    
    
    String d = "SELECT * FROM TABELA WHERE DATARESERVA BETWEEN ? AND ? ORDER BY DATARESERVADA ASC";
    EmprestimoEquipamento e;
    public List<reservaequipamentoBean> listarReservas(String nome_fun) {
        List<reservaequipamentoBean> reservaE = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(pesquisaReservas);
            pstm.setString(1, nome_fun);
            rs = pstm.executeQuery();
            reservaequipamentoBean reb;

            while (rs.next()) {
                reb = new reservaequipamentoBean();
                
                reb.setNome_equip(rs.getString("cadastro_equipamento.nomeequipamento"));
                reb.setNome_loc(rs.getString("cadastro_locatario.NOME_LOCATARIO"));
                
                reb.setIdreserva(rs.getInt("idreserva"));
                reb.setIdlocatario(rs.getInt("idlocatario"));
                reb.setIdequipamento(rs.getInt("idequipamento"));
                reb.setDataReserva(rs.getDate("datareserva"));
                reb.setHoraReserva(rs.getString("horareserva"));
                reb.setDataEmprestimo(rs.getDate("dataemprestimo"));
                reb.setHoraEmprestimo(rs.getString("horaemprestimo"));
                reb.setDataDevolucao(rs.getDate("datadevolucao"));
                reb.setHoraDevolucao(rs.getString("horadevolucao"));
                reb.setQuantidade(rs.getInt("quantidade"));
                reb.setEmprestado(rs.getString("emprestado"));
                e.dataemprestimoR = rs.getDate("dataemprestimo");
                e.datadevolucaoR = rs.getDate("datadevolucao");
                e.horaemprestimoR = rs.getString("horaemprestimo");
                e.horadevolucaoR = rs.getString("horadevolucao");
                

                reservaE.add(reb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(reservaequipamentoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservaE;
    }
    
    String Reservas = "SELECT * FROM RESERVA_EQUIPAMENTO WHERE idequipamento LIKE ? ";
    public List<reservaequipamentoBean> Reservas(String nome_fun) {
        List<reservaequipamentoBean> reservaE = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(Reservas);
            pstm.setString(1, nome_fun);
            rs = pstm.executeQuery();
            reservaequipamentoBean reb;

            while (rs.next()) {
                reb = new reservaequipamentoBean();
                
                e.dataemprestimoR = rs.getDate("dataemprestimo");
                e.datadevolucaoR = rs.getDate("datadevolucao");
                e.horaemprestimoR = rs.getString("horaemprestimo");
                e.horadevolucaoR = rs.getString("horadevolucao");
                reb.setIdreserva(rs.getInt("idreserva"));
                reb.setIdlocatario(rs.getInt("idlocatario"));
                reb.setIdequipamento(rs.getInt("idequipamento"));
                reb.setDataReserva(rs.getDate("datareserva"));
                reb.setHoraReserva(rs.getString("horareserva"));
                reb.setDataEmprestimo(rs.getDate("dataemprestimo"));
                reb.setHoraEmprestimo(rs.getString("horaemprestimo"));
                reb.setDataDevolucao(rs.getDate("datadevolucao"));
                reb.setHoraDevolucao(rs.getString("horadevolucao"));
                reb.setQuantidade(rs.getInt("quantidade"));
                reb.setEmprestado(rs.getString("emprestado"));

                reservaE.add(reb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(reservaequipamentoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservaE;
    }
    public void reservarEquipamentos(reservaequipamentoBean reb) {
        try {
            pstm = bd.conectar().prepareStatement(reservararEquipamentos);
            pstm.setInt(1, reb.getIdlocatario());
            pstm.setInt(2, reb.getIdequipamento());
            pstm.setDate(3, reb.getDataReserva());
            pstm.setString(4, reb.getHoraReserva());
            pstm.setDate(5, reb.getDataEmprestimo());
            pstm.setString(6, reb.getHoraEmprestimo());
            pstm.setDate(7, reb.getDataDevolucao());
            pstm.setString(8, reb.getHoraDevolucao());
            pstm.setInt(9, reb.getQuantidade());
            
            pstm.setString(10, reb.getEmprestado());
            pstm.executeUpdate();
            bd.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(reservaequipamentoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}


