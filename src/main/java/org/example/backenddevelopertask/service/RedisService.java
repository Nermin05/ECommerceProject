package org.example.backenddevelopertask.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final StringRedisTemplate stringRedisTemplate;

    public void cacheUser(String email,String fullName) {
        stringRedisTemplate.opsForValue().set(email,fullName,10, TimeUnit.MINUTES);
    }
    public String getUser(String email) {
      return stringRedisTemplate.opsForValue().get(email);
    }
}
