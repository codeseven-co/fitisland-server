package happyperson.fitisland.global.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import happyperson.fitisland.domain.user.dto.request.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        log.info("UsernamePasswordAuthenticationFilter.attemptAuthentication");

        try {

            // 1. request에서 username과 password를 Object로 받는다.
            LoginRequest loginReq = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginRequest.class);

            // 2. AuthenticationManager는 사용자 아이디 / 비밀번호가 유효한 인증인지 확인한다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    loginReq.getEmail(),
                    loginReq.getPassword()
            );

            // 3. authenticate()메서드 내의 Authentication이 유효한지 확인하고, Authentication 객체를 리턴한다.
            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        log.info("===== successful Authentication =====");
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String username = userDetails.getUsername();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        String accessToken = jwtUtil.createJwt(username, role, 60 * 60 * 1000L); // 1시간
        response.addCookie(createCookie("Authorization", accessToken));
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*60);
        //cookie.setSecure(true); //Https 통신에서만 사용 가능하도록
        cookie.setPath("/"); //쿠키 위치 전역
        cookie.setHttpOnly(true);

        return cookie;
    }

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
                                              AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
