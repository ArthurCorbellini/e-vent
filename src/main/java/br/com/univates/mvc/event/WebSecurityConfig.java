package br.com.univates.mvc.event;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Arthur
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/home/**").permitAll()
		.antMatchers("/register/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/user/eventos", true).permitAll())
		.logout(form -> form.logoutUrl("/logout").logoutSuccessUrl("/home")).csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// para criar o usuário no banco
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();		
//		UserDetails user = User.builder().username("adm").password(encoder.encode("adm")).roles("ADM").build();		
//		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder).withUser(user);
		
		// funcionando com os usuários já criados
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
