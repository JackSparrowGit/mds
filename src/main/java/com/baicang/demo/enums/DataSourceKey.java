package com.baicang.demo.enums;

import java.util.function.Supplier;

/**
 * @Project: rsa
 * @Author: Jack
 * @CreateTime: 2018/7/26 15:16
 * @Describe:
 */
public enum DataSourceKey implements Supplier<DataSourceKey> {

    master("master"),
    slave("slave"),
    slave_a("slave_a"),
    ;

    private String name;

    DataSourceKey(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public DataSourceKey get() {
        return this;
    }
}
