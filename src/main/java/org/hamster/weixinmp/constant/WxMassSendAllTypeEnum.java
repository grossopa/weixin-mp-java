/**
 * 
 */
package org.hamster.weixinmp.constant;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 *
 */
public enum WxMassSendAllTypeEnum {
    MPNEWS(WxMassSendAllType.MPNEWS),
    TEXT(WxMassSendAllType.TEXT),
    VOICE(WxMassSendAllType.VOICE),
    IMAGE(WxMassSendAllType.IMAGE),
    MPVIDEO(WxMassSendAllType.MPVIDEO),
	;

	/**
	 * @param text
	 */
	WxMassSendAllTypeEnum(final String text) {
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
	
	public static WxMassSendAllTypeEnum inst(String strVal) {
		for (WxMassSendAllTypeEnum type : WxMassSendAllTypeEnum.values()) {
			if (type.toString().equalsIgnoreCase(strVal)) {
				return type;
			}
		}
		return null;
	}
}
