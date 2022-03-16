package co.com.lina.demoWebflux.controller;


import co.com.lina.demoWebflux.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/personas/")
public class PersonaController {

    private static final Logger log = LoggerFactory.getLogger(PersonaController.class);

    //crear
    @GetMapping("mostrar")
    // Retornar un flujo de datos de una sola persona
    public Mono<Persona> mostrar(){
        // Mono.just --> retornar un Mono de personas
        return Mono.just(new Persona(1, "Lina Maria"));
    }

    @GetMapping
    public Flux<Persona> listar(){
        List<Persona> personas = new ArrayList<>();

        personas.add(new Persona(1, "Juan Pablo"));
        personas.add(new Persona(1, "Diana"));

        Flux <Persona> personasFlux = Flux.fromIterable(personas);

        return personasFlux;
    }
}
