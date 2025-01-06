package happyperson.fitisland.global.security.jwt;

import happyperson.fitisland.domain.user.entity.User;
import happyperson.fitisland.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserRepository userRepository,
                                  JWTUtil jwtUtil
    ) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        log.info("===== do AuthorizationFilter Internal =====");

        String accessToken = null;
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            chain.doFilter(request, response);
            return;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Authorization")) {
                accessToken = cookie.getValue();
            }
        }

        if (accessToken == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String email = jwtUtil.getEmail(accessToken);
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));

            // 3. 시큐리티 내 권한 처리를 위해 'UsernamePasswordAuthenticationToken'을 만들고, Authentication 객체를 만든다.

            UserDetailsImpl userDetails = new UserDetailsImpl(user);
            Authentication auth =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            // 4. Authentication 객체를 시큐리티 세션에 저장한다.
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        chain.doFilter(request, response);
    }
}
