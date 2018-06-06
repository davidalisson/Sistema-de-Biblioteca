package br.com.sisbiblioteca.pendenciasLivro;

import br.com.sisbiblioteca.acessoMysql.acessoMysql;
import br.com.sisbiblioteca.emprestimoLivro.registroLivroBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class pendenciasLivroControl {
    
    PreparedStatement pstm;
    ResultSet rs;
    acessoMysql bd = new acessoMysql();
    
    //String consultaPendenciaAluno = "SELECT cadastro_locatario.NOME_LOCATARIO,CADASTRO_LIVRO.NOMELIVRO , CADASTRO_LIVRO.IDLIVRO * FROM REGISTRO_LIVRO,  CADASTRO_LOCATARIO, CADASTRO_LIVRO WHERE"
    //+ " REGISTRO_LIVRO.idlivro = CADASTRO_LIVRO.idlivro AND REGISTRO_LIVRO.idlocatario = cadastro_locatario.idlocatario AND REGISTRO_LIVRO.TIPO_LOCATARIO = 'aluno' and"
    //+ " REGISTRO_LIVRO.status != 'devolvido'";
    
    String consultaPendenciaAluno = "SELECT cadastro_locatario.NOME_LOCATARIO,CADASTRO_LIVRO.NOMELIVRO  , CADASTRO_LIVRO.IDLIVRO, REGISTRO_LIVRO. * FROM REGISTRO_LIVRO,  cadastro_locatario, CADASTRO_LIVRO WHERE REGISTRO_LIVRO.idlivro = CADASTRO_LIVRO.idlivro AND REGISTRO_LIVRO.idlocatario = cadastro_locatario.idlocatario AND REGISTRO_LIVRO.TIPO_LOCATARIO = 'aluno' and registro_livro.status != 'devolvido'";

    
    //REGISTRO_LIVRO. * FROM REGISTRO_LIVRO,
    public List<registroLivroBean> listarPendentesAluno(String nome_pendente) {
        List<registroLivroBean> pendente = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consultaPendenciaAluno);
            rs = pstm.executeQuery();
            registroLivroBean rlb;

            while (rs.next()) {
                rlb = new registroLivroBean();
                rlb.setNome_locatario(rs.getString("cadastro_locatario.nome_locatario"));
                rlb.setNome_livro(rs.getString("cadastro_livro.nomelivro"));
                rlb.setCodigoLivro(rs.getInt("cadastro_livro.idlivro"));                
                rlb.setQuantidade(rs.getInt("registro_livro.quantidade"));
                rlb.setData_emprestimo(rs.getDate("registro_livro.data_emprestimo"));
                rlb.setData_prevista_devolucao(rs.getDate("registro_livro.data_prevista_devolucao"));

                rlb.setIdlivro(rs.getInt("idlivro"));
                rlb.setIdregistrolivro(rs.getInt("idregistrolivro"));
                pendente.add(rlb);
            }

        } catch (SQLException ex) {
            Logger.getLogger(pendenciasLivroControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pendente;
    }

    String consultaPendenciaFuncionario = "SELECT cadastro_locatario.NOME_LOCATARIO,CADASTRO_LIVRO.NOMELIVRO  , CADASTRO_LIVRO.IDLIVRO * FROM REGISTRO_LIVRO,  cadastro_locatario, CADASTRO_LIVRO WHERE"
    + " REGISTRO_LIVRO.idlivro = CADASTRO_LIVRO.idlivro AND REGISTRO_LIVRO.idlocatario = cadastro_locatario.idlocatario AND ? AND REGISTRO_LIVRO.TIPO_LOCATARIO = 'funcionario' and"
    + " registro_livro.status != 'devolvido'";

public List<registroLivroBean> listarPendentesFuncionario(String nome_pendente) {
        List<registroLivroBean> pendente = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consultaPendenciaFuncionario);
            rs = pstm.executeQuery();
            registroLivroBean rlb;

            while (rs.next()) {
                rlb = new registroLivroBean();
                rlb.setNome_locatario(rs.getString("cadastro_locatario.nome_locatario"));
                rlb.setNome_livro(rs.getString("cadastro_livro.nomelivro"));
                rlb.setCodigoLivro(rs.getInt("cadastro_livro.idlivro"));                
                rlb.setQuantidade(rs.getInt("registro_livro.quantidade"));
                rlb.setData_emprestimo(rs.getDate("registro_livro.data_emprestimo"));
                rlb.setData_prevista_devolucao(rs.getDate("registro_livro.data_prevista_devolucao"));

                rlb.setIdlivro(rs.getInt("idlivro"));
                pendente.add(rlb);
            }

        } catch (SQLException ex) {
            Logger.getLogger(pendenciasLivroControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pendente;
    }

    String consultaPendenciaOutro = "SELECT cadastro_locatario.NOME_LOCATARIO,CADASTRO_LIVRO.NOMELIVRO , CADASTRO_LIVRO.IDLIVRO * FROM REGISTRO_LIVRO,  cadastro_locatario, CADASTRO_LIVRO WHERE"
    + " REGISTRO_LIVRO.idlivro = CADASTRO_LIVRO.idlivro AND REGISTRO_LIVRO.idlocatario = cadastro_locatario.idlocatario AND ? AND REGISTRO_LIVRO.TIPO_LOCATARIO = 'outro'"
    + " and registro_livro.status != 'devolvido'";

    public List<registroLivroBean> listarPendentesOutro(String nome_pendente) {
        List<registroLivroBean> pendente = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consultaPendenciaOutro);
            rs = pstm.executeQuery();
            registroLivroBean rb;

            while (rs.next()) {
                rb = new registroLivroBean();
                rb.setNome_locatario(rs.getString("cadastro_locatario.nome_locatario"));
                rb.setNome_livro(rs.getString("cadastro_livro.nomelivro"));
                rb.setCodigoLivro(rs.getInt("cadastro_livro.idlivro"));                
                rb.setQuantidade(rs.getInt("registro_livro.quantidade"));
                rb.setData_emprestimo(rs.getDate("registro_livro.data_emprestimo"));
                rb.setData_prevista_devolucao(rs.getDate("registro_livro.data_prevista_devolucao"));

                rb.setIdlivro(rs.getInt("idlivro"));
                pendente.add(rb);
            }

        } catch (SQLException ex) {
            Logger.getLogger(pendenciasLivroControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pendente;
    }
    
}
