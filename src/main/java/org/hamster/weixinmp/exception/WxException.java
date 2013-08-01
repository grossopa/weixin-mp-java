/**
 * 
 */
package org.hamster.weixinmp.exception;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 */
public class WxException extends Exception {

	private static final long serialVersionUID = -5181800588832010641L;

	/**
	 * 
	 */
	public WxException() {
	}

	/**
	 * @param message
	 */
	public WxException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public WxException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WxException(String message, Throwable cause) {
		super(message, cause);
	}

}
