package happyperson.fitisland.global.security.config;

import happyperson.fitisland.global.security.oauth2.CustomSuccessHandler;
import happyperson.fitisland.domain.oauthjwt.service.CustomOAuth2UserService;
import happyperson.fitisland.global.security.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Profile("deploy") // deploy 프로파일에서만 활성화
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {

                // cors setting
                CorsConfiguration configuration = new CorsConfiguration();

                configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://fitisland-client.vercel.app"));
                configuration.setAllowedMethods(Collections.singletonList("*"));
                configuration.setAllowCredentials(true);
                configuration.setAllowedHeaders(Collections.singletonList("*"));
                configuration.setMaxAge(3600L);
                configuration.setExposedHeaders(Collections.singletonList("Set-Cookie"));
                configuration.setExposedHeaders(Collections.singletonList("Authorization"));
                return configuration;
            }))
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
//            .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
//            .oauth2Login(oauth2 -> oauth2
//                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
//                    .userService(customOAuth2UserService))
//                .successHandler(customSuccessHandler)
//            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated())
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//            .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
//
//                @Override
//                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//
//                    CorsConfiguration configuration = new CorsConfiguration();
//
//                    configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); //프론트주소
//                    configuration.setAllowedMethods(Collections.singletonList("*"));
//                    configuration.setAllowCredentials(true);
//                    configuration.setAllowedHeaders(Collections.singletonList("*"));
//                    configuration.setMaxAge(3600L);
//
//                    configuration.setExposedHeaders(Collections.singletonList("Set-Cookie"));
//                    configuration.setExposedHeaders(Collections.singletonList("Authorization"));
//
//                    return configuration;
//                }
//            }));
//
//        //csrf disable
//        http
//                .csrf(AbstractHttpConfigurer::disable);
//
//        //From 로그인 방식 disable
//        http
//                .formLogin((auth) -> auth.disable());
//
//        //HTTP Basic 인증 방식 disable
//        http
//                .httpBasic((auth) -> auth.disable());
//
//        //JWTFilter 추가
//        http
//                .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
//
//        //oauth2
//        http
//                .oauth2Login((oauth2) -> oauth2
//                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
//                                .userService(customOAuth2UserService))
//                        .successHandler(customSuccessHandler)
//                );
//
//
//        //경로별 인가 작업
//        http
//                .authorizeHttpRequests((auth) -> auth
//                    .requestMatchers("/h2-console/**").permitAll() // H2 콘솔 경로 추가
//                    .requestMatchers("/").permitAll()
//                    .anyRequest().authenticated()); //인증 받은 사람만 저근 가능
//
//        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
//
//        //세션 설정 : STATELESS
//        http
//                .sessionManagement((session) -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();
//    }
}