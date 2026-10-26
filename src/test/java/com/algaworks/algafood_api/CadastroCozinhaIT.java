package com.algaworks.algafood_api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {

	@LocalServerPort
	private int port;

	@Test
	public void shouldRetornarStatus200_WhenConsultarCozinhas() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
				.basePath("/cozinhas")
				.port(port)
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void shouldConter4Cozinhas_WhenConsultarCozinhas() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
				.basePath("/cozinhas")
				.port(port)
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value())
				.body("", Matchers.hasSize(4))
				.body("nome", Matchers.hasItems("Indiana", "Tailandesa"));
	}

}