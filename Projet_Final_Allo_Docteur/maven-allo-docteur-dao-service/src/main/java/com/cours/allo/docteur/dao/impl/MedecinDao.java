/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.IMedecinDao;
import com.cours.allo.docteur.dao.entities.Medecin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TA013BE
 */
@Repository
@Transactional
public class MedecinDao extends AbstractDao<Medecin> implements IMedecinDao{
    private static final Log log = LogFactory.getLog(MedecinDao.class);
    private final static String className = MedecinDao.class.getSimpleName();

    private MedecinDao() {
        super(Medecin.class);
        log.debug("--> ************ Initialisation de " + className + " ************");
    }

    @Override
    public Medecin findMedecinByIdMedecin(Integer id) {
        return super.findById(id);
    }

    @Override
    public Medecin findMedecinByNumeroAccreditation(String numeroAccreditation) {
        return (Medecin) super.findByCriteria("NumeroAccreditation", numeroAccreditation);
    }

    @Override
    public Medecin findMedecinByNumeroSecuriteSociale(String numeroSecuriteSociale) {
        return (Medecin) super.findByCriteria("NumeroSecuriteSociale", numeroSecuriteSociale);
    }

    @Override
    public Medecin findMedecinByNumeroTelephone(String numeroTelephone) {
         return (Medecin) super.findByCriteria("NumeroTelephone", numeroTelephone);
    }

    @Override
    public Medecin findMedecinByPwdAndId(String pwd, String email) {
        Medecin medecin = (Medecin) super.findByCriteria("email", email);
        if(medecin !=null && medecin.getIdUtilisateur().getMotPasse().equals(pwd)){
            return medecin;
        } else
         return null;
    }

    @Override
    public Medecin createMedecin(Medecin medecin) {
        return super.create(medecin);
    }

    @Override
    public Medecin updateMedecin(Medecin medecin) {
        return super.update(medecin);
    }

    @Override
    public boolean deleteMedecin(Medecin medecin) {
        return super.delete(medecin);
    }
    
}
