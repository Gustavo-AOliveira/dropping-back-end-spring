package br.com.dropping.controller;

import br.com.dropping.domain.*;
import br.com.dropping.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Transactional
    public ResponseEntity saveSneaker(@RequestBody @Valid DadosCadastroSneaker dados, UriComponentsBuilder uriBuilder){
        var produto = new Produto(dados);
            repository.save(produto);
        var uri = uriBuilder.path("api/products/{id}").buildAndExpand(produto.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhamentoSneaker(produto));
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<DadosListagemSneaker>> getAll(){
        List<DadosListagemSneaker> productsList = repository.findAll().stream().map(DadosListagemSneaker::new).toList();
            return ResponseEntity.ok(productsList);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateSneaker(@PathVariable Integer id, @RequestBody @Valid DadosAtualizacaoSneaker dados){
            var produto = repository.getReferenceById(id);
        produto.updateData(dados);
            return ResponseEntity.ok(new DadosDetalhamentoSneaker(produto));
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSneaker(@PathVariable Integer id){
            var produto = repository.getReferenceById(id);
        repository.delete(produto);
            return ResponseEntity.noContent().build();
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        var produto = repository.getReferenceById(id);
            return ResponseEntity.ok(new DadosDetalhamentoSneaker(produto));
    }




}









