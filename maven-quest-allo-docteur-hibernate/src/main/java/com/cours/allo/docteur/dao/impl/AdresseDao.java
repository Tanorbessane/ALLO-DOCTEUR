/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class AdresseDao extends AbstractDao<Adresse> implements IAdresseDao {

    private static final Log log = LogFactory.getLog(AdresseDao.class);
    private final static String className = AdresseDao.class.getSimpleName();

    private AdresseDao() {
        super(Adresse.class);
        log.debug("--> ************ Initialisation de " + className + " ************");
    }

    /**
     * Holder
     */
    private static class MySingletonHolder {

        /**
         * Instance unique non préinitialisée
         */
        private final static AdresseDao instance = new AdresseDao();
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     *
     * @return
     */
    public static AdresseDao getInstance() {
        if (AdresseDao.MySingletonHolder.instance == null) {
            log.debug("--> Nouvelle Instance de " + className);
        } else {
            log.debug("--> Re-Utilisation de l'instance " + className + " dejà existante");
        }
        return AdresseDao.MySingletonHolder.instance;
    }

    @Override
    public List<Adresse> findAllAdresses() {
       
        return  super.findAll();
    }

    @Override
    public Adresse findAdresseById(int idAdresse) {
        return  super.findById(idAdresse);
    }

    @Override
    public List<Adresse> findAdressesByVille(String ville) {
        return  super.findByCriteria("Ville", ville);
    }

    @Override
    public List<Adresse> findAdressesByCodePostal(String codePostal) {
        return super.findByCriteria("codepostal", codePostal);
    }

    @Override
    public Adresse createAdresse(Adresse adresse) {
        return super.create(adresse);
    }

    @Override
    public Adresse updateAdresse(Adresse adresse) {
     //   int newVersion = 0 ;
      //  Integer testVersion = findAdresseById(adresse.getIdAdresse()).getVersion();
     //   if(testVersion == null){
   //      testVersion =0;
   //     }else{
   //     newVersion = testVersion + 1;
  //      }
 //       adresse.setVersion(newVersion);
        return super.update(adresse);
    }

    @Override
    public boolean deleteAdresse(Adresse adresse) {
        return super.delete(adresse);
    }
}
