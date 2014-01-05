/**
 * 
 */
package org.hamster.weixinmp.gson;

import java.lang.reflect.Type;

import org.hamster.weixinmp.constant.WxMenuBtnTypeEnum;
import org.hamster.weixinmp.dao.entity.menu.WxMenuBtnEntity;
import org.springframework.util.CollectionUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 * 
 */
public class WxMenuBtnSerializer implements JsonSerializer<WxMenuBtnEntity> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object,
	 * java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public JsonElement serialize(WxMenuBtnEntity src, Type typeOfSrc,
			JsonSerializationContext context) {
		return recursiveParse(src);
	}

	public JsonObject recursiveParse(WxMenuBtnEntity parentEntity) {
		JsonObject parent = new JsonObject();
		parent.addProperty("type", parentEntity.getType());
		parent.addProperty("name", parentEntity.getName());

		WxMenuBtnTypeEnum type = WxMenuBtnTypeEnum.valueOf(parentEntity
				.getType());
		switch (type) {
		case CLICK:
			parent.addProperty("key", parentEntity.getKey());
			break;
		case VIEW:
			parent.addProperty("url", parentEntity.getUrl());
			break;
		default:
			break;
		}
		if (!CollectionUtils.isEmpty(parentEntity.getSubButtons())) {
			JsonArray children = new JsonArray();
			for (WxMenuBtnEntity child : parentEntity.getSubButtons()) {
				children.add(recursiveParse(child));
			}
			parent.add("sub_button", children);
		}
		return parent;
	}

}
