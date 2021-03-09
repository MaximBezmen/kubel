package com.kubel.config;

import com.kubel.security.AuthenticationFailureHandler;
import com.kubel.security.CustomUserDetailsService;
import com.kubel.security.JWTAuthenticationFilter;
import com.kubel.security.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.kubel.security.SecurityConstants.CONFIRM_URL;
import static com.kubel.security.SecurityConstants.SING_UP_URL;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public JWTAuthenticationFilter tokenAuthenticationFilter() throws Exception {
//        return new JWTAuthenticationFilter(authenticationManager(), customUserDetailsService);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .formLogin()
                .disable()
                .logout()
                .disable()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, CONFIRM_URL,"/user/resetPassword*",
                        "/user/changePassword*").permitAll()
                .antMatchers(HttpMethod.POST, SING_UP_URL, "/user/savePassword").permitAll()
                .antMatchers(
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/api-docs/**")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/users/*",
                        "users/*/ads",
                        "users/*/ads/*/messages",
                        "/ads").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/users").hasAnyAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .userDetailsService(customUserDetailsService)
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilterBefore(new JWTAuthorizationFilter(customUserDetailsService), UsernamePasswordAuthenticationFilter.class)
                //this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //http.addFilterBefore(new JWTAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}
