/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author TA013BE
 */
@Entity
@Table(name = "adresse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a"),
    @NamedQuery(name = "Adresse.findByIdAdresse", query = "SELECT a FROM Adresse a WHERE a.idAdresse = :idAdresse"),
    @NamedQuery(name = "Adresse.findByRue", query = "SELECT a FROM Adresse a WHERE a.rue = :rue"),
    @NamedQuery(name = "Adresse.findBycodepostal", query = "SELECT a FROM Adresse a WHERE a.codePostal = :codepostal"),
    @NamedQuery(name = "Adresse.findByVille", query = "SELECT a FROM Adresse a WHERE a.ville = :ville"),
    @NamedQuery(name = "Adresse.findByPays", query = "SELECT a FROM Adresse a WHERE a.pays = :pays"),
    @NamedQuery(name = "Adresse.findByPrincipale", query = "SELECT a FROM Adresse a WHERE a.principale = :principale"),
    @NamedQuery(name = "Adresse.findByVersion", query = "SELECT a FROM Adresse a WHERE a.version = :version")})
public class Adresse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdresse")
    private Integer idAdresse;
    @Column(name = "rue")
    private String rue;
    @Column(name = "codePostal")
    private String codePostal;
    @Column(name = "ville")
    private String ville;
    @Column(name = "pays")
    private String pays;
    @Column(name = "principale")
    private Boolean principale;
    @Column(name = "version")
    @Version
    private Integer version;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Utilisateur idUtilisateur;

    public Adresse() {
    }

    public Adresse(Integer idAdresse, String rue, String codePostal, String ville, String pays, Utilisateur idUtilisateur) {
        this.idAdresse = idAdresse;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.idUtilisateur = idUtilisateur;
    }

    public Adresse(String rue, String codePostal, String ville, String pays, Utilisateur idUtilisateur) {
        this(null, rue, codePostal, ville, pays, idUtilisateur);
    }

    public Adresse(Integer idAdresse) {
        this(idAdresse, null, null, null, null, null);
    }

    public Integer getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Boolean getPrincipale() {
        return principale;
    }

    public void setPrincipale(Boolean principale) {
        this.principale = principale;
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdresse != null ? idAdresse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.idAdresse == null && other.idAdresse != null) || (this.idAdresse != null && !this.idAdresse.equals(other.idAdresse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("[idAdresse=%s , rue=%s , ville=%s , codePostal=%s , pays=%s , principale=%s , version=%s, idUtilisateur=%s]\n", idAdresse, rue, ville, codePostal, pays, principale, version, idUtilisateur);
    }
    
    
}
