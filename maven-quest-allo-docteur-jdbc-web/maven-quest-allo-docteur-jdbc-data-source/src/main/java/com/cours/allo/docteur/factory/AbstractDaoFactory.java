/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.factory;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public abstract class AbstractDaoFactory {

    public static String className = AbstractDaoFactory.class.getName();
    private static final Log log = LogFactory.getLog(AbstractDaoFactory.class);

    public enum FactoryDaoType {

        JDBC_DAO_FACTORY;
    }

    public abstract IUtilisateurDao getUtilisateurDao();

    public abstract IAdresseDao getAdresseDao();

    /**
     * Méthode pour récupérer une factory de DAO
     *
     * @param daoType
     * @return AbstractDaoFactory
     */
    public static AbstractDaoFactory getFactory(FactoryDaoType daoType) {
        AbstractDaoFactory myDaoFactory = null;

        if (daoType.equals(FactoryDaoType.JDBC_DAO_FACTORY)) {
            log.info("JDBC_DAO_FACTORY activé");
            myDaoFactory = DaoFactory.getInstance();
        }
        return myDaoFactory;

    }
}
