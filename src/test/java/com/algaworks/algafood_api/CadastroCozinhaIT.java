package com.algaworks.algafood_api;

import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import com.algaworks.algafood_api.util.DatabaseCleaner;
import com.algaworks.algafood_api.util.ResourceUtils;
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

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {

	private static final int COZINHA_ID_INEXISTENTE = 100;
	private static final String[] COZINHAS_NOME = {"Tailandesa", "Americana"};
	private static final String COZINHA_RESOURCE_PATH = "/json/correto/cozinha-chinesa.json";

	private int quantidadeCozinhasCadastradas;
	private String jsonCorretoCozinhaChinesa;
	private Cozinha cozinhaAmericana;


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
		quantidadeCozinhasCadastradas = preparaDados();
		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource(COZINHA_RESOURCE_PATH);
	}

	@Test
	public void shouldRetornarStatus200_WhenConsultarCozinhas() {
		given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void shouldConter2Cozinhas_WhenConsultarCozinhas() {
		given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value())
				.body("", Matchers.hasSize(quantidadeCozinhasCadastradas))
				.body("nome", Matchers.hasItems(COZINHAS_NOME));
	}

	@Test
	public void shouldRetornarStatus201_WhenCadastrarCozinha() {
		given()
				.body(jsonCorretoCozinhaChinesa)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void shouldRetornarRespostaEStatusCorretos_WhenConsultarCozinhaExistente() {
		given()
				.pathParams("cozinhaId", cozinhaAmericana.getId())
				.accept(ContentType.JSON)
		.when()
				.get("/{cozinhaId}")
		.then()
				.statusCode(HttpStatus.OK.value())
				.body("nome", Matchers.equalTo(cozinhaAmericana.getNome()));
	}

	@Test
	public void shouldRetornarStatus404_WhenConsultarCozinhaInexistente() {
		given()
				.pathParams("cozinhaId", COZINHA_ID_INEXISTENTE)
				.accept(ContentType.JSON)
				.when()
				.get("/{cozinhaId}")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private int preparaDados() {
		List<Cozinha> cozinhas = new ArrayList<>();

		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Tailandesa");
		cozinhas.add(cozinha);


		cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhas.add(cozinhaAmericana);

		return (cozinhaRepository.saveAll(cozinhas).size());
	}

}