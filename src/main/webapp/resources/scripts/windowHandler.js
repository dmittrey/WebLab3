window.onload = () => {

    downloadDots();

    $('#plot').on("click", (e) => {
        clickPointEvent(e);// draw and add point
    });

    document.getElementById("form:X_value").oninput = (e) => {
        injectXAlert(e.target);
    };

    document.getElementById("form:Y_value").oninput = (e) => {
        injectYAlert(e.target);
    };

    document.getElementById("form:R_value").oninput = (e) => {
        injectRAlert(e.target);
    };

    $('#cleaner').on("click", () => {
        cleanPlot();
    });
}

downloadDots = () => {
    let promise = getDots();
    promise.then(function (responseData) {
        let dots = responseData.jqXHR.pfArgs.dotsJSON;
        console.log("dotJSON request successful!");
        if (dots !== undefined) {
            updateTable();
            resetDots(JSON.parse(dots));
            switchRadius();
        } else drawPlot();
    }).catch(function (error) {
        console.error("Request failed", error);
    });
}

processSubmit = () => {
    if (!injectFormAlerts(
        document.getElementById("form:X_value"),
        document.getElementById("form:Y_value"),
        document.getElementById("form:R_value")
    ))
        addHit();

    downloadDots();
}