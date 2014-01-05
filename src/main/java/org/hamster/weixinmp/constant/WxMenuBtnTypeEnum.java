/**
 * 
 */
package org.hamster.weixinmp.constant;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 *
 */
public enum WxMenuBtnTypeEnum {
	VIEW(WxMenuBtnType.VIEW), CLICK(WxMenuBtnType.CLICK);
	/**
	 * @param text
	 */
	WxMenuBtnTypeEnum(final String text) {
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
	
	public static WxMenuBtnTypeEnum inst(String strVal) {
		for (WxMenuBtnTypeEnum type : WxMenuBtnTypeEnum.values()) {
			if (type.toString().equalsIgnoreCase(strVal)) {
				return type;
			}
		}
		return null;
	}
}
