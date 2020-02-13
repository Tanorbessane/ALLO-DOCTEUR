/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.main;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.factory.AbstractDaoFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author elhad
 */
public class Main {

    private static final Log log = LogFactory.getLog(Main.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IServiceFacade serviceFacade = null;
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JPA_DAO_FACTORY);
        //findAllUtilisateurs
        // log.debug(serviceFacade.getAdresseDao().findAllAdresses());

         log.debug(serviceFacade.getAdresseDao().findAdressesByCodePostal("75000"));
      //  log.debug(serviceFacade.getUtilisateurDao().findUtilisateursByCodePostal("75000"));
        //log.debug(serviceFacade.getUtilisateurDao().findUtilisateurById(1));
        log.debug("Test Fini");
        //Utilisateur userCRUD = new Utilisateur("Mr", "Jean", "Macron", "jean.macron@gmail.com", "passw0rd", "0655112244", new Date(System.currentTimeMillis()));
        //  userCRUD = serviceFacade.getUtilisateurDao().createUtilisateur(userCRUD);
        //   Adresse adresseCRUD = new Adresse("5 rue de l'enfer", "75000", "Paris", "France", userCRUD);
        //   adresseCRUD = serviceFacade.getAdresseDao().createAdresse(adresseCRUD);
        //   log.debug(serviceFacade.getAdresseDao().findAdresseById(adresseCRUD.getIdAdresse()));
//        log.debug(serviceFacade.getUtilisateurDao().findUtilisateurById(user.getIdUtilisateur()));
    }
}
