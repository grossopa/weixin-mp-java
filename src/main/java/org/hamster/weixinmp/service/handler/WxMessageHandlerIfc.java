/**
 * 
 */
package org.hamster.weixinmp.service.handler;

import java.util.Map;

import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 5, 2014
 *
 */
public interface WxMessageHandlerIfc {
	
	WxMsgTypeEnum[] listIntetestedMessageType();
	
	WxBaseRespEntity handle(WxBaseMsgEntity msg, Map<String, Object> context);
	
	Integer priority();
}
