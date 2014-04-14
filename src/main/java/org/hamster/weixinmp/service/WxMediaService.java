/**
 * 
 */
package org.hamster.weixinmp.service;

import java.util.Date;
import java.util.Map;

import lombok.Data;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxMediaTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseItemMediaEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemImageEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemThumbEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemVideoEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemVoiceEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 * 
 */
@Service
public class WxMediaService {

	@Autowired
	WxConfig config;

	public WxBaseItemMediaEntity remoteMediaUpload(String accessToken,
			WxMediaTypeEnum type, byte[] content) throws WxException {
		MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
		String typeString = null;
		switch (type) {
		case IMAGE:
		case THUMB:
		case VIDEO:
		case VOICE:
			typeString = type.toString().toLowerCase();
			break;
		case MUSIC:
		case DEFAULT:
		case PIC_DESC:
			throw new WxException("Not supported upload type : "
					+ type.toString());
		default:
			break;
		}

		Map<String, String> params = WxUtil.getAccessTokenParams(accessToken);
		System.out.println(typeString);
		params.put("type", typeString);
		ContentBody contentBody = new ByteArrayBody(content, ContentType.MULTIPART_FORM_DATA, "name.jpg");
		entityBuilder.addPart("media", contentBody);
		MediaResultMapper result = WxUtil.sendRequest(
				config.getMediaUploadUrl(), HttpMethod.POST, params,
				entityBuilder.build(), MediaResultMapper.class);

		WxBaseItemMediaEntity resultEntity = null;
		switch (type) {
		case IMAGE:
			WxItemImageEntity imageEntity = new WxItemImageEntity();
			imageEntity.setMediaId(result.getMedia_id());
			imageEntity.setCreatedDate(new Date(result.getCreated_at() * 1000));
			resultEntity = imageEntity;
			break;
		case THUMB:
			WxItemThumbEntity thumbEntity = new WxItemThumbEntity();
			thumbEntity.setMediaId(result.getMedia_id());
			thumbEntity.setCreatedDate(new Date(result.getCreated_at() * 1000));
			resultEntity = thumbEntity;
			break;
		case VIDEO:
			WxItemVideoEntity videoEntity = new WxItemVideoEntity();
			videoEntity.setMediaId(result.getMedia_id());
			videoEntity.setCreatedDate(new Date(result.getCreated_at() * 1000));
			resultEntity = videoEntity;
			break;
		case VOICE:
			WxItemVoiceEntity voiceEntity = new WxItemVoiceEntity();
			voiceEntity.setMediaId(result.getMedia_id());
			voiceEntity.setCreatedDate(new Date(result.getCreated_at() * 1000));
			resultEntity = voiceEntity;
			break;
		case MUSIC:
		case DEFAULT:
		case PIC_DESC:
			throw new WxException("Not supported upload type : "
					+ type.toString());
		default:
			break;
		}
		return resultEntity;
	}

}

@Data
class MediaResultMapper {
	private String type;
	private String media_id;
	private Long created_at;
}