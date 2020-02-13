/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.ConnectionHelper;
import com.cours.allo.docteur.dao.EntityFactorySingleton;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.exception.CustomException;
import static com.cours.allo.docteur.utils.Constants.EXCEPTION_CODE_USER_ALREADY_EXIST;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao extends AbstractDao<Utilisateur> implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(UtilisateurDao.class);

    private final static String className = AdresseDao.class.getSimpleName();
    protected EntityManagerFactory emf = EntityFactorySingleton.getInstance().getEntityManagerFactory();

    private UtilisateurDao() {
        super(Utilisateur.class);
        log.debug("--> ************ Initialisation de " + className + " ************");
    }

    /**
     * Holder
     */
    private static class MySingletonHolder {

        /**
         * Instance unique non préinitialisée
         */
        private final static UtilisateurDao instance = new UtilisateurDao();
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     *
     * @return
     */
    public static UtilisateurDao getInstance() {
        if (UtilisateurDao.MySingletonHolder.instance == null) {
            log.debug("--> Nouvelle Instance de " + className);
        } else {
            log.debug("--> Re-Utilisation de l'instance " + className + " dejà existante");
        }
        return UtilisateurDao.MySingletonHolder.instance;
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        return super.findAll();
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        return super.findById(idUtilisateur);
    }

    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
        return super.findByCriteria("Identifiant", identifiant);
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        return super.findByCriteria("Prenom", prenom);
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
        return super.findByCriteria("Nom", nom);
    }

    @Override
    public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) {
        return super.findByCriteria("CodePostal", codePostal);
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        return super.create(user);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
        return update(user);
    }

    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
        return super.delete(user);
    }
}
