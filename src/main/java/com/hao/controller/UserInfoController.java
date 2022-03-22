package com.hao.controller;


import com.hao.entity.UserInfo;
import com.hao.jwt.JWTUtil;
import com.hao.service.UserInfoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hao
 * @since 2022-03-20
 */
@RestController
@RequestMapping("/hao/userInfo")
public class UserInfoController {
    @Resource
    private UserInfoService service;

    @RequestMapping("/getAll")
    @Cacheable("aboutuser")
    public Object getAllUser() {
        return service.list();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody UserInfo user) {
        HashMap<String, Object> result = new HashMap<>();
        UserInfo login = service.login(user);
        if (login != null) {
            result.put("msg", "登录成功!");
            result.put("status", 200);
            //token
            HashMap<String, String> payload = new HashMap<>();
            payload.put("id", login.getId().toString());
            payload.put("username", login.getUsername());
            payload.put("password", login.getPassword());
            String token = JWTUtil.getToken(payload);
            result.put("token", token);
            result.put("uid", login.getId());
        } else {
            result.put("msg", "登录失败!");
            result.put("status", 400);
        }
        return result;
    }
}

