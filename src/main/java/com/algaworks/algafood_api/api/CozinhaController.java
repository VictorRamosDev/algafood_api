package com.algaworks.algafood_api.api;

import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import com.algaworks.algafood_api.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    private final CozinhaRepository repository;

    private final CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    public CozinhaController(CozinhaRepository cozinhaRepository, CadastroCozinhaService cadastroCozinhaService) {
        this.repository = cozinhaRepository;
        this.cadastroCozinhaService = cadastroCozinhaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cozinha> listar() {
        return repository.findAll();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
        Optional<Cozinha> cozinhaOp = repository.findById(id);

        return cozinhaOp.map(cozinha -> ResponseEntity.status(HttpStatus.OK)
                .body(cozinha))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha salvar(@RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Optional<Cozinha> cozinhaAtualOp = repository.findById(cozinhaId);

        if (cozinhaAtualOp.isPresent()) {
            BeanUtils.copyProperties(cozinha, cozinhaAtualOp.get(), "id");
            Cozinha cozinhaSalva = repository.save(cozinhaAtualOp.get());
            return ResponseEntity.ok(cozinhaSalva);
        }

        return ResponseEntity.notFound().build();
    }

//    @DeleteMapping("/{cozinhaId}")
//    public ResponseEntity<?> deletar(@PathVariable Long cozinhaId) {
//        try {
//            cadastroCozinhaService.remover(cozinhaId);
//            return ResponseEntity.noContent().build();
//        } catch (EntidadeEmUsoException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.remover(cozinhaId);
    }

}


