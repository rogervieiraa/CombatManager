package test;

import com.combatmanager.error.AcessException;

public class TestError {
	public static void main(String[] args) {
		String word = "t e s t ";
		try
		{
		     if(word.contains(" "))
		     {
		          throw new AcessException();
		     }
		}
		catch(AcessException ex)
		{
			ex.showButtonActivatedDenied();
		}
	}
}
