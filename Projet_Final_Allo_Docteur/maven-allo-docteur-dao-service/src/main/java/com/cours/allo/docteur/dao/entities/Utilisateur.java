/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author TA013BE
 */
@Entity
@Table(name = "utilisateur")
@XmlRootElement
@NamedQueries({
     @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
    //@NamedQuery(name ="Utilisateur.findByCodePostal", query = "SELECT u FROM Utilisateur u LEFT JOIN Adresse.idUtilisateur a WHERE u.idUtilisateur = a.idUtilisateur AND a.codePostal = :codePostal"),
    @NamedQuery(name ="Utilisateur.findByCodePostal", query = "SELECT u FROM Adresse a LEFT JOIN a.idUtilisateur u WHERE a.codePostal = :codepostal"),
    @NamedQuery(name = "Utilisateur.findByIdUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.idUtilisateur = :idUtilisateur"),
    @NamedQuery(name = "Utilisateur.findByCivilite", query = "SELECT u FROM Utilisateur u WHERE u.civilite = :civilite"),
    @NamedQuery(name = "Utilisateur.findByPrenom", query = "SELECT u FROM Utilisateur u WHERE u.prenom = :prenom"),
    @NamedQuery(name = "Utilisateur.findByNom", query = "SELECT u FROM Utilisateur u WHERE u.nom = :nom"),
    @NamedQuery(name = "Utilisateur.findByIdentifiant", query = "SELECT u FROM Utilisateur u WHERE u.identifiant = :identifiant"),
    @NamedQuery(name = "Utilisateur.findByMotPasse", query = "SELECT u FROM Utilisateur u WHERE u.motPasse = :motPasse"),
    @NamedQuery(name = "Utilisateur.findByNumeroTelephone", query = "SELECT u FROM Utilisateur u WHERE u.numeroTelephone = :numeroTelephone"),
    @NamedQuery(name = "Utilisateur.findByDateNaissance", query = "SELECT u FROM Utilisateur u WHERE u.dateNaissance = :dateNaissance"),
    @NamedQuery(name = "Utilisateur.findByDateCreation", query = "SELECT u FROM Utilisateur u WHERE u.dateCreation = :dateCreation"),
    @NamedQuery(name = "Utilisateur.findByDateModification", query = "SELECT u FROM Utilisateur u WHERE u.dateModification = :dateModification"),
    @NamedQuery(name = "Utilisateur.findByActif", query = "SELECT u FROM Utilisateur u WHERE u.actif = :actif"),
    @NamedQuery(name = "Utilisateur.findByMarquerEffacer", query = "SELECT u FROM Utilisateur u WHERE u.marquerEffacer = :marquerEffacer"),
    @NamedQuery(name = "Utilisateur.findByVersion", query = "SELECT u FROM Utilisateur u WHERE u.version = :version"),
    @NamedQuery(name = "Utilisateur.findByIdentifiantAndPassword", query = "SELECT u FROM Utilisateur u WHERE u.identifiant = :identifiant and u.motPasse = :motPasse"),
    @NamedQuery(name = "Utilisateur.findMainAddress", query = "SELECT a FROM Adresse a INNER JOIN a.idUtilisateur u WHERE u.idUtilisateur = :idUtilisateur")
})
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUtilisateur")
    private Integer idUtilisateur;
    @Column(name = "civilite")
    private String civilite;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "nom")
    private String nom;
    @Column(name = "identifiant")
    private String identifiant;
    @Column(name = "motPasse")
    private String motPasse;
    @Column(name = "numeroTelephone")
    private String numeroTelephone;
    @Column(name = "dateNaissance")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNaissance;
    @Column(name = "dateCreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "dateModification")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    @Column(name = "actif")
    private Boolean actif;
    @Column(name = "marquerEffacer")
    private Boolean marquerEffacer;
    @Column(name = "version")
    @Version
    private Integer version;
   @OneToMany(mappedBy = "idUtilisateur", cascade = CascadeType.MERGE, orphanRemoval=true, fetch = FetchType.EAGER)
    private List<Adresse> adresses = new ArrayList<>();

	@OneToMany(mappedBy = "userPatient", cascade = CascadeType.MERGE, orphanRemoval = true,
			   fetch = FetchType.LAZY)
	private List<Patient> patients = new ArrayList<>();

	@OneToMany(mappedBy = "userDoctor", cascade = CascadeType.MERGE, orphanRemoval = true,
			   fetch = FetchType.LAZY)
	private List<Medecin> doctors = new ArrayList<>();
    public Utilisateur() {
    }

    public Utilisateur(Integer idUtilisateur, String civilite, String prenom, String nom, String identifiant, String motPasse, String numeroTelephone, Date dateNaissance, Date dateCreation, Date dateModification, Boolean actif, Boolean marquerEffacer, Integer version, List<Adresse> adresses) {
        this.idUtilisateur = idUtilisateur;
        this.civilite = civilite;
        this.prenom = prenom;
        this.nom = nom;
        this.identifiant = identifiant;
        this.motPasse = motPasse;
        this.numeroTelephone = numeroTelephone;
        this.dateNaissance = dateNaissance;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.actif = actif;
        this.marquerEffacer = marquerEffacer;
        this.version = version;
        this.adresses = adresses;

    }

    public Utilisateur(Integer idUtilisateur, String civilite, String prenom, String nom, String identifiant, String motPasse, String numeroTelephone, Date dateNaissance, List<Adresse> adresses) {
        this(idUtilisateur, civilite, prenom, nom, identifiant, motPasse, numeroTelephone, dateNaissance, null, null, true, false, 0, adresses);
    }

    public Utilisateur(String civilite, String prenom, String nom, String identifiant, String motPasse, Date dateNaissance) {
        this(null, civilite, prenom, nom, identifiant, motPasse, null, dateNaissance, null, null, true, false, 0);
    }

    public Utilisateur(Integer idUtilisateur, String civilite, String prenom, String nom, String identifiant, String motPasse, String numeroTelephone, Date dateNaissance, Date dateCreation, Date dateModification, Boolean actif, Boolean marquerEffacer, Integer version) {
        this(idUtilisateur, civilite, prenom, nom, identifiant, motPasse, numeroTelephone, dateNaissance, dateCreation, dateModification, true, false, version, null);
    }

    public Utilisateur(String civilite, String prenom, String nom, String identifiant, String motPasse, String numeroTelephone, Date dateNaissance) {
        this(null, civilite, prenom, nom, identifiant, motPasse, numeroTelephone, dateNaissance, null, null, true, false, 0, null);
    }

    public Utilisateur(Integer idUtilisateur) {
        this(idUtilisateur, null, null, null, null, null, null, null, null, null, true, false, 0, null);
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public Boolean getMarquerEffacer() {
        return marquerEffacer;
    }

    public void setMarquerEffacer(Boolean marquerEffacer) {
        this.marquerEffacer = marquerEffacer;
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }

   @XmlTransient
    public List<Adresse> getAdresses() {
        return adresses;
    }


    public void setAdresseSet(List<Adresse> adresses) {
        this.adresses = adresses;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
        return hash;
    }

@XmlTransient

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.idUtilisateur == null && other.idUtilisateur != null) || (this.idUtilisateur != null && !this.idUtilisateur.equals(other.idUtilisateur))) {
            return false;
        }
        return true;
    }
@XmlTransient

    public List<Medecin> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Medecin> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return String.format("\n[idUtilisateur=%s, civilite=%s, prenom=%s, nom=%s, identifiant=%s, motPasse=%s, dateNaissance=%s, dateCreation=%s, dateModification=%s, actif=%s, marquerEffacer=%s ,version=%s]\n", idUtilisateur, civilite, prenom, nom, identifiant, motPasse, dateNaissance, dateCreation, dateModification, actif, marquerEffacer, version);
    }
    public Adresse getAdressePrincipale() {
		Iterator<Adresse> it;
		Adresse curAddr;

		if (adresses == null)
			return null;

		it = adresses.iterator();

		while (it.hasNext()) {
			curAddr = (Adresse) it.next();
			if (curAddr.getPrincipale())
				return curAddr;
		}

		return null;
	}
}
