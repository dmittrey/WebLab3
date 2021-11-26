window.onload = () => {

    let promise = getDots();
    promise.then(function (responseData) {
        let dots = responseData.jqXHR.pfArgs.dotsJSON;
        console.log("dotJSON request successful!");
        if (dots !== undefined) {
            resetDots(JSON.parse(dots));
            switchRadius();
        } else drawPlot();
    }).catch(function (error) {
        console.error("Request failed", error);
    });

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