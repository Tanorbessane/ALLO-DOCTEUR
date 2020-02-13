/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.IDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.exception.CustomException;
import static com.cours.allo.docteur.utils.Constants.EXCEPTION_CODE_USER_ALREADY_EXIST;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ElHadji
 * @param <T>
 */
@Transactional
public abstract class AbstractDao<T> implements IDao<T> {

    @PersistenceContext
    private EntityManager em;
    private Class<T> myClass = null;

    public AbstractDao(Class<T> myClass) {
        this.myClass = myClass;
    }

    @Override
    public List<T> findAll() {
        List<T> myEntities;
        try {
            myEntities = em.createNamedQuery(myClass.getSimpleName() + ".findAll").getResultList();
        } catch (Exception e) {
            myEntities = null;
        }
        return myEntities;
    }

    @Override
    public T findById(int id) {
        T t = null;
        try {
            t = (T) em.createNamedQuery(myClass.getSimpleName() + ".findById" + myClass.getSimpleName()).setParameter("id" + myClass.getSimpleName(), id).getSingleResult();

        } catch (Exception e) {
        }
        return t;
    }

    @Override
    public List<T> findByCriteria(String criteria, Object valueCriteria) {
        List<T> myEntities;
        myEntities = null;
        try {
            myEntities = em.createNamedQuery(myClass.getSimpleName() + ".findBy" + criteria).setParameter(criteria.toLowerCase(), valueCriteria).getResultList();
            return myEntities;
        } catch (Exception e) {
            myEntities = null;
        }
        return myEntities;
    }

    @Override
    public T create(T t) {
        List<T> myEntities;
        try {
            if (t instanceof Utilisateur) {
                Utilisateur utilisateur = (Utilisateur) t;
                myEntities = findByCriteria("Identifiant", utilisateur.getIdentifiant());
                if (myEntities.size() != 0) {
                    throw new CustomException(
                            "L'utilisateur portant l'identitifiant " + utilisateur.getIdentifiant() + " existe deja", EXCEPTION_CODE_USER_ALREADY_EXIST);
                }
                utilisateur.setDateCreation(new Date());
                utilisateur.setDateModification(new Date());
                em.persist(utilisateur);
            } else {
                em.persist(t);
            }
        } catch (Exception e) {
        }
        return t;
    }

    @Override
    public T update(T t) {
        try {
            if (t instanceof Utilisateur) {
                Utilisateur utilisateur = (Utilisateur) t;
                utilisateur.setDateCreation(new Date());
                utilisateur.setDateModification(new Date());
                utilisateur = em.merge(utilisateur);
                t = (T) utilisateur;
            } else if (t instanceof Adresse) {
                Adresse adresse = (Adresse) t;
                adresse = em.merge(adresse);
                t = (T) adresse;
            }
        } catch (Exception e) {
        }
        //   em.flush();
        return t;
    }

    @Override
    public boolean delete(T t) {
        try {
            em.remove(em.merge(t));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
