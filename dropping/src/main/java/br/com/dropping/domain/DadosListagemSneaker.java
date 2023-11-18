package br.com.dropping.domain;

public record DadosListagemSneaker(Integer id, String brand, String name,String size, String color, String gender) {

    public DadosListagemSneaker(Produto produto) {
        this(produto.getId(), produto.getBrand(), produto.getName(), produto.getSize(), produto.getColor(), produto.getGender());
    }
}
