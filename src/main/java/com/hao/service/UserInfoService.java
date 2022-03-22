package com.hao.service;

import com.hao.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hao
 * @since 2022-03-20
 */
public interface UserInfoService extends IService<UserInfo> {
    UserInfo login(UserInfo userInfo);
}
