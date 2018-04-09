package com.melt.test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>(10);
		
		map.put("1", "12");
		System.out.println(map);
		
	}
}
