package com.algaworks.algafood_api.api;

import com.algaworks.algafood_api.model.Cozinha;
import com.algaworks.algafood_api.model.CozinhasXmlWrapper;
import com.algaworks.algafood_api.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaRepository repository;

    @GetMapping
    public List<Cozinha> listarJson() {
        return repository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(repository.listar());
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
        Cozinha cozinha = repository.buscar(id);

        if (cozinha != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cozinha);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public Cozinha salvar(@RequestBody Cozinha cozinha) {
        return repository.salvar(cozinha);
    }

}


