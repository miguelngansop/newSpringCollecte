package com.sarki.micro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
@Table(name="operations")
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_operations",discriminatorType=DiscriminatorType.STRING,length=15)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Retrait.class, name = "retrait"), @Type(value = Versement.class, name = "versement"), 
	 @Type(value = Prelevement.class, name = "Prelevement")})
public abstract class Operation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7341440696856003055L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numOperation;
	@Min(0)
	private double montant;
	
	@Min(0)
	private double soldePrecedent;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
	
	@ManyToOne 
	@JoinColumn(name="NUM_CPTE")
	private Compte compte;
	
	public Operation() {}

	public Operation(long id, double montant, Date createdAt, Date updatedAt) {
		super();
		this.numOperation = id;
		this.montant = montant;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public long getId() {
		return numOperation;
	}

	public void setId(long id) {
		this.numOperation = id;
	}

	public double getSoldePrecedent() {
		return soldePrecedent;
	}

	public void setSoldePrecedent(double soldePrecedent) {
		this.soldePrecedent = soldePrecedent;
	}
	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public long getNumOperation() {
		return numOperation;
	}

	public void setNumOperation(long numOperation) {
		this.numOperation = numOperation;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}


	

	
}
