function showDateTime() {
    let date = new Date();
    let dateTime = {
        year: date.getFullYear(),
        month: date.toLocaleString('default', {month: 'short'}),
        day: date.getDay(),
        hour: (date.getHours() === 0) ? 12 : date.getHours(), // 0 - 23
        min: date.getMinutes(), // 0 - 59
        seg: date.getSeconds(), // 0 - 59
    }
    let session = "AM";

    if(dateTime.hour > 12){
        dateTime.hour = dateTime.hour - 12;
        session = "PM";
    }

    for (const i in dateTime) {
        if (i === "month") continue;
        let v = dateTime[i];
        dateTime[i] = (v < 10) ? "0" + v : v;
    }

    const dateStr = dateTime.day + "/" + dateTime.month + "/" + dateTime.year;
    const timeStr = dateTime.hour + ":" + dateTime.min + ":" + dateTime.seg + " " + session;
    document.getElementById("dateTimePrinter").innerText = dateStr + "\n" + timeStr;
    document.getElementById("dateTimePrinter").textContent = dateStr + "\n" + timeStr;

    setTimeout(showDateTime, 6000);
}

showDateTime();