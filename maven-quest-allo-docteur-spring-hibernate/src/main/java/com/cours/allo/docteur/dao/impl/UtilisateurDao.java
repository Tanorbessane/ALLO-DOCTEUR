/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao extends AbstractDao<Utilisateur> implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(UtilisateurDao.class);

    private final static String className = AdresseDao.class.getSimpleName();

    private UtilisateurDao() {
        super(Utilisateur.class);
        log.debug("--> ************ Initialisation de " + className + " ************");
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
