package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    @Mapping(source = "senha", target = "senhaHash")
    @Mapping(target = "habilitado", constant = "false")
    @Mapping(target = "contaTrancada", constant = "false")
    Usuario toUsuario(RegisterRequest registerRequest);
}
