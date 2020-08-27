/*
* Filling the X and R inputs
* */
const valuesX = ['-5', '-4', '-3', '-2', '-1', '0', '1', '2', '3'];
const valuesR = ['1', '1.5', '2', '2.5', '3'];
for (const number of valuesX) {
    $('#options_x')
        .append(`<input type="checkbox" id="${number}" name="x[]" value="${number}" form="request-form">`)
        .append(`<label for="${number}">${number}</label>`);
}
for (const number of valuesR) {
    $('#options_r')
        .append(`<input type="radio" id="${number}" name="r" value="${number}" form="request-form" ${(number==='1') ? 'checked' : ''}>`)
        .append(`<label for="${number}">${number}</label>`);
}


/*
* validate input
* TODO: CORRECT WHEN THERE IS TEXT AFTER THE NUMBER
* */
function validateY(input) {
    let val = parseFloat(input.replace(',','.'));
    if (isNaN(val))
        return false;

    return val >= -5 && val <= 3;
}

function validateInput() {
    var validX, validY, validR;
    validY = validateY($('input#y').val());

    validX = $('input:checkbox').filter(':checked').length > 0;
    validR = $('input:radio').filter(':checked').length === 1;

    console.log('validX=' + validX + ', validY=' + validR + ', validR=' + validR);

    $('#submit-btn').attr('disabled', !(validX && validY && validR));

    return validX && validY && validR;
}


/*
* send data async
* */
$('#request-form').submit(function (event) {
    event.preventDefault();
    if (!validateInput()) {
        alert('at least one checkbox selected, the radio selected and a nice number for Y');
        return;
    }

    let action = "handler.php";
    let data = $(this).serialize();

    $.post(action, data, function (response) {
        if (response.RESULT_CODE === 0) {
            response.RESULTS.map(item => addResultRow(item));
        } else {
            alert(response.PROPS);
            console.log(response.RESULTS);
        }
    });
});


/*
* Adding results to the table
* */
function addResultRow(response) {
    $('.results-table #results_table_body').append(
        "<tr>" +
        "<td>" + response.x + "</td>" +
        "<td>" + response.y + "</td>" +
        "<td>" + response.r + "</td>" +
        "<td>" + response.result + "</td>" +
        "<td>" + response.currentTime + "</td>" +
        "<td>" + response.computedTime + "</td>" +
        "</tr>"
    );
}

$(window).resize(drawCanvas)
$(window).on("load", drawCanvas)
$("input[type='radio']").change(drawCanvas)