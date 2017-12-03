package mate.discretas.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericCRUD {
    private Conexion conexion = new Conexion();
    
    public List<Map<String,Object >> getList(String query){
        
        List<Map<String, Object>> results = new ArrayList<>();
        Connection conn = conexion.Conectar();
        if(conn!=null){
            try{    
                PreparedStatement st = conn.prepareStatement(query);
                ResultSet rs= st.executeQuery();
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();
                
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columns; i++) {
                        row.put(md.getColumnLabel(i).toLowerCase(), rs.getObject(i));
                    }
                    results.add(row);
                }
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return results;
        
    }
    
    public Map<String,Object > get(String query){
        
        Map<String, Object> result = new HashMap<>();;
        Connection conn = conexion.Conectar();
        if(conn!=null){
            try{    
                PreparedStatement st = conn.prepareStatement(query);
                ResultSet rs= st.executeQuery();
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();
                
                while (rs.next()) {
                    for (int i = 1; i <= columns; i++) {
                        result.put(md.getColumnLabel(i).toLowerCase(), rs.getObject(i));
                    }
                }
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
        
    }
    
    public void sqlCUD(String query){
        Connection conn = conexion.Conectar();
        if(conn!=null){
            try {
                PreparedStatement st = conn.prepareStatement(query);
                st.executeUpdate();
                st.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
