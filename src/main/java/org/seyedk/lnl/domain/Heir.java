package org.seyedk.lnl.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Heir {

	String firstname;
	String gender;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Customer_ID")
	@JsonIgnore
	private Customer heiress;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	String lastname;

	public Heir() {
	}

	public Heir(String firstname, String lastname, String gender, Customer customer) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.heiress = customer;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getGender() {
		return gender;
	}

	public Customer getHeiress() {
		return heiress;
	}

	public long getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setHeiress(Customer heiress) {
		this.heiress = heiress;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return String.format("Heir [id=%s, firstname=%s, lastname=%s, gender=%s]", id, firstname, lastname, gender);
	}

}
