package by.andd3dfx.iot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().httpBasic();
  }

  /*
   * For security reasons - we use BCryptPasswordEncoder instead of usual NoOpPasswordEncoder
   * So here are hashes for user/user2/admin passwords: userPass/user2Pass/adminPass
   *
   * To determine hash for your password - print value of next expression:
   * (new BCryptPasswordEncoder().encode("your password"))
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        .withUser("user").password("$2a$10$TqszIMxBXoAud1woVs7Izeksy6w2A.I0waaXCM7Vx4b44XUUwPIdO").roles("USER")
        .and()
        .withUser("user2").password("$2a$10$gLffHARhrn373Cv7kRQO4edgB/MkYGoM8wLpxQz43/238TYOkzUSC").roles("USER")
        .and()
        .withUser("admin").password("$2a$10$vIcDANd2gqVUYXNx3Ggg2efgQh.i1m3zXJIw/6KdHAzvztaaYTdnW").roles("ADMIN");
  }
}
