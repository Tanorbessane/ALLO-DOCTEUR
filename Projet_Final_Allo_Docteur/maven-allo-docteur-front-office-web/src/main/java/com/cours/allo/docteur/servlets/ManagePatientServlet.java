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
public class ManagePatientServlet extends HttpServlet {

    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context
                = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        serviceFacade = (IServiceFacade) context.getBean("serviceFacade");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idPatient = (Integer) session.getAttribute("patientId");
        RendezVous rendezVous = serviceFacade.getRendezVousDao().findRendezVousById(idPatient);
        Patient patient = serviceFacade.getPatientDao().findPatientById(idPatient);
        request.setAttribute("patient", patient);
        request.setAttribute("rendezVous", rendezVous);
        this.getServletContext().getRequestDispatcher("/pages/user/patients.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
