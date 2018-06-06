package br.com.sisbiblioteca.cadastrolocatario;

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
public class cadastrolocatarioControl {

    PreparedStatement pstm;
    ResultSet rs;
    acessoMysql bd = new acessoMysql();

    String cadastro_locatario_aluno ="INSERT INTO cadastro_locatario(NOME_LOCATARIO, DATA_NASCIMENTO,SEXO, CELULAR, CPF, RG, EMAIL, ENDERECO, BAIRRO, CARGO, DATA_CADASTRO, HORA_CADASTRO, TIPO_LOCATARIO, SERIE, TURMA, NMATRICULA, ESCOLA, CIDADE, CEP, UF) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String consulta_locatario_aluno ="SELECT * FROM cadastro_locatario WHERE IDLOCATARIO LIKE ? and tipo_locatario = 'aluno'";
    String alterar_locatario_aluno = "UPDATE cadastro_locatario SET NOME_LOCATARIO =?, DATA_NASCIMENTO = ?, SEXO = ?, CELULAR = ?, CPF = ?, RG = ?, EMAIL = ?, ENDERECO = ?, BAIRRO = ?, CARGO = ?, DATA_CADASTRO = ?, HORA_CADASTRO = ?, SERIE = ?, TURMA = ?, NMATRICULA = ?, ESCOLA = ?, CIDADE = ?, CEP = ?, UF = ? WHERE IDLOCATARIO = ?";
    String excluir_locatario_aluno = "DELETE FROM cadastro_locatario WHERE IDLOCATARIO = ?";

    String cadastro_locatario_funcionario ="INSERT INTO cadastro_locatario(NOME_LOCATARIO, DATA_NASCIMENTO, SEXO, CELULAR, CPF, RG, EMAIL, ENDERECO, BAIRRO, CARGO, DATA_CADASTRO, HORA_CADASTRO, TIPO_LOCATARIO, SERIE, TURMA, NMATRICULA, ESCOLA, CIDADE, CEP, UF) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String consulta_locatario_funcionario ="SELECT * FROM cadastro_locatario WHERE IDLOCATARIO LIKE ? and tipo_locatario = 'funcionario'";
    String alterar_locatario_funcionario = "UPDATE cadastro_locatario SET NOME_LOCATARIO = ?, DATA_NASCIMENTO = ?, SEXO = ?, CELULAR = ?, CPF = ?, RG = ?, EMAIL = ?, ENDERECO = ?, BAIRRO = ?, CARGO = ?, DATA_CADASTRO = ?, HORA_CADASTRO = ?, RESP_CADASTRO = ? WHERE IDLOCATARIO = ?";
    String excluir_locatario_funcionario = "DELETE FROM cadastro_locatario WHERE IDLOCATARIO = ?";

