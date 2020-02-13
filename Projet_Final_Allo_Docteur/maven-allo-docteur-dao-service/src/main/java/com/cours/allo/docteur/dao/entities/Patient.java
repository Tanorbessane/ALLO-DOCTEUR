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
@Table(name = "patient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p"),
    @NamedQuery(name = "Patient.findByIdPatient", query = "SELECT p FROM Patient p WHERE p.idPatient = :idPatient"),
    @NamedQuery(name = "Patient.findByNumeroSecuriteSociale", query = "SELECT p FROM Patient p WHERE p.numeroSecuriteSociale = :numeroSecuriteSociale"),
    @NamedQuery(name = "Patient.findByVersion", query = "SELECT p FROM Patient p WHERE p.version = :version")})
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPatient")
    private Integer idPatient;
    @Column(name = "numeroSecuriteSociale")
    private String numeroSecuriteSociale;
    @Column(name = "version")
    @Version
    private Integer version;
   /* @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientRdv")
    private List<RendezVous> rendezvousList;
*/
    @ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(referencedColumnName = "idUtilisateur", name = "idUtilisateur")
	private Utilisateur userPatient;

	@OneToMany(mappedBy = "patientRdv", cascade = CascadeType.MERGE, orphanRemoval = true,
			   fetch = FetchType.LAZY)
	private List<RendezVous> rdv = new ArrayList<>();

	/**
	 * @return the userPatient
	 */
	public Utilisateur getUserPatient() {
		return userPatient;
	}
    public Patient() {
    }

    public Patient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public Patient(String numeroSecuriteSociale, Utilisateur idUtilisateur) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        this.userPatient = idUtilisateur;
    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public String getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    public void setNumeroSecuriteSociale(String numeroSecuriteSociale) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Utilisateur getIdUtilisateur() {
        return userPatient;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.userPatient = idUtilisateur;
    }

    @XmlTransient
    public List<RendezVous> getRendezvousList() {
        return rdv;
    }

    public void setRendezvousList(List<RendezVous> rendezvousList) {
        this.rdv = rendezvousList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPatient != null ? idPatient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.idPatient == null && other.idPatient != null) || (this.idPatient != null && !this.idPatient.equals(other.idPatient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cours.allo.docteur.dao.entities.Patient[ idPatient=" + idPatient + " ]";
    }
    
}
