package br.com.ezzysoft.restaurante.bean;

import br.com.ezzysoft.restaurante.dao.GrupoDAO;
import br.com.ezzysoft.restaurante.entidade.Grupo;
import br.com.ezzysoft.restaurante.util.exception.ErroSistema;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name = "MBGrupo")
@SessionScoped
public class MBGrupo extends CrudBean<Grupo, GrupoDAO> implements Serializable{

    private GrupoDAO grupoDAO;
    private byte[] imagem;
    private String nomeFoto;
    private UploadedFile arquivo;
    private StreamedContent verArquivo;

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

    public StreamedContent doDownload() throws ErroSistema{
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String grupoId = context.getExternalContext().getRequestParameterMap().get("grupoId");
            Grupo grupo = new Grupo();
            return new DefaultStreamedContent(new ByteArrayInputStream(grupo.getFoto()));
        }
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

    public StreamedContent getVerArquivo() {
        return verArquivo;
    }

    public void setVerArquivo(StreamedContent verArquivo) {
        this.verArquivo = verArquivo;
    }
}
