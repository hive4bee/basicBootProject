package com.boot08.security;


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
	
//	@Autowired
//	DataSource dataSource;
	
//	@Autowired
//	CustomUsersService customUsersService;
	
	//로그인이 되기 위해서는 SecurityConfig에 AuthenticationManagerBuilder를 주입해서 인증에 대한 처리를 해야 한다.
	//AuthenticationManagerBuilder는 말 그대로 인증에 대한 다양한 설정을 생성할 수 있다.
	//예를 들어, 메모리상에 있는 정보만을 이용한다거나, JDBC나 LDAP등의 정보를 이용해서 인증 처리가 가능하다.
//	@Autowired
//	private AuthenticationManagerBuilder auth;
//	@Autowired
//	public void configureGlobal() throws Exception {
//		log.info("build Auth global");
//		auth.inMemoryAuthentication().withUser("manager").password("1111").roles("MANAGER");
//	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		log.info("build Auth global");
		//1.메모리상의 인증
//		auth.inMemoryAuthentication()
//			.withUser("manager")
//			.password("1111")
//			.roles("MANAGER");
		//2.DB를 연동하고 실제 정보를 이용해서 로그인을 진행해 본다.
		//여기서 DB를 연동하는 방법은 크게 1)직접 SQL등을 지정해서 처리하는 방법과 2)기존에 작성된 Repository나 서비스 객체들을 이용해서 별도로 시큐리티 관련 서비스를 개발한다.
		//1)DataSource와 SQL을 이용해서 처리하는 방법//우선 DataSource타입의 객체를 주입해야 한다.//또한 SQL문은 사용자 계정 정보를 이용해서 필요한 정보와 권한을 확인하는 SQL이 필요하다.
//		String query1 = "select uid username, upw as password, true enabled from tbl_members where uid=?";
//		String query2 = "select member uid, role_name as role from tbl_member_roles where member=?";
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.usersByUsernameQuery(query1)
//			.rolePrefix("ROLE_")
//			.authoritiesByUsernameQuery(query2);
		
		auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("Security config.........................");
		http.authorizeRequests().antMatchers("/guest/**").permitAll();
		http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		
		//인가 받은 권한이 없는 관계로 접근이 막혔다면 /login이라는 경로를 이용해서 로그인 페이지를 띄워, 로그인을 해야 접근할 수 있다는 걸 인식시켤 필요가 있다.
//		http.formLogin();//이때 별도의 로그인 페이지를 제작하지 않아도 기본 템플릿을 제공해 준다.
		http.formLogin().loginPage("/login").loginProcessingUrl("/login").successHandler(loginSuccessHandler());//커스텀 로그인 페이지를 사용할 때...
		//passwordParameter(), usernameParameter()로 username, password name을 자유롭게 지정 가능...
		
		//access denied page
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		
		//invalidate session: logout
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		
		//http.userDetailsService(customUserService);
		http.rememberMe().key("boot08").userDetailsService(customUserService());
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
//스프링 시큐리티는 인증(Authentication)과 인가(Authorization)라는 개념이다.
//인증은 증명하다라는 의미이며, 인가는 권한부여, 허가와 같은 의미로 사용된다.
//인증에 대한 실제적인 처리를 담당하는 인증 매너지(Authentication Manager)이다.
//인증 매니저는 겨롸적으로 인증과 관련된 모든 정보를 UserDetails라는 타입으로 반환하는데, 
//이를 위해서 자신이 어떻게 관련 정보를 처리해야 하는지를 판단할 UserDetailsService라는 존재를 활용하게 된다.
//만일 개발자가 인증되는 방식을 수정하고 싶다면 UserDetailsService라는 인터페이스를 구현하고, 인증 매니저에 연결시켜 준다.
//결과로 반환되는 UserDetails는 기본적인 사용자 계정과 같은 정보와 더불어 사용자가 어떤 권한들을 가지고 있는지를 Collection 타입으로 가지고 있다.
//인증 매니저와 인증 정보를 제공하는 UserDetailsService의 관계를 좀 더 쉽게 표현하면 다음과 같다.(책 참고)
//AuthenticationManager(인증 매니저): AuthenticationManagerBuilder(인증 매니저 빌더), Authentication(인증)
//UserDetailsService인터페이스: UserDetailsManager인터페이스
//UserDetails인터페이스: User클래스
//UserDetailsService 인터페이스는 인증의 주체에 대한 정보를 가져오는 하나의 메서드만이 존재한다(loadUserByUsername())
//loadUserByUsername()메서드는 UserDetails라는 인터페이스 타입을 반환한다.(사용자의 계정 정보 + 사용자가 가진 권한 정보)

//모든 인증은 인증매니저(AuthenticationManager)를 통해서 이루어진다. 인증 매니저를 생성하기 위해 인증 매니저 빌더(AuthenticationManagerBuilder)라는 것이 사용된다.
//인증 매니저를 이용해서 인증(Authentication)이라는 작업이 수행된다.
//인증 매니저들은 인증/인가를 위한 UserDetailsService를 통해서 필요한 정보들을 가져온다.
//UserDetails는 사용자의 정보 + 권한 정보들의 묶음이다.
