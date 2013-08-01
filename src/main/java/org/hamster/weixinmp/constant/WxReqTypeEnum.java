package org.hamster.weixinmp.constant;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
public enum WxReqTypeEnum {
	TEXT("text"), IMAGE("image"), LOCATION("location"), 
	LINK("link"), EVENT("event");
	/**
	 * @param text
	 */
	private WxReqTypeEnum(final String text) {
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
	
	public static WxReqTypeEnum inst(String strVal) {
		for (WxReqTypeEnum type : WxReqTypeEnum.values()) {
			if (type.toString().equals(strVal)) {
				return type;
			}
		}
		return null;
	}
	
}
