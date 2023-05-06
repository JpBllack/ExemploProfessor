package br.unitins;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.PessoaFisicaDTO;
import br.unitins.topicos1.dto.PessoaFisicaResponseDTO;
import br.unitins.topicos1.resource.PessoaFisicaResource;
import br.unitins.topicos1.service.PessoaFisicaService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class PessoaFisicaResourceTest {

    @Inject
    PessoaFisicaService pfService;

    @Test
    public void getAllTest() {
        given()
          .when().get("/pessoasfisicas")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {
        PessoaFisicaDTO pf = new PessoaFisicaDTO(
            "333.333.333-33",
            2,
            "Elon Musk"
        );

        given()
          .contentType(ContentType.JSON)
          .body(pf)
          .when().post("/pessoasfisicas")
          .then()
             .statusCode(201)
             .body("id", notNullValue(),
              	 "nome", is("Elon Musk"),
             	 "cpf", is("333.333.333-33"),
                 "sexo.label", is("Masculino"));
    }

    @Test
    public void testUpdate() {
        // Adicionando uma pessoa no banco de dados
        PessoaFisicaDTO pf = new PessoaFisicaDTO(
            "333.333.333-33",
            2,
            "Elon Musk"
        );
        Long id = pfService.create(pf).id();

        // Criando outra pessoa para atuailzacao
        PessoaFisicaDTO pfUpdate = new PessoaFisicaDTO(
            "444.444.444-44",
            1,
            "Elen"
        );

        given()
          .contentType(ContentType.JSON)
          .body(pfUpdate)
          .when().put("/pessoasfisicas/" + id)
          .then()
             .statusCode(204);

        // Verificando se os dados foram atualizados no banco de dados
        PessoaFisicaResponseDTO pfResponse = pfService.findById(id);
        assertThat(pfResponse.nome(), is("Elen"));
        assertThat(pfResponse.cpf(), is("444.444.444-44"));
    }

    @Test
    public void testDelete() {
        // Adicionando uma pessoa no banco de dados
        PessoaFisicaDTO pf = new PessoaFisicaDTO(
            "333.333.333-33",
            2,
            "Elon Musk"
        );
        Long id = pfService.create(pf).id();

        given()
          .when().delete("/pessoasfisicas/" + id)
          .then()
             .statusCode(204);

        // verificando se a pessoa fisica foi excluida
        PessoaFisicaResponseDTO pfResponse = null;
        try {
            pfResponse =  pfService.findById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(pfResponse);   
        }   
     
    }

    @Test
    @TestHTTPEndpoint(PessoaFisicaResource.class)
    public void getAllTest2() {
        given()
          .when().get()
          .then()
             .statusCode(200)
             .body("$.size()", is(2), 
                   "[0].nome", is("Marco"),
                   "[0].cpf", is("111.111.111-11"));
    }





}