package com.mpc.util;

import java.net.InetAddress;

public class PingUtil {

	public static void main(String[] args) {
		try {
			
			InetAddress tagetIp = InetAddress.getByName("209.118.34.197");
			// 타임아웃 2초
			boolean reachable =  tagetIp.isReachable(2000);
			if (reachable) {
				System.out.println("Test OK!!!");
			} else {
				System.out.println("Test NG!!!");
			}

			System.out.println("Name : "+tagetIp.getHostName());
			System.out.println("Addr : "+tagetIp.getHostAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
