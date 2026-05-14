package br.com.felipe034sato.exception.handler;

import br.com.felipe034sato.exception.ExeptionResponse;
import br.com.felipe034sato.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice // tratamento global, concentrar um tratamento que seria espalhado em todo codigo.
@RestController
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) // trata erros nao tratado
    public final ResponseEntity<ExeptionResponse> handleAllExceptions(Exception ex, WebRequest request){
        ExeptionResponse response = new ExeptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); //500 ERRO DO SISTEMA
    }


    @ExceptionHandler(ResourceNotFoundException.class) // trata erros especificos do servidor
    public final ResponseEntity<ExeptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request){
        ExeptionResponse response = new ExeptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); //400 ERRO DO USUARIO
    }
}
