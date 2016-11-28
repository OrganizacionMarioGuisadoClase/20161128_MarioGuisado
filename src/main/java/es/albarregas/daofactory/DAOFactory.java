package es.albarregas.daofactory;

import es.albarregas.dao.IAlumnosDAO;
import es.albarregas.dao.IEquiposDAO;


public abstract class DAOFactory {

    public static final int MYSQL = 1;
    public static final int ORACLE = 2;
    public static final int FICHERO = 3;
    
    public abstract IAlumnosDAO getAlumnosDAO();
    public abstract IEquiposDAO getEquiposDAO();
    
    public static DAOFactory getDAOFactory(){
        
        DAOFactory daof = new MySQLDAOFactory();
        
        return daof;
    }
    
}