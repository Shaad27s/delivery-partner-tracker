package shaadProject.example.delivery.partner.app.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import shaadProject.example.delivery.partner.app.JTWconfigruation.jtwFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class security {

    @Autowired
    private UserDetailsService myUserDetail;

    @Autowired
    private jtwFilter jtwFilter;

  @Bean
  public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
     http.csrf(customizer->customizer.disable());
     http.authorizeHttpRequests(request-> request
             .requestMatchers("/auth/register","/auth/login")
             .permitAll()
             .requestMatchers(
                     "/auth/register",
                     "/auth/login",
                     "/v3/api-docs/**",
                     "/swagger-ui/**",
                     "/swagger-ui.html",
                     "/swagger-resources/**",
                     "/webjars/**"
             ).permitAll()
             .anyRequest()
             .authenticated());
     http.httpBasic(Customizer.withDefaults());
     http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
     http.addFilterBefore(jtwFilter, UsernamePasswordAuthenticationFilter.class);
     return http.build();
 }

    @Bean
    public AuthenticationProvider authenticationProvider(){
     DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
     provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
     provider.setUserDetailsService(myUserDetail);
     return  provider;
 }
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
     return configuration.getAuthenticationManager();
  }




}
