package happyperson.fitisland.domain.oauthjwt.service;

import happyperson.fitisland.domain.oauthjwt.dto.CustomOAuth2User;
import happyperson.fitisland.domain.oauthjwt.dto.GoogleResponse;
import happyperson.fitisland.domain.oauthjwt.dto.NaverResponse;
import happyperson.fitisland.domain.oauthjwt.dto.OAuth2Response;
import happyperson.fitisland.domain.oauthjwt.dto.UserDTO;
import happyperson.fitisland.domain.oauthjwt.entity.UserEntity;
import happyperson.fitisland.domain.oauthjwt.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info(String.valueOf(oAuth2User));

        //요청 서비스 식별 ex)google, naver
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response;

        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {
            oAuth2Response = null;
        }

        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();

        //DB에서 유저 정보 가져오기
        Optional<UserEntity> existData = userRepository.findByUsername(username);

        UserEntity userEntity = existData.orElseGet(() -> {
            // 사용자가 존재하지 않으면 새로운 UserEntity 생성
            UserEntity newUserEntity = new UserEntity();
            newUserEntity.setUsername(username);
            newUserEntity.setEmail(oAuth2Response.getEmail());
            newUserEntity.setName(oAuth2Response.getName());
            newUserEntity.setRole("ROLE_USER");
            return newUserEntity;
        });

        // 사용자가 존재하면 정보 업데이트
        if (existData.isPresent()) {
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setName(oAuth2Response.getName());
        }

        // UserEntity 저장
        userRepository.save(userEntity);

        // UserDTO 생성 및 CustomOAuth2User 반환
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setName(userEntity.getName());
        userDTO.setRole(userEntity.getRole());

        return new CustomOAuth2User(userDTO);
    }
}
