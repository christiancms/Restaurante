package br.com.ezzysoft.restaurante.entidade;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author christian medeiros
 */
public class VerificaSenha {
    
    public static String verificaSenha(String senha) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(senha.getBytes());
        byte[] b = md.digest();
        StringBuilder sb = new StringBuilder();
        for(byte b1 : b){
        sb.append(Integer.toHexString(b1 & 0xff));
        }
        return sb.toString();
    }
}
