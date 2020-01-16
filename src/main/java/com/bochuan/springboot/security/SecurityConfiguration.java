package com.bochuan.springboot.security;

import com.bochuan.springboot.jwt.JwtTokenVerifier;
import com.bochuan.springboot.jwt.JwtUsernamePasswordAuthenticationFilter;
import com.bochuan.springboot.modal.User;
import com.bochuan.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//    }

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(),  JwtUsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers( "/login").permitAll()
                .antMatchers("/api/**").hasAnyRole(ApplicationUserRole.FARMER.name(), ApplicationUserRole.ADMIN.name())
//                .antMatchers(HttpMethod.DELETE, "/api/v1/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/api/v1/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/api/v1/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole(ApplicationUserRole.FARMER.name(), ApplicationUserRole.ADMIN.name())
                .anyRequest()
                .authenticated();
//                .and()
//                .httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    // @Bean：用@Bean标注方法等价于XML中配置的bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        // 新建Filter注册类
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        // 添加自定义Filter
        registrationBean.setFilter(getCorsFilter());

//        registrationBean.setName("my little CORS Filter");
        // 设置Filter的URL模式
        registrationBean.addUrlPatterns("/*");
        // 设置Filter的顺序
        registrationBean.setOrder(1);
        System.out.println("I am here loading......");
        return registrationBean;
    }

    @Bean
    public MyCorsFilter getCorsFilter() {
        return new MyCorsFilter();
    }


//    @Override
//    @Bean // so this will be instantiated for us
//    protected UserDetailsService userDetailsService() {
//        UserDetails gangbalaUser = User.builder()
//                .username("gangbala")
//                .password(passwordEncoder.encode("666666"))
////                .roles(ApplicationUserRole.ADMIN.name()) // ROLE_ADMIN
//                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
//                .build();
//
//        UserDetails sagiUser = User.builder()
//                .username("sagi")
//                .password(passwordEncoder.encode("666666"))
////                .roles(ApplicationUserRole.FARMER.name()) // ROLE_FARMER
//                .authorities(ApplicationUserRole.FARMER.getGrantedAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(
//                gangbalaUser,
//                sagiUser
//        );
//    }
}
