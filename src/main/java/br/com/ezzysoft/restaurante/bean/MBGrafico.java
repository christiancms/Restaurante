package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.ProdutoDAO;
import br.com.ezzysoft.restaurante.entidade.Produto;
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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author christian
 */
@ManagedBean(name = "MBGrafico")
@RequestScoped
public class MBGrafico implements Serializable {

    private PieChartModel pieModel;
    ProdutoDAO facadeProduto = new ProdutoDAO();
    List<Produto> produtosList = new ArrayList<>();

    @PostConstruct
    public void init() {
        createPieModel();
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    private void createPieModel() {
        try {
            produtosList = facadeProduto.buscarGrafico();
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

}
