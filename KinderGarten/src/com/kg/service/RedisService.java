package com.kg.service;

import redis.clients.jedis.ShardedJedis;

public interface RedisService {
	public ShardedJedis getRedisClient();

	public void returnResource(ShardedJedis shardedJedis);

	public void returnResource(ShardedJedis shardedJedis, boolean broken);
}
