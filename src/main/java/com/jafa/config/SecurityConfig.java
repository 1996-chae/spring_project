package com.jafa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.jafa.security")
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	LogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository =  new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);
				
		http.formLogin()
			.loginPage("/member/login") 
			.loginProcessingUrl("/member/login")
			.usernameParameter("loginId")
			.passwordParameter("loginPwd")
			.failureHandler(authenticationFailureHandler)
			.successHandler(authenticationSuccessHandler);
			
		
        http.exceptionHandling()
        	.accessDeniedHandler(accessDeniedHandler);
        
        http.logout()
        	.logoutUrl("/member/logout")
        	.logoutSuccessHandler(logoutSuccessHandler)
        	.deleteCookies("remember-me","JSESSION_ID");
        
		http.rememberMe()
			.key("leesoungchae") 
			.tokenRepository(persistentTokenRepository())
			.rememberMeParameter("remember-me")
			.tokenValiditySeconds(86400);
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN","MEMBER");
//      auth.inMemoryAuthentication().withUser("leekwanghyup").password("{noop}1234").roles("MEMBER");
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
    }
}
