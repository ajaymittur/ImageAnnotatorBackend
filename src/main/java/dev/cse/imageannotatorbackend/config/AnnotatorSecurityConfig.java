package dev.cse.imageannotatorbackend.config;

import dev.cse.imageannotatorbackend.service.CustomAnnotatorDetailsService;
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
@Order(1)
public class AnnotatorSecurityConfig extends WebSecurityConfigurerAdapter {

	private CustomAnnotatorDetailsService annotatorDetailsService;
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	private PasswordEncoder passwordEncoder; // Bean has been defined in UserSecurityConfig class

	@Autowired
	public AnnotatorSecurityConfig(CustomAnnotatorDetailsService annotatorDetailsService, CustomAuthenticationEntryPoint customAuthenticationEntryPoint, PasswordEncoder passwordEncoder) {
		this.annotatorDetailsService = annotatorDetailsService;
		this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(annotatorDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Handle logout on client side by changing basic authentication header username and password to "" and "" (empty or null)
		http
				.authorizeRequests()
				.antMatchers("/", "/annotator/create").permitAll()
				.antMatchers("/annotator/**").hasRole("ANNOTATOR")

				.and()
				.httpBasic()
				.authenticationEntryPoint(customAuthenticationEntryPoint)

				.and()
				.csrf().disable();
	}
}
