package com.hao;

import com.hao.entity.UserInfo;
import com.hao.mapper.UserInfoMapper;
import com.hao.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpAxiosApplicationTests {

    @Resource
    private UserInfoMapper mapper;
    @Resource
    private UserInfoService service;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testMysql() {
        List<UserInfo> userInfos = mapper.selectList(null);
        for (UserInfo userInfo : userInfos) {
            System.out.println(userInfo);
        }
    }

    @Test
    void testRedis() {
        redisTemplate.opsForValue().set("name", "hao");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

}
