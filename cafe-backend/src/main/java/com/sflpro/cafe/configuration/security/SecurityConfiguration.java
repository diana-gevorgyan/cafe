package com.sflpro.cafe.configuration.security;

import com.sflpro.cafe.configuration.ApplicationProperties;
import com.sflpro.cafe.configuration.security.filter.AuthenticationFilter;
import com.sflpro.cafe.configuration.security.filter.AuthorizationFilter;
import com.sflpro.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtService jwtService;
    private final UserService userService;
    private final ApplicationProperties applicationProperties;

    @Autowired
    public SecurityConfiguration(
            final JwtService jwtService,
            final UserService userService,
            final ApplicationProperties applicationProperties
    ) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.applicationProperties = applicationProperties;
    }

    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        final String managerAuthority = "MANAGER";
        final String waiterAuthority = "WAITER";

        httpSecurity
                .cors().configurationSource(corsConfig())
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/users/register").hasAuthority(managerAuthority)
                .antMatchers(HttpMethod.GET, "/users/waiters").hasAuthority(managerAuthority)
                .antMatchers(HttpMethod.GET, "/tables/all").hasAuthority(managerAuthority)
                .antMatchers(HttpMethod.POST, "/tables/create").hasAuthority(managerAuthority)
                .antMatchers(HttpMethod.PUT, "/tables/*/assign/*").hasAuthority(managerAuthority)
                .antMatchers(HttpMethod.GET, "/tables/waiter/**").hasAnyAuthority(managerAuthority, waiterAuthority)
                .antMatchers(HttpMethod.GET, "/products/all").hasAnyAuthority(managerAuthority, waiterAuthority)
                .antMatchers(HttpMethod.POST, "/products/create").hasAuthority(managerAuthority)
                .antMatchers(HttpMethod.GET, "/users/all").hasAuthority(managerAuthority)
                .anyRequest().denyAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager(), jwtService, applicationProperties))
                .addFilter(new AuthorizationFilter(authenticationManager(), jwtService, applicationProperties));
    }

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    private CorsConfigurationSource corsConfig() {
        final var source = new UrlBasedCorsConfigurationSource();
        final var corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of(CorsConfiguration.ALL));
        corsConfiguration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        corsConfiguration.setAllowCredentials(Boolean.TRUE);
        corsConfiguration.setExposedHeaders(List.of("Content-Disposition", "Location", "Authorization", "jwt-auth-token"));
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "jwt-auth-token", "Access-Control-Allow-Origin", "Content-Type", "Cache-Control", "X-Requested-With"));
        source.registerCorsConfiguration(applicationProperties.getSecurity().getCorsAllowAllPattern(), corsConfiguration);
        return source;
    }
}
