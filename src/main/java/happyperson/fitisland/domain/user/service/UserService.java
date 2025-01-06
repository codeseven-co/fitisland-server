package happyperson.fitisland.domain.user.service;

import happyperson.fitisland.domain.user.dto.request.JoinRequest;
import happyperson.fitisland.domain.user.dto.response.UserResponse;
import happyperson.fitisland.domain.user.entity.User;
import happyperson.fitisland.domain.user.exception.EmailAlreadyExistException;
import happyperson.fitisland.domain.user.exception.NicknameAlreadyExistException;
import happyperson.fitisland.domain.user.exception.UserNotFoundException;
import happyperson.fitisland.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(JoinRequest request) {
        // validate
        checkDuplicateEmail(request.getEmail());
        checkDuplicateNickname(request.getNickname());


        userRepository.save(request.toEntity(passwordEncoder.encode(request.getPassword())));
    }

    private void checkDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistException();
        }
    }

    private void checkDuplicateNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new NicknameAlreadyExistException();
        }
    }

    @Transactional(readOnly = true)
    public UserResponse.AccountDto findAccount(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return new UserResponse.AccountDto(user);
    }

    @Transactional(readOnly = true)
    public UserResponse.ProfileDto findProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return new UserResponse.ProfileDto(user.getProfile());
    }
}
