/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.util.ArrayList;
import models.MatriculaModel;
import services.Dao;

/**
 *
 * @author paoma
 */
public class MatriculaController {
    private MatriculaModel modelMatricula;
    private Dao dao;

    public MatriculaController() {
        this.dao = new Dao();
    }

    public ArrayList<MatriculaModel> getAlumnosMatriculadosPorGrupo(String id_grupo) throws Exception {
        return dao.getAlumnosPorIdGrupo(id_grupo);
    }
    
    public boolean setNotaPorMatricula(String no_matricula, float nota) throws Exception {
        return dao.setNotaPorMatricula(no_matricula, nota);
    }
}
