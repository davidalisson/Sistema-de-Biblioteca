/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisbiblioteca.registroequipamentos;
import java.sql.Date;

/**
 *
 * @author Murilo Alves
 */
public class reservaequipamentoBean {
    private Integer idreserva;
    private Integer idlocatario;
    private Integer idequipamento;
    
    private Date dataReserva;
    private String horaReserva;
    
    private Date dataEmprestimo;
    private String horaEmprestimo;
    
    private Date dataDevolucao;
    private String horaDevolucao;
    
    private Integer quantidade;
    
    private String emprestado;
    private Integer cod_usu_equip;
    
    private String status;
    
    private String nome_loc;
    private String nome_equip;

    /**
     * @return the idreserva
     */
    public Integer getIdreserva() {
        return idreserva;
    }

    /**
     * @param idreserva the idreserva to set
     */
    public void setIdreserva(Integer idreserva) {
        this.idreserva = idreserva;
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
     * @return the idequipamento
     */
    public Integer getIdequipamento() {
        return idequipamento;
    }

    /**
     * @param idequipamento the idequipamento to set
     */
    public void setIdequipamento(Integer idequipamento) {
        this.idequipamento = idequipamento;
    }

    /**
     * @return the dataReserva
     */
    public Date getDataReserva() {
        return dataReserva;
    }

    /**
     * @param dataReserva the dataReserva to set
     */
    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    /**
     * @return the horaReserva
     */
    public String getHoraReserva() {
        return horaReserva;
    }

    /**
     * @param horaReserva the horaReserva to set
     */
    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }

    /**
     * @return the dataEmprestimo
     */
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    /**
     * @param dataEmprestimo the dataEmprestimo to set
     */
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    /**
     * @return the horaEmprestimo
     */
    public String getHoraEmprestimo() {
        return horaEmprestimo;
    }

    /**
     * @param horaEmprestimo the horaEmprestimo to set
     */
    public void setHoraEmprestimo(String horaEmprestimo) {
        this.horaEmprestimo = horaEmprestimo;
    }

    /**
     * @return the dataDevolucao
     */
    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    /**
     * @param dataDevolucao the dataDevolucao to set
     */
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * @return the horaDevolucao
     */
    public String getHoraDevolucao() {
        return horaDevolucao;
    }

    /**
     * @param horaDevolucao the horaDevolucao to set
     */
    public void setHoraDevolucao(String horaDevolucao) {
        this.horaDevolucao = horaDevolucao;
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
     * @return the emprestado
     */
    public String getEmprestado() {
        return emprestado;
    }

    /**
     * @param emprestado the emprestado to set
     */
    public void setEmprestado(String emprestado) {
        this.emprestado = emprestado;
    }

    /**
     * @return the cod_usu_equip
     */
    public Integer getCod_usu_equip() {
        return cod_usu_equip;
    }

    /**
     * @param cod_usu_equip the cod_usu_equip to set
     */
    public void setCod_usu_equip(Integer cod_usu_equip) {
        this.cod_usu_equip = cod_usu_equip;
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
     * @return the nome_loc
     */
    public String getNome_loc() {
        return nome_loc;
    }

    /**
     * @param nome_loc the nome_loc to set
     */
    public void setNome_loc(String nome_loc) {
        this.nome_loc = nome_loc;
    }

    /**
     * @return the nome_equip
     */
    public String getNome_equip() {
        return nome_equip;
    }

    /**
     * @param nome_equip the nome_equip to set
     */
    public void setNome_equip(String nome_equip) {
        this.nome_equip = nome_equip;
    }

    
}
