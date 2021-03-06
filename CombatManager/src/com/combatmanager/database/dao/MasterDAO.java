package com.combatmanager.database.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public abstract class MasterDAO {
	
	
	public abstract
	List<Object>		SelectAll()
	throws				SQLException;
	
	public abstract
	Object 				Select(Object parameter)
		throws			SQLException;
		
	public abstract
	void 				Update(Object last_parameter, Object new_parameter)
		throws			SQLException;
		
	public abstract
	void 				Insert(Object parameter)
		throws			SQLException;
		
	public abstract
	void 				Delete(Object parameter)
		throws			SQLException;
		
	public void	 			Set(PreparedStatement pst, int position, Object value) throws SQLException {
				
			if(value == null) {
				pst.setNull(position, Types.NULL);
			} else if (value instanceof String) {
				pst.setString(position, (String)value);
			}
	}
		
	
}
