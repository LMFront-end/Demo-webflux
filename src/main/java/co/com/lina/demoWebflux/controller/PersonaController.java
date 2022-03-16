package co.com.lina.demoWebflux.controller;


import co.com.lina.demoWebflux.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/personas/")
public class PersonaController {

    private static final Logger log = LoggerFactory.getLogger(PersonaController.class);


    @GetMapping("mostrar")
    // Retornar un flujo de datos de una sola persona
    public Mono<Persona> mostrar(){
        // Mono.just --> retornar un Mono de personas
        return Mono.just(new Persona(1, "Lina Maria"));
    }

    @GetMapping("")
    public Flux<Persona> listar(){
        List<Persona> personas = new ArrayList<>();

        personas.add(new Persona(1, "Juan Pablo"));
        personas.add(new Persona(1, "Diana"));

        return Flux.fromIterable(personas);
    }

    @GetMapping("/response")
    // devolver un flujo de personas
    public Mono<ResponseEntity<Flux<Persona>>> listarEntity(){
        List<Persona> personas = new ArrayList<>();

        personas.add(new Persona(1, "Juan Pablo"));
        personas.add(new Persona(1, "Diana"));

        Flux<Persona> personaFlux =  Flux.fromIterable(personas);

        // Enfoque asncrono --> libreria de webFlux
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON).body(personaFlux)
        );
    }

    // simulación de una base de datos
    @DeleteMapping("/{modo}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable("modo") Integer modo) {
        return buscarPersona(modo)
                // el operador flapMap --> Retornar otro flujo de datos
                .flatMap(p -> {
                    return eliminar(p)
                            // retornar algo vacio HttpStatus.NO_CONTENT
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
                }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    public Mono<Void> eliminar(Persona p){
        log.info("Eliminando " + p.getIdPersona() + " = " + p.getNombre());

        return Mono.empty();
    }

    public Mono<Persona> buscarPersona(Integer mono){
        if (mono == 1){
            return Mono.just(new Persona(1, "Lina"));
        }else return Mono.empty();
    }

}
