package test;
import org.junit.Test;

import com.combatmanager.configuration.Configuration;
import com.combatmanager.database.model.User;

import static org.junit.Assert.assertEquals;

public class MainTest {
	
	@Test
	public void exemple() {
		JunitTestExemple jte = new JunitTestExemple();
		assertEquals("City [name=Criciuma, state=SC, country=Brasil]", jte.test());
	}
	
	@Test
	public void testConfigurationModel() {
		User localUser = new User();
		localUser.setUser("Roger");
		
		Configuration config = new Configuration(localUser);
		ConfigucarionModelTest cmt = new ConfigucarionModelTest();
		localUser.setProfile("Cadastral");
		assertEquals(3, cmt.test(config));
		localUser.setProfile("Matricular");
		assertEquals(5, cmt.test(config));
		localUser.setProfile("Financeiro");
		assertEquals(7, cmt.test(config));
		localUser.setProfile("Completo");
		assertEquals(11, cmt.test(config));
		
	}
	
}
