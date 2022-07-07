package com.example.secureREST;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	 
//	  @Bean
//	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	    // @formatter:off
//	    http
//	        .authorizeHttpRequests((authz) -> authz
//	            .anyRequest().authenticated()
//	        )
//	        .oauth2ResourceServer().jwt();
//	    // @formatter:on
//	 
//	    return http.build();
//	  }
//	}
	
  @Override
  protected void configure(HttpSecurity http) throws Exception {
  	JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();
  	jwtAuthConverter.setJwtGrantedAuthoritiesConverter(new RoleConverter());
  	
      http
          .csrf().disable()
          .authorizeRequests()
              .antMatchers("/graphql").permitAll()
//              .antMatchers("/vendor/**").permitAll()
//              .antMatchers("/graphiql").permitAll()
              .anyRequest().authenticated()
          .and()
          .oauth2ResourceServer()
          .jwt()
          .jwtAuthenticationConverter(jwtAuthConverter);
  }
}
