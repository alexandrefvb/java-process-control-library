package br.com.alexandrefvb.jpcl.exception;

public class UnsupportedPlatformException extends RuntimeException {

	public UnsupportedPlatformException() {
	}
	
	public UnsupportedPlatformException(Exception e) {
		super(e);
	}

	private static final long serialVersionUID = 1L;

}
