package br.com.sisbiblioteca.reservaLivro;

import java.sql.Date;

/**
 *
 * @author Nalia Fernandes
 */
public class reservaBean {
    private Integer idlocatario;
    private Integer idlivro;
    private Date datareserva;
    private String horareserva;
    private Date diaemprestimo;
    private Integer quantidade;
    private String nomeLivro;
    private String nomeLocatario;
    private String status;
    private String tipo_locatario;

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
     * @return the datareserva
     */
    public Date getDatareserva() {
        return datareserva;
    }

    /**
     * @param datareserva the datareserva to set
     */
    public void setDatareserva(Date datareserva) {
        this.datareserva = datareserva;
    }

    /**
     * @return the horareserva
     */
    public String getHorareserva() {
        return horareserva;
    }

    /**
     * @param horareserva the horareserva to set
     */
    public void setHorareserva(String horareserva) {
        this.horareserva = horareserva;
    }

    /**
     * @return the diaemprestimo
     */
    public Date getDiaemprestimo() {
        return diaemprestimo;
    }

    /**
     * @param diaemprestimo the diaemprestimo to set
     */
    public void setDiaemprestimo(Date diaemprestimo) {
        this.diaemprestimo = diaemprestimo;
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

   
}
