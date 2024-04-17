package m2m_phase2.clothing.clothing.security;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.List;


@Configuration
@EnableWebSecurity
public class OAuth2LoginSecurityConfigurer   {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(auth -> {
                   auth.requestMatchers("/giohang").authenticated();
                   auth.anyRequest().permitAll();
                })
                .formLogin(formlogin -> formlogin
                    .loginPage("/login")
                    .defaultSuccessUrl("/home")
                    .permitAll()
                ).logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .permitAll()
                )
                .oauth2Login(
                    Customizer.withDefaults()
                )

                .build();

//        return http
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/custom-login").authenticated();
//                    auth.anyRequest().permitAll();
//                })
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login") // Set your login page URL
//                        .usernameParameter("username") // Set username parameter name (default is username)
//                        .passwordParameter("password") // Set password parameter name (default is password)
//                        .defaultSuccessUrl("/giohang") // Redirect to /giohang after successful login
//                        .permitAll()) // Allow everyone to access the login page
//                .oauth2Login(
//                            Customizer.withDefaults()
//                )
//                .build();
    }

}
