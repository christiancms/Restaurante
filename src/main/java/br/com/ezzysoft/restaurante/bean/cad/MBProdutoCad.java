package br.com.ezzysoft.restaurante.bean.cad;

import br.com.ezzysoft.restaurante.bean.InterfaceCad;
import br.com.ezzysoft.restaurante.dao.GrupoDAO;
import br.com.ezzysoft.restaurante.entidade.Grupo;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBProdutoCad")
@SessionScoped
public class MBProdutoCad implements InterfaceCad {

    private List<SelectItem> gruposSelect;
    
    @Override
    public void cadOnLoad() {
    }

    @Override
    public void cadOpen() {
    }

    @Override
    public void cadRemove() {
    }

    @Override
    public void cadSave() {
    }

    @Override
    public boolean cadValidate() {
        return true;
    }

    public List<SelectItem> getGruposSelect() {
        if (this.gruposSelect == null) {
            GrupoDAO grupodao = new GrupoDAO();
            List<Grupo> listaGrupos = grupodao.listAll();
            
            if (listaGrupos != null && !listaGrupos.isEmpty()) {
                SelectItem item;
                for (Grupo grupoLista : listaGrupos) {
                   // item = new SelectItem(grupoLista, listaGrupos.get)
                }
            }
            
        }
        return gruposSelect;
    }

}
