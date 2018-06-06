/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisbiblioteca.cadastroInstituicao;

import br.com.sisbiblioteca.acessoMysql.acessoMysql;
import br.com.sisbiblioteca.cadastrolocatario.cadastrolocatario;
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
public class instituicaoControl {
    PreparedStatement pstm;
    ResultSet rs;
    acessoMysql bd = new acessoMysql();
    String cadastroInst = "INSERT INTO CADASTRO_INSTITUICAO(NOME, CNPJ,CIDADE,ENDERECO, NUM, BAIRRO) VALUES(?,?,?,?,?,?)";
    
    String pesquisaInst = "SELECT * FROM CADASTRO_INSTITUICAO WHERE NOME LIKE ?";
    
    String alterarInst = "UPDATE CADASTRO_INSTITUICAO SET NOME = ?, CNPJ = ?,CIDADE = ?,ENDERECO = ?, NUM = ?, BAIRRO = ? WHERE ID = ?";

    cadastrolocatario cl;
    
    public void alterarInst(instituicaoBean fb) {
        try {
            pstm = bd.conectar().prepareStatement(alterarInst);
            pstm.setString(1, fb.getNome());
            pstm.setString(2, fb.getCnpj());
            pstm.setString(3, fb.getCidade());
            pstm.setString(4, fb.getEndereco());
            pstm.setInt(5, fb.getNum());
            pstm.setString(6, fb.getBairro());
       
            pstm.setInt(7, fb.getIdinst());

            pstm.executeUpdate();
            bd.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(instituicaoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cadastraInst(instituicaoBean ib) {
        try {
            pstm = bd.conectar().prepareStatement(cadastroInst);
            pstm.setString(1, ib.getNome());
            pstm.setString(2, ib.getCnpj());
            pstm.setString(3, ib.getCidade());
            pstm.setString(4, ib.getEndereco());
            pstm.setInt(5, ib.getNum());
            pstm.setString(6, ib.getBairro());
            


            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(instituicaoControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
public List<instituicaoBean> listarInst(String nome_fun) {
        List<instituicaoBean> inst = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(pesquisaInst);
            pstm.setString(1, nome_fun);
            rs = pstm.executeQuery();
            instituicaoBean ib;

            while (rs.next()) {
                ib = new instituicaoBean();

                ib.setIdinst(rs.getInt("id"));
                ib.setNome(rs.getString("nome"));
                
                ib.setCnpj(rs.getString("cnpj"));
                ib.setCidade(rs.getString("cidade"));
                ib.setEndereco(rs.getString("endereco"));
                ib.setNum(rs.getInt("num"));
                ib.setBairro(rs.getString("bairro"));
                
                


                inst.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(instituicaoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inst;
    }
}
