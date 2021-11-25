window.onload = () => {
    {
        drawPlot()
    }

    $('#plot').on("click", (e) => {
        runCommand(e); // draw and add point
    });

// $(".X_value").on("click", (e) => {
//     let x = e.target;
//     if (validateX(x.value)) {
//         setXValue(x);
//     }
//     injectXAlert(x.value);
// });
//
// $('#Y_value').on("input", (e) => {
//     let y = e.target;
//     if (validateY(y.value)) {
//         setYValue(y);
//     }
//     injectYAlert(y.value);
// });

    document.getElementById("form:R_value").oninput = (e) => {
        console.log(document.getElementById("form:R_value").value);
    };

// $('#R_value').on("input", (e) => {
//     let r = e.target;
//     if (validateR(r.value)) {
//         setRValue(r);
//         switchRadius(getValues());
//     }
//     injectRAlert(r.value);
// });

    $('#cleaner').on("click", () => {
        cleanPlot();
        cleanTable();
        clearRequest();
    });

}