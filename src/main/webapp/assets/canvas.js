
let canvasCtx = $('#graph-canvas')[0].getContext('2d');
const width = canvasCtx.canvas.width;
const height = canvasCtx.canvas.height;
let R = height / 3;

// side boundaries of the logical viewport
const maxX = 10;
const minX = -10;
const maxY = maxX * height / width;
const minY = minX * height / width

// Returns the physical x-coordinate of a logical x-coordinate:
function getPhysicalX(x) {
    return (x - minX) / (maxX - minX) * width ;
}

// Returns the physical y-coordinate of a logical y-coordinate:
function getPhysicalY(y) {
    return height - (y - minY) / (maxY - minY) * height ;
}


/*
* Taking a life the canvas
* */
function drawCanvas() {
    if (!canvasCtx) {
        alert('your browser doesn\'t support canvas');
        return;
    }

    canvasCtx.clearRect(0, 0, width, height);
    canvasCtx.font='8px sans-serif';
    canvasCtx.strokeStyle = "rgba(255,255,255,0.8)";
    canvasCtx.fillStyle = "rgba(255,255,255,0.8)";

    //circle on the right down
    canvasCtx.beginPath();
    canvasCtx.moveTo(getPhysicalX(0), getPhysicalY(0));
    canvasCtx.arc(getPhysicalX(0), getPhysicalY(0), R/2, Math.PI/2, 0, true);
    canvasCtx.closePath();
    canvasCtx.fill();
    canvasCtx.stroke();

    //square in the right up
    canvasCtx.fillRect(getPhysicalX(0), getPhysicalY(0) - R, R, R);

    //triangle
    canvasCtx.beginPath();
    canvasCtx.moveTo(getPhysicalX(0), getPhysicalY(0)-R);
    canvasCtx.lineTo(getPhysicalX(0), getPhysicalY(0));
    canvasCtx.lineTo(getPhysicalX(0)-R/2, getPhysicalY(0));
    canvasCtx.closePath();
    canvasCtx.fill();
    canvasCtx.stroke();


    //draw Axis
    const limitMargin = 15;
    canvasCtx.save();
    canvasCtx.strokeStyle = "black";
    canvasCtx.fillStyle = "black";

    // +Y axis
    canvasCtx.beginPath();
    canvasCtx.moveTo(getPhysicalX(0), getPhysicalY(0));
    canvasCtx.lineTo(getPhysicalX(0), getPhysicalY(maxY)+limitMargin);
    canvasCtx.stroke();

    // -Y axis
    canvasCtx.beginPath();
    canvasCtx.moveTo(getPhysicalX(0), getPhysicalY(0));
    canvasCtx.lineTo(getPhysicalX(0), getPhysicalY(minY)-limitMargin);
    canvasCtx.stroke();

    // +X axis
    canvasCtx.beginPath();
    canvasCtx.moveTo(getPhysicalX(0), getPhysicalY(0));
    canvasCtx.lineTo(getPhysicalX(maxX)+limitMargin, getPhysicalY(0));
    canvasCtx.stroke();

    // -X axis
    canvasCtx.beginPath();
    canvasCtx.moveTo(getPhysicalX(0), getPhysicalY(0));
    canvasCtx.lineTo(getPhysicalX(minX)-limitMargin, getPhysicalY(0));
    canvasCtx.stroke();

    // axis names and arrows
    canvasCtx.fillText('X', width - limitMargin, getPhysicalY(0) - 3)
    canvasCtx.fillText('Y', getPhysicalX(0) - 10, maxY + limitMargin)

    // drawing tick marks
    let valR = $('input[name="r"]:checked').val();
    const startTickX = width / 1.95, finishTickX = width / 2.05;
    const startTickY = height / 1.9, finishTickY = height / 2.1;

    // Y axis tick marks
    canvasCtx.fillText(-valR/2, width / 2.05+8, getPhysicalY(0) + R / 2+2)
    canvasCtx.fillText(-valR, width / 2.05+8, getPhysicalY(0) + R+2)
    canvasCtx.fillText(valR/2, width / 2.05+8, getPhysicalY(0) - R / 2+2)
    canvasCtx.fillText(valR, width / 2.05+8, getPhysicalY(0) - R+2)
    canvasCtx.beginPath();
    canvasCtx.moveTo(startTickX, getPhysicalY(0) + R);
    canvasCtx.lineTo(finishTickX, getPhysicalY(0) + R);
    canvasCtx.stroke();
    canvasCtx.beginPath();
    canvasCtx.moveTo(startTickX, getPhysicalY(0) + R / 2);
    canvasCtx.lineTo(finishTickX, getPhysicalY(0) + R / 2);
    canvasCtx.stroke();
    canvasCtx.beginPath();
    canvasCtx.moveTo(startTickX, getPhysicalY(0) - R);
    canvasCtx.lineTo(finishTickX, getPhysicalY(0) - R);
    canvasCtx.stroke();
    canvasCtx.beginPath();
    canvasCtx.moveTo(startTickX, getPhysicalY(0) - R / 2);
    canvasCtx.lineTo(finishTickX, getPhysicalY(0) - R / 2);
    canvasCtx.stroke();

    // X tick marks
    canvasCtx.fillText(-valR/2, getPhysicalX(0) - R / 2-6, height / 2.2)
    canvasCtx.fillText(-valR, getPhysicalX(0) - R-3, height / 2.2)
    canvasCtx.fillText(valR/2, getPhysicalX(0) + R / 2-6, height / 2.2)
    canvasCtx.fillText(valR, getPhysicalX(0) + R-3, height / 2.2)
    canvasCtx.beginPath();
    canvasCtx.moveTo(getPhysicalX(0) + R, startTickY);
    canvasCtx.lineTo(getPhysicalX(0) + R, finishTickY);
    canvasCtx.stroke();
    canvasCtx.beginPath();
    canvasCtx.moveTo(getPhysicalX(0) + R / 2, startTickY);
    canvasCtx.lineTo(getPhysicalX(0) + R / 2, finishTickY);
    canvasCtx.stroke();
    canvasCtx.beginPath();
    canvasCtx.moveTo(getPhysicalX(0) - R, startTickY);
    canvasCtx.lineTo(getPhysicalX(0) - R, finishTickY);
    canvasCtx.stroke();
    canvasCtx.beginPath();
    canvasCtx.moveTo(getPhysicalX(0) - R / 2, startTickY);
    canvasCtx.lineTo(getPhysicalX(0) - R / 2, finishTickY);
    canvasCtx.stroke();

    drawSavedPoints();
}



