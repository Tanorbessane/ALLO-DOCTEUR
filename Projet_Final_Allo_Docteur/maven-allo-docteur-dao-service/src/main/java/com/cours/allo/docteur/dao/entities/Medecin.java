/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TA013BE
 */
@Entity
        @Table(name = "medecin")
        @XmlRootElement
        @NamedQueries({
            @NamedQuery(name = "Medecin.findAll", query = "SELECT m FROM Medecin m"),
            @NamedQuery(name = "Medecin.findByIdMedecin", query = "SELECT m FROM Medecin m WHERE m.idMedecin = :idMedecin"),
            @NamedQuery(name = "Medecin.findByNumeroAccreditation", query = "SELECT m FROM Medecin m WHERE m.numeroAccreditation = :numeroAccreditation"),
            @NamedQuery(name = "Medecin.findByVersion", query = "SELECT m FROM Medecin m WHERE m.version = :version")})
public class Medecin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMedecin")
    private Integer idMedecin;
    @Column(name = "numeroAccreditation")
    private String numeroAccreditation;
    @Column(name = "version")
    @Version
    private Integer version;
    /*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMedecin")
    private List<Creneau> creneauList;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;
*/
    @ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(referencedColumnName = "idUtilisateur", name = "idUtilisateur")
	private Utilisateur userDoctor;

	@OneToMany(mappedBy = "doctorCreneau", cascade = CascadeType.MERGE, orphanRemoval = true,
			   fetch = FetchType.LAZY)
	private List<Creneau> creneauList = new ArrayList<>();
    public Medecin() {
    }

    public Medecin(Integer idMedecin) {
        this.idMedecin = idMedecin;
    }

    public Integer getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Integer idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getNumeroAccreditation() {
        return numeroAccreditation;
    }

    public void setNumeroAccreditation(String numeroAccreditation) {
        this.numeroAccreditation = numeroAccreditation;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @XmlTransient
    public List<Creneau> getCreneauList() {
        return creneauList;
    }

    public void setCreneauList(List<Creneau> creneauList) {
        this.creneauList = creneauList;
    }

    public Utilisateur getIdUtilisateur() {
        return userDoctor;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.userDoctor = idUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedecin != null ? idMedecin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medecin)) {
            return false;
        }
        Medecin other = (Medecin) object;
        if ((this.idMedecin == null && other.idMedecin != null) || (this.idMedecin != null && !this.idMedecin.equals(other.idMedecin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cours.allo.docteur.dao.entities.Medecin[ idMedecin=" + idMedecin + " ]";
    }

}
