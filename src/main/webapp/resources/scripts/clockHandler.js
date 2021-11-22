const deg = 6;
const hr = $('.hr');
const mn = $('.mn');
const sc = $('.sc');

updateClock = () => {
    let day = new Date();
    let ss = day.getSeconds() * deg;
    let mm = day.getMinutes() * deg;
    let hh = day.getHours() * 30 + mm / 12;

    hr.css("transform", "rotateZ(" + hh + "deg)");
    mn.css("transform", "rotateZ(" + mm + "deg)");
    sc.css("transform", "rotateZ(" + ss + "deg)");
}

updateClock()

setInterval(updateClock, 1000);