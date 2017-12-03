/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mate.discretas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.discretas.dao.IncidenciaDAO;
import mate.discretas.model.Incidencia;

/**
 *
 * @author charlie
 */
@WebServlet(name = "CRUDServlet", urlPatterns = {"/CRUD"})
public class CRUDServlet extends HttpServlet {

    private IncidenciaDAO incidenciaDAO = new IncidenciaDAO();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String id = request.getParameter("id");
        String result = "";
        if(id==null){
            PrintWriter out = response.getWriter();
            List<Incidencia> incidencias = incidenciaDAO.getIncidencias();
            for(Incidencia inc : incidencias){
                result += inc.getId()+"?";
                result += inc.getUsuario()+"?";
                result += inc.getOficina()+"?";
                result += inc.getEnfermedad()+"?";
                result += inc.getFechaRevision()+"?";
                result += inc.getFechaTermino()+"?";
                result += inc.getSolucion()+"?";
            }
            response.setContentType("text/html;charset=UTF-8");
            out.print(result);
            out.close();
        }else{
            Incidencia incidencia = incidenciaDAO.getIncidencia(Integer.parseInt(id));
            Date fechaRevision = new Date();
            Date fechaTermino = new Date();
            String[] arrayRevision = incidencia.getFechaRevision().split("-");
            String[] arrayTermino = incidencia.getFechaTermino().split("-");
            
            fechaRevision.setDate(Integer.parseInt(arrayRevision[2]));
            fechaRevision.setMonth(Integer.parseInt(arrayRevision[1])-1);
            fechaRevision.setYear(Integer.parseInt(arrayRevision[0])-1900);
            fechaTermino.setDate(Integer.parseInt(arrayTermino[2]));
            fechaTermino.setMonth(Integer.parseInt(arrayTermino[1])-1);
            fechaTermino.setYear(Integer.parseInt(arrayTermino[0])-1900);
            
            request.setAttribute("incidencia", incidencia);
            request.setAttribute("fechaRevision", fechaRevision);
            request.setAttribute("fechaTermino", fechaTermino);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp?id="+id);
            dispatcher.forward(request, response);
        }
        
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
        
        String fechaRString =  req.getParameter("fechaRevision");
        String[] fechaRArray = fechaRString.split("-");
        String fechaTString = req.getParameter("fechaTermino");
        String[] fechaTArray = fechaTString.split("-");
        String fechaRevision = fechaRArray[0]+"-"+fechaRArray[1]+"-"+fechaRArray[2];
        String fechaTermino = fechaTArray[0]+"-"+fechaTArray[1]+"-"+fechaTArray[2];     
        
        
        
        Incidencia incidencia = new Incidencia();
        incidencia.setId(Integer.parseInt(req.getParameter("id")));
        incidencia.setOficina(req.getParameter("idOficina"));
        incidencia.setEnfermedad(req.getParameter("idEnfermedad"));
        incidencia.setDoctor(req.getParameter("doctor"));
        incidencia.setUsuario(req.getParameter("usuario"));
        incidencia.setFechaRevision(fechaRevision);
        incidencia.setFechaTermino(fechaTermino);
        incidencia.setSolucion(req.getParameter("tratamiento"));
        
        
        incidenciaDAO.updateIncidencia(incidencia);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        String id = req.getParameter("idIncidencia");
        incidenciaDAO.deleteIncidencia(Integer.parseInt(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        String fechaRString =  req.getParameter("fechaRevision");
        String[] fechaRArray = fechaRString.split("-");
        String fechaTString = req.getParameter("fechaTermino");
        String[] fechaTArray = fechaTString.split("-");
        String fechaRevision = fechaRArray[0]+"-"+fechaRArray[1]+"-"+fechaRArray[2];
        String fechaTermino = fechaTArray[0]+"-"+fechaTArray[1]+"-"+fechaTArray[2];

        Map<String,Object> params = new HashMap<>();
        params.put("idEnfermedad", req.getParameter("idEnfermedad"));
        params.put("idOficina", req.getParameter("idOficina"));
        params.put("usuario",req.getParameter("usuario"));
        params.put("fechaRevision", fechaRevision);
        params.put("fechaTermino", fechaTermino);
        
        incidenciaDAO.insertIncidencia(params);
    }

    
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
