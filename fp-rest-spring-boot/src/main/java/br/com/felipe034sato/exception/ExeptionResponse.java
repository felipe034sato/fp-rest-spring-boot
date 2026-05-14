package br.com.felipe034sato.exception;

import java.util.Date;

public record ExeptionResponse(Date timestamp, String message, String details) {
//Simplifica criação de classes que irao somente armazenar valor
// mais claro, facilitador, segurança. Automaticamente cria getters e setters, classe construtor
}
