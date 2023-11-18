package br.com.dropping.domain;

public record DadosDetalhamentoSneaker(Integer id, String brand, String name, String size, String color, String gender) {
    public DadosDetalhamentoSneaker(Produto produto) {
        this(produto.getId(), produto.getBrand(), produto.getName(), produto.getSize(), produto.getColor(), produto.getGender());
    }
}
