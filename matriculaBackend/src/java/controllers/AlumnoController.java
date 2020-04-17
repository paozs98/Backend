/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.util.ArrayList;
import models.AlumnoModel;
import services.Dao;

/**
 *
 * @author paoma
 */
public class AlumnoController {
    private AlumnoModel modelAlumno;
    private Dao dao;

    public AlumnoController() {
        this.dao = new Dao();
    }

}
