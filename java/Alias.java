package com.afc.gateway.zuul.external;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PasswordProtection;
import java.util.Enumeration;

public class Alias {

	public static void main(String[] args) throws Exception {
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(new FileInputStream("src/test/resources/keystore/zuul-external.jks"), "localhost".toCharArray());
		for (Enumeration<String> aliases = ks.aliases(); aliases.hasMoreElements(); ) {
			String alias = aliases.nextElement();
			System.out.println(alias);
			
			if (alias.startsWith("abcd")) {
				Entry entry = ks.getEntry(alias, new PasswordProtection("pass".toCharArray()));
				ks.deleteEntry(alias);
				ks.setEntry("newalias", entry, new PasswordProtection("localhost".toCharArray()));
				ks.store(new FileOutputStream("src/test/resources/keystore/zuul-external2.jks"), "localhost".toCharArray());
			}
		}
	}

}
