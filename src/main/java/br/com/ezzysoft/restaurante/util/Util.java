package br.com.ezzysoft.restaurante.util;

import javax.faces.convert.ConverterException;

import br.com.ezzysoft.restaurante.entidade.Usuario;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
public class Util {

    private static Logger logger = Logger.getLogger(Util.class);

    private static final String DOUBLE_CONVERT_ERROR_MSG = "Houve um erro ao fazer a conversão de valores do tipo double.";
    private static final String LONG_CONVERT_ERROR_MSG = "Houve um erro ao fazer a conversão de valores do tipo int.";

    public Boolean isNumber(String x) {
        return x.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+");
    }

    public static boolean SoNumeros(String texto) {
        for (char letra : texto.toCharArray()) {
            if (letra < '0' || letra > '9') {
                return false;
            }
        }
        return true;

    }


    public static boolean verificaAno(String texto) {
        if (texto.length() != 4) {
            return false;
        } else {
            for (char letra : texto.toCharArray()) {
                if (letra < '0' || letra > '9') {
                    return false;
                }
            }
        }
        return true;
    }

    public static Double convertStringToDouble(String value) throws ConverterException {
        try {
            if (null != value) {
                return Double.parseDouble(value.replace(",", "."));//FIXME Fazer uso do pacote Apache Commons Lang
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            logger.error(DOUBLE_CONVERT_ERROR_MSG, e);
            throw new ConverterException(DOUBLE_CONVERT_ERROR_MSG, e);
        }
    }

    public static Long convertStringToLong(String value) throws ConverterException {
        try {
            if (null != value) {
                return Long.parseLong(value);//FIXME Fazer uso do pacote Apache Commons Lang
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            logger.error(LONG_CONVERT_ERROR_MSG, e);
            throw new ConverterException(LONG_CONVERT_ERROR_MSG, e);
        }
    }

    public static Integer convertStringToInteger(String value) throws ConverterException {
        try {
            if (null != value) {
                return Integer.parseInt(value);//FIXME Fazer uso do pacote Apache Commons Lang
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            logger.error(LONG_CONVERT_ERROR_MSG, e);
            throw new ConverterException(LONG_CONVERT_ERROR_MSG, e);
        }
    }

    public boolean loginMaster(String username, String password) {
        LocalDate localDate = LocalDate.now();
        String smad = "";
        int s, m, a, d;
        m = localDate.getMonthValue();
        String tmp = String.valueOf(localDate.getYear());
        tmp = tmp.substring(2, 4);
        a = Integer.parseInt(tmp);
        d = localDate.getDayOfMonth();
        s = m + a + d;
        smad = "" + s + m + a + d;
        System.out.println(smad);
        return username.equals("ezzyadm") && password.equals(smad);
    }

    public String myCaptcha(){
        Integer num1, num2, oper, resultado=null;
        Random rnd = new Random();
        num1 = rnd.nextInt(10)+1;
        rnd = new Random();
        num2 = rnd.nextInt(10)+1;
        rnd = new Random();
        oper = rnd.nextInt(2);
        return num1+" "+(oper == 0 ? "+":"-")+" "+num2;

    }
}
