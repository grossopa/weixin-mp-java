/**
 * 
 */
package org.hamster.weixinmp.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.auth.WxAuth;
import org.hamster.weixinmp.dao.entity.auth.WxAuthReq;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.util.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * 
 * 
 * @author grossopaforever@gmail.com
 * @version Jan 1, 2014
 * 
 */
@Service
public class WxAuthService {

    private static final Logger log = LoggerFactory.getLogger(WxAuthService.class);

    @Autowired
    protected WxConfig config;

    /**
     * access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token。正常情况下access_token有效期为7200秒，
     * 重复获取将导致上次获取的access_token失效。由于获取access_token的api调用次数非常有限，建议开发者全局存储与更新access_token，
     * 频繁刷新access_token会导致api调用受限，影响自身业务。<br><br>
     *
     * 请开发者注意，由于技术升级，公众平台的开发接口的access_token长度将增长，其存储至少要保留512个字符空间。此修改将在1个月后生效，
     * 请开发者尽快修改兼容。<br><br>
     *
     * 公众号可以使用AppID和AppSecret调用本接口来获取access_token。AppID和AppSecret可在开发模式中获得（需要已经成为开发者，且帐号没有
     * 异常状态）。注意调用所有微信接口时均需使用https协议。
     * 
     * <p>
     * http请求方式: GET<br>
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     * </p>
     * 
     * @param appid 第三方用户唯一凭证
     * @param appsecret 第三方用户唯一凭证密钥，即appsecret
     * @return WxAuth
     * @throws WxException
     */
    public WxAuth getAccessToken(String appid, String appsecret) throws WxException {
        Map<String, String> paramsJson = new HashMap<String, String>();
        paramsJson.put("grant_type", "client_credential");
        paramsJson.put("appid", appid);
        paramsJson.put("secret", appsecret);

        WxAuth result = WxUtil.sendRequest(config.getAccessTokenCreateUrl(), HttpMethod.GET, paramsJson, null,
                WxAuth.class);
        result.setGrantType("client_credential");
        result.setAppid(appid);
        result.setSecret(appsecret);
        return result;
    }

    /**
     * <p>在开发者首次提交验证申请时，微信服务器将发送GET请求到填写的URL上，并且带上四个参数（signature、timestamp、nonce、echostr）
     * ，开发者通过对签名（即signature）的效验，来判断此条消息的真实性。</p>
     * 
     * <p>此后，每次开发者接收用户消息的时候，微信也都会带上前面三个参数（signature、timestamp、nonce）访问开发者设置的URL，
     * 开发者依然通过对签名的效验判断此条消息的真实性。效验方式与首次提交验证申请一致。</p>
     * 
     * <p>
     * 开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，
     * 成为开发者成功，否则接入失败。
     *
     * 加密/校验流程如下：<br><br>
     * 1. 将token、timestamp、nonce三个参数进行字典序排序<br>
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密<br>
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信<br>
     * 
     * </p>
     * 
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return 是否验证成功
     * @throws WxException
     */
    public boolean validateAuth(String signature, String timestamp, String nonce, String echostr) throws WxException {
        WxAuthReq authReq = new WxAuthReq();
        authReq.setCreatedDate(new Date());
        authReq.setSignature(signature);
        authReq.setTimestamp(timestamp);
        authReq.setNonce(nonce);
        authReq.setEchostr(echostr);

        String excepted = hash(getStringToHash(timestamp, nonce, config.getToken()));

        if (signature == null || !signature.equals(excepted)) {
            log.error("Authentication failed! excepted echostr ->" + excepted);
            log.error("                                 actual ->" + signature);
            return false;
        }

        return true;
    }

    protected static String getStringToHash(String timestamp, String nonce, String token) {
        List<String> list = new ArrayList<String>();
        list.add(timestamp);
        list.add(nonce);
        list.add(token);

        String result = "";
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            result += list.get(i);
        }
        return result;
    }

    protected static String hash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] b = md.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // never happens
        }
        return null;
    }

}
