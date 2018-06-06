package br.com.sisbiblioteca.cadastroLivro;

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
 * @author Nalia Fernandes
 */
public class livroControl {

    cadastroLivro l;
    PreparedStatement pstm;
    ResultSet rs;
    acessoMysql bd = new acessoMysql();
    String cadastroLivro = "INSERT INTO CADASTRO_LIVRO (NOMELIVRO, AUTOR, CATEGORIA, ANOPUBLICACAO, EDITORA, QUANTIDADE, EDICAO, GENERO, CLASSIFICACAOETARIA, VOLUME, STATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    String consultaLivroAutor = "SELECT * FROM CADASTRO_LIVRO WHERE AUTOR LIKE ?";
    String consultaLivroCategoria = "SELECT * FROM CADASTRO_LIVRO WHERE CATEGORIA LIKE ?";
    String consultaLivroGenero = "SELECT * FROM CADASTRO_LIVRO WHERE GENERO LIKE ?";
    String consultaLivroEditora = "SELECT * FROM CADASTRO_LIVRO WHERE EDITORA LIKE ?";
    
    String controleQuantidade = "UPDATE cadastro_livro SET quantidade = ? WHERE idlivro = ?";
    String consultaLivro = "SELECT nomelivro, COUNT( * ) FROM cadastro_livro WHERE nomelivro LIKE  ? AND STATUS =  'disponivel' GROUP BY nomelivro";

    //Cadastro
    public void CadastroLivro(livroBean ab) {
        try {
            pstm = bd.conectar().prepareStatement(cadastroLivro);

            pstm.setString(1, ab.getNomelivro());
            pstm.setString(2, ab.getAutor());
            pstm.setString(3, ab.getCategoria());
            pstm.setInt(4, ab.getAnopublicacao());
            pstm.setString(5, ab.getEditora());
            pstm.setInt(6, ab.getQuantidade());
            pstm.setInt(7, ab.getEdicao());
            pstm.setString(8, ab.getGenero());
            pstm.setInt(9, ab.getClassificacaoEtaria());
            pstm.setInt(10, ab.getVolume());
            pstm.setString(11, ab.getStatus());


            pstm.executeUpdate();
            bd.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(livroControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Consulta Por Nome
    public List<livroBean> ListarLivros(String nome_livro) {
        List<livroBean> Livros = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consultaLivroAutor);
            pstm.setString(1, nome_livro);
            rs = pstm.executeQuery();
            livroBean cb;

            while (rs.next()) {
                cb = new livroBean();
                cb.setIdlivro(rs.getInt("idlivro"));
                cb.setNomelivro(rs.getString("nomelivro"));
                cb.setAutor(rs.getString("autor"));
                cb.setCategoria(rs.getString("categoria"));
                cb.setAnopublicacao(rs.getInt("anopublicacao"));
                cb.setEditora(rs.getString("editora"));
                cb.setQuantidade(rs.getInt("quantidade"));
                cb.setEdicao(rs.getInt("edicao"));
                cb.setGenero(rs.getString("genero"));
                cb.setClassificacaoEtaria(rs.getInt("classificacaoetaria"));
                cb.setVolume(rs.getInt("volume"));

                Livros.add(cb);

            }

        } catch (SQLException ex) {
            Logger.getLogger(livroControl.class.getName()).log(Level.SEVERE, null, ex);
        }



        return Livros;
    }

    //Consulta Por Autor
    public List<livroBean> ListarLivrosAutor(String autor_livro) {
        List<livroBean> Livros = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consultaLivroAutor);
            pstm.setString(1, autor_livro);
            rs = pstm.executeQuery();
            livroBean cb;

            while (rs.next()) {
                cb = new livroBean();
                cb.setIdlivro(rs.getInt("idlivro"));
                cb.setNomelivro(rs.getString("nomelivro"));
                cb.setAutor(rs.getString("autor"));
                cb.setCategoria(rs.getString("categoria"));
                cb.setAnopublicacao(rs.getInt("anopublicacao"));
                cb.setEditora(rs.getString("editora"));
                cb.setQuantidade(rs.getInt("quantidade"));
                cb.setEdicao(rs.getInt("edicao"));
                cb.setGenero(rs.getString("genero"));
                cb.setClassificacaoEtaria(rs.getInt("classificacaoetaria"));
                cb.setVolume(rs.getInt("volume"));

                Livros.add(cb);

            }

        } catch (SQLException ex) {
            Logger.getLogger(livroControl.class.getName()).log(Level.SEVERE, null, ex);
        }



