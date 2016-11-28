package es.albarregas.daofactory;

import es.albarregas.dao.EquiposDAO;
import es.albarregas.dao.IAlumnosDAO;
import es.albarregas.dao.IEquiposDAO;
import es.albarregas.dao.AlumnosDAO;


public class MySQLDAOFactory extends DAOFactory{

    @Override
    public IAlumnosDAO getAlumnosDAO() {
        return new AlumnosDAO();
    }

    @Override
    public IEquiposDAO getEquiposDAO() {
        return new EquiposDAO();
    }

}