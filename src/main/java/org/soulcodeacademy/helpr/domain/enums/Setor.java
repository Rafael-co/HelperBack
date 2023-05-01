package org.soulcodeacademy.helpr.domain.enums;

public enum Setor {
    MARKETING("ROLE_MARKETING"),
    MANUTENCAO("ROLE_MANUTENCAO"),
    RH("ROLE_RH"),
    DESENVOLVIMENTO("ROLE_DESENVOLVIMENTO"),
    SUSTENTACAO("ROLE_SUSTENTACAO");

    private String descricao;

    Setor(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}








