package br.com.ezzysoft.restaurante.ws.service;

import br.com.ezzysoft.restaurante.entidade.Cliente;
import br.com.ezzysoft.restaurante.entidade.Colaborador;
import br.com.ezzysoft.restaurante.entidade.ItemPedido;
import br.com.ezzysoft.restaurante.entidade.Pedido;
import br.com.ezzysoft.restaurante.entidade.Produto;
import br.com.ezzysoft.restaurante.ws.ItemTransporter;
import br.com.ezzysoft.restaurante.ws.PedidoTransporter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author christian
 */
@Stateless
@Path("/pedido")
public class PedidoService extends AbstractFacade<Pedido> {

    @EJB
    private ColaboradorService colaboradorFacade;
    @EJB
    private ClienteService clienteFacade;
    @EJB
    private ProdutoService produtoFacade;
    @EJB
    private ItenspedidoService itemFacade;

    @PersistenceContext(unitName = "EzzysoftPU")
    private EntityManager em;

    public PedidoService() {
        super(Pedido.class);
    }

    @POST
    @Path("/create")
    @Override
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Pedido entity) {
        super.create(entity);
    }

    @POST
    @Path("/cria")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public String setPedido(String json) {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=");
        System.out.println("Json: " + json);
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=");

        String ret = "";
        if (!json.isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                PedidoTransporter pedT = mapper.readValue(json, PedidoTransporter.class);
                createPedidoWS(pedT);
                Logger.getLogger(PedidoService.class.getName()).log(Level.SEVERE, null, String.format("Json Recebido [%s]", json));

                return ret = json;
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro lançado no Glassfish";
            }
        }
        return ret = json;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Pedido entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Pedido find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @POST
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPedidos() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfh = new SimpleDateFormat("HH:mm:ss");
        List<Pedido> lista = super.findAll();
        ObjectMapper mapper = new ObjectMapper();
        List<ItemPedido> itens;
        PedidoTransporter pt;
        ItemTransporter it;
        List<ItemTransporter> itenspedido = new ArrayList<>();

        String dados = "{\"pedidos\":[";
        if (!lista.isEmpty()) {
            for (Pedido elem : lista) {
                pt = new PedidoTransporter();
                itens = new ArrayList<>();

                pt.setIdPedido(elem.getId());
                pt.setClienteId(elem.getCliente().getId());
                pt.setColaboradorId(elem.getColaborador().getId());
                pt.setDataPedido(df.format(new Date()));//elem.getDataPedido().toString());
                pt.setHoraPedido(dfh.format(new Date()));//elem.getHoraPedido().toString());
                pt.setMesa(elem.getMesa());
                pt.setDescricao(elem.getDescricao());

                itens = elem.getItensPedido();
                itenspedido = new ArrayList<>();
                for (ItemPedido elemento : itens) {
                    if (Objects.equals(elem.getId(), elemento.getPedido())) {
                        it = new ItemTransporter();
                        it.setIdItem(elemento.getId());
                        it.setPedidoId(elemento.getPedido().getId());
                        it.setProdutoId(elemento.getProduto().getId());
                        it.setQuantidade(elemento.getQuantidade());
                        itenspedido.add(it);
                    }
                }
                pt.setItensTransporter(itenspedido);
                try {
                    dados += mapper.writeValueAsString(pt);
                    dados += ", ";
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(PedidoService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dados = dados.substring(0, dados.length() - 2);
        }
        return dados + "]}";
    }

    @POST
    @Path("/list")
    @Override
    @Produces(MediaType.APPLICATION_XML)
    public List<Pedido> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pedido> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public String createPedidoWS(PedidoTransporter pedT) throws ParseException {
        String retValidate = validaPedidoWS(pedT);
        if ("ok".equals(retValidate)) {
            fillPedidoWeb(pedT);
        }
        return retValidate;
    }

    private String validaPedidoWS(PedidoTransporter pedT) {

        if (pedT.getItensTransporter().isEmpty()) {
            return "Pedido " + pedT.getIdPedido() + " sem itens!";
        }
//
//        if (pedT.getItensTransporter() != null && !pedT.getItensTransporter().isEmpty()) {
//            String prodRet = "";
//            for (ItemTransporter prod : pedT.getItensTransporter()) {
//                if (prod.getCodigoProduto() == null) {
//                    prodRet += "Produto: " + prod.getNum() + " sem código do produto no APHERP!";
//                } else {
//                    Produto produto = (Produto) facadeGeneric.find(prod.getCodigoProduto(), Produto.class);
//                    if (produto == null) {
//                        prodRet += "Produto " + prod.getCodigoProduto()
//                                + " não encontrado no APHERP para o código do produto no Romaneio " + prod.getNum();
//                    }
//                }
//            }
//            if (!prodRet.isEmpty()) {
//                return prodRet;
//            }
//        }
        return "ok";
    }

    private Pedido fillPedidoWeb(PedidoTransporter pedT) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
        Pedido ped = new Pedido();
        Cliente cli;
        Colaborador colab;
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        Date convrtData  = (Date) sdf.parse(pedT.getDataPedido());
//        java.sql.Date sql = new java.sql.Date(convrtData);
//        ped.setDataPedido((java.sql.Date) );
//java.sql.Date sqlDate = java.sql.Date.valueOf(convrtData);
        if (pedT.getClienteId() > 0) {
            cli = clienteFacade.find(pedT.getClienteId());
            ped.setCliente(cli);
        } else {

            ped.setCliente(new Cliente("Diversos"));
        }
        if (pedT.getColaboradorId() > 0) {
            colab = colaboradorFacade.find(pedT.getColaboradorId());
            ped.setColaborador(colab);
        } else {
            ped.setColaborador(new Colaborador(1l, "PDV"));
        }
        ped.setDescricao(pedT.getDescricao().trim().equalsIgnoreCase("") ? "AppMobile" : pedT.getDescricao());
        ped.setMesa(pedT.getMesa());
        try {
            String dataAtual = sdf.format(new Date());
            String horaAtual = shf.format(new Date());
            ped.setDataPedido(sdf.parse(dataAtual));
            ped.setHoraPedido(shf.parse(horaAtual));
        } catch (Exception e) {
        }

        create(ped);

        fillItensPedido(ped, pedT);
        return ped;

    }

    private Pedido fillItensPedido(Pedido ped, PedidoTransporter pedT) {
        ItemPedido item;

        Produto prod = new Produto();
        List<ItemPedido> lista = new ArrayList<>();

        for (ItemTransporter itemT : pedT.getItensTransporter()) {

            item = new ItemPedido();
            item.setPedido(ped);

            prod = (Produto) produtoFacade.find(itemT.getProdutoId());
            item.setProduto(prod);
            item.setQuantidade(itemT.getQuantidade());
            itemFacade.create(item);
        }
        ped.setItensPedido(lista);
        return ped;
    }

    @Override
    public int findMaxId() {
        return super.findMaxId();
    }
}
