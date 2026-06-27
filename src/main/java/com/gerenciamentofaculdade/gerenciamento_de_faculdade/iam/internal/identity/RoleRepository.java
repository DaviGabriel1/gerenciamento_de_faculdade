package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Usuario> findByNomeRole(String nomeRole);
    Optional<Role> getRoleByNomeRole(EnumRole nomeRole);
}
