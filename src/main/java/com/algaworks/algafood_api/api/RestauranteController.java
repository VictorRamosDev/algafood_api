package com.algaworks.algafood_api.api;

import com.algaworks.algafood_api.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood_api.domain.exception.NegocioException;
import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;
import com.algaworks.algafood_api.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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
    public List<Restaurante> listar() {
        List<Restaurante> restaurantes = restauranteRepository.findAll();

        System.out.println(restaurantes.get(0).getNome());
        restaurantes.get(0).getFormasPagamento().forEach(System.out::println);

        System.out.println(restaurantes.get(1).getNome());
        restaurantes.get(1).getFormasPagamento().forEach(System.out::println);

        return restaurantes;
    }

    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId) {
        if (true) {
            throw new IllegalArgumentException("Teste");
        }

        try {
            return cadastroRestauranteService.buscarOuFalhar(restauranteId);
        } catch (MethodArgumentTypeMismatchException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            return null;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody @Valid Restaurante request) {
        try {
            return cadastroRestauranteService.salvar(request);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restauranteId}")
    public Restaurante atualizar(
            @PathVariable Long restauranteId,
            @RequestBody Restaurante restaurante
    ) {
        return cadastroRestauranteService.atualizar(restauranteId, restaurante);
    }

    @PatchMapping("/{restauranteId}")
    public Restaurante atualizarParcial(
            @PathVariable Long restauranteId,
            @RequestBody Map<String, Object> campos,
            HttpServletRequest request
    ) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        merge(campos, restaurante, request);

        return cadastroRestauranteService.atualizar(restauranteId, restaurante);
    }

    private void merge(Map<String, Object> campos, Restaurante restauranteDestino, HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

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
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }

    @DeleteMapping("/{restauranteId}")
    public void remover(@PathVariable Long restauranteId) {
        cadastroRestauranteService.remover(restauranteId);
    }

}