    String cadastro_locatario_outro ="INSERT INTO cadastro_locatario(NOME_LOCATARIO, DATA_NASCIMENTO, SEXO, CELULAR, CPF, RG, EMAIL, ENDERECO, BAIRRO, CARGO, DATA_CADASTRO, HORA_CADASTRO, TIPO_LOCATARIO, SERIE, TURMA, NMATRICULA, ESCOLA, CIDADE, CEP, UF) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String consulta_locatario_outro ="SELECT * FROM cadastro_locatario WHERE IDLOCATARIO LIKE ? and tipo_locatario = 'outro'";
    String alterar_locatario_outro = "UPDATE cadastro_locatario SET NOME_LOCATARIO = ?, DATA_NASCIMENTO = ?, SEXO = ?, CELULAR = ?, CPF = ?, RG = ?, EMAIL = ?, ENDERECO = ?, BAIRRO = ?, CIDADE = ?, CEP = ?, UF = ?, DATA_CADASTRO = ?, HORA_CADASTRO = ?, RESP_CADASTRO = ? WHERE IDLOCATARIO = ?";
    String excluir_locatario_outro = "DELETE FROM cadastro_locatario WHERE IDLOCATARIO = ?";

    
    public void cadastro_locatario_aluno(cadastrolocatarioBean ab) {
        try {
            pstm = bd.conectar().prepareStatement(cadastro_locatario_aluno);
            pstm.setString(1, ab.getNome_locatario());
            pstm.setDate(2, ab.getData_nascimento());
            pstm.setString(3, ab.getSexo());
            pstm.setString(4, ab.getCelular());
            pstm.setString(5, ab.getCpf());
            pstm.setString(6, ab.getRg());
            pstm.setString(7, ab.getEmail());
            pstm.setString(8, ab.getEndereco());
            pstm.setString(9, ab.getBairro());
            pstm.setString(10, "");
            pstm.setDate(11, ab.getData_cadastro());
            pstm.setString(12, ab.getHora_cadastro());
            pstm.setString(13, ab.getTipo_locatario());
           // pstm.setString(12, ab.getResp_cadastro());
            pstm.setString(14, ab.getSerie());
            pstm.setString(15, ab.getTurma());
            pstm.setInt(16, ab.getnMatricula());
            pstm.setString(17, ab.getEscola());
            pstm.setString(18, "");
            pstm.setString(19, "");
            pstm.setString(20, "");
          
            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(cadastrolocatarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public List<cadastrolocatarioBean> listar_locatario_aluno(String nome_locatario)
{
        List<cadastrolocatarioBean> aluno = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consulta_locatario_aluno);
            pstm.setString(1, nome_locatario);
            rs = pstm.executeQuery();
            cadastrolocatarioBean ab;

            while (rs.next()) {
                ab = new cadastrolocatarioBean();
                ab.setIdlocatario(rs.getInt("idlocatario"));
                ab.setNome_locatario(rs.getString("nome_locatario"));
                ab.setData_nascimento(rs.getDate("data_nascimento"));
                ab.setSexo(rs.getString("sexo"));
                ab.setCelular(rs.getString("celular"));
                ab.setCpf(rs.getString("cpf"));
                ab.setRg(rs.getString("rg"));
                ab.setEmail(rs.getString("email"));
                ab.setEndereco(rs.getString("endereco"));
                ab.setBairro(rs.getString("bairro"));
                ab.setCargo(rs.getString("cargo"));
                ab.setData_cadastro(rs.getDate("data_cadastro"));
                ab.setHora_cadastro(rs.getString("hora_cadastro"));
               // ab.setResp_cadastro(rs.getString("resp_cadastro"));
                ab.setSerie(rs.getString("serie"));
                ab.setTurma(rs.getString("turma"));
                ab.setnMatricula(rs.getInt("nMatricula"));
                ab.setEscola(rs.getString("escola"));
                ab.setCidade(rs.getString("cidade"));
                ab.setCep(rs.getString("cep"));
                ab.setUf(rs.getString("uf"));
                ab.setTipo_locatario(rs.getString("tipo_locatario"));

                aluno.add(ab);
            }

        } catch (SQLException ex) {
            Logger.getLogger(cadastrolocatarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aluno;
    }

 public void alterar_locatario_aluno(cadastrolocatarioBean ab) {
        try {
            pstm = bd.conectar().prepareStatement(alterar_locatario_aluno);
            pstm.setString(1, ab.getNome_locatario());
            pstm.setDate(2, ab.getData_nascimento());
            pstm.setString(3, ab.getSexo());
            pstm.setString(4, ab.getCelular());
            pstm.setString(5, ab.getCpf());
            pstm.setString(6, ab.getRg());
            pstm.setString(7, ab.getEmail());
            pstm.setString(8, ab.getEndereco());
            pstm.setString(9, ab.getBairro());
            pstm.setString(10, "");
            pstm.setDate(11, ab.getData_cadastro());
            pstm.setString(12, ab.getHora_cadastro());
            pstm.setString(13, ab.getTipo_locatario());
           // pstm.setString(12, ab.getResp_cadastro());
            pstm.setString(14, ab.getSerie());
            pstm.setString(15, ab.getTurma());
            pstm.setInt(16, ab.getnMatricula());
            pstm.setString(17, ab.getEscola());
            pstm.setString(18, "");
            pstm.setString(19, "");
            pstm.setString(20, "");

//            pstm.setInt(21, ab.getIdlocatario());

            pstm.executeUpdate();
            bd.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(cadastrolocatarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  public void excluir_locatario_aluno(int idAluno){
        try {
            pstm = bd.conectar().prepareStatement(excluir_locatario_aluno);
            pstm.setInt(1, idAluno);

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(cadastrolocatarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
public void cadastro_locatario_funcionario(cadastrolocatarioBean ab) {
        try {
            pstm = bd.conectar().prepareStatement(cadastro_locatario_funcionario);
            pstm.setString(1, ab.getNome_locatario());
            pstm.setDate(2, ab.getData_nascimento());
            pstm.setString(3, ab.getSexo());
            pstm.setString(4, ab.getCelular());
            pstm.setString(5, ab.getCpf());
            pstm.setString(6, ab.getRg());
            pstm.setString(7, ab.getEmail());
            pstm.setString(8, ab.getEndereco());
            pstm.setString(9, ab.getBairro());
            pstm.setString(10, ab.getCargo());
            pstm.setDate(11, ab.getData_cadastro());
            pstm.setString(12, ab.getHora_cadastro());
           // pstm.setString(13, ab.getResp_cadastro());
            pstm.setString(13, ab.getTipo_locatario());
            pstm.setInt(14, 0);
            pstm.setString(15, "");
            pstm.setInt(16, 0);
            pstm.setString(17, "");
            pstm.setString(18, "");
            pstm.setInt(19, 0);
            pstm.setString(20, "");

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(cadastrolocatarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<cadastrolocatarioBean> listar_locatario_funcionario(String nome_locatario) {
        List<cadastrolocatarioBean> funcionario = new ArrayList();
        try {

            pstm = bd.conectar().prepareStatement(consulta_locatario_funcionario);
            pstm.setString(1, nome_locatario);
            rs = pstm.executeQuery();
            cadastrolocatarioBean ab;

            while (rs.next()) {
                ab = new cadastrolocatarioBean();
                ab.setIdlocatario(rs.getInt("idlocatario"));
                ab.setNome_locatario(rs.getString("nome_locatario"));
                ab.setData_nascimento(rs.getDate("data_nascimento"));
                ab.setSexo(rs.getString("sexo"));
                ab.setCelular(rs.getString("celular"));
                ab.setCpf(rs.getString("cpf"));
                ab.setRg(rs.getString("rg"));
                ab.setEmail(rs.getString("email"));
                ab.setEndereco(rs.getString("endereco"));
                ab.setBairro(rs.getString("bairro"));
                ab.setCargo(rs.getString("cargo"));
                ab.setData_cadastro(rs.getDate("data_cadastro"));
                ab.setHora_cadastro(rs.getString("hora_cadastro"));
              //  ab.setResp_cadastro(rs.getString("resp_cadastro"));
                ab.setTipo_locatario(rs.getString("tipo_locatario"));
                ab.setTipo_locatario(rs.getString("tipo_locatario"));
                ab.setSerie(rs.getString("serie"));
                ab.setTurma(rs.getString("turma"));
                ab.setnMatricula(rs.getInt("nmatricula"));
                ab.setEscola(rs.getString("escola"));
                ab.setCidade(rs.getString("cidade"));
                ab.setCep(rs.getString("cep"));
                ab.setUf(rs.getString("uf"));
                ab.setTipo_locatario(rs.getString("tipo_locatario"));
               
                funcionario.add(ab);
            }

        } catch (SQLException ex) {
            Logger.getLogger(cadastrolocatarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionario;
    }
 public void alterar_locatario_funcionario(cadastrolocatarioBean ab) {
        try {
            pstm = bd.conectar().prepareStatement(alterar_locatario_funcionario);
           pstm.setString(1, ab.getNome_locatario());
            pstm.setDate(2, ab.getData_nascimento());
            pstm.setString(3, ab.getSexo());
            pstm.setString(4, ab.getCelular());
            pstm.setString(5, ab.getCpf());
            pstm.setString(6, ab.getRg());
            pstm.setString(7, ab.getEmail());
            pstm.setString(8, ab.getEndereco());
            pstm.setString(9, ab.getBairro());
            pstm.setString(10, ab.getCargo());
            pstm.setDate(11, ab.getData_cadastro());
            pstm.setString(12, ab.getHora_cadastro());
           // pstm.setString(13, ab.getResp_cadastro());
            pstm.setString(13, ab.getTipo_locatario());
            pstm.setInt(14, 0);
            pstm.setString(15, "");
            pstm.setInt(16, 0);
            pstm.setString(17, "");
            pstm.setString(18, "");
            pstm.setInt(19, 0);
            pstm.setString(20, "");

            pstm.setInt(14, ab.getIdlocatario());

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(cadastrolocatarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void excluir_locatario_funcionario(int idFuncionario){
        try {
            pstm = bd.conectar().prepareStatement(excluir_locatario_funcionario);
            pstm.setInt(1, idFuncionario);

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(cadastrolocatarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
public void cadastro_locatario_outro(cadastrolocatarioBean ab) {
       

    try {
            pstm = bd.conectar().prepareStatement(cadastro_locatario_outro);
            pstm.setString(1, ab.getNome_locatario());
            pstm.setDate(2, ab.getData_nascimento());
            pstm.setString(3, ab.getSexo());
            pstm.setString(4, ab.getCelular());
            pstm.setString(5, ab.getCpf());
            pstm.setString(6, ab.getRg());
            pstm.setString(7, ab.getEmail());
            pstm.setString(8, ab.getEndereco());
            pstm.setString(9, ab.getBairro());
            pstm.setString(10, "");
            pstm.setDate(11, ab.getData_cadastro());
            pstm.setString(12, ab.getHora_cadastro());
            pstm.setString(13, ab.getTipo_locatario());
            pstm.setInt(14, 0);
            pstm.setString(15, "");
            pstm.setInt(16, 0);
              pstm.setString(17, "");
            pstm.setString(18, ab.getCidade());
            pstm.setString(19, ab.getCep());
            pstm.setString(20, ab.getUf());
            // pstm.setString(15, ab.getResp_cadastro());
            
      
          
            
            

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(cadastrolocatarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<cadastrolocatarioBean> listar_locatario_outro(String nome_locatario) {
        List<cadastrolocatarioBean> outro = new ArrayList();
        try {
            pstm = bd.conectar().prepareStatement(consulta_locatario_outro);
            pstm.setString(1, nome_locatario);
            rs = pstm.executeQuery();
            cadastrolocatarioBean ab;

            while (rs.next()) {
                ab = new cadastrolocatarioBean();
                ab.setIdlocatario(rs.getInt("idlocatario"));
                ab.setNome_locatario(rs.getString("nome_locatario"));
                ab.setData_nascimento(rs.getDate("data_nascimento"));
                ab.setSexo(rs.getString("sexo"));
                ab.setCelular(rs.getString("celular"));
                ab.setCpf(rs.getString("cpf"));
                ab.setRg(rs.getString("rg"));
                ab.setEmail(rs.getString("email"));
                ab.setEndereco(rs.getString("endereco"));
                ab.setBairro(rs.getString("bairro"));
                ab.setCargo(rs.getString("cargo"));
                ab.setData_cadastro(rs.getDate("data_cadastro"));
                ab.setHora_cadastro(rs.getString("hora_cadastro"));
                ab.setTipo_locatario(rs.getString("tipo_locatario"));
                ab.setSerie(rs.getString("serie"));
                ab.setTurma(rs.getString("turma"));
                ab.setnMatricula(rs.getInt("nmatricula"));
                ab.setEscola(rs.getString("escola"));
               // ab.setResp_cadastro(rs.getString("resp_cadastro"));
                ab.setCidade(rs.getString("cidade"));
                ab.setCep(rs.getString("cep"));
                ab.setUf(rs.getString("uf"));
                ab.setTipo_locatario(rs.getString("tipo_locatario"));
                
                outro.add(ab);
            }

        } catch (SQLException ex) {
            Logger.getLogger(cadastrolocatarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outro;
    }
public void alterar_locatario_outro(cadastrolocatarioBean ab) {
        try {
            pstm = bd.conectar().prepareStatement(alterar_locatario_outro);
            pstm.setString(1, ab.getNome_locatario());
            pstm.setDate(2, ab.getData_nascimento());
            pstm.setString(3, ab.getSexo());
            pstm.setString(4, ab.getCelular());
            pstm.setString(5, ab.getCpf());
            pstm.setString(6, ab.getRg());
            pstm.setString(7, ab.getEmail());
            pstm.setString(8, ab.getEndereco());
            pstm.setString(9, ab.getBairro());
            pstm.setString(10, ab.getCidade());
            pstm.setString(11, ab.getCep());
            pstm.setString(12, ab.getUf());
            pstm.setDate(13, ab.getData_cadastro());
            pstm.setString(14, ab.getHora_cadastro());
            pstm.setString(15, ab.getResp_cadastro());

            pstm.setInt(16, ab.getIdlocatario());

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(cadastrolocatarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void excluir_locatario_outro(int idOutro){
        try {
            pstm = bd.conectar().prepareStatement(excluir_locatario_outro);
            pstm.setInt(1, idOutro);

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(cadastrolocatarioControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
