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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ElHadji
 */
@Repository
@Transactional
public class AdresseDao extends AbstractDao<Adresse> implements IAdresseDao {

    private static final Log log = LogFactory.getLog(AdresseDao.class);
    private final static String className = AdresseDao.class.getSimpleName();

    private AdresseDao() {
        super(Adresse.class);
        log.debug("--> ************ Initialisation de " + className + " ************");
    }

    @Override
    public List<Adresse> findAllAdresses() {

        return super.findAll();
    }

    @Override
    public Adresse findAdresseById(int idAdresse) {
        return super.findById(idAdresse);
    }

    @Override
    public List<Adresse> findAdressesByVille(String ville) {
        return super.findByCriteria("Ville", ville);
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
    @Transactional
    public Adresse updateAdresse(Adresse adresse) {
        return super.update(adresse);
    }

    @Override
    public boolean deleteAdresse(Adresse adresse) {
        return super.delete(adresse);
    }
}
