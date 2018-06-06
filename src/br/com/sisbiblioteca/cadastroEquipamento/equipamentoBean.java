/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisbiblioteca.cadastroEquipamento;

/**
 *
 * @author Murilo Alves
 */
public class equipamentoBean {

    private Integer idequipamento;
    private String nomeequipamento;
    private Integer quantidade;
    private String categoria;
    private String caracteristicas;
    private String status;

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
     * @return the nomeequipamento
     */
    public String getNomeequipamento() {
        return nomeequipamento;
    }

    /**
     * @param nomeequipamento the nomeequipamento to set
     */
    public void setNomeequipamento(String nomeequipamento) {
        this.nomeequipamento = nomeequipamento;
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
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the caracteristicas
     */
    public String getCaracteristicas() {
        return caracteristicas;
    }

    /**
     * @param caracteristicas the caracteristicas to set
     */
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
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
}
