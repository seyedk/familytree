package org.seyedk.lnl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilyApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void PrintStandardOut() {
		System.out.println("This could be done easier!");
	}

}
