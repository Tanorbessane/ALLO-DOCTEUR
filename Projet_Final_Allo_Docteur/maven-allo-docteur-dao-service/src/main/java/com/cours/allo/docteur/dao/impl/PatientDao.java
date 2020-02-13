/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.IPatientDao;
import com.cours.allo.docteur.dao.entities.Patient;
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
@Transactional(noRollbackFor = Exception.class)
public class PatientDao extends AbstractDao<Patient> implements IPatientDao{
    private static final Log log = LogFactory.getLog(UtilisateurDao.class);

    private final static String className = PatientDao.class.getSimpleName();

    private PatientDao() {
        super(Patient.class);
        log.debug("--> ************ Initialisation de " + className + " ************");
    }
     @Override
    public List<Patient> findAllPatient() {
        return super.findAll();
    }
    
    @Override
    public Patient findPatientById(int idPatient) {
         return super.findById(idPatient);
    }

    @Override
    public Patient findPatientByNumeroSecuriteSocial(String numeroSecuriteSocial) {
         return (Patient) super.findByCriteria("SecuriteSocial", numeroSecuriteSocial);
    }

    @Override
    public Patient findPatientByNumeroTelephone(String numeroTelephone) {
        return (Patient) super.findByCriteria("NumeroTelephone", numeroTelephone);
    }

    @Override
    public List<Patient> findPatientsByNom(String nom) {
        return super.findByCriteria("Nom", nom);
    }

    @Override
    public List<Patient> findPatientsByPrenom(String prenom) {
        return super.findByCriteria("Prenom", prenom);
    }

    @Override
    public Patient createPatient(Patient patient) {
         return super.create(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return update(patient);
    }

    @Override
    public boolean deletePatient(Patient patient) {
        return super.delete(patient);
    }

   
}
