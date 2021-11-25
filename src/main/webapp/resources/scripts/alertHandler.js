const SUCCESS_ALERT = "";

const X_RANGE_ALERT = "Параметр X задается числом в промежутке (-5...3)!";
const X_FORM_ALERT = "Параметр X задается числом!";
const X_EXIST_ALERT = "Не введён параметр X!";

const Y_RANGE_ALERT = "Параметр Y задается числом в промежутке (-5...3)!";
const Y_FORM_ALERT = "Параметр Y задается числом!";
const Y_EXIST_ALERT = "Не введён параметр Y!";

const R_RANGE_ALERT = "Параметр R задается числом в промежутке [2...5]!";
const R_FORM_ALERT = "Параметр R задается числом!";
const R_EXIST_ALERT = "Не введён параметр R!";

injectFormAlerts = (comp1, comp2, comp3) => {
    return injectXAlert(comp1) | injectYAlert(comp2) | injectRAlert(comp3);
}

injectXAlert = (component) => {
    $("#X_error").html(xAlerts(component.value));
    return (xAlerts(component.value) !== SUCCESS_ALERT)
}

injectYAlert = (component) => {
    $("#Y_error").html(yAlerts(component.value));
    return (yAlerts(component.value) !== SUCCESS_ALERT);
}

injectRAlert = (component) => {
    $("#R_error").html(rAlerts(component.value))
    return (rAlerts(component.value) !== SUCCESS_ALERT);
}

xAlerts = (field) => {
    if (validateTextExist(field)) {
        if (validateTextForm(field)) {
            if (validateYRange(field)) {
                return SUCCESS_ALERT;
            } else return X_RANGE_ALERT;
        } else return X_FORM_ALERT;
    } else return X_EXIST_ALERT;
}

yAlerts = (field) => {
    if (validateTextExist(field)) {
        if (validateTextForm(field)) {
            if (validateYRange(field)) {
                return SUCCESS_ALERT;
            } else return Y_RANGE_ALERT;
        } else return Y_FORM_ALERT;
    } else return Y_EXIST_ALERT;
}

rAlerts = (field) => {
    if (validateTextExist(field)) {
        if (validateTextForm(field)) {
            if (validateRRange(field)) {
                return SUCCESS_ALERT;
            } else return R_RANGE_ALERT;
        } else return R_FORM_ALERT;
    } else return R_EXIST_ALERT;
}