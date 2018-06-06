package br.com.sisbiblioteca.devolucaolivros;

import br.com.sisbiblioteca.acessoMysql.acessoMysql;
import br.com.sisbiblioteca.emprestimoLivro.registroLivroBean;
import br.com.sisbiblioteca.emprestimoLivro.registroLivroControl;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Regina Kecia
 */
public class devolucaolivrosControl {

    PreparedStatement pstm;
    ResultSet rs;
    acessoMysql bd = new acessoMysql();
    String consultaDevolucaoAluno = "SELECT cadastro_locatario.nome_locatario, cadastro_livro.nomelivro, registro_livro.quantidade, registro_livro.data_emprestimo, registro_livro.data_prevista_devolucao FROM cadastro_locatario, cadastro_livro,"
            + " registro_livro WHERE cadastro_locatario.idlocatario = registro_livro.idlocatario AND registro_livro.idlivro = cadastro_livro.idlivro AND REGISTRO_LIVRO.TIPO_LOCATARIO = 'aluno'";
    String consultaDevolucaoFuncionario = "SELECT cadastro_locatario.nome_locatario, cadastro_livro.nomelivro, registro_livro.quantidade, registro_livro.data_emprestimo, registro_livro.data_prevista_devolucao FROM cadastro_locatario, cadastro_livro,"
            + " registro_livro WHERE cadastro_locatario.idlocatario = registro_livro.idlocatario AND registro_livro.idlivro = cadastro_livro.idlivro AND REGISTRO_LIVRO.TIPO_LOCATARIO = 'funcionario'";
    String consultaDevolucaoOutro = "SELECT cadastro_locatario.nome_locatario, cadastro_livro.nomelivro, registro_livro.quantidade, registro_livro.data_emprestimo, registro_livro.data_prevista_devolucao FROM cadastro_locatario, cadastro_livro,"
            + " registro_livro WHERE cadastro_locatario.idlocatario = registro_livro.idlocatario AND registro_livro.idlivro = cadastro_livro.idlivro AND REGISTRO_LIVRO.TIPO_LOCATARIO = 'outro'";
   
    String alterarLivro = "UPDATE cadastro_livro SET status = ? WHERE idlivro = ?";

    public void alterarLivro(registroLivroBean cb) {
        try {

            pstm = bd.conectar().prepareStatement(alterarLivro);

            pstm.setString(1, cb.getStatusL());

            pstm.setInt(2, cb.getIdlivro());

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(devolucaolivrosControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    String devolucaoLivro = "UPDATE registro_livro SET status = ?, data_dev_emp = ? WHERE idregistrolivro = ?";

    public void devolverLivro(registroLivroBean pb) {
        try {
            pstm = bd.conectar().prepareStatement(devolucaoLivro);

            pstm.setString(1, pb.getStatus());
            
            pstm.setDate(2, pb.getData_dev_emp());

            pstm.setInt(3, pb.getIdregistrolivro());

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(devolucaolivrosControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      
    
    public List<registroLivroBean> listarDevolucaoAluno(Date DInicial, Date DFinal) {
        List<registroLivroBean> registrolivro = new ArrayList();
        try {


            String consultaPorDataAluno = "SELECT cadastro_locatario.NOME_LOCATARIO,CADASTRO_LIVRO.NOMELIVRO , CADASTRO_LIVRO.IDLIVRO, REGISTRO_LIVRO. * FROM REGISTRO_LIVRO,  cadastro_locatario, CADASTRO_LIVRO WHERE REGISTRO_LIVRO.idlivro = CADASTRO_LIVRO.idlivro AND REGISTRO_LIVRO.idlocatario = cadastro_locatario.idlocatario AND data_emprestimo BETWEEN ? AND ? AND REGISTRO_LIVRO.TIPO_LOCATARIO = 'aluno' and registro_livro.status != 'devolvido'";

            pstm = bd.conectar().prepareStatement(consultaPorDataAluno);
            pstm.setDate(1, DInicial);
            pstm.setDate(2, DFinal);
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
                rb.setIdregistrolivro(rs.getInt("idregistrolivro"));
                registrolivro.add(rb);
            }

        } catch (SQLException ex) {
            Logger.getLogger(registroLivroControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registrolivro;
    }

    public List<registroLivroBean> listarDevolucaoFuncionario(Date DInicial, Date DFinal) {
        List<registroLivroBean> registrolivro = new ArrayList();
        try {


            String consultaPorDataFuncionario = "SELECT cadastro_locatario.NOME_LOCATARIO,CADASTRO_LIVRO.NOMELIVRO  , CADASTRO_LIVRO.IDLIVRO, REGISTRO_LIVRO. * FROM REGISTRO_LIVRO,  cadastro_locatario, CADASTRO_LIVRO WHERE REGISTRO_LIVRO.idlivro = CADASTRO_LIVRO.idlivro AND REGISTRO_LIVRO.idlocatario = cadastro_locatario.idlocatario AND data_emprestimo BETWEEN ? AND ? AND REGISTRO_LIVRO.TIPO_LOCATARIO = 'funcionario' and registro_livro.status != 'devolvido'";

            pstm = bd.conectar().prepareStatement(consultaPorDataFuncionario);
            pstm.setDate(1, DInicial);
            pstm.setDate(2, DFinal);
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

                rb.setIdregistrolivro(rs.getInt("idregistrolivro"));
                registrolivro.add(rb);
            }

        } catch (SQLException ex) {
            Logger.getLogger(registroLivroControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registrolivro;
    }

    public List<registroLivroBean> listarDevolucaoOutro(Date DInicial, Date DFinal) {
        List<registroLivroBean> registrolivro = new ArrayList();
        try {


            String consultaPorDataOutro = "SELECT cadastro_locatario.NOME_LOCATARIO,CADASTRO_LIVRO.NOMELIVRO , CADASTRO_LIVRO.IDLIVRO, REGISTRO_LIVRO. * FROM REGISTRO_LIVRO,  cadastro_locatario, CADASTRO_LIVRO WHERE REGISTRO_LIVRO.idlivro = CADASTRO_LIVRO.idlivro AND REGISTRO_LIVRO.idlocatario = cadastro_locatario.idlocatario AND data_emprestimo BETWEEN ? AND ? AND REGISTRO_LIVRO.TIPO_LOCATARIO = 'outro' and registro_livro.status != 'devolvido'";

            pstm = bd.conectar().prepareStatement(consultaPorDataOutro);
            pstm.setDate(1, DInicial);
            pstm.setDate(2, DFinal);
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
                rb.setIdregistrolivro(rs.getInt("idregistrolivro"));
                registrolivro.add(rb);
            }

        } catch (SQLException ex) {
            Logger.getLogger(registroLivroControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registrolivro;
    }
    
    //WHERE IDREGISTROLIVRO = ?
   
}