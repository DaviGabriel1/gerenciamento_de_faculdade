package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.access;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findRefreshTokenByUuid(String uuid);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM refresh_token WHERE deletado_em IS NOT NULL AND deleted_at <= :dataLimite", nativeQuery = true)
    void deleteByUuid(String uuid);
}
