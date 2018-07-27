package com.baicang.demo.domain;

import lombok.Data;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @Project: rsa
 * @Author: Jack
 * @CreateTime: 2018/7/26 15:54
 * @Describe:
 */
@Data
@TableName("t_max")
public class Max extends Model<Max> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
