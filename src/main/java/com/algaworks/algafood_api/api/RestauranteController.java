package com.algaworks.algafood_api.api;

import com.algaworks.algafood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;
import com.algaworks.algafood_api.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;
    private final CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    public RestauranteController(
            RestauranteRepository restauranteRepository,
            CadastroRestauranteService cadastroRestauranteService)
    {
        this.restauranteRepository = restauranteRepository;
        this.cadastroRestauranteService = cadastroRestauranteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
        Optional<Restaurante> restauranteOpt = restauranteRepository.findById(restauranteId);

        return restauranteOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante request) {
        try {
            Restaurante restaurante = cadastroRestauranteService.salvar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long restauranteId,
            @RequestBody Restaurante restaurante
    ) {
        try {
            Restaurante restauranteAtualizado = cadastroRestauranteService.atualizar(restauranteId, restaurante);
            return ResponseEntity.ok(restauranteAtualizado);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(
            @PathVariable Long restauranteId,
            @RequestBody Map<String, Object> campos
    ) {
        Optional<Restaurante> restauranteAtualOpt = restauranteRepository.findById(restauranteId);
        if (restauranteAtualOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtualOpt.get());

        return atualizar(restauranteId, restauranteAtualOpt.get());
    }

    private void merge(Map<String, Object> campos, Restaurante restauranteDestino) {
        ObjectMapper mapper = new ObjectMapper();
        Restaurante restauranteRequest = mapper.convertValue(campos, Restaurante.class);

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            if (field != null) {
                field.setAccessible(true);
            }

            Object novoValor = ReflectionUtils.getField(field, restauranteRequest);
            System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<?> remover(@PathVariable Long restauranteId) {
        try {
            cadastroRestauranteService.remover(restauranteId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
