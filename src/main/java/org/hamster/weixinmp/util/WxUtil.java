/**
 * 
 */
package org.hamster.weixinmp.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.model.WxRespCode;
import org.springframework.http.HttpMethod;

import com.google.gson.Gson;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
public class WxUtil {

	private WxUtil() {
	}

	public static final Long currentTimeInSec() {
		return Long.valueOf(new Date().getTime() / 1000);
	}

	@SuppressWarnings("unchecked")
	public static final <T> T sendRequest(String url, HttpMethod method,
			Map<String, String> params, HttpEntity requestEntity,
			Class<T> resultClass) throws WxException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpRequestBase request = null;

		try {
			if (HttpMethod.GET.equals(method)) {
				request = new HttpGet();
			} else if (HttpMethod.POST.equals(method)) {
				request = new HttpPost();
				if (requestEntity != null) {
					((HttpPost) request).setEntity(requestEntity);
				}
			}
			URIBuilder builder = new URIBuilder(url);

			if (params != null) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					builder.addParameter(entry.getKey(), entry.getValue());
				}
			}
			request.setURI(builder.build());

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			String respBody = EntityUtils.toString(entity);
			if (entity != null) {
				EntityUtils.consume(entity);
			}

			if (String.class.isAssignableFrom(resultClass)) {
				return (T) respBody;
			} else {
				Gson gson = new Gson();

				if (respBody.indexOf("{\"errcode\"") == 0
						|| respBody.indexOf("{\"errmsg\"") == 0) {
					WxRespCode exJson = gson.fromJson(respBody,
							WxRespCode.class);
					if (WxRespCode.class.getName().equals(
							resultClass.getName())
							&& exJson.getErrcode() == 0) {
						return (T) exJson;
					} else {
						throw new WxException(exJson);
					}
				}
				T result = gson.fromJson(respBody, resultClass);
				if (result instanceof WxBaseEntity) {
					((WxBaseEntity) result).setCreatedDate(new Date());
				}
				return result;
			}

		} catch (IOException e) {
			throw new WxException(e);
		} catch (URISyntaxException e) {
			throw new WxException(e);
		}
	}

	public static StringEntity toJsonStringEntity(Object obj) {
		Gson gson = new Gson();
		return new StringEntity(gson.toJson(obj), Consts.UTF_8);
	}

	public static Map<String, String> getAccessTokenParams(String accessToken) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("access_token", accessToken);
		return result;
	}

	public static String getParameterizedUrl(String url, String... args) {
		String result = url;
		for (int i = 0; i < args.length; i += 2) {
			String p = args[i];
			String v = args[i + 1];
			result = result.replaceAll(p, v);
		}
		return result;
	}

}
