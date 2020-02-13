package com.cours.allo.docteur.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;

public class ReferMedecinServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;

    @Override
    public void init() throws ServletException {
        serviceFacade = ServiceFactory.getDefaultServiceFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 this.getServletContext().getRequestDispatcher("/pages/referMedecin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 try {   
    		String civilite = request.getParameter("civility");
    		String prenom = request.getParameter("firstName");
 	        String nom = request.getParameter("name");
 	        String identifiant = request.getParameter("identifier");
 	        String password = request.getParameter("password");
 	        String naissance = request.getParameter("naissance");
 	        String accreditation = request.getParameter("numAcreditation");
 	        String tel = request.getParameter("tel");
 	        String rue = request.getParameter("street");
 	        String cp = request.getParameter("postalCode");
 	        String ville = request.getParameter("city");
 	        String pays = request.getParameter("Pays");
 	        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
 	        Date dateNaissance = dateFormat.parse(naissance);    

    		List<Medecin> medecins = serviceFacade.getMedecinDao().findAllMedecins();
    		boolean result = false;
    		for (Medecin medecin : medecins) {
  	            if (medecin.getUtilisateur().getIdentifiant().equals(identifiant)) {
  	                result = false;
  	            } else {
  	                result = true;
  	            }
  	        }
    		
    		if (result) {
                Utilisateur utilisateur = new Utilisateur(civilite, prenom,  nom,  identifiant,  password, dateNaissance) ;
                Utilisateur newUser = serviceFacade.getUtilisateurDao().createUtilisateur(utilisateur);
                log.debug("Utilisateur ajouté");
                Adresse adresse = new Adresse(rue, cp, ville, pays, newUser.getIdUtilisateur());
                serviceFacade.getAdresseDao().createAdresse(adresse);
                log.debug("Adresse de l'utilisateur ajouté");
                Medecin newMedecin = new Medecin(accreditation, tel, newUser.getIdUtilisateur());
                Medecin createdMedecin = serviceFacade.getMedecinDao().createMedecin(newMedecin);
                log.debug("ajout de l'utilisateur en tant que medecin avec l'id => " + createdMedecin.getIdMedecin());
            }else {
            	log.debug("Utilisateur existant");
            }   
    	 }catch(Exception ex) {
    		 log.debug("Erreur => " + ex.getMessage());
    	 }

		 response.sendRedirect(this.getServletContext().getContextPath() + "/HomeMedecinServlet");
    }

    @Override
    public void destroy() {
    }
}
