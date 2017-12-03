package mate.discretas.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mate.discretas.model.Doctor;
import mate.discretas.util.GenericCRUD;

public class DoctorDAO{
    
    private GenericCRUD gcrud = new GenericCRUD();
    
    public List<Doctor> getDoctores(){
        List<Doctor> doctores = new ArrayList<>();
        String query = "SELECT * FROM DOCTOR";
        List<Map<String, Object>> results = gcrud.getList(query);
        
        for(Map<String, Object> map: results){
            Doctor doctor = new Doctor();
            doctor.setId(Integer.parseInt(map.get("iddoctor").toString()));
            doctor.setDoctor(map.get("doctor").toString());
            doctores.add(doctor);
        }
        
        return doctores;
    }
    
    public Doctor getDoctor(int id){
        Doctor doctor = new Doctor();
        String query = "SELECT * FROM DOCTOR WHERE idDOCTOR="
                + "(SELECT idDOCTOR FROM ENFERMEDAD WHERE idENFERMEDAD="+id+")";
        Map<String, Object> map = gcrud.get(query);
        doctor.setId(Integer.parseInt(map.get("iddoctor").toString()));
        doctor.setDoctor(map.get("doctor").toString());
        
        
        return doctor;
    }
    
}
