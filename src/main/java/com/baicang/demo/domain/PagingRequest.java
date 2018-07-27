package com.baicang.demo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Project: rsa
 * @Author: Jack
 * @CreateTime: 2018/7/26 16:26
 * @Describe:
 */
@Data
public class PagingRequest implements Serializable {

    private static final long   serialVersionUID         = 1L;

    private int offset;
    private int limit;


    public PagingRequest () {
        this( 1, 10 );
    }

    public PagingRequest ( int offset, int limit ) {
        this.offset = offset;
        this.limit = limit;
    }

}
