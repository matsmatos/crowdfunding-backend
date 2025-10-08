package com.crowdfunding.application.usecase;

import com.crowdfunding.application.dto.AuthResponseDTO;
import com.crowdfunding.application.dto.LoginDTO;
import com.crowdfunding.domain.entity.User;
import com.crowdfunding.domain.exception.DomainException;
import com.crowdfunding.domain.repository.UserRepository;
import com.crowdfunding.infrastructure.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticateUserUseCase(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder,
                                   JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional(readOnly = true)
    public AuthResponseDTO execute(LoginDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new DomainException("Invalid credentials"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new DomainException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponseDTO(
                token,
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole().name()
        );
    }
}
