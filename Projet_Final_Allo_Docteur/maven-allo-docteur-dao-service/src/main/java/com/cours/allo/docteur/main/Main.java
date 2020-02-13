/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.main;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
  //       Utilisateur userCRUD = new Utilisateur("Mr", "Jean", "Macron", "tadrasfure.macrogn@gmail.com", "passw0rd", "0655112244", new Date(System.currentTimeMillis()));
 //      userCRUD = serviceFacade.getUtilisateurDao().createUtilisateur(userCRUD);
   //     Adresse adresseCRUD = new Adresse("5 rue de l'enfer", "75000", "Paris", "France", userCRUD);
    //    serviceFacade.getAdresseDao().createAdresse(adresseCRUD);
      //  log.debug(userCRUD.getAdressePrincipale().getCodePostal());
  // log.debug(serviceFacade.getAdresseDao().findAllAdresses());
    //    log.debug("YESYYYYYYYYYYY"+serviceFacade.getUtilisateurDao().findUtilisateurByIdentifiantAndPassword("dimitry.gozin@gmail.com","passw0rd"));
     //   Utilisateur UserTest = serviceFacade.getUtilisateurDao().findUtilisateurById(1);
     //   log.debug(UserTest.getAdressePrincipale().getCodePostal());
        List<RendezVous> patients = serviceFacade.getRendezVousDao().findRendezVousByIdPatient(1);
        for (int i = 0; i < patients.size(); i++) {
            System.out.println(patients.get(i));
        }
        //log.debug();
    //Patient patient = new  Patient("123456789", userCRUD);
    //patient = serviceFacade.getPatientDao().createPatient(patient);
    //log.debug(patient);
//      //   log.debug(serviceFacade.getUtilisateurDao().findUtilisateurById(1));
//         Utilisateur userCRUD = serviceFacade.getUtilisateurDao().findUtilisateurById(1);
//       // log.debug(userCRUD.getAdresses()); 
//         log.debug("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
//          log.debug(userCRUD.getVersion()+ "*************** Avant UPDATE *************"); 
//        userCRUD.setNom("Seye");
//        userCRUD = serviceFacade.getUtilisateurDao().updateUtilisateur(userCRUD);
//       log.debug(userCRUD.getVersion()+"Apres UPDATE"); 
//       

      //  Adresse AdresseCRUD = serviceFacade.getAdresseDao().findAdresseById(1);
        // log.debug(userCRUD.getAdresses()); 
        log.debug("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
    //    log.debug(AdresseCRUD.getVersion() + "*************** Avant UPDATE *************");
   //     AdresseCRUD.setPays("dsddddsd");

    //    AdresseCRUD = serviceFacade.getAdresseDao().updateAdresse(AdresseCRUD);
    //    log.debug(AdresseCRUD.getVersion() + "Apres UPDATE");
        //  userCRUD = serviceFacade.getUtilisateurDao().createUtilisateur(userCRUD);
        //    log.debug(serviceFacade.getUtilisateurDao().findUtilisateurById(userCRUD.getIdUtilisateur()));
    }
}
