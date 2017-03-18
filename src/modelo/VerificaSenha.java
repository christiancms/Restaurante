/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
