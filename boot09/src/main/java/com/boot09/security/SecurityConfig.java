package com.boot09.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		log.info("build Auth global");

		auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("Security config.........................");
		http.authorizeRequests().antMatchers("/boards/list").permitAll();
		http.authorizeRequests().antMatchers("/boards/register").hasAnyRole("BASIC","MANAGER","ADMIN");
		http.authorizeRequests().antMatchers("/").hasRole("ADMIN");
		
		//인가 받은 권한이 없는 관계로 접근이 막혔다면 /login이라는 경로를 이용해서 로그인 페이지를 띄워, 로그인을 해야 접근할 수 있다는 걸 인식시켤 필요가 있다.
//		http.formLogin();//이때 별도의 로그인 페이지를 제작하지 않아도 기본 템플릿을 제공해 준다.
		http.formLogin().loginPage("/login").loginProcessingUrl("/login").successHandler(loginSuccessHandler());//커스텀 로그인 페이지를 사용할 때...
		//passwordParameter(), usernameParameter()로 username, password name을 자유롭게 지정 가능...
		
		//access denied page
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		
		//invalidate session: logout
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		
		//http.userDetailsService(customUserService);
		http.rememberMe().key("boot09").userDetailsService(customUserService());
	}
	//authorizeRequests()는 HttpServletRequest를 이용한다는 것을 의미한다.
	//antMatches()에서는 특정한 경로를 지정한다. 이후 permitAll()은 모두, hasRole()은 특정 권한을 가진 사람만이 접근할 수 있음을 의미한다.
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
		
	}
	
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean 
	public UserDetailsService customUserService() {
		return new CustomUsersService();
	}
	
	
}
