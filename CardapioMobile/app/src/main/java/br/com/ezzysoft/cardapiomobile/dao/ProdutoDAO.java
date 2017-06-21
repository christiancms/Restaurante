package br.com.ezzysoft.cardapiomobile.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.ezzysoft.cardapiomobile.bean.Produto;
import br.com.ezzysoft.cardapiomobile.util.exception.ErroSistema;

/**
 * Created by christian on 21/06/17.
 */

public class ProdutoDAO implements Crud<Produto> {

    MobileDB mobileDB;


    public ProdutoDAO(Context context) {
        mobileDB = new MobileDB(context);
    }

    @Override
    public long salvar(Produto entidade) throws ErroSistema {
        return 0;
    }

    @Override
    public void deletar(Produto entidade) throws ErroSistema {

    }

    @Override
    public List<Produto> buscar() throws ErroSistema {
        return null;
    }

    public List<Produto> carregarProdutoDB() {
        SQLiteDatabase db = mobileDB.getWritableDatabase();

        List<Produto> lista = new ArrayList<>();

        try {
            Cursor c = db.query("produto", null, null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    Produto produto = new Produto();

                    produto.setId(Integer.parseInt(c.getString(0)));
                    produto.setDescricao(c.getString(1));
                    produto.setPrecoVenda(c.getString(2));
                    produto.setSaldoEstoq(c.getString(3));

                    lista.add(produto);
                } while (c.moveToNext());
            }
            c.close();
            return lista;
        } finally {
            db.close();
        }

    }
}
