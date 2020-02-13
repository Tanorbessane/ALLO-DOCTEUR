/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import com.cours.allo.docteur.dao.entities.Patient;
import java.util.List;

/**
 *
 * @author TA013BE
 */
public interface IPatientDao {
	public List<Patient> findAllPatient();

	public Patient findPatientById(int idPatient);

	public Patient findPatientByNumeroSecuriteSocial(String numeroSecuriteSocial);

	public Patient findPatientByNumeroTelephone(String numeroTelephone);

	public List<Patient> findPatientsByNom(String nom);

	public List<Patient> findPatientsByPrenom(String prenom);

	public Patient createPatient(Patient patient);

	public Patient updatePatient(Patient patient);

	public boolean deletePatient(Patient patient);
}
