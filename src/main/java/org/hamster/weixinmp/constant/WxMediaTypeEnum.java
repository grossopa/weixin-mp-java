/**
 * 
 */
package org.hamster.weixinmp.constant;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 *
 */
public enum WxMediaTypeEnum {
	IMAGE(WxMediaType.IMAGE),
	MUSIC(WxMediaType.MUSIC),
	PIC_DESC(WxMediaType.PIC_DESC),
	THUMB(WxMediaType.THUMB),
	VIDEO(WxMediaType.VIDEO),
	VOICE(WxMediaType.VOICE),
	DEFAULT(WxMediaType.DEFAULT),
	;

	/**
	 * @param text
	 */
	WxMediaTypeEnum(final String text) {
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
	
	public static WxMediaTypeEnum inst(String strVal) {
		for (WxMediaTypeEnum type : WxMediaTypeEnum.values()) {
			if (type.toString().equalsIgnoreCase(strVal)) {
				return type;
			}
		}
		return null;
	}
}
