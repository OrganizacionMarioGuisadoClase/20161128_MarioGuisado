package es.albarregas.controladores;

import es.albarregas.dao.IAlumnosDAO;
import es.albarregas.dao.IEquiposDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mario
 */
@WebServlet(name = "Operacion", urlPatterns = {"/Operacion"})
public class Operacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        IAlumnosDAO adao = DAOFactory.getDAOFactory().getAlumnosDAO();
        IEquiposDAO edao = DAOFactory.getDAOFactory().getEquiposDAO();
        String salida = "";

        switch (request.getParameter("operacion")) {
            case "annadirAlumno":
                salida = "jspx/fInsertarAlumno.jspx";
                break;
            case "annadirEquipo":
                salida = "jspx/fInsertarEquipo.jspx";
                break;
            case "leerAlumnos":
                request.setAttribute("lista", adao.getAlumnos(""));
                salida = "jspx/verAlumnos.jspx";
                break;
            case "leerAlumnosEquipos":
                request.setAttribute("lista", adao.getAlumnosEquipo());
                salida = "jspx/verAlumnosEquipos.jspx";
                break;
            case "leerEquipos":
                request.setAttribute("lista", edao.getEquipos(""));
                salida = "jspx/verEquipos.jspx";
                break;
        }

        request.getRequestDispatcher(salida).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
