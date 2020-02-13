/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.ConnectionHelper;
import com.cours.allo.docteur.dao.DataSourceSingleton;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import static com.cours.allo.docteur.utils.Constants.JDBC_DRIVER;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao extends AbstractDao<Utilisateur> implements IUtilisateurDao {
    
    private static final Log log = LogFactory.getLog(UtilisateurDao.class);
    private final static String className = AdresseDao.class.getSimpleName();
    DataSource dataSource = DataSourceSingleton.getInstance().getDataSource();
    private final static String SQL_SELECT_ALL = "SELECT * FROM Utilisateur";
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM Utilisateur LEFT JOIN adresse ON adresse.idUtilisateur = utilisateur.idUtilisateur WHERE utilisateur.idUtilisateur = ?";
    private final static String SQL_SELECT_BY_IDENTIFIANT = "SELECT * FROM utilisateur WHERE utilisateur.identifiant=?";
    private final static String SQL_SELECT_BY_PRENOM = "SELECT * FROM utilisateur WHERE utilisateur.prenom=?";
    private final static String SQL_SELECT_BY_NOM = "SELECT * FROM Utilisateur WHERE utilisateur.nom = ?";
    private final static String SQL_SELECT_CODE_POSTALE = "SELECT * FROM utilisateur LEFT JOIN adresse ON utilisateur.idUtilisateur = adresse.idUtilisateur WHERE adresse.codePostal=?";
    private final static String SQL_INSERT = "INSERT INTO utilisateur(idUtilisateur,civilite,prenom,nom,identifiant,motPasse,dateNaissance,"
            + "dateCreation,dateModification,actif,marquerEffacer,version) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1);";
    private final static String SQL_SELECT_ORDER = "SELECT * FROM utilisateur ORDER BY utilisateur.idUtilisateur DESC LIMIT 1";
    private final static String SQL_SELECT_COUNT = "SELECT COUNT(*) as count FROM utilisateur WHERE identifiant LIKE ?";
    private final static String SQL_UPDATE = "UPDATE Utilisateur SET civilite = ?, prenom = ?, nom = ?, identifiant = ?, motPasse = ?, dateNaissance = ?, dateCreation = ?, dateModification = ?, actif = ?, marquerEffacer = ?, version = ? WHERE idUtilisateur = ?";
    private final static String SQL_DELETE = "DELETE FROM utilisateur WHERE idUtilisateur=?";
     private final static String SQL_UPDATE_WO_PASSWORD ="UPDATE Utilisateur SET civilite = ?, prenom = ?, nom = ?, identifiant = ?, dateNaissance = ?, dateCreation = ?, dateModification = ?, actif = ?, marquerEffacer = ?, version = ? WHERE idUtilisateur = ?";
    
    public UtilisateurDao() {
        super(Utilisateur.class);
        log.debug("--> ************ Initialisation de " + className + " ************");
    }

    
    /**
     * Holder
     */
    private static class MySingletonHolder {

        /**
         * Instance unique non préinitialisée
         */
        private final static UtilisateurDao instance = new UtilisateurDao();
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     *
     * @return
     */
    public static UtilisateurDao getInstance() {
        if (UtilisateurDao.MySingletonHolder.instance == null) {
            log.debug("--> Nouvelle Instance de " + className);
        } else {
            log.debug("--> Re-Utilisation de l'instance " + className + " dejà existante");
        }
        return UtilisateurDao.MySingletonHolder.instance;
    }
    
    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Utilisateur> result;
        Utilisateur curUser;
        
        result = new ArrayList<>();
        
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                curUser = ResulSetUser(resultSet);
                curUser.setAdresses(getAddresseUser(curUser.getIdUtilisateur()));
                result.add(curUser);
            }
        } catch (Exception e) {
            return null;
        } finally {
            ConnectionHelper.closeSqlResources(connection, preparedStatement, resultSet);
        }
        
        if (result.size() == 0) {
            result = null;
        }
        
        return result;
    }
    
    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, idUtilisateur);
            resultSet = preparedStatement.executeQuery();
            Map<Integer, Utilisateur> map = new HashMap();
            List<Adresse> adresses = new ArrayList();
            while (resultSet.next()) {
                Adresse adresse = this.ResulSetAdresse(resultSet);
                adresses.add(adresse);
                Utilisateur utilisateur = this.ResulSetUser(resultSet);
                map.put(utilisateur.getIdUtilisateur(), utilisateur);
            }
            for (Map.Entry<Integer, Utilisateur> entry : map.entrySet()) {
                Adresse adresseByUser = new Adresse();
                for (Adresse adresse : adresses) {
                    if (Objects.equals(adresse.getIdUtilisateur(), entry.getKey())) {
                        adresseByUser = adresse;
                    }
                }
                entry.getValue().setAdresses(adresses);
                Utilisateur utilisateur = entry.getValue();
                return utilisateur;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(connection, preparedStatement, resultSet);
        }
        log.debug("Sortie de la methode");
        return null;
    }
    
    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = dataSource.getConnection();
            ResultSet result = null;
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(SQL_SELECT_BY_IDENTIFIANT);
                preparedStatement.setString(1, String.valueOf(identifiant));
                result = preparedStatement.executeQuery();
                while (result.next()) {
                    int i = 0;
                    for (Utilisateur utilisateur : utilisateurs) {
                        if (utilisateur.getIdUtilisateur() == result.getInt("idUtilisateur")) {
                            utilisateur.getAdresses().add(ResulSetAdresse(result));
                            i = 1;
                        }
                    }
                    if (i == 0) {
                        Utilisateur user = ResulSetUser(result);
                        user.getAdresses().add(ResulSetAdresse(result));
                        utilisateurs.add(user);
                    }
                }
            } finally {
                ConnectionHelper.closeSqlResources(connection, preparedStatement, result);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utilisateurs;
    }
    
    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Utilisateur> result;
        Utilisateur curUser;
        
        result = new ArrayList<>();
        
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_PRENOM);
            
            preparedStatement.setString(1, prenom);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                curUser = ResulSetUser(resultSet);
                curUser.setAdresses(getAddresseUser(curUser.getIdUtilisateur()));
                result.add(curUser);
            }
        } catch (Exception e) {
            return null;
        } finally {
            ConnectionHelper.closeSqlResources(connection, preparedStatement, resultSet);
        }
        
        if (result.isEmpty()) {
            return null;
        }
        
        return result;
    }
    
    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Utilisateur> result;
        Utilisateur curUser;
        
        result = new ArrayList<>();
        
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_NOM);
            
            preparedStatement.setString(1, nom);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                curUser = ResulSetUser(resultSet);
                curUser.setAdresses(getAddresseUser(curUser.getIdUtilisateur()));
                result.add(curUser);
            }
        } catch (Exception e) {
            return null;
        } finally {
            ConnectionHelper.closeSqlResources(connection, preparedStatement, resultSet);
        }
        
        if (result.isEmpty()) {
            return null;
        }
        
        return result;
    }
    
    @Override
    public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Utilisateur> result;
        Utilisateur curUser;
        
        result = new ArrayList<>();
        
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_CODE_POSTALE);
            
            preparedStatement.setString(1, codePostal);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                curUser = ResulSetUser(resultSet);
                curUser.setAdresses(getAddresseUser(curUser.getIdUtilisateur()));
                result.add(curUser);
            }
        } catch (Exception e) {
            return null;
        } finally {
            ConnectionHelper.closeSqlResources(connection, preparedStatement, resultSet);
        }
        
        if (result.isEmpty()) {
            return null;
        }
        
        return result;
    }
    
    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            try {
                preparedStatement = connection.prepareStatement(SQL_SELECT_COUNT);
                preparedStatement.setString(1, user.getIdentifiant());
                result = preparedStatement.executeQuery();
                result.next();
                if (result.getInt("count") != 0) {
                    return null;
                }
                preparedStatement = connection.prepareStatement(SQL_INSERT);
                preparedStatement.setString(1, null);
                preparedStatement.setString(2, user.getCivilite());
                preparedStatement.setString(3, user.getPrenom());
                preparedStatement.setString(4, user.getNom());
                preparedStatement.setString(5, user.getIdentifiant());
                preparedStatement.setString(6, user.getMotPasse());
                preparedStatement.setString(7, String.valueOf(new java.sql.Timestamp(user.getDateNaissance().getTime())));
                preparedStatement.setString(8, String.valueOf(new java.sql.Timestamp(System.currentTimeMillis())));
                preparedStatement.setString(9, String.valueOf(new java.sql.Timestamp(System.currentTimeMillis())));
                preparedStatement.setString(10, String.valueOf(user.isActif() ? 1 : 0));
                preparedStatement.setString(11, String.valueOf(user.isMarquerEffacer() ? 1 : 0));
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER);
                result = preparedStatement.executeQuery();
                result.next();
                user = ResulSetUser(result);
            } finally {
                ConnectionHelper.closeSqlResources(connection, preparedStatement, result);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int newVersion;
        
        newVersion = findUtilisateurById(user.getIdUtilisateur()).getVersion() + 1;
        user.setVersion(newVersion);
        user.setDateModification(new Date());
        
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            
            preparedStatement.setString(1, user.getCivilite());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getNom());
            preparedStatement.setString(4, user.getIdentifiant());
            preparedStatement.setString(5, user.getMotPasse());
            preparedStatement.setTimestamp(6, new Timestamp(user.getDateNaissance().getTime()));
            preparedStatement.setTimestamp(7, new Timestamp(user.getDateCreation().getTime()));
            preparedStatement.setTimestamp(8, new Timestamp(user.getDateModification().getTime()));
            preparedStatement.setBoolean(9, user.isActif());
            preparedStatement.setBoolean(10, user.isMarquerEffacer());
            preparedStatement.setInt(11, user.getVersion());
            preparedStatement.setInt(12, user.getIdUtilisateur());
            
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return null;
        } finally {
            ConnectionHelper.closeSqlResources(connection, preparedStatement, null);
        }
        
        return user;
    }
 @Override
    public Utilisateur updateUtilisateurWithoutMotPasse(Utilisateur user) {
         Connection connection = null;
        PreparedStatement preparedStatement = null;
        int newVersion;
        
        newVersion = findUtilisateurById(user.getIdUtilisateur()).getVersion() + 1;
        user.setVersion(newVersion);
        user.setDateModification(new Date());
        
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_WO_PASSWORD);
            
            preparedStatement.setString(1, user.getCivilite());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getNom());
            preparedStatement.setString(4, user.getIdentifiant());
            preparedStatement.setTimestamp(5, new Timestamp(user.getDateNaissance().getTime()));
            preparedStatement.setTimestamp(6, new Timestamp(user.getDateCreation().getTime()));
            preparedStatement.setTimestamp(7, new Timestamp(user.getDateModification().getTime()));
            preparedStatement.setBoolean(8, user.isActif());
            preparedStatement.setBoolean(9, user.isMarquerEffacer());
            preparedStatement.setInt(10, user.getVersion());
            preparedStatement.setInt(11, user.getIdUtilisateur());
            
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return null;
        } finally {
            ConnectionHelper.closeSqlResources(connection, preparedStatement, null);
        }
        
        return user;
    }
   
    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
        Connection connection;
        boolean ret = false;
        try {
            Class.forName(JDBC_DRIVER);
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(SQL_DELETE);
                preparedStatement.setString(1, String.valueOf(user.getIdUtilisateur()));
                int result = preparedStatement.executeUpdate();
                if (result == 1) {
                    ret = true;
                }
            } finally {
                ConnectionHelper.closeSqlResources(connection, preparedStatement, null);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    @Override
     public boolean deleteUtilisateur(int id) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (Exception e) {
            return false;
        } finally {
            ConnectionHelper.closeSqlResources(connection, preparedStatement, null);
        }
        return true;
    }
}
