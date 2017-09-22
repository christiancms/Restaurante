package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.GrupoDAO;
import br.com.ezzysoft.restaurante.entidade.Grupo;
import br.com.ezzysoft.restaurante.util.Util;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBGrupo")
//@SessionScoped
@ApplicationScoped
public class MBGrupo extends CrudBean<Grupo, GrupoDAO> implements Serializable {

    private GrupoDAO grupoDAO;
    private byte[] imagem;
    private String nomeFoto;
    private UploadedFile arquivo;
    Util util;

    public UploadedFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(UploadedFile arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public GrupoDAO getDao() {
        if (grupoDAO == null) {
            grupoDAO = new GrupoDAO();
        }
        return grupoDAO;
    }

    @Override
    public Grupo criarNovaEntidade() {
        return new Grupo();
    }

    public void doUpload(FileUploadEvent event) throws ErroSistema {
        setArquivo(event.getFile());
        setNomeFoto(arquivo.getFileName());
        setImagem(arquivo.getContents());
        this.getEntidade().setFoto(getImagem());
    }

    public StreamedContent getPicture() throws ErroSistema, IOException {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (id != null && grupoDAO.buscar() != null && !grupoDAO.buscar().isEmpty()) {
            Long imagemId = Long.parseLong(id);
            for (Grupo g : grupoDAO.buscar()) {
                if (Objects.equals(g.getId(), imagemId)) {
                    return new DefaultStreamedContent(new ByteArrayInputStream(g.getFoto()));
                }
            }
        }
        return new DefaultStreamedContent();
    }

    public String getNomeFoto() {
        return nomeFoto;
    }

    public void setNomeFoto(String nomeFoto) {
        this.nomeFoto = nomeFoto;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public GrupoDAO getGrupoDAO() {
        return grupoDAO;
    }

    public void setGrupoDAO(GrupoDAO grupoDAO) {
        this.grupoDAO = grupoDAO;
    }

}
