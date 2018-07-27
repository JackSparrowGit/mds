package com.baicang.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Project: rsa
 * @Author: Jack
 * @CreateTime: 2018/7/26 15:17
 * @Describe:
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Object determineCurrentLookupKey() {
        Object dataSourceKey = DynamicDataSourceContextHolder.getDataSourceKey();
        logger.info("Current DataSource is [{}]", dataSourceKey);
        return dataSourceKey;
    }
}
