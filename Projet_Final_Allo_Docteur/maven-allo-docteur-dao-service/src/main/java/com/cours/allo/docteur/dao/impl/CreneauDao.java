/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.ICreneauDao;
import com.cours.allo.docteur.dao.entities.Creneau;
import java.util.List;
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
public class CreneauDao extends AbstractDao<Creneau> implements ICreneauDao {

    private static final Log log = LogFactory.getLog(CreneauDao.class);
    private final static String className = CreneauDao.class.getSimpleName();

    private CreneauDao() {
        super(Creneau.class);
        log.debug("--> ************ Initialisation de " + className + " ************");
    }

    @Override
    public List<Creneau> findCreneauxByHeureDebut(Integer heureDebut) {
        return super.findByCriteria("HeureDebut", heureDebut);
    }

    @Override
    public List<Creneau> findCreneauxByHeureFin(Integer heureFin) {
        return super.findByCriteria("HeureFin", heureFin);
    }

    @Override
    public List<Creneau> findCreneauxByMinuteDebut(Integer minuteDebut) {
        return super.findByCriteria("MinuteDebut", minuteDebut);
    }

    @Override
    public List<Creneau> findCreneauxByMinuteFin(Integer minuteFin) {
        return super.findByCriteria("MinuteFin", minuteFin);
    }

    @Override
    public Creneau createCreneau(Creneau creneau) {
        return super.create(creneau);
    }

    @Override
    public boolean deleteCreneau(Creneau creneau) {
        return super.delete(creneau);
    }

    @Override
    public Creneau updateCreneau(Creneau creneau) {
        return super.update(creneau);
    }

}
