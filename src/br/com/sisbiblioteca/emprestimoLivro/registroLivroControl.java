package br.com.sisbiblioteca.emprestimoLivro;

import br.com.sisbiblioteca.acessoMysql.acessoMysql;
import br.com.sisbiblioteca.cadastroLivro.livroBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nalia Fernandes
 */
public class registroLivroControl {
    PreparedStatement pstm;
    ResultSet rs;
    acessoMysql bd = new acessoMysql();

    String registroLivroAluno = "INSERT INTO registro_livro (idlocatario, idlivro, data_emprestimo, data_prevista_devolucao, hora_emprestimo, quantidade, tipo_locatario) VALUES (?,?,?,?,?,?,?)";
    String registroLivroFuncionario = "INSERT INTO registro_livro (idlocatario, idlivro, data_emprestimo,hora_emprestimo, data_prevista_devolucao,  quantidade, tipo_locatario) VALUES (?,?,?,?,?,?,?)";
    String registroLivroOutro = "INSERT INTO registro_livro (idlocatario, idlivro, data_emprestimo, data_prevista_devolucao, hora_emprestimo, quantidade, tipo_locatario) VALUES (?, ?,?,?,?,?,?)";
    String alterarStatus = "UPDATE cadastro_livro SET status = ? WHERE idlivro = ?";

    public void alterarStatus(livroBean cb){
        try {
            pstm = bd.conectar().prepareStatement(alterarStatus);
            pstm.setString(1, cb.getStatus());
            pstm.setInt(2, cb.getIdlivro());
            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(registroLivroControl.class.getName()).log(Level.SEVERE, null, ex);
        }

}
    //Efetuar Registro
    public void registrarLivroAluno(registroLivroBean ev){
            try{
                pstm = bd.conectar().prepareStatement(registroLivroAluno);
                pstm.setInt(1, ev.getIdlocatario());
                pstm.setInt(2, ev.getIdlivro());
                pstm.setDate(3, ev.getData_emprestimo());
                pstm.setDate(4, ev.getData_prevista_devolucao());
                pstm.setString(5, ev.getHora_emprestimo());
                pstm.setInt(6, ev.getQuantidade());
                pstm.setString(7, ev.getTipo_locatario());
                pstm.executeUpdate();
                bd.desconectar();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
 public void registrarLivroFuncionario(registroLivroBean ev){
            try{
                pstm = bd.conectar().prepareStatement(registroLivroFuncionario);
                pstm.setInt(1, ev.getIdlocatario());
                pstm.setInt(2, ev.getIdlivro());
                pstm.setDate(3, ev.getData_emprestimo());
                pstm.setString(4, ev.getHora_emprestimo());
                pstm.setDate(5, ev.getData_prevista_devolucao());
                pstm.setInt(6, ev.getQuantidade());
                pstm.setString(7, ev.getTipo_locatario());
                pstm.executeUpdate();
                bd.desconectar();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
public void registrarLivroOutro(registroLivroBean ev){
            try{
                pstm = bd.conectar().prepareStatement(registroLivroOutro);
                pstm.setInt(1, ev.getIdlocatario());
                pstm.setInt(2, ev.getIdlivro());
                pstm.setDate(3, ev.getData_emprestimo());
                pstm.setDate(4, ev.getData_prevista_devolucao());
                pstm.setString(5, ev.getHora_emprestimo());
                pstm.setInt(6, ev.getQuantidade());
                pstm.setString(7, ev.getTipo_locatario());
                pstm.executeUpdate();
                bd.desconectar();

            }catch(Exception e){
                e.printStackTrace();
            }
        }

}
