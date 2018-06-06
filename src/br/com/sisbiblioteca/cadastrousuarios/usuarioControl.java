/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisbiblioteca.cadastrousuarios;

import br.com.sisbiblioteca.acessoMysql.acessoMysql;
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
public class usuarioControl {

    PreparedStatement pstm;
    ResultSet rs;
    acessoMysql bd = new acessoMysql();
    String cadastroFuncionario = "INSERT INTO CADASTRO_FUNCIONARIO(NOME, END,BAIRRO, CELULAR, CPF, LOGIN, SENHA ) VALUES(?,?,?,?,?,?,?)";
    String pesquisaFuncionario = "SELECT * FROM CADASTRO_FUNCIONARIO WHERE NOME LIKE ?";
    String alterarFuncionario = "UPDATE CADASTRO_FUNCIONARIO SET NOME = ?, END = ?,BAIRRO = ?, CELULAR = ?, CPF = ?, LOGIN = ?, SENHA = ? WHERE ID = ?";

    public void alterarFuncionario(usuarioBean fb) {
        try {
            pstm = bd.conectar().prepareStatement(alterarFuncionario);
            pstm.setString(1, fb.getNomefuncionario());
            pstm.setString(2, fb.getEndfuncionario());
            pstm.setString(3, fb.getBairro());
            pstm.setString(4, fb.getCelular());
            pstm.setString(5, fb.getCpf());
            pstm.setString(6, fb.getLogin());
            pstm.setString(7, fb.getSenha());

            pstm.setInt(8, fb.getIdfuncionario());

            pstm.executeUpdate();
            bd.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(usuarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cadastraFuncionario(usuarioBean fb) {
        try {
            pstm = bd.conectar().prepareStatement(cadastroFuncionario);
            pstm.setString(1, fb.getNomefuncionario());
            pstm.setString(2, fb.getEndfuncionario());
            pstm.setString(3, fb.getBairro());
            pstm.setString(4, fb.getCelular());
            pstm.setString(5, fb.getCpf());
            pstm.setString(6, fb.getLogin());
            pstm.setString(7, fb.getSenha());

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(usuarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<usuarioBean> listarFuncionarios(String nome_fun) {
        List<usuarioBean> funcionario = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(pesquisaFuncionario);
            pstm.setString(1, nome_fun);
            rs = pstm.executeQuery();
            usuarioBean fb;

            while (rs.next()) {
                fb = new usuarioBean();

                fb.setIdfuncionario(rs.getInt("id"));
                fb.setNomefuncionario(rs.getString("nome"));
                fb.setEndfuncionario(rs.getString("end"));
                fb.setBairro(rs.getString("bairro"));
                fb.setCelular(rs.getString("celular"));
                fb.setCpf(rs.getString("cpf"));
                fb.setLogin(rs.getString("login"));
                fb.setSenha(rs.getString("senha"));


                funcionario.add(fb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(usuarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionario;
    }
    String pesquisaFLogin = "SELECT LOGIN FROM CADASTRO_FUNCIONARIO WHERE LOGIN LIKE ?";

    public List<usuarioBean> listarLogin(String nome_fun) {
        List<usuarioBean> funcionario = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(pesquisaFLogin);
            pstm.setString(1, nome_fun);
            rs = pstm.executeQuery();
            usuarioBean fb;

            while (rs.next()) {
                fb = new usuarioBean();

                fb.setLogin(rs.getString("login"));

                funcionario.add(fb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(usuarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionario;
    }
}
