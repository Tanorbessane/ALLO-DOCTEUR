/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import com.cours.allo.docteur.dao.entities.Medecin;
import java.util.List;

/**
 *
 * @author TA013BE
 */
public interface IMedecinDao {
	public List<Medecin> findAll();

	public Medecin findMedecinByIdMedecin(Integer id);

	public Medecin findMedecinByNumeroAccreditation(String numeroAccreditation);

	public Medecin findMedecinByNumeroSecuriteSociale(String numeroSecuriteSociale);

	public Medecin findMedecinByNumeroTelephone(String numeroTelephone);

	public Medecin findMedecinByPwdAndId(String pwd, String email);

	public Medecin createMedecin(Medecin medecin);

	public Medecin updateMedecin(Medecin medecin);

	public boolean deleteMedecin(Medecin medecin);

}