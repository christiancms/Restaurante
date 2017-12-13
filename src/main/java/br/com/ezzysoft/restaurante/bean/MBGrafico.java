package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.ProdutoDAO;
import br.com.ezzysoft.restaurante.entidade.Mesa;
import br.com.ezzysoft.restaurante.entidade.Pedido;
import br.com.ezzysoft.restaurante.entidade.Produto;
import br.com.ezzysoft.restaurante.facade.MesaFacade;
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

import org.primefaces.model.chart.MeterGaugeChartModel;
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
    @EJB
    private MesaFacade facadeMesa;

    private PieChartModel pieModel;
    private PieChartModel pieModel2;
    private MeterGaugeChartModel meterGaugeModel2;

    List<Produto> produtosList = new ArrayList<>();
    List<Pedido> pedidosList = new ArrayList<>();
    List<Mesa> mesasList = new ArrayList<>();

    public ProdutoFacade getFacade() {
        return ejbFacade;
    }

    public PedidoFacade getFacadePedido() {
        return facadePedido;
    }

    public MesaFacade getFacadeMesa(){ return facadeMesa; }

    @PostConstruct
    public void init() {
        createPieModel();
        createPieModel2();
        createMeterGaugeModels();
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    private void createMeterGaugeModels() {

        meterGaugeModel2 = initMeterGaugeModel();
        meterGaugeModel2.setTitle("Mesas Ocupadas");
        meterGaugeModel2.setSeriesColors("66cc66,E7E658,cc6666");
        meterGaugeModel2.setGaugeLabel("");
//        meterGaugeModel2.setGaugeLabelPosition("bottom");
//        meterGaugeModel2.setShowTickLabels(false);
//        meterGaugeModel2.setLabelHeightAdjust(110);
//        meterGaugeModel2.setIntervalOuterRadius(100);
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
                pieModel.setSeriesColors("FFA500,008000,0000FF,FF0000,FFFF00,FFC0CB");
                pieModel.setLegendPosition("e");
                pieModel.setFill(true);
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
                pieModel2.setSeriesColors("FFA500,008000,0000FF,FF0000,FFFF00,FFC0CB");
                pieModel2.setLegendPosition("e");
                pieModel2.setFill(true);
                pieModel2.setShowDataLabels(true);
                pieModel2.setDiameter(250);
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(MBGrafico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MeterGaugeChartModel getMeterGaugeModel2() {
        return meterGaugeModel2;
    }


    private MeterGaugeChartModel initMeterGaugeModel() {
        int total = getFacadeMesa().findAll().size();
        int ocupadas = getFacadeMesa().buscarGrafico().size();
        Double verde,amarelo,vermelho;
        verde = total * 0.35;
        amarelo = total * 0.7;

        vermelho = total * 1.0;
        int livre = Math.toIntExact(Math.round(verde));
        int intermediario = Math.toIntExact(Math.round(amarelo));
        int critico = Math.toIntExact(Math.round(vermelho));

        List<Number> intervals = new ArrayList<Number>(){{
            add(livre);
            add(intermediario);
            add(critico);
        }};

        return new MeterGaugeChartModel(ocupadas, intervals);
    }
}
