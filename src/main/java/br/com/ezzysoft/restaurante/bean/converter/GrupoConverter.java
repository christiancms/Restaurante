package br.com.ezzysoft.restaurante.bean.converter;

import br.com.ezzysoft.restaurante.dao.GrupoDAO;
import br.com.ezzysoft.restaurante.entidade.Grupo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@ManagedBean
@ViewScoped
@FacesConverter(forClass = Grupo.class, value = "grupoConverter")
public class GrupoConverter implements Converter{
    
    GrupoDAO grupoDAO = new GrupoDAO();
    
    
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String valor) {
        if (valor == null || valor.isEmpty()) {
            return null;
        }else {
            Long id = Long.parseLong(valor);
            Grupo grupo = grupoDAO.findById(id);
            return grupo;
        }
    }

    
    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object valor) {
        Grupo grupo = (Grupo)valor;
        if (grupo != null) {
            return String.valueOf(grupo.getId());
        }else {
            return null;
        }
    }
}
