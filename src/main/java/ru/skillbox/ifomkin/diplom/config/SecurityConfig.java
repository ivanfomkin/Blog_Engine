//package ru.skillbox.ifomkin.diplom.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final UserDetailsService userDetailsService;
//
//    @Autowired
//    public SecurityConfig(@Qualifier("customUserDetailService") UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
////        auth.inMemoryAuthentication()
////                    .withUser("u1")
////                    .password("1")
////                    .authorities("ROLE_USER")
////                .and()
////                    .withUser("u2")
////                    .password("2")
////                    .authorities("ROLE_USER");
//    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return NoOpPasswordEncoder.getInstance();
////
////
////    }
//}
