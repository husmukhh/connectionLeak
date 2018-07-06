package com.seeka.test;

import java.sql.SQLException;

public class ConnectionLeakException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8411924093298217670L;

	public ConnectionLeakException(String message){
		System.out.println(message);
	}
	
	@Override
	public String getSQLState() {
		return "ConnectionLeak-1";
	}

	@Override
	public int getErrorCode() {
		return 101;
	}

	
	
}
