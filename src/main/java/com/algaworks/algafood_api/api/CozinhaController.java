package com.algaworks.algafood_api.api;

import com.algaworks.algafood_api.model.Cozinha;
import com.algaworks.algafood_api.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> listarJson() {
        System.out.println("Listar JSON");
        return repository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public List<Cozinha> listarXml() {
        System.out.println("Listar XML");
        return repository.listar();
    }
}
