package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.UsuarioModel;

/*
 * @author Alessandro Fazio Pérez / Jose Alonso Alfaro Perez
 */
public class Dao {

    private RealDataBase db;

    public Dao() {
        db = new RealDataBase();
    }

    public void addUsuario(UsuarioModel u) throws Exception {
        String sql = "insert into usuario (correo, contrasena) "
                + "values('%s', '%s')";
        sql = String.format(sql, u.getCorreo(), u.getContrasena());

        if (db.executeUpdate(sql) == 0) {
            System.out.println("Usuario ya existe");
        }
    }
    
    public ArrayList<UsuarioModel> searchAllUsuarios() throws Exception {
        String sql = "select * from usuario";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<UsuarioModel> usuarios = new ArrayList<>();

        while (rs.next()) {
            usuarios.add(new UsuarioModel(rs.getString("correo"), rs.getString("contrasena")));
        }

        return usuarios;
    }
    
    public UsuarioModel getUsuario(String correo, String contrasena) throws Exception {
        String sql = "select * from usuario where correo = '%s' and contrasena = '%s'";
        sql = String.format(sql, correo, contrasena);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new UsuarioModel(rs.getString("correo"), rs.getString("contrasena"));
        } else {
            System.out.println("Usuario no existe");
            return null;
        }
    }
