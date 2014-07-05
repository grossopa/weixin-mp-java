/**
 * 
 */
package org.hamster.weixinmp.service;

import java.util.Iterator;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.model.send.SendImageJson;
import org.hamster.weixinmp.model.send.SendItemPicDescJson;
import org.hamster.weixinmp.model.send.SendMusicJson;
import org.hamster.weixinmp.model.send.SendTextJson;
import org.hamster.weixinmp.model.send.SendVideoJson;
import org.hamster.weixinmp.model.send.SendVoiceJson;
import org.hamster.weixinmp.model.send.base.AbstractCustomSendJson;
import org.hamster.weixinmp.model.send.item.SendItemArticleJson;
import org.hamster.weixinmp.model.send.item.SendItemImageJson;
import org.hamster.weixinmp.model.send.item.SendItemMusicJson;
import org.hamster.weixinmp.model.send.item.SendItemTextJson;
import org.hamster.weixinmp.model.send.item.SendItemVideoJson;
import org.hamster.weixinmp.model.send.item.SendItemVoiceJson;
import org.hamster.weixinmp.model.send.item.wrapper.WxSendItemArticleWrapper;
import org.hamster.weixinmp.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

/**
 * 当用户主动发消息给公众号的时候（包括发送信息、点击自定义菜单、订阅事件、扫描二维码事件、支付成功事件、用户维权），
 * 微信将会把消息数据推送给开发者，开发者在一段时间内（目前修改为48小时）可以调用客服消息接口，通过POST一个JSON数据包
 * 来发送消息给普通用户，在48小时内不限制发送次数。此接口主要用于客服等有人工消息处理环节的功能，方便开发者为用户提供更加 优质的服务。
 * 
 * <p>
 * http请求方式: POST<br>
 * https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
 * </p>
 * 
 * @author <a href="mailto:grossopaforever@gmail.com">Jack Yin</a>
 * @version Jul 5, 2014 4:48:18 PM
 * 
 */
@Service
public class WxMessageCustomService {

    @Autowired
    WxConfig config;

    @Autowired
    WxStorageService storageService;

    /**
     * <code>
     * {<br>
     * &nbsp;&nbsp;"touser":"OPENID",<br>
     * &nbsp;&nbsp;"msgtype":"text",<br>
     * &nbsp;&nbsp;"text":<br>
     * &nbsp;&nbsp;{<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;"content":"Hello World"<br>
     * &nbsp;&nbsp;}<br>
     * }<br>
     * </code>
     * 
     * @param accessToken
     *            调用接口凭证
     * @param toUser
     *            普通用户openid
     * @param content
     *            文本消息内容
     * @return
     * @throws WxException
     */
    public String sendText(String accessToken, String toUser, String content) throws WxException {
        SendTextJson json = new SendTextJson();
        json.setTouser(toUser);
        SendItemTextJson itemJson = new SendItemTextJson();
        itemJson.setText(content);
        json.setContent(itemJson);

        String result = send(accessToken, json);
        return result;
    }

    /**
     * <code>
     * {<br>
     * &nbsp;&nbsp;"touser":"OPENID",<br>
     * &nbsp;&nbsp;"msgtype":"image",<br>
     * &nbsp;&nbsp;"image":<br>
     * &nbsp;&nbsp;{<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;"media_id":"MEDIA_ID"<br>
     * &nbsp;&nbsp;}<br>
     * }<br>
     * </code>
     * 
     * @param accessToken 调用接口凭证
     * @param toUser 普通用户openid
     * @param mediaId 发送的图片的媒体ID
     * @return
     * @throws WxException
     */
    public String sendImage(String accessToken, String toUser, String mediaId) throws WxException {
        SendImageJson json = new SendImageJson();
        json.setTouser(toUser);
        SendItemImageJson itemJson = new SendItemImageJson();
        itemJson.setMediaId(mediaId);
        json.setImage(itemJson);

        String result = send(accessToken, json);
        return result;
    }

    /**
     * <code>
     * {<br>
     * &nbsp;&nbsp;"touser":"OPENID",<br>
     * &nbsp;&nbsp;"msgtype":"voice",<br>
     * &nbsp;&nbsp;"voice":<br>
     * &nbsp;&nbsp;{<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;"media_id":"MEDIA_ID"<br>
     * &nbsp;&nbsp;}<br>
     * }<br>
     * </code>
     * 
     * @param accessToken 调用接口凭证
     * @param toUser 普通用户openid
     * @param mediaId 发送的语音的媒体ID
     * @return
     * @throws WxException
     */
    public String sendVoice(String accessToken, String toUser, String mediaId) throws WxException {
        SendVoiceJson json = new SendVoiceJson();
        json.setTouser(toUser);
        SendItemVoiceJson itemJson = new SendItemVoiceJson();
        itemJson.setMediaId(mediaId);
        json.setVoice(itemJson);

        String result = send(accessToken, json);
        return result;
    }

