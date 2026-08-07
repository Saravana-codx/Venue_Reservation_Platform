package com.hall.VenueMgmt.auth.service;

import com.hall.VenueMgmt.auth.dto.AuthResponse;
import com.hall.VenueMgmt.auth.dto.LoginRequest;
import com.hall.VenueMgmt.auth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}