/*
    public void addBien(Bien b) throws Exception {
        String sql = "insert into Bien (marca, modelo, precio, descripcion, solicitud, cantidad) "
                + "values('%s', '%s', %d, '%s', %d, %d)";
        sql = String.format(sql, b.getMarca(), b.getModelo(), b.getPrecio(), b.getDescripcion(), b.getSolicitud().getCodigo(), b.getCantidad());

        if (db.executeUpdate(sql) == 0) {
            System.out.println("Bien ya existe");
        }
    }

    public void addDependencia(Dependencia d) throws Exception {
        String sql = "insert into Dependencia (id, nombre) "
                + "values('%s', '%s')";
        sql = String.format(sql, d.getId(), d.getNombre());

        if (db.executeUpdate(sql) == 0) {
            System.out.println("Dependencia ya existe");
        }
    }

    public void addSolicitud(Solicitud s) throws Exception {
        String sql = "insert into Solicitud (numero, funcionario, dependencia, estado, tipo) values(%d, '%s', '%s', 'Espera', '%s')";
        sql = String.format(sql, s.getCodigo(), s.getFuncionario().getId(), s.getDependencia().getId(), s.getTipo());

        if (db.executeUpdate(sql) == 0) {
            System.out.println("Solicitud ya existe");
        }

        for (int i = 0; i < s.getBienes().size(); i++) {
            s.getBienes().get(i).setSolicitud(s);
            this.addBien(s.getBienes().get(i));
        }
    }

    public void addActivo(Activo a) throws Exception {
        String sql = "insert into Activo (marca, modelo, precio, categoria) "
                + "values('%s', '%s', %d, '%s')";
        sql = String.format(sql, a.getMarca(), a.getModelo(), a.getPrecio(), a.getCategoria().getNombre());

        if (db.executeUpdate(sql) == 0) {
            System.out.println("Activo ya existe");
        }
    }

    public void addFuncionario(Funcionario f) throws Exception {
        String sql = "insert into Funcionario (id, nombre, puesto, dependencia) "
                + "values('%s', '%s', '%s', '%s')";
        sql = String.format(sql, f.getId(), f.getNombre(), f.getUsuario().getId(), f.getDependencia().getId());

        if (db.executeUpdate(sql) == 0) {
            System.out.println("Funcionario ya existe");
        }
    }

    public void addCategoria(Categoria c) throws Exception {
        String sql = "insert into Categoria (nombre) values('%s')";
        sql = String.format(sql, c.getNombre());

        if (db.executeUpdate(sql) == 0) {
            System.out.println("Categoria ya existe");
        }
    }

    public void deleteSolicitud(int codigo) throws Exception {
        String sql = "delete from Solicitud where numero = %d";
        sql = String.format(sql, codigo);

        if (db.executeUpdate(sql) == 0) {
            System.out.println("No se pudo borrar");
        }
    }

    public void deleteBien(int codigo) throws Exception {
        String sql = "delete from Bien where solicitud = %d";
        sql = String.format(sql, codigo);

        if (db.executeUpdate(sql) == 0) {
            System.out.println("No se pudo borrar");
        }
    }

    public void deleteUsuario(String id) throws Exception {
        String sql = "delete from Usuario where Usuario.id = '%s'";
        sql = String.format(sql, id);

        if (db.executeUpdate(sql) == 0) {
            System.out.println("No se pudo borrar");
        }
    }

    public void deleteDependencia(String id) throws Exception {
        String sql = "delete from Dependencia where Dependencia.id = '%s'";
        sql = String.format(sql, id);

        if (db.executeUpdate(sql) == 0) {
            System.out.println("No se pudo borrar");
        }
    }

    public void deleteCategoria(String nombre) throws Exception {
        String sql = "delete from Categoria where Categoria.nombre = '%s'";
        sql = String.format(sql, nombre);

        if (db.executeUpdate(sql) == 0) {
            System.out.println("No se pudo borrar");
        }
    }

    public Usuario searchUsuario(String id) throws Exception {
        String sql = "select * from Usuario u where u.id = '%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new Usuario(rs.getString("id"), rs.getString("clave"), rs.getString("permiso"));
        } else {
            System.out.println("Usuario no existe");
            return null;
        }
    }

    public boolean isSearchUsuario(String id, String clave) throws Exception {
        String sql = "select * from Usuario u where u.id = '%s' and u.clave='%s'";
        sql = String.format(sql, id, clave);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public Dependencia searchDependencia(String id) throws Exception {
        String sql = "select * from Dependencia where id = '%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new Dependencia(rs.getString("id"), rs.getString("nombre"));
        } else {
            System.out.println("Dependencia no existe");
            return null;
        }
    }

    public Categoria searchCategoria(String nombre) throws Exception {
        String sql = "select * from Categoria where nombre = '%s'";
        sql = String.format(sql, nombre);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new Categoria(rs.getString("nombre"));
        } else {
            System.out.println("Categoria no existe");
            return null;
        }
    }

    public Bien searchBien(int codigo) throws Exception {
        String sql = "select * from Bien where codigo = %d";
        sql = String.format(sql, codigo);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new Bien(rs.getInt("codigo"), rs.getString("marca"), rs.getString("modelo"), rs.getString("descripcion"), rs.getInt("cantidad"), rs.getInt("precio"), this.searchSolicitud(rs.getInt("solicitud")));
        } else {
            System.out.println("Bien no existe");
            return null;
        }
    }

    public Funcionario searchFuncionario(String id) throws Exception {
        String sql = "select * from Funcionario where id = '%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new Funcionario(rs.getString("nombre"), rs.getString("id"),
                    this.searchDependencia(rs.getString("dependencia")), this.searchUsuario(rs.getString("puesto")));
        } else {
            return null;
        }
    }

    public Solicitud searchSolicitud(int codigo) throws Exception {
        String sql = "select * from Solicitud where numero = %d";
        sql = String.format(sql, codigo);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new Solicitud(rs.getInt("numero"), rs.getDate("fecha"), this.searchFuncionario(rs.getString("funcionario")),
                    this.searchFuncionario(rs.getString("registrador")), this.searchDependencia(rs.getString("dependencia")), rs.getString("estado"), rs.getString("tipo"));

        } else {
            System.out.println("Solicitud no existe");
            return null;
        }
    }

    public ArrayList<Funcionario> searchAllRegistradores() throws Exception {
        String sql = "select * from Funcionario";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Funcionario> registradores = new ArrayList<>();

        while (rs.next()) {
            if (this.searchUsuario(rs.getString("id")).getPermiso().equals("Registrador")) {
                registradores.add(new Funcionario(rs.getString("nombre"), rs.getString("id"),
                        this.searchDependencia(rs.getString("dependencia")), this.searchUsuario(rs.getString("puesto"))));
            }
        }

        if (registradores.isEmpty()) {
            System.out.println("No existen registradores");
        }

        return registradores;
    }

    public ArrayList<Funcionario> searchAllFuncionarios() throws Exception {
        String sql = "select * from Funcionario";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        while (rs.next()) {
            funcionarios.add(new Funcionario(rs.getString("nombre"), rs.getString("id"), this.searchDependencia(rs.getString("dependencia")), this.searchUsuario(rs.getString("puesto"))));
        }

        if (funcionarios.isEmpty()) {
            System.out.println("No existen funcionarios");
        }

        return funcionarios;
    }

    public ArrayList<Activo> searchAllActivo() throws Exception {
        String sql = "select * from Activo";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Activo> activos = new ArrayList<>();

        while (rs.next()) {
            activos.add(new Activo(rs.getString("marca"), rs.getString("modelo"), rs.getInt("codigo"), rs.getInt("precio"), this.searchCategoria(rs.getString("categoria"))));
        }

        if (activos.isEmpty()) {
            System.out.println("No existen activos");
        }

        return activos;
    }

    public ArrayList<Funcionario> searchAllFuncionariosDependencia(String id) throws Exception {
        String sql = "select * from Funcionario where dependencia = '%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        while (rs.next()) {
            funcionarios.add(new Funcionario(rs.getString("nombre"), rs.getString("id"), this.searchDependencia(rs.getString("dependencia")), this.searchUsuario(rs.getString("puesto"))));
        }

        if (funcionarios.isEmpty()) {
            System.out.println("No existen funcionarios");
        }

        return funcionarios;
    }

    public ArrayList<Dependencia> searchAllDependencias() throws Exception {
        String sql = "select * from Dependencia";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Dependencia> dependencias = new ArrayList<>();

        while (rs.next()) {
            dependencias.add(new Dependencia(rs.getString("id"), rs.getString("nombre")));
        }

        return dependencias;
    }

    public ArrayList<Categoria> searchAllCategorias() throws Exception {
        String sql = "select * from Categoria";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Categoria> categorias = new ArrayList<>();

        while (rs.next()) {
            categorias.add(new Categoria(rs.getString("nombre")));
        }

        if (categorias.isEmpty()) {
            System.out.println("No existen categorias");
        }

        return categorias;
    }

    public ArrayList<Solicitud> searchSolicitudes(String objeto, String condicion) throws Exception {
        String sql = "select * from Solicitud where %s = '%s'";
        sql = String.format(sql, objeto, condicion);
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Solicitud> solicitudes = new ArrayList<>();

        while (rs.next()) {
            Solicitud s = new Solicitud(rs.getInt("numero"), rs.getDate("fecha"), this.searchFuncionario(rs.getString("funcionario")),
                    this.searchFuncionario(rs.getString("registrador")), this.searchDependencia(rs.getString("dependencia")), rs.getString("estado"), rs.getString("tipo"), this.searchAllBienes(rs.getInt("numero")));
            solicitudes.add(s);
        }

        if (solicitudes.isEmpty()) {
            return solicitudes;
        }

        return solicitudes;
    }

    public ArrayList<Solicitud> searchSolicitudesRegistrador(String objeto, String condicion, String estado) throws Exception {
        String sql = "select * from Solicitud where %s = '%s' and estado = '%s'";
        sql = String.format(sql, objeto, condicion, estado);
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Solicitud> solicitudes = new ArrayList<>();

        while (rs.next()) {
            Solicitud s = new Solicitud(rs.getInt("numero"), rs.getDate("fecha"), this.searchFuncionario(rs.getString("funcionario")),
                    this.searchFuncionario(rs.getString("registrador")), this.searchDependencia(rs.getString("dependencia")), rs.getString("estado"), rs.getString("tipo"), this.searchAllBienes(rs.getInt("numero")));
            solicitudes.add(s);
        }

        if (solicitudes.isEmpty()) {
            return solicitudes;
        }

        return solicitudes;
    }

    public ArrayList<Solicitud> searchSolicitudesAprobadas() throws Exception {
        String sql = "select * from Solicitud where estado = 'Aprobada'";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Solicitud> solicitudes = new ArrayList<>();

        while (rs.next()) {
            Solicitud s = new Solicitud(rs.getInt("numero"), rs.getDate("fecha"), this.searchFuncionario(rs.getString("funcionario")),
                    this.searchFuncionario(rs.getString("registrador")), this.searchDependencia(rs.getString("dependencia")), rs.getString("estado"), rs.getString("tipo"), this.searchAllBienes(rs.getInt("numero")));
            solicitudes.add(s);
        }

        if (solicitudes.isEmpty()) {
            System.out.println("No existen solicitudes aprobadas");
            return solicitudes;
        }

        return solicitudes;
    }

    public ArrayList<Bien> searchAllBienes(int codigo) throws Exception {
        String sql = "select * from Bien where solicitud = %d";
        sql = String.format(sql, codigo);
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Bien> bienes = new ArrayList<>();

        while (rs.next()) {
            Bien b = new Bien(rs.getInt("codigo"), rs.getString("marca"), rs.getString("modelo"), rs.getString("descripcion"), rs.getInt("cantidad"), rs.getInt("precio"), this.searchSolicitud(rs.getInt("solicitud")));
            bienes.add(b);
        }

        if (bienes.isEmpty()) {
            System.out.println("No existen bienes");
            return bienes;
        }

        return bienes;
    }

    public ArrayList<Solicitud> searchAllSolicitudes() throws Exception {
        String sql = "select * from Solicitud";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<Solicitud> solicitudes = new ArrayList<>();

        while (rs.next()) {
            Solicitud s = new Solicitud(rs.getInt("numero"), rs.getDate("fecha"), this.searchFuncionario(rs.getString("funcionario")),
                    this.searchFuncionario(rs.getString("registrador")), this.searchDependencia(rs.getString("dependencia")), rs.getString("estado"), rs.getString("tipo"), this.searchAllBienes(rs.getInt("numero")));
            solicitudes.add(s);
        }

        if (solicitudes.isEmpty()) {
            System.out.println("No existen solicitudes");
        }

        return solicitudes;
    }

    public void setEstadoSolicitud(int codigo, String estado) throws Exception {
        String sql = "update Solicitud set estado = '%s' where numero = %d";
        sql = String.format(sql, estado, codigo);

        if (db.executeUpdate(sql) == 0) {
            System.out.println("No se puede actualizar");
        }

    }

    public int ultimaSolicitud() throws Exception {
        String sql = "select numero from Solicitud where numero = (select max(numero) from Solicitud)";
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return rs.getInt("numero");
        } else {
            return 0;
        }
    }

    public void setRegistrador(int codigo, String registrador) throws Exception {
        String sql = "update Solicitud set registrador = '%s' where numero = %d";
        sql = String.format(sql, registrador, codigo);

        if (db.executeUpdate(sql) == 0) {
            System.out.println("No se pudo asignar registrador");
        }
    }

    public void setFuncionario(String id, String nombre) throws Exception {
        String sql = "update Funcionario set nombre = '%s' where id = '%s'";
        sql = String.format(sql, nombre, id);

        if (db.executeUpdate(sql) == 0) {
            System.out.println("No se pudo modificar funcionario");
        }
    }

    public void setDependenciaFuncionario(String id, String dependencia) throws Exception {
        String sql = "update Funcionario set dependencia = '%s' where id = '%s'";
        sql = String.format(sql, dependencia, id);

        if (db.executeUpdate(sql) == 0) {
            System.out.println("No se pudo modificar funcionario");
        }
    }

    public void setFuncionario(String id, String nombre, String dependencia) throws Exception {
        String sql = "update Funcionario set nombre = '%s', dependencia = '%s' where id = '%s'";
        sql = String.format(sql, nombre, dependencia, id);

        if (db.executeUpdate(sql) == 0) {
            System.out.println("No se pudo modificar funcionario");
        }
    }

    public void setDependencia(String id, String nombre) throws Exception {
        String sql = "update Dependencia set nombre = '%s' where id = '%s'";
        sql = String.format(sql, nombre, id);

        if (db.executeUpdate(sql) == 0) {
            System.out.println("No se pudo modificar funcionario");
        }
    }

    public boolean isSolicitudAceptada(int solicitud) throws SQLException {
        String sql = "select estado from Solicitud where solicitud.numero = '%d' and estado = 'aprobada'";
        sql = String.format(sql, solicitud);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }
*/
}
