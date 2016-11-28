package es.albarregas.dao;

import es.albarregas.beans.Alumno;
import java.util.ArrayList;

public interface IAlumnosDAO {
    public void addAlumno(Alumno a);
    public ArrayList<Alumno> getAlumnos(String where);
    public ArrayList<Alumno> getAlumnosEquipo();
    public void closeConnection();
}
