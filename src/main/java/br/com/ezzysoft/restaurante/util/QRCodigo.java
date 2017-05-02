package br.com.ezzysoft.restaurante.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import net.glxn.qrgen.javase.QRCode;
import net.glxn.qrgen.core.image.ImageType;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
public class QRCodigo {

    public void geraCodigo(String url,String destino){
        destino = "/tmp/qr-code.png";
        ByteArrayOutputStream bout =
                QRCode.from(url)
                        .withSize(250, 250)
                        .to(ImageType.PNG)
                        .stream();

        try (OutputStream out = new FileOutputStream(destino)) {
            bout.writeTo(out);
            out.flush();

        } catch (FileNotFoundException e){
        } catch (IOException e) {
        }
    }
}
