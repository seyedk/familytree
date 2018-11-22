package org.seyedk.lnl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seyedk.lnl.domain.Marriage;
import org.seyedk.lnl.domain.Person;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarriageTest {

	@Test
	public void WhenAManMarriesAWomanThenManIsHusbandInMarriage() {
		Person man = new Person(1, Person.MALE);
		Person woman = new Person(2, Person.FEMALE);

		Marriage m = new Marriage(man, woman);

		assertEquals(man, m.getHusband());

	}

	@Test
	public void WhenAWomanMarriesAManThenWomanIsWifeInMarriage() {
		Person man = new Person(1, Person.MALE);
		Person woman = new Person(2, Person.FEMALE);

		Marriage m = new Marriage(man, woman);

		assertEquals(woman, m.getWife());

	}

	@Test
	public void WhenHusbandIsFemaleThenItShouldThrowIellgalArgumentException() {
		Person husband = new Person(1, Person.FEMALE);
		Person wife = new Person(2, Person.FEMALE);
		try {
			new Marriage(husband, wife);
			fail("Should have thrown an IllegalArgumentException");

		} catch (IllegalArgumentException ex) {
			// pass ...
		}
	}

	@Test
	public void WhenWifeIsMaleThenItThrowsIellegalArgumentExeption() {
		Person husband = new Person(1, Person.MALE);
		Person wife = new Person(2, Person.MALE);
		try {
			new Marriage(husband, wife);
			fail("Should have thrown an IllegalArgumentException");

		} catch (IllegalArgumentException ex) {
			// pass ...
		}
	}

}
