package com.itwillbs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final MyUserDetailsService myUserDetailsService;

	//  암호화 설정
	@Bean
	public PasswordEncoder passwordEncoder() {
//		BCryptPasswordEncoder : 스프링 시큐리티에서 제공하는 클래스 중 하나
//		            비밀번호를 암호와하는 데 사용할 수 있는 메서드 가진 클래스
//		BCrypt 해싱 함수를 사용해서 비밀번호를 인코딩해주는 메서드 제공
//		저장소에 저장된 비밀번호의 일치 여부를 확인해주는 메서드 제공
		return new BCryptPasswordEncoder();
	}
	
	//  주소 => 권한부여   
	//  / 주소, /login, /insert, /boardList   .permitAll() 
	//  /infot, /main,                       .hasRole("USER") 
	//  /list, /admin                        .hasRole("ADMIN")
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	http.authorizeHttpRequests().requestMatchers(&quot;/**&quot;).hasRole(&quot;USER&quot;).and().formLogin();
//	return http.build();	
		return http
				.authorizeHttpRequests(authorizeHttpRequestsCustomizer
						->authorizeHttpRequestsCustomizer
				.requestMatchers("/","/login/**","/insert/**",
						"/boardList/**","/content/**").permitAll()
				.requestMatchers("/info/**","/update/**",
						"/main/**","/delete/**","/boardUpdate/**",
						"/boardDelete/**","/boardWrite/**").hasAnyRole("USER","ADMIN")
				.requestMatchers("/list/**","/admin/**").hasRole("ADMIN")
				.anyRequest()
				.authenticated()
				)
				.formLogin(formLoginCustomizer
						-> formLoginCustomizer
						.loginPage("/login")
						.loginProcessingUrl("/loginPro")
						.usernameParameter("id")
						.passwordParameter("pass")
						.defaultSuccessUrl("/main")
						.failureUrl("/login")
				)
				.logout(logoutCustomizer
						-> logoutCustomizer
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/")
						)
				.userDetailsService(myUserDetailsService)
		.build();
	}
	
}
