package net.fanzhiwei.dao.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;

import javax.sql.DataSource;

/**
 * @author fanzhiwei
 */
@Configuration
@MapperScan(basePackages = "net.fanzhiwei.dao.mapper", sqlSessionFactoryRef = "myBatisSqlSessionFactory")
public class MyBatisSqlSessionFactoryConfig {
    @Bean(name = "myBatisSqlSessionFactory")
    @Primary
    public SqlSessionFactory getMyBatisSqlSessionFactory(@Qualifier("primaryDataSource") DataSource datasource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(datasource);
        factory.setVfs(SpringBootVFS.class);
        factory.setConfigLocation(new DefaultResourceLoader().getResource("dao/mybatis-config.xml"));
        return factory.getObject();
    }
}
