package br.com.sisbiblioteca.devolucaolivros;

import java.util.Date;

/**
 *
 * @author Regina Kecia
 */
public class devolucaolivrosBean {

    private Integer iddevolucaolivro;
    private Integer idregistro_livro_dev;
    private String nomeLocatario;
    private String nomeLivro;
    private Integer codigoL;
    private Integer quantidade;
    private Date data_emprestimo;
    private Date data_prevista_devolucao;
    private Date data_devolucao;
    private Date DataInicial;
    private Date DataFinal;
    
    

    /**
     * @return the iddevolucaolivro
     */
    public Integer getIddevolucaolivro() {
        return iddevolucaolivro;
    }

    /**
     * @param iddevolucaolivro the iddevolucaolivro to set
     */
    public void setIddevolucaolivro(Integer iddevolucaolivro) {
        this.iddevolucaolivro = iddevolucaolivro;
    }

    /**
     * @return the idregistro_livro_dev
     */
    public Integer getIdregistro_livro_dev() {
        return idregistro_livro_dev;
    }

    /**
     * @param idregistro_livro_dev the idregistro_livro_dev to set
     */
    public void setIdregistro_livro_dev(Integer idregistro_livro_dev) {
        this.idregistro_livro_dev = idregistro_livro_dev;
    }

    /**
     * @return the nomeLocatario
     */
    public String getNomeLocatario() {
        return nomeLocatario;
    }

    /**
     * @param nomeLocatario the nomeLocatario to set
     */
    public void setNomeLocatario(String nomeLocatario) {
        this.nomeLocatario = nomeLocatario;
    }

    /**
     * @return the nomeLivro
     */
    public String getNomeLivro() {
        return nomeLivro;
    }

    /**
     * @param nomeLivro the nomeLivro to set
     */
    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
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
     * @return the data_prev_devolucao
     */
    public Date getData_prevista_devolucao() {
        return data_prevista_devolucao;
    }

    /**
     * @param data_prev_devolucao the data_prev_devolucao to set
     */
    public void setData_prevista_devolucao(Date data_prevista_devolucao) {
        this.data_prevista_devolucao = data_prevista_devolucao;
    }

    /**
     * @return the data_devolucao
     */
    public Date getData_devolucao() {
        return data_devolucao;
    }

    /**
     * @param data_devolucao the data_devolucao to set
     */
    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    /**
     * @return the DataInicial
     */
    public Date getDataInicial() {
        return DataInicial;
    }

    /**
     * @param DataInicial the DataInicial to set
     */
    public void setDataInicial(Date DataInicial) {
        this.DataInicial = DataInicial;
    }

    /**
     * @return the DataFinal
     */
    public Date getDataFinal() {
        return DataFinal;
    }

    /**
     * @param DataFinal the DataFinal to set
     */
    public void setDataFinal(Date DataFinal) {
        this.DataFinal = DataFinal;
    }

    /**
     * @return the codigoL
     */
    public Integer getCodigoL() {
        return codigoL;
    }

    /**
     * @param codigoL the codigoL to set
     */
    public void setCodigoL(Integer codigoL) {
        this.codigoL = codigoL;
    }

}
