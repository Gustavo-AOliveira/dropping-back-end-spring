package br.com.dropping.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DadosCadastroSneaker(
        @NotBlank(message = "A marca não pode estar em branco")
        String brand,
        @NotBlank(message = "O nome não pode estar em branco")
        String name,
        @Size(min = 1, message = "O tamanho não pode estar em branco")
        @Pattern(regexp = "\\d+", message = "O tamanho deve conter apenas números")
        String size,
        @NotBlank(message = "A cor não pode estar em branco")
        String color,
        @NotBlank(message = "O gênero não pode estar em branco")
        String gender
) {

}
