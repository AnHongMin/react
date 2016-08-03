package com.mpc.test;

import com.mpc.util.SeedCrypto;

public class SeedTest {

	public static void main(String[] args) {
		String str = "hongmin.an";
		String temp1 = SeedCrypto.encrypt(str);
		System.out.println(temp1);
		String temp2 = SeedCrypto.decrypt(temp1);
		System.out.println(temp2);
	}

}
