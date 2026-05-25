package br.com.felipe034sato.controllers;

import br.com.felipe034sato.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController //para fazer aplicações rest, abstração
public class GreetingController {

    private static final String template = "Hello, %s!"; //placeholder
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting") //Para ser reconhecido como um metodo exposto do HTTP
    public Greeting greeting(
            @RequestParam(value="name",defaultValue = "World")//Sem isso se uma requisiçao vier via Http não conseguira ler os parametros
            String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/address")
    public String outcome(){
        return "Rua viconde";
    }
}

//incrementAndGet = incrementa e retorna um valor maior
//AutomicLong = guarda multilos numeros de forma segura
