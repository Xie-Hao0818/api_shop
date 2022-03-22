package com.hao.controller;


import com.hao.entity.UserInfo;
import com.hao.service.UserInfoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(@RequestBody UserInfo user) {
        HashMap<String, Object> result = new HashMap<>();
        UserInfo login = service.login(user);
        if (login != null) {
            result.put("msg", "登录成功!");
            result.put("status", 200);
            //token
        }else{
            result.put("msg", "登录失败!");
            result.put("status", 400);
        }
        return result;
    }
}

