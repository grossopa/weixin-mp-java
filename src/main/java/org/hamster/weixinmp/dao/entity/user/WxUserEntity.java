/**
 * 
 */
package org.hamster.weixinmp.dao.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;

import com.google.gson.annotations.SerializedName;

/**
 * @author grossopaforever@gmail.com
 * @version Dec 30, 2013
 * 
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "user")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxUserEntity extends WxBaseEntity {
    @SerializedName("openid")
    @Column(name = "open_id", length = WxConfig.COL_LEN_INDICATOR, nullable = false)
    private String openId;
    @Column(name = "subscribe", nullable = true)
    private Integer subscribe;
    @SerializedName("nickname")
    @Column(name = "nickname", length = WxConfig.COL_LEN_USER_NAME, nullable = true)
    private String nickName;
    @Column(name = "sex", nullable = true)
    private Integer sex;
    @Column(name = "language", length = WxConfig.COL_LEN_INDICATOR, nullable = true)
    private String language;
    @Column(name = "city", length = WxConfig.COL_LEN_INDICATOR, nullable = true)
    private String city;
    @Column(name = "province", length = WxConfig.COL_LEN_INDICATOR, nullable = true)
    private String province;
    @Column(name = "country", length = WxConfig.COL_LEN_INDICATOR, nullable = true)
    private String country;
    @SerializedName("headimgurl")
    @Column(name = "head_img_url", length = WxConfig.COL_LEN_URL, nullable = true)
    private String headImgUrl;
    @SerializedName("subscribe_time")
    @Column(name = "subscribe_time", nullable = true)
    private Long subscribeTime;
    @SerializedName("unionid")
    @Column(name = "unionid", length = 100, nullable = true)
    private String unionid;

    @Column(name = "oauth_access_token", length = 200, nullable = true)
    private String oauthAccessToken;
    @Column(name = "oauth_expires_in", nullable = true)
    private Long oauthExpiresIn;
    @Column(name = "oauth_refresh_token", length = 200, nullable = true)
    private String oauthRefreshToken;
    @Column(name = "oauth_scope", length = 20, nullable = true)
    private String oauthScope;
    @Column(name = "oauth_create_time", nullable = true)
    private Long oauthCreateTime;

    @Column(name = "privileges", length = 200, nullable = true)
    private String privileges;

}
