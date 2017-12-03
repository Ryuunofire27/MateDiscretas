package mate.discretas.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mate.discretas.model.Incidencia;
import mate.discretas.util.GenericCRUD;

public class IncidenciaDAO {
    private GenericCRUD gcrud = new GenericCRUD();
    
    public List<Incidencia> getIncidencias(){
        
        List<Incidencia> incidencias = new ArrayList<>();
        String query = "SELECT idINCIDENCIA,USUARIO,FECHA_REVISION,FECHA_TERMINO, "
                + "enf.enfermedad enfermedad, ofi.oficina oficina, tra.tratamiento solucion, "
                + "doc.doctor doctor "
                + "FROM INCIDENCIA inc INNER JOIN ENFERMEDAD enf ON inc.idENFERMEDAD=enf.idENFERMEDAD "
                + "INNER JOIN OFICINA ofi ON inc.idOFICINA = ofi.idOFICINA "
                + "INNER JOIN TRATAMIENTO tra ON enf.idTRATAMIENTO= tra.idTRATAMIENTO "
                + "INNER JOIN DOCTOR doc ON enf.idDOCTOR = doc.idDOCTOR";
        List<Map<String, Object>> results = gcrud.getList(query);
        
        for(Map<String, Object> map: results){
            Incidencia incidencia = new Incidencia();
            incidencia.setId(Integer.parseInt(map.get("idincidencia").toString()));
            incidencia.setDoctor(map.get("doctor").toString());
            incidencia.setEnfermedad(map.get("enfermedad").toString());
            incidencia.setFechaRevision( map.get("fecha_revision").toString());
            incidencia.setFechaTermino(map.get("fecha_termino").toString());
            incidencia.setOficina(map.get("oficina").toString());
            incidencia.setSolucion(map.get("solucion").toString());
            incidencia.setUsuario(map.get("usuario").toString());
            incidencias.add(incidencia);
        }
        
        return incidencias;
        
    }
    
    public Incidencia getIncidencia(int id){
        
        Incidencia incidencia = new Incidencia();
        String query = "SELECT idINCIDENCIA,USUARIO,FECHA_REVISION,FECHA_TERMINO, "
                + "enf.enfermedad enfermedad, ofi.oficina oficina, tra.tratamiento solucion, "
                + "doc.doctor doctor "
                + "FROM INCIDENCIA inc INNER JOIN ENFERMEDAD enf ON inc.idENFERMEDAD=enf.idENFERMEDAD "
                + "INNER JOIN OFICINA ofi ON inc.idOFICINA = ofi.idOFICINA "
                + "INNER JOIN TRATAMIENTO tra ON enf.idTRATAMIENTO= tra.idTRATAMIENTO "
                + "INNER JOIN DOCTOR doc ON enf.idDOCTOR = doc.idDOCTOR "
                + "WHERE inc.idINCIDENCIA="+id;
        Map<String, Object> map = gcrud.get(query);
        
        incidencia.setId(Integer.parseInt(map.get("idincidencia").toString()));
        incidencia.setDoctor(map.get("doctor").toString());
        incidencia.setEnfermedad(map.get("enfermedad").toString());
        incidencia.setFechaRevision(map.get("fecha_revision").toString());
        incidencia.setFechaTermino(map.get("fecha_termino").toString());
        incidencia.setOficina(map.get("oficina").toString());
        incidencia.setSolucion(map.get("solucion").toString());
        incidencia.setUsuario(map.get("usuario").toString());

        
        return incidencia;
        
    }
    
    public void insertIncidencia(Map<String,Object> params){
        
        String sql = "INSERT INTO INCIDENCIA(idENFERMEDAD,idOFICINA, USUARIO, FECHA_REVISION, FECHA_TERMINO) "
                + "VALUES ("+params.get("idEnfermedad")+","+params.get("idOficina")
                +",'"+params.get("usuario")+"','"+params.get("fechaRevision")
                +"','"+params.get("fechaTermino")+"')";
        
        gcrud.sqlCUD(sql);
        
    }
    
    public void updateIncidencia(Incidencia incidencia){
        String sql = "UPDATE INCIDENCIA "
                + "SET idENFERMEDAD=" + incidencia.getEnfermedad()
                +" idOFICINA="+incidencia.getOficina()
                +" USUARIO='"+incidencia.getUsuario()+"'"
                +" FECHA_REVISION='"+incidencia.getFechaRevision()+"'"
                +" FECHA_TERMINO='"+incidencia.getFechaTermino()+"'"
                +" WHERE idINCIDENCIA="+incidencia.getId();
        
        gcrud.sqlCUD(sql);
    }
    
    public void deleteIncidencia(int id){
        String sql = "DELETE FROM INCIDENCIA WHERE idINCIDENCIA=" + id;
        
        gcrud.sqlCUD(sql);
    }
}
