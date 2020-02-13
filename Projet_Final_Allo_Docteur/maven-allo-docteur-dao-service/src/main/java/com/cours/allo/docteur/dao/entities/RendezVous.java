package com.cours.allo.docteur.dao.entities;

import java.io.Serializable;
import java.util.Date;

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

@Entity
@Table(name = "RendezVous")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "RendezVous.findAll", query = "SELECT r FROM RendezVous r"),
		@NamedQuery(name = "RendezVous.findById", query = "SELECT r FROM RendezVous r WHERE r.idRendezVous = :idRendezVous"),
		@NamedQuery(name = "RendezVous.findByJour", query = "SELECT r FROM RendezVous r WHERE r.jour = :jour"),
		@NamedQuery(name = "RendezVous.Revenue", query = "SELECT SUM(r.prixConsultation) FROM RendezVous r WHERE r.present = 1"),
		@NamedQuery(name = "RendezVous.findAllFutur", query = "SELECT r FROM RendezVous r WHERE r.jour > :jour"),
		@NamedQuery(name = "RendezVous.findRendezVousByJourAndIdMedecin", query = "SELECT r FROM RendezVous r INNER JOIN r.creneauRdv c WHERE c.doctorCreneau.idMedecin = :idMedecin and r.jour = :jour"),
		@NamedQuery(name = "RendezVous.findByIdMedecin", query = "SELECT r FROM RendezVous r INNER JOIN r.creneauRdv c WHERE c.doctorCreneau.idMedecin = :idmedecin"),
                @NamedQuery(name = "RendezVous.findByIdPatient", query = "SELECT r FROM RendezVous r INNER JOIN r.patientRdv p WHERE p.idPatient = :idpatient")

})          
public class RendezVous implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRendezVous")
	private Integer idRendezVous;

	@Column(name = "jour")
	private Date jour;

	@Column(name = "prixConsultation")
	private Double prixConsultation;

	@Column(name = "present")
	private Boolean present;

	@Version
	@Column(name = "version")
	private Integer version;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(referencedColumnName = "idPatient", name = "idPatient")
	private Patient patientRdv;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(referencedColumnName = "idCreneau", name = "idCreneau")
	private Creneau creneauRdv;

	/**
	 * @return the patientRdv
	 */
	public Patient getPatientRdv() {
		return patientRdv;
	}

	/**
	 * @return the present
	 */
	public Boolean getPresent() {
		return present;
	}

	public Creneau getCreneau() {
		return creneauRdv;
	}

	public Date getJour() {
		return jour;
	}

	public void setJour(Date jour) {
		this.jour = jour;
	}

}