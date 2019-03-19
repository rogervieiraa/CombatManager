package com.combatmanager.security;
import com.combatmanager.error.AccessException;
import com.combatmanager.view.View;

public class ValidateAcess {
	
	/**
	 * @author Roger
	 * @throws an AcessException
	 * @return if have access
	 */
	public static boolean canAccess(Configuration config,View view) throws AccessException {
		if(config.getPermissionValue() == 0) {
			throw new AccessException(view.getName(), "Usuario indefinido, permissao 0.");
		}
		if(view.getAccess()%config.getPermissionValue() == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * @author Roger
	 * @throws an AcessException
	 */
	public static void tryToAcessView(Configuration config,View view) throws AccessException {
		if(config.getPermissionValue() == 0) {
			throw new AccessException(view.getName(), "Usuario indefinido, permissao 0.");
		}
		if(view.getAccess()%config.getPermissionValue() == 0) {
			return;
		}
		throw new AccessException(view.getName(), "Acesso Negado");
	}
	
}
