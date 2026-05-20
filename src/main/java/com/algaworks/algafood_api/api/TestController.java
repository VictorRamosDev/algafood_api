package com.algaworks.algafood_api.api;

import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tests")
public class TestController {

    private RestauranteRepository restauranteRepository;

    private CozinhaRepository cozinhaRepository;

    @Autowired
    public TestController(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
    }

    @GetMapping("/restaurantes/top2-por-nome")
    public List<Restaurante> buscaRestaurantesTop2PorNome(String nome) {
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/count-por-cozinha")
    public int buscaRestaurantesPorCozinha(Long cozinhaId) {
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }

    @GetMapping("/restaurantes/consultar-por-nome")
    public List<Restaurante> buscaRestaurantesPorNome(Long cozinhaId, String nome) {
        return restauranteRepository.consultarPorNome(nome, cozinhaId);
    }

    @GetMapping("/restaurantes/consultar-por-nome-taxa-frete")
    public List<Restaurante> buscaRestaurantesPorNomeTaxaFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.findByCriteriaOptimized(nome, taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/consultar-por-taxa-frete-gratis")
    public List<Restaurante> buscaRestaurantesPorTaxaFreteGratis(String nome) {
        return restauranteRepository.findComFreteGratis(nome);
    }

    @GetMapping("/restaurantes/consultar-primeiro")
    public Optional<Restaurante> buscaPrimeiroRestaurante() {
        return restauranteRepository.buscarPrimeiro();
    }

    @GetMapping("/cozinhas/consultar-primeiro")
    public Optional<Cozinha> buscaPrimeiraCozinha() {
        return cozinhaRepository.buscarPrimeiro();
    }
}
