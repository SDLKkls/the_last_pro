package com.baizhi.cmm.conf;

import com.baizhi.cmm.realm.AdminRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConf {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/login.jsp");
        Map<String, String> map = new HashMap<>();
        map.put("/admin/loginAdmin", "anon");
        map.put("/code/getcode", "anon");
        map.put("/login.jsp", "anon");
        map.put("/img/favicon.ico", "anon");
        map.put("/css/common.css", "anon");
        map.put("/css/login.css", "anon");
        map.put("/script/jquery.js", "anon");
        map.put("/script/common.js", "anon");
        map.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    @Bean
    public SecurityManager getSecurityManager(AdminRealm adminRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(adminRealm);
        return securityManager;
    }

    @Bean
    public AdminRealm getAdminRealm() {
        return new AdminRealm();
    }

}
