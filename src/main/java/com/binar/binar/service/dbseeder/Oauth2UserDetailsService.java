package com.binar.binar.service.dbseeder;

import com.binar.binar.entity.oauth.User;
import com.binar.binar.repository.oauth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//@EnableCaching
//public class Oauth2UserDetailsService implements UserDetailsService, CacheObjectInterface {
public class Oauth2UserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

//    @Cacheable(value = "oauth_username", unless = "#result == null")
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(s);
        if (null == user) {
            throw new UsernameNotFoundException(String.format("Username %s is not found", s));
        }

        return user;
    }

    @CacheEvict("oauth_username")
    public void clearCache(String s) {
    }

//    @CacheEvict(value = "oauth_username", allEntries = true)
//    @Override
//    public void clearCache() {
//
//    }
}
