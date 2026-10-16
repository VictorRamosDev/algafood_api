package com.algaworks.algafood_api;

import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@Test
	public void shouldCadastrarCozinhaComSucesso() {
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
	public void shouldLancarViolationExceptionAoCadastrarCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();

		ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
			cadastroCozinhaService.salvar(novaCozinha);
		});

		Assertions.assertNotNull(erroEsperado);
	}

}