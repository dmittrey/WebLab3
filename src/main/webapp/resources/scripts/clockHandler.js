const deg = 6;
const hr = $('.hr');
const mn = $('.mn');
const sc = $('.sc');

updateClock = () => {
    let day = new Date();
    let hh = day.getHours() * 5 * deg;
    let mm = day.getMinutes() * deg;
    let ss = day.getSeconds() * deg;

    // hr.css("transform", "rotateZ(" + hh + (mm / 12) + "deg)");
    hr.css("transform", "rotateZ(" + (mm / 12) + "deg)");
    mn.css("transform", "rotateZ(" + mm + "deg)");
    sc.css("transform", "rotateZ(" + ss + "deg)");
    console.log(day.getHours());
    console.log(hh);
    console.log(hh + (mm / 12));

}

updateClock()

setInterval(updateClock, 1000);