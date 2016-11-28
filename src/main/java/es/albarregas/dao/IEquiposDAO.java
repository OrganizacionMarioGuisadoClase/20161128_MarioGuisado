package es.albarregas.dao;

import es.albarregas.beans.Equipo;
import java.util.ArrayList;


public interface IEquiposDAO {
    public void addEquipo(Equipo e);
    public ArrayList<Equipo> getEquipos(String where);
    public void closeConnection();
}