    /**
     * <code>
     * {<br>
     * &nbsp;&nbsp;"touser":"OPENID",<br>
     * &nbsp;&nbsp;"msgtype":"video",<br>
     * &nbsp;&nbsp;"video":<br>
     * &nbsp;&nbsp;{<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;"media_id":"MEDIA_ID"<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;"title":"TITLE",<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;"description":"DESCRIPTION"<br>
     * &nbsp;&nbsp;}<br>
     * }<br>
     * </code>
     * 
     * @param accessToken 调用接口凭证
     * @param toUser 普通用户openid
     * @param mediaId 发送的视频的媒体ID
     * @return
     * @throws WxException
     */
    public String sendVideo(String accessToken, String toUser, String mediaId, String title, String description) throws WxException {
        SendVideoJson json = new SendVideoJson();
        json.setTouser(toUser);
        SendItemVideoJson itemJson = new SendItemVideoJson();
        itemJson.setMediaId(mediaId);
        itemJson.setTitle(title);
        itemJson.setDescription(description);
        json.setVideo(itemJson);

        String result = send(accessToken, json);
        return result;
    }

    /**
     * <code>
     * {<br>
     * &nbsp;&nbsp;"touser":"OPENID",<br>
     * &nbsp;&nbsp;"msgtype":"music",<br>
     * &nbsp;&nbsp;"music":<br>
     * &nbsp;&nbsp;{<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;"title":"MUSIC_TITLE",<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;"description":"MUSIC_DESCRIPTION",<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;"musicurl":"MUSIC_URL",<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;"hqmusicurl":"HQ_MUSIC_URL",<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;"thumb_media_id":"THUMB_MEDIA_ID"<br> 
     * &nbsp;&nbsp;}<br>
     * }<br>
     * </code>
     * 
     * @param accessToken 调用接口凭证
     * @param toUser 普通用户openid
     * @param title 音乐标题
     * @param description 音乐描述
     * @param musicUrl 音乐链接
     * @param hqMusicUrl 高品质音乐链接，wifi环境优先使用该链接播放音乐
     * @param thumbMediaId 缩略图的媒体ID
     * @return
     * @throws WxException
     */
    public String sendMusic(String accessToken, String toUser, String title, String description, String musicUrl,
            String hqMusicUrl, String thumbMediaId) throws WxException {
        SendMusicJson json = new SendMusicJson();
        json.setTouser(toUser);
        SendItemMusicJson itemJson = new SendItemMusicJson();
        itemJson.setTitle(title);
        itemJson.setDescription(description);
        itemJson.setMusicurl(musicUrl);
        itemJson.setHqmusicurl(hqMusicUrl);
        itemJson.setThumb_media_id(thumbMediaId);
        json.setMusic(itemJson);

        String result = send(accessToken, json);
        return result;
    }
    
    /**
     * <code>
     * {
     * &nbsp;&nbsp;"touser":"OPENID",
     * &nbsp;&nbsp;"msgtype":"news",
     * &nbsp;&nbsp;"news":{
     * &nbsp;&nbsp;&nbsp;&nbsp;"articles": [
     * &nbsp;&nbsp;&nbsp;&nbsp;{
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title":"Happy Day",
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"description":"Is Really A Happy Day",
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"url":"URL",
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"picurl":"PIC_URL"
     * &nbsp;&nbsp;&nbsp;&nbsp;},
     * &nbsp;&nbsp;&nbsp;&nbsp;{
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"title":"Happy Day",
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"description":"Is Really A Happy Day",
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"url":"URL",
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"picurl":"PIC_URL"
     * &nbsp;&nbsp;&nbsp;&nbsp;}
     * &nbsp;&nbsp;&nbsp;&nbsp;]
     * &nbsp;&nbsp;}
     * }
     * </code>
     * 
     * @param accessToken 调用接口凭证
     * @param toUser 普通用户openid
     * @param articles 文章(<=10)
     * @return
     * @throws WxException
     */
    public String sendArticles(String accessToken, String toUser, Iterator<SendItemArticleJson> articles) throws WxException {
        SendItemPicDescJson json = new SendItemPicDescJson();
        json.setTouser(toUser);
        
        WxSendItemArticleWrapper wrapper = new WxSendItemArticleWrapper();
        wrapper.setArticles(Lists.newArrayList(articles));
        json.setNews(wrapper);

        String result = send(accessToken, json);
        return result;
    }

    protected String send(String accessToken, AbstractCustomSendJson json) throws WxException {
        Map<String, String> params = Maps.newHashMap();
        params.put("access_token", accessToken);
        Gson gson = new Gson();
        StringEntity requestEntity = new StringEntity(gson.toJson(json), Charsets.UTF_8);
        String result = WxUtil.sendRequest(config.getCustomSendUrl(), HttpMethod.POST, params, requestEntity,
                String.class);

        storageService.saveJson(json, result);
        return result;
    }
}
