/**
 * 
 */
package org.hamster.weixinmp.constant;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 5, 2014
 *
 */
public enum WxMsgEventTypeEnum {
	SUBSCRIBE(WxMsgEventType.SUBSCRIBE),
	UNSUBSCRIBE(WxMsgEventType.UNSUBSCRIBE),
	SCAN(WxMsgEventType.SCAN),
	LOCATION(WxMsgEventType.LOCATION),
	CLICK(WxMsgEventType.CLICK);
	
	/**
	 * @param text
	 */
	WxMsgEventTypeEnum(final String text) {
		this._text = text;
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
	
	public static WxMsgEventTypeEnum inst(String strVal) {
		for (WxMsgEventTypeEnum type : WxMsgEventTypeEnum.values()) {
			if (type.toString().equalsIgnoreCase(strVal)) {
				return type;
			}
		}
		return null;
	}
}
