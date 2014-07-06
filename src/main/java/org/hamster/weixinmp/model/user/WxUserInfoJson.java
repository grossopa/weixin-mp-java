/**
 * 
 */
package org.hamster.weixinmp.model.user;

import java.util.List;

import lombok.Data;

/**
 *
 * @author <a href="mailto:grossopaforever@gmail.com">Jack Yin</a>
 * @version Jul 6, 2014 4:44:11 PM
 *
 */
@Data
public class WxUserInfoJson {
    private String openid;
    private String nickname;
    private Integer sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private List<String> privilege;
}
