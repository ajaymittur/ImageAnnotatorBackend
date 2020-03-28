package dev.cse.imageannotatorbackend.config;

import dev.cse.imageannotatorbackend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(2)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

	private CustomUserDetailsService userDetailsService;
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Autowired
	public UserSecurityConfig(CustomUserDetailsService userDetailsService, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
		this.userDetailsService = userDetailsService;
		this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Handle logout on client side by changing basic authentication header username and password to "" and "" (empty or null)
		http
				.authorizeRequests()
				.antMatchers("/user/create").permitAll()
				.antMatchers("/user/**").hasRole("USER")
				.anyRequest().permitAll()

				.and()
				.httpBasic()
				.authenticationEntryPoint(customAuthenticationEntryPoint)

				.and()
				.csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
