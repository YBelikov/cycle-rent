package com.belikov.valteris.cycle.config;

import com.belikov.valteris.cycle.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String LOGIN = "/login";
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/*", "/fonts/*", "/js/*", "/img/*", LOGIN, "/signUp").permitAll()
//                .antMatchers("/user/**", "/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("/**").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage(LOGIN)
                .loginProcessingUrl(LOGIN)
                .usernameParameter("username")
                .passwordParameter("password")
                .successForwardUrl("/index").permitAll()

                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .permitAll()

                .and()
                .exceptionHandling().accessDeniedPage(LOGIN)

//                .and()
//                .oauth2Login()

                .and()
                .csrf()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userService);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**");
    }

//    @Bean
//    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
//        return map -> {
//          return new User();
//        };
//    }
}
