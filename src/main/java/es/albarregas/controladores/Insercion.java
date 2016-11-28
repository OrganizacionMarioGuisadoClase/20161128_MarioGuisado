package es.albarregas.controladores;

import es.albarregas.beans.Alumno;
import es.albarregas.beans.Equipo;
import es.albarregas.dao.IAlumnosDAO;
import es.albarregas.dao.IEquiposDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Insercion", urlPatterns = {"/Insercion"})
public class Insercion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String salida;
        request.setAttribute("insertar", request.getParameter("insertar"));
        if (request.getParameter("insertar").equals("alumno")) {
            IAlumnosDAO adao = DAOFactory.getDAOFactory().getAlumnosDAO();
            Alumno alumno = new Alumno();
            alumno.setIdAlumno(0);
            alumno.setNombre(request.getParameter("nombre"));
            alumno.setGrupo(request.getParameter("grupo"));
            adao.addAlumno(alumno);
            request.setAttribute("datos", alumno);
            salida = "/jspx/finalInsertar.jspx";
        } else {
            IEquiposDAO edao = DAOFactory.getDAOFactory().getEquiposDAO();
            Equipo equipo = new Equipo();
            equipo.setIdEquipo(0);
            equipo.setMarca(request.getParameter("marca"));
            equipo.setNumSerie(request.getParameter("numSerie"));
            edao.addEquipo(equipo);
            request.setAttribute("datos", equipo);
            salida = "/jspx/finalInsertar.jspx";
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
