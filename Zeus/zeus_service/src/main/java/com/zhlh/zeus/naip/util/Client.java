package com.zhlh.zeus.naip.util;

import com.zhlh.zeus.constant.NaipConstants;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;


public class Client implements NaipConstants{
	// 太保的测试环境地址
//	private static String targetURL = UAT_URL;
	private static String targetURL = PRODUCT_URL;
	//太保的生产环境地址
//	private static String targetURL = "https://jttp.cpic.com.cn:8443/jttp/itxsvc/param";
	// 版本标识，固定值 3
	private static String messageRouter =  MESSAGEROUTER_VERSION;
	// 合作伙伴编码，由太保指定，每个商户不一样
	private static String partnerCode =  PARTNER_CODE;
	// 业务协议，固定值 CPIC_ECOM
	private static String documentProtocol =  DOCUMENTPROTOCOL_ZHLH;

	public static String sentHttpPostRequest(String requestMsg){
		
		String result = null;

		HttpClient httpclient = new DefaultHttpClient();

		// 导入数字证书并注册SSLSocketFactory
		try {

			registerSSLSocketFactory(httpclient);

			// 设置超时时间
			int timeout = 60000;
			HttpConnectionParams.setSoTimeout(httpclient.getParams(), timeout);

			// 注意：必须以post方式发送请求
			HttpPost httppost = new HttpPost(targetURL);

			// 设置请求参数
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// 版本标识
			params.add(new BasicNameValuePair(MESSAGEROUTER, messageRouter));
			// 业务伙伴代码
			params.add(new BasicNameValuePair(TRADINGPARTNER, partnerCode));
			// 业务协议
			params.add(new BasicNameValuePair(DOCUMENTPROTOCOL, documentProtocol));
			// xml请求报文
			params.add(new BasicNameValuePair(REQUESTMESSAGE, requestMsg));

			// 注意：编码必须是UTF-8
			HttpEntity request = new UrlEncodedFormEntity(params, NaipConstants.UTF8);
			httppost.setEntity(request);

			// 返回内容为xml，请使用xml解析工具对返回内容进行解析
			HttpResponse httpResponse = httpclient.execute(httppost);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity);
			}

		} catch (Exception e) {
			//.throwSnsgException("AITPE0001", "网络连接异常"+e);
		}
		return result;
	}

	private static void registerSSLSocketFactory(HttpClient httpclient) throws Exception {
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		// 注意：请将数字证书的路径（E:\\cer\\cpic_jttp.keystore）换成您保存本文件的具体路径
//		FileInputStream instream = new FileInputStream(new File("E:\\cer\\cpic_jttp.keystore"));
		Client client = new Client();
		//测试证书
//		InputStream instream = client.getClass().getResourceAsStream("/naip/individualAccount/cpic_jttp.keystore");
		//生产证书
		InputStream instream = client.getClass().getResourceAsStream("/naip/individualAccount/cpic_jttp_pe.keystore");
		try {
//			trustStore.load(instream, UAT_PWD.toCharArray());//测试
			trustStore.load(instream, PRODUCT_PWD.toCharArray());//生产
		} finally {
			instream.close();
		}

		SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
		Scheme sch = new Scheme(HTTPS, socketFactory, 443);

		httpclient.getConnectionManager().getSchemeRegistry().register(sch);
	}

}
