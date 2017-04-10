package br.com.ezzysoft.restaurante.util.exception;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
public class ErroSistema extends Exception {

    public ErroSistema(String message) {
        super(message);
    }

    public ErroSistema(String message, Throwable cause) {
        super(message, cause);
    }
}
