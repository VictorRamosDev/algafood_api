package com.algaworks.algafood_api.domain.service;

import com.algaworks.algafood_api.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood_api.domain.exception.NegocioException;
import com.algaworks.algafood_api.domain.exception.RestauranteNaoEncontradaException;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    public static final String MSG_RESTAURANTE_EM_USO = "O restaurante com código %d está sendo utilizado no sistema.";
    private final RestauranteRepository restauranteRepository;
    private final CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    public CadastroRestauranteService(RestauranteRepository restauranteRepository, CadastroCozinhaService cadastroCozinhaService) {
        this.restauranteRepository = restauranteRepository;
        this.cadastroCozinhaService = cadastroCozinhaService;
    }

    public Restaurante salvar(Restaurante request) {
        Long cozinhaId = request.getCozinha().getId();

        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
        request.setCozinha(cozinha);

        return restauranteRepository.save(request);
    }

    public Restaurante atualizar(Long restauranteId, Restaurante request) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);

        try {
            Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(request.getCozinha().getId());
            restaurante.setCozinha(cozinha);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }

        BeanUtils.copyProperties(request, restaurante, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
        return restauranteRepository.save(restaurante);
    }

    public void remover(Long restauranteId) {
        try {
            restauranteRepository.deleteById(restauranteId);
        } catch (EmptyResultDataAccessException e) {
            throw new RestauranteNaoEncontradaException(restauranteId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
        }
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradaException(restauranteId));
    }
}
