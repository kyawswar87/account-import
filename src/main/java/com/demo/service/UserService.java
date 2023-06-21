package com.demo.service;

import com.demo.domain.User;
import com.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public Optional<User> findOneByUserName(String userName) {
        log.info("find by user name {}", userName);
        try {
            return userRepository.findByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
