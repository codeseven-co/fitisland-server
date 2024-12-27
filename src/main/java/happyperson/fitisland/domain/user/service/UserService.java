package happyperson.fitisland.domain.user.service;

import happyperson.fitisland.domain.user.dto.response.UserResponse;
import happyperson.fitisland.domain.user.entity.User;
import happyperson.fitisland.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponse.AccountDto findAccount(Long id) {
        return new UserResponse.AccountDto(userRepository.getReferenceById(id));
    }

    @Transactional(readOnly = true)
    public UserResponse.ProfileDto findProfile(Long id) {
        User user = userRepository.getReferenceById(id);
        return new UserResponse.ProfileDto(user.getProfile());
    }
}
