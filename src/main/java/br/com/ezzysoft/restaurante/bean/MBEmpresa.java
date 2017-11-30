package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.EmpresaDAO;
import br.com.ezzysoft.restaurante.entidade.Empresa;
import br.com.ezzysoft.restaurante.ws.PJTransporter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "mbEmpresa")
@SessionScoped
public class MBEmpresa implements Serializable{

    private EmpresaDAO empresaDAO;
    public static final String URL_CNPJ = "https://www.receitaws.com.br/v1/cnpj/";

//    @Override
    public EmpresaDAO getDao() {
        if (empresaDAO == null) {
            empresaDAO = new EmpresaDAO();
        }
        return empresaDAO;
    }

//    @Override
    public Empresa criarNovaEntidade() {
        return new Empresa();
    }

    public PJTransporter getVerificaCNPJ(String cnpj) throws MalformedURLException {
        //cnpj = "12709186000131";
        URL url;
        try {
            url = new URL("http://www.receitaws.com.br/v1/cnpj/" + cnpj);
        } catch (MalformedURLException e) {
            return null;
        }
        if (url != null) {
            try {
                url.openConnection();
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String texto, json = "";
                while ((texto = br.readLine()) != null) {
                    json += texto;
                }
                ObjectMapper mapper = new ObjectMapper();
                PJTransporter pjt = mapper.readValue(json, PJTransporter.class);
                return pjt;
            } catch (IOException e) {
                System.out.println(e.fillInStackTrace());
                return null;
            }
        }

        return null;
    }
}
