/**
 * 
 */
package org.hamster.weixinmp.exception;

import lombok.Getter;

import org.hamster.weixinmp.model.WxErrorJson;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 */
public class WxException extends Exception {

	private static final long serialVersionUID = -5181800588832010641L;
	@Getter
	private WxErrorJson error;

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

	
	/**
	 * 
	 */
	public WxException(WxErrorJson errorJson) {
		super(errorJson.getErrmsg());
		this.error = errorJson;
	}
}
