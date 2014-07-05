/**
 * 
 */
package org.hamster.weixinmp.service;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;

import lombok.Data;

import org.apache.http.client.utils.URIBuilder;
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
 * 公众号在使用接口时，对多媒体文件、多媒体消息的获取和调用等操作，是通过media_id来进行的。通过本接口，公众号可以上传或下载
 * 多媒体文件。但请注意，每个多媒体文件（media_id）会在上传、用户发送到微信服务器3天后自动删除，以节省服务器资源。
 * 
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 * 
 */
@Service
public class WxMediaService {

    @Autowired
    WxConfig config;

    /**
     * <b>上传多媒体文件</b>
     * <p>
     * 公众号可调用本接口来上传图片、语音、视频等文件到微信服务器，上传后服务器会返回对应的media_id，公众号此后可根据该
     * media_id来获取多媒体。请注意，media_id是可复用的，调用该接口需http协议。
     * </p>
     * <p>
     * http请求方式: POST/FORM<br>
     * http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
     * </p>
     * <p><b>注意事项</b></p>
     * <p>
     * 上传的多媒体文件有格式和大小限制，如下：<br><br>
     * 图片（image）: 1M，支持JPG格式<br>
     * 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式<br>
     * 视频（video）：10MB，支持MP4格式<br>
     * 缩略图（thumb）：64KB，支持JPG格式<br>
     * 媒体文件在后台保存时间为3天，即3天后media_id失效。<br>
     * </p>
     * 
     * @param accessToken 必须,调用接口凭证
     * @param type 必须,媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param content 必须,form-data中媒体文件标识，有filename、filelength、content-type等信息
     * @return {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
     * @throws WxException
     */
    public WxBaseItemMediaEntity remoteMediaUpload(String accessToken, WxMediaTypeEnum type, byte[] content)
            throws WxException {
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
            throw new WxException("Not supported upload type : " + type.toString());
        default:
            break;
        }

        Map<String, String> params = WxUtil.getAccessTokenParams(accessToken);
        System.out.println(typeString);
        params.put("type", typeString);
        ContentBody contentBody = new ByteArrayBody(content, ContentType.MULTIPART_FORM_DATA, "name.jpg");
        entityBuilder.addPart("media", contentBody);
        MediaResultMapper result = WxUtil.sendRequest(config.getMediaUploadUrl(), HttpMethod.POST, params,
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
            throw new WxException("Not supported upload type : " + type.toString());
        default:
            break;
        }
        return resultEntity;
    }
    
    /**
     * <p><b>下载多媒体文件</b></p>
     * <p>
     * http请求方式: GET<br>
     * http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
     * </p>
     * <p>
     * 公众号可调用本接口来获取多媒体文件。请注意，视频文件不支持下载，调用该接口需http协议。
     * </p>
     * 
     * @param accessToken 必须,调用接口凭证
     * @param mediaId 必须,媒体文件ID
     * @return
     */
    public String getMediaGetUrl(String accessToken, String mediaId) {
        try {
            URIBuilder builder = new URIBuilder(config.getMediaDownloadUrl());
            builder.addParameter("access_token", accessToken).addParameter("media_id", mediaId);
            return builder.build().toString();
        } catch (URISyntaxException e) {
            // never happens
            e.printStackTrace();
            return null;
        }
    }

}

@Data
class MediaResultMapper {
    private String type;
    private String media_id;
    private Long created_at;
}