/**
 * 
 */
package org.hamster.weixinmp.test.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.hamster.weixinmp.test.xml.WxXMLUtilTest;
import org.junit.Ignore;
import org.junit.Test;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 30, 2013
 */
public class WxControllerManualTest {
	
	public static final String WX_URL = "http://localhost:8080/rest/weixinmp";
	
	@Test 
	@Ignore
	public void testPostMsgText() throws ClientProtocolException, IOException {
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(WX_URL);

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("signature", "38f004a5f222473f3abd85fd8e4b1de2349119c6"));
		params.add(new BasicNameValuePair("timestamp", "1375192987"));
		params.add(new BasicNameValuePair("nonce", "1374785014"));
		
		httppost.setEntity(new StringEntity(WxXMLUtilTest.MSG_TEXT_XML));
		
		//Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream = entity.getContent();
		    try {
		    	String result = IOUtils.toString(instream);
		    	System.out.println(result);
		    } finally {
		        instream.close();
		    } 
		}
	}
	
	public static final void main(String[] args) {
		try {
			new WxControllerManualTest().testPostMsgText();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
