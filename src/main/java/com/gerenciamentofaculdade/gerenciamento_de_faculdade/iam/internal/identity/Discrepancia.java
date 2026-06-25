package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity;

public enum Discrepancia {
    COORDENADOR("coordenador"), PROFESSOR("professor"), ALUNO("aluno");

    private final String nome;

    public String getNome() {
        return nome;
    }

    Discrepancia(String nome) {
        this.nome = nome;
    }
}
