package com.bochuan.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// @Bean 需要在配置类中使用，即类上需要加上@Configuration注解
// @Configuration与@Bean结合使用。@Configuration可理解为用spring的时候xml里面的<beans>标签，
// @Bean可理解为用spring的时候xml里面的<bean>标签。
// @Bean注解的作用之一就是能够管理第三方jar包内的类到容器中。 现在我们引入一个第三方的jar包，
// 这其中的某个类，StringUtil需要注入到我们的IndexService类中，因为我们没有源码，不能再StringUtil中增加@Component或者@Service注解。
// 这时候我们可以通过使用@Bean的方式，把这个类交到Spring容器进行管理，最终就能够被注入到IndexService实例中。

public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
