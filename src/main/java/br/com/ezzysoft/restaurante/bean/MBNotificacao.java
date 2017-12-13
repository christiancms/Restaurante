package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.config.FCM;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by christian on 16/10/17.
 */
@ManagedBean(name = "mbNotificacao")
@SessionScoped
public class MBNotificacao implements Serializable {

    public void avisar(){

        Random rnd = new Random();
        String mesa = String.valueOf(rnd.nextInt()*10);
        String tokenId = "/topics/ezzy";
        String server_key = "";
        String message = "Mesa: "+mesa;

        //Method to send Push Notification
        //FCM.send_FCM_Notification(tokenId, server_key, message);
    }

}
