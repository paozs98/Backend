/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import models.ProfesorModel;
import services.Dao;

/**
 *
 * @author paoma
 */
public class ProfesorController {
    private ProfesorModel modelProfesor;
    private Dao dao;

    public ProfesorController() {
        this.dao = new Dao();
    }

    public ProfesorModel getProfesorPorCedula(String cedula) throws Exception {
        return dao.getProfesorPorCedula(cedula);
    }
}
