package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.access;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.catalog.models.Curso;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.email.EmailService;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.email.EmailTemplateName;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.*;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity.*;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        Optional<Role> roleOptional = roleRepository.getRoleByNomeRole(EnumRole.PADRAO);
        if (roleOptional.isPresent()) {
            usuario.setRole(roleOptional.get());
        }
        else {
            usuario.setRole(
                    roleRepository.save(
                            Role
                                    .builder()
                                    .nomeRole(EnumRole.PADRAO)
                                    .build()
                    )
            );
        }
        usuario.setSenhaHash(passwordEncoder.encode(usuario.getSenhaHash()));
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
    public @Nullable AuthenticationResponse autheticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSenha()
                )
        );
        var claims = new HashMap<String, Object>();
        var usuario = ((Usuario) auth.getPrincipal());
        assert usuario != null;
        if(usuario.getRole() == null) {
            throw new NullPointerException("Usuário não tem papel definido, aguarde a confirmação de um coordenador");
        }
        claims.put("fullName",usuario.fullName());
        var jwtToken = jwtService.generateToken(claims,usuario);
        String refreshToken = generateRefreshToken(usuario);
        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private String generateRefreshToken(Usuario usuario) {
        String uuid = UUID.randomUUID().toString();
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .uuid(uuid)
                        .criadoEm(LocalDateTime.now())
                        .expiraEm(LocalDateTime.now().plusMinutes(refreshTokenExpiration))
                        .usuario(usuario)
                        .build()
        );
        return uuid;
    }

    @Override
    public void activateAccount(String codigo) throws MessagingException {
        Codigo codigoSalvado = codigoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("código invalido"));
        if(LocalDateTime.now().isAfter(codigoSalvado.getExpiraEm())) {
            sendValidationEmail(codigoSalvado.getUsuario());
            throw new RuntimeException("o código de ativação expirou, um novo código foi enviado");
        }
        var usuario = usuarioRepository.findById(codigoSalvado.getUsuario().getId())
                .orElseThrow(() -> new UsernameNotFoundException("usuário não encontrado"));
        usuario.setHabilitado(true);
        usuarioRepository.save(usuario);
        codigoSalvado.setValidadoEm(LocalDateTime.now());
        codigoRepository.save(codigoSalvado);
    }

    @Override
    public AuthenticationResponse refresh(String uuid) {
        RefreshToken refreshToken = refreshTokenRepository.findRefreshTokenByUuid(uuid)
                .orElseThrow(() -> new InvalidParameterException("refresh token não encontrado"));
        if(refreshToken.getExpiraEm().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Refresh Token invalido, entre novamente");
        }
        var claims = new HashMap<String, Object>();
        Usuario user = refreshToken.getUsuario();
        claims.put("fullName",user.fullName());
        var jwtToken = jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken.getUuid())
                .build();
    }

    @Override
    @Transactional
    public void logout(String refreshToken) {
        refreshTokenRepository.deleteByUuid(refreshToken);
    }
}
