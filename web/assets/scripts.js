
/*
* Filling the X and R inputs
* */
const valuesX = ['-3', '-2', '-1', '0', '1', '2', '3', '4', '5'];
const valuesR = ['1', '1.5', '2', '2.5', '3'];
for (const number of valuesX) {
    $('#options_x')
        .append(`<div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="${number}" name="x[]" value="${number}">
                    <label class="form-check-label" for="${number}">${number}</label>
                 </div>`);
}
for (const number of valuesR) {
    $('select#r').append(`<option value="${number}" ${(number==='1') ? 'selected' : ''}/>${number}</option>`);
}


/*
* validate input
* */
function validateRange(input, minRange) {
    let val = parseFloat(input);
    if (isNaN(input))
        return false;

    return (val >= minRange && val <= 3);
}

function validateInput() {
    let validX = $('input:checkbox').filter(':checked').length > 0;
    let validY = validateRange($('input#y').val(), -3);
    let validR = validateRange($('select#r option:selected').val(), 1);

    $('#submit-btn').attr('disabled', !(validX && validY && validR));

    return validX && validY && validR;
}


/*
* send data async
* */
/*$('#request-form').submit(function (event) {
    event.preventDefault();
    if (!validateInput()) {
        alert('at least one checkbox selected, the radio selected and a nice number for Y');
        return;
    }

    let action = "handler.php";
    let data = $(this).serialize();

    $.post(action, data, function (response) {
        if (response.RESULT_CODE === '0') {
            drawCanvas();
            response.RESULTS.map(item => {
                addToLocalStorage(item);
                addResultRow(item);
                drawPoint(item.x, item.y, item.r);
            });
        } else {
            alert(response.RESULTS);
            console.log(response.RESULTS);
        }
    });
});*/



$(window).resize(drawCanvas)
$(window).on("load", drawCanvas)
$("select#r").change(function () {
    drawCanvas();
    validateInput();
});