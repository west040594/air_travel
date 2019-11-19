package com.tstu.airflight.service.impl;

import com.tstu.airflight.model.Client;
import com.tstu.airflight.model.User;
import com.tstu.airflight.model.enums.UserRole;
import com.tstu.airflight.model.enums.UserStatus;
import com.tstu.airflight.repository.ClientRepository;
import com.tstu.airflight.repository.UserRepository;
import com.tstu.airflight.service.ClientService;
import com.tstu.airflight.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.neo4j.storageengine.api.EntityType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Client client = Client.builder()
                .balance(new BigDecimal(100000))
                .user(user).build();
        Client savedClient = clientService.create(client);
        return savedClient.getUser();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь с логином: " + username + " не найден"));
    }
}
