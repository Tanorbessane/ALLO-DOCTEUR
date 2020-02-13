package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.factory.ServiceFactory;
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



/***
 * 
 * @author sigt_sf
 *
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;

    @Override
    public void init() throws ServletException {
        serviceFacade = ServiceFactory.getDefaultServiceFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //recupération des identifiants 
        String identifiant = request.getParameter("login");
        String pass = request.getParameter("password");
        
        List<Medecin> medecins = serviceFacade.getMedecinDao().findAllMedecins();
        
        boolean result = false;
        
        if (medecins != null && medecins.size() != 0) {
        	
	        for (Medecin medecin : medecins) {
	            if (medecin.getUtilisateur().getIdentifiant().equals(identifiant) && medecin.getUtilisateur().getMotPasse().equals(pass)) {
	                result = true;
	                log.debug(medecin.getIdMedecin());
	                session.setAttribute("medecinId", medecin.getIdMedecin());
	                break;
	            } else {
	                result = false;
	            }
	        }
	        
	    }else {
	    	log.debug("La liste des medecins est vide ou null");
	    }
        
        if (!result) {
            log.debug("Medecin non trouvé");
            response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
        } else {
            log.debug("Medecin trouvé");
            response.sendRedirect(this.getServletContext().getContextPath() + "/HomeMedecinServlet");
        }
    }

    @Override
    public void destroy() {
    }

}
