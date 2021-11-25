window.onload = () => {
    {
        drawPlot()
    }
    //
    // $("#plot").on("click", (e) => {
    //     let coordinates = {};
    //     coordinates.x = convertToCoordinatesX(e.pageX - 99.5);
    //     coordinates.y = convertToCoordinatesY(e.pageY - 175);
    //     $("#clickX").val(coordinates.x);
    //     $("#clickY").val(coordinates.y);
    //     $("#clickSubmit").click();
    //     console.log(coordinates.x + " " + coordinates.y);
    // });

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