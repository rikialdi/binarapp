package com.binar.binar.repository.oauth;

import com.binar.binar.entity.oauth.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Query("FROM User u WHERE LOWER(u.username) = LOWER(?1)")
    User findOneByUsername(String username);

    @Query("FROM User u WHERE u.otp = ?1")
    User findOneByOTP(String otp);
}
