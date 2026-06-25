package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.access;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
