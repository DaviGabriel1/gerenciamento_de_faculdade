package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.access;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.catalog.models.Curso;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.email.EmailService;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.email.EmailTemplateName;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.*;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity.RoleRepository;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity.Usuario;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity.UsuarioMapper;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity.UsuarioRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

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
    private final UsuarioMapper usuarioMapper;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    @Override
    public void register(RegisterRequest request) throws MessagingException {
        Usuario usuario = usuarioMapper.toUsuario(request);
        usuarioRepository.save(usuario);
        sendValidationEmail(usuario);
    }

    private void sendValidationEmail(Usuario usuario) throws MessagingException {
        var newToken = generateAndSaveActivationCode(usuario);
        emailService.sendEmail(
                usuario.getEmail(),
                usuario.getNomeCompleto(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Ative sua conta"
        );
    }
    private String generateAndSaveActivationCode(Usuario usuario) {
        String generateToken = generateActivationCode(6);
        var codigo = Codigo.builder()
                .codigo(generateToken)
                .criadoEm(LocalDateTime.now())
                .ExpiraEm(LocalDateTime.now().plusMinutes(15))
                .usuario(usuario)
                .build();
        codigoRepository.save(codigo);
        return codigo.getCodigo();
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
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
