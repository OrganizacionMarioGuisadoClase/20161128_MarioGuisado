package es.albarregas.dao;

import es.albarregas.beans.Alumno;
import es.albarregas.beans.Equipo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlumnosDAO implements IAlumnosDAO {

    private PreparedStatement preparada = null;
    private ResultSet resultado = null;

    @Override
    public void addAlumno(Alumno a) {

        try {
            preparada = ConnectionFactory.getConnection().prepareStatement("INSERT INTO alumnos VALUES (0,?,?,null);");
            preparada.setString(1, a.getNombre());
            preparada.setString(2, a.getGrupo());
            preparada.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al crear sentencia");
            ex.printStackTrace();
        }

        try {
            if (preparada != null) {
                preparada.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if (resultado != null) {
                resultado.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Alumno> getAlumnos(String where) {

        Alumno alumno;
        ArrayList<Alumno> listado = new ArrayList();

        try {
            preparada = ConnectionFactory.getConnection().prepareStatement("SELECT * FROM alumnos " + where + ";");
            resultado = preparada.executeQuery();
            while (resultado.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(resultado.getInt("idAlumno"));
                alumno.setNombre(resultado.getString("nombre"));
                alumno.setGrupo(resultado.getString("grupo"));
                listado.add(alumno);
            }
        } catch (SQLException ex) {
            System.out.println("Error al crear sentencia");
            ex.printStackTrace();
        }

        try {
            if (preparada != null) {
                preparada.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if (resultado != null) {
                resultado.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listado;
    }

    @Override
    public ArrayList<Alumno> getAlumnosEquipo() {
        Alumno alumno;
        Equipo equipo;
        ArrayList<Alumno> listado = new ArrayList();

        try {
            preparada = ConnectionFactory.getConnection().prepareStatement("SELECT a.nombre,a.grupo,e.marca FROM alumnos AS a LEFT JOIN equipos AS e ON a.idEquipo = e.idEquipo ;");
            resultado = preparada.executeQuery();
            while (resultado.next()) {
                equipo = new Equipo();
                equipo.setMarca(resultado.getString("marca"));
                alumno = new Alumno();
                alumno.setNombre(resultado.getString("nombre"));
                alumno.setGrupo(resultado.getString("grupo"));
                alumno.setEquipo(equipo);
                listado.add(alumno);
            }
        } catch (SQLException ex) {
            System.out.println("Error al crear sentencia");
            ex.printStackTrace();
        }

        try {
            if (preparada != null) {
                preparada.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if (resultado != null) {
                resultado.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listado;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
