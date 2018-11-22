package org.seyedk.lnl.domain;

import java.io.Serializable;
import java.util.Date;

public class Marriage implements Serializable {

	private Date date;
	private Person husband;
	private String locaiton;
	private String location;
	private Person wife;

	/**
	 * Zero-argument constructor used when deserializing
	 */
	private Marriage() {

	}

	/**
	 * Creates a marriage between a husband and a wife. It is the responsibility of
	 * the caller to invoke {@link Person#addMarriage(Marriage)}.
	 * 
	 * @throws IllegalArgumentException The <code>husband</code> is not
	 *                                  {@link Person#MALE} or the <code>wife</code>
	 *                                  is not {@link Person#FEMALE}.
	 */
	public Marriage(Person husband, Person wife) {
		if (husband.getGender() != Person.MALE) {
			throw new IllegalArgumentException("The husband in a marriage must be male");

		}
		if (wife.getGender() != Person.FEMALE) {
			String s = "The wife in a Marriage must be FEMALE";
			throw new IllegalArgumentException(s);
		}

		this.husband = husband;
		this.wife = wife;
	}

	/**
	 * Returns the date on which the husband and wife were married.
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Returns the husband in this marriage.
	 */
	public Person getHusband() {
		return this.husband;
	}

	/**
	 * Returns the location at which the husband and wife were married.
	 */
	public String getLocation() {
		if (this.location == null) {
			return null;

		} else {
			return this.location.trim();
		}
	}

	/**
	 * Returns the wife in this marriage.
	 */
	public Person getWife() {
		return this.wife;
	}

	/**
	 * Sets the date on which the husband and wife were married.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Sets the location at which the husband and wife were married.
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Returns a brief description of this marriage.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.husband.getFullName() + " and " + this.wife.getFullName() + " were married");

		if (this.date != null) {
			sb.append(" on " + this.date);
		}
		if (this.location != null) {
			sb.append(" in " + this.location);
		}

		return sb.toString();
	}

}
