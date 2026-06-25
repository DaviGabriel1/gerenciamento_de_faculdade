package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("usuário com email %s não encontrado", userEmail)));
    }
}
