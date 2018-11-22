package org.seyedk.lnl.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {

	private String firstName;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "heiress")
	Set<Heir> heirs;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUSTOMER_ID")
	private Long id;
	private String lastName;

	private String secretKey;

	protected Customer() {
	}

	public Customer(String firstName, String lastName, String secretKey, Set<Heir> heirs) {
		super();
		this.firstName = firstName;
		this.heirs = heirs;
		this.lastName = lastName;
		this.secretKey = secretKey;
	}

	public String getFirstName() {
		return firstName;
	}

	public Set<Heir> getHeirs() {
		return heirs;
	}

	public Long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setHeirs(Set<Heir> heirs) {
		this.heirs = heirs;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	@Override
	public String toString() {
		return String.format("Customer [firstName=%s, heirs=%s, id=%s, lastName=%s]", firstName, heirs, id, lastName);
	}
}