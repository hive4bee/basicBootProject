package com.hive4bee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.java.Log;

@EnableWebSecurity
@Configuration
@Log
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config.....");
//		http.authorizeRequests().antMatchers("/guest/**").permitAll();
//		http.authorizeRequests().antMatchers("/manager/**").hasAnyRole("MANAGER");
//		http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN");
		http.authorizeRequests()
			.antMatchers("/boards/list").permitAll()
			.antMatchers("/boards/register").hasAnyRole("BASIC", "MANAGER", "ADMIN");
		
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		//http.formLogin().loginPage("/login");
		http.formLogin().loginPage("/login").loginProcessingUrl("/login").successHandler(loginSuccessHandler());//커스텀 로그인 페이지를 사용할 때...
		http.rememberMe().key("boot08_thymeleaf").userDetailsService(customUserService());
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}
	
	@Bean 
	public UserDetailsService customUserService() {
		return new CustomUsersService();
	}
	
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
}
