package happyperson.fitisland.global.security.jwt;

import happyperson.fitisland.domain.user.entity.User;
import happyperson.fitisland.domain.user.exception.UserNotFoundException;
import happyperson.fitisland.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("===== loadUserByUsername =====");
        User user = userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);
        return new UserDetailsImpl(user);
    }
}
