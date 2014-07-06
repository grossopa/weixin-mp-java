/**
 * 
 */
package org.hamster.weixinmp.model.oauth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author <a href="mailto:grossopaforever@gmail.com">Jack Yin</a>
 * @version Jul 6, 2014 2:26:55 PM
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxOAuthTokenJson {
    private String access_token;
    private Long expires_in;
    private String refresh_token; 
    private String openid;
    private String scope;
}
