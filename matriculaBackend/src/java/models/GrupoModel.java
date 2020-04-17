/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author paoma
 */
public class GrupoModel {
    private String numeroGrupo;
    private String no_ciclo;
    private String codigo_curso;
    private String cedula_profesor;
    private String horario;

    public GrupoModel(String numeroGrupo, String no_ciclo, String codigo_curso, String cedula_profesor, String horario) {
        this.numeroGrupo = numeroGrupo;
        this.no_ciclo = no_ciclo;
        this.codigo_curso = codigo_curso;
        this.cedula_profesor = cedula_profesor;
        this.horario = horario;
    }

    public String getCedula_profesor() {
        return cedula_profesor;
    }

    public void setCedula_profesor(String cedula_profesor) {
        this.cedula_profesor = cedula_profesor;
    }

    public String getCodigo_curso() {
        return codigo_curso;
    }

    public void setCodigo_curso(String codigo_curso) {
        this.codigo_curso = codigo_curso;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNo_ciclo() {
        return no_ciclo;
    }

    public void setNo_ciclo(String no_ciclo) {
        this.no_ciclo = no_ciclo;
    }

    public String getNumeroGrupo() {
        return numeroGrupo;
    }

    public void setNumeroGrupo(String numeroGrupo) {
        this.numeroGrupo = numeroGrupo;
    }

    public GrupoModel() {
    }


}
