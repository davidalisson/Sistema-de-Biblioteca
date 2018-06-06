/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sisbiblioteca.devolucaoEquip;
import java.sql.Date;
/**
 *
 * @author David
 */
public class devolucaoBean {
    
    private Integer idDevolucao;
    private Integer idEmpEquipDevol;
    private Date dataDevolucao;
    private Date dInicial;
    private Date dFinal;

    /**
     * @return the idDevolucao
     */
    public Integer getIdDevolucao() {
        return idDevolucao;
    }

    /**
     * @param idDevolucao the idDevolucao to set
     */
    public void setIdDevolucao(Integer idDevolucao) {
        this.idDevolucao = idDevolucao;
    }

    /**
     * @return the idEmpEquipDevol
     */
    public Integer getIdEmpEquipDevol() {
        return idEmpEquipDevol;
    }

    /**
     * @param idEmpEquipDevol the idEmpEquipDevol to set
     */
    public void setIdEmpEquipDevol(Integer idEmpEquipDevol) {
        this.idEmpEquipDevol = idEmpEquipDevol;
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
     * @return the dInicial
     */
    public Date getdInicial() {
        return dInicial;
    }

    /**
     * @param dInicial the dInicial to set
     */
    public void setdInicial(Date dInicial) {
        this.dInicial = dInicial;
    }

    /**
     * @return the dFinal
     */
    public Date getdFinal() {
        return dFinal;
    }

    /**
     * @param dFinal the dFinal to set
     */
    public void setdFinal(Date dFinal) {
        this.dFinal = dFinal;
    }

    
   
}