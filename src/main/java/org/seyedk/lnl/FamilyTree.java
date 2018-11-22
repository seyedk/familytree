package org.seyedk.lnl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.seyedk.lnl.domain.Person;

public class FamilyTree implements Serializable {

	private Map<Integer, Person> people;

	/**
	 * Creates an empty family tree
	 */
	public FamilyTree() {
		this.people = new HashMap<Integer, Person>();
	}

	/**
	 * Adds a person to this family tree. If a person with the same id as the person
	 * being added already exists in this family tree, the old person is removed and
	 * replaced with the new.
	 */
	public void addPerson(Person person) {
		this.people.put(person.getId(), person);
	}

	/**
	 * Returns whether or not this family tree contains a person with the given id.
	 */
	public boolean containsPerson(int id) {
		return this.people.containsKey(new Integer(id));
	}

	/**
	 * Returns a collection of <code>Person</code>s that are in this family
	 */
	public Collection<Person> getPeople() {
		return this.people.values();
	}

	/**
	 * Returns a person in this family tree with a given id. If no person with that
	 * id exists in this family tree, then <code>null</code> is returned.
	 */
	public Person getPerson(int id) {
		return this.people.get(new Integer(id));
	}

	//////////////////////// Utility Methods ///////////////////////

	@Override
	public String toString() {
		return "A FamilyTree with " + this.people.size() + " people";
	}
}
