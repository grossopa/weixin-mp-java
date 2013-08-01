/**
 * 
 */
package org.hamster.weixinmp.constant;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
public enum WxRespTypeEnum {
	text("text"), music("music"), news("news");
	/**
	 * @param text
	 */
	private WxRespTypeEnum(final String _text) {
		this._text = _text;
	}

	private final String _text;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return _text;
	}
}
