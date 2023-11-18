package br.com.dropping.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Produtos")
@Entity(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brand;
    private String name;
    private String size;
    private String color;
    private String gender;

    public Produto(DadosCadastroSneaker dados) {
        this.brand = dados.brand();
        this.name = dados.brand();
        this.size = dados.size();
        this.color = dados.color();
        this.gender = dados.gender();
    }

    public void updateData(DadosAtualizacaoSneaker dados) {
        if(dados.brand() != null){
            this.brand = dados.brand();
        }
        if(dados.name() != null){
            this.name = dados.name();
        }
        if(dados.size() != null){
            this.size = dados.size();
        }
        if(dados.color() != null){
            this.color = dados.color();
        }
        if(dados.gender() != null){
            this.gender = dados.gender();
        }
    }
}














