package br.com.dropping.controller;

import br.com.dropping.domain.*;
import br.com.dropping.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity saveSneaker(@RequestBody @Valid DadosCadastroSneaker dados, UriComponentsBuilder uriBuilder){
        var produto = new Produto(dados);
            repository.save(produto);
        var uri = uriBuilder.path("api/products/{id}").buildAndExpand(produto.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhamentoSneaker(produto));
    }
    @GetMapping
    public ResponseEntity<List<DadosListagemSneaker>> getAll(){
        List<DadosListagemSneaker> productsList = repository.findAll().stream().map(DadosListagemSneaker::new).toList();
            return ResponseEntity.ok(productsList);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateSneaker(@PathVariable Integer id, @RequestBody @Valid DadosAtualizacaoSneaker dados){
            var produto = repository.getReferenceById(id);
        produto.updateData(dados);
            return ResponseEntity.ok(new DadosDetalhamentoSneaker(produto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSneaker(@PathVariable Integer id){
            var produto = repository.getReferenceById(id);
        repository.delete(produto);
            return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        var produto = repository.getReferenceById(id);
            return ResponseEntity.ok(new DadosDetalhamentoSneaker(produto));
    }




}









