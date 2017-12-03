package mate.discretas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mate.discretas.model.Oficina;
import mate.discretas.util.Conexion;

public class OficinaDAO{
    
    private Conexion conexion = new Conexion();
    
    public List<Oficina> getOficinas(){
        List<Oficina> oficinas = new ArrayList<>();
        Connection conn = conexion.Conectar();
        String query = "SELECT * FROM OFICINA";
        if(conn!=null){
            try{    
                PreparedStatement st = conn.prepareStatement(query);
            
                ResultSet rs= st.executeQuery();
                
                while(rs.next()){
                    Oficina oficina = new Oficina();
                    oficina.setId(rs.getInt("idOFICINA"));
                    oficina.setOficina(rs.getString("OFICINA"));
                    oficinas.add(oficina);
                }
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return oficinas;
    }
}
