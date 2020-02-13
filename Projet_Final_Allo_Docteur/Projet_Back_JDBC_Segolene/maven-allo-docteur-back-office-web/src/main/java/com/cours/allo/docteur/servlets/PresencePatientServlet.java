package com.cours.allo.docteur.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;
  
  
  public class PresencePatientServlet extends HttpServlet {

		private static final long serialVersionUID = 1L;
		private static final Log log = LogFactory.getLog(LoginServlet.class);
		private IServiceFacade serviceFacade = null;
		    
	    @Override
	    public void init() throws ServletException {
	        serviceFacade = ServiceFactory.getDefaultServiceFacade();
	    }
	    
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	   	
	    	//String idPatient = request.getParameterValues("idPatient")[0];
	    	String idRendezVous = request.getParameterValues("idRendezVous")[0];
		    
		    //rendre le patient present
		    RendezVous rdv = serviceFacade.getRendezVousDao().findRendezVousById(Integer.parseInt(idRendezVous));
		    if( rdv != null) {
		    	rdv.setPresent(true);
		    	serviceFacade.getRendezVousDao().updateRendezVous(rdv);
		    	log.debug("Rdv mis à jour");
		    }else {
		    	log.debug("Rdv non trouvé");
		    }
		    response.sendRedirect("RdvMedecinServlet");
	    }
	    
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       //
	    }

	    @Override
	    public void destroy() {
	    }

	}
