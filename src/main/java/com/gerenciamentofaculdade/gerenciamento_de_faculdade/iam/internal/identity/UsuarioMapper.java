package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    Usuario toUsuario(RegisterRequest registerRequest);
}
