/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import com.cours.allo.docteur.dao.entities.RendezVous;
import java.util.Date;
import java.util.List;

/**
 *
 * @author TA013BE
 */
public interface IRendezVousDao {

	public List<RendezVous> findAll();

	public RendezVous findRendezVousById(Integer idRendezVous);

	public List<RendezVous> findRendezVousByJour(Date time);

	public List<RendezVous> findRendezVousByIdMedecin(Integer idMedecin);
        
        public List<RendezVous> findRendezVousByIdPatient(Integer idPatient);

	public List<RendezVous> findAllFuturRendezVous(Date time);

	public List<RendezVous> findRendezVousByJourAndIdMedecin(Integer idMedecin, Date time);

	public Long chiffreAffaire();

	public boolean deleteRendezVous(RendezVous rdv);

	public RendezVous updateRendezVous(RendezVous rdv);

	public RendezVous createRendezVous(RendezVous rdv);
}