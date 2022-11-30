package lt.codeacademy.services;

import lt.codeacademy.entities.User;
import lt.codeacademy.repositiries.UserCudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

 
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UserCudRepository userCudRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userCudRepository.getUserByUsername(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
 
}
