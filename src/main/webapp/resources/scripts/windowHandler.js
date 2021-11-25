window.onload = () => {
    {
        drawPlot()
    }

    $('#plot').on("click", (e) => {
        let x = convertToCoordinatesX(e.pageX - 99.5);
        let y = convertToCoordinatesY(e.pageY - 175);
        console.log(x + " " + y);
        addClick([{name: 'x', value: x}, {name: 'y', value: y}]);
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