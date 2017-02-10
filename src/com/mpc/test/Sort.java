package com.mpc.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Sort {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<10; i++){
			list.add(String.valueOf(i));	
		}
		
		Comparator<String> compare = new Comparator<String>(){			
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		};
		// 오름차순
		Collections.sort(list,compare);
		
		// 내림차순
		Collections.reverse(list); 
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
	}
}
