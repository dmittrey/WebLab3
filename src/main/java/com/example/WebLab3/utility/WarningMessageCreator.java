package com.example.WebLab3.utility;

import com.example.WebLab3.interfaces.MessageCreator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class WarningMessageCreator implements MessageCreator {

    @Override
    public void createMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Server validation", message));
    }
}
