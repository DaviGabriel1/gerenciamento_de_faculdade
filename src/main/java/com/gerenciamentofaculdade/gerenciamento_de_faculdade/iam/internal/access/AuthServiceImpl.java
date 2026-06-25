package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.access;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.email.EmailService;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.*;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity.RoleRepository;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CodigoRepository codigoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    @Override
    public void register(RegisterRequest request) {

    }

    @Override
    public AuthenticationResponse autheticate(AuthenticationRequest request) {
        return null;
    }

    @Override
    public void activateAccount(String codigo) {

    }

    @Override
    public AuthenticationResponse refresh(String uuid) {
        return null;
    }

    @Override
    public void logout(String refreshToken) {

    }
}
