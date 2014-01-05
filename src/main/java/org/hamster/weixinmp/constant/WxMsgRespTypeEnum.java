/**
 * 
 */
package org.hamster.weixinmp.constant;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 5, 2014
 *
 */
public enum WxMsgRespTypeEnum {
	TEXT(WxMsgRespType.TEXT), IMAGE(WxMsgRespType.IMAGE), MUSIC(WxMsgRespType.MUSIC), 
	NEWS(WxMsgRespType.NEWS), VIDEO(WxMsgRespType.VIDEO), VOICE(WxMsgRespType.VOICE);
	/**
	 * @param text
	 */
	private WxMsgRespTypeEnum(final String text) {
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
	
	public static WxMsgRespTypeEnum inst(String strVal) {
		for (WxMsgRespTypeEnum type : WxMsgRespTypeEnum.values()) {
			if (type.toString().equalsIgnoreCase(strVal)) {
				return type;
			}
		}
		return null;
	}
}
