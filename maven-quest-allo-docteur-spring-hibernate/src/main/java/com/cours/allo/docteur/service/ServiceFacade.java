/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.service;

import com.cours.allo.docteur.dao.IAdresseDao;
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
}
