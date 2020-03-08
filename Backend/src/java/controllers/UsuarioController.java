/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.UsuarioModel;
import services.Dao;

/**
 *
 * @author paoma
 */
public class UsuarioController {
    private UsuarioModel modelUsuario;
    private Dao dao;
    
    public UsuarioModel getUsuario(String correo, String contrasena) throws Exception {
        modelUsuario = dao.getUsuario(correo, contrasena);
        return modelUsuario;
    }
}
