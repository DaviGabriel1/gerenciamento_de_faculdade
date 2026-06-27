package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity.Usuario;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    void register(RegisterRequest request) throws MessagingException;
    AuthenticationResponse autheticate(AuthenticationRequest request);
    void activateAccount(String codigo) throws MessagingException;
    AuthenticationResponse refresh(String uuid);
    void logout(String refreshToken);


}
