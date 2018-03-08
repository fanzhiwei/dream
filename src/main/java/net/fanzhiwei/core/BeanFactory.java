package net.fanzhiwei.core;

import net.fanzhiwei.core.filter.ApiCommonFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用做各种bean生成
 */
@Configuration
public class BeanFactory {
    @Autowired
    private ApiCommonFilter apiCommonFilter;

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(apiCommonFilter);
        return registrationBean;
    }
}
