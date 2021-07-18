package io.javabrains.resumeportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService(userDetailsService);

        // set your config on the auth object  - works as a temporary stop gap
        auth.inMemoryAuthentication().withUser("foo").password("foo").roles("USER")
        .and().withUser("dewey").password("yogre").roles("USER")
        .and().withUser("admin").password("admin").roles("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


//        http.authorizeRequests()
//                .antMatchers("/edit").authenticated()
//                .antMatchers("/*").permitAll()
//                .and().formLogin();

        /*
        "/**" indicates all paths
        hasRole("ROLE")
        hasAnyRole("ROLE", "ROLE2")
         */
        // from most to least restricted
        http.authorizeRequests()
                .antMatchers("/edit").authenticated()
                .antMatchers("/*").permitAll()
                .and().formLogin();

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        // clear text - NOT FOR PRODUCTION
        return NoOpPasswordEncoder.getInstance();
    }
}
