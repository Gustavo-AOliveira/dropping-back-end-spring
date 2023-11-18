package br.com.dropping.domain;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoSneaker(
        String brand,
        String name,
        String size,
        String color,
        String gender
) {


}

