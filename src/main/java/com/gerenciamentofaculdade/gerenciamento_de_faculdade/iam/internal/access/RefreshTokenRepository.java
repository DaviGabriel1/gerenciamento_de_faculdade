package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.access;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findRefreshTokenByUuid(String uuid);
    void deleteByUuid(String refreshToken);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM refresh_token WHERE deletado_em IS NOT NULL AND deletado_em <= :dataLimite", nativeQuery = true)
    int hardDeleteExpiredRefreshTokens(@Param("dataLimite") LocalDateTime dataLimite);
}
