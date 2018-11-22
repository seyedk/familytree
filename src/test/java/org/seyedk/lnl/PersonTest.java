package org.seyedk.lnl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seyedk.lnl.domain.Marriage;
import org.seyedk.lnl.domain.Person;
import org.seyedk.lnl.exception.FamilyTreeException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {

	@Test
	public void testAddMarriage() {
		Person husband = new Person(1, Person.MALE);
		Person wife = new Person(2, Person.FEMALE);
		Marriage m = new Marriage(husband, wife);
		husband.addMarriage(m);
		assertTrue(husband.getMarriages().contains(m));
	}

	@Test
	public void testDateOfDeathCannotBeEarilerThanDateOfBirth() {
		Date dateOfBirth = new Date(12000L);

		Date dateOfDeath = new Date(10000L);
		Person p = new Person(1, Person.FEMALE);
		p.setDateOfBirth(dateOfBirth);
		try {
			p.setDateOfDeath(dateOfDeath);
			fail("Shoudl have thrown FamilyTreeException");
		} catch (FamilyTreeException ex) {
			// passed ... ;)
		}

	}

	@Test
	public void testFemaleNotInMarriage() {
		Person husband = new Person(1, Person.MALE);
		Person wife = new Person(2, Person.FEMALE);
		Marriage m = new Marriage(husband, wife);

		Person p = new Person(3, Person.FEMALE);
		try {
			p.addMarriage(m);
			fail("Should have thrown an FamilyTreeException");

		} catch (FamilyTreeException ex) {
			// pass...
		}
	}

	@Test
	public void testMaleNotInMarriage() {
		Person husband = new Person(1, Person.MALE);
		Person wife = new Person(2, Person.FEMALE);
		Marriage m = new Marriage(husband, wife);

		Person p = new Person(3, Person.MALE);
		try {
			p.addMarriage(m);
			fail("Should have thrown an FamilyTreeException");

		} catch (FamilyTreeException ex) {
			// pass...
		}
	}

	@Test
	public void WhenAFemaleIsSetAsFatherItThrowsFamilyTreeException() {
		Person child = new Person(1, Person.FEMALE);
		Person father = new Person(2, Person.FEMALE);
		try {
			child.setFather(father);
			fail("Should have thrown an FamilyTreeException");

		} catch (FamilyTreeException ex) {
			// pass...
		}
	}

	@Test
	public void WhenFatherOfAChildIsNotSetGetFatherReturnsUnknown() {
		Person child = new Person(1, Person.FEMALE);
		assertEquals(Person.UNKNOWN, child.getFatherId());
	}

	@Test
	public void WhenMiddlenameIsSetForPersonThePersonHasAMiddleName() {
		String name = "Bob";
		Person p = new Person(4, Person.MALE);
		p.setMiddleName(name);
		assertEquals(name, p.getMiddleName());
	}

	@Test
	public void WhenMotherOfAChildIsNotSetGetFatherReturnsUnknown() {
		Person child = new Person(1, Person.FEMALE);
		assertEquals(Person.UNKNOWN, child.getMotherId());
	}

	@Test
	public void WhenNewPersonIsCreatedThePersonIdIsCorrectlyAssignedToPerson() {
		int id = 4;
		Person p = new Person(id, Person.MALE);
		assertEquals(id, p.getId());
	}

	@Test
	public void WhenPersonNameIsSetTheFullNameIsSetasFirstThenMiddleThenLastname() {
		String first = "First";
		String middle = "Middle";
		String last = "Last";

		Person p = new Person(3, Person.FEMALE);
		p.setFirstName(first);
		p.setMiddleName(middle);
		p.setLastName(last);

		String full = first + " " + middle + " " + last;
		assertEquals(full, p.getFullName());
	}

	@Test
	public void WhenSuppliedInvalidGenderItThrowsFamiltyTreeException() {
		try {
			new Person(1, Person.Gender.UNKNOWN);
			fail("Should have thrown a FamiltyTreeException");
		} catch (FamilyTreeException ex) {
			// pass ...
		}
	}

	@Test
	public void WhenSuppliedInvalidPersonIdItThrowsFamilyTreeException() {
		try {
			new Person(-7, Person.FEMALE);
			fail("Should have thrown a FamilyTreeException");

		} catch (FamilyTreeException ex) {
			// pass ..
		}
	}

	@Test
	public void WhenTheFatherIsSetTheChildHasFather() {
		int id = 2;
		Person child = new Person(1, Person.FEMALE);
		Person father = new Person(id, Person.MALE);
		child.setFather(father);
		assertEquals(father, child.getFather());
		assertEquals(id, child.getFatherId());
	}

}
