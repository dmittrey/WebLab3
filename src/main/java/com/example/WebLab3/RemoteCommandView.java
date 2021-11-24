package com.example.WebLab3;

import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class RemoteCommandView {

    public void execute() {
        String param1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param1");
        String param2 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param2");
        if (param1 != null || param2 != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", "param1: " + param1 + ", param2: " + param2));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", "Using RemoteCommand."));
        }

        PrimeFaces.current().ajax().addCallbackParam("serverTime", System.currentTimeMillis());
    }
}
