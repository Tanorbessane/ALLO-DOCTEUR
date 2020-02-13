/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.service;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.ICreneauDao;
import com.cours.allo.docteur.dao.IMedecinDao;
import com.cours.allo.docteur.dao.IPatientDao;
import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.*;

/**
 *
 * @author ElHadji
 */
public class ServiceFacade implements IServiceFacade {

    private static final Log log = LogFactory.getLog(ServiceFacade.class);
    // On liste toutes les DAO : un DAO pour chaque entité (Utilisateur,Adresse ect ....)
    //L'annotation @Autowired permet de demander une injection automatique par type.

    //L'annotation @Qualifier permet de faciliter la détermination du bean à injecter grâce à l'annotation @Autowired 
    //dans le cas où plusieurs instances gérées par le conteneur correspondent au type souhaité.
    @Autowired
    @Qualifier("UtilisateurDao")
    private IUtilisateurDao utilisateurDao;
    @Autowired
    @Qualifier("AdresseDao")
    private IAdresseDao adresseDao;
    @Autowired
    @Qualifier("patientDao")
    private IPatientDao patientDao;

    @Autowired
    @Qualifier("medecinDao")
    private IMedecinDao medecinDao;

    @Autowired
    @Qualifier("creneauDao")
    private ICreneauDao creneauDao;

    @Autowired
    @Qualifier("rendezVousDao")
    private IRendezVousDao rendezVousDao;

    public ServiceFacade() {
        log.debug("Instanciation de notre Service Facade !!!");
    }

    @Override
    @Autowired
    @Qualifier("UtilisateurDao")
    public IUtilisateurDao getUtilisateurDao() {
        return utilisateurDao;
    }

    @Override
    @Autowired
    public IAdresseDao getAdresseDao() {
        return adresseDao;
    }

    @Override
    public IRendezVousDao getRendezVousDao() {
        return rendezVousDao;
    }

    @Override
    public IMedecinDao getMedecinDao() {
        return medecinDao;
    }

    @Override
    public ICreneauDao getCreneauDao() {
        return creneauDao;
    }

    @Override
    public IPatientDao getPatientDao() {
        return patientDao;
    }

}
