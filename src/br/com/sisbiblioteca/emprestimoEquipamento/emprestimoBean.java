/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisbiblioteca.emprestimoEquipamento;

import java.sql.Date;

/**
 *
 * @author David
 */
public class emprestimoBean {
    private Integer cod_emp_equip;
    private Integer cod_loc_equip;
    private Integer cod_equip_equip;
    private Integer cod_usu_equip;
    private Date data_emp_equip;
    private String hora_emp_equip;
    private String hora_dev_equip;
    private Integer quant_equip;
    private Date data_prev_equip;
    private String nome_equip;
    private String nome_loc;
    private String status;
    private Date data_dev_emp;
    private String statusE;
    private String nome_func;

    /**
     * @return the cod_emp_equip
     */
    public Integer getCod_emp_equip() {
        return cod_emp_equip;
    }

    /**
     * @param cod_emp_equip the cod_emp_equip to set
     */
    public void setCod_emp_equip(Integer cod_emp_equip) {
        this.cod_emp_equip = cod_emp_equip;
    }

    /**
     * @return the cod_loc_equip
     */
    public Integer getCod_loc_equip() {
        return cod_loc_equip;
    }

    /**
     * @param cod_loc_equip the cod_loc_equip to set
     */
    public void setCod_loc_equip(Integer cod_loc_equip) {
        this.cod_loc_equip = cod_loc_equip;
    }

    /**
     * @return the cod_equip_equip
     */
    public Integer getCod_equip_equip() {
        return cod_equip_equip;
    }

    /**
     * @param cod_equip_equip the cod_equip_equip to set
     */
    public void setCod_equip_equip(Integer cod_equip_equip) {
        this.cod_equip_equip = cod_equip_equip;
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
     * @return the data_emp_equip
     */
    public Date getData_emp_equip() {
        return data_emp_equip;
    }

    /**
     * @param data_emp_equip the data_emp_equip to set
     */
    public void setData_emp_equip(Date data_emp_equip) {
        this.data_emp_equip = data_emp_equip;
    }

    /**
     * @return the hora_emp_equip
     */
    public String getHora_emp_equip() {
        return hora_emp_equip;
    }

    /**
     * @param hora_emp_equip the hora_emp_equip to set
     */
    public void setHora_emp_equip(String hora_emp_equip) {
        this.hora_emp_equip = hora_emp_equip;
    }

    /**
     * @return the hora_dev_equip
     */
    public String getHora_dev_equip() {
        return hora_dev_equip;
    }

    /**
     * @param hora_dev_equip the hora_dev_equip to set
     */
    public void setHora_dev_equip(String hora_dev_equip) {
        this.hora_dev_equip = hora_dev_equip;
    }

    /**
     * @return the quant_equip
     */
    public Integer getQuant_equip() {
        return quant_equip;
    }

    /**
     * @param quant_equip the quant_equip to set
     */
    public void setQuant_equip(Integer quant_equip) {
        this.quant_equip = quant_equip;
    }

    /**
     * @return the data_prev_equip
     */
    public Date getData_prev_equip() {
        return data_prev_equip;
    }

    /**
     * @param data_prev_equip the data_prev_equip to set
     */
    public void setData_prev_equip(Date data_prev_equip) {
        this.data_prev_equip = data_prev_equip;
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
     * @return the statusE
     */
    public String getStatusE() {
        return statusE;
    }

    /**
     * @param statusE the statusE to set
     */
    public void setStatusE(String statusE) {
        this.statusE = statusE;
    }

    /**
     * @return the nome_func
     */
    public String getNome_func() {
        return nome_func;
    }

    /**
     * @param nome_func the nome_func to set
     */
    public void setNome_func(String nome_func) {
        this.nome_func = nome_func;
    }

    
}