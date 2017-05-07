package br.com.ezzysoft.cardapiomobile.ws;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.ezzysoft.cardapiomobile.bean.Grupo;

/**
 * Created by christian on 07/05/17.
 */
public class GrupoHttp {

    public static final String
    GRUPOS_URL_JSON = "";

    private static HttpURLConnection connectar(String urlArquivo) throws IOException {
        final int SEGUNDOS = 1000;
        URL url = new URL(urlArquivo);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setReadTimeout(300 * SEGUNDOS);
        conexao.setConnectTimeout(300 * SEGUNDOS);
        conexao.setRequestMethod("POST");
        conexao.setDoInput(true);
        conexao.setDoOutput(false);
        conexao.setRequestProperty("Content-Type", "application/json");
        conexao.setRequestProperty("Accept-Charset", "UTF-8");
        conexao.connect();
        return conexao;
    }

    public static boolean temConexao(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    public static List<Grupo> carregarGruposJson() {
        try {
            HttpURLConnection conexao = connectar(GRUPOS_URL_JSON);
            int resposta = conexao.getResponseCode();
            if (resposta == HttpURLConnection.HTTP_OK) {
                InputStream is = conexao.getInputStream();
                JSONObject json = new JSONObject(bytesParaString(is));
                return lerJsonGrupos(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Grupo> lerJsonGrupos(JSONObject json) throws JSONException {
        List<Grupo> listaDeGrupos = new ArrayList<Grupo>();

        JSONArray jsonGrupo = json.getJSONArray("grupo");
        for (int i = 0; i < jsonGrupo.length(); i++) {
            JSONObject jsonGrupos = jsonGrupo.getJSONObject(i);
            Grupo grupo = new Grupo(
                    jsonGrupos.getInt("id"),
                    jsonGrupos.getString("descricao")
            );
            listaDeGrupos.add(grupo);
        }
        return listaDeGrupos;
    }

    private static String bytesParaString(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream bufferzao = new ByteArrayOutputStream();
        int bytesLidos;
        while ((bytesLidos = is.read(buffer)) != -1) {
            bufferzao.write(buffer, 0, bytesLidos);
        }
        return new String(bufferzao.toByteArray(), "UTF-8");
    }
}
