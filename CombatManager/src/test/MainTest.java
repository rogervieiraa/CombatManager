package test;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MainTest {
	
	@Test
	public void exemple() {
		JunitTestExemple jte = new JunitTestExemple();
		assertEquals("City [name=Criciuma, state=SC, country=Brasil]", jte.test());
	}
}
