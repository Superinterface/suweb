package com.sujianan.util;

import redis.clients.jedis.Jedis;

public class RedisConnect {
	public static void main(String[] args) {
		Jedis jd = new Jedis("127.0.0.1");
		jd.append("k01", "001001001");
		System.out.println(jd.get("k01"));
	}
}
