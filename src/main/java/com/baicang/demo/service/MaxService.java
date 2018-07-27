package com.baicang.demo.service;

import com.baicang.demo.domain.Max;
import com.baicang.demo.domain.PagingRequest;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * @Project: rsa
 * @Author: Jack
 * @CreateTime: 2018/7/26 16:25
 * @Describe:
 */
public interface MaxService extends IService<Max> {

    PageInfo<Max> listPage(PagingRequest pagingRequest, Wrapper wrapper);

}
