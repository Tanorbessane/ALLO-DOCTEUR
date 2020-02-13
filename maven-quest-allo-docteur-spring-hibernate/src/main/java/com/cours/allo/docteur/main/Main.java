/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.main;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IServiceFacade serviceFacade = (ServiceFacade) ctx.getBean("serviceFacade");
//        
//        //log.debug(serviceFacade.getAdresseDao().findAllAdresses());
//      //   log.debug(serviceFacade.getUtilisateurDao().findUtilisateurById(1));
//         Utilisateur userCRUD = serviceFacade.getUtilisateurDao().findUtilisateurById(1);
//       // log.debug(userCRUD.getAdresses()); 
//         log.debug("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
//          log.debug(userCRUD.getVersion()+ "*************** Avant UPDATE *************"); 
//        userCRUD.setNom("Seye");
//        userCRUD = serviceFacade.getUtilisateurDao().updateUtilisateur(userCRUD);
//       log.debug(userCRUD.getVersion()+"Apres UPDATE"); 
//       

        Adresse AdresseCRUD = serviceFacade.getAdresseDao().findAdresseById(1);
        // log.debug(userCRUD.getAdresses()); 
        log.debug("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        log.debug(AdresseCRUD.getVersion() + "*************** Avant UPDATE *************");
        AdresseCRUD.setPays("dsddddsd");

        AdresseCRUD = serviceFacade.getAdresseDao().updateAdresse(AdresseCRUD);
        log.debug(AdresseCRUD.getVersion() + "Apres UPDATE");
        //  userCRUD = serviceFacade.getUtilisateurDao().createUtilisateur(userCRUD);
        //    log.debug(serviceFacade.getUtilisateurDao().findUtilisateurById(userCRUD.getIdUtilisateur()));
    }
}
