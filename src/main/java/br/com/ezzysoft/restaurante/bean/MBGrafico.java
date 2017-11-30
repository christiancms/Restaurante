package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.ProdutoDAO;
import br.com.ezzysoft.restaurante.entidade.Pedido;
import br.com.ezzysoft.restaurante.entidade.Produto;
import br.com.ezzysoft.restaurante.facade.PedidoFacade;
import br.com.ezzysoft.restaurante.facade.ProdutoFacade;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.chart.PieChartModel;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author christian
 */
@ManagedBean(name = "mbGrafico")
@RequestScoped
public class MBGrafico implements Serializable {

    @EJB
    private ProdutoFacade ejbFacade;
    @EJB
    private PedidoFacade facadePedido;

    private PieChartModel pieModel;
    private PieChartModel pieModel2;
    List<Produto> produtosList = new ArrayList<>();
    List<Pedido> pedidosList = new ArrayList<>();

    public ProdutoFacade getFacade() {
        return ejbFacade;
    }

    public PedidoFacade getFacadePedido() {
        return facadePedido;
    }

    @PostConstruct
    public void init() {
        createPieModel();
        createPieModel2();
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    private void createPieModel() {
        try {
            produtosList = getFacade().buscarGrafico();
            Map graf = new HashMap();
            Integer count = 0;

            if (!produtosList.isEmpty()) {
                pieModel = new PieChartModel();
                for (Produto elem : produtosList) {
                    if (graf.containsKey(elem.getMarca().getDescricao())) {
                        count = (Integer) graf.get(elem.getMarca().getDescricao());
                        graf.put(elem.getMarca().getDescricao(), count+1);

                    } else {
                        graf.put(elem.getMarca().getDescricao(), 1);
                    }

                }
                pieModel.setData(graf);
                pieModel.setTitle("Venda de Produtos por Marca");
                pieModel.setLegendPosition("e");
                pieModel.setFill(false);
                pieModel.setShowDataLabels(true);
                pieModel.setDiameter(250);
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(MBGrafico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createPieModel2() {
        try {
            pedidosList = getFacadePedido().buscarGrafico();
            Map graf = new HashMap();
            Integer count = 0;

            if (!pedidosList.isEmpty()) {
                pieModel2 = new PieChartModel();
                for (Pedido elem : pedidosList) {
                    if (graf.containsKey(elem.getStatus().getOpcao())) {
                        count = (Integer) graf.get(elem.getStatus().getOpcao());
                        graf.put(elem.getStatus().getOpcao(), count+1);

                    } else {
                        graf.put(elem.getStatus().getOpcao(), 1);
                    }

                }
                pieModel2.setData(graf);
                pieModel2.setTitle("Vendas/Pedidos por Status");
                pieModel2.setLegendPosition("e");
                pieModel2.setFill(false);
                pieModel2.setShowDataLabels(true);
                pieModel2.setDiameter(250);
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(MBGrafico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
