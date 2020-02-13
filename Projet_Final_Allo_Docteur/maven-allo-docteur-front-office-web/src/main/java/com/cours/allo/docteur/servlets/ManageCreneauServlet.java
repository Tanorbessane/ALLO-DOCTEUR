/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.service.IServiceFacade;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author TA013BE
 */
public class ManageCreneauServlet extends HttpServlet {

    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;
    private Object session;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context
                = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        serviceFacade = (IServiceFacade) context.getBean("serviceFacade");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            int idPatient = (Integer) session.getAttribute("patientId");
            request.setAttribute("idPatient", idPatient);
            String jour = request.getParameter("date");
            int idMedecin = Integer.parseInt(request.getParameter("medecin"));
            List<RendezVous> rendezVous;
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateRdv = dateFormat.parse(jour);
            
            rendezVous = serviceFacade.getRendezVousDao().findRendezVousByJourAndIdMedecin(idMedecin, dateRdv);
            List<Patient> patients = serviceFacade.getPatientDao().findAllPatient();
            for (Patient patient : patients) {
                for (RendezVous rdv : rendezVous) {
                    
                    if (patient.getIdPatient().equals(rdv.getPatientRdv())) {
                        rendezVous.remove(rdv);
                    }
                }
            }
            request.setAttribute("rendezVous", rendezVous);
            this.getServletContext().getRequestDispatcher("/pages/user/selection-creneau.jsp").forward(request, response);
            
        } catch (ParseException ex) {
            Logger.getLogger(ManageCreneauServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
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
