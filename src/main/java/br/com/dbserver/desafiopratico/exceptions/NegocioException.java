package br.com.dbserver.desafiopratico.exceptions;

public class NegocioException extends RuntimeException {
    public NegocioException(String message) {
        super(message);
    }
}
