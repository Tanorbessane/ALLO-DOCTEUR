/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import com.cours.allo.docteur.dao.entities.Creneau;
import java.util.List;

/**
 *
 * @author TA013BE
 */
public interface ICreneauDao {
	public List<Creneau> findAll();

	public List<Creneau> findCreneauxByHeureDebut(Integer heureDebut);

	public List<Creneau> findCreneauxByHeureFin(Integer heureFin);

	public List<Creneau> findCreneauxByMinuteDebut(Integer minuteDebut);

	public List<Creneau> findCreneauxByMinuteFin(Integer minuteFin);

	public Creneau createCreneau(Creneau creneau);

	public boolean deleteCreneau(Creneau creneau);

	public Creneau updateCreneau(Creneau creneau);
}
