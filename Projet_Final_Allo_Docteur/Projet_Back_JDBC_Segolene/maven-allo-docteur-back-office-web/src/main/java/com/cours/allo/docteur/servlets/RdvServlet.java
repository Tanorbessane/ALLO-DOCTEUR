package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class RdvServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;

    @Override
    public void init() throws ServletException {
        serviceFacade = ServiceFactory.getDefaultServiceFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idMedecin = (Integer) session.getAttribute("medecinId");
        List<RendezVous> rendezVous = null;
		try {
			Medecin medecin = serviceFacade.getMedecinDao().findMedecinById(idMedecin); 
			rendezVous = serviceFacade.getRendezVousDao().findRendezVousByMedecinAndDate(medecin.getUtilisateur().getIdentifiant() , "17/02/2019");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
        if (rendezVous.size() == 0)
        	log.debug("Liste des rendez-vous vide");
        
        request.setAttribute("rendezVous", rendezVous);
        this.getServletContext().getRequestDispatcher("/pages/rdvDay.jsp").forward(request, response);
        log.debug("Affichage des rendez-vous");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public void destroy() {
    }

}
