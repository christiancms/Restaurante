package br.com.ezzysoft.restaurante.ws.service;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author christian
 */
@ApplicationPath("/ws")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.com.ezzysoft.restaurante.ws.service.ClienteService.class);
        resources.add(br.com.ezzysoft.restaurante.ws.service.ColaboradorService.class);
        resources.add(br.com.ezzysoft.restaurante.ws.service.GrupoService.class);
        resources.add(br.com.ezzysoft.restaurante.ws.service.ItenspedidoService.class);
        resources.add(br.com.ezzysoft.restaurante.ws.service.MarcaService.class);
        resources.add(br.com.ezzysoft.restaurante.ws.service.PedidoService.class);
        resources.add(br.com.ezzysoft.restaurante.ws.service.ProdutoService.class);
        resources.add(br.com.ezzysoft.restaurante.ws.service.UnidadeService.class);
    }
    
}

