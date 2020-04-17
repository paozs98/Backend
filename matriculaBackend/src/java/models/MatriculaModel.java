/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author paoma
 */
public class MatriculaModel {
    private String no_matricula;
    private AlumnoModel alumno;
    private GrupoModel grupo;
    private Float nota;

    public MatriculaModel(String no_matricula, AlumnoModel alumno, GrupoModel grupo, Float nota) {
        this.no_matricula = no_matricula;
        this.alumno = alumno;
        this.grupo = grupo;
        this.nota = nota;
    }

    public AlumnoModel getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoModel alumno) {
        this.alumno = alumno;
    }

    public GrupoModel getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoModel grupo) {
        this.grupo = grupo;
    }

    public String getNo_matricula() {
        return no_matricula;
    }

    public void setNo_matricula(String no_matricula) {
        this.no_matricula = no_matricula;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }


}
