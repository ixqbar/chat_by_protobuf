package com.xingqiba.exception;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class ConfigException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4667107326211599574L;
	
	public ConfigException() { 
		
	}
	
	public ConfigException(String message) {
		super(message);
	}
	
	public ConfigException(Throwable cause) {
		super(cause);
	}

	public ConfigException(String message, Throwable cause) {
		super(message, cause);		
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
