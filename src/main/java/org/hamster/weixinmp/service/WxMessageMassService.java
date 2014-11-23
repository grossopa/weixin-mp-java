/**
 * 
 */
package org.hamster.weixinmp.service;

import java.util.List;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxMassSendAllTypeEnum;
import org.hamster.weixinmp.controller.util.WxJsonUtil;
import org.hamster.weixinmp.dao.entity.item.WxItemPicDescEntity;
import org.hamster.weixinmp.dao.entity.msg.mass.WxMsgMassSentItemEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.model.WxRespCode;
import org.hamster.weixinmp.model.send.mass.SendMassArticleJson;
import org.hamster.weixinmp.model.send.mass.WxMassRespCode;
import org.hamster.weixinmp.model.send.mass.WxMassUploadNewsWrapper;
import org.hamster.weixinmp.model.send.mass.WxMassUploadVideoWrapper;
import org.hamster.weixinmp.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;


/**
 * 在公众平台网站上，为订阅号提供了每天一条的群发权限，为服务号提供每月（自然月）4条的群发权限。而对于某些具备开发能力的公众号运营者，
 * 可以通过高级群发接口，实现更灵活的群发能力。
 * 
 * <p>请注意：</p>
 * <p>
 * 1、该接口暂时仅提供给已微信认证的服务号<br>
 * 2、虽然开发者使用高级群发接口的每日调用限制为100次，但是用户每月只能接收4条，请小心测试<br>
 * 3、无论在公众平台网站上，还是使用接口群发，用户每月只能接收4条群发消息，多于4条的群发将对该用户发送失败。<br>
 * 4、具备微信支付权限的公众号，在使用高级群发接口上传、群发图文消息类型时，可使用<a>标签加入外链<br>
 * </p>
 * 
 * @author <a href="mailto:grossopaforever@gmail.com">Jack Yin</a>
 * @version Nov 8, 2014 3:09:01 PM
 * 
 */
@Service
public class WxMessageMassService {
    
    @Autowired
    WxConfig config;
    
    @Autowired
    WxStorageService storageService;
    
    /**
     * 上传图文消息素材
     * 
     * <p>请参考<code>WxItemPicDescEntity</code>中Mass部分的参数.</p>
     * 返回说明<br>
     * 返回数据示例（正确时的JSON返回结果）：<br>
     * <code>{<br>
     * &nbsp;&nbsp;"type":"news",<br>
     * &nbsp;&nbsp;"media_id":"CsEf3ldqkAYJAU6EJeIkStVDSvffUJ54vqbThMgplD-VJXXof6ctX5fI6-aYyUiQ",<br>
     * &nbsp;&nbsp;"created_at":1391857799<br>
     * }</code>
     * 
     * 
     * @param articles
     * @throws WxException 
     */
    public WxMsgMassSentItemEntity uploadNews(String accessToken, List<WxItemPicDescEntity> articles) throws WxException {
        WxMassUploadNewsWrapper wrapper = new WxMassUploadNewsWrapper();
        List<SendMassArticleJson> jsonList = Lists.newArrayList();
        wrapper.setArticles(jsonList);
        for (WxItemPicDescEntity article : articles) {
            SendMassArticleJson sendArticle = new SendMassArticleJson();
            sendArticle.setAuthor(article.getAuthor());
            sendArticle.setThumb_media_id(article.getThumbMediaId());
            sendArticle.setTitle(article.getTitle());
            sendArticle.setContent_source_url(article.getContentSourceUrl());
            sendArticle.setContent(article.getDescription());
            sendArticle.setDigest(article.getDigest());
            sendArticle.setShow_cover_pic(article.getShowCoverPic());
            wrapper.getArticles().add(sendArticle);
        }
        
        String result = send(accessToken, config.getMsgMassUploadNews(), wrapper);
        
        WxMsgMassSentItemEntity entity = WxJsonUtil.toWxMsgMassSentItem(result);
        return entity;
    }
    
    /**
     * 根据分组进行群发-视频
     * 请注意，此处视频的media_id需通过POST请求到下述接口特别地得到<br>
     * https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=ACCESS_TOKEN <br>
     * 
     * 返回将为:<br>
     * <code>{
     *  "type":"video",
     *  "media_id":"IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc",
     *  "created_at":1398848981
     *}
     * </code>
     * 
     * @param accessToken
     * @param mediaId
     * @param title
     * @param description
     * @return
     * @throws WxException
     */
    public WxMsgMassSentItemEntity uploadVideo(String accessToken, String mediaId, String title, String description) throws WxException {
        WxMassUploadVideoWrapper wrapper = new WxMassUploadVideoWrapper(mediaId, title, description);
        
        String result = send(accessToken, config.getMsgMassUploadVideo(), wrapper);
        WxMsgMassSentItemEntity entity = WxJsonUtil.toWxMsgMassSentItem(result);
        return entity;
    }
    
