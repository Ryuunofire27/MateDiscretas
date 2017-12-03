package mate.discretas.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mate.discretas.model.Enfermedad;
import mate.discretas.util.GenericCRUD;

public class EnfermedadDAO{
    
    private GenericCRUD gcrud = new GenericCRUD();
    
    public List<Enfermedad> getEnfermedades(){
        List<Enfermedad> enfermedades = new ArrayList<>();
        String query = "SELECT * FROM ENFERMEDAD";
        List<Map<String, Object>> results = gcrud.getList(query);
        
        for(Map<String, Object> map: results){
            Enfermedad enfermedad = new Enfermedad();
            enfermedad.setId(Integer.parseInt(map.get("idenfermedad").toString()));
            enfermedad.setEnfermedad(map.get("enfermedad").toString());
            enfermedades.add(enfermedad);
        }
        
        return enfermedades;
    }
}
