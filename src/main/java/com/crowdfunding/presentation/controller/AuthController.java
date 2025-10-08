package com.crowdfunding.presentation.controller;

import com.crowdfunding.application.dto.AuthResponseDTO;
import com.crowdfunding.application.dto.LoginDTO;
import com.crowdfunding.application.dto.RegisterDTO;
import com.crowdfunding.application.usecase.AuthenticateUserUseCase;
import com.crowdfunding.application.usecase.RegisterUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final RegisterUserUseCase registerUserUseCase;

    public AuthController(AuthenticateUserUseCase authenticateUserUseCase,
                          RegisterUserUseCase registerUserUseCase) {
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO dto) {
        AuthResponseDTO response = authenticateUserUseCase.execute(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterDTO dto) {
        AuthResponseDTO response = registerUserUseCase.execute(dto);
        return ResponseEntity.ok(response);
    }
}
