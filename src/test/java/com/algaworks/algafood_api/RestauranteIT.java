package com.algaworks.algafood_api;

import com.algaworks.algafood_api.domain.model.*;
import com.algaworks.algafood_api.domain.repository.CidadeRepository;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import com.algaworks.algafood_api.domain.repository.EstadoRepository;
import com.algaworks.algafood_api.domain.repository.RestauranteRepository;
import com.algaworks.algafood_api.util.DatabaseCleaner;
import com.algaworks.algafood_api.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestauranteIT {

    private static final String VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE = "Violação de regra de negócio";
    private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inválidos";
    private static final int RESTAURANTE_ID_INEXISTENTE = 100;

    private static final String RESTAURANTE_CORRETO_RESOURCE_PATH = "/json/correto/restaurante-new-york-barbecue.json";
    private static final String RESTAURANTE_INCORRETO_COZINHA_INEXISTENTE_RESOURCE_PATH = "/json/incorreto/restaurante-new-york-barbecue-com-cozinha-inexistente.json";
    private static final String RESTAURANTE_INCORRETO_SEM_COZINHA_RESOURCE_PATH = "/json/incorreto/restaurante-new-york-barbecue-sem-cozinha.json";
    private static final String RESTAURANTE_INCORRETO_SEM_FRETE_RESOURCE_PATH = "/json/incorreto/restaurante-new-york-barbecue-sem-frete.json";
    private static final Logger log = LoggerFactory.getLogger(RestauranteIT.class);

    @LocalServerPort
    private int port;

    private String jsonRestauranteCorreto;
    private String jsonRestauranteIncorretoComCozinhaInexistente;
    private String jsonRestauranteIncorretoSemCozinha;
    private String jsonRestauranteIncorretoSemFrete;

    private Restaurante burguerTopRestaurante;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        jsonRestauranteCorreto = ResourceUtils.getContentFromResource(RESTAURANTE_CORRETO_RESOURCE_PATH);
        jsonRestauranteIncorretoComCozinhaInexistente = ResourceUtils.getContentFromResource(RESTAURANTE_INCORRETO_COZINHA_INEXISTENTE_RESOURCE_PATH);
        jsonRestauranteIncorretoSemCozinha = ResourceUtils.getContentFromResource(RESTAURANTE_INCORRETO_SEM_COZINHA_RESOURCE_PATH);
        jsonRestauranteIncorretoSemFrete = ResourceUtils.getContentFromResource(RESTAURANTE_INCORRETO_SEM_FRETE_RESOURCE_PATH);

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void shouldRetornarStatus200_WhenConsultarRestaurantes() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldRetornarStatus201_WhenCadastrarRestaurante() {
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonRestauranteCorreto)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldRetornar400_WhenCadastrarRestauranteSemTaxaFrete() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonRestauranteIncorretoSemFrete)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
    }

    @Test
    public void shouldRetornar400_WhenCadastrarRestauranteSemCozinha() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonRestauranteIncorretoSemCozinha)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
    }

    @Test
    public void shouldRetornar400_WhenCadastrarRestauranteComCozinhaInexistente() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonRestauranteIncorretoComCozinhaInexistente)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
    }

    @Test
    public void shouldRetornarRespostaEStatusCorretos_WhenConsultarRestauranteExistente() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("restauranteId", burguerTopRestaurante.getId())
        .when()
                .get("/{restauranteId}")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(burguerTopRestaurante.getNome()));

    }

    @Test
    public void shouldRetornarStatus404_WhenConsultarRestauranteInexistente() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE)
        .when()
                .get("/{restauranteId}")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        Cozinha cozinhaBrasileira = new Cozinha();
        cozinhaBrasileira.setNome("Brasileira");
        cozinhaRepository.save(cozinhaBrasileira);

        Cozinha cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Americana");
        cozinhaRepository.save(cozinhaAmericana);

        Estado estado = new Estado();
        estado.setNome("Estado Teste");
        estadoRepository.save(estado);

        Cidade cidade = new Cidade();
        cidade.setEstado(estado);
        cidade.setNome("Cidade Teste");
        cidadeRepository.save(cidade);

        Endereco endereco = new Endereco();
        endereco.setBairro("Bairro Teste");
        endereco.setCep("12345678");
        endereco.setNumero("123");
        endereco.setLogradouro("Avenida Teste");
        endereco.setComplemento("Complemento Teste");
        endereco.setCidade(cidade);
        burguerTopRestaurante = new Restaurante();
        burguerTopRestaurante.setNome("Burguer Top");
        burguerTopRestaurante.setTaxaFrete(new BigDecimal(10));
        burguerTopRestaurante.setCozinha(cozinhaAmericana);
        burguerTopRestaurante.setEndereco(endereco);
        restauranteRepository.save(burguerTopRestaurante);

        Restaurante comidaMineiraRestaurante = new Restaurante();
        comidaMineiraRestaurante.setNome("Comida Mineira");
        comidaMineiraRestaurante.setTaxaFrete(new BigDecimal(10));
        comidaMineiraRestaurante.setCozinha(cozinhaBrasileira);
        restauranteRepository.save(comidaMineiraRestaurante);
    }

}
