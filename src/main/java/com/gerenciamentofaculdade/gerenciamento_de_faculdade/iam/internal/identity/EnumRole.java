package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity;

public enum EnumRole {
    COORDENADOR("coordenador"),
    PROFESSOR("professor"),
    ALUNO("aluno"),
    PADRAO("padrao");
    private final String role;
    EnumRole(String role) {
        this.role = role;
    }
    public String getNomeRole() {
        return role;
    }
}
