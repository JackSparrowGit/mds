# mds
This is a demo project for Multiple data sources example
# springcloud + mysql + MybatisPlus 多数据源主从复制整合demo
# description:
需要搭建本地一主两从mysql
3306:master,3307:slave1,3308:slave2
3306:
  SHOW MASTER STATUS
  File	            Position	Binlog_Do_DB	Binlog_Ignore_DB	Executed_Gtid_Set
  mysql-bin.000005	6443			
3307:
  STOP SLAVE;
  RESET SLAVE ALL;
  
  CHANGE MASTER TO 
  MASTER_HOST='192.168.1.89', 
  MASTER_USER='root', 
  MASTER_PASSWORD='root', 
  MASTER_PORT=3306,
  MASTER_LOG_FILE='mysql-bin.000005',
  MASTER_LOG_POS=6443;
  
  SET GLOBAL bulk_insert_buffer_size=8*1024*1024;
  START SLAVE ;
3308:
  STOP SLAVE;
  RESET SLAVE ALL;
  
  CHANGE MASTER TO 
  MASTER_HOST='192.168.1.89', 
  MASTER_USER='root', 
  MASTER_PASSWORD='root', 
  MASTER_PORT=3306,
  MASTER_LOG_FILE='mysql-bin.000005',
  MASTER_LOG_POS=6443;
  
  SET GLOBAL bulk_insert_buffer_size=8*1024*1024;
  START SLAVE ;
# 完整yml配置:
server:
  port: 8080
spring:
  aop:
    proxy-target-class: true
    auto: true
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      master:
        name: master
        username: root
        password: root
        url: jdbc:mysql://192.168.1.89:3306/db1?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&useSSL=false
        driver-class-name: com.mysql.jdbc.Driver
        # 连接池配置，说明请参考Druid Wiki，配置_DruidDataSource参考配置 -> https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_DruidDataSource%E5%8F%82%E8%80%83%E9%85%8D%E7%BD%AE
        filters: stat # 配置监控统计拦截的filters,默认值为stat，配置多个请以英文逗号分隔，如stat,wall,log4j
        initialSize: 5
        minIdle: 5
        maxActive: 20
        maxWait: 60000 # 配置获取连接等待超时的时间
        timeBetweenEvictionRunsMillis: 60000 # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        minEvictableIdleTimeMillis: 300000 # 配置一个连接在池中最小生存的时间，单位是毫秒
        validationQuery: SELECT 1
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        poolPreparedStatements: true # 是否打开PSCache
        maxPoolPreparedStatementPerConnectionSize: 20 # 指定每个连接上PSCache的大小
      slave:
        name: slave
        username: root
        password: root
        url: jdbc:mysql://192.168.1.89:3307/db1?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&useSSL=false
        driver-class-name: com.mysql.jdbc.Driver
        # 连接池配置，说明请参考Druid Wiki，配置_DruidDataSource参考配置 -> https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_DruidDataSource%E5%8F%82%E8%80%83%E9%85%8D%E7%BD%AE
        filters: stat # 配置监控统计拦截的filters,默认值为stat，配置多个请以英文逗号分隔，如stat,wall,log4j
        initialSize: 5
        minIdle: 5
        maxActive: 20
        maxWait: 60000 # 配置获取连接等待超时的时间
        timeBetweenEvictionRunsMillis: 60000 # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        minEvictableIdleTimeMillis: 300000 # 配置一个连接在池中最小生存的时间，单位是毫秒
        validationQuery: SELECT 1
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        poolPreparedStatements: true # 是否打开PSCache
        maxPoolPreparedStatementPerConnectionSize: 20 # 指定每个连接上PSCache的大小
      slave_a:
        name: slave
        username: root
        password: root
        url: jdbc:mysql://192.168.1.89:3308/db1?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&useSSL=false
        driver-class-name: com.mysql.jdbc.Driver
        # 连接池配置，说明请参考Druid Wiki，配置_DruidDataSource参考配置 -> https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_DruidDataSource%E5%8F%82%E8%80%83%E9%85%8D%E7%BD%AE
        filters: stat # 配置监控统计拦截的filters,默认值为stat，配置多个请以英文逗号分隔，如stat,wall,log4j
        initialSize: 5
        minIdle: 5
        maxActive: 20
        maxWait: 60000 # 配置获取连接等待超时的时间
        timeBetweenEvictionRunsMillis: 60000 # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        minEvictableIdleTimeMillis: 300000 # 配置一个连接在池中最小生存的时间，单位是毫秒
        validationQuery: SELECT 1
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        poolPreparedStatements: true # 是否打开PSCache
        maxPoolPreparedStatementPerConnectionSize: 20 # 指定每个连接上PSCache的大小
      # 以下为druid框架属性配置
      initial-size: 5
      max-active: 20
      min-idle: 5
      max-wait: 60000
      pool-prepared-statements: false
      validation-query: SELECT 1
      validation-query-timeout: 30000
      test-on-borrow: false
      test-on-return: false
      test-while-idle: true
      filters: stat,wall,log4j
      filter:
        stat:
          db-type: mysql
          log-slow-sql: true
          slow-sql-millis: 1000
          merge-sql: true
        wall:
          enabled: true
          config:
            delete-allow: true
            drop-table-allow: false
        slf4j:
          enabled: true
      web-stat-filter:
        enabled: true
        url-pattern: /*
        exclusions: /*.js,/*.gif,/*.jpg,/*.png,/*.css,/*.ico,/druid/*
        session-stat-enable: true
        session-stat-max-count: 10
        principal-session-name: user
        profile-enable: true
      stat-view-servlet:
        enabled: true
        url-pattern: /druid/*
        reset-enable: true
        login-username: admin
        login-password: admin
      aop-patterns: com.baicang.demo.service.*


mybatis-plus:
  mapper-locations: mappers/*.xml
  typeAliasesPackage: com.baicang.demo.domain
  typeHandlersPackage: com.baicang.demo.handler
  global-config:
    id-type: 3
    field-strategy: 2
    db-column-underline: false
    refresh-mapper: true
    is-capital-mode: false
    #逻辑删除配置
    logic-delete-value: 1
    logic-not-delete-value: 0
    sql-injector: com.baomidou.mybatisplus.mapper.LogicSqlInjector
  configuration:
    map-underscore-to-camel-case: true
    cache-enabled: true
    lazyLoadingEnabled: true
    jdbcTypeForNull: null
    multipleResultSetsEnabled: true


pagehelper:
    helperDialect: mysql
    supportMethodsArguments: true
    params: count=countSql
logging:
  level:
    com.baicang: debug
		
  
