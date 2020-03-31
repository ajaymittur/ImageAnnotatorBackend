package dev.cse.imageannotatorbackend.config;

import dev.cse.imageannotatorbackend.service.MyUserDetailsService;
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
@Order(2)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

	private MyUserDetailsService myUserDetailsService;
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	private PasswordEncoder passwordEncoder; // Bean has been defined in AnnotatorSecurityConfig class

	@Autowired
	public UserSecurityConfig(MyUserDetailsService myUserDetailsService, CustomAuthenticationEntryPoint customAuthenticationEntryPoint, PasswordEncoder passwordEncoder) {
		this.myUserDetailsService = myUserDetailsService;
		this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Handle logout on client side by changing basic authentication header username and password to "" and "" (empty or null)
		http
				.csrf().disable()
				.antMatcher("/user/**").authorizeRequests()
				.antMatchers("/", "/user/create").permitAll()
				.antMatchers("/user/**").hasRole("USER")

				.and()
				.httpBasic()
				.authenticationEntryPoint(customAuthenticationEntryPoint)

				.and()
				.sessionManagement().disable();
	}

	// Configuration class to match all remaining requests
//	@Configuration
//	@Order(3)
//	public static class RootSecurityConfig extends WebSecurityConfigurerAdapter {
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http
//					.csrf().disable()
//					.authorizeRequests().anyRequest().authenticated()
//
//					.and()
//					.httpBasic()
//
//					.and()
//					.sessionManagement().disable()
//			;
//		}
//	}
}
