/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.dao.entities.Utilisateur;
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
public class UpdatePatientServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;
    private Object session;

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
        Patient patient = serviceFacade.getPatientDao().findPatientById(idPatient);
        request.setAttribute("numeroSS", patient.getNumeroSecuriteSociale());
        request.setAttribute("patient", patient);
        request.setAttribute("numeroSS", patient.getNumeroSecuriteSociale());
        setPatient(request, patient);
        setAddressDans(request, patient);

        this.getServletContext().getRequestDispatcher("/pages/user/modification-informations-patient.jsp").forward(request, response);
    }

    private void setPatient(HttpServletRequest request, Patient patient) {
        Utilisateur user;
        user = patient.getIdUtilisateur();
        request.setAttribute("name", user.getPrenom());
        request.setAttribute("civility", user.getCivilite());
        request.setAttribute("lastName", user.getNom());
        request.setAttribute("email", user.getIdentifiant());
        request.setAttribute("tel", user.getNumeroTelephone());
    }

    private void setAddressDans(HttpServletRequest request, Patient patient) {
        IUtilisateurDao dao;
        Adresse mainAddr;

        dao = serviceFacade.getUtilisateurDao();
        mainAddr = dao.findAdressePrincipale(patient.getIdUtilisateur().getIdUtilisateur());
        request.setAttribute("country", mainAddr.getVille());
        request.setAttribute("rue", mainAddr.getRue());
        request.setAttribute("pays", mainAddr.getPays());
        request.setAttribute("codePostal", mainAddr.getCodePostal());
    }

    private void updateUserPatient(HttpServletRequest request, Patient patient) {
        patient.getIdUtilisateur().setIdentifiant((String) request.getParameter("identifier"));
        patient.getIdUtilisateur().setPrenom((String) request.getParameter("firstName"));
        patient.getIdUtilisateur().setNom((String) request.getParameter("name"));
        patient.getIdUtilisateur().setCivilite((String) request.getParameter("civility"));
        patient.getIdUtilisateur().setNumeroTelephone((String) request.getParameter("tel"));
        serviceFacade.getUtilisateurDao().updateUtilisateur(patient.getIdUtilisateur());
        Adresse mainAddr = serviceFacade.getUtilisateurDao().findAdressePrincipale(patient.getIdUtilisateur().getIdUtilisateur());
        mainAddr.setVille((String) request.getParameter("country"));
        mainAddr.setRue((String) request.getParameter("rue"));
        mainAddr.setPays((String) request.getParameter("pays"));
        mainAddr.setCodePostal((String) request.getParameter("codePostal"));
        serviceFacade.getAdresseDao().updateAdresse(mainAddr);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idPatient = (Integer) session.getAttribute("patientId");

        Patient patientUp = serviceFacade.getPatientDao().findPatientById(idPatient);

      //  IPatientDao patientDao;
        // patientDao = serviceFacade.getPatientDao();
        updateUserPatient(request, patientUp);
        serviceFacade.getPatientDao().updatePatient(patientUp);
        response.sendRedirect(this.getServletContext().getContextPath() + "/ManagePatientServlet");
        //   patientDao.updatePatient(patientUp);
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

    @Override
    public void destroy() {
    }
}
