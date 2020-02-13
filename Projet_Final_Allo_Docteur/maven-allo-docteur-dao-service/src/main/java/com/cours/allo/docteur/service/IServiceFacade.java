/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.service;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.ICreneauDao;
import com.cours.allo.docteur.dao.IMedecinDao;
import com.cours.allo.docteur.dao.IPatientDao;
import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;

/**
 *
 * @author ElHadji
 */
public interface IServiceFacade {

    public IAdresseDao getAdresseDao();

    public IUtilisateurDao getUtilisateurDao();

    public IRendezVousDao getRendezVousDao();

    public IMedecinDao getMedecinDao();

    public ICreneauDao getCreneauDao();

    public IPatientDao getPatientDao();
}
