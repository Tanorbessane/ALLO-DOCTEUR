/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.ConnectionHelper;
import com.cours.allo.docteur.dao.DataSourceSingleton;
import com.cours.allo.docteur.dao.IDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ElHadji
 * @param <T>
 */
public abstract class AbstractDao<T> implements IDao<T> {
private Class<T> myClass= null;
    public AbstractDao(Class<T> myClass) {
        this.myClass=myClass;
    }

    @Override
    public List<T> findAll() {
        myClass.getSimpleName();
        return null;
    }

    @Override
    public T findById(int id) {
        return null;
    }

    @Override
    public List<T> findByCriteria(String criteria, Object valueCriteria) {
        return null;
    }

    @Override
    public T create(T t) {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public boolean delete(T t) {
        return false;
    }

    public List<Adresse> getAddresseUser(int userId) {
        List<Adresse> result;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resSet = null;

        result = new ArrayList<>();

        try {
            connection = DataSourceSingleton.getInstance().getDataSource().getConnection();
            preparedStatement = connection.prepareStatement("SELECT Adresse.* FROM Adresse WHERE idUtilisateur = ?");

            preparedStatement.setInt(1, userId);

            resSet = preparedStatement.executeQuery();
            while (resSet.next()) {
                result.add(ResulSetAdresse(resSet));
            }
        } catch (Exception e) {
            return null;
        } finally {
            ConnectionHelper.closeSqlResources(connection, preparedStatement, resSet);
        }

        if (result.isEmpty()) {
            result = null;
        }

        return result;
    }

    public Adresse ResulSetAdresse(ResultSet result) {
        Adresse adresse = new Adresse();
        try {
            adresse.setIdAdresse(result.getInt("idAdresse"));
            adresse.setRue(result.getString("rue"));
            adresse.setCodePostal(result.getString("codePostal"));
            adresse.setVille(result.getString("ville"));
            adresse.setPays(result.getString("pays"));
            adresse.setPrincipale(result.getBoolean("principale"));
            adresse.setVersion(result.getInt("version"));
            adresse.setIdUtilisateur(result.getInt("idUtilisateur"));

        } catch (SQLException ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adresse;
    }

    public Utilisateur ResulSetUser(ResultSet result) {
        Utilisateur utilisateur = new Utilisateur();
        try {
            utilisateur.setIdUtilisateur(result.getInt("idUtilisateur"));
            utilisateur.setCivilite(result.getString("civilite"));
            utilisateur.setPrenom(result.getString("prenom"));
            utilisateur.setNom(result.getString("nom"));
            utilisateur.setIdentifiant(result.getString("identifiant"));
            utilisateur.setMotPasse(result.getString("motPasse"));
            utilisateur.setDateNaissance(result.getDate("dateNaissance"));
            utilisateur.setDateCreation(result.getDate("dateCreation"));
            utilisateur.setDateModification(result.getDate("dateModification"));
            utilisateur.setActif(result.getBoolean("actif"));
            utilisateur.setMarquerEffacer(result.getBoolean("marquerEffacer"));
            utilisateur.setVersion(result.getInt("version"));
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utilisateur;
    }
}
