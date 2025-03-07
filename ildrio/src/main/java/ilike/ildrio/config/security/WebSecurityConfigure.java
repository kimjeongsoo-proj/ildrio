package ilike.ildrio.config.security;

import ilike.ildrio.common.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled = true)
@EnableConfigurationProperties(WebSecurityProperties.class)
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {


	private final WebUserDetailsService userDetailsService;

	private final PasswordEncoder passwordEncoder;

	private final WebSecurityProperties properties;


	private final SessionUtil session;


	public WebSecurityConfigure(WebUserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder,
			WebSecurityProperties properties,
			SessionUtil session) {

		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.properties = properties;
		this.session = session;
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
				.antMatchers("/resources/**", "/error", "/inicis/**", "/uploads/**", "/admin/**");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		//@formatter:off
		httpSecurity
				.headers()
				.frameOptions()
				.disable()
				.and()
				.cors()
				.and()
				.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/**").permitAll()
				.anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage(properties.getLoginUrl())
				.loginProcessingUrl(properties.getLoginProcessingUrl())
				.permitAll()
				.and()
				.logout()
				.logoutUrl(properties.getLogoutUrl())
				.addLogoutHandler(new LogoutSuccessHandler(session))
				.permitAll()
				.and()
				.oauth2Login()
				.userInfoEndpoint()
				.and()
				.successHandler(successHandler());

		//@formatter:on
		httpSecurity.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	public AuthenticationFilter authenticationFilter() throws Exception {
		AuthenticationFilter filter = new AuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationFailureHandler(new LoginFaileHandler(session));
		filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(properties.getLoginProcessingUrl(), "POST"));
		filter.setUsernameParameter(properties.getUsernameParameter());
		filter.setPasswordParameter(properties.getPasswordParameter());
		return filter;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authProvider());
	}

	@Bean
	public AuthenticationProvider authProvider() {
		return new WebUserDetailsAuthenticationProvider(passwordEncoder, userDetailsService);
	}

	public SimpleUrlAuthenticationSuccessHandler successHandler() {
		return new SimpleUrlAuthenticationSuccessHandler(properties.getSuccessForwardUrl());
	}
}