function getPointsByRows() {
    let myRows = [];
    $('#results-table tbody tr').each(function (index) {
        myRows[index] = {};
        $(this).find("td").each(function (cellIndex) {
            myRows[index][$($("th")[cellIndex]).html()] = $(this).html();
        });
    });
    return myRows;
}

function drawSavedPoints() {
    let valR = $('input[name="r"]:checked').val();
    let savedTablePoints = getPointsByRows();
    if (savedTablePoints == null)
        return;

    for (const i in savedTablePoints) {
        let color = (savedTablePoints[i].RESULT == "true") ? "green" : "red";
        drawPoint(savedTablePoints[i].X, savedTablePoints[i].Y, valR, color);
    }
}



function drawPoint(x, y, r, color = "red") {
    let R = height / 3 / r;

    canvasCtx.beginPath();
    canvasCtx.moveTo((width / 2) + R * x, (height / 2) - R * y);
    canvasCtx.arc((width / 2) + R * x, (height / 2) - R * y, width/300,0,2*Math.PI);
    canvasCtx.closePath();
    canvasCtx.strokeStyle = color;
    canvasCtx.fillStyle = color;
    canvasCtx.fill();
    canvasCtx.stroke();
}


$('#graph-canvas').click(function (event) {
    try {
        let valR = $('input[name="r"]:checked').val();
        let physicR = height / 3 / valR;
        const clickedX = (getMousePos(event).X - width/2) / physicR;
        const clickedY = (-getMousePos(event).Y + height/2) / physicR;

        $('input[name="x"]')
            .append(`<div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="${clickedX}" name="x" value="${clickedX}" checked>
                    <label class="form-check-label" for="${clickedX}">${clickedX}</label>
                </div>`);
        $("input#y").val(clickedY);
        $('button#submit-btn').click();
        drawPoint(clickedX, clickedY, valR);
    } catch (e) {
        console.log(e.toLocaleString());
    }
});

function getMousePos(evt) {
    let rect = $('#graph-canvas')[0].getBoundingClientRect();
    return {
        X: 300*(evt.clientX - rect.left)/rect.width,
        Y: 150*(evt.clientY - rect.top)/rect.height
    };

}