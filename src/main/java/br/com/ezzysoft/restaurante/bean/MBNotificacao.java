package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.config.FCM;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by christian on 16/10/17.
 */
@ManagedBean(name = "MBNotificacao")
@SessionScoped
public class MBNotificacao implements Serializable {

    public void avisar(){

        Random rnd = new Random();
        String mesa = String.valueOf(rnd.nextInt()*10);
        String tokenId="";
        // hRNYQ,Xc teI!J&&wEl<Z5}$D$|%E)+OPFTp$F789R9J8+I2])BWWWS=wkUIKwsIz[JzQj{wEOWD<1*E.JT'^$-8;pc-D)$=5:VS0(cH$LyI7KpJ6-6E,'Bz9Jzn'0,s+I.yK%&f.,>HB^s:X0v665h<]!)GW1=`)7de
        String server_key="";
        // gHUjHUSNHmc te$5$$,:Tkxt_}$D$|%E(az(8U9$(xeE]'w7W9t)W7Du'!D,a4=cy]a2/`B2>vy_-E;\WjX1AB)<%3W*YWs/dvTBBD+y+EKB,47(W\@zGfY]9[5gG=K7Q-xK5HJm9Z&S8B9O@4GD5Ui/w`pEL<&WGP%Ws8e
        String message = "Mesa: "+mesa;

        //Method to send Push Notification
        FCM.send_FCM_Notification(tokenId, server_key, message);
    }

}
