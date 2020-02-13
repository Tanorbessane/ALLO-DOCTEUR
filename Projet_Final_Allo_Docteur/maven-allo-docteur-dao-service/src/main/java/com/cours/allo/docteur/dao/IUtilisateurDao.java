/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import java.util.List;

/**
 *
 * @author ElHadji
 */
public interface IUtilisateurDao {

    public List<Utilisateur> findAllUtilisateurs();

    public Utilisateur findUtilisateurById(int idUtilisateur);

    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant);

    public List<Utilisateur> findUtilisateursByPrenom(String prenom);

    public List<Utilisateur> findUtilisateursByNom(String nom);

    public List<Utilisateur> findUtilisateursByCodePostal(String codePostal);
    
    public Adresse findAdressePrincipale(int idUtilisateur);
    
    public Utilisateur findUtilisateurByIdentifiantAndPassword(String identifiant, String password);

    public Utilisateur createUtilisateur(Utilisateur user);

    public Utilisateur updateUtilisateur(Utilisateur user);

    public boolean deleteUtilisateur(Utilisateur user);

}
