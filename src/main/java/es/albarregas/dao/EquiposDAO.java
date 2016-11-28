package es.albarregas.dao;

import es.albarregas.beans.Equipo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class EquiposDAO implements IEquiposDAO {
    
    private PreparedStatement preparada = null;
    private ResultSet resultado = null;

    @Override
    public void addEquipo(Equipo e) {
         try {
            preparada = ConnectionFactory.getConnection().prepareStatement("INSERT INTO equipos VALUES (0,?,?);");
            preparada.setString(1, e.getMarca());
            preparada.setString(2, e.getNumSerie());
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
    public ArrayList<Equipo> getEquipos(String where) {
        Equipo equipo;
        ArrayList<Equipo> listado = new ArrayList();
        
        try {
            preparada = ConnectionFactory.getConnection().prepareStatement("SELECT * FROM equipos " + where + ";");
            resultado = preparada.executeQuery();
            while (resultado.next()) {
                equipo = new Equipo();
                equipo.setIdEquipo(resultado.getInt("idEquipo"));
                equipo.setMarca(resultado.getString("marca"));
                equipo.setNumSerie(resultado.getString("numSerie"));
                listado.add(equipo);
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
