/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import controllers.AlumnoController;
import controllers.MatriculaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.AlumnoModel;
import models.MatriculaModel;
import org.json.JSONObject;

/**
 *
 * @author paoma
 */
@WebServlet(name="MatriculaServlet", urlPatterns={"/MatriculaServlet"})
public class MatriculaServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlumnoServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AlumnoServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
      response.setHeader("Access-Control-Allow-Methods", "GET");
      MatriculaController alumno = new MatriculaController();
      try {
            ArrayList<MatriculaModel> alumnos = alumno.getAlumnosMatriculadosPorGrupo(request.getParameter("id_grupo"));
        JSONObject jsonResponse = new JSONObject();
        if(alumnos == null){
          jsonResponse.put("alumnos", "null");
        } else {
          jsonResponse.put("alumnos", alumnos);
        }
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.print(jsonResponse);
        writer.flush();
        writer.close();
      } catch (Exception ex) {
        Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
      response.setHeader("Access-Control-Allow-Methods", "POST");
      MatriculaController alumno = new MatriculaController();
      try {
        boolean registro = alumno.setNotaPorMatricula(request.getParameter("no_matricula"), Float.parseFloat(request.getParameter("nota")));
        JSONObject jsonResponse = new JSONObject();
          jsonResponse.put("registro", registro);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.print(jsonResponse);
        writer.flush();
        writer.close();
      } catch (Exception ex) {
        Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
    }


    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
