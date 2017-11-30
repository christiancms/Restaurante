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
        String tokenId = "f-gC24bxYwA:APA91bF7lmRq-0cTDVoEgU7fOiF_ctt_Z4wrfW40U7xV7nv84QltPYN6bKVqDjAJDX-oJa5AZAWs_MEoeAX6fCh-VSJBbI3_7Eg7zDM80HUK6WBCrKIJe_j0WdM3BSRtYz-FdcNZlFTp";
      //String server_key = "AAAA8Wqw51k:APA91bEm7EDrV0E5qbz34TcV15tTP2D-aImQZo6zmOLl_OJ36kJbGytvuNM_FHBPcGvc0Lp3qN_a76HQh_8QT4tyL7drvzExRsdZWTn95hAegyVw2pUNVlLQdPRruL4l-bXYCcdm1t0D";
        String server_key = "AAAA8Wqw51k:APA91bEm7EDrV0E5qbz34TcV15tTP2D-aImQZo6zmOLl_OJ36kJbGytvuNM_FHBPcGvc0Lp3qN_a76HQh_8QT4tyL7drvzExRsdZWTn95hAegyVw2pUNVlLQdPRruL4l-bXYCcdm1t0D";
        String message = "Mesa: "+mesa;

        //Method to send Push Notification
        FCM.send_FCM_Notification(tokenId, server_key, message);
    }

}
