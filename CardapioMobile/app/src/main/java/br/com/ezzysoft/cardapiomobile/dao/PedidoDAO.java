package br.com.ezzysoft.cardapiomobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.ezzysoft.cardapiomobile.bean.ItemPedido;
import br.com.ezzysoft.cardapiomobile.bean.Pedido;
import br.com.ezzysoft.cardapiomobile.bean.Produto;
import br.com.ezzysoft.cardapiomobile.util.exception.ErroSistema;

/**
 * Created by christian on 21/06/17.
 */

public class PedidoDAO implements Crud<ItemPedido> {

    MobileDB mobileDB;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdfDiaMesAno = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date();

    public PedidoDAO(Context context) {
        mobileDB = new MobileDB(context);
    }

    @Override
    public long salvar(ItemPedido itemPedido) throws ErroSistema {
        SQLiteDatabase db = mobileDB.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("idPedido", findMyIdPedido());
            values.put("valorTotal", (itemPedido.getVlrUnitProduto()) * itemPedido.getQtdeProduto());
            values.put("desconto", itemPedido.getDesconto());
            values.put("quantidade", itemPedido.getQtdeProduto());
            values.put("idProduto", itemPedido.getIdProduto());
            values.put("codOpId", itemPedido.getCodOpId());
            values.put("ultAtt", simpleDateFormat.format(date));
            long id = db.insert("itens", "", values);
            return id;
        } finally {
            db.close();
        }

    }

    public long findIdPedido() {
        SQLiteDatabase db = mobileDB.getWritableDatabase();
        long idPedido = 0;
        try {
            Cursor c = db.rawQuery("SELECT MAX(id) FROM pedido", null);
            if (c != null) {
                if (c.moveToFirst()) {
                    idPedido = c.getInt(0) + 1;
                }
            }

            c.close();

        } catch (Exception ex) {

        }
        return idPedido;
    }

    public long findMyIdPedido() {
        SQLiteDatabase db = mobileDB.getWritableDatabase();
        long idPedido = 0;
        try {
            Cursor c = db.rawQuery("SELECT MAX(id) FROM pedido", null);
            if (c != null) {
                if (c.moveToFirst()) {
                    idPedido = c.getInt(0);
                }
            }

            c.close();

        } catch (Exception ex) {

        }
        return idPedido;
    }


    @Override
    public void deletar(ItemPedido entidade) throws ErroSistema {

    }

    @Override
    public List<ItemPedido> buscar() throws ErroSistema {
        return null;
    }

    public List<Pedido> carregarPedidoDB() {
        SQLiteDatabase db = mobileDB.getWritableDatabase();


        List<Pedido> lista = new ArrayList<>();

        try {
            Cursor c = db.rawQuery("SELECT * FROM itens as i INNER JOIN produto as p " +
                    "on p.id=i.idProduto INNER JOIN pedido as pd " +
                    "on pd.id=i.idPedido INNER JOIN cliente as c " +
                    "on pd.idCliente=c.id", null);
            if (c.moveToFirst()) {
                do {
                    Pedido pedido = new Pedido();

                    pedido.setId(Integer.parseInt(c.getString(0)));
                    pedido.setIdCliente(Integer.parseInt(c.getString(1)));

                    lista.add(pedido);
                } while (c.moveToNext());
            }
            c.close();
            return lista;
        } finally {
            db.close();
        }
    }


    public List<ItemPedido> carregarItemDB() {
        SQLiteDatabase db = mobileDB.getWritableDatabase();

        List<ItemPedido> lista = new ArrayList<>();

        try {
            Cursor c = db.rawQuery("SELECT * FROM itens as i INNER JOIN produto as p " +
                    "on p.id=i.idProduto INNER JOIN pedido as pd " +
                    "on pd.id=i.idPedido INNER JOIN cliente as c " +
                    "on pd.idCliente=c.id WHERE pd.id=" + findMyIdPedido(), null);
            if (c.moveToFirst()) {
                do {
                    ItemPedido itemPedido = new ItemPedido();

                    itemPedido.setId(Integer.parseInt(c.getString(0)));
                    itemPedido.setIdPedido(Integer.parseInt(c.getString(1)));
                    itemPedido.setVlrUnitProduto(Double.parseDouble(c.getString(2)));
                    itemPedido.setDesconto(Double.parseDouble(c.getString(3)));
                    itemPedido.setQtdeProduto(Double.parseDouble(c.getString(4)));
                    itemPedido.setIdProduto(Integer.parseInt(c.getString(5)));

                    lista.add(itemPedido);
                } while (c.moveToNext());
            }
            c.close();
            return lista;
        } finally {
            db.close();
        }
    }

    public List<ItemPedido> carregarItemPedido(int id) {
        SQLiteDatabase db = mobileDB.getWritableDatabase();

        List<ItemPedido> lista = new ArrayList<>();

        try {
            Cursor c = db.rawQuery("SELECT * FROM itens as i INNER JOIN produto as p " +
                    "on p.id=i.idProduto INNER JOIN pedido as pd " +
                    "on pd.id=i.idPedido INNER JOIN cliente as c " +
                    "on pd.idCliente=c.id WHERE pd.id=" + id, null);
            if (c.moveToFirst()) {
                do {
                    ItemPedido itemPedido = new ItemPedido();

                    itemPedido.setId(Integer.parseInt(c.getString(0)));
                    itemPedido.setIdPedido(Integer.parseInt(c.getString(1)));
                    itemPedido.setVlrUnitProduto(Double.parseDouble(c.getString(2)));
                    itemPedido.setDesconto(Double.parseDouble(c.getString(3)));
                    itemPedido.setQtdeProduto(Double.parseDouble(c.getString(4)));
                    itemPedido.setIdProduto(Integer.parseInt(c.getString(5)));

                    lista.add(itemPedido);
                } while (c.moveToNext());
            }
            c.close();
            return lista;
        } finally {
            db.close();
        }
    }

    public List<Pedido> carregarPedidoRepresentanteDB() {
        SQLiteDatabase db = mobileDB.getWritableDatabase();

        int representante = 1;
        List<Pedido> lista = new ArrayList<>();

        try {
            Cursor c = db.rawQuery("SELECT * FROM pedido as pd INNER JOIN cliente as c\n" +
                    "on pd.idCliente=c.id WHERE pd.representanteId =" + representante, null);
            if (c.moveToFirst()) {
                do {
                    Pedido pedido = new Pedido();

                    pedido.setId(Integer.parseInt(c.getString(0)));
                    pedido.setRepresentanteId(Integer.parseInt(c.getString(2)));
                    pedido.setValorTotal(c.getString(3) != null ? Double.parseDouble(c.getString(3)) : 0d);
                    pedido.setFormaPgto(c.getString(4));
                    pedido.setObs(c.getString(5));
                    pedido.setIdCliente(Integer.parseInt(c.getString(6)));
                    pedido.setDescontoTotal(c.getString(7) != null ? Double.parseDouble(c.getString(7)) : 0d);
                    pedido.setStatus(c.getString(8).equals("1"));
                    Date d = sdfDiaMesAno.parse(c.getString(9));
                    pedido.setUltAtt(d);

                    lista.add(pedido);
                } while (c.moveToNext());
            }
            c.close();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return lista;
    }

    public long alterarPedido(Pedido pedido) {
        SQLiteDatabase db = mobileDB.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("representanteId", pedido.getRepresentanteId());
            values.put("valorTotal", pedido.getValorTotal());
            values.put("formaPgto", pedido.getFormaPgto());
            values.put("obs", pedido.getObs());
            values.put("status", pedido.getStatus());
            long id = db.update("pedido", values, "id=?", new String[]{String.valueOf(findMyIdPedido())});
            return id;
        } finally {
            db.close();
        }
    }

    public Produto findProduto(Integer id) {
        SQLiteDatabase db = mobileDB.getWritableDatabase();
        Produto p = new Produto();
        try {
            Cursor c = db.rawQuery("SELECT * FROM produto WHERE id = ?", new String[]{id.toString()});
            if (c != null) {
                if (c.moveToFirst()) {
                    p.setId(c.getInt(0));
                    p.setDescricao(c.getString(1));
                    p.setPrecoVenda(c.getString(2));
                    p.setSaldoEstoq(c.getString(3));
                }
            }
            c.close();

        } catch (Exception ex) {

        }
        return p;
    }

