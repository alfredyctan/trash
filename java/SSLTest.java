package com.afc.gateway.zuul.external;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpRequest;
import org.springframework.cloud.commons.httpclient.DefaultApacheHttpClientConnectionManagerFactory;
import org.springframework.cloud.commons.httpclient.DefaultApacheHttpClientFactory;

public class SSLTest {

	public static void main2(String[] args) throws Exception {
		System.setProperty("javax.net.ssl.keyStore", "src/test/resources/keystore/zuul-external.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "localhost");
		System.setProperty("javax.net.ssl.trustStore", "src/test/resources/keystore/zuul-external.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "localhost");
//		System.setProperty("javax.net.debug", "ssl");

		
//		SSLContext sslContext = SSLContexts.createDefault();
//		
//		SSLContexts.
		
//		System.out.println("DefaultSSLParameters : " + sslContext.getDefaultSSLParameters());
		
		CloseableHttpClient client = newClient();

		HttpRequest httpRequest = new BasicHttpRequest("GET", "/filuname-afc/api/kernel/v1/labels/en");
		HttpHost httpHost = new HttpHost("stage.afc.link", 443, "https");

		CloseableHttpResponse httpResponse = client.execute(httpHost, httpRequest);

		InputStream inputStream = httpResponse.getEntity().getContent();
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}

		System.out.println(result.toString("UTF-8"));
	}

	protected static CloseableHttpClient newClient() throws Exception {
		
		final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
		
		
		
//		SSLContext systemSsl;
//		try {
//			systemSsl = SSLContext.getInstance("TLS");
//			System.out.println("system ssl : " + systemSsl.getProvider().getName());
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		SSLContext sslContext = SSLContexts.createDefault();
//		System.out.println("system ssl : " + sslContext.getProvider().getName());
//		
//		KeyStore ks = KeyStore.getInstance("JKS");
//		ks.load(new FileInputStream("src/test/resources/keystore/zuul-external.jks"), "localhost".toCharArray());
//
//		TrustManagerFactory tmf = TrustManagerFactory.getInstance("PKIX");
//		tmf.init(ks);
//		
//		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
//		kmf.init(ks, "localhost".toCharArray());
//		
//		
////		List<TrustManager> tms =  Arrays.asList(tmf.getTrustManagers());
////		tms.add(new DisableValidationTrustManager());
//		
//		TrustManager[] tms = new TrustManager[tmf.getTrustManagers().length + 1];
//		tms[0] = new DisableValidationTrustManager();
//		int i = 1;
//		for (TrustManager tm : tmf.getTrustManagers()) {
//			tms[i++] = tm;
//		}
//		
//		
//		sslContext.init(kmf.getKeyManagers(), tms, new SecureRandom());
//		 
		
		HttpClientBuilder builder = new DefaultApacheHttpClientFactory(
			HttpClientBuilder.create()//.setSSLContext(sslContext)
		).createBuilder()//.setSSLContext(sslContext)
		;
		
		builder.useSystemProperties();
		
		return builder.setDefaultRequestConfig(requestConfig).setConnectionManager(
			new DefaultApacheHttpClientConnectionManagerFactory().newConnectionManager(
				false, 1, 1, 100, TimeUnit.SECONDS, null
			)
		).disableRedirectHandling().build();
	}

	public static void main(String[] args) throws Exception {
		System.setProperty("javax.net.ssl.keyStore", "src/test/resources/keystore/zuul-external.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "localhost");
		System.setProperty("javax.net.ssl.trustStore", "src/test/resources/keystore/zuul-external.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "localhost");
		System.setProperty("javax.net.debug", "ssl");

		

		URL url = new URL("https://stage.afc.link/filuname-afc/api/kernel/v1/labels/en");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		InputStream inputStream = conn.getInputStream();
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}

		System.out.println(result.toString("UTF-8"));
	}
	
	
	public static void main3(String[] args) throws Exception {
		System.setProperty("javax.net.ssl.keyStore", "src/test/resources/keystore/zuul-external.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "localhost");
		System.setProperty("javax.net.ssl.trustStore", "src/test/resources/keystore/zuul-external.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "localhost");
		System.setProperty("javax.net.debug", "ssl");

		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(new FileInputStream("src/test/resources/keystore/zuul-external.jks"), "localhost".toCharArray());
		
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(ks, "localhost".toCharArray());
		
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("PKIX");
		tmf.init(ks);
		
		SSLContext sslContext1 = SSLContext.getInstance("TLS");
		System.out.println("sslContext1");
		init(kmf, tmf, sslContext1);
		
		SSLContext sslContext2 = SSLContext.getInstance("TLS");
		System.out.println("sslContext2");
		init(kmf, tmf, sslContext2);
	}

	private static void init(KeyManagerFactory kmf, TrustManagerFactory tmf, SSLContext sslContext) throws Exception {
//		
//		Field[] fs = sslContext.getClass().getDeclaredFields();
//		for (Field f : fs) {
//			System.out.println(f.getName());
//		}
//		
		
		Field contextSpiField = sslContext.getClass().getDeclaredField("contextSpi");
		contextSpiField.setAccessible(true);
		sun.security.ssl.SSLContextImpl contextSpi = (sun.security.ssl.SSLContextImpl)contextSpiField.get(sslContext); 

		System.out.println("contextSpi hash: " + contextSpi.hashCode());
		
		Class parent = contextSpi.getClass().getSuperclass().getSuperclass().getSuperclass();
		System.out.println("parent : " + parent.getName());

		Field keyManagerField = parent.getDeclaredField("keyManager");
		keyManagerField.setAccessible(true);
		System.out.println("before engineInit key Manager : " + keyManagerField.get(contextSpi));
		
//		Field[] fs2 = contextSpi.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredFields();
//		System.out.println("f #: " + fs2.length);
//		for (Field f : fs2) {
//			System.out.println("f2 : " +f.getName());
//		}
//
//		Method[] methods = contextSpi.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredMethods();
//		System.out.println("m #: " + methods.length);
//		for (Method m : methods) {
//			System.out.println("m : " + m.getName());
//		}
		
		Method engineInitMethod = parent.getDeclaredMethod("engineInit", javax.net.ssl.KeyManager[].class, javax.net.ssl.TrustManager[].class, java.security.SecureRandom.class);
		engineInitMethod.setAccessible(true);
		engineInitMethod.invoke(contextSpi, kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
		
		System.out.println("after engineInit key Manager : " + keyManagerField.get(contextSpi));
	}
}
