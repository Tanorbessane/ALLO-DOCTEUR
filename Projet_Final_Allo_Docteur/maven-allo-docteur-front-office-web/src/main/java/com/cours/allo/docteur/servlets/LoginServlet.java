/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

/**
 *
 * @author TA013BE
 */
import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.service.IServiceFacade;
import java.io.IOException;
import java.util.List;
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
 * @author elhad
 */
// @WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
private static final Log log = LogFactory.getLog(LoginServlet.class);
 private IServiceFacade serviceFacade = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WebApplicationContext context =
WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
serviceFacade = (IServiceFacade) context.getBean("serviceFacade");
log.debug("allAdresses: " + serviceFacade.getAdresseDao().findAllAdresses());

        this.getServletContext().getRequestDispatcher("/pages/login/allo-doctor-compte.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        List<Patient> patients = serviceFacade.getPatientDao().findAllPatient();
        boolean result = false;
        
        for (Patient patient : patients) {
            String IdentientDb = patient.getIdUtilisateur().getIdentifiant();
            if( IdentientDb != null){
            if (patient.getIdUtilisateur().getIdentifiant().equals(login) && patient.getIdUtilisateur().getMotPasse().equals(password)) {
                result = true;
                session.setAttribute("patientId", patient.getIdPatient());
                break;
            } else {
                result = false;
            }
        }else {
                result = false;
        }
        }
        if (!result) {
            System.out.println("wrong");
            response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
        } else {
            System.out.println("good");
            response.sendRedirect(this.getServletContext().getContextPath() + "/ManagePatientServlet");
        }
    }
}
