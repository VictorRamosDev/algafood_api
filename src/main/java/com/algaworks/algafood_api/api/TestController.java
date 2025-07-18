package com.algaworks.algafood_api.api;

import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;
import com.algaworks.algafood_api.infrastructure.repository.spec.RestauranteComFreteGratisSpec;
import com.algaworks.algafood_api.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private RestauranteRepository restauranteRepository;

    @Autowired
    public TestController(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
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
        var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);

        return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
    }
}
