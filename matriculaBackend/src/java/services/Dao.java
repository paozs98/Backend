package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.AlumnoModel;
import models.GrupoModel;
import models.MatriculaModel;
import models.ProfesorModel;

import models.UsuarioModel;

public class Dao {

    private RealDataBase db;

    public Dao() {
        db = new RealDataBase();
    }

    public void addUsuario(UsuarioModel u) throws Exception {
        String sql = "CALL INSERTAR_USUARIO('%s', '%s')";
        sql = String.format(sql, u.getCedula(), u.getContrasena());

        if (db.executeUpdate(sql) == 0) {
            System.out.println("Usuario ya existe");
        }
    }
    
    public ArrayList<UsuarioModel> searchAllUsuarios() throws Exception {
        String sql = "select * from usuario";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<UsuarioModel> usuarios = new ArrayList<UsuarioModel>();

        while (rs.next()) {
            usuarios.add(new UsuarioModel(rs.getString("correo"), rs.getString("contrasena")));
        }

        return usuarios;
    }
    
    public UsuarioModel getUsuario(String cedula, String contrasena) throws Exception {
        String sql = "CALL BUSCAR_USUARIO('%s','%s')";
        sql = String.format(sql, cedula, contrasena);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new UsuarioModel(rs.getString("cedula"), rs.getString("clave"));
        } else {
            System.out.println("Usuario no existe");
            return null;
        }
    }

    public ArrayList<GrupoModel> getGruposPorProfesor(String cedula) throws SQLException {
        String sql = "CALL LISTAR_GRUPO_PROFESOR('%s')";
        sql = String.format(sql, cedula);
        ResultSet rs = db.executeQuery(sql);

        ArrayList<GrupoModel> grupos = new ArrayList<GrupoModel>();

        while (rs.next()) {
            grupos.add(new GrupoModel(rs.getString("numero_grupo"), rs.getString("no_ciclo"), rs.getString("codigo_curso"), rs.getString("cedula_profesor"), rs.getString("horario")));
        }
        return grupos;
    }

     public ArrayList<MatriculaModel> getAlumnosPorIdGrupo(String id_grupo) throws SQLException, Exception {
        String sql = "CALL LISTAR_ALUMNOS_GRUPO('%s')";
        sql = String.format(sql, id_grupo);
        ResultSet rs = db.executeQuery(sql);

        ArrayList<MatriculaModel> matriculas = new ArrayList<MatriculaModel>();

        while (rs.next()) {
            matriculas.add(new MatriculaModel(rs.getString("no_matricula"), this.getAlumnoPorCedula(rs.getString("cedula_alumno")), this.getGrupoPorNumero(id_grupo), rs.getFloat("nota")));
        }
        return matriculas;
    }

     public AlumnoModel getAlumnoPorCedula(String cedula) throws Exception {
        String sql = "CALL BUSCAR_ALUMNO('%s')";
        sql = String.format(sql, cedula);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new AlumnoModel(rs.getString("cedula_alumno"), rs.getString("nombre"), rs.getString("telefono"), rs.getString("email"), rs.getString("fecha_nacimiento"), rs.getString("carrera"));
        } else {
            System.out.println("Alumno no existe");
            return null;
        }
    }

     public GrupoModel getGrupoPorNumero(String numero) throws Exception {
        String sql = "CALL BUSCAR_GRUPO('%s')";
        sql = String.format(sql, numero);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new GrupoModel(rs.getString("numero_grupo"), rs.getString("no_ciclo"), rs.getString("codigo_curso"), rs.getString("cedula_profesor"), rs.getString("horario"));
        } else {
            System.out.println("Grupo no existe");
            return null;
        }
    }

     public ProfesorModel getProfesorPorCedula(String cedula) throws Exception {
        String sql = "CALL BUSCAR_PROFESOR('%s')";
        sql = String.format(sql, cedula);
        ResultSet rs = db.executeQuery(sql);

        if (rs.next()) {
            return new ProfesorModel(rs.getString("cedula_profesor"), rs.getString("nombre"), rs.getString("telefono"), rs.getString("email"));
        } else {
            System.out.println("Profesor no existe");
            return null;
        }
    }

     public boolean setNotaPorMatricula(String no_matricula, float nota) throws Exception {
        String sql = "CALL REGISTRO_NOTA('%s', %f)";
        sql = String.format(sql, no_matricula, nota);

        if (db.executeUpdate(sql) == 0) {
            return false;
        }
        return true;

    }

}
