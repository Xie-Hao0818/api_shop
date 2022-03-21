package com.hao.controller;


import com.hao.service.UserInfoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

}