//    public Cliente findCliente(Integer id) {
//        SQLiteDatabase db = mobileDB.getWritableDatabase();
//        Cliente cliente = new Cliente();
//        try {
//            Cursor c = db.rawQuery("SELECT * FROM cliente WHERE id = ?", new String[]{id.toString()});
//            if (c != null) {
//                if (c.moveToFirst()) {
//                    cliente.setId(c.getInt(0));
//                    cliente.setNome(c.getString(1));
//                    cliente.setCadNacional(c.getString(2));
//                }
//            }
//            c.close();
//
//        } catch (Exception ex) {
//
//        }
//        return cliente;
//    }

    public Pedido findPedido(Integer id) {
        SQLiteDatabase db = mobileDB.getWritableDatabase();
        Pedido pedido = new Pedido();
        try {
            Cursor c = db.rawQuery("SELECT * FROM pedido WHERE id = ?", new String[]{id.toString()});
            if (c != null) {
                if (c.moveToFirst()) {
                    pedido.setId(c.getInt(0));
                    pedido.setRepresentanteId(Integer.parseInt(c.getString(2)));
                    pedido.setValorTotal(Double.parseDouble(c.getString(3)));
                    pedido.setFormaPgto(c.getString(4));
                    pedido.setObs(c.getString(5));
                    pedido.setIdCliente(Integer.parseInt(c.getString(6)));
                    pedido.setDescontoTotal(Double.parseDouble(c.getString(7)));

                }
            }
            c.close();

        } catch (Exception ex) {

        }
        return pedido;
    }


}
