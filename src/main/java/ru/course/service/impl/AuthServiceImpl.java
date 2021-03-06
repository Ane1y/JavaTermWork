package ru.course.service.impl;

import ru.course.controller.AuthRequest;
import ru.course.entity.User;
import ru.course.repository.jpa.UserRepository;
import ru.course.security.jwt.JwtTokenProvider;
import ru.course.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<Object, Object> signIn(AuthRequest authRequest) {
        String userName = authRequest.getUserName();
        String password = authRequest.getPassword();
        List<String> roles = userRepository.findUserByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!")).getRoles();

        String token = jwtTokenProvider.createToken(userName, password, roles);

        Map<Object, Object> model = new HashMap<>();
        model.put("userName", userName);
        model.put("token", token);
        model.put("roles", roles);

        return model;
    }

    @Override
    public Map<Object, Object> signUp(AuthRequest authRequest) {
        String userName = authRequest.getUserName();
        String password = authRequest.getPassword();
        if (userRepository.findUserByUserName(userName).isPresent()) {
            return null;
        }

        List<String> roles = Collections.singletonList("ROLE_USER");
        User user = new User(userName, passwordEncoder.encode(password), roles);
        userRepository.save(user);

        String token = jwtTokenProvider.createToken(userName, password, roles);

        Map<Object, Object> model = new HashMap<>();
        model.put("userName", userName);
        model.put("token", token);
        model.put("roles", roles);

        return model;
    }
}
