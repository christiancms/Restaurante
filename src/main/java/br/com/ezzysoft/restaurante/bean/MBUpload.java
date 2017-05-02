package br.com.ezzysoft.restaurante.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Christian Medeiros <christian.souza@gmail.com>
 */

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
}
