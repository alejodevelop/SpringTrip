package com.example.demo.security;

import java.security.Key;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // this line helps to authorize the use of preAuthorize annotations
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

        /*
         * creating a passwordEncoder instance in order to encode the password before
         * setting the user password
         */
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
                this.passwordEncoder = passwordEncoder;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http

                                // this csrf protection has to be used for any request that could be processed
                                // by a browser by normal users.
                                // setting how the csrf tokens are generated, but it is configure for default
                                // .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                                // .and()
                                .csrf().disable()
                                .authorizeRequests()
                                // ant matchers and permit all let us whitelist the specified urls to be public
                                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                                // restrict the access to specified path only for whose have the right role
                                .antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
                                // .antMatchers(HttpMethod.DELETE, "/management/api/**")
                                // .hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                                // .antMatchers(HttpMethod.POST, "/management/api/**")
                                // .hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                                // .antMatchers(HttpMethod.PUT, "/management/api/**")
                                // .hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                                // .antMatchers(HttpMethod.GET, "management/api/**")
                                // .hasAnyRole(ApplicationUserRole.ADMIN.name(),
                                // ApplicationUserRole.ADMINTRAINEE.name())
                                .anyRequest()
                                .authenticated()
                                .and()
                                // enable basic auth, what shows a pop up form for sign in
                                // you need to sign in for every single request with basic auth
                                // it is useful for secur somethin like a API
                                // .httpBasic();
                                // form based authentication
                                .formLogin()
                                // change loging page
                                .loginPage("/login").permitAll()
                                // this would be the redirect page once login is successful
                                .defaultSuccessUrl("/courses", true)
                                .passwordParameter("password")
                                .usernameParameter("username")
                                .and()
                                // help to extend the time of the session
                                // .rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) //
                                // defaults to 2 weeks
                                // .Key("somethingverysecured");
                                // .rememberMeParameter("remember-me")
                                .logout()
                                .logoutUrl("/logout")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                                .clearAuthentication(true)
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID", "remember-me")
                                .logoutSuccessUrl("/login");

        }

        // creating new users different than default user

        @Override
        @Bean
        protected UserDetailsService userDetailsService() {
                UserDetails jacobUser = User.builder()
                                .username("jacob")
                                // it's necesary to have a password encoder
                                .password(passwordEncoder.encode("jacob"))
                                // .roles(ApplicationUserRole.STUDENT.name()) // ROLE_STUDENT
                                .authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
                                .build();

                UserDetails lindaUser = User.builder()
                                .username("linda")
                                .password(passwordEncoder.encode("linda"))
                                // .roles(ApplicationUserRole.ADMIN.name()) // ROLE_ADMIN
                                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
                                .build();

                UserDetails tomUser = User.builder()
                                .username("tom")
                                .password(passwordEncoder.encode("tom"))
                                // .roles(ApplicationUserRole.ADMINTRAINEE.name()) // ROLE_ADMINTRAINEE
                                .authorities(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities())
                                .build();

                return new InMemoryUserDetailsManager(
                                jacobUser,
                                lindaUser,
                                tomUser);
        }

}
