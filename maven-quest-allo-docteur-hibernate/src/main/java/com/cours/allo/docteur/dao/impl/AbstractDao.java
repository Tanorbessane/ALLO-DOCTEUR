/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import antlr.StringUtils;
import com.cours.allo.docteur.dao.ConnectionHelper;
import com.cours.allo.docteur.dao.EntityFactorySingleton;
import com.cours.allo.docteur.dao.IDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.exception.CustomException;
import static com.cours.allo.docteur.utils.Constants.EXCEPTION_CODE_USER_ALREADY_EXIST;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ElHadji
 * @param <T>
 */
public abstract class AbstractDao<T> implements IDao<T> {

    private Class<T> myClass = null;
    protected EntityManagerFactory emf = EntityFactorySingleton.getInstance().getEntityManagerFactory();

    public AbstractDao(Class<T> myClass) {
        this.myClass = myClass;
    }

    @Override
    public List<T> findAll() {
        List<T> myEntities;
        EntityManager em = emf.createEntityManager();
        try {
            myEntities = em.createNamedQuery(myClass.getSimpleName() + ".findAll").getResultList();
        } catch (Exception e) {
            myEntities = null;
            em.getTransaction().rollback();
        }
        return myEntities;
    }

    @Override
    public T findById(int id) {
        EntityManager em = emf.createEntityManager();
        List<T> myEntities;
        T t = null;
        try {
            t = (T) em.createNamedQuery(myClass.getSimpleName() + ".findById" + myClass.getSimpleName()).setParameter("id" + myClass.getSimpleName(), id).getSingleResult();
        
        } catch (Exception e) {
             return null;
       
        } finally {
            ConnectionHelper.closeSqlResources(em);
        }
        return t;
    }

    @Override
    public List<T> findByCriteria(String criteria, Object valueCriteria) {
        List<T> myEntities;
        EntityManager em = emf.createEntityManager();
        try {
            myEntities = em.createNamedQuery(myClass.getSimpleName() + ".findBy" + criteria).setParameter(criteria.toLowerCase(), valueCriteria).getResultList();
           return myEntities;
        } catch (Exception e) {
            myEntities = null;
        }
        ConnectionHelper.closeSqlResources(em);
        return  myEntities;
    }

    @Override
    public T create(T t) {
        EntityManager em;
          List<T> myEntities;
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            if (t instanceof Utilisateur) {
                Utilisateur utilisateur = (Utilisateur) t;
                //FindByIdentifiant
                myEntities = findByCriteria("Identifiant",utilisateur.getIdentifiant());
                if ( myEntities.size() != 0)
                   throw new CustomException(
                        "L'utilisateur portant l'identitifiant " + utilisateur.getIdentifiant() + " existe deja", EXCEPTION_CODE_USER_ALREADY_EXIST);
                
                utilisateur.setDateCreation(new Date());
                utilisateur.setDateModification(new Date());
                em.persist(utilisateur);
            } else  if (t instanceof Adresse) {
                Adresse adresse = (Adresse) t;
                adresse.setPrincipale(Boolean.TRUE);
                adresse.setVersion(1);
                em.persist(t);
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            t = null;

            em.getTransaction().rollback();
        }
        ConnectionHelper.closeSqlResources(em);
        return t;
    }

    @Override
    public T update(T t) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            if (t instanceof Utilisateur) {
                Utilisateur utilisateur = (Utilisateur) t;
                utilisateur.setDateCreation(new Date());
                utilisateur.setDateModification(new Date());
                utilisateur.setVersion(utilisateur.getVersion()+1);
                em.merge(utilisateur);
            } else if (t instanceof Adresse) {
                Adresse adresse = (Adresse) t;
                adresse.setVersion(adresse.getVersion()+1);
                em.merge(adresse);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            t = null;
        }
        ConnectionHelper.closeSqlResources(em);
        return t;
    }

    @Override
    public boolean delete(T t) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(em.merge(t));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            ConnectionHelper.closeSqlResources(em);
        }
        return true;
    }
}
