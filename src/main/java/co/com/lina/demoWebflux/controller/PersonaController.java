package co.com.lina.demoWebflux.controller;


import co.com.lina.demoWebflux.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/personas/")
public class PersonaController {

    private static final Logger log = LoggerFactory.getLogger(PersonaController.class);

    //crear
    @GetMapping("mostrar")
    // Retornar un flujo de datos de una sola persona
    public Mono<Persona> mostrar(){
        return Mono.just(new Persona(1, "Lina Maria"));
    }
}
