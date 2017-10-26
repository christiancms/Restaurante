package br.com.ezzysoft.restaurante.util.exception;

/**
 * Created by christian on 07/10/17.
 */
public class NegocioException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NegocioException(String msg) {
        super(msg);
    }
}
