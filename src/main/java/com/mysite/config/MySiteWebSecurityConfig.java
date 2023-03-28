package com.mysite.config;

import com.mysite.service.auth.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@EnableWebSecurity
public class MySiteWebSecurityConfig {

    @Value("${app.property.allowed-origins}")
    private String[] permitAllPaths;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RoleService roleService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        Map<String, List<String[]>> roleResourceMap = roleService.buildRoleResourceMap();

        // role-resource mapping
        http.authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(permitAllPaths).permitAll();
                    roleResourceMap.forEach((k, v) -> v.forEach(e -> authorize.requestMatchers(HttpMethod.valueOf(e[0]), e[1]).hasRole(k)));
                    authorize.anyRequest().authenticated();
                }
        );

        // Form login Configuration
        http.formLogin(form -> form.successHandler((request, response, authentication) -> {

        }).failureHandler((request, response, exception) -> {

        }));

        return http.build();
    }
}
