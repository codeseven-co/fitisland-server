package happyperson.fitisland.global.security.oauth2;

import happyperson.fitisland.domain.user.dto.response.GoogleResponse;
import happyperson.fitisland.domain.user.dto.response.NaverResponse;
import happyperson.fitisland.domain.user.dto.response.OAuth2Response;
import happyperson.fitisland.domain.user.entity.Social;
import happyperson.fitisland.domain.user.entity.User;
import happyperson.fitisland.domain.user.repository.SocialRepository;
import happyperson.fitisland.domain.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final SocialRepository socialRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info(String.valueOf(oAuth2User));

        //요청 서비스 식별 ex)google, naver
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response;

        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            oAuth2Response = null;
        }

        Optional<Social> existSocial = socialRepository.findByProviderAndProviderId(oAuth2Response.getProvider(), oAuth2Response.getProviderId());
        // 소셜로그인 이메일과 동일한 이메일로 조회 후 없을시 일반 회원가입시킨다.
        User user = userRepository.findByEmail(oAuth2Response.getEmail())
                .orElseGet(() ->
                        User.builder()
                                .email(oAuth2Response.getEmail())
                                .password(passwordEncoder.encode(oAuth2Response.getProviderId()))
                                .name(oAuth2Response.getName())
                                .role("USER")
                                .createdAt(LocalDateTime.now())
                                .lastLoginAt(LocalDateTime.now())
                                .build()
                );

        userRepository.save(user);

        // 소셜로그인 정보가 없다면 해당 정보를 등록한다.
        if (existSocial.isEmpty()) {
            socialRepository.save(
                    Social.builder()
                            .provider(oAuth2Response.getProvider())
                            .providerId(oAuth2Response.getProviderId())
                            .user(user)
                            .build()
            );
        }

        return new CustomOAuth2User(user);
    }
}
