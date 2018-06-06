/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisbiblioteca.login;

import br.com.sisbiblioteca.acessoMysql.acessoMysql;
import br.com.sisbiblioteca.cadastroInstituicao.instituicaoBean;
import br.com.sisbiblioteca.cadastroInstituicao.instituicaoControl;
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
public class loginControl {

    PreparedStatement pstm;
    ResultSet rs;
    acessoMysql bd = new acessoMysql();
    String vSenha = "SELECT LOGIN, SENHA, NOME FROM CADASTRO_FUNCIONARIO WHERE LOGIN = ? AND SENHA = ?";
    
    String pesquisaInst = "SELECT * FROM CADASTRO_INSTITUICAO WHERE id = '1'";
    
    login l;
    
    public List<instituicaoBean> listarInst(String nome_fun) {
        List<instituicaoBean> inst = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(pesquisaInst);
            pstm.setString(1, nome_fun);
            rs = pstm.executeQuery();
            instituicaoBean ib;

            while (rs.next()) {
                ib = new instituicaoBean();

                inst.add(ib);
            }
        } catch (SQLException ex) {
            Logger.getLogger(instituicaoControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inst;
    }

    public boolean vsenha(String login, String senha) {

        try {
            boolean autenticado = false;

            pstm = bd.conectar().prepareStatement(vSenha);
            pstm.setString(1, login);
            pstm.setString(2, senha);


            rs = pstm.executeQuery();
            

            if (rs.next()) {
                rs.getString("LOGIN");
                rs.getString("SENHA"); 
                l.nome = rs.getString("NOME");
                
                autenticado = true;
            }

            pstm.close();

            return autenticado;
        } catch (SQLException ex) {

            Logger.getLogger(loginControl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
}
