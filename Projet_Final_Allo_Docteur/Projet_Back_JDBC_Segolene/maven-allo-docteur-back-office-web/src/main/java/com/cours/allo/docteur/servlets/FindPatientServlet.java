package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author sigt_sf
 *
 */
public class FindPatientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;

    @Override
    public void init() throws ServletException {
        serviceFacade = ServiceFactory.getDefaultServiceFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        if (request.getParameter("search") != null) {
            String searchParam = (String) request.getParameter("searchName");
            System.out.println(request.getParameter("search"));
            List<Patient> patients = null;
            switch (request.getParameter("search")) {
                case "prenom":
                    patients = serviceFacade.getPatientDao().findPatientByPrenom(searchParam);
                    request.setAttribute("patients", patients);
                    break;
                case "nom":
                    patients = serviceFacade.getPatientDao().findPatientByNom(searchParam);
                    request.setAttribute("patients", patients);
                    break;
                case "secu":
                    patients = serviceFacade.getPatientDao().findPatientBySecu(searchParam);
                    request.setAttribute("patients", patients);
                    break;
            }
            this.getServletContext().getRequestDispatcher("/pages/findPatient.jsp").forward(request, response);
            log.debug("Resultat rechercher find patient");
        } else {
            List<Patient> patients = serviceFacade.getPatientDao().findAllPatients();
            request.setAttribute("patients", patients);
            this.getServletContext().getRequestDispatcher("/pages/findPatient.jsp").forward(request, response);
            log.debug("Affichage de tous les patients");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
