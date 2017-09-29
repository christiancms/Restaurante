package br.com.ezzysoft.restaurante.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */
@ManagedBean(name="MBUploadFoto")
@ApplicationScoped
public class MBUpload {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        try {
            if (file != null) {

                file.getInputstream();
                FacesMessage msg = new FacesMessage("", file.getFileName() + "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Erro");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    private StreamedContent image = null;

    public StreamedContent getImage() {
        return image;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    public void enterImage(FileUploadEvent e) {
        try {
            UploadedFile file = e.getFile();
            image = new DefaultStreamedContent(file.getInputstream(), "images/grupos");
        } catch (Exception ex) {
            System.out.println("hata:" + ex.getMessage());
        }
    }

}
