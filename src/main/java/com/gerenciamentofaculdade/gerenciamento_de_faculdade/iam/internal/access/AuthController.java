package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.access;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.AuthService;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.AuthenticationRequest;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.AuthenticationResponse;
import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.RegisterRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "authentication")
public class AuthController {
    @Qualifier("authServiceImpl")
    private final AuthService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegisterRequest request
    ) throws MessagingException {
        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("authenticate")
    public ResponseEntity<@NonNull AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.autheticate(request));
    }

    @GetMapping("activate-account")
    public void confirm(
            @RequestParam String token
    ) throws MessagingException {
        authenticationService.activateAccount(token);
    }

    @GetMapping("refresh-token")
    public ResponseEntity<@NonNull AuthenticationResponse> refreshToken(
            @RequestParam String refreshToken
    ) {
        return ResponseEntity.ok(authenticationService.refresh(refreshToken));
    }

    @DeleteMapping("logout")
    public ResponseEntity<?> logout(
            @RequestParam String refreshToken
    ) {
        this.authenticationService.logout(refreshToken);
        return ResponseEntity.ok().build();
    }
}
