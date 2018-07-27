package com.baicang.demo.controller;

import com.baicang.demo.domain.Max;
import com.baicang.demo.domain.PagingRequest;
import com.baicang.demo.domain.RespEntity;
import com.baicang.demo.service.MaxService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Project: rsa
 * @Author: Jack
 * @CreateTime: 2018/7/26 16:30
 * @Describe:
 */
@RestController
@RequestMapping("/max")
public class MaxController {

    @Autowired
    private MaxService maxService;

    @GetMapping
    public RespEntity<PageInfo<Max>> list(PagingRequest pagingRequest) {
        return RespEntity.ok().setResponseContent(maxService.listPage(pagingRequest, null));
    }

    @GetMapping("{id}")
    public RespEntity<Max> get(@PathVariable String id) {
        return RespEntity.ok().setResponseContent(maxService.selectById(id));
    }


    @PostMapping
    public RespEntity insert(@RequestBody Max max) {
        if (!maxService.insert(max)) {
            return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功");
    }

    @PutMapping
    public RespEntity update(@RequestBody Max message) {
        if (!maxService.updateById(message)) {
            return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }

    @DeleteMapping
    public RespEntity delete(@PathVariable Long id) {
        if (!maxService.deleteById(id)) {
            return RespEntity.badRequest("删除失败");
        }
        return RespEntity.ok("删除成功");
    }
}
