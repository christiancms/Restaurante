package br.com.ezzysoft.cardapiomobile.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by christian on 21/06/17.
 */

public class MobileDB extends SQLiteOpenHelper {

    public static final String CARDAPIO = "cardapiomobile.sqlite";

    public MobileDB(Context context) {
        super(context, CARDAPIO, null, 1);
//        context.deleteDatabase(PEDIDO);
//            SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Cliente
        db.execSQL("create table if not exists cliente(id integer primary key autoincrement, " +
                "nome text, cadNacional text, ultAtt numeric)");

        // Produto
        db.execSQL("create table if not exists produto(id integer primary key autoincrement, " +
                "descricao text, precoVenda real, saldoEstoq real, ultAtt numeric)");

        // Usuário
        db.execSQL("create table if not exists usuario(id integer primary key autoincrement, " +
                "login text, senha text, representanteId integer, ultAtt numeric, " +
                "foreign key (representanteId) references representante(id))");

        // Pedido
        db.execSQL("create table if not exists pedido(id integer primary key autoincrement, " +
                "pedidoerpId integer, representanteId integer, valorTotal real, formaPgto text," +
                " obs text, idCliente integer, descontoTotal real, status numeric, ultAtt numeric," +
                "foreign key (idCliente) references cliente(id)," +
                "foreign key (representanteId) references representante(id))");

        // Itens do Pedido
        db.execSQL("create table if not exists itens(id integer primary key autoincrement, " +
                "idPedido integer, valorTotal real, desconto real, quantidade real, idProduto integer," +
                " codOpId integer, ultAtt numeric," +
                "foreign key (idPedido) references pedido(id)," +
                "foreign key (idProduto) references produto(id)," +
                "foreign key (codOpId) references codigooperacao(id))");

        // Código de Operação
        db.execSQL("create table if not exists codigooperacao(id integer primary key autoincrement, " +
                "descricao text, geraCarga numeric, cfopde integer, cfopfe integer, ultAtt numeric)");

        // Representante
        db.execSQL("create table if not exists representante(id integer primary key autoincrement, " +
                "nome text, limiteDesc real, comissao real, alteraPv numeric, senhaLiberacao text, ultAtt numeric)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS cliente");
        onCreate(db);
    }

}
