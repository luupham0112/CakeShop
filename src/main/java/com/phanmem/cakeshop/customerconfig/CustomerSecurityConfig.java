package com.phanmem.cakeshop.customerconfig;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class CustomerSecurityConfig extends WebSecurityConfigurerAdapter {

    //    @Autowired
//    CustomerDetailServiceImpl customerDetailService;
    @Bean
    public UserDetailsService detailsService(){
        return new CustomerServiceConfig();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setUserDetailsService(detailsService());
        provider.setPasswordEncoder(cryptPasswordEncoder());
        return  provider;
    }

    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/*").permitAll()
                .antMatchers("/shop/*")
                .hasAuthority("CUSTOMER")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/do-login")
                .defaultSuccessUrl("/shop")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }
    @Bean
    public BCryptPasswordEncoder cryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws  Exception {
        auth.authenticationProvider(authenticationProvider());
    }
//    @Override
//    public void configure(WebSecurity web) throws  Exception{
//        web.ignoring().antMatchers("/resources/**","/static/**","/imageProduct/**","/admin/**","/user/**","/css/**","/js/**");
//    }
}
