/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import static com.cours.allo.docteur.utils.Constants.PERSISTANCE_UNIT;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class EntityFactorySingleton {

    private static final Log log = LogFactory.getLog(EntityFactorySingleton.class);
    private final static String className = EntityFactorySingleton.class.getSimpleName();
    private EntityManagerFactory emFactory = null;

    private EntityFactorySingleton() {
        log.debug("--> ************ Initialisation de " + className + " ************");
        emFactory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT);
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emFactory;
    }
    private static class SingletonHolder {

        private final static EntityFactorySingleton instance = new EntityFactorySingleton();
    }

    public static EntityFactorySingleton getInstance() {
        return SingletonHolder.instance;
    }

}
