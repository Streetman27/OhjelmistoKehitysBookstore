package fi.swd.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.swd.Bookstore.web.UserDetailServiceImpl;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailServiceImpl userDetailsService;	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    	http
        .authorizeRequests()
        	.antMatchers("/css/**").permitAll() // Enable css when logged out
        	.and()
        .authorizeRequests()
        	.antMatchers("/","/home").permitAll()
        	.and()	
        .authorizeRequests()
        	.anyRequest().authenticated()
        	.and()
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/booklist")
          .permitAll()
          .and()
      .logout()
          .permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	/*	
     	auth
    	.inMemoryAuthentication()
    	.withUser("user")
    	.password("user")
    	.roles("USER");
    	auth
    	.inMemoryAuthentication()
    	.withUser("admin")
    	.password("admin")
    	.roles("ADMIN"); 
    	*/
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}