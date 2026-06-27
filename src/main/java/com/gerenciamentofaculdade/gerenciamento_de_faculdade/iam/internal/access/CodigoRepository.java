package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.access;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodigoRepository extends JpaRepository<Codigo, Long> {
    Optional<Codigo> findByCodigo(String codigo);
}
