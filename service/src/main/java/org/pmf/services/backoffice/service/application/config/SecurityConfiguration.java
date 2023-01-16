package org.pmf.services.office.service.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled= true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
            "/**swagger**/**",
            "/healthcheck",
            "/swagger-resources",
            "/swagger-resources/**",
            "/integration/**",
            "/financial-request/**",
            "/v2/api-docs"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
            .formLogin().disable()
            .csrf().disable()
            .sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeRequests()
            .antMatchers(AUTH_WHITELIST).permitAll()
//            .antMatchers(HttpMethod.GET, "/orderpackages1/**").hasAuthority("SCOPE_financial:order-package")
            .antMatchers("/**").authenticated()
            .and()
            .oauth2ResourceServer().jwt();
    }
}