package mate.discretas.dao;

import java.util.Map;
import mate.discretas.model.Tratamiento;
import mate.discretas.util.GenericCRUD;

public class TratamientoDAO {
    
    private GenericCRUD gcrud = new GenericCRUD();
    
    public Tratamiento getTratamiento(int id){
        Tratamiento tratamiento = new Tratamiento();
        String query = "SELECT * FROM TRATAMIENTO WHERE idTRATAMIENTO="
                + "(SELECT idTRATAMIENTO FROM ENFERMEDAD WHERE idENFERMEDAD="+id+")";
        Map<String, Object> map = gcrud.get(query);
        tratamiento.setId(Integer.parseInt(map.get("idtratamiento").toString()));
        tratamiento.setTratamiento(map.get("tratamiento").toString());
        
        return tratamiento;
    }
}
