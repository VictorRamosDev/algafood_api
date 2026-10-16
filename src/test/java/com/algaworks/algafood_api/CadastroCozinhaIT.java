package com.algaworks.algafood_api;

import com.algaworks.algafood_api.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.service.CadastroCozinhaService;
import com.algaworks.algafood_api.domain.service.CadastroRestauranteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

@SpringBootTest
class CadastroCozinhaIT {

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@Test
	public void shouldAtribuirIdWhenCadastroCozinhaComDadosCorretos() {
		// Cenario
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		// Acao
		novaCozinha = cadastroCozinhaService.salvar(novaCozinha);

		// Validacao
		Assertions.assertNotNull(novaCozinha);
		Assertions.assertNotNull(novaCozinha.getId());
	}

	@Test
	public void shouldLancarViolationExceptionWhenCadastrarCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();

		ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
			cadastroCozinhaService.salvar(novaCozinha);
		});

		Assertions.assertNotNull(erroEsperado);
	}

	@Test
	public void shouldFalharWhenExcluirCozinhaEmUso() {
		EntidadeEmUsoException erroEsperado = Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
			Cozinha cozinhaEmUso = new Cozinha();
			cozinhaEmUso.setNome("Chinesa");
			Restaurante restaurante = new Restaurante();
			cozinhaEmUso = cadastroCozinhaService.salvar(cozinhaEmUso);

			restaurante.setCozinha(cozinhaEmUso);
			restaurante.setNome("Xingling Food");
			restaurante.setTaxaFrete(BigDecimal.valueOf(10));
			cadastroRestauranteService.salvar(restaurante);

			cadastroCozinhaService.remover(cozinhaEmUso.getId());
		});

		Assertions.assertNotNull(erroEsperado);
	}

	@Test
	public void shouldFalharWhenExcluirCozinhaInexistente() {
		CozinhaNaoEncontradaException erroEsperado =
				Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
					cadastroCozinhaService.remover(100L);
				});

		Assertions.assertNotNull(erroEsperado);
	}

}