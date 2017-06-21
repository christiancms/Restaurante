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

import br.com.ezzysoft.cardapiomobile.bean.Produto;

/**
 * Created by christian on 21/06/17.
 */

public class ProdutoHttp {

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
    public static List<Produto> carregarProdutosJson(String var) {
        try {
            HttpURLConnection conexao = connectar("http://" + var + "/wsrest/jfrmservices/estg/produto/get");
            int resposta = conexao.getResponseCode();
            if (resposta == HttpURLConnection.HTTP_OK) {
                InputStream is = conexao.getInputStream();
                JSONObject json = new JSONObject(bytesParaString(is));
                return lerJsonProdutos(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Produto> lerJsonProdutos(JSONObject json) throws JSONException {
        List<Produto> listaDeProdutos = new ArrayList<Produto>();

        JSONArray jsonEstgproduto = json.getJSONArray("estgproduto");
        for (int i = 0; i < jsonEstgproduto.length(); i++) {
            JSONObject jsonProduto = jsonEstgproduto.getJSONObject(i);
            Produto produto = new Produto(
                    jsonProduto.getInt("id"),
                    jsonProduto.getString("descricao"),
                    jsonProduto.getString("observacao"),
                    jsonProduto.getString("unidade"),
                    jsonProduto.getString("marca"),
                    jsonProduto.getString("precoVenda"),
                    jsonProduto.getString("saldo")

            );
            listaDeProdutos.add(produto);
        }
        return listaDeProdutos;
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
