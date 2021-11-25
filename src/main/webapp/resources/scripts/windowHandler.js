window.onload = () => {
    {drawPlot()} //draw plot on start

    $('#plot').on("click", (e) => {
        clickPointEvent(e);// draw and add point
    });

    document.getElementById("form:X_value").oninput = (e) => {
        console.log("X Input detected!")
        injectXAlert(e.target);
    };

    document.getElementById("form:Y_value").oninput = (e) => {
        console.log("Y Input detected!")
        injectYAlert(e.target);
    };

    document.getElementById("form:R_value").oninput = (e) => {
        console.log("R Input detected!")
        injectRAlert(e.target);
    };

    $('#cleaner').on("click", () => {
        cleanPlot();
    });
}

processSubmit = () => {
    if (!injectFormAlerts(
        document.getElementById("form:X_value"),
        document.getElementById("form:Y_value"),
        document.getElementById("form:R_value")
    ))
        addHit();
}