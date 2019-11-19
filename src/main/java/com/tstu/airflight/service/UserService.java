package com.tstu.airflight.service;

import com.tstu.airflight.model.User;

public interface UserService {
    User register(User user);
    User findByUsername(String username);
}
