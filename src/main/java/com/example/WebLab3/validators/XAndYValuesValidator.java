package com.example.WebLab3.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("xAndYValidator")
public class XAndYValuesValidator implements Validator<Double> {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Double aDouble) throws ValidatorException {
        if (aDouble <= -5 || aDouble >= 3)
            throw new ValidatorException(new FacesMessage());
    }
}
