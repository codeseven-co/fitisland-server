package happyperson.fitisland.global.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Profile("local") // local 프로파일
@Configuration
@EnableWebSecurity
public class LocalSecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(auth ->
//                auth.requestMatchers("/**").permitAll()
//            );
//        http
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
//                .anyRequest().authenticated()
//            )
//            .csrf(csrf -> csrf
//                .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
//            )
//            .headers(headers -> headers
//                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
//            );
//
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)  // 개발 편의를 위해 비활성화
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                .requestMatchers("/api/**").permitAll()  // 공개 API
                .anyRequest().authenticated()
            )
            .headers(headers -> headers
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
            )
            .formLogin(login -> login.permitAll());  // 폼 로그인 활성화

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails testUser = User.withDefaultPasswordEncoder()
            .username("test@test.com")
            .password("test123")
            .roles("USER")
            .build();

        // ADMIN 계정도 필요하다면
        UserDetails adminUser = User.withDefaultPasswordEncoder()
            .username("admin@test.com")
            .password("admin123")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(testUser, adminUser);
    }
}
