package com.xingqiba.exception;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class MessageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1735789754128620105L;
	
	public MessageException() { 
		
	}
	
	public MessageException(String message) {
		super(message);
	}
	
	public MessageException(Throwable cause) {
		super(cause);
	}

	public MessageException(String message, Throwable cause) {
		super(message, cause);		
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
		
}
