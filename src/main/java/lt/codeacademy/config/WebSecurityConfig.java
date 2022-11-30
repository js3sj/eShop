package lt.codeacademy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*import lt.codeacademy.Configuration;
import lt.codeacademy.Order;
import lt.codeacademy.WebSecurity;
import lt.codeacademy.WebSecurityConfigurerAdapter;*/
import lt.codeacademy.handlers.UserAuthenticationSuccessHandler;
import lt.codeacademy.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	private UserAuthenticationSuccessHandler successHandler;
	
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/user/create").permitAll()
        	.antMatchers("/user/registrationFailed").permitAll()
        	.antMatchers("/user/registrationFailed/**").permitAll()
        	.antMatchers("/user/registrationSuccess").permitAll()
        	.antMatchers("/user/registrationSuccess/**").permitAll()
        	.antMatchers("/items/index").hasAnyAuthority("USER", "ADMIN")
        	.antMatchers("/orders/index").hasAnyAuthority("USER", "ADMIN")
            .antMatchers("/basket/index").hasAnyAuthority("USER", "ADMIN")
            .antMatchers("/user/**").hasAuthority("ADMIN")
            .antMatchers("/user/index").hasAuthority("ADMIN")
            .antMatchers("/items/edit/**").hasAuthority("ADMIN")
            .antMatchers("/items/update/**").hasAuthority("ADMIN")
            .antMatchers("/items/delete/**").hasAuthority("ADMIN")
            .antMatchers("/items/create/**").hasAuthority("ADMIN")
            .antMatchers("/orders/delete/**").hasAuthority("ADMIN")
            .antMatchers("/user/**").hasAuthority("ADMIN")
            .anyRequest().permitAll()//.authenticated() // nutrinti permitALL ir naudoti.authenticated()
            .and()
            .formLogin().successHandler(successHandler).loginPage("/login").loginProcessingUrl("/process-login").permitAll()
            .and()
            .logout().permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/403")
            ;
    }
}
