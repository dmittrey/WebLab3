const X_START = -5;
const X_END = 3;

const Y_START = -5;
const Y_END = 3;

const R_START = 2;
const R_END = 5

validateX = (x) => {
    console.log(validateTextExist(x) &&
        validateTextForm(x) &&
        validateYRange(x));
    return validateTextExist(x) &&
        validateTextForm(x) &&
        validateYRange(x);
}

validateY = (y) => {
    console.log(validateTextExist(y) &&
        validateTextForm(y) &&
        validateYRange(y));
    return validateTextExist(y) &&
        validateTextForm(y) &&
        validateYRange(y);
}

validateR = (r) => {
    console.log(validateTextExist(r) &&
        validateTextForm(r) &&
        validateRRange(r));
    return validateTextExist(r) &&
        validateTextForm(r) &&
        validateRRange(r);
}

validateTextExist = (field) => {
    return !jQuery.isEmptyObject(field) && (field.trim() !== "");
}

validateTextForm = (field) => {
    return (/^(0$|-?[0-9]\d*(\.\d*[0-9]$)?|-?0\.\d*[0-9])$/).test(field);
}

validateXRange = (field) => {
    return (Number(field) > X_START) && (Number(field) < X_END);
}

validateYRange = (field) => {
    return (Number(field) > Y_START) && (Number(field) < Y_END);
}

validateRRange = (field) => {
    return (Number(field) >= R_START) && (Number(field) <= R_END);
}