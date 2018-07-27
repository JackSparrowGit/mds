package com.baicang.demo.service.impl;

import com.baicang.demo.domain.Max;
import com.baicang.demo.domain.PagingRequest;
import com.baicang.demo.mapper.MaxMapper;
import com.baicang.demo.service.MaxService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * @Project: rsa
 * @Author: Jack
 * @CreateTime: 2018/7/26 16:28
 * @Describe:
 */
@Service
public class MaxServiceImpl extends ServiceImpl<MaxMapper, Max> implements MaxService {

    @Override
    public PageInfo<Max> listPage(PagingRequest pagingRequest, Wrapper wrapper) {
        PageHelper.startPage(pagingRequest.getOffset(), pagingRequest.getLimit());
        return new PageInfo<>(super.selectList(wrapper));
    }
}