    /**
     * 根据分组进行群发<br>
     * 
     * 请注意：在返回成功时，意味着群发任务提交成功，并不意味着此时群发已经结束，所以，仍有可能在后续的发送过程中出现异常情况导致用户未收到消息，
     * 如消息有时会进行审核、服务器不稳定等。此外，群发任务一般需要较长的时间才能全部发送完毕，请耐心等待。
     * 
     * @param accessToken
     * @param groupId
     * @param mediaIdOrContent
     * @param msgType
     * @return
     * @throws WxException
     */
    public WxMassRespCode sendAll(String accessToken, String groupId, String mediaIdOrContent, WxMassSendAllTypeEnum msgType) throws WxException {
        JsonObject json = new JsonObject();
        JsonObject filter = new JsonObject();
        filter.addProperty("group_id", groupId);
        JsonObject midOrContent = new JsonObject();
        switch (msgType) {
        case IMAGE:
        case MPNEWS:
        case MPVIDEO:
        case VOICE:
            midOrContent.addProperty("media_id", mediaIdOrContent);
            break;
        case TEXT:
            midOrContent.addProperty("text", mediaIdOrContent);
            break;
        default:
            break;
        
        }
        json.add("filter", filter);
        json.add(msgType.name(), midOrContent);
        

        String result = send(accessToken, config.getMsgMassUploadVideo(), json.toString());
        WxMassRespCode entity = WxJsonUtil.toWxMassErrorJson(result);
        return entity;
    }
    
    /**
     * 根据OpenID列表群发<br>
     * 
     * 请注意：在返回成功时，意味着群发任务提交成功，并不意味着此时群发已经结束，所以，仍有可能在后续的发送过程中出现异常情况导致用户未收到消息，
     * 如消息有时会进行审核、服务器不稳定等。此外，群发任务一般需要较长的时间才能全部发送完毕，请耐心等待。
     * 
     * @param accessToken
     * @param openIds
     * @param mediaIdOrContent
     * @param msgType
     * @return
     * @throws WxException
     */
    public WxMassRespCode sendAll(String accessToken, List<String> openIds, String mediaIdOrContent, WxMassSendAllTypeEnum msgType) throws WxException {
        JsonObject json = new JsonObject();
        JsonObject filter = new JsonObject();
        JsonArray openIdsJson = new JsonArray();
        for (String openId : openIds) {
            JsonPrimitive openIdJson = new JsonPrimitive(openId);
            openIdsJson.add(openIdJson);
        }
        filter.add("touser", openIdsJson);
        JsonObject midOrContent = new JsonObject();
        switch (msgType) {
        case IMAGE:
        case MPNEWS:
        case MPVIDEO:
        case VOICE:
            midOrContent.addProperty("media_id", mediaIdOrContent);
            break;
        case TEXT:
            midOrContent.addProperty("text", mediaIdOrContent);
            break;
        default:
            break;
        
        }
        json.add("filter", filter);
        json.add(msgType.name(), midOrContent);
        

        String result = send(accessToken, config.getMsgMassUploadVideo(), json.toString());
        WxMassRespCode entity = WxJsonUtil.toWxMassErrorJson(result);
        return entity;
    }
    
    /**
     * 删除群发<br>
     * 
     * 请注意，只有已经发送成功的消息才能删除删除消息只是将消息的图文详情页失效，已经收到的用户，还是能在其本地看到消息卡片。 
     * 另外，删除群发消息只能删除图文消息和视频消息，其他类型的消息一经发送，无法删除。
     * 
     * @param accessToken
     * @param msgId
     * @return
     * @throws WxException
     */
    public WxRespCode delete(String accessToken, Integer msgId) throws WxException {
        JsonObject json = new JsonObject();
        json.addProperty("msg_id", msgId);
        String result  = send(accessToken, config.getMsgMassMassDelete(), json.toString());
        WxRespCode entity = WxJsonUtil.toWxErrorJson(result);
        return entity;
    }
    
    protected String send(String accessToken, String url, Object json) throws WxException {
        Map<String, String> params = Maps.newHashMap();
        params.put("access_token", accessToken);
        Gson gson = new Gson();
        StringEntity requestEntity = new StringEntity(gson.toJson(json), Charsets.UTF_8);
        String result = WxUtil.sendRequest(url, HttpMethod.POST, params, requestEntity, String.class);
        return result;
    }
}