        return Livros;
    }
    //Consulta Por Categoria

    public List<livroBean> ListarLivrosCategoria(String categoria_livro) {
        List<livroBean> Livros = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consultaLivroCategoria);
            pstm.setString(1, categoria_livro);
            rs = pstm.executeQuery();
            livroBean cb;

            while (rs.next()) {
                cb = new livroBean();
                cb.setIdlivro(rs.getInt("idlivro"));
                cb.setNomelivro(rs.getString("nomelivro"));
                cb.setAutor(rs.getString("autor"));
                cb.setCategoria(rs.getString("categoria"));
                cb.setAnopublicacao(rs.getInt("anopublicacao"));
                cb.setEditora(rs.getString("editora"));
                cb.setQuantidade(rs.getInt("quantidade"));
                cb.setEdicao(rs.getInt("edicao"));
                cb.setGenero(rs.getString("genero"));
                cb.setClassificacaoEtaria(rs.getInt("classificacaoetaria"));
                cb.setVolume(rs.getInt("volume"));


                Livros.add(cb);

            }

        } catch (SQLException ex) {
            Logger.getLogger(livroControl.class.getName()).log(Level.SEVERE, null, ex);
        }



        return Livros;
    }
    //Consulta Por Genero

    public List<livroBean> ListarLivrosGenero(String genero_livro) {
        List<livroBean> Livros = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consultaLivroGenero);
            pstm.setString(1, genero_livro);
            rs = pstm.executeQuery();
            livroBean cb;

            while (rs.next()) {
                cb = new livroBean();
                cb.setIdlivro(rs.getInt("idlivro"));
                cb.setNomelivro(rs.getString("nomelivro"));
                cb.setAutor(rs.getString("autor"));
                cb.setCategoria(rs.getString("categoria"));
                cb.setAnopublicacao(rs.getInt("anopublicacao"));
                cb.setEditora(rs.getString("editora"));
                cb.setQuantidade(rs.getInt("quantidade"));
                cb.setEdicao(rs.getInt("edicao"));
                cb.setGenero(rs.getString("genero"));
                cb.setClassificacaoEtaria(rs.getInt("classificacaoetaria"));
                cb.setVolume(rs.getInt("volume"));


                Livros.add(cb);

            }

        } catch (SQLException ex) {
            Logger.getLogger(livroControl.class.getName()).log(Level.SEVERE, null, ex);
        }



        return Livros;
    }
    //Consulta Por Editora

    public List<livroBean> ListarLivrosEditora(String editora_livro) {
        List<livroBean> Livros = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consultaLivroEditora);
            pstm.setString(1, editora_livro);
            rs = pstm.executeQuery();
            livroBean cb;

            while (rs.next()) {
                cb = new livroBean();
                cb.setIdlivro(rs.getInt("idlivro"));
                cb.setNomelivro(rs.getString("nomelivro"));
                cb.setAutor(rs.getString("autor"));
                cb.setCategoria(rs.getString("categoria"));
                cb.setAnopublicacao(rs.getInt("anopublicacao"));
                cb.setEditora(rs.getString("editora"));
                cb.setQuantidade(rs.getInt("quantidade"));
                cb.setEdicao(rs.getInt("edicao"));
                cb.setGenero(rs.getString("genero"));
                cb.setClassificacaoEtaria(rs.getInt("classificacaoetaria"));
                cb.setVolume(rs.getInt("volume"));


                Livros.add(cb);

            }

        } catch (SQLException ex) {
            Logger.getLogger(livroControl.class.getName()).log(Level.SEVERE, null, ex);
        }



        return Livros;
    }

    //Excluir Livro
    public void excluirLivro() {
        String excluirLivro = "DELETE FROM CADASTRO_LIVRO WHERE NOMELIVRO = '" + l.nomeLivro + "' AND AUTOR = '" + l.nomeAutor + "' AND CATEGORIA = '" + l.categoria + "' AND ANOPUBLICACAO = '" + l.anopublicacao + "' AND VOLUME = '" + l.volume + "'";
        try {
            pstm = bd.conectar().prepareStatement(excluirLivro);
           
            pstm.executeUpdate();
            bd.desconectar();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Alterar
    public void alterarLivro(livroBean cb) {

        String alterarLivro = "UPDATE CADASTRO_LIVRO SET NOMELIVRO = ?, AUTOR = ?, CATEGORIA = ?, ANOPUBLICACAO = ?, EDITORA = ?, QUANTIDADE = ?, EDICAO = ?, GENERO = ?, CLASSIFICACAOETARIA = ?, VOLUME = ?  WHERE NOMELIVRO = '" + l.nomeLivro + "' AND AUTOR = '" + l.nomeAutor + "' AND CATEGORIA = '" + l.categoria + "' AND ANOPUBLICACAO = '" + l.anopublicacao + "' AND VOLUME = '" + l.volume + "'";

        try {

            pstm = bd.conectar().prepareStatement(alterarLivro);
            pstm.setString(1, cb.getNomelivro());
            pstm.setString(2, cb.getAutor());
            pstm.setString(3, cb.getCategoria());
            pstm.setInt(4, cb.getAnopublicacao());
            pstm.setString(5, cb.getEditora());
            pstm.setInt(6, cb.getQuantidade());
            pstm.setInt(7, cb.getEdicao());
            pstm.setString(8, cb.getGenero());
            pstm.setInt(9, cb.getClassificacaoEtaria());
            pstm.setInt(10, cb.getVolume());

            pstm.executeUpdate();
            bd.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(livroControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //Controle de quantidade

    public void Controlequantidade(livroBean cb) {
        try {
            pstm = bd.conectar().prepareStatement(controleQuantidade);
            pstm.setInt(1, cb.getIdlivro());
            pstm.setInt(2, cb.getQuantidade());
            pstm.executeUpdate();
            bd.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(livroControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
