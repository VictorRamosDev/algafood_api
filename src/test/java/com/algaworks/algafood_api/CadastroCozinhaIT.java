package com.algaworks.algafood_api;

import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import com.algaworks.algafood_api.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@BeforeEach
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";

		databaseCleaner.clearTables();
		preparaDados();
	}

	@Test
	public void shouldRetornarStatus200_WhenConsultarCozinhas() {
		RestAssured.given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void shouldConter2Cozinhas_WhenConsultarCozinhas() {
		RestAssured.given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value())
				.body("", Matchers.hasSize(2))
				.body("nome", Matchers.hasItems("Tailandesa", "Americana"));
	}

	@Test
	public void shouldRetornarStatus201_WhenCadastrarCozinha() {
		RestAssured.given()
				.body("{ \"nome\": \"Chinesa\" }")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	private void preparaDados() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Tailandesa");


		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Americana");

		cozinhaRepository.save(cozinha);
		cozinhaRepository.save(cozinha2);
	}

}