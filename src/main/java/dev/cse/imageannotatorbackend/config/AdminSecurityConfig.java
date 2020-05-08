package dev.cse.imageannotatorbackend.config;

import dev.cse.imageannotatorbackend.service.AdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(3)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    private AdminDetailsService adminDetailsService;
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private PasswordEncoder passwordEncoder; // Bean has been defined in AnnotatorSecurityConfig class

    @Autowired
    public AdminSecurityConfig(AdminDetailsService adminDetailsService, CustomAuthenticationEntryPoint customAuthenticationEntryPoint, PasswordEncoder passwordEncoder) {
        this.adminDetailsService = adminDetailsService;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                cors().and()
                .csrf().disable()
                .antMatcher("/admin/**").authorizeRequests()
                .antMatchers("/", "/admin/create").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")

                .and()
                .httpBasic()
                .authenticationEntryPoint(customAuthenticationEntryPoint)

                .and()
                .sessionManagement().disable();
    }
}
