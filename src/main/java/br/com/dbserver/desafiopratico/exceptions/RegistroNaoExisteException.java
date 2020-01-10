package br.com.dbserver.desafiopratico.exceptions;

public class RegistroNaoExisteException extends RuntimeException {

    public RegistroNaoExisteException(String message) {
        super(message);
    }
}
