//package com.nullexceptional.digibooky.domain.security;
//
//import com.nullexceptional.digibooky.domain.members.Role;
//import com.nullexceptional.digibooky.domain.security.authentication.external.UserAuthenticationProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final AuthenticationEntryPoint authenticationEntryPoint;
//    private final UserAuthenticationProvider userAuthenticationProvider;
//
//    public SecurityConfig(AuthenticationEntryPoint authenticationEntryPoint, UserAuthenticationProvider userAuthenticationProvider) {
//        this.authenticationEntryPoint = authenticationEntryPoint;
//        this.userAuthenticationProvider = userAuthenticationProvider;
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                .anyRequest().authenticated()
//                .and().httpBasic()
//                .authenticationEntryPoint(authenticationEntryPoint);
//    }
//    //configurer les niveaux d'access aux controllers
//    /*private void configureAntMatchers(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/users").hasRole(Role.Member.toString())
//               .antMatchers("/army/promote/**").hasRole("HUMAN_RELATIONSHIPS")
//                .and().httpBasic()
//                .authenticationEntryPoint(authenticationEntryPoint);
//    }*/
//
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(userAuthenticationProvider);
//    }
//}
