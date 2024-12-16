package vn.dodientu.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.dodientu.dto.UserDetailsImpl;
import vn.dodientu.model.User;
import vn.dodientu.repository.UserRepository;

@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found."));

        return new UserDetailsImpl(user);
    }
}
