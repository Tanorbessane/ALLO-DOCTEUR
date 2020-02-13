/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.factory;


import com.cours.allo.docteur.dao.entities.RendezVous;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elhad
 */
public abstract class AbstractStatisticSingleton {

    protected List<RendezVous> rdv = new ArrayList<>();
    protected File retFile;
  
    public List<RendezVous> getRendezVous() {
        return rdv;
    }

    public abstract File toXml(List<RendezVous> rdv);

 
    
}
