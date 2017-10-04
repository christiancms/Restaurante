package br.com.ezzysoft.restaurante.util;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author christian
 */
public class AndroidUtil {

    //Send Push Notification to a single device
    private String sendNotification(String message, String appId) {
        Sender sender = new Sender("AIzaSyCz2bnoLP-TzzpS-7AQNSYO87icMwpoj_g"); // Here you will write APP key given by Android end
        Message msg = new Message.Builder().addData("message", message).build();
        String str = null;
        try {
            Result results = sender.send(msg, appId, 5); // Where appId is given by Android end
            if (results.getMessageId() != null) {
//                str = val_true;
            } else {
//                str = val_false;
                String error = results.getErrorCodeName();
//                logger.info("message sending failed:: " + error);
                if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    //Send Push Notification to multiple devices
    private String sendMulptipleNotifiaction(String message, ArrayList devices) {
        Sender sender = new Sender("AIzaSyCz2bnoLP-TzzpS-7AQNSYO87icMwpoj_g");// Here you will write APP key given by Android end
        Message msg = new Message.Builder().addData("message", message).build();
        String str = null;
        try {
            MulticastResult result = sender.send(msg, devices, 5); // where devices is the list of multiple device AppId's
            for (Result r : result.getResults()) {
                if (r.getMessageId() != null) {
//                    str = val_true;
                } else {
//                    str = val_false;
                    String error = r.getErrorCodeName();
                    if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
