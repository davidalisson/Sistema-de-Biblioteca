package br.com.sisbiblioteca.reservaLivro;

import br.com.sisbiblioteca.acessoMysql.acessoMysql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nalia Fernandes
 */
public class reservaControl {
    PreparedStatement pstm;
    ResultSet rs;
    acessoMysql bd = new acessoMysql();

    String reservaLivroAluno = "INSERT INTO reservar_livro (idlocatario, idlivro, datareserva, horareserva, diaemprestimo, quantidade, tipo_locatario) VALUES (?,?,?,?,?,?,?)";
    String reservaLivroFuncionario = "INSERT INTO reservar_livro (idlocatario, idlivro, datareserva,horareserva, diaemprestimo, quantidade, tipo_locatario) VALUES (?, ?,?,?,?,?,?)";
    String reservaLivroOutro = "INSERT INTO reservar_livro (idlocatario, idlivro, datareserva, horareserva, diaemprestimo, quantidade, tipo_locatario) VALUES (?, ?,?,?,?,?,?)";

    String consultarReservaAluno = "SELECT cadastro_locatario.nome_locatario, cadastro_livro.nomelivro, reservar_livro.datareserva, reservar_livro.horareserva, reservar_livro.diaemprestimo, reservar_livro.tipo_locatario FROM cadastro_locatario, cadastro_livro, reservar_livro"
    + " reservar_livro WHERE reservar_livro.idlocatario = cadastro_livro.idlivro AND reservar_livro.idlocatario = cadastro_locatario.idlocatario AND RESERVAR_LIVRO.TIPO_LOCATARIO = 'aluno'";

    String consultarReservaFuncionario = "SELECT cadastro_locatario.nome_locatario, cadastro_livro.nomelivro, reservar_livro.datareserva, reservar_livro.horareserva, reservar_livro.diaemprestimo, reservar_livro.tipo_locatario FROM cadastro_locatario, cadastro_livro, reservar_livro"
    + " reservar_livro WHERE reservar_livro.idlocatario = cadastro_livro.idlivro AND reservar_livro.idlocatario = cadastro_locatario.idlocatario AND RESERVAR_LIVRO.TIPO_LOCATARIO = 'funcionario'";

    String consultarReservaOutro = "SELECT cadastro_locatario.nome_locatario, cadastro_livro.nomelivro, reservar_livro.datareserva, reservar_livro.horareserva, reservar_livro.diaemprestimo, reservar_livro.tipo_locatario FROM cadastro_locatario, cadastro_livro, reservar_livro"
    + " reservar_livro WHERE reservar_livro.idlocatario = cadastro_livro.idlivro AND reservar_livro.idlocatario = cadastro_locatario.idlocatario AND RESERVAR_LIVRO.TIPO_LOCATARIO = 'outro'";

     //Efetuar Registro
       public void reservarLivroAluno(reservaBean ev){
            try{
                pstm = bd.conectar().prepareStatement(reservaLivroAluno);
                pstm.setInt(1, ev.getIdlocatario());
                pstm.setInt(2, ev.getIdlivro());
                pstm.setDate(3, ev.getDatareserva());
                pstm.setString(4, ev.getHorareserva());
                pstm.setDate(5, ev.getDiaemprestimo());
                pstm.setInt(6, ev.getQuantidade());
                pstm.setString(7, ev.getTipo_locatario());
                pstm.executeUpdate();
                bd.desconectar();

            }catch (SQLException ex) {
            Logger.getLogger(reservaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        public void reservarLivroFuncionario(reservaBean ev){
            try{
                pstm = bd.conectar().prepareStatement(reservaLivroFuncionario);
                pstm.setInt(1, ev.getIdlocatario());
                pstm.setInt(2, ev.getIdlivro());
                pstm.setDate(3, ev.getDatareserva());
                pstm.setString(4, ev.getHorareserva());
                pstm.setDate(5, ev.getDiaemprestimo());
                pstm.setInt(6, ev.getQuantidade());
                pstm.setString(7, ev.getTipo_locatario());
                pstm.executeUpdate();
                bd.desconectar();

            }catch (SQLException ex) {
            Logger.getLogger(reservaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

         public void reservarLivroOutro(reservaBean ev){
            try{
                pstm = bd.conectar().prepareStatement(reservaLivroOutro);
                pstm.setInt(1, ev.getIdlocatario());
                pstm.setInt(2, ev.getIdlivro());
                pstm.setDate(3, ev.getDatareserva());
                pstm.setDate(5, ev.getDiaemprestimo());
                pstm.setString(4, ev.getHorareserva());
                pstm.setInt(6, ev.getQuantidade());
                pstm.setString(7, ev.getTipo_locatario());
                pstm.executeUpdate();
                bd.desconectar();

            }catch (SQLException ex) {
            Logger.getLogger(reservaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

         public List<reservaBean> listarReservasAluno(String id_locatario) {
        List<reservaBean> Reservas = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consultarReservaAluno);

            rs = pstm.executeQuery();
            reservaBean cb;

            while (rs.next()) {
                cb = new reservaBean();
                cb.setNomeLivro(rs.getString("cadastro_livro.nomelivro"));
                cb.setNomeLocatario(rs.getString("cadastro_locatario.nome_locatario"));
                cb.setDiaemprestimo(rs.getDate("reservar_livro.diaemprestimo"));
                cb.setDatareserva(rs.getDate("reservar_livro.datareserva"));
                cb.setTipo_locatario(rs.getString("reservar_livro.tipo_locatario"));
                cb.setHorareserva(rs.getString("reservar_livro.horareserva"));

                Reservas.add(cb);
            }

        } catch (SQLException ex) {
            Logger.getLogger( reservaControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return  Reservas;
    }

          public List<reservaBean> listarReservasFuncionario(String id_cfuncionario) {
        List<reservaBean> ReservasFuncionario = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consultarReservaFuncionario);

            rs = pstm.executeQuery();
            reservaBean cb;

            while (rs.next()) {
                cb = new reservaBean();
                cb.setNomeLivro(rs.getString("cadastro_livro.nomelivro"));
                cb.setNomeLocatario(rs.getString("cadastro_locatario_funcionario.nome_funcionario"));
                cb.setDiaemprestimo(rs.getDate("reservar_livro.diaemprestimo"));
                cb.setDatareserva(rs.getDate("reservar_livro.datareserva"));
                cb.setTipo_locatario(rs.getString("reservar_livro.tipo_locatario"));
                cb.setHorareserva(rs.getString("reservar_livro.horareserva"));


                ReservasFuncionario.add(cb);
            }

        } catch (SQLException ex) {
            Logger.getLogger( reservaControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return  ReservasFuncionario;
    }

           public List<reservaBean> listarReservasOutro(String id_coutro) {
        List<reservaBean> ReservasOutro = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consultarReservaOutro);

            rs = pstm.executeQuery();
            reservaBean cb;

            while (rs.next()) {
                cb = new reservaBean();
                cb.setNomeLivro(rs.getString("cadastro_livro.nomelivro"));
                cb.setNomeLocatario(rs.getString("cadastro_locatario_outros.nome_outro"));
                cb.setDiaemprestimo(rs.getDate("reservar_livro.diaemprestimo"));
                cb.setDatareserva(rs.getDate("reservar_livro.datareserva"));
                cb.setTipo_locatario(rs.getString("reservar_livro.tipo_locatario"));
                cb.setHorareserva(rs.getString("reservar_livro.horareserva"));


                ReservasOutro.add(cb);
            }

        } catch (SQLException ex) {
            Logger.getLogger( reservaControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return  ReservasOutro;
    }
}

