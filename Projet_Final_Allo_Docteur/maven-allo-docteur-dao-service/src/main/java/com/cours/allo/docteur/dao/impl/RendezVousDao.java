/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
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
public class RendezVousDao extends AbstractDao<RendezVous> implements IRendezVousDao {

     private static final Log log = LogFactory.getLog(RendezVousDao.class);

    private final static String className = AdresseDao.class.getSimpleName();

    private RendezVousDao() {
        super(RendezVous.class);
        log.debug("--> ************ Initialisation de " + className + " ************");
    }

    @Override
    public RendezVous findRendezVousById(Integer idRendezVous) {
         return super.findById(idRendezVous);
    }

    @Override
    public List<RendezVous> findRendezVousByJour(Date time) {
        return super.findByCriteria("Jour", time);
    }

    @Override
    public List<RendezVous> findRendezVousByIdMedecin(Integer idMedecin) {
        return super.findByCriteria("IdMedecin", idMedecin);
    }
     @Override
    public List<RendezVous> findRendezVousByIdPatient(Integer idPatient) {
        return super.findByCriteria("IdPatient", idPatient);
    }
    @Override
    public List<RendezVous> findAllFuturRendezVous(Date time) {
         return super.findAll();
    }
  
    @Override
    public List<RendezVous> findRendezVousByJourAndIdMedecin(Integer idMedecin, Date time) {
       List<RendezVous> ret;
		Query query;

		log.debug("Entree de la methode");

		ret = null;

		try {
			query = em.createNamedQuery("RendezVous.findRendezVousByJourAndIdMedecin");
			query.setParameter("idMedecin", idMedecin);
			query.setParameter("jour", time);
			ret = query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
    }

    @Override
    public Long chiffreAffaire() {
       return null;
    }

    @Override
    public boolean deleteRendezVous(RendezVous rdv) {
         return super.delete(rdv);
    }

    @Override
    public RendezVous updateRendezVous(RendezVous rdv) {
         return update(rdv);
    }

    @Override
    public RendezVous createRendezVous(RendezVous rdv) {
        return super.create(rdv);
    }

 
   
    
}
