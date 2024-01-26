package com.springboot.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }



    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Authentication Manager uses userDetailsService to get the details from db, also uses password encoder for
    // encryption and decryption
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()
                .disable()
                .authorizeHttpRequests((authorize) ->
                        //The below line allows all HTTP methods to be accessed by all roles
                        //authorize.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());

                        //Below code only allows GET method to be accessed by all.
                        authorize.
                        requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                                //whitelisted login auth api
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated()).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    //In-memory db authentication
    //To fetch specific usernames with roles and password
   /* @Bean
    public UserDetailsService userDetailsService(){
        UserDetails ayush = User.builder()
                .username("ayush")
                .password(passwordEncoder().encode("Ayush@12345"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(ayush,admin);
    }*/
}
