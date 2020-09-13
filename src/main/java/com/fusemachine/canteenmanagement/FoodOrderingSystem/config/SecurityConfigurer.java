package com.fusemachine.canteenmanagement.FoodOrderingSystem.config;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Utils.JwtRequestFilter;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.CustomUserDetailsService;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true
)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;


	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//

	@Autowired
	public JwtRequestFilter jwtAuthenticationFilter;

	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.accessDeniedHandler((request, response, accessDeniedException) -> {
					AccessDeniedHandler defaultAccessDeniedHandler = new AccessDeniedHandlerImpl();
					defaultAccessDeniedHandler.handle(request, response, accessDeniedException);
					})
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.headers().frameOptions().sameOrigin()
				.and()
				.authorizeRequests()
				.antMatchers("/api/v1/users/**").permitAll()
				.anyRequest().authenticated();
		//.anyRequest().permitAll();

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	//for encoding username and password
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
