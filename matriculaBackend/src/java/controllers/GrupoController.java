/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import models.GrupoModel;
import services.Dao;

/**
 *
 * @author paoma
 */
public class GrupoController {
    private GrupoModel modelGrupo;
    private Dao dao;

    public GrupoController() {
        this.dao = new Dao();
    }

    public ArrayList<GrupoModel> getGruposPorProfesorId(String cedula) throws Exception {
        return dao.getGruposPorProfesor(cedula);
    }
}

