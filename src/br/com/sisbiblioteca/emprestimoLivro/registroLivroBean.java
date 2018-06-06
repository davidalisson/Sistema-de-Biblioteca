package br.com.sisbiblioteca.emprestimoLivro;

import java.sql.Date;

/**
 *
 * @author Nalia Fernandes
 */
public class registroLivroBean {
    private Integer idregistrolivro;
    private Integer idlocatario;
    private Integer idlivro;
    private Date data_emprestimo;
    private Date data_prevista_devolucao;
    private String hora_emprestimo;
    private String status;
    private Integer quantidade;
    private String tipo_locatario;
    private String statusEmp;
    
    private String statusL;
    private String nome_locatario;
    private String nome_livro;
    private Integer codigoLivro;
    private Date data_dev_emp;
    /**
     * @return the idregistrolivro
     */
    public Integer getIdregistrolivro() {
        return idregistrolivro;
    }

    /**
     * @param idregistrolivro the idregistrolivro to set
     */
    public void setIdregistrolivro(Integer idregistrolivro) {
        this.idregistrolivro = idregistrolivro;
    }

    /**
     * @return the idlocatario
     */
    public Integer getIdlocatario() {
        return idlocatario;
    }

    /**
     * @param idlocatario the idlocatario to set
     */
    public void setIdlocatario(Integer idlocatario) {
        this.idlocatario = idlocatario;
    }

    /**
     * @return the idlivro
     */
    public Integer getIdlivro() {
        return idlivro;
    }

    /**
     * @param idlivro the idlivro to set
     */
    public void setIdlivro(Integer idlivro) {
        this.idlivro = idlivro;
    }

    /**
     * @return the data_emprestimo
     */
    public Date getData_emprestimo() {
        return data_emprestimo;
    }

    /**
     * @param data_emprestimo the data_emprestimo to set
     */
    public void setData_emprestimo(Date data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    /**
     * @return the data_prevista_devolucao
     */
    public Date getData_prevista_devolucao() {
        return data_prevista_devolucao;
    }

    /**
     * @param data_prevista_devolucao the data_prevista_devolucao to set
     */
    public void setData_prevista_devolucao(Date data_prevista_devolucao) {
        this.data_prevista_devolucao = data_prevista_devolucao;
    }

    /**
     * @return the hora_emprestimo
     */
    public String getHora_emprestimo() {
        return hora_emprestimo;
    }

    /**
     * @param hora_emprestimo the hora_emprestimo to set
     */
    public void setHora_emprestimo(String hora_emprestimo) {
        this.hora_emprestimo = hora_emprestimo;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the tipo_locatario
     */
    public String getTipo_locatario() {
        return tipo_locatario;
    }

    /**
     * @param tipo_locatario the tipo_locatario to set
     */
    public void setTipo_locatario(String tipo_locatario) {
        this.tipo_locatario = tipo_locatario;
    }

    /**
     * @return the statusL
     */
    public String getStatusL() {
        return statusL;
    }

    /**
     * @param statusL the statusL to set
     */
    public void setStatusL(String statusL) {
        this.statusL = statusL;
    }

    /**
     * @return the nome_locatario
     */
    public String getNome_locatario() {
        return nome_locatario;
    }

    /**
     * @param nome_locatario the nome_locatario to set
     */
    public void setNome_locatario(String nome_locatario) {
        this.nome_locatario = nome_locatario;
    }

    /**
     * @return the nome_livro
     */
    public String getNome_livro() {
        return nome_livro;
    }

    /**
     * @param nome_livro the nome_livro to set
     */
    public void setNome_livro(String nome_livro) {
        this.nome_livro = nome_livro;
    }

    /**
     * @return the codigoLivro
     */
    public Integer getCodigoLivro() {
        return codigoLivro;
    }

    /**
     * @param codigoLivro the codigoLivro to set
     */
    public void setCodigoLivro(Integer codigoLivro) {
        this.codigoLivro = codigoLivro;
    }

    /**
     * @return the data_dev_emp
     */
    public Date getData_dev_emp() {
        return data_dev_emp;
    }

    /**
     * @param data_dev_emp the data_dev_emp to set
     */
    public void setData_dev_emp(Date data_dev_emp) {
        this.data_dev_emp = data_dev_emp;
    }

    /**
     * @return the statusEmp
     */
    public String getStatusEmp() {
        return statusEmp;
    }

    /**
     * @param statusEmp the statusEmp to set
     */
    public void setStatusEmp(String statusEmp) {
        this.statusEmp = statusEmp;
    }

}
