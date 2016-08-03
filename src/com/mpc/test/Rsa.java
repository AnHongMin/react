package com.mpc.test;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class Rsa {
	public static void main(String[] args) {
		try {
			// RSA 공개키/개인키를 생성한다.
			KeyPairGenerator clsKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
			clsKeyPairGenerator.initialize(2048);
			KeyPair clsKeyPair = clsKeyPairGenerator.genKeyPair();
			Key clsPublicKey = clsKeyPair.getPublic();
			Key clsPrivateKey = clsKeyPair.getPrivate();
			KeyFactory fact = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec clsPublicKeySpec = fact.getKeySpec(clsPublicKey, RSAPublicKeySpec.class);
			RSAPrivateKeySpec clsPrivateKeySpec = fact.getKeySpec(clsPrivateKey, RSAPrivateKeySpec.class);
			
			// Public Key
			X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(clsPublicKey.getEncoded()); // key 가 바로 byte 배열
			PublicKey publicKey = fact.generatePublic(x509Spec);			
			System.out.println(publicKey + "\n\n");
			
			// Private Key
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clsPrivateKey.getEncoded());
			PrivateKey privateKey = fact.generatePrivate(keySpec);
			System.out.println(privateKey.toString());			
			 
			 
			// 암호화 한다.
			String strPinNumber = "TEST";

			Cipher clsCipher = Cipher.getInstance("RSA");
			clsCipher.init(Cipher.ENCRYPT_MODE, clsPublicKey);
			byte[] arrCipherData = clsCipher.doFinal(strPinNumber.getBytes());
			String strCipher = new String(arrCipherData);
		//	System.out.println("cipher(" + strCipher + ")");

			// 복호화 한다.
			clsCipher.init(Cipher.DECRYPT_MODE, clsPrivateKey);
			byte[] arrData = clsCipher.doFinal(arrCipherData);

			String strResult = new String(arrData);
		//	System.out.println("result(" + strResult + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}