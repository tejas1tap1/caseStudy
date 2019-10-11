package com.beehyv.shoppingcart.config;

import com.beehyv.shoppingcart.entity.UserCredentials;
import com.beehyv.shoppingcart.repo.UserCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialsRepo userCredentialsRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredentials user = userCredentialsRepo.findByEmail(email);
        if(user==null)
            throw  new UsernameNotFoundException("User 404");

        return new UserPrinciple(user);
    }
}

