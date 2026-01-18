package org.uteq.backend.auth.service;

import org.uteq.backend.auth.dto.LoginRequest;
import org.uteq.backend.auth.dto.LoginResponse;

public interface IAuthService {
    public LoginResponse login(LoginRequest loginRequest);
}
