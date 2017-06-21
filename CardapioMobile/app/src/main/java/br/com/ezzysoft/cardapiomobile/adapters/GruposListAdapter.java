package br.com.ezzysoft.cardapiomobile.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import br.com.ezzysoft.cardapiomobile.bean.Grupo;

/**
 * Created by christian on 07/05/17.
 */
public class GruposListAdapter extends ArrayAdapter<Grupo> {

    public GruposListAdapter(Context context, int resource) {
        super(context, 0);
    }
}
