package br.com.ezzysoft.cardapiomobile.util.exception;

/**
 * Created by christian on 21/06/17.
 */

public class ErroSistema extends Exception {

    public ErroSistema(String message) {
        super(message);
    }

    public ErroSistema(String message, Throwable cause) {
        super(message, cause);
    }
}