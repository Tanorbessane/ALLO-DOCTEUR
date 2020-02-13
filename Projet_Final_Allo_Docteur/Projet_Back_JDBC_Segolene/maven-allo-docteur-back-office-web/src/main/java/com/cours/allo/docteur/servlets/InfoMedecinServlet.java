package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.factory.ServiceFactory;
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

public class InfoMedecinServlet extends HttpServlet {

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
	        HttpSession session = request.getSession();
	        int idMedecin = (Integer) session.getAttribute("medecinId");
	        Medecin medecin = serviceFacade.getMedecinDao().findMedecinById(idMedecin);
	        request.setAttribute("medecin", medecin);
	        this.getServletContext().getRequestDispatcher("/pages/infos.jsp").forward(request, response);
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
	    	 try {
		        	HttpSession session = request.getSession();
		  	        int idMedecin = (Integer) session.getAttribute("medecinId");
		  	        Medecin medecinUp = serviceFacade.getMedecinDao().findMedecinById(idMedecin);
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
		  	        int medecinId = medecinUp.getIdMedecin();
		  	        int userId = medecinUp.getUtilisateur().getIdUtilisateur();
		  	        int adresseId = medecinUp.getUtilisateur().getAdresse().getIdAdresse();

		  	        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  	        Date dateNaissance = dateFormat.parse(naissance);

		  	        List<Medecin> medecins = serviceFacade.getMedecinDao().findAllMedecins();
		  	        for (Medecin medecin : medecins) {
		  	            if (medecin.getUtilisateur().getIdentifiant().equals(identifiant)) {
		  	                log.debug("Utilisateur non mis a jour");
		  	            } else {
		  	                Utilisateur utilisateur = new Utilisateur(userId, civilite, prenom, nom, identifiant, password, dateNaissance);
		  	                serviceFacade.getUtilisateurDao().updateUtilisateur(utilisateur);
		  	                Adresse adresse = new Adresse(adresseId, rue, cp, ville, pays);
		  	                serviceFacade.getAdresseDao().updateAdresse(adresse);
		  	                Medecin newMedecin = new Medecin(medecinId, accreditation, tel);
		  	                serviceFacade.getMedecinDao().updateMedecin(newMedecin);
		  	                log.debug("Utilisateur  mis a jour");
		  	                break;
		  	            }
		  	        }
		        } catch (ParseException ex) {
		            Logger.getLogger(InfoMedecinServlet.class.getName()).log(Level.SEVERE, null, ex);
		        }
	    }

	    @Override
	    public void destroy() {
	    }

	}
