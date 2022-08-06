package com.fundamentos.spring.fundamentos.service;

import com.fundamentos.spring.fundamentos.entity.User;
import com.fundamentos.spring.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveTransactional (List<User> userList) {
        userList.stream()
                .peek(user -> LOG.info("Usuario insertado: " + user))
                .forEach(user -> userRepository.save(user));
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }
}
