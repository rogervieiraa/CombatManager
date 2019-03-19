package com.combatmanager.security;
import com.combatmanager.error.AcessException;
import com.combatmanager.view.View;

public class ValidateAcess {
	
	/**
	 * @author Roger
	 * @throws an AcessException
	 */
	public static boolean acessWindow(Configuration config,View view) throws AcessException {
		if(config.getPermissionValue() == 0) {
			throw new AcessException(view.getName(), "Usuario indefinido, permissao 0.");
		}
		if(view.getAcess()%config.getPermissionValue() == 0) {
			return true;
		}
		
		throw new AcessException(view.getName(), "Acesso Negado");
	}
	
}
