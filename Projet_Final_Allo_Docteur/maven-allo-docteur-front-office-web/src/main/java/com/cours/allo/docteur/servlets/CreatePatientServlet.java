/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.impl.UtilisateurDao;
import com.cours.allo.docteur.service.IServiceFacade;
import java.io.IOException;
import java.io.PrintWriter;
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
public class CreatePatientServlet extends HttpServlet {

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
        this.getServletContext().getRequestDispatcher("/pages/login/creation-compte.jsp").forward(request, response);
    }

    private boolean addUser(HttpServletRequest request) throws ParseException {
        String civilite = request.getParameter("civility");
        String prenom = request.getParameter("firstName");
        String nom = request.getParameter("name");
        String identifiant = request.getParameter("identifier");
        String password = request.getParameter("password");
        String naissance = request.getParameter("naissance");
        String secu = request.getParameter("secu");
        String tel = request.getParameter("tel");
        String rue = request.getParameter("street");
        String cp = request.getParameter("postalCode");
        String ville = request.getParameter("city");
        String pays = request.getParameter("Pays");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaissance = dateFormat.parse(naissance);

        List<Patient> patients = serviceFacade.getPatientDao().findAllPatient();
        boolean result = false;
        for (Patient patient : patients) {
            if (patient.getIdUtilisateur().getIdentifiant().equals(identifiant)) {
                result = false;
            } else {
                Utilisateur utilisateur = new Utilisateur(civilite, prenom, nom, identifiant, password, dateNaissance);
                Utilisateur newUser = serviceFacade.getUtilisateurDao().createUtilisateur(utilisateur);
                Adresse adresse = new Adresse(rue, cp, ville, pays, newUser);
                serviceFacade.getAdresseDao().createAdresse(adresse);
                Patient newPatient = new Patient(secu, newUser);
                serviceFacade.getPatientDao().createPatient(newPatient);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (!this.addUser(request)) {
                log.debug("User already exist");
                response.sendRedirect(this.getServletContext().getContextPath() + "/CreatePatientServlet");
            } else {
                log.debug("good");
                response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
            }
            //   this.addUser(request);
        } catch (ParseException ex) {
            Logger.getLogger(CreatePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
