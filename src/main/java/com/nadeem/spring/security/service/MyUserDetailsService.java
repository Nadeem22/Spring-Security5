package com.nadeem.spring.security.service;

        import com.nadeem.spring.security.model.MyUserDetails;
        import com.nadeem.spring.security.model.User;
        import com.nadeem.spring.security.repository.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Service;

        import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In MyUserDetailsService");
       Optional<User> user= userRepository.findByUsername(username);
        System.out.println("In MyUserDetailsService-return");
       user.orElseThrow(()-> new UsernameNotFoundException("Not Found: " +username));
      return user.map(MyUserDetails::new).get();
    }
}